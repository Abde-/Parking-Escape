/**
 *
 * @author Abdeselam / Cedric
 */

public class Car {
    
    // variables 'final' indiquent que ces valeurs sont seulement assignées une
    // seule fois, ça ne sert pas à grande chose mais pour info c'est cool de savoir
    // que ces statuts ne changent pas (les voitures ne changent pas d'orientation
    // et la voiture goal reste goal
    
    private final boolean vertical;
    private final boolean goal;
    
    // -- on suppose que front est la partie la plus proche de la fin (le haut pour le vertical
    private int[] front;
    private int[] behind;
    // -- et la droite pour l'horizontal)
    
    private final int[] exit;
    
    public Car(int[] coord1,int[] coord2, boolean x, int[] ex){
        // pas de gestion des coordonnées, on suppose que c'est juste
        vertical = coord1[1] == coord2[1];
        goal = x;
        exit = ex;
        
        if(goal){
            int index = 0;
            
            if (vertical){
                index = 1;
            }
            if ( coord1[index] > coord2[index] ){
                front = coord1; behind = coord2;
            }
            else{
                behind = coord1; front = coord2;
            }
        }
    }
    
    public int move(boolean x, int[] dim){
        // boolean x indique vers quelle direction la voiture va
        int weight = 0;
        // if x -> voiture avance vers le front, sinon vers l'arrière
        // pour la voiture goal, avancer vers le front = vers l'entrée

        
        if(x){
            // bouger vers le front
            if(vertical){
                if (front[1]+1 < dim[1] ){ front[1] = front[1] +1; behind[1] = behind[1] +1;
                    weight = -1;}
            }
            else{
                if (front[0]+1 < dim[0] ){ front[0] = front[0] +1; behind[0] = behind[0] +1; 
                    weight = -1;}
            }
            // -- à modifier -> par rapport à la sortie
            if (goal && weight != -1){ weight = 0;}
            else if ( weight != -1 ){ weight = 1; }
            // -- à modifier
        }
        else{
            // bouger vers l'arriere
            if(vertical){
                if (behind[1]-1 >= 0 ){ front[1] = front[1] -1; behind[1] = behind[1] -1;
                    weight = -1;}
            }
            else{
                if (behind[0]-1 >= 0 ){ front[0] = front[0] -1; behind[0] = behind[0] -1; 
                    weight = -1;}
            }
            // -- à modifier -> par rapport à la sortie
            if (goal && weight != -1){ weight = 2;}
            else if ( weight != -1 ){ weight = 1; }
            // -- à modifier
        }
        
        // return poids du mouvement, si weight == -1, mouvement impossible
        // voiture sort du parking
        return weight;
    }
    
    public int[] getFront(){
        return front;
    }
    public int[] getBehind(){
        return behind;
    }
    // créer methodes de mouvement etc
}
