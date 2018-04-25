/**
 *
 *
 *
 *
 */

import java.lang.Thread;
import java.lang.InterruptedException;

public abstract class Miner {

static final long INC = 500;                                                                 
static final long EXC = 3000;

public void makeSandwiches(long time, String miner) {
//default making of sandwiches
    try{
    System.out.println(miner + " miners are making sandwiches!");
    Thread.currentThread().sleep(time);
    } catch( InterruptedException ie ) {
        System.out.println("Thread was interrupted");
    }
}

public void eatSandwiches(long time, String miner) {
//default eating of sandwiches
    try{
    System.out.println(miner + " miners are eating sandwiches!");
    Thread.currentThread().sleep(time);
    } catch( InterruptedException ie ) {
        System.out.println("Thread was interrupted");
    }
}

}
