import java.util.concurrent.Callable;
import java.util.concurrent.Semaphore;
/**
 * @Authors Melchor Dominguez, April Crawford
 * Group that monitors information and can perform tallies and send messages 
 */
public class Messenger implements Callable<Integer>{
    
    /** Array  holding semaphores for the materials for sandwhiches*/
    private Semaphore[] materials;

    /** Array holding semaphores for the miners to make the sandwhiches*/
    private Semaphore[] miners;

    private Docks docks;
    
    /** numbered constants for the materials/miners*/
    private static final int BREAD = 0;
    private static final int CHEESE = 1;
    private static final int BOLOGNA = 2;

    /**
     * Constructor for messanger where Semaphores are gathered
     * @param docks - locations for the semaphores 
     */
    public Messenger (Docks docks){
        materials = docks.getMaterials();
        miners = docks.getMiners();
        this.docks = docks;
    }//end Messanger 
    
    /**
     * call method which will lock the semaphores to be used
     * @return - -1 : if an error has occured
     *            1 : if an error did not occur
     */
    public Integer call(){
        int miner = docks.getCurrentMiner();
        for(int i = 0; i < miners.length; i++){
            if(i == miner){
                try{
                    miners[i].acquire();
                }catch(InterruptedException e){
                    System.out.println("Interrupted ");
                }//end try-catch
            }else{
                try{
                    materials[i].acquire();
                }catch(InterruptedException e){
                    System.out.println("Interrupted!");
                }
            }//end if-else
        }//end for
        System.out.println("Send " + miner);
        return 1;
    }//end call
}//end Messanger class
