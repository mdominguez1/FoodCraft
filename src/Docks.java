import java.util.concurrent.Semaphore;

/**
 * @Author - Melchor Dominguez, April Crawford
 * @Version - 4.24.2018
 * This class represents the shared memory of your program. 
 * All semaphores should be located here
 */
public class Docks{
    
    /** static final variables to define the size for miners and materials*/
    private static final int SANDWHICH_SIZE = 3;
    private static final int NUM_MINERS = 3;
    private static final int SEMAPHORE_SIZE = 1;

    /** Array holding semaphores for the materials for sandwhiches*/
    private Semaphore[] materials;
    
    /** Array holding semaphores for the miners to make the sandwhiches */
    private Semaphore[] miners;
    
    /** Semaphore for the foreman to send materials */
    private Semaphore foremanSem;
    
    /** Semaphore for the messenger to notify miners*/
    private Semaphore messengerSem;
    
    /** Enum specifying the different types of miners*/
    private enum Miner{
        BREAD, CHEESE, BOLOGNA;
    }//end enum
    
    /** Array which hold the number of materials sent */
    private int[] sent;
    
    /** Miner enum to show the next miner to make a sandwhich*/
    private Miner curMiner = null;
    
    /**
     * Constructor that will provide initial set ups for the Docks class
     * and shared memory for the miners, forman, and messeger 
     */
    protected Docks(){
        setMaterials();
        setMiners();
        setCommunication();
        acquireAll();
    }//end constructor
    
    /**
     * Method to block all semaphores 
     */
    private final void acquireAll(){
        try{
            for(Semaphore sem: materials){
                sem.acquire();
            }//end for

            for(Semaphore sem: miners){
                sem.acquire();
            }///end for
    
            foremanSem.acquire();
            messengerSem.acquire();
        }catch(InterruptedException e){
            System.out.println("InterruptedException");
        }
    }//end acquireAll()

    /**
     * Helper method which will set all semaphores for the 
     * materials which will be used
     *
     */
    private final void setMaterials(){
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
    private final void setMiners(){
        //Initialize the miner array to hold Semaphores;
        miners = new Semaphore[SANDWHICH_SIZE];

        for(int i = 0; i <  NUM_MINERS; i++){
            miners[i] = new Semaphore(SEMAPHORE_SIZE);
        }//end for

    }//end setMiners()
    
    /** 
     * Method to set further communication for Messanger and Foreman
     */
    private final void setCommunication(){
        messengerSem = new Semaphore(SEMAPHORE_SIZE);
        foremanSem = new Semaphore(SEMAPHORE_SIZE);
    }//end setCommunication
    
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
    
    /**
     * Return the foreman semaphore
     * @return - the miner semaphore
     */
    protected Semaphore getForeman(){
        return foremanSem;
    }//end getForeman()
    
    /**
     * Returns the messenger semaphore
     * @return - the messenger semaphore 
     */
    protected Semaphore getMessenger(){
        return messengerSem;
    }//end getMessenger

    /**
     * Set method for the messanger class to change the mienr which 
     * will be
     * @param check - number corresponding to what miner should be woken up
     *                0 : Bread
     *                1 : Cheese
     *                2 : Bologna
     */
    protected void setCurrentMiner(int check){
        if(check == 1){
            curMiner = Miner.CHEESE;
        }else if(check == 2){
            curMiner = Miner.BOLOGNA;
        }else if(check == 0){
            curMiner = Miner.BREAD;
        }//end if-else
    }//end setCurrentMiner()
    
    /**
     * Method to return an int correlating to the current miner 
     * @return - 0 : if the current miner is Bread
     *           1 : if the current miner is Cheese
     *           2 : if the current miner is Bologna
     *          -1 : if an error has occured
     */
    protected int getCurrentMiner(){
        if(curMiner == Miner.BREAD){
            return 0;
        }else if(curMiner == Miner.CHEESE){
            return 1;
        }else if(curMiner == Miner.BOLOGNA){
            return 2;
        }//end if-else
        return -1;
    }//end getCurrentMiner
    
    /**
     * Method for the Foreman to send materials to the messanger
     * @param sent - Array holding materials
     *             sent[0] - Bread
     *             sent[1] - Cheese
     *             sent[2] - Bologna
     */
    protected void send(int[] sent){
        
        //check to see which item was not sent
        for(int i = 0; i < sent.length; i++){
            if(sent[i] == 0){
               setCurrentMiner(i);
            }//end if
        }//end for

    }//end send()
}//end Docks
