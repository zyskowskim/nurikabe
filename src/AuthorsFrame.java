import javax.swing.*;
import java.awt.*;

public class AuthorsFrame extends JFrame {

    Color kremowy = new Color(222, 229, 184);

    JPanel layout = new JPanel(new BorderLayout());

    public AuthorsFrame(){
        super("Autorzy");
    }

    JLabel authorsText = new JLabel("Autorzy");

    JLabel authors = new JLabel("<html><ul style='margin-bottom: 10px;'>" +
            "<li style='margin-bottom: 10px;'>Mateusz Kowalski</li>" +
            "<li style='margin-bottom: 10px;'>Mateusz Zyskowski</li>" +
            "<li style='margin-bottom: 10px;'>Filip Cukierski</li>" +
            "<li style='margin-bottom: 10px;'>Piotr Kosacz</li>" +
            "</ul></html>");

    ImageIcon img = new ImageIcon("resources/nurikabeIcon.png");

    JPanel bottom = new JPanel();

    JButton powrot = new JButton("Powrót");

    public void launchFrame(){
        layout.add(authorsText, BorderLayout.NORTH);
        layout.add(authors, BorderLayout.CENTER);
        layout.add(bottom, BorderLayout.SOUTH);
        layout.setBackground(kremowy);

        bottom.add(powrot);
        bottom.setBackground(kremowy);

        authors.setFont(new Font("Verdana", Font.BOLD, 25));

        authorsText.setFont(new Font("Verdana", Font.BOLD, 35));
        authorsText.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(10, 0, 0, 0),
                BorderFactory.createLineBorder(kremowy)));
        authorsText.setHorizontalAlignment(JLabel.CENTER);

        powrot.setBackground(new Color(59, 89, 182));
        powrot.setForeground(Color.WHITE);
        powrot.setPreferredSize(new Dimension(150, 35));
        powrot.setFont(new Font("Tahoma", Font.BOLD, 15));
        powrot.addActionListener(zmienClick -> dispose());

        setIconImage(img.getImage());
        setSize(600, 400);
        add(layout);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }
}
