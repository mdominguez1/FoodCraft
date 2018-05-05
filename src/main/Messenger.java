package main;

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
    
    /** Docks class for reading materials*/
    private Docks docks;
    
    /** Semaphore for the messenger to know when to reach its critical section*/
    private Semaphore messengerSem;
    
    /** numbered constants for the materials/miners*/
    private static final int BREAD = 0;
    private static final int CHEESE = 1;
    private static final int BOLOGNA = 2;

    /** Integer for status of messenger */
    private Integer status;

    /**
     * Constructor for messanger where Semaphores are gathered
     * @param docks - locations for the semaphores 
     */
    public Messenger (Docks docks){
        materials = docks.getMaterials();
        miners = docks.getMiners();
        this.docks = docks;
        messengerSem = docks.getMessenger();
        status = 1;
    }//end Messanger 
    
    /**
     * call method which will lock the semaphores to be used
     * @return - -1 : if an error has occured
     *            1 : if an error did not occur
     */
    public Integer call(){
        while(status != -1) {
        try{
            messengerSem.acquire();
        
            //System.out.println("Messenger will not notify any miners");
            //return -1;
        
            System.out.println("Messenger will notify miners soon....");
            int miner = docks.getCurrentMiner();
            for(int i = 0; i < miners.length; i++){
                if(i == miner){
                    miners[i].release();
                }else{
                    materials[i].release();
                }//end if-else
            }//end for
        }catch (InterruptedException e){
            System.out.println("Messenger will not notify any miners");
            status = -1;
        }
        }
        return status;
    }//end call
}//end Messanger class
