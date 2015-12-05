import java.util.Stack;
import java.util.Iterator;
/**
 *
 * @author Abdeselam / Cedric
 */

public class Car {
    
    private Stack<int[][]> steps = new Stack<>();  // stack des déplacements
    private final boolean goal;
    private int lastIntersect;
    
    private final int orient; // la direction vers laquelle bouge la voiture (vertical: 0 ou horizontal: 1)
    private final boolean exitOrient; // la direction vers laquelle est l'exit
    
    private int[] front = new int[2];
    private int[] behind = new int[2];
    private int[] RecoverFront = new int[2];
    private int[] RecoverBehind = new int[2];
    
    public Car(int[] coord1, int[] coord2, boolean isGoalCar, int[] exit){
        goal = isGoalCar;
        orient = coord1[0] != coord2[0] ? 0 : 1;    // (vertical: 0 ou horizontal: 1)
        
        // le front sera toujours celui le plus proche de la sortie
        if ( coord1[orient] > coord2[orient] ){
            front[0] = coord1[0]; front[1] = coord1[1];
            behind[0] = coord2[0]; behind[1] = coord2[1];
        }
        else{
            front[0] = coord2[0]; front[1] = coord2[1];
            behind[0] = coord1[0]; behind[1] = coord1[1];
        }
        
        exitOrient = Math.abs(front[orient] - exit[orient]) < Math.abs(behind[orient] - exit[orient]) ;
        
        // on rajoute l'étape -> situation initiale -> new int car copie des valeurs, si on rajoutait
        // directenemt new int[][] -> on copie la référence de l'objet du coup on aura une liste d'éléments identiques
        steps.add(new int[][] { {behind[0],behind[1]},{front[0],front[1]}});
    }
    
    // constructeur de copie
    public Car(Car other){
        steps = other.copyStack();
        front = other.copyFront();
        behind = other.copyBehind();
        RecoverFront = other.copyRecoverFront();
        RecoverBehind = other.copyRecoverBehind();
        lastIntersect = other.lastIntersect;
        goal = other.isGoal();
        exitOrient = other.getExitOrient();
        orient = other.getOrient();
    }

    public int move(int[] dim, Car[] cars, boolean direction){
        int movement = direction ? 1 : -1; // +1 si on bouge vers l'avant et -1 en arrière
        int weight = -1; // le poids -1 est une indication d'un mouvement impossible
        
        // sauvegarde des dernières coordonnées pour retourner à sa dernière position
        RecoverBehind[0] = behind[0]; RecoverBehind[1] = behind[1];
        RecoverFront[0] = front[0]; RecoverFront[1] = front[1];
        
        // mise à jour coordonnées
        behind[orient] = behind[orient] +movement; front[orient] = front[orient] +movement;    
        
        // rajout du mouvement dans stack de mouvements
        steps.add(new int[][] { {behind[0],behind[1]},{front[0],front[1]} });
        
        // vérification que le mouvement est possible
        
        // si la voiture goal est en collision avec une autre, on sauvegarde l'état
        // pour pouvoir donner une raison si on ne trouve pas de solution
        int IntersectWith = this.IntersectWithACar(cars);
        
        if (IntersectWith >= 0){
            // si goal, sauvegarde de l'indice de la voiture qui l'a bloqué
            if (goal) lastIntersect = IntersectWith;
        }
        else if (this.isInGrid(dim)) {
            // attribution du poids relatif au mouvement
            // composante heuristique, si goal va vers la sortie, le poids est de 0
            // par contre si goal séloigne de la sortie, le poids est de 2
            // pour toute autre voiture que goal tout déplacement a un poids de 1
            // voir plus d'informations dans notre rapport
            if (goal){
                if(exitOrient == direction) weight = 0;
                else weight = 2;
            }
            else weight = 1;
        }
        return weight;
    }
       
    public boolean isInGrid(int[] dim){
        return (behind[orient] >= 0 && front[orient] < dim[orient]);
    }
    
    public int IntersectWithACar(Car[] cars){
        int i = 0, intersectWith = -1;
        while ( i < cars.length && intersectWith == -1 ){
            if(this.isIntersect(cars[i])){
                intersectWith = i;
            }
            i +=1;
        }
        return intersectWith;
    }
    
    public boolean isIntersect(Car other){
        boolean res = false;
        if (other.isInDomain(front) || other.isInDomain(behind)){
            res = true;
        }
        return res;
    }

    public boolean isInDomain(int[] coord){
        boolean res = false;
        if ( (coord[0] == front[0] && coord[1] == front[1]) ||
                (coord[0] == behind[0] && coord[1] == behind[1]) ){
            res = true;
        }
        return res;
    }
    
    public void moveBack(){
        // rétablit les coordonnées de la position précédente
        behind[0] = RecoverBehind[0]; behind[1] = RecoverBehind[1];
        front[0] = RecoverFront[0]; front[1] = RecoverFront[1];
        
        // rétablit le chemin précédent
        steps.pop();
    }
    
    // afficher etapes
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
        
    public boolean isGoal(){
        return goal;
    }
    
    // méthodes utilisées pour copier la voiture
    public Stack copyStack(){
        return (Stack<Integer>)steps.clone();
    }
    public int[] copyFront(){
        return front.clone();
    }
    public int[] copyBehind(){
        return behind.clone();
    }
    public int[] copyRecoverFront(){
        return RecoverFront.clone();
    }
    public int[] copyRecoverBehind(){
        return RecoverBehind.clone();
    }
    public int getOrient(){
        return orient;
    }
    public boolean getExitOrient(){
        return exitOrient;
    }
}
