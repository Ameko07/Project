import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class Hop {
    public static final int WIDTH = 400;
    public static final int HEIGHT = 600;
    public static final int DELAY = 40;

    private final JFrame frame;
    private final Field field;
    private final Axel axel;
    private Timer timer;
    private GamePanel gamePanel;

    public Hop() {
        ArrayList<Block> block = new ArrayList<>();
        this.field = new Field(block,WIDTH, HEIGHT);
        //this.axel = new Axel(field, WIDTH/2, field.START_ALTITUDE);
        this.axel = new Axel(field, 10, 10);
        this.gamePanel = new GamePanel(field, axel);

        this.frame = new JFrame("Hop!");
        frame.add(gamePanel);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void round() {
        axel.update();
        field.update();
        frame.repaint();
    }

    /**methode over ()
     * @return true : boolean si axel touche la lave || false : boolean si axel est toujours dans la zonne ed jeu
     * **/
    public boolean over() {
        if (this.axel.getY() == HEIGHT){
            return true;
        }else {
            return false;
        }

    }

    public static void main(String[] args) {
        Hop game = new Hop();

        game.timer = new Timer(DELAY, (ActionEvent e) -> {
            game.round();
            if (game.over()) {
                game.timer.stop();
                game.frame.remove(game.gamePanel);
            }
        });
        game.timer.start();
    }
}
