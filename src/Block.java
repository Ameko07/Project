import java.util.Random;

public class Block {
    private final int x, y;
    private final int width;

    public Block(int x, int y, int width) {
        this.x = x;
        this.y = y;
        if (width<20){
            this.width = 50;
        }else this.width = width;

    }

    public Block ( int fieldWidth,int alt) { //fieldWith est la largeur du terrain
        Random randomBlock = new Random();

        this.width = randomBlock.nextInt(50) + 50; //génère un entier entre 50 et 100 (niveau 0)
        this.x = randomBlock.nextInt(fieldWidth - this.width); //génère un entier entre 0 et la plage MAX de x
        this.y = alt;
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
}
