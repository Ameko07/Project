import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JFrame {
    private final Hop game;

    public MainMenu(Hop game) {
        this.game = game;
        setTitle("Hop! - Menu Principal");
        setSize(Hop.WIDTH, Hop.HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Image de fond
        JLabel background = new JLabel(new ImageIcon("src/MenuImage.png"));
        background.setLayout(new GridBagLayout());

        //Panel qui contient les boutons
        JPanel buttonPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        buttonPanel.setOpaque(false); //fond transparent

        // Bouton "Play" pour lancer le jeu
        JButton playButton = new JButton("PLAY");
        playButton.setFont(new Font("Verdana", Font.BOLD, 20));
        playButton.setFocusPainted(false);
        playButton.setBackground(new Color(34, 139, 34));
        playButton.setForeground(Color.BLACK);
        playButton.setBounds(50, 300, 300, 50);

        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();  // Ferme le menu
                game.startGame();  // Lance le jeu
            }
        });

        //Bouton "Quit" pour quitter le jeu
        JButton quitButton = createButton("QUIT", new Color(178,34, 34), Color.BLACK, 20);
        quitButton.addActionListener(e -> System.exit(0));

        //Ajout des boutons au panel
        buttonPanel.add(playButton);
        buttonPanel.add(quitButton);

        // Ajout des composants
        background.add(buttonPanel, new GridBagConstraints());
        add(background);
        setVisible(true);
    }

    /** Créer un bouton personnalité **/
    private JButton createButton(String text, Color bgColor, Color fgColor, int fontSize) {
        JButton button = new JButton(text);
        button.setFont(new Font("Verdana", Font.BOLD, fontSize));
        button.setFocusPainted(false);
        button.setBackground(bgColor);  // Couleur de fond
        button.setForeground(fgColor);  // Couleur du texte
        return button;
    }
}
