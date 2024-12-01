import java.util.ArrayList;

public class Axel {
    public static final double MAX_FALL_SPEED = -20;
    public static final double JUMP_SPEED = 20;
    public static final double GRAVITY = 1;
    public static final double DIVE_SPEED = 3 * GRAVITY;
    public static final double LATERAL_SPEED = 8;

    private int x, y;
    private int dx , dy;

    private boolean falling;
    private boolean jumping;
    private boolean diving;
    private boolean left;
    private boolean right;

    private boolean surviving;

    private final Field field;

    public Axel(Field f, int x, int y) {
        this.field = f;
        this.x = x;
        this.y = y;
        this.surviving = true;
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
    // setting bolean attribut
    /**setter setJumping
     * @param jumping : boolean
     * set Jumping**/
    public void setJumping(boolean jumping) {
        this.jumping = jumping;
    }

    /**setter setDiving()
     * @param diving : boolean
     * modifie l'attribut diving**/
    public void setDiving(boolean diving) {
        this.diving = diving;
    }

    /**setter setFalling
     * @param falling : boolean
     * modifie l'attribut falling**/
    public void setFalling(boolean falling) {
        this.falling = falling;
    }

    /**setter setLeft
     * @param left : boolean
     * modifie l'attribut left**/
    public void setLeft(boolean left) {
        this.left = left;
    }

    /**setter setRight
     * @param right
     * modifie l'attribut right**/
    public void setRight(boolean right) {
        this.right = right;
    }

    // peut-être utiliser plutard


    public void setDx(int dx) {
        this.dx = dx;
    }
    public void setDy(int dy) {
        this.dy = dy;
    }

    /**setter computeMove
     * modifie les attribut dx et dy**/
    public void computeMove (){
        // si les qttribut sont true alors il se déplace
        if (jumping){
            setDy(-10);
            System.out.println("je monte");
        }else if (right){
            setDx(10);
        }else if (left){
            setDx(-10);
        }
    };


    private ArrayList<Block> blockTrie(){

        ArrayList<Block> res = new ArrayList<>();
        ArrayList<Block> blockField = this.field.getEnsBlock();
        for (Block b : blockField){

            // vérifier si un block se trouve sur sa trajectoire de chute
            if (this.x >= b.getX() && this.x <b.getX()+b.getWidth()){
                res.add(b);
            }
        }
        return res;
    }
    /**methode checkCollision
     *@return boolean
     * retourne true si axel est sur un block sinon false **/



    public boolean checkCollision(){
        // fonction auxiliaire pour trier les blocks potentiel
        ArrayList<Block> blk = blockTrie();

        // on va parcourir blk pour savoir si Axel est sur un bloc (on sait déjà qu'ils sont dans la zone de chute de Axel donc il ne reste plus qu'à savoir le quel est touché par axel c a d s'il est à la même hauteur que lui)

        for (Block b : blk){
            if (this.y == b.getY()) {
                return true;
            }

        }
        return false;
    }



    public void update() {}
}
