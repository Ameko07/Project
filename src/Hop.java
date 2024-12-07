import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class Hop {
    public static final int WIDTH = 400;
    public static final int HEIGHT = 600;
    public static final int DELAY = 40;
    private static final int[] LEVELS = {1000, 1500, 2500, 3750, 5000, 7000};

    private final JFrame frame;
    private final Field field;
    private final Axel axel;
    private Timer timer;
    private GamePanel gamePanel;
    private JLabel score;
    private boolean started = false; //le défilement du terrain commence quand c'est true

    public Hop() {
        ArrayList<Block> block = new ArrayList<>();
        this.field = new Field(block,WIDTH, HEIGHT);
        Block b = field.getEnsBlock().get(0);
        this.axel = new Axel(field, b.getX()+b.getWidth()/2, b.getY()-gamePanel.getAxelWidth());
        this.gamePanel = new GamePanel(field, axel);

        this.frame = new JFrame("Hop!");

        this.score = new JLabel("score = ");
        score= new JLabel("Score: 0 || Level : 1", SwingConstants.CENTER);
        score.setFont(new Font("Arial", Font.BOLD, 18));
        frame.add(score, BorderLayout.NORTH);


        frame.add(gamePanel);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void levelUp() {
        int score = axel.getScore();
        int currentLevel = field.getLevel();
        if (currentLevel <= LEVELS.length && score >= LEVELS[currentLevel - 1]) {
            field.incrementeDifficulte();
        }
    }

    public void round() {
        if (!started && axel.isJumping()) {
            started = true; //le défilement commence quand Axel saute
            field.setScrolling(true);
        }
        if (started) {
            field.update();
        }
        axel.update();
        levelUp();
        score.setText("score : " + axel.getScore() + " | Level : " + field.getLevel());
        frame.repaint();
        over();
    }

    /**methode over ()
     * @return true : boolean si axel touche la lave || false : boolean si axel est toujours dans la zonne de   jeu
     * **/
    public boolean over() {
        return axel.isDead();
    }

//    public boolean finPartie(){
//        if (over()) {
//            timer.stop();
//            frame.remove(gamePanel);
//            return true;
//        }
//        return false;
//    }



    public static void main(String[] args) {
        Hop game = new Hop();

        game.timer = new Timer(DELAY, (ActionEvent e) -> {
            game.round();
            if (game.over()) {
                //System.out.println("mort");
                game.timer.stop();
                game.frame.remove(game.gamePanel);
            }
        });
        game.timer.start();
    }
}