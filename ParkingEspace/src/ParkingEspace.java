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
        
        // lecture et parsing du fichier de données
        Parser parsedInfo = new Parser("test/test.txt");
        
        // résolution du problème
        Solver solution = new Solver(parsedInfo);
        
        System.out.println("Exit: " + Integer.toString(parsedInfo.getExit()[0])+" "+Integer.toString(parsedInfo.getExit()[1]));
        
        Car goalCar = parsedInfo.getGoal();
        for(int i = 0; i < 4; ++i){
            goalCar.printSteps("Goal");
            System.out.println(goalCar.move(parsedInfo.getDimension(), new Car[] {parsedInfo.getCars()[0]}, true));
        }
    }
}
