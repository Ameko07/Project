import java.util.ArrayList;
import java.util.Random;



public class Field {
    public static final int ALTITUDE_GAP = 80;
    public static final int START_ALTITUDE = 40;
    public static final int BLOCK_HEIGHT = 10; // Hauteur des blocs
    public static final int INITIAL_BLOCK_WIDTH = 100;
    public static final int INITIAL_SCROLL_SPEED = 2;

    public final int width, height;
    private int bottom, top; // bottom and top altitude
    private boolean scrolling;
    private int scrollSpeed;
    private int maxBlockWidth;
    private int level;

    // ensemble des blocks aléatoire
    private ArrayList<Block> ensBlock ;


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
        this.bottom = 0;
        this.top = height;
        this.scrolling = false;
        this.scrollSpeed = INITIAL_SCROLL_SPEED;
        this.maxBlockWidth = INITIAL_BLOCK_WIDTH;
        this.level = 1;

    }

    public void generateBlocks() {
        int altitude = height - START_ALTITUDE;
        while (altitude > 0) {
            ensBlock.add(new Block(altitude, width));
            altitude -= ALTITUDE_GAP;
        }
    }
    //génère nouveaux blocs lors du défilement
    private void generateNewBlocks() {
        if (ensBlock.isEmpty()) {
            // Si aucun bloc, commencer à générer à partir de top
            ensBlock.add(new Block(top - ALTITUDE_GAP, width));
        }

        // Altitude du dernier bloc (le + haut)
        int highestAltitude = getHighestBlockAltitude();

        // Générer un nouveau bloc par dessus le bloc le plus haut
        int newAltitude = highestAltitude - ALTITUDE_GAP;
        ensBlock.add(new Block(newAltitude, width));
        
    }

    public void incrementeDifficulte() {
        level++;
        scrollSpeed ++;
        maxBlockWidth = Math.max(30, maxBlockWidth - 10); //réduire largeur des blocs (min 30 max 100)
        System.out.println("Level up! current level:" + level );
    }


    // getter
    private int getHighestBlockAltitude() {
        if (ensBlock.isEmpty()) {
            return top; // si aucun bloc retourner top
        }
        return ensBlock.get(ensBlock.size() - 1).getY();
    }



    public int getBottom() {
        return bottom;
    }

    public int getTop() {
        return top;
    }

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

    public int getLevel() {
        return level;
    }

    public int getMaxBlockWidth() {
        return maxBlockWidth;
    }

    //setter
    public void setScrolling(boolean scrolling) {
        this.scrolling = scrolling;
    }

    //Implantation du défilement
    public void update() {

        // Déplacer les limites inférieure (bottom) et supérieure (top)
        //ici on fait - car le tableau commence en haut à gauche
        bottom -= scrollSpeed;
        top -= scrollSpeed;

        // Supprimer les blocs en dessous de bottom lors du défilement
        ensBlock.removeIf(b -> b.getY() > top);
        System.out.println("Blocks after removal: " + ensBlock.size());

        // Ajouter de nouveaux blocs au-dessus tant qu'Axel est en vie
        while (getHighestBlockAltitude() > top - height) {
            generateNewBlocks();
        }
    }


}