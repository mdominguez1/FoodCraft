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

public class CheeseMiner extends Miner implements Callable {

private static final String name = "Cheese";

private Semaphore bologna;                                                                           
private Semaphore bread;                                                                            
private Semaphore minerSupply;                                                                      
                                                                                                    
public CheeseMiner(Semaphore material1, Semaphore material2, Semaphore supply) {                   
                                                                                                    
    this.bologna = material1;                                                                        
    this.bread = material2;                                                                         
    this.minerSupply = supply;                                                                      
                                                                                                    
}

public Integer call() {

    //if given signal that supplies are ready
    long makeTime = ThreadLocalRandom.current().nextLong(INC, EXC);
    long eatTime = ThreadLocalRandom.current().nextLong(INC, EXC);

    makeSandwiches(makeTime, name);
    eatSandwiches(eatTime, name);

    return 0;//return some int
}

}
