/**
 *
 * @author Abdeselam / Cedric
 */

public class Car {
    
    private boolean orient;
    private boolean goal;
    
    public Car(int[] coord1,int[] coord2, boolean x){
        // pas de gestion des coordonnées, on suppose que c'est juste
        if (coord1[0] == coord2[0]) orient = true;
        if (coord1[1] == coord2[1]) orient = false;
        
        goal = x;
    } 

    // créer methodes de mouvement etc
    
    
    
}
