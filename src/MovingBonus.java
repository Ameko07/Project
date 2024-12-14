import java.awt.*;

public class MovingBonus extends Bonus{
    private int direction;
    private int speed;

    //constructor
    public MovingBonus(int x, int y, Image img, int direction,int speed ){
        super(x,y,img);
        this.direction = 1;
        this.speed = 2;


    }

    public void update(int fieldWidth) {
        int newX = getX() + direction * speed;
        //si on atteint bord (droite ou gauche), on change de direction
        if (newX <= 0 || newX + getWidth() >= fieldWidth) {
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
