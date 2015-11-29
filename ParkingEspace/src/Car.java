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
    
    public int move(boolean x, int[] dim, Car[] cars){
        // boolean x indique vers quelle direction la voiture va
        int weight = 0;
        int[] before = new int[2];
        // if x -> voiture avance vers le front, sinon vers l'arrière
        // pour la voiture goal, avancer vers le front = vers l'entrée

        
        // cette partie est un peu lourde, à toi de voir si changer :P
        // je pense qu'il y a moyen de faire plus court, je verrai à la fin du projet... x'D
        
        if(x){
            // bouger vers le front
            int[] newFront = new int[2];
            if(vertical){
                
                newFront[0] = front[0]; newFront[1] = front[1]+1;
                if (newFront[1] < dim[1] && intersection(newFront,cars)){ 
                    before = front;
                    behind[1] = front[1]; front[1] = newFront[1];
                }
                else{
                    weight = -1;
                }
            }
            else{
                newFront[1] = front[1]; newFront[0] = front[0]+1;
                if ( newFront[0] < dim[0] && intersection(newFront,cars) ){ 
                    before = front;
                    behind[0] = front[0]; front[0] = newFront[0];
                }
                else{
                    weight = -1;
                }
            }
            // attribution du poids du mouvement
            if (goal && weight != -1){ 
                if(closerToExit(before,front)){ weight = 0; }
                else{ weight = 2; }
            }
            else if ( weight != -1 ){ weight = 1; }

        }
        else{
            // bouger vers l'arriere
            int[] newBehind = new int[2];
            if(vertical){
                newBehind[0] = behind[0]; newBehind[1] = behind[1]-1;
                if (newBehind[1] >= 0 && intersection(newBehind,cars)){
                    before = front;
                    front[1] = behind[1]; behind[1] = newBehind[1];
                }
            }
            else{
                newBehind[1] = behind[1]; newBehind[0] = behind[0]-1;
                if (newBehind[1] >= 0 && intersection(newBehind,cars)){ 
                    before = front;
                    front[0] = behind[0]; behind[0] = newBehind[0];
                }
            }
            // attribution du poids du mouvement
            if (goal && weight != -1){ 
                if(closerToExit(before,front)){ weight = 0; }
                else{ weight = 2; }
            }
            else if ( weight != -1 ){ weight = 1; }

        }
        
        // return poids du mouvement, si weight == -1, mouvement impossible
        // voiture sort du parking
        return weight;
    }
    
    private boolean closerToExit(int[] before, int [] after){
        // regarde si une des coordonnées de la voiture est plus proche du exit ou pas
        boolean res = false;
        
        if( before[0] != after[0]){
            res = Math.abs(before[0]-exit[0]) < Math.abs(after[0]-exit[0]) ;
        }
        else if (before [1] != after[1]){
            res = Math.abs(before[1]-exit[1]) < Math.abs(after[1]-exit[1]) ;
        }
        
        return res;
    }
    
    private boolean intersection(int[] coord ,Car[] cars){
        boolean res = true;
        for( Car j : cars){
            if (coord[0] == j.getFront()[0] || coord[1] == j.getFront()[1] ||
                coord[0] == j.getBehind()[0] || coord[1] == j.getBehind()[1]){
                    res = false;
            }
        }
        return res;
    }
    
    public int[] getFront(){
        return front;
    }
    public int[] getBehind(){
        return behind;
    }

}
