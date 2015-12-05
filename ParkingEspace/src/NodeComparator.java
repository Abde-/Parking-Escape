/**
 *
 * @author Abdeselam, Cedric
 */
import java.util.Comparator;

public class NodeComparator implements Comparator<Node>{
    // classe pour comparer 2 noeuds, qu'on utilisera dans le Heap
    
    public int compare( Node first, Node second ){
        int res;
        if (first.getWeight() > second.getWeight()) res = 1;
        else if (first.getWeight() < second.getWeight()) res = -1;
        else res = 0;
        return res;
    }
    
}
