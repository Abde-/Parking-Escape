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
    private final HashMap<Node,Integer> hashtable;

    public QueueManager() {
        heap = new PriorityQueue<>(comparator);
        hashtable = new HashMap<>();
    }
    
    public void addToQueue(Node newNode){
        if (isInHash(newNode)) heap.add(newNode);
    }
    
    private boolean isInHash(Node newNode){
        res = false;
        if (  )
    }
}
