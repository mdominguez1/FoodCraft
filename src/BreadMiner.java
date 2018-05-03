/**
 * Bread Miner class, extends the abstract class Miner and implements the
 * interface Callable.
 *
 * @author Melchor Dominguez and April Crawford
 * @version 05/02/2018
 */

import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.*;

public class BreadMiner extends Miner{

/**Name of miner supply*/
private String bread = "Bread";            

/** integers for the miner tO set the semaphore*/
private static final int MATERIAL1 = 1;
private static final int MATERIAL2 = 2;
private static final int MINER = 0;                                                         

/**
 * Constructor sets three semaphors; two for needed materials and one for
 * what the miners have access to.
 *
 * @param docks - the class which holds all the semaphores 
 */                                        
public BreadMiner(Docks docks) {                   
                                                                            
    //this.setMaterial1(cheese);                                                                        
    //this.setMaterial2(bologna);                                                                         
    //this.setMinerSupply(supply);   
    //this.setName(bread);

    Semaphore[] materials = docks.getMaterials();
    Semaphore[] miners = docks.getMiners();
    this.setForeman(docks.getForeman());

    this.setMaterial1(materials[MATERIAL1]);
    this.setMaterial2(materials[MATERIAL2]);
    this.setMinerSupply(miners[MINER]);
                                                                                                                                                      
}//end constructor

}//end Breadminer
