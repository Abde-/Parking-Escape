/**
 *
 * @author Abdeselam / Cedric
 */

import java.io.FileNotFoundException;

public class ParkingEspace {

    /**
     * @param args le path du fichier test (pour NetBeans on teste directement dans la ligne 16)
     * @throws FileNotFoundException 
     */
    
    public static void main(String[] args) throws FileNotFoundException{
        Parser x = new Parser("test/test.txt");
        
        System.out.println("Exit: " + Integer.toString(x.getExit()[0])+" "+Integer.toString(x.getExit()[1]));
        
        
    }
}
