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
    /* créer chaque méthode
       pour parser X ou Y information, par exemple
    
        Car goal = Parser.getGoal()
        Car[] cars = Parser.getCars()
        int dimension = Parser.getDimension()
    
        etc~
            
        on peut créer une méthode principale qui fasse tout ça à la construction
            -> méthodes crées ~ parseDim pour parser chaque truc dans des methodes
            différentes, juste for the structure
        et puis mettre chaque truc dans des variables
    */
        String[] criteres = {"Parking", "Elements"};
        
        // constructor ouvre fichier et affiche ce qu'il y a à l'interieur
        // reste juste qu'à parser. paramete filepath avec fichier.
        
        File x = new File(filepath);
        BufferedReader br = new BufferedReader(new FileReader(x));
        
        // BufferedReader -> lire de façon buffed, càd, chaque fois
        // une ligne -> that's why string = br.readLine != null
        System.out.println(Integer.parseInt(" 5".trim()));
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
            }
        }
        catch (IOException e){
            System.out.println(e);
        }
    }
    
    private void parseDim(String toParse, BufferedReader br) throws FileNotFoundException{
        // cette methode va parser les dimensions et l'endroit de l'exit
        try{
            String[] dimensionString = toParse.substring("Parking:".length()).split("fois");
            
            dimension = new int[2];
            dimension[0] = Integer.parseInt(dimensionString[0].trim());
            dimension[1] = Integer.parseInt(dimensionString[1].trim());
            exit = new int[2]; exit[0] = -1; exit[1] = -1;
            
            String string; int i = 0;
            
            System.out.println(Boolean.toString(i <= dimension[0]*2));
            
            while ((i <= dimension[0]*2) && (exit[0] == -1)){
                string = br.readLine();
                
                if (i == 0 || i == dimension[0]*2){
                    for (int j = 0; j < dimension[1]; j++){
                        if(!string.substring(4*j,4*(j+1)).equals("+---")){
                            if (i == 0) exit[0] = 0;
                            else exit[0] = dimension[0] -1;
                            exit[1] = j;
                        } 
                    }
                }
                
                else if (i % 2 != 0){
                    if (string.charAt(0) != '|'){
                        System.out.print(string);
                        exit[0] = (i+1) / 2;
                        exit[1] = 0;
                    }
                    else if (string.charAt(string.length()-1) != '|'){
                        exit[0] = i / 2;
                        exit[1] = dimension[0]-1;
                    }
                }
                i += 1;
            }
        }
        catch (IOException e){
            System.out.println(e);
        }
        
    // exemple de print pour check -> ok

    }
    
    private void parseCars(String toParse, BufferedReader br) throws FileNotFoundException{
        System.out.println("FoundCars");
        // exemple de print pour check -> ok
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
}
