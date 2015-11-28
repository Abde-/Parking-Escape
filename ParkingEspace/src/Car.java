/**
 *
 * @author Abdeselam / Cedric
 */

public class Car {
    
    // variables 'final' indiquent que ces valeurs sont seulement assignées une
    // seule fois, ça ne sert pas à grande chose mais pour info c'est cool de savoir
    // que ces statuts ne changent pas (les voitures ne changent pas d'orientation
    // et la voiture goal reste goal
    
    private final boolean orient;
    private final boolean goal;
    
    public Car(int[] coord1,int[] coord2, boolean x){
        // pas de gestion des coordonnées, on suppose que c'est juste
        if (coord1[0] == coord2[0]) orient = true;
        else if (coord1[1] == coord2[1]) orient = false;
        
        goal = x;
    } 

    // créer methodes de mouvement etc
    
    
    
}
