
/**
 *
 * @author Abde / Cedric
 */
public class Solver {
    
    boolean solved;
    Node solution;
    QueueManager heap;
    
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
            System.out.println("Une façon de sortir du parking"+"en %d mouvements a été trouvée");
        }
        System.out.println(solution);
    }
    
}
