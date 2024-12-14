import java.awt.*;

public class Bonus {
    //attributs
    private int x, y; //position du bonus
    private static int width = 15; //largeur bonus
    private static int height = 15; //hauteur bonus
    private static Image bonusImage;

    //constructor
    public Bonus(int x, int y, Image img) {
        this.x = x;
        this.y = y;
        bonusImage = img;
    }

    //getters
    /**gettre getX()
     * @return x**/
    public int getX() {
        return x;
    }

    /**getter getY()
     * @return y**/
    public int getY() {
        return y;
    }

    /**getter getWidth()
     * @return width **/
    public int getWidth() {
        return width;
    }

    /**getter getHeight()
     * @return height**/
    public int getHeight() {
        return height;
    }

    //setters
    /**setter setX()
     * @param x : int
     * modifie l'attribut x**/
    public void setX(int x) {
        this.x = x;
    }

    //dessin de l'image du bonus
    public void drawBonus(Graphics g, int decalageY) {
        //decalageY sert à calculer le décalage de la position Y lors du défilement

        g.drawImage(bonusImage, x, y - decalageY, width, height, null);
    }

    /**@methode bonusCollecte()
     * @param a : Axel
     * return true si axel a touché le bonus sinon false
    vérifie si axel a ramassé bonus
    **/

    public boolean bonusCollecte(Axel a) {
        return a.getX() < x + width && a.getX() + GamePanel.getAxelWidth() > x &&
                a.getY() < y + height && a.getY() + GamePanel.getAxelHeight() > y;
    }

}
