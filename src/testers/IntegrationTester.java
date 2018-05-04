import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ExecutorCompletionService;

/**
 * @Author Melchor Dominguez
 * Tester to test the integration of classes 
 *
 */
public class IntegrationTester{
    
    public static void main(String[] args){
        Docks docks = new Docks();

        final ExecutorService pool = Executors.newFixedThreadPool(5);

        ExecutorCompletionService<Integer> complete = new ExecutorCompletionService<Integer>(pool);

        Callable<Integer> foreman = new Foreman(docks);
        complete.submit(foreman);
        Callable<Integer> messenger = new Messenger(docks);
        complete.submit(messenger);
        Callable<Integer> breadMiner = new BreadMiner(docks);
        complete.submit(breadMiner);
        //pool.shutdown();
        docks.getForeman().release();
        try{
            while(true){
                Future<Integer> future = complete.take();
                int returnForeman = future.get();
            }
        }catch(InterruptedException e){
            System.out.println("Interrupted");
        }catch(ExecutionException e){
            System.out.println("Execution");
        }
    }
}//end IntegrationTester class 
