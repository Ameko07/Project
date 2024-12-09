
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


import java.util.ArrayList;

public class GamePanel extends JPanel implements KeyListener  {
    private static final int BLOCK_HEIGHT = 10;
    private static final int AXEL_WIDTH = 30;
    private static final int AXEL_HEIGHT = 30;

    private final Axel axel;
    private final Field field;
    private Image background;
    private Image catND;
    private Image catNG;
    private Image catSD;
    private Image catSG;
    private Image catFD;
    private Image catFG;





    public GamePanel(Field field, Axel axel) {
        this.field = field;
        this.axel = axel;

        setPreferredSize(new Dimension(field.width, field.height));
        setFocusable(true);
        requestFocusInWindow();
        addKeyListener(this);

        catND = new ImageIcon("src/CatND.png").getImage();
        catNG = new ImageIcon("src/CatNG.png").getImage();
        catSD = new ImageIcon("src/CatSautD.png").getImage();
        catSG = new ImageIcon("src/catSautG.png").getImage();
        catFD = new ImageIcon("src/CatFallD.png").getImage();
        catFG = new ImageIcon("src/CatFallG.png").getImage();




        //charger image de background
        background = new ImageIcon("src/cyperpunk.png").getImage();

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Color Cbloc = new Color(168,44,31);

        //dessiner le background
        if (background != null) {
            g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
        }

        // Dessiner les blocs visibles
        for (Block b : field.getEnsBlock()) {
            if (b.getY() > field.getBottom() && b.getY() < field.getTop()) {
                // Ajuster la position pour simuler le défilement

                g.setColor(Cbloc);
                g.fillRect(b.getX(), b.getY() - field.getBottom(), b.getWidth(), Field.BLOCK_HEIGHT);
            }
        }
        //g.fillOval(axel.getX(), axel.getY() - field.getBottom(), AXEL_WIDTH, AXEL_HEIGHT);

        Image axelImage = catND;  // Image par défaut (neutre droite)

        // Si Axel est en train de sauter
        if (this.axel.isJumping()) {
            if (this.axel.isLeft()) {
                axelImage = catSG;  // Saut gauche
            }if (this.axel.isRight()) {
                axelImage = catSD;  // Saut droite
            } else if (!this.axel.isLeft() && !this.axel.isRight()) {
                axelImage = catSG;
            }
        }

        else if (this.axel.isDiving() || this.axel.isFalling()) {
            if (this.axel.isLeft()) {
                axelImage = catFG;  // Chute gauche
            } else if (this.axel.isRight()) {
                axelImage = catFD;  // Chute droite
            } else if (this.axel.checkCollision()) {
                axelImage = catND;
            }
            else {
                axelImage = catFG;
            }
        }
        if (this.axel.isLeft()) {
            axelImage = catNG;  // Neutre gauche
        }
        else if (this.axel.isRight()) {
            axelImage = catND;  // Neutre droite
        }

        g.drawImage(axelImage, axel.getX() - 15, axel.getY() - field.getBottom(), AXEL_WIDTH, AXEL_HEIGHT, this);
    }






    //methode of keyListener
    @Override
    /**methode keyPresed
     * @param e : KeyEvent
     * change l'etat d'action d'axel si une touche directionnel est appyer**/
    public void keyPressed(KeyEvent e) {
        // récupération du code enum de la touche
        int keyCode = e.getKeyCode();

        // boolean if and else are also good
        switch (keyCode){

            case KeyEvent.VK_UP: axel.setJumping(true);System.out.println("saute");break;
            case KeyEvent.VK_DOWN: axel.setDiving(true);break;
            case KeyEvent.VK_LEFT: axel.setLeft(true);break;
            case KeyEvent.VK_RIGHT: axel.setRight(true);break;

        }

    }
    /**methode keyPresed
     * @param e : KeyEvent
     * change l'etat d'action d'axel si une touche directionnel est relaché**/
    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();

        // boolean if and else are also good
        switch (keyCode){

            case KeyEvent.VK_UP: axel.setJumping(false);break;
            case KeyEvent.VK_DOWN: axel.setDiving(false);break;
            case KeyEvent.VK_LEFT: axel.setLeft(false);break;
            case KeyEvent.VK_RIGHT: axel.setRight(false);break;

        }
    }

    /**methode keyTyped
     * @param e : KeyEvent
     * do nothing**/
    @Override
    public void keyTyped(KeyEvent e) {

    }

    public static int getAxelWidth() {
        return AXEL_WIDTH;
    }

    public static int getAxelHeight() {
        return AXEL_HEIGHT;
    }
}