
/**
 *
 * @author Abde / Cedric
 */

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
        
public class Solver {
    
    private boolean solved;
    private Node solution;
    private QueueManager heap;
    private int[] dim;
    private int[] exit;
    private boolean vertical;
    private int nbOfMoves;
    
    public Solver (Parser parsedInfo){
        // algorithme de résolution du problème
        
        dim = parsedInfo.getDimension();
        exit = parsedInfo.getExit();
        vertical = parsedInfo.getGoal().isVertical();
        
        // création du heap utilisé pour trier les noeuds selon leur priorité
        heap = new QueueManager();
        
        // création du premier noeud sur base des informations du parsing
        Node node = new Node(parsedInfo.getCars(), parsedInfo.getGoal(), 0, parsedInfo.getDimension(), parsedInfo.getExit(), heap);
        
        // initialisation de la queue
        heap.addToQueue(node);
        
        // tant qu'on a pas une solution et que le heap n'est pas vide
        // on extrait le sommet du heap et on l'étend
        while (!node.isResult() && !heap.isEmpty()){
            node = heap.getHeadOfQueue();
            node.extend();
        }
        
        // traitement du résultat
        solution = node;
        if (node.isResult()){
            solved = true;
            nbOfMoves = solution.getGoalCar().getNbOfMoves()-1;
            for (int i = 0; i < solution.getCarsList().length; ++i)
                nbOfMoves += solution.getCarsList()[i].getNbOfMoves()-1;  
        }
        else solved = false;
    }
    
    public void printResult(){
        
        System.out.println();
        System.out.println("Le parking a une dimension "+Integer.toString(dim[0]) + " fois "+ Integer.toString(dim[1]));
        System.out.println("Il contient 1 Goal car et "+Integer.toString(solution.getCarsList().length)+ " autres voitures.") ;
        
        solution.getGoalCar().printFirstPlace();
        for (int i = 0; i < solution.getCarsList().length; ++i)
            solution.getCarsList()[i].printFirstPlace();
          
        System.out.println();
            
        if (solved){    
 
            for (int i = 0; i < solution.getCarsList().length; ++i){
                solution.getCarsList()[i].printSteps();
                System.out.println();
            }
            solution.getGoalCar().printSteps();
            System.out.println();

            System.out.println("Une façon de sortir du parking en "+Integer.toString(nbOfMoves)+" mouvements a été trouvée.");

        }
        else {
            System.out.println("Il n'y a pas moyen de sortir du parking.");
            solution.getGoalCar().printNoSolution();
        }
    }
    
    public void writeSolution(String path) throws FileNotFoundException, UnsupportedEncodingException{
        PrintWriter writer = new PrintWriter(path,"UTF-8");
        
        writer.println("Situation finale:");
        writeMatrix(writer);
        
        if (solved)
            writer.println("Une façon de sortir du parking en "+Integer.toString(nbOfMoves)+" mouvements a été trouvée.");
        else{
            writer.println("Pas moyen de sortir du parking.");
        }
        writer.println();
        
        solution.getGoalCar().writeSteps(writer);
        for (int i = 0; i < solution.getCarsList().length; ++i)
            solution.getCarsList()[i].writeSteps(writer);
        
        writer.close();
    }
    
    public void writeMatrix(PrintWriter writer){
        
        char[][] carMatrix = new char[dim[0]][dim[1]];
        for(int i = 0; i < dim[0]; ++i) for (int j = 0; j < dim[1]; ++j) carMatrix[i][j] = ' ';
        
        // création de nouvelle matrice qui représente les emplacements des voitures
        int[] currentFront = solution.getGoalCar().copyFront(), currentBehind = solution.getGoalCar().copyBehind();
        carMatrix[currentFront[0]][currentFront[1]] = solution.getGoalCar().getID().charAt(0);
        carMatrix[currentBehind[0]][currentBehind[1]] = solution.getGoalCar().getID().charAt(0);
        
        for (Car i: solution.getCarsList()){
            currentFront = i.copyFront(); currentBehind = i.copyBehind();
            carMatrix[currentFront[0]][currentFront[1]] = i.getID().charAt(0);
            carMatrix[currentBehind[0]][currentBehind[1]] = i.getID().charAt(0);
        }
        
        // création ligne par ligne de la matrice
        String line;
        for(int i = 0; i < dim[0]*2+1; ++i){
            line = "";
                
            if(i == 0 || i == dim[0]*2){
                for(int j = 0; j < dim[1]; ++j){
                    if(exit[0] == i/2 && exit[1] == j && vertical) line += "+   ";
                    else line += "+---";
                }
                line += "+";
            }
            else if(i % 2 == 1){
                for(int j = 0; j < dim[1]; ++j){
                    String pre = " ";
                    if (j == 0)
                        pre =  ((exit[0] == i/2 && exit[1] == j && !vertical) ? " " : "|");
                    line +=pre+ " "+ carMatrix[i/2][j] + " ";
                }
                String pre = "|";
                if ( exit[0] == i/2 && exit[1] == dim[1]-1 && !vertical){
                    pre = " ";
                }
                line += pre;
            }
            else{
                for(int j = 0; j < dim[1]; ++j){
                    line += "+   ";
                }
                line += "+";
            }
            writer.println(line);
        }

    }
}
