
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


import java.util.ArrayList;

public class GamePanel extends JPanel implements KeyListener  {
    private static final int BLOCK_HEIGHT = 10;
    private static final int AXEL_WIDTH = 10;
    private static final int AXEL_HEIGHT = 10;

    private final Axel axel;
    private final Field field;
    private Image background;


    


    public GamePanel(Field field, Axel axel) {
        this.field = field;
        this.axel = axel;

        setPreferredSize(new Dimension(field.width, field.height));
        setFocusable(true);
        requestFocusInWindow();
        addKeyListener(this);
        
        //catN = ImageIcon("C:/Users/lafat/Université/IPO/ProjetIPO/cat1.jpg").getImage();

       

        //charger image de background
        background = new ImageIcon("src/cyperpunk.png").getImage();

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background,0,0,field.getWidth(), field.getHeight(),this);
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
        g.fillOval(axel.getX(), axel.getY() - field.getBottom(), AXEL_WIDTH, AXEL_HEIGHT);

        // Dessiner Axel (calibré avec le défilement)
        //g.drawImage(catN, axel.getX(), axel.getY() - field.getBottom(),50,50,this);
        

        //AXEL_WIDTH, AXEL_HEIGHT
    }






    //methode of keyListener

    /**methode keyPresed
     * @param e : KeyEvent
     * change l'etat d'action d'axel si une touche directionnel est appyer**/
    @Override
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