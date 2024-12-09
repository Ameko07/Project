import java.util.Random;

public class Block {
    private int x, y;
    private final int width;

    //Constructeur pour ajuster largeur des blocs
    public Block(int y, int fieldWidth, int maxBlockWidth) {
        Random random = new Random();
        this.y = y;
        this.width = random.nextInt(maxBlockWidth / 2) + (maxBlockWidth / 2); // Largeur entre maxBlockWidth/2 et maxBlockWidth
        this.x = random.nextInt(fieldWidth - this.width); // Position aléatoire
    }

    public Block (int alt, int fieldWidth) { //fieldWith est la largeur du terrain
        Random randomBlock = new Random();
        this.y = alt;
        this.width = randomBlock.nextInt(50) + 50; //génère un entier entre 50 et 100 (niveau 0)
        this.x = randomBlock.nextInt(fieldWidth - this.width); //génère un entier entre 0 et la plage MAX de x
    }


    /**getter getWidth()
     * @return this.width : int
     * **/
    public int getWidth() {
        return width;
    }

    /**getter getX()
     * @return x : int  **/
    public int getX() {
        return x;
    }
    /**getter getY()
     * @return y : int  **/
    public int getY() {
        return y;
    }

    public void setX (int x) {
        this.x = x;
    }

}


