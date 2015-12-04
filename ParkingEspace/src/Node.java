/**
 *
 * @author Abdeselam / Cedric
 */

import java.util.HashMap; // Pour vérifier que nouveau node dans Hashtamèyr

public class Node {
    
    private int weight; // poids chemin
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
        // création de nouveaux noeuds et rajouter à queue
        int newWeight = goal.move(dim, cars, true);
        if (newWeight != -1) queue.addToQueue(new Node(cars,goal,weight+newWeight,dim,exit,queue));
        newWeight = goal.move(dim,cars,false);
        if (newWeight != -1) queue.addToQueue(new Node(cars,goal,weight+newWeight,dim,exit,queue));
        
        Car[] tempCarlist = new Car[cars.length-1];
        
        
    }
    
    @Override 
    public int hashCode(){
        // fonction de hashage par rapport aux coordonnées des voitures
    }
    
    public int getWeight(){
        return weight;
    }
    
    public boolean isResult(){
        return goal.isInDomain(exit);
    }
}
