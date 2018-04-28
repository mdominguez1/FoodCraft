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

/**
 * Constructor sets three semaphors; two for needed materials and one for
 * what the miners have access to.
 *
 * @param cheese semaphor for material needed for sandwich
 * @param bologna semaphor for material needed for sandwich
 * @param supply semaphor for material that miners have access to
 */                                        
public BreadMiner(Semaphore cheese, Semaphore bologna, Semaphore supply) {                   
                                                                            
    this.setMaterial1(cheese);                                                                        
    this.setMaterial2(bologna);                                                                         
    this.setMinerSupply(supply);   
    this.setName(bread);
                                                                                                                                                      
}//end constructor

}//end Breadminer
