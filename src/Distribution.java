/**
 * @Author - Melchor Dominguez, April Crawford
 * Driver which will start the sandwhich making process
 */
public class Distribution{

    /** boolean value to show if the output will be written to a file*/
    private static boolean toFile;
    
    /** int to show how long the driver will be running until terminated */
    private static int time;

    /** integer to exit out of the system whenever an error has occured*/
    private static final int ERROR = -1;
    
    /** Docks class which will hold everything regarding to shared memory for the threads*/
    private static Docks docks;
   
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
                toFile = false;
            }else if(args[1].equals("F")){
                toFile = true;
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

}//end Distribution class
