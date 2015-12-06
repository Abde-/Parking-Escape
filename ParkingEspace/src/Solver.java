
/**
 *
 * @author Abde / Cedric
 */
public class Solver {
    
    private boolean solved;
    private Node solution;
    private QueueManager heap;
    
    public Solver (Parser parsedInfo){
        // algorithme de résolution du problème
        
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
            int nbOfMoves = solution.getGoalCar().getNbOfMoves();
            for (int i = 0; i < solution.getCarsList().length; ++i)
                nbOfMoves += solution.getCarsList()[i].getNbOfMoves();       
            
            System.out.println("Une façon de sortir du parking en "+Integer.toString(nbOfMoves)+"mouvements a été trouvée");
        }
        else {
            System.out.println("Il n'y a pas moyen de sortir du parking");
        }
    }
    
}
