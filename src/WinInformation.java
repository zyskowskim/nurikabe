import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class WinInformation extends Frame {

    public WinInformation() {
        super("Nurikabe - Wygrałeś!!!");
    }

    public void launchFrame() throws IOException {
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(1);
            }
        });

        Color kremowy = new Color(222, 229, 184);

        Panel p = new Panel(new BorderLayout());
        p.setBackground(kremowy);

        Panel p1 = new Panel();
        p.add(p1, BorderLayout.NORTH);

        JLabel nurikabeNapis = new JLabel("Wygrałeś!!!");
        nurikabeNapis.setFont(new Font("Verdana", Font.BOLD, 45));
        nurikabeNapis.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(100, 10, 20, 10),
                BorderFactory.createLineBorder(kremowy)));
        p1.add(nurikabeNapis);

        Panel p3 = new Panel();
        p.add(p3, BorderLayout.CENTER);

        BufferedImage congratulationsImage = ImageIO.read(new File("resources/congratulation.png"));
        JLabel picLabel = new JLabel(new ImageIcon(congratulationsImage));

        p3.add(picLabel);

        Panel p2 = new Panel();
        p.add(p2, BorderLayout.SOUTH);

        JButton powrot = new JButton("Powrót");
        p2.add(powrot);
        powrot.setBackground(new Color(59, 89, 182));
        powrot.setForeground(Color.WHITE);
        powrot.setPreferredSize(new Dimension(100, 35));
        powrot.setFont(new Font("Tahoma", Font.BOLD, 15));
        powrot.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent powrotClick) {
                dispose();
            }
        });

        add(p);
        pack();
        setBounds((Toolkit.getDefaultToolkit().getScreenSize().width >> 1) - (getSize().width >> 1),
                (Toolkit.getDefaultToolkit().getScreenSize().height >> 1) - (getSize().height >> 1), getSize().width, getSize().height);
        setBounds(550, 150, 600, 400);
        EventQueue.invokeLater(() -> setVisible(true));
        setResizable(false);
    }
}

