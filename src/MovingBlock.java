public class MovingBlock extends Block {
    private int direction;
    private int speed;

    public MovingBlock(int y, int fieldWidth, int maxBlockWidth) {
        super(y, fieldWidth, maxBlockWidth); //appelle constructeur de block aléatoire
        this.direction = 1; //se déplace à droite
        this.speed = 2; //vitesse de base
    }

    //met à jour position horizontale bloc
    public void update(int fieldWidth) {
        int newX = getX() + direction * speed;
        //si on atteint bord (droite ou gauche), on change de direction
        if (newX <= 0 || newX + getWidth() >= fieldWidth) {
            direction = direction * (-1);
        } else {
            setX(newX);
        }
    }
}
