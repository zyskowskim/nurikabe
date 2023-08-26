import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;

public class MainFrame extends Frame {

    public MainFrame() {
        super("Nurikabe");
    }

    public void launchFrame() {
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

        ImageIcon img = new ImageIcon("resources/nurikabeIcon.png");
        setIconImage(img.getImage());

        JLabel nurikabeNapis = new JLabel("Nurikabe\n");
        nurikabeNapis.setFont(new Font("Verdana", Font.BOLD, 40));
        nurikabeNapis.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(10, 10, 20, 10),
                BorderFactory.createLineBorder(kremowy)));
        p1.add(nurikabeNapis);

        Panel p2 = new Panel();
        p.add(p2, BorderLayout.CENTER);

        JLabel tekst = new JLabel(String.format("<html><div WIDTH=%d>%s</div></html>", 750, "Nurikabe to japońska łamigłówka w stylu sudoku. Ta gra, czasami nazywana „wyspą w przepływie”, to układanka binarna. Dla każdej komórki możemy zdecydować, czy będzie ona biała czy czarna zgodnie z określonymi zasadami."));
        tekst.setFont(new Font("Verdana", Font.BOLD, 20));
        tekst.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(10, 400, 40, 400),
                BorderFactory.createLineBorder(kremowy)));
        p2.add(tekst);

        JLabel wybor = new JLabel("Wybierz poziom\n");
        wybor.setFont(new Font("Verdana", Font.BOLD, 30));
        wybor.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(10, 400, 10, 400),
                BorderFactory.createLineBorder(kremowy)));
        p2.add(wybor);

        JButton latwy = new JButton("Łatwy");
        p2.add(latwy);
        latwy.setBackground(new Color(59, 182, 98));
        latwy.setForeground(Color.WHITE);
        latwy.setPreferredSize(new Dimension(120,50));
        latwy.setFont(new Font("Tahoma", Font.BOLD, 15));
        latwy.addActionListener(easyLevelClick -> {
            EasyLevel easyLevelGuiWindow = new EasyLevel();
            easyLevelGuiWindow.launchFrame();
            dispose();
        });

        JButton sredni = new JButton("Średni");
        p2.add(sredni);
        sredni.setBackground(new Color(141, 129, 49));
        sredni.setForeground(Color.WHITE);
        sredni.setPreferredSize(new Dimension(120,50));
        sredni.setFont(new Font("Tahoma", Font.BOLD, 15));
        sredni.addActionListener(easyLevelClick -> {
            MediumLevel mediumLevelGuiWindow = new MediumLevel();
            mediumLevelGuiWindow.launchFrame();
            dispose();
        });

        JButton trudny = new JButton("Trudny");
        p2.add(trudny);
        trudny.setBackground(new Color(159, 31, 31));
        trudny.setForeground(Color.WHITE);
        trudny.setPreferredSize(new Dimension(120,50));
        trudny.setFont(new Font("Tahoma", Font.BOLD, 15));
        trudny.addActionListener(hardLevelClick -> {
            HardLevel hardLevelGuiWindow = new HardLevel();
            hardLevelGuiWindow.launchFrame();
            dispose();
        });

        Panel p3 = new Panel();
        p.add(p3, BorderLayout.SOUTH);

        JButton wczytaj = new JButton("Wczytaj poziom");
        p3.add(wczytaj);
        wczytaj.setBackground(new Color(59, 89, 182));
        wczytaj.setForeground(Color.WHITE);
        wczytaj.setPreferredSize(new Dimension(180,35));
        wczytaj.setFont(new Font("Tahoma", Font.BOLD, 15));
        wczytaj.addActionListener(wczytajClick -> {
            try {
                LoadFile.loadFile();
                if (LoadFile.getGameID() == 1){
                    EasyLevel easyLevelGuiWindow = new EasyLevel();
                    easyLevelGuiWindow.launchLoadedFrame();
                }
                else if (LoadFile.getGameID() == 2){
                    MediumLevel mediumLevelGuiWindow = new MediumLevel();
                    mediumLevelGuiWindow.launchLoadedFrame();
                }
                else if (LoadFile.getGameID() == 3){
                    HardLevel hardLevelGuiWindow = new HardLevel();
                    hardLevelGuiWindow.launchLoadedFrame();
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        });

        JButton zasady = new JButton("Zasady");
        p3.add(zasady);
        zasady.setBackground(new Color(59, 89, 182));
        zasady.setForeground(Color.WHITE);
        zasady.setPreferredSize(new Dimension(100,35));
        zasady.setFont(new Font("Tahoma", Font.BOLD, 15));
        zasady.addActionListener(zasadyClick -> {
            RulesFrame rulesFrameGuiWindow = new RulesFrame();
            rulesFrameGuiWindow.launchFrame();
        });

        JButton autorzy = new JButton("Autorzy");
        p3.add(autorzy);
        autorzy.setBackground(new Color(59, 89, 182));
        autorzy.setForeground(Color.WHITE);
        autorzy.setPreferredSize(new Dimension(100,35));
        autorzy.setFont(new Font("Tahoma", Font.BOLD, 15));
        autorzy.setMargin((new Insets(0, 0, 0, 0)));
        autorzy.addActionListener(autorzyClick ->{
            AuthorsFrame authorsFrame = new AuthorsFrame();
            authorsFrame.launchFrame();
        });

        JButton wyjdz = new JButton("Wyjdź");
        p3.add(wyjdz);
        wyjdz.setBackground(new Color(59, 89, 182));
        wyjdz.setForeground(Color.WHITE);
        wyjdz.setPreferredSize(new Dimension(100,35));
        wyjdz.setFont(new Font("Tahoma", Font.BOLD, 15));
        wyjdz.setMargin(new Insets(0, 0, 0, 0));
        wyjdz.addActionListener(wyjdzClick -> System.exit(1));

        add(p);
        pack();
        setBounds((Toolkit.getDefaultToolkit().getScreenSize().width >> 1) - (getSize().width >> 1),
                (Toolkit.getDefaultToolkit().getScreenSize().height >> 1) - (getSize().height >> 1), getSize().width, getSize().height);
        setBounds(400, 100, 800, 550);
        EventQueue.invokeLater(() -> setVisible(true));
        setResizable(false);
    }

    public static void main(String[] args) {
        MainFrame mainFrameGuiWindow = new MainFrame();
        mainFrameGuiWindow.launchFrame();
    }
}
