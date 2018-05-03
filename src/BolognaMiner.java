/**
 *
 *
 *
 *
 */

import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.*;

public class BolognaMiner extends Miner{

/** Name of the miner supply */
private static final String name = "Bologna";

/** integers for the miner to set the semaphores*/
private static final int MATERIAL1 = 0;
private static final int MATERIAL2 = 1;
private static final int MINER = 2;

public BolognaMiner(Docks docks){
    
    Semaphore[] materials = docks.getMaterials();
    Semaphore[] miners = docks.getMiners();
    this.setForeman(docks.getForeman());

    this.setMaterial1(materials[MATERIAL1]);
    this.setMaterial2(materials[MATERIAL2]);
    this.setMinerSupply(miners[MINER]);
}//end constructor

}
