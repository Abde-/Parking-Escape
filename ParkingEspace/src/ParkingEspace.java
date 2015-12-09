/**
 *
 * @author Abdeselam / Cedric
 */

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

public class ParkingEspace {

    /**
     * @param args le path du fichier test (pour NetBeans on teste directement dans la ligne 16)
     * @throws FileNotFoundException  
     */
        
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException{
        long depart = System.currentTimeMillis();
        // lecture et parsing du fichier de données
        Parser parsedInfo = new Parser(args[0]);
        
        // résolution du problème
        Solver solution = new Solver(parsedInfo);
        solution.printResult();
        String newPath = args[0].replace(".txt","")+"_output.txt";
        solution.writeSolution(newPath);
        
        System.out.println(System.currentTimeMillis() - depart + " ms") ;
    }
}
