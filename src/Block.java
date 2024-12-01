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
