/**
 *
 * @author Abdeselam / Cedric
 */


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
        goal.goBack();
        
        newWeight = goal.move(dim,cars,false);
        if (newWeight != -1) queue.addToQueue(new Node(cars,goal,weight+newWeight,dim,exit,queue));
        goal.goBack();
        
        Car[] tempCarlist;
        
        for (int i = 0; i < cars.length; ++i){
            
        }
        // à finir
        
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
