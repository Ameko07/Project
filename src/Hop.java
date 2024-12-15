import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class Hop {
    public static final int WIDTH = 400;
    public static final int HEIGHT = 600;
    public static final int DELAY = 40;
    private static final int[] LEVELS = {1000, 1500, 2500, 3750, 5000, 7000};

    private JFrame frame;
    private Field field;
    private Axel axel;
    private Timer timer;
    private GamePanel gamePanel;
    private JLabel score;
    private boolean started = false; //le défilement du terrain commence quand c'est true
    private Sound backgroundMusic;
    private Sound deathSound;
    private Sound bonusSound;


    public Hop() {
        new MainMenu(this);
    }

    public void startGame() {
        ArrayList<Block> block = new ArrayList<>();
        field = new Field(block,WIDTH, HEIGHT);
        this.gamePanel = new GamePanel(field, null); // Initialisation temporaire

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


        frame.setVisible(true);

        //Ajout de la musique et autres sons
        backgroundMusic = new Sound ("src/Rest_Area.WAV");
        backgroundMusic.loop();
        deathSound = new Sound("src/catmeowing.WAV");
        bonusSound = new Sound("src/bonus.wav");

        timer = new Timer(DELAY, (ActionEvent e) -> round());
        timer.start();

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
        if (axel.isDead()) {
            if (backgroundMusic.isPlaying()) {
                backgroundMusic.stop();  // Arrêter la musique
            }
            if (!deathSound.isPlaying()) {
                deathSound.playOnce();
            }
            gameOver();
            return;
        }


        levelUp();
        score.setText("score : " + axel.getScore() + " | Level : " + field.getLevel());
        frame.repaint();
        axel.bonusCollecte(field.getBonus());
        field.verifierCollecteBonus(axel);
        over();
    }

    /**methode over ()
     * @return true : boolean si axel touche la lave || false : boolean si axel est toujours dans la zonne de   jeu
     * **/
    public boolean over() {
        return axel.isDead();
    }

    private void gameOver() {
        timer.stop();

        //Options de fin de partie :
        String [] options = {"Rejouer", "Menu", "Quitter"};
        int choix = JOptionPane.showOptionDialog(
                frame,
                "Game Over ! \n Votre score : " + axel.getScore() +
                        "\n Niveau atteint : " + field.getLevel(), "GAME OVER!",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[0]
        );

        // Gestion des choix
        switch (choix) {
            case 0 -> {  // Rejouer
                frame.dispose();
                new Hop().startGame();
            }
            case 1 -> {  // Retour au menu principal
                frame.dispose();
                new MainMenu(new Hop());
            }
            case 2 -> {  // Quitter le jeu
                frame.dispose();
                System.exit(0);
            }
            default -> System.exit(0);  // quitter le jeu (option par défaut)
        }
    }




    public static void main(String[] args) {
        Hop game = new Hop();

        game.timer = new Timer(DELAY, (ActionEvent e) -> {
            game.round();
            if (game.over()) {
                game.gameOver();
            }
        });
        game.timer.start();
    }
}