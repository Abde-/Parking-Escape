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
    private HashMap<String,Integer> hashTable;

    public QueueManager() {
        heap = new PriorityQueue<>(comparator);
        hashTable = new HashMap<>();
    }
    
    public void addToQueue(Node newNode){
        if (!isAlreadyExtended(newNode)) heap.add(newNode);
    }
    
    private boolean isAlreadyExtended(Node newNode){
        // la clé sera les coordonnées de toutes les voitures concatenées
        boolean res = false;
        if (hashTable.get(newNode.HashCode()) != null){
            res = true;
        }
        else hashTable.put(newNode.HashCode(), 0 );
        return res;
    }
}
