/**
 *
 * @author Abdeselam / Cedric
 */

interface Car {
    
    // variables 'final' indiquent que ces valeurs sont seulement assignées une
    // seule fois, ça ne sert pas à grande chose mais pour info c'est cool de savoir
    // que ces statuts ne changent pas (les voitures ne changent pas d'orientation
    // et la voiture goal reste goal
    
    // -- on suppose que front est la partie la plus proche de la fin (le haut pour le vertical
    
    // -- et la droite pour l'horizontal)
        
    public int moveFront(int[] dim);
    public int moveBehind(int[] dim);
    
    public boolean inExit(int[] exit);

    public boolean isIntersect(Car other);

}
