/**
 * Bologna Miner class, extends the abstract class Miner and implements the
 * interface Callable.
 *
 * @author Melchor Dominguez and April Crawford
 * @version 05/02/2018
 */

import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.*;

public class BolognaMiner extends Miner{

/**Name of the miners*/
private static final String name = "Bologna";

/**semaphore for cheese needed*/
private Semaphore cheese;
/**semaphore for bread needed*/
private Semaphore bread;
/**semaphore for miner's supply*/
private Semaphore minerSupply;
/**name of miner supply*/
private String bologna = "Bologna";

/**
 * Constructor sets three semaphors; two for needed materials and one for
 * what the miners have access to.
 *
 * @param material1 semaphor for needed material for sandwich
 * @param material2 semaphor for needed material for sandwich
 * @param supply semaphor for material that miners have access to
 */
public BolognaMiner(Semaphore material1, Semaphore material2, Semaphore supply) {

    this.cheese = material1;
    this.bread = material2;
    this.setName(bread);
}

/**
 * Call creates two random numbers to determine how long the miners take to eat
 * and make sandwiches, then calls the methods from the abstract class.
 *
 * @return 1 for call method was run and is finished
 */
@Override
public Integer call() {

    //if given signal that supplies are ready
    long makeTime = ThreadLocalRandom.current().nextLong(INC, EXC);
    long eatTime = ThreadLocalRandom.current().nextLong(INC, EXC);

    grabMaterials();

    makeSandwiches(makeTime, name);
    eatSandwiches(eatTime, name);

    minerSupply.release();

    return 1;//return 1 when miners are done eating
}

/**
 * 
 */
private void grabMaterials() {
    
    bread.release();
    cheese.release();

}

}
