
/**
 *
 * @author Abde / Cedric
 */
public class Solver {
    
    private boolean solved;
    private Node solution;
    private QueueManager heap;
    private int[] dim;
    private int[] exit;
    private boolean vertical;
    
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
        if (node.isResult()) solved = true;
        else solved = false;
    }
    
    public void printResult(){
        if (solved){
            printMatrix();
            int nbOfMoves = solution.getGoalCar().getNbOfMoves()-1;
            for (int i = 0; i < solution.getCarsList().length; ++i)
                nbOfMoves += solution.getCarsList()[i].getNbOfMoves()-1;       
            
            System.out.println("Une façon de sortir du parking en "+Integer.toString(nbOfMoves)+" mouvements a été trouvée");
            
            solution.getGoalCar().printFirstPlace();
            for (int i = 0; i < solution.getCarsList().length; ++i)
                solution.getCarsList()[i].printFirstPlace();
            
            System.out.println();
            
            solution.getGoalCar().printSteps();
            for (int i = 0; i < solution.getCarsList().length; ++i)
                solution.getCarsList()[i].printSteps();
        }
        else {
            System.out.println("Il n'y a pas moyen de sortir du parking");
            solution.getGoalCar().printNoSolution();
        }
    }
    
    public void printMatrix(){
        /////////////////////////////// nouvelle fonction - fonction temporaire
        char[][] carMatrix = new char[dim[0]][dim[1]];
        for(int i = 0; i < dim[0]; ++i) for (int j = 0; j < dim[1]; ++j) carMatrix[i][j] = ' ';
            
        int[] currentFront = solution.getGoalCar().copyFront(), currentBehind = solution.getGoalCar().copyBehind();
        carMatrix[currentFront[0]][currentFront[1]] = solution.getGoalCar().getID().charAt(0);
        carMatrix[currentBehind[0]][currentBehind[1]] = solution.getGoalCar().getID().charAt(0);
        
        for (Car i: solution.getCarsList()){
            currentFront = i.copyFront(); currentBehind = i.copyBehind();
            carMatrix[currentFront[0]][currentFront[1]] = i.getID().charAt(0);
            carMatrix[currentBehind[0]][currentBehind[1]] = i.getID().charAt(0);
        }
            
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
            System.out.println(line);
        }
        //////////////////////////////////// nouvelle fonction - fonction temporaire
    }
}
