/**
 *
 * @author Abde / Cedric
 */

import java.util.PriorityQueue;
import java.util.HashMap;

public class QueueManager {
    // Classe qui implémente un heap afin de gérer la file de priorités des noeuds
    // c'est-à-dire la composante heuristique (les poids différents des arêtes).
    // Durant l'ajout d'un élément dans le heap, la méthode isAlreadyExtended
    // va vérifier si on a déjà traité le noeud. Voir plus de détail dans notre rapport
    
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
    
    public Node getHeadOfQueue() {
        return(heap.poll());
    }
    
    private boolean isAlreadyExtended(Node newNode){
        // Vérifie si on a déjà traité ce noeud. Si oui il ne sera pas rajouté au heap
        // si non on va l'ajouter à la liste des noeuds déjà traités en hashant
        // la liste des coordonnées concaténées de toutes les voitures

        boolean res = false;
        if (hashTable.get(newNode.hashString()) != null){
            res = true;
        }
        else hashTable.put(newNode.hashString(), 0 );
        return res;
    }
}
