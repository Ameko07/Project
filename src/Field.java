import javax.swing.*;
import java.awt.*;
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
    private int scrollSpeed;
    private boolean scrolling;
    private int maxBlockWidth;
    private int level;
    private ArrayList<Bonus> bonus;
    private Image bonusImage;

    // ensemble des blocks aléatoire
    private ArrayList<Block> ensBlock ;


    //constructeur
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
        this.bonus = new ArrayList<>();
        this.bonusImage = new ImageIcon("src/Strawberry.png").getImage();

    }

    /**methode generateBlocks()
     * remplit ensBlock des premières block avant le défilement **/
    public void generateBlocks() {
        int altitude = height - START_ALTITUDE;
        while (altitude > 0) {
            ensBlock.add(new Block(altitude, width));
            altitude -= ALTITUDE_GAP;
        }
    }
    /**methode generateNewBlocks()
     * génère nouveaux blocs lors du défilement**/
    private void generateNewBlocks() {
        if (ensBlock.isEmpty()) {
            // Si aucun bloc, commencer à générer à partir de top
            ensBlock.add(new Block(top - ALTITUDE_GAP, width));
        }

        // Altitude du dernier bloc (le + haut)
        int highestAltitude = getHighestBlockAltitude();

        // Générer un nouveau bloc par dessus le bloc le plus haut
        int newAltitude = highestAltitude - ALTITUDE_GAP;

        Block newBlock;
        //Génère aléatoirement bloc normal OU block qui bouge
        if (level >= 3 && Math.random() < 0.3) {
            newBlock = new MovingBlock(newAltitude, width, maxBlockWidth);
        }
        else {
            newBlock =new Block(newAltitude, width, maxBlockWidth);
        }
        ensBlock.add(newBlock);

    }

    /**methode generateBonus()
     * genere des bonus aléatoirement en choisissant au hasard le block auquel le bonus apparaitra**/
    private void generateBonus() {
        Random rand = new Random();
        //savoir quel est le type de block d'apparitrion
        for (Block b : ensBlock) {

            if (b instanceof MovingBlock) { //si un bloc = instance de movingblock, on update le block et le bonus suivra le mouvement du bloc
                if (b.getBonus()== 0){

                    if (rand.nextInt(2) == 1) {
                        int bonusX = b.getX() + rand.nextInt(b.getWidth() - 15);
                        int bonusY = b.getY() - 15;
                        bonus.add(new MovingBonus(bonusX, bonusY, bonusImage,((MovingBlock) b).getDirection()/50, ((MovingBlock) b).getSpeed()/50));
                        b.setBonus();
                    }
                }
            }
            // sinon ça sera un bonus noraml
            if (b.getBonus()== 0) {
                if (rand.nextInt(2) == 1) {
                    int bonusX = b.getX() + rand.nextInt(b.getWidth() - 15);
                    int bonusY = b.getY() - 15;
                    bonus.add(new Bonus(bonusX, bonusY, bonusImage));
                }
            }b.setBonus();
        }
        for (Bonus bns : bonus){
            if (bns instanceof MovingBonus){
                ((MovingBonus) bns).update(width);
            }
        }
    }

    /**methode verifierCollecteBonus()
     * @param axel: Axel
     * @return boolean
     * supprime le bonus lorsque axel le collecte et retourne true si le bonus a été collecté sinon false
     **/
    public void verifierCollecteBonus(Axel axel) {
        bonus.removeIf(b -> {
            if (b.bonusCollecte(axel)) {
                axel.incrementScore(100); // ajoute 100 pts au score
                return true;
            }
            return false;
        });
    }

    /**methode incrementeDifficulte()
     * augmentre level , scrollSpeed et réduit la taille des blocs **/
    public void incrementeDifficulte() {
        level++;
        scrollSpeed ++;
        maxBlockWidth = Math.max(30, maxBlockWidth - 10); //réduire largeur des blocs (min 30 max 100)
        System.out.println("Level up! current level:" + level );
    }


    // getter
    /**getter getHighestBlockAktitude
     * @return le block le plus haut**/
    private int getHighestBlockAltitude() {
        if (ensBlock.isEmpty()) {
            return top; // si aucun bloc retourner top
        }
        return ensBlock.get(ensBlock.size() - 1).getY();
    }


    //les 2 sont inversé en raison de l'emplacement des coordonnée origine de la fenêtre
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

    /**getter getWidth()
     * @return width**/
    public int getWidth() {
        return width;
    }

    /**getter getHeiht()
     * @return height**/
    public int getHeight() {
        return height;
    }

    /**getter  getLevel()
     * @return level**/
    public int getLevel() {
        return level;
    }

    /**getter getMaxBlockWidth()
     * @return maxBlockWidth**/
    public int getMaxBlockWidth() {
        return maxBlockWidth;
    }

    /**getter getBonus()
     * @return bonus**/
    public ArrayList<Bonus> getBonus() {
        return bonus;
    }

    //setter
    /**setter setScrolling
     * active le scroll de l'écrant **/
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

        // Ajouter de nouveaux blocs au-dessus tant qu'Axel est en vie
        while (getHighestBlockAltitude() > top - height) {
            generateNewBlocks();
        }
        //Implémente blocs qui bougent
        for (Block b : ensBlock) { //parcourt blocs de ensBlock
            if (b instanceof MovingBlock) { //si un bloc = instance de movingblock, on update le block
                ((MovingBlock) b).update(width);
            }
        }

        //Supprime les bonus hors de l'écran
        bonus.removeIf(b -> b.getY() > top);
        generateBonus();

    }


}