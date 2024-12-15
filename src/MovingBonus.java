import java.awt.*;

public class MovingBonus extends Bonus{
    private int direction;
    private int speed;
    private Block b;

    //constructor
    public MovingBonus(int x, int y, Image img, int direction,int speed ,Block b){
        super(x,y,img);
        this.direction = direction;
        this.speed = speed;
        this.b = b;

    }

    public void update(int fieldWidth) {
        int newX = b.getX() + direction * speed;
        //si on atteint bord (droite ou gauche), on change de direction
        if (newX <= 0 || newX + b.getWidth() >= fieldWidth) {
            direction = direction * (-1);
        } else {
            setX(newX);
        }
    }



    @Override

    public int getX() {
        return super.getX();
    }

    @Override
    public int getY() {
        return super.getY();
    }
}
