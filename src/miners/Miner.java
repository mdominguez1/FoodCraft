package miners;

import java.lang.Thread;
import java.lang.InterruptedException;
import java.util.concurrent.*;

/**
 * @Author Melchor Dominguez, April Crawford
 * @Version 5.4.2018
 *
 * Abstract class Miner provides most of the functionality for the Cheese, Bread, and Bologna miner
 * sublcasses (eating and making sandwiches!)
 */
public abstract class Miner implements Callable<Integer>{

    /**Minimum time for eating or making sandwiches (milliseconds)*/
    private static final long INC = 500;                            
    /**Maximum time for eating or making sandwiches (milliseconds)*/                                     
    private static final long EXC = 3000;
    /**Name of the miner*/
    private String name;
    /**Semaphore for a material needed for sandwiches*/
    private Semaphore material1;
    /**Semaphore for a material needed for sandwiches*/
    private Semaphore material2;
    /**Semaphore for the material the miners already have*/
    private Semaphore minerSupply;
    /** Semaphore for the foreman*/
    private Semaphore foremanSem;

    /** int that will represent the miners, -1 if never modified*/
    private int status = -1;


    /**
     * Method takes a random time and makes sandwiches for a specific miner.
     *
     * @param time random time to make sandwiches
     * @param miner name of miner making sandwiches
     */
    public void makeSandwiches(long time, String miner) {
        //default making of sandwiches
        try{
            System.out.println(miner + " miners are making sandwiches!");
            Thread.sleep(time);
        } catch( InterruptedException ie ) {
            System.out.println(miner + "miners were unable to finish making sandwiches :(");
            status = -1;
        }
    }

    /** 
     * Chamges the status for the miners
     * @param status - the new status for the miner
     */
    protected void setStatus(int status){
        this.status = status;
    }

    /**
     * Method takes a random time and lets miners eat sandwiches.
     *
     * @param time random time to eat sandwiches
     * @param miner name of miner eating sandwiches
     */
    private void eatSandwiches(long time, String miner) {
        //default eating of sandwiches
        try{
            System.out.println(miner + " miners are eating sandwiches!");
            Thread.sleep(time);
        } catch( InterruptedException ie ) {
            System.out.println(name + "miners have to stop eating sandwhiches :(");
            status = -1;
        }
    }

    /**
     * Method allows miner subclass to set its name.
     *
     * @param name name of the miner
     */
    protected void setName(String name) {
        this.name = name;
    }

    /**
     * Method allows miner subclass to set the Semaphore for the second needed material.
     *
     * @param material1 Semaphore for the first needed sandwich material
     */
    protected void setMaterial1(Semaphore material1) {
        this.material1 = material1;
    }

    /**
     * Method which will set the foreman semaphore to notify the foreman
     * when materials have been used
     * @param foremanSem - the foreman semaphore
     */
    protected void setForeman(Semaphore foremanSem){
        this.foremanSem = foremanSem;
    }

    /**
     * Method allows miner subclass to set the Semaphore for the second needed material.
     *
     * @param material2 Semaphore for the second needed sandwich material
     */
    protected void setMaterial2(Semaphore material2) {
        this.material2 = material2;
    }

    /**
     * Method allows miner subclass to set the Semaphore for the material they already have.
     *
     * @param minerSupply Semaphore for the material miners already have
     */
    protected void setMinerSupply(Semaphore minerSupply) {
        this.minerSupply = minerSupply;
    }

    /**                                                                                                 
     * Call creates two random numbers to determine how long the miners take to eat                     
     * and make sandwiches, then calls the methods from the abstract class.                             
     *                                                                                                  
     * @return 1 for call method was run and is finished                                                
     */                                                                                                 
    public Integer call() {                                                                             
        while(status != -1) {
        try{
            minerSupply.acquire();
        }catch(InterruptedException e){
            System.out.println(name + " miner can no longer continue :(");
            return -1;
        }

        //if given signal that supplies are ready                                                       
        long makeTime = ThreadLocalRandom.current().nextLong(INC, EXC);                                 
        long eatTime = ThreadLocalRandom.current().nextLong(INC, EXC);                                  

        grabMaterials();                                                                                

        makeSandwiches(makeTime, name);         
        if(status == -1){
            return -1;   
        }
        eatSandwiches(eatTime, name);                                                                          
        }
        return status;//return some int to signal completion                                                                  
    }                                                                   

    /**
     * Method allows miners to take the two materials they need for their sandwiches before they
     * begin to make their sandwiches.
     */                                                                                                    
    private void grabMaterials() {                                                                      

        try{
            material1.acquire();                                                                              
            material2.acquire();
        }catch(InterruptedException e){
            System.out.println("Interrupted materials");
        }
        System.out.println(name + " miners grabbed materials...");
        foremanSem.release();                                 

    }                                                                                            

}                         
