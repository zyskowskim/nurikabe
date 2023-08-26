import javax.swing.*;
import java.awt.*;

public class RulesFrame extends JFrame {

    public RulesFrame() {
        super("Zasady");
    }

    JLabel rules = new JLabel("<html><ul style='margin-bottom: 10px;'>" +
            "<li style='margin-bottom: 10px;'>Każda wyspa zawiera dokładnie jedną liczbę (wskazówkę).</li>" +
            "<li style='margin-bottom: 10px;'>Liczba pól na każdej wyspie jest równa wartości wskazówki.</li>" +
            "<li style='margin-bottom: 10px;'>Wyspy mogą stykać się tylko kątami.</li>" +
            "<li style='margin-bottom: 10px;'>Między wyspami przepływa rzeka, która nie może mieć rozmiaru 2x2 lub większego.</li>" +
            "<li style='margin-bottom: 10px;'>Wszystkie pola rzeki muszą być ze sobą połączone.</li>" +
            "</ul></html>");

    JLabel text = new JLabel("Zasady");

    JLabel imageExample = new JLabel(new ImageIcon("resources/example.png"));
    ImageIcon img = new ImageIcon("resources/nurikabeIcon.png");
    JLabel exampleText = new JLabel("Przykładowe rozwiązanie planszy Nurikabe", SwingConstants.CENTER);

    JButton powrot = new JButton("Powrót");

    public void launchFrame() {

        Color kremowy = new Color(222, 229, 184);

        JPanel layout = new JPanel(new BorderLayout());
        layout.setBackground(kremowy);
        setSize(1100, 600);

        JPanel top = new JPanel();
        top.setBackground(kremowy);
        layout.add(top, BorderLayout.NORTH);

        JPanel center = new JPanel();
        center.setBackground(kremowy);
        layout.add(center, BorderLayout.CENTER);

        JPanel bottom = new JPanel();
        bottom.setBackground(kremowy);
        layout.add(bottom, BorderLayout.SOUTH);

        setIconImage(img.getImage());

        text.setFont(new Font("Verdana", Font.BOLD, 35));
        text.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(10, 0, 10, 0),
                BorderFactory.createLineBorder(kremowy)));
        top.add(text);

        center.add(rules, BorderLayout.NORTH);
        rules.setFont(new Font("Verdana", Font.BOLD, 20));

        center.add(imageExample, BorderLayout.CENTER);

        center.add(exampleText, BorderLayout.SOUTH);
        exampleText.setFont(new Font("Verdana", Font.BOLD, 20));

        bottom.add(powrot);
        powrot.setBackground(new Color(59, 89, 182));
        powrot.setForeground(Color.WHITE);
        powrot.setPreferredSize(new Dimension(150, 35));
        powrot.setFont(new Font("Tahoma", Font.BOLD, 15));
        powrot.addActionListener(zmienClick -> dispose());

        add(layout);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }
}