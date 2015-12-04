import java.util.Stack;
import java.util.Iterator;
/**
 *
 * @author Abdeselam / Cedric
 */

public class Car {
    
    private Stack<int[][]> steps;
    private final boolean goal;
    
    private final int orient; // la direction vers laquelle bouge la voiture
    private final boolean exitorient; // la direction vers laquelle est l'exit
    
    private int[] front = new int[2];
    private int[] behind = new int[2];
    
    public Car(int[] coord1, int[] coord2, boolean x, int[] exit){
        goal = x;
        orient = coord1[0] != coord2[0] ? 0 : 1;
        
        // le front sera toujours celui le plus proche à la sortie
        if ( coord1[orient] > coord2[orient] ){
            front[0] = coord1[0]; front[1] = coord1[1];
            behind[0] = coord2[0]; behind[1] = coord2[1];
        }
        else{
            front[0] = coord2[0]; front[1] = coord2[1];
            behind[0] = coord1[0]; behind[1] = coord1[1];
        }
        
        exitorient = Math.abs(front[orient] - exit[orient]) < Math.abs(behind[orient] - exit[orient]);
        
        // on rajoute l'étape -> situation initiale -> new int car copie des valeurs, si on rajoutait
        // directenemt new int[][] -> on copie la référence de l'objet du coup on aura une liste d'éléments identiques
        steps.add(new int[][] { new int[] {behind[0],behind[1]}, new int[] {front[0],front[1]}});
    }
    
    ////////////////////////////////////// à tester
    // oui Cedric, finalement on passe la liste de Cars et on fait le mouvement
    // par rapport à ce check
    public int moveFront(int[] dim, Car[] cars, boolean direction){
        int movement = direction ? 1 : -1; // si on bouge vers l'arrière ou vers le front
        
        // mise à jour coordonnées
        behind[orient] = behind[orient] +movement; front[orient] = front[orient] +movement;
        
        // attribution de poids de mouvement
        int weight = goal ? 0 : 1;
        if (weight == 2 && exitorient == direction) weight = 2; // si la voiture goal bouge vers l'exit;
        
        // vérification que mouvement possible
        if (behind[orient] >= 0 || front[orient] < dim[orient]){
            int i = 0;
            while ( i < cars.length && weight != -1 ){
                if(this.isIntersect(cars[i])){
                    weight = -1;
                }
                i +=1;
            }
        }
        else weight = -1;
        
        // si mouvement valide on rajoute le mouvement dans stack de mouvements, sinon
        // on reprends les dernières coordonnées possibles
        if (weight == -1){
            behind[0] = steps.lastElement()[0][0]; behind[1] = steps.lastElement()[0][1];
            front[0] = steps.lastElement()[1][0]; front[1] = steps.lastElement()[1][1];
        }
        else steps.add(new int[][] { new int[] {behind[0],behind[1]}, new int[] {front[0],front[1]}});

        return weight;
    }
    ////////////////////////////////////// à tester
    
    public boolean isIntersect(Car other){
        boolean res = false;
        if (other.isInDomain(front) || other.isInDomain(behind)){
            res = true;
        }
        return res;
    }

    public boolean isInDomain(int[] coord){
        boolean res = false;
        if ( (coord[0] == front[0] && coord[1] == front[1]) &&
                (coord[0] == behind[0] && coord[1] == behind[1]) ){
            res = true;
        }
        return res;
    }
    
    public void printSteps(String carName){
        Iterator<int[][]> stepIterator = steps.iterator();
        int[][] current = stepIterator.next();
        
        String res = "Voiture "+ carName + ": ";
        res += "[("+Integer.toString(current[0][0]) + "," + Integer.toString(current[0][1]) + "),(" +
                Integer.toString(current[1][0]) + "," + Integer.toString(current[1][1]) + ")] ";
        
        while(stepIterator.hasNext()){
            current = stepIterator.next();
            res += "-> ";
            res += "[("+Integer.toString(current[0][0]) + "," + Integer.toString(current[0][1]) + "),(" +
                    Integer.toString(current[1][0]) + "," + Integer.toString(current[1][1]) + ")] ";
        }
        
        System.out.println(res);
    }
}
