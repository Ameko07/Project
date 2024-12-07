import java.util.ArrayList;
import java.util.Random;



public class Field {
    public static final int ALTITUDE_GAP = 80;
    public static final int START_ALTITUDE = 40;
    public static final int BLOCK_HEIGHT = 10; // Hauteur des blocs

    public final int width, height;
    private int bottom, top; // bottom and top altitude
    private boolean scrolling;

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
        System.out.println("Generated block at altitude: " + newAltitude);
    }


    private int getHighestBlockAltitude() {
        if (ensBlock.isEmpty()) {
            return top; // si aucun bloc retourner top
        }
        return ensBlock.get(ensBlock.size() - 1).getY();
    }


    // getter


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

    //setter
    public void setScrolling(boolean scrolling) {
        this.scrolling = scrolling;
    }

//Implantation du défilement
public void update() {
    int scrollSpeed = 2; // Vitesse constante du défilement

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
