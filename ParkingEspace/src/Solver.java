
/**
 *
 * @author Abde / Cedric
 */
public class Solver {
    
    String solution;
    QueueManager heap;
    
    public Solver (Parser parsedInfo){
        // algorithme de résolution du problème
        
        // création du heap utilisé pour trier les noeuds selon leur priorité
        heap = new QueueManager();
        
        // création du premier noeud sur base des informations du parsing
        Node node = new Node(parsedInfo.getCars(), parsedInfo.getGoal(), 0, parsedInfo.getDimension(), parsedInfo.getExit(), heap);
        // et ajout du noeud dans le heap
        heap.addToQueue(node);
        
        do {
            node = heap
        } while ()
        
        
        
    }
    
    public void print(){
        System.out.println(solution);
    }
    
}
