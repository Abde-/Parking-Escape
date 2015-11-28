/**
 *
 * @author Abdeselam / Cedric
 */

import java.lang.Math;

public class Car {
    
    // variables 'final' indiquent que ces valeurs sont seulement assignées une
    // seule fois, ça ne sert pas à grande chose mais pour info c'est cool de savoir
    // que ces statuts ne changent pas (les voitures ne changent pas d'orientation
    // et la voiture goal reste goal
    
    private final boolean vertical;
    private final boolean goal;
    private int[] front;
    private int[] behind;
    
    public Car(int[] coord1,int[] coord2, boolean x, int[] exit){
        // pas de gestion des coordonnées, on suppose que c'est juste
        vertical = coord1[1] == coord2[1];
        goal = x; 
        
        if(goal){
            int index = 0;
            
            if (vertical){
                index = 1;
            }
            if (Math.abs(coord1[index]-exit[index]) < Math.abs(coord2[index]-exit[index])){
                front = coord1; behind = coord2;
            }
            else{
                behind = coord1; front = coord2;
            }
        }
        
    }
    
    public int move(boolean x, int[] dim){
        // boolean x indique vers quelle direction la voiture va
        int weight;
        // if x -> voiture avance vers le front, sinon vers l'arrière
        // pour la voiture goal, avancer vers le front = vers l'entrée
        
        
        
        if(x){
            if(){
                // à finir chez moi
            }
            
        }
        else{
            
        }
        // return poids du mouvement, si weight == -1, mouvement impossible
        // voiture sort de
        return weight;
    }

    // créer methodes de mouvement etc
    
    
    
}
