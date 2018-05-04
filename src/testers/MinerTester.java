import java.util.concurrent.Callable;                                                               
import java.util.concurrent.FutureTask;                                                             
import java.util.concurrent.ExecutionException;     
import java.util.concurrent.Semaphore;                                                
/**                                                                                                 
 * @Author Melchor Dominguez                                                                        
 * Tester to test the Foreman class                                                                 
 */                                                                                                 
public class MinerTester{                                                                         
                                                                                                    
    public static void main(String[] args){                                                                                                                     
                                                                                  
        Semaphore one = new Semaphore(1);
        Semaphore two = new Semaphore(1);
        Semaphore three = new Semaphore(1);
                          
        FutureTask<Integer> foremanTask = new FutureTask<Integer>(new BreadMiner(one, two, three));               
        Thread foremanThread = new Thread(foremanTask);                                             
        foremanThread.start();                                                                      
                                                                                                    
        try{                                                                                        
            int result = foremanTask.get();                                                         
        }catch(InterruptedException e){                                                             
            System.out.println("interuppted Miner");                                              
        }catch(ExecutionException e){                                                               
            System.out.println("Execution Exception in Miner");                                   
            System.out.println(e.getMessage());                                                     
            e.printStackTrace();                                                                    
        }//end try-catch                                                                            
    }//end main()                                                                                   
}//end ForemanTester
