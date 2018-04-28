/**
 * @Authors Melchor Dominguez, April Crawford
 * Group that monitors information and can perform tallies and send messages 
 */
public class Messanger implements Callabe<Integer>{
    
    /** Array  holding semaphores for the materials for sandwhiches*/
    private Semaphore[] materials;

    /** Array holding semaphores for the miners to make the sandwhiches*/
    private Semaphore[] miners;
    
    /** numbered constants for the materials/miners*/
    private static final int BREAD = 0;
    private static final int CHEESE = 1;
    private static final int BOLOGNA = 2;

    /**
     * Constructor for messanger where Semaphores are gathered
     * @param docks - locations for the semaphores 
     */
    public Messanger (Dock docks){
        materials = docks.getMaterials();
        miners = docks.getMiners();
    }//end Messanger 

    public 
}//end Messanger class
