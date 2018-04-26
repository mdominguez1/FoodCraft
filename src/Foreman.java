import java.util.concurrent.ThreadLocalRandom;
/**
 * @Authors Melchor Dominguez, April Crawford
 *
 * The Foreman class is the master sandwich maker, kind of like the Avatar;
 * Instead of elements, this class masters the three sandwich materials: Bread,
 * Cheese, and Bologna; 
 *
 */
public class Foreman implements Callable<int>{
    
    /** probability line that will handle which materials are made*/
    private double[] probabilityLine;
    
    /** The materials which will be sent off*/
    private int[] materials;
    
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
        setProbabilityLine();
        setMaterials();
    }//end constructor
    
    /**
     * Method which will prepare a number line to evenly distribute 
     * the chance to pick a material
     */
    private final void setProbabilityLine(){
        probabilityLine = new double[NUM_MATERIALS];

        //Set a number line to evenly distribute probability of materials
        for(int i = 0; i < NUM_MATERIALS; i++){
            probabilityLine[0] = (double)((i+1) / NUM_MATERIALS);
        }//end for

    }//end setProbabilityLine
    
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
    private final clearMaterials(){
        
        //resets every material to be sent out to zero
        for(int i = 0; i < materials.length; i++){
            materials[i] = 0;
        }//end for
    }//end clearMaterials()
    
    /** 
     * Call method which will start the thread  
     */
    public int call(){
        pickMaterials
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


    }//end chooseOne()
    

}//end Foreman class 
