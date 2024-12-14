import java.util.ArrayList;

public class Axel {
    // Final attribut
    public static final double MAX_FALL_SPEED = -20;
    public static final double JUMP_SPEED = 20;
    public static final double GRAVITY = 1;
    public static final double DIVE_SPEED = 3 * GRAVITY;
    public static final double LATERAL_SPEED = 8;
    private final Field field;
    // coord
    private int x, y;
    private int dx, dy;


    // States
    private boolean falling;
    private boolean jumping;
    private boolean diving;
    private boolean left;
    private boolean right;
    private boolean surviving;



    private int score = 0;

    //Constructor
    public Axel(Field f, int x, int y) {
        this.field = f;
        this.x = x;
        this.y = y;
        this.dx = 0;
        this.dy = 0;
        this.surviving = true;
    }


    /**
     * getter getX()
     *
     * @return x : int
     **/
    public int getX() {
        return x;
    }

    /**
     * getter getY()
     * @return y : int
     **/
    public int getY() {
        return y;
    }

    /**getter isJumping()
     * @return jumping : boolean **/
    public boolean isJumping() {
        return jumping;
    }

    /**getter isFalling()
     * @return falling : boolean**/
    public boolean isFalling() {
        return falling;
    }

    /**getter isDiving()
     * @return diving : boolean**/
    public boolean isDiving() {
        return diving;
    }

    /**getter isLeft()
     * @return left : boolean**/
    public boolean isLeft() {
        return left;
    }

    /**getter isRight()
     * @return right : boolean**/
    public boolean isRight() {
        return right;
    }

    /****/
    public boolean getSurviving() {
        return this.surviving;
    }

    /**getter getScore()
     * @return score : boolean**/
    public int getScore() {
        return score;
    }


    // setting bolean attribut



    /**
     * setter setJumping()
     * @param jumping : boolean
     * set Jumping
     **/
    public void setJumping(boolean jumping) {
        this.jumping = jumping;
    }


    /**
     * setter setDiving()
     * @param diving : boolean
     * modifie l'attribut diving
     **/
    public void setDiving(boolean diving) {
        this.diving = diving;
    }

    /**
     * setter setFalling
     * @param falling : boolean
     * modifie l'attribut falling
     **/
    public void setFalling(boolean falling) {
        this.falling = falling;
    }

    /**
     * setter setLeft
     * @param left : boolean
     * modifie l'attribut left
     **/
    public void setLeft(boolean left) {
        this.left = left;
    }

    /**
     * setter setRight
     * @param right : boolean
     * modifie l'attribut right
     **/
    public void setRight(boolean right) {
        this.right = right;
    }

    /**method isDead()
     * @return true || false si axel touche la lave  **/
    public boolean isDead() {
        return y >= field.getTop();
    }

    /**setter setSurviving()
     * @param surviving
     * modfie l'attribut surviving**/
    public void setSurviving(boolean surviving) {
        this.surviving = surviving;
    }

    /**setter setDx()
     * @param dx : int
     * modifie l'atttribut dx**/
    public void setDx(int dx) {
        this.dx = dx;
    }

    /**setter setDx()
     * @param dy : int
     * modifie l'atttribut dy**/
    public void setDy(int dy) {
        this.dy = dy;
    }


    //methodes
    /**methode calculScore()
     * calcule score par rapport dernier plus block atteint**/
    public void calculScore() {

        // active le score seulement en atterrissant sur le plus haut block atteint
        if (checkCollision()) {

            // Y+10 pour le visuel
            if (score <= field.getHeight() - y + 10) {
                score = field.getHeight() - (y + 10);
            }
        }


    }


    /**incrementScore()
     * @param points : int
     * rajoute x points au score **/
    public void incrementScore(int points) {
        score += points;
    }



    /**
     * setter computeMove
     * modifie les attribut dx et dy
     **/

    public void computeMove() {
        dx = 0;
        // si les attribut sont true alors il se déplace
        if (left) {
            dx = -((int) LATERAL_SPEED);
        } else if (right) {
            dx = (int) LATERAL_SPEED;
        } else dx = 0;


        //saut et atterrissage sur bloc
        if (jumping && checkCollision()) {
            dy = - (int) JUMP_SPEED;
            falling = true;
            jumping = false;
        } if (falling) {
            dy = dy + (int) GRAVITY;
            if(checkCollision()) {
                dy = 0;

            }
            if (dy < MAX_FALL_SPEED) {
                dy = (int) MAX_FALL_SPEED;
            }

            if (diving) {
                dy = dy + (int) DIVE_SPEED;
            }
        }
        if (checkCollision()) {
            falling = false;
            dy = 0;

        }
    }


    /**methode blocTrie()
     * @return res : ArrayList<Block>
     * trie les block susceptible d'être sur le passage d'axel **/
    private ArrayList<Block> blockTrie() {

        ArrayList<Block> res = new ArrayList<>();
        ArrayList<Block> blockField = this.field.getEnsBlock();
        for (Block b : blockField) {

            // vérifier si un block se trouve sur sa trajectoire de chute
            if (this.x >= b.getX() && this.x < b.getX() + b.getWidth()) {
                res.add(b);
            }
        }
        return res;
    }

    /**
     * methode checkCollision
     *
     * @return boolean
     * retourne true si axel est sur un block sinon false
     **/


    public boolean checkCollision() {
        ArrayList<Block> blk = blockTrie();

        for (Block b : blk) {
            // Vérifie si Axel est aligné horizontalement avec le bloc
            if (x + GamePanel.getAxelWidth() > b.getX() && x < b.getX() + b.getWidth()) {
                // Vérifie si Axel est juste au-dessus du bloc
                if (y + GamePanel.getAxelHeight() >= b.getY() && y + GamePanel.getAxelHeight() <= b.getY() + dy) {
                    // Positionner Axel juste au-dessus du bloc
                    y = b.getY() - GamePanel.getAxelHeight();
                    dy = 0; // Réinitialiser la vitesse verticale
                    return true;
                }
            }
        }
        return false;
    }

    /**methode bonusCollecte()
     * @param bonuses : ArrayList<Bonus>
     * @return boolean
     * rajoute des points au score et return true si le bonus est collecté **/

    public void bonusCollecte(ArrayList<Bonus> bonuses) {
        bonuses.removeIf(bonus -> {
            if (bonus.bonusCollecte(this)) {
                score += 10; // Ajouter 10 points au score
                return true; // Retirer le bonus
            }
            return false;
        });
    }

    //chepa comment le documenter
    /****/
    public void update() {
        computeMove(); // Calcule les déplacements
        x = x + dx;
        if (falling) {
            y = y + dy;
        } else {
            falling = true;
        }

        calculScore();

    }
}
//test
