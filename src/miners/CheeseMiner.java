package miners;

import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.*;
import main.Docks;

/**
 * @Author Melchor Dominguez, April Crawford
 * Cheese miner that extends the abstract Miner class and makes a cheese version
 */
public class CheeseMiner extends Miner{

    
    /** Name for the miners*/
    private static final String name = "Cheese";

    /** integers for the miner to set the semaphore*/
    private static final int MATERIAL1 = 2;
    private static final int MATERIAL2 = 0;
    private static final int MINER = 1;
    private static final int STATUS = 3;
    
    /**
     * Constructor to make a cheese version of the miners
     * @param docks - uniform class where semaphores are located
     */
    public CheeseMiner(Docks docks) {
        Semaphore[] materials = docks.getMaterials();
        Semaphore[] miners = docks.getMiners();
        this.setForeman(docks.getForeman());

        this.setMaterial1(materials[MATERIAL1]);
        this.setMaterial2(materials[MATERIAL2]);
        this.setMinerSupply(miners[MINER]);                                                                     
        this.setName(name);
        this.setStatus(STATUS);

    }//end CheeseMiner()

}//end CheeseMiner class
