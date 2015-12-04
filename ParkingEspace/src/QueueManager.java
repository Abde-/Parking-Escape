/**
 *
 * @author Abde / Cedric
 */

import java.util.PriorityQueue;
import java.util.HashMap;

public class QueueManager {
     // class qui va gérer la file de priorités des noeuds
    
    private final NodeComparator comparator = new NodeComparator();
    private final PriorityQueue<Node> heap;
    private HashMap<String,Integer> hashtable;

    public QueueManager() {
        heap = new PriorityQueue<>(comparator);
        hashtable = new HashMap<>();
    }
    
    public void addToQueue(Node newNode){
        if (!isInHash(newNode)) heap.add(newNode);
    }
    
    private boolean isInHash(Node newNode){
        // la clé sera les coordonnées de toutes les voitures concatenées
        boolean res = false;
        if (hashtable.get(newNode.HashCode()) != null){
            res = true;
        }
        else hashtable.put(newNode.HashCode(), 0 );
        return res;
    }
}
