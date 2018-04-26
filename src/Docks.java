import java.util.concurrent.Semaphore;

/**
 * @Author - Melchor Dominguez, April Crawford
 * @Version - 4.24.2018
 * This class represents the shared memory of your program. 
 * All semaphores should be located here
 */
public class Docks{
    
    /** static final variables to define the size for miners and materials*/
    private static final SANDWHICH_SIZE = 3;
    private static final NUM_MINERS = 3;
    private static final SEMAPHORE_SIZE = 1;

    /** Array holding semaphores for the materials for sandwhiches*/
    private Semaphore[] materials;
    
    /** Array holding semaphores for the miners to make the sandwhiches */
    private Semaphore[] miners;
    
    /** Object that will send signal to the Message class from the Foreman*/
    private Object messageSignal; 
    
    /**
     * Constructor that will provide initial set ups for the Docks class
     * and shared memory for the miners, forman, and messeger 
     */
    protected Docks(){
        setMaterials();
        setMiners();
        initializeSignal();
    }//end constructor
    
    /**
     * Helper method which will set all semaphores for the 
     * materials which will be used
     *
     */
    private void setMaterials(){
        //Initialize the material array to hold Semaphores
        materials = new Semaphore[SANDWHICH_SIZE];

        for(int i = 0; i < SANDWHICH_SIZE; i++){
            materials[i] = new Semaphore(SEMAPHORE_SIZE);
        }//end for
    }//end setMaterials()
    
    /**
     * helper method whcih will set all semaphores for the miners
     * to be restricted within their class
     */
    private void setMiners(){
        //Initialize the miner array to hold Semaphores;
        miners = new Semaphore[SANDWHICH_SIZE];

        for(int i = 0; i <  NUM_MINERS; i++){
            miners[i] = new Semaphore(SEMAPHORE_SIZE);
        }//for

    }//end setMiners()
    
    /**
     * Helper method to return the Materials semaphore 
     * @return - material semaphore
     */
    protected Semaphore[] getMaterials(){
        return materials;
    }//end getMaterials()
    
    /**
     * Helper method to return the Miner semaphore
     * @return - miner semaphores
     */
    protected Semaphore[] getMiners(){
        return miners;
    }//end getMiners()
}//end Docks
