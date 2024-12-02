import java.util.ArrayList;
import java.util.Random;
public class Field {
    public static final int ALTITUDE_GAP = 80;
    public static final int START_ALTITUDE = 40;

    public final int width, height;
    private int bottom, top; // bottom and top altitude

    // ensemble des blocks aléatoire
    private ArrayList<Block> ensBlock ;

    /**public Field(int width, int height) {
     this.width = width;
     this.height = height;
     ArrayList<Block> blocks; // création de l'ensemble

     blocks = new ArrayList<>();
     Random r = new Random(); // randomisation des block

     for (int i = 40 ; i<height; i+= 80){ // intervalle de 80 altitude
     Block b = new Block(r.nextInt(width),i,r.nextInt(width/3));
     blocks.add(b);
     }

     this.ensBlock = blocks;
     }**/

    public Field(int width, int height){
        this.width = width;
        this.height = height;
        this.ensBlock = new ArrayList<>();


    }

    public Field(ArrayList<Block> EnsembleBlock, int width, int height) {
        this.width = width;
        this.height = height;
        this.ensBlock = new ArrayList<>(EnsembleBlock);
        if (EnsembleBlock == null || EnsembleBlock.isEmpty()) {
            generateBlocks();
        }
    }

    public void generateBlocks() {
        int altitude = height - START_ALTITUDE;
        while (altitude > 0) {
            ensBlock.add(new Block(altitude, width));
            altitude -= ALTITUDE_GAP;
        }
    }

    // getter

    /**getter getEnsBlock()
     * @return ensBlock : ArrayList<Block> **/
    public ArrayList<Block> getEnsBlock(){
        return ensBlock;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void update() {


    }
}