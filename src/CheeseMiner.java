/**
 *
 *
 *
 *
 *
 */

import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.*;

public class CheeseMiner extends Miner{

private static final String name = "Cheese";

/** integers for the miner to set the semaphore*/
private static final int MATERIAL1 = 2;
private static final int MATERIAL2 = 0;
private static final int MINER = 1;                                                              
                                                                                                    
public CheeseMiner(Docks docks) {
    Semaphore[] materials = docks.getMaterials();
    Semaphore[] miners = docks.getMiners();
    this.setForeman(docks.getForeman());
    
    this.setMaterial1(materials[MATERIAL1]);
    this.setMaterial2(materials[MATERIAL2]);
    this.setMinerSupply(miners[MINER]);                                                                     
                                                                                                    
}

}
