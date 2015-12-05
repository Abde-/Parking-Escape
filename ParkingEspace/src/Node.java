/**
 *
 * @author Abdeselam / Cedric
 */

public class Node {
    
    private int weight; // poids total de toutes les arêtes du chemin
    private int[] dim;
    private Car[] cars;
    private Car goal;
    private int[] exit;
    QueueManager queue;
    
    public Node(Car[] voitures, Car paramgoal, int poids, int[] dimension, int[] ex, QueueManager q) {
        cars = new Car[voitures.length];
        for(int i = 0; i < voitures.length; ++i){
            cars[i] = new Car(voitures[i]);
        }
        goal = new Car(paramgoal);
        dim = dimension;
        exit = ex;
        queue = q;
    }
    
    public void extend(){
        // création des nouveaux noeuds correspondants à tous les mouvements
        // possibles à partir du noeud courrant et ajout au heap
        
        boolean forward = true, backward = false;
        int newWeight;
        
        // mouvements de la voiture goal
        // avant
        newWeight = goal.move(dim, cars, forward);
        if (newWeight != -1) queue.addToQueue(new Node(cars,goal,weight+newWeight,dim,exit,queue));
        goal.moveBack();
        
        // arrière
        newWeight = goal.move(dim, cars, backward);
        if (newWeight != -1) queue.addToQueue(new Node(cars,goal,weight+newWeight,dim,exit,queue));
        goal.moveBack();
        
        // mouvement des autres voitures
        for (int i = 0; i < cars.length; ++i){
            // avant
            newWeight = cars[i].move(dim, cars, forward);
            if (newWeight != -1) queue.addToQueue(new Node(cars,goal,weight+newWeight,dim,exit,queue));
            cars[i].moveBack();
            
            // arrière
            newWeight = cars[i].move(dim, cars, backward);
            if (newWeight != -1) queue.addToQueue(new Node(cars,goal,weight+newWeight,dim,exit,queue));
            cars[i].moveBack();
        }        
    }
    

    public String HashCode(){
        // fonction de 'hashage' par rapport aux coordonnées des voitures
        String res = "";
        res += Integer.toString(goal.copyBehind()[0]) + Integer.toString(goal.copyBehind()[1]) +
                Integer.toString(goal.copyFront()[0]) + Integer.toString(goal.copyFront()[1]);
        for (Car i: cars){
            res += Integer.toString(i.copyBehind()[0]) + Integer.toString(i.copyBehind()[1]) +
                Integer.toString(i.copyFront()[0]) + Integer.toString(i.copyFront()[1]);
        }
        return res;
    }
    
    public int getWeight(){
        return weight;
    }
    
    public boolean isResult(){
        return goal.isInDomain(exit);
    }
}
