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
        long depart = System.currentTimeMillis();
        // lecture et parsing du fichier de données
        Parser parsedInfo = new Parser("test/test.txt");
        
        // résolution du problème
        Solver solution = new Solver(parsedInfo);
        solution.printResult();
        
        System.out.println(System.currentTimeMillis() - depart + " ms") ;
    }
}
