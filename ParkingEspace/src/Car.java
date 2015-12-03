/**
 *
 * @author Abdeselam / Cedric
 */

interface Car {
    
    public int moveFront(int[] dim);
    public int moveBehind(int[] dim);
    
    public boolean isIntersect(Car other);
    public boolean isInDomain(int[] coord);
    
    // voiture.isInDomain(Exit);
    
}
