import java.awt.*;

public class Bonus {
    private int x, y; //position du bonus
    private static int width = 15; //largeur bonus
    private static int height = 15; //hauteur bonus
    private static Image bonusImage;

    public Bonus(int x, int y, Image img) {
        this.x = x;
        this.y = y;
        bonusImage = img;
    }

    //getters
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    //dessin de l'image du bonus
    public void drawBonus(Graphics g, int decalageY) {
        //decalageY sert à calculer le décalage de la position Y lors du défilement
        g.drawImage(bonusImage, x, y - decalageY, width, height, null);
    }

    //vérifie si axel a ramassé bonus
    public boolean bonusCollecte(Axel a) {
        return a.getX() < x + width && a.getX() + GamePanel.getAxelWidth() > x &&
                a.getY() < y + height && a.getY() + GamePanel.getAxelHeight() > y;    }
}
