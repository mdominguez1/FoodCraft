import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Semaphore;

/**
 * @Author Melchor Dominguez
 * Tester to test the Mesenger class
 */
public class MessengerTester{
    

    public static void main(String[] args){
        //Create a docks class for the messenger
        Docks dock = new Docks();
        Semaphore[] materials = dock.getMaterials();
        Semaphore[] miners = dock.getMiners();

        FutureTask<Integer> messengerTask = new FutureTask<Integer>(new Messenger(dock));
        Thread messengerThread = new Thread(messengerTask);

        int[] material = new int[3];
        material[0] = 1; //send bread
        material[1] = 1; //send cheese
        material[2] = 0; //don't send bologna
        dock.send(material);

        messengerThread.start();

        try{
            int result = messengerTask.get();
        }catch(InterruptedException e){
            System.out.println("Interrupted Messenger");
        }catch(ExecutionException e){
            System.out.println("Execution Exception in Messenger");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }//try-catch
    }
}
