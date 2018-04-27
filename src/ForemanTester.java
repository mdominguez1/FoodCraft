import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ExecutionException;
/**
 * @Author Melchor Dominguez
 * Tester to test the Foreman class
 */
public class ForemanTester{
    
    public static void main(String[] args){
        //Create a docks class for the foreman
        Docks dock = new Docks();

        FutureTask<Integer> foremanTask = new FutureTask<Integer>(new Foreman(dock));
        Thread foremanThread = new Thread(foremanTask);
        foremanThread.start();
        
        try{
            int result = foremanTask.get();
        }catch(InterruptedException e){
            System.out.println("interuppted Foreman");
        }catch(ExecutionException e){
            System.out.println("Execution Exception in foreman");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }//end try-catch
    }
}
