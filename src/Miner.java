/**
 * Abstract class Miner provides most of the functionality for the Cheese, Bread, and Bologna miner
 * sublcasses (eating and making sandwiches!)
 *
 * @author Melchor Dominguez and April Crawford
 * @version 05/02/2018
 */

import java.lang.Thread;
import java.lang.InterruptedException;
import java.util.concurrent.*;

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
        System.out.println("Thread was interrupted");
    }
}

/**
 * Method takes a random time and lets miners eat sandwiches.
 *
 * @param time random time to eat sandwiches
 * @param miner name of miner eating sandwiches
 */
public void eatSandwiches(long time, String miner) {
//default eating of sandwiches
    try{
    System.out.println(miner + " miners are eating sandwiches!");
    Thread.sleep(time);
    } catch( InterruptedException ie ) {
        System.out.println("Thread was interrupted");
    }
}

/**
 * Method allows miner subclass to set its name.
 *
 * @param name name of the miner
 */
public void setName(String name) {
    this.name = name;
}

/**
 * Method allows miner subclass to set the Semaphore for the second needed material.
 *
 * @param material1 Semaphore for the first needed sandwich material
 */
public void setMaterial1(Semaphore material1) {
    this.material1 = material1;
}

/**
 * Method allows miner subclass to set the Semaphore for the second needed material.
 *
 * @param material2 Semaphore for the second needed sandwich material
 */
public void setMaterial2(Semaphore material2) {
    this.material2 = material2;
}

/**
 * Method allows miner subclass to set the Semaphore for the material they already have.
 *
 * @param minerSupply Semaphore for the material miners already have
 */
public void setMinerSupply(Semaphore minerSupply) {
    this.minerSupply = minerSupply;
}

/**                                                                                                 
 * Call creates two random numbers to determine how long the miners take to eat                     
 * and make sandwiches, then calls the methods from the abstract class.                             
 *                                                                                                  
 * @return 1 for call method was run and is finished                                                
 */                                                                                                 
public Integer call() {                                                                             
    
    minerSupply.release();
                                      
    //if given signal that supplies are ready                                                       
    long makeTime = ThreadLocalRandom.current().nextLong(INC, EXC);                                 
    long eatTime = ThreadLocalRandom.current().nextLong(INC, EXC);                                  
                                                                                                    
    grabMaterials();                                                                                
                                                                                                    
    makeSandwiches(makeTime, name);                                                                 
    eatSandwiches(eatTime, name);                                                                          
                                                                                                    
    return 1;//return some int to signal completion                                                                  
}                                                                   

/**
 * Method allows miners to take the two materials they need for their sandwiches before they
 * begin to make their sandwiches.
 */                                                                                                    
private void grabMaterials() {                                                                      
                                                                                                    
    material1.release();                                                                              
    material2.release();                                                                               
    System.out.println(name + " miners grabbed materials...");                                      
                                                                                                    
}                                                                                            
                                                                                                    
}                         
