import java.io.PrintStream;
import java.io.File;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.Executors;
import java.io.FileNotFoundException;

/**
 * @Author - Melchor Dominguez, April Crawford
 * Driver which will start the sandwhich making process
 */
public class Distribution{
    
    /** int to show how long the driver will be running until terminated */
    private static int time;

    /** integer to exit out of the system whenever an error has occured*/
    private static final int ERROR = -1;
    
    /** Docks class which will hold everything regarding to shared memory for the threads*/
    private static Docks docks;
    
    /** ExecutorCompletion Service to hold the threadpool*/
    private static ExecutorCompletionService<Integer> complete;
    
    /** Thread pool to control the threads*/
    private static ExecutorService pool;

    /** The new file where everything will be printed to if specified*/
    private static final String newOut = "log.txt";

    /** The number of threads that will be running concurrently*/ 
    private static final int NUM_THREADS = 5;

   
    /**
     * Main method that will accept two command line arguments
     * @param args - The command line arguments to take
     *      1. The amount of time before the driver ends the distribution operation,
     *          using deferred cancellation/termination. If the amount is 0 or negative,
     *          the operation continues until the main threas is killed (ctrl-C), which
     *          then in turn kills all the child threads.
     *      2. 'T' or 'F'. 'T' indicates to append output from the threads to a single file,
     *          'F' means all output should be printed to the screen. If you are logging output to
     *          a file, then the time until the operation is stopped must be a positive value.
     *          Call the output file log.txt. All threads are writing to the same file,
     *          concurrently.
     * No zombie processed. 
     */
    public static void main(String[] args){
        parseArgs(args);
        makeDocks();
        setPool();
        getResults();
    }//end main()
    
    /**
     * Helper method for the main class to parse through the command line
     * arguments.
     * @param args - The command line input gathered from the main method
     */
    private static final void parseArgs(String[] args){
        if(args.length != 2){
            System.out.println("Usage: java Distribution <time> <T/F>");
            System.exit(ERROR);
        }else{

            time = Integer.parseInt(args[0]);
            if(time < 0){
                System.out.println("Invalid time to run, must be positive");
                System.exit(ERROR);
            }//end if

            if(args[1].equals("T")){
                //ignore
            }else if(args[1].equals("F")){
                changeOut();
            }else{
                System.out.println("Invalid letter chosen for 2nd argument: must be T or F");
                System.exit(ERROR);
            }//ennd if-else
        }//end if-else
    }//end parseArgs()
    
    /**
     * Helper method to initialize the dock field for the main method 
     * and to debug docks class if needed
     */
    private static final void makeDocks(){
        docks = new Docks();
    }//end makeDocks();
    
    /**
     * Method to change the direction of the standard out for system so that
     * it writes to specified file
     */
    private static final void changeOut(){

        //Creating a File object that will be written to 
        try{
            PrintStream newFile = new PrintStream(new File(newOut));
            System.setOut(newFile);
        }catch(FileNotFoundException e){
            System.out.println("New output not found");
        }

    }//end changeOut()
    
    /**
     * Sets up the initial pool for the threads
     * 
     */
    private static final void setPool(){
        ExecutorService pool = Executors.newFixedThreadPool(NUM_THREADS);
        complete = new ExecutorCompletionService<Integer>(pool);

        Callable<Integer> foreman = new Foreman(docks);
        Callable<Integer> messenger = new Messenger(docks);
        Callable<Integer> breadMiner = new BreadMiner(docks);
        Callable<Integer> cheeseMiner = new CheeseMiner(docks);
        Callable<Integer> bolognaMiner = new BolognaMiner(docks);
        complete.submit(foreman);
        complete.submit(messenger);
        complete.submit(breadMiner);
        complete.submit(cheeseMiner);
        complete.submit(bolognaMiner);
    }//end setPool()
    
    /**
     * Parse through the results
     */
    private static final void getResults(){
        for(int i = 0; i < NUM_THREADS; i++){
            try{
                Future<Integer> future = complete.take();
                int returnForeman = future.get();
            }catch(InterruptedException e){
                System.out.println("Interrupted Future");
            }catch(ExecutionException e){
                System.out.println("Execution Error Future");
            }
        }//end for 
    }//end getResults()

}//end Distribution class
