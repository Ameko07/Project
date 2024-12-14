import java.util.Random;

public class Block {
    private int x, y;
    private final int width;
    private int bonus ;

    //Constructeur pour ajuster largeur des blocs
    public Block(int y, int fieldWidth, int maxBlockWidth) {

        // randomisation
        Random random = new Random();
        this.y = y;
        this.width = random.nextInt(maxBlockWidth / 2) + (maxBlockWidth / 2); // Largeur aléatoire entre maxBlockWidth/2 et maxBlockWidth
        this.x = random.nextInt(fieldWidth - this.width);// Position aléatoire
        this.bonus = 0;
    }

    public Block (int alt, int fieldWidth) { //fieldWith est la largeur du terrain
        Random randomBlock = new Random();
        this.y = alt;
        this.width = randomBlock.nextInt(50) + 50; //génère un entier entre 50 et 100 (niveau 0)
        this.x = randomBlock.nextInt(fieldWidth - this.width); //génère un entier entre 0 et la plage MAX de x
        this.bonus = 0;
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

    /**getter getBonus()
     * @return bonus**/
    public int getBonus() {
        return bonus;
    }

    // setter
    /***setter setBonus()
     * modifie l'attribut bonus*/
    public void setBonus() {
        this.bonus++;
    }

    /**
     *setter setX()
     * @param x : int
     * modifie l'attribut x**/
    public void setX (int x) {
        this.x = x;
    }

}


