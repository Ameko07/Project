import java.util.ArrayList;

public class Axel {
    public static final double MAX_FALL_SPEED = -20;
    public static final double JUMP_SPEED = 20;
    public static final double GRAVITY = 1;
    public static final double DIVE_SPEED = 3 * GRAVITY;
    public static final double LATERAL_SPEED = 8;

    private int x, y;
    private int dx, dy;

    private boolean falling;
    private boolean jumping;
    private boolean diving;
    private boolean left;
    private boolean right;

    private boolean surviving;

    private final Field field;

    private int score = 0;

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
     *
     * @return y : int
     **/
    public int getY() {
        return y;
    }

    public boolean isFalling() {
        return falling;
    }

    public boolean isDiving() {
        return diving;
    }

    public boolean isLeft() {
        return left;
    }

    public boolean isRight() {
        return right;
    }
    // setting bolean attribut

    /**
     * setter setJumping
     *
     * @param jumping : boolean
     *                set Jumping
     **/
    public void setJumping(boolean jumping) {
        this.jumping = jumping;
    }


    /**
     * setter setDiving()
     *
     * @param diving : boolean
     *               modifie l'attribut diving
     **/
    public void setDiving(boolean diving) {
        this.diving = diving;
    }

    /**
     * setter setFalling
     *
     * @param falling : boolean
     *                modifie l'attribut falling
     **/
    public void setFalling(boolean falling) {
        this.falling = falling;
    }

    /**
     * setter setLeft
     *
     * @param left : boolean
     *             modifie l'attribut left
     **/
    public void setLeft(boolean left) {
        this.left = left;
    }

    /**
     * setter setRight
     *
     * @param right modifie l'attribut right
     **/
    public void setRight(boolean right) {
        this.right = right;
    }


    public boolean getSurviving() {
        return this.surviving;
    }

    public boolean isDead() {

        return y >= field.getTop();
    }

    public boolean isJumping() {
        return jumping;
    }

    // peut-être utiliser plutard


    public int getScore() {
        return score;
    }

    public void calculScore() {
        if (checkCollision()) {
            if (score <= field.getHeight() - y + 10) {
                score = field.getHeight() - (y + 10);
            }
        }


    }

    public void incrementScore(int points) {
        score += points;
    }

    public void setDx(int dx) {
        this.dx = dx;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }

    public void setSurviving(boolean surviving) {
        this.surviving = surviving;
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

    //vérifie si un bonus est ramassé
    public void bonusCollecte(ArrayList<Bonus> bonuses) {
        bonuses.removeIf(bonus -> {
            if (bonus.bonusCollecte(this)) {
                score += 10; // Ajouter 10 points au score
                return true; // Retirer le bonus
            }
            return false;
        });
    }


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

