/**
 *
 * @author Abde / Cedric
 */

import java.io.*;


public class Parser {
    
    private Car goal;
    private Car[] cars;
    private int[] dimension;
    private int[] exit; 
    
    public Parser(String filepath) throws FileNotFoundException{
        
        String[] criteres = {"Parking", "Elements", "Emplacements"};
        
        // constructor ouvre fichier et affiche ce qu'il y a à l'interieur
        // reste juste qu'à parser. paramete filepath avec fichier.
        
        File x = new File(filepath);
        BufferedReader br = new BufferedReader(new FileReader(x));
        
        // BufferedReader -> lire de façon buffed, càd, chaque fois
        // une ligne -> that's why string = br.readLine != null
        
        try{  
            String string;
            while ((string = br.readLine()) != null){
                for ( int i = 0 ; i < criteres.length ; ++i){
                    if ( string.length() > criteres[i].length() && 
                            string.substring(0,criteres[i].length()).equals(criteres[i]) ){
                        // la condition de ouf x'D
                        switch(i){
                            // on donne le BufferedReader à l'endroit où on trouve la ligne correspondante
                            // et on s'occupe de parser dans chaque info dans chaque méthode 
                            case 0: parseDim(string,br); break;
                            case 1: parseCars(string,br); break;
                        }
                    } 
                }
                System.out.println(string);
            }
        }
        catch (IOException e){
            System.out.println(e);
        }
    }
    
    private void parseDim(String toParse, BufferedReader br){
        System.out.println("FoundDim");
        // exemple de print pour check -> ok

    }
    
    private void parseCars(String toParse, BufferedReader br){
        System.out.println("FoundCars");
        // exempel de print pour check -> ok
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
