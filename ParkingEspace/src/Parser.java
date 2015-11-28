/**
 *
 * @author Abde / Cedric
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;


public class Parser {
    
    private Car goal;
    private Car[] cars;
    private int[] dimension;
    private int[] exit; 
    
    public Parser(String filepath) throws FileNotFoundException{
        // constructor ouvre fichier et affiche ce qu'il y a à l'interieur
        // reste juste qu'à parser. paramete filepath avec fichier.
        
        File x = new File(filepath);
        BufferedReader br = new BufferedReader(new FileReader(x));
        
        // BufferedReader -> lire de façon buffed, càd, chaque fois
        // une ligne -> that's why string = br.readLine != null
        
        try{  
            String string;
            while ((string = br.readLine()) != null){
                System.out.println(string);
            }
        }
        catch (IOException e){
            System.out.println(e);
        }
    }        
        
    public Car[] getCars(){
        return cars; //
    }
    
    public Car getGoal(){
        return goal;
    }
    
    public int[] getDimension(){
        return dimension; // array de 2 car X*Y (2 nombres)
    }
    public int[] getExit(){
        return exit; // (x,y) pour la sortie
    }
    
    /* créer chaque méthode
       pour parser X ou Y information, par exemple
    
        Car goal = new Parser.getGoal()
        Car[] cars = new Parser.getCars()
        int dimension = Parser.getDimension()
    
        etc~
            
        on peut créer une méthode principale qui fasse tout ça à la construction
        et puis mettre chaque truc dans des variables
    */
    
}
