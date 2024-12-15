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
        background.setLayout(new BorderLayout());

        // Bouton "Play"
        JButton playButton = new JButton("PLAY");
        playButton.setFont(new Font("Verdana", Font.BOLD, 20));
        playButton.setFocusPainted(false);
        playButton.setBackground(new Color(34, 139, 34));
        playButton.setForeground(Color.BLACK);

        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();  // Ferme le menu
                game.startGame();  // Lance le jeu
            }
        });

        // Ajout des composants
        background.add(playButton, BorderLayout.SOUTH);
        add(background);
        setVisible(true);
    }
}
