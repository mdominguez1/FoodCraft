import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.Callable;
import java.util.concurrent.Semaphore;
/**
 * @Authors Melchor Dominguez, April Crawford
 *
 * The Foreman class is the master sandwich maker, kind of like the Avatar.
 * Instead of elements, this class masters the three sandwich materials: Bread,
 * Cheese, and Bologna. 
 *
 */
public class Foreman implements Callable<Integer>{
    
    /** probability line that will handle which materials are made*/
    private double[] probabilityLine;
    
    /** The materials which will be sent off*/
    private int[] materials;

    /** Docks class to communicate between the Foreman and Messanger*/
    private Docks dock;
    
    /** status that will be returned by the Foreman to show success or failure*/
    private int status;

    /** Semaphore for the foreman to connect*/
    private Semaphore foremanSem;
    
    /** Semaphore for the messenger to respond*/
    private Semaphore messengerSem;
    
    /** constant number of how many materials the foreman controls */
    private static final int NUM_MATERIALS = 3;
    
    /** constatnt number of how many materials will be sent off */
    private static final int SEND_OFF = 2;

    /**
     * Constructor which will prepare the foreman to decide who gets to eat,
     * and who starves.
     * @param dock - Docks class which will hold information for the foreman
     * to communicate with the messanger
     */
    public Foreman(Docks dock){
        this.dock = dock;
        setProbabilityLine();
        setMaterials();
        this.foremanSem = dock.getForeman();
        this.messengerSem = dock.getMessenger();
    }//end constructor
    
    /**
     * Method which will prepare a number line to evenly distribute 
     * the chance to pick a material
     */
    private final void setProbabilityLine(){
        // Initialize the probability line size
        probabilityLine = new double[NUM_MATERIALS];

        //Set a number line to evenly distribute probability of materials
        for(int i = 0; i < NUM_MATERIALS; i++){
            probabilityLine[i] = ((double)(i+1)/(double)NUM_MATERIALS);
        }//end for

        //printProbabilityLine();

    }//end setProbabilityLine();
    
    /**
     * Method to print probability line for debugging 
     */
    private void printProbabilityLine(){
        for(int i = 0; i < NUM_MATERIALS; i++){
            System.out.println(i + " : " + probabilityLine[i]);
        }//end for
    }//end printProbabilityLine()
    
    /**
     * Method which will prepare an array of materials to appoint
     * which materials will be sent off
     */
    private final void setMaterials(){
        materials = new int[NUM_MATERIALS];
        clearMaterials();
    }//end setMaterials
    
    /**
     * Method which will reset the number of materials to be sent out to zero
     */
    private final void clearMaterials(){
        
        //resets every material to be sent out to zero
        for(int i = 0; i < materials.length; i++){
            materials[i] = 0;
        }//end for

    }//end clearMaterials()
    
    /** 
     * Call method which will start the thread  
     * @return - 1 : if the Foreman successfully sent materials
     *           0 : if an error occured and materials are corrupt
     */
    public Integer call(){
        try{
            foremanSem.acquire();
        }catch(InterruptedException e){
            System.out.println("foreman semaphore interrupted");
        }//end try-catch

        pickMaterials();

        int count = 0;
        for(int i : materials){
            count += i;
        }//end for

        if(count == 2){
            status = 1;
            dock.send(materials);
            messengerSem.release();
            System.out.println("success");
        }else{
            status = 0;
            System.out.println("failure");
        }//end if-else

        return status;
    }//end call()
    
    /**
     * Method which will pick the materials which will send off materials
     */
    private void pickMaterials(){
        clearMaterials();
        
        //send off the correct number of materials 
        for(int i = 0; i < SEND_OFF; i++){
            chooseOne();
        }//end for 
    }//end pickMaterials()
    
    /**
     * Method which will choose a new material which will be sent off 
     */
    private void chooseOne(){
        /** random number used to gernerate */
        double rand = ThreadLocalRandom.current().nextDouble();
        int material = -1;
        for(int i = 0; i < materials.length; i++){
            if(rand < probabilityLine[i]){
                material = i;
                break;
            }//end if
        }//end for
        
        //if material did not get a new value, print error message
        if(material == -1){
            System.out.println("Error in choosing material");
            System.exit(material);
        }//end if

        //if material has already been chosen, choose again
        if(materials[material] > 0){
            System.out.println(material + " : material already chosen");
            chooseOne();
        }else{
            System.out.println(material);
            materials[material]++;
        }//end if-else
    }//end chooseOne()

}//end Foreman class 
