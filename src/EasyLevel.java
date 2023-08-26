import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class EasyLevel extends Frame {

    public static int levelId = 1;
    public static int ROWS = 4;
    public static int COLUMNS = 5;
    public static final Color[] COLORS = {Color.WHITE, Color.DARK_GRAY};
    public static final Color[][] cellColors = new Color[ROWS][COLUMNS];
    private static int finalSum = 0;
    public int mistakesNum = 0;
    public int[][] tablicaTestowa = new int[ROWS][COLUMNS];
    JPanel p2 = new JPanel();
    private JLabel mistakeNumberLabel;

    private void applyColorTransition(JPanel cell, Color startColor, Color endColor) {
        Transition colorTransition = new Transition(cell, startColor, endColor);
        colorTransition.startTransition();
    }

    private void saveToFile() {
        SaveToFile saveToFile = new SaveToFile(levelId,finalSum,mistakesNum,tablicaTestowa);
        saveToFile.Save();
    }

    private void saveAsPhoto() {
        SaveAsPhoto saveAsPhoto = new SaveAsPhoto();
        saveAsPhoto.Save(p2);
    }

    public EasyLevel() {
        super("Nurikabe - Poziom Łatwy");
    }

    public void launchFrame() {
        finalSum = 0;
        mistakesNum = 0;
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLUMNS; col++) {
                cellColors[row][col] = COLORS[0];
            }
        }

        ImageIcon img = new ImageIcon("resources/nurikabeIcon.png");
        setIconImage(img.getImage());

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(1);
            }
        });

        Font czcionkaWKomorce = new Font("Verdana", Font.BOLD, 35);
        Color kremowy = new Color(222, 229, 184);

        Panel p = new Panel(new BorderLayout());
        p.setBackground(kremowy);

        Panel p1 = new Panel();
        p.add(p1, BorderLayout.NORTH);

        JLabel nurikabeNapis = new JLabel("Poziom Łatwy\n");
        nurikabeNapis.setFont(new Font("Verdana", Font.BOLD, 40));
        nurikabeNapis.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(10, 0, 10, 0),
                BorderFactory.createLineBorder(kremowy)));
        p1.add(nurikabeNapis);

        JPanel westBlankSpace = new JPanel();
        westBlankSpace.setBackground(kremowy);
        westBlankSpace.setPreferredSize(new Dimension(150, 200));
        p.add(westBlankSpace, BorderLayout.WEST);

        mistakeNumberLabel = new JLabel("");
        mistakeNumberLabel.setBorder(BorderFactory.createEmptyBorder(100, 0, 0, 0));
        mistakeNumberLabel.setFont(new Font("Verdana", Font.BOLD, 15));
        westBlankSpace.add(mistakeNumberLabel);

        JPanel eastBlankSpace = new JPanel();
        eastBlankSpace.setBackground(kremowy);
        eastBlankSpace.setPreferredSize(new Dimension(150, 200));
        p.add(eastBlankSpace, BorderLayout.EAST);

        p2.setLayout(new GridLayout(ROWS, COLUMNS));
        p2.setBackground(kremowy);
        p.add(p2, BorderLayout.CENTER);

        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLUMNS; col++) {
                JPanel cell = new JPanel();
                cell.setBackground(COLORS[0]);
                cell.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                final int r = row, c = col;

                if (row == 0 && col == 2) {
                    JLabel wartosc = new JLabel("1");
                    wartosc.setFont(czcionkaWKomorce);
                    wartosc.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
                    cell.add(wartosc, BorderLayout.CENTER);
                }

                if (row == 2 && col == 0 || row == 2 && col == 4 || row == 3 && col == 2) {
                    JLabel wartosc = new JLabel("2");
                    wartosc.setFont(czcionkaWKomorce);
                    wartosc.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
                    cell.add(wartosc, BorderLayout.CENTER);
                }

                cell.addMouseListener(new MouseAdapter() {
                    private int index = 0;

                    final boolean warunek1 = (r == 0 && c == 0 || r == 0 && c == 1 || r == 0 && c == 3 ||
                            r == 0 && c == 4 || r == 1 && c == 1 || r == 1 && c == 2 || r == 1 && c == 3 ||
                            r == 2 && c == 1 || r == 2 && c == 3 || r == 3 && c == 0 || r == 3 && c == 1 ||
                            r == 3 && c == 3 || r == 3 && c == 4);
                    final boolean warunek2 = (r == 1 && c == 0 || r == 1 && c == 4 || r == 2 && c == 2);

                    @Override
                    public void mouseClicked(MouseEvent e) {
                        Component[] components = cell.getComponents();
                        boolean hasLabel = false;
                        for (Component component : components) {
                            if (component instanceof JLabel) {
                                hasLabel = true;
                                break;
                            }
                        }

                        if (hasLabel) {
                            cell.setEnabled(false);
                        } else {
                            if (cellColors[r][c] == COLORS[0]){
                                index = 1;
                            } else {
                                index = 0;
                            }
                            cellColors[r][c] = COLORS[index];
                            tablicaTestowa[r][c] = index;;
                            Color startColor = cell.getBackground();
                            Color endColor = COLORS[index];
                            applyColorTransition(cell, startColor, endColor);
                            if (COLORS[index] != COLORS[0]) {
                                if (warunek1) {
                                    finalSum++;
                                }
                                if (warunek2) {
                                    finalSum--;
                                    mistakesNum++;
                                }
                            } else {
                                if (warunek1) {
                                    finalSum--;
                                }
                                if (warunek2) {
                                    finalSum++;
                                    mistakesNum--;
                                }
                            }
                        }

                        if (finalSum == 13) {
                            WinInformation winInformationGuiWindow = new WinInformation();
                            try {
                                winInformationGuiWindow.launchFrame();
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                    }
                });
                p2.add(cell);
            }
        }

        JPanel p3 = new JPanel();
        p3.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        p3.setBackground(kremowy);
        p.add(p3, BorderLayout.SOUTH);

        JButton check = new JButton("Sprawdź ilość błędów");
        p3.add(check);
        check.setBackground(new Color(59, 89, 182));
        check.setForeground(Color.WHITE);
        check.setPreferredSize(new Dimension(200, 35));
        check.setFont(new Font("Tahoma", Font.BOLD, 15));
        check.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                mistakeNumberLabel.setText("<html>Liczba <br/>błędów: <br/>" + mistakesNum + "</html>");
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                mistakeNumberLabel.setText("");
            }
        });

        JButton zmien = new JButton("Menu");
        p3.add(zmien);
        zmien.setBackground(new Color(59, 89, 182));
        zmien.setForeground(Color.WHITE);
        zmien.setPreferredSize(new Dimension(90, 35));
        zmien.setFont(new Font("Tahoma", Font.BOLD, 15));
        zmien.addActionListener(zmienClick -> {
            MainFrame mainFrameGuiWindow = new MainFrame();
            mainFrameGuiWindow.launchFrame();
            dispose();
        });

        JButton reset = new JButton("Reset");
        p3.add(reset);
        reset.setBackground(new Color(59, 89, 182));
        reset.setForeground(Color.WHITE);
        reset.setPreferredSize(new Dimension(100, 35));
        reset.setFont(new Font("Tahoma", Font.BOLD, 15));
        reset.addActionListener(resetClick -> {
            EasyLevel easyLevelFrameGuiWindow = new EasyLevel();
            easyLevelFrameGuiWindow.launchFrame();
            dispose();
        });

        JButton zapisz = new JButton("Zapisz jako obraz");
        p3.add(zapisz);
        zapisz.setBackground(new Color(59, 89, 182));
        zapisz.setForeground(Color.WHITE);
        zapisz.setPreferredSize(new Dimension(180, 35));
        zapisz.setFont(new Font("Tahoma", Font.BOLD, 15));
        zapisz.addActionListener(zapiszClick -> saveAsPhoto());

        JButton zapiszGre = new JButton("Zapisz grę");
        p3.add(zapiszGre);
        zapiszGre.setBackground(new Color(59, 89, 182));
        zapiszGre.setForeground(Color.WHITE);
        zapiszGre.setPreferredSize(new Dimension(125, 35));
        zapiszGre.setFont(new Font("Tahoma", Font.BOLD, 15));
        zapiszGre.addActionListener(zapiszGreClick -> saveToFile());

        JButton wyjdz = new JButton("Wyjdź");
        p3.add(wyjdz);
        wyjdz.setBackground(new Color(59, 89, 182));
        wyjdz.setForeground(Color.WHITE);
        wyjdz.setPreferredSize(new Dimension(100, 35));
        wyjdz.setFont(new Font("Tahoma", Font.BOLD, 15));
        wyjdz.addActionListener(wyjdzClick -> System.exit(1));

        add(p);
        pack();
        setBounds((Toolkit.getDefaultToolkit().getScreenSize().width >> 1) - (getSize().width >> 1),
                (Toolkit.getDefaultToolkit().getScreenSize().height >> 1) - (getSize().height >> 1), getSize().width, getSize().height);
        setBounds(400, 100, 900, 550);
        EventQueue.invokeLater(() -> setVisible(true));
        setResizable(false);
    }

    public void launchLoadedFrame() {
        int value = 0;
        finalSum = LoadFile.getPositiveNumber();
        mistakesNum = LoadFile.getMistakesNumber();
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLUMNS; col++) {
                cellColors[row][col] = COLORS[LoadFile.getDataList().get(value)];
                value++;
            }
        }

        ImageIcon img = new ImageIcon("resources/nurikabeIcon.png");
        setIconImage(img.getImage());

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(1);
            }
        });

        Font czcionkaWKomorce = new Font("Verdana", Font.BOLD, 35);
        Color kremowy = new Color(222, 229, 184);

        Panel p = new Panel(new BorderLayout());
        p.setBackground(kremowy);

        Panel p1 = new Panel();
        p.add(p1, BorderLayout.NORTH);

        JLabel nurikabeNapis = new JLabel("Poziom Łatwy\n");
        nurikabeNapis.setFont(new Font("Verdana", Font.BOLD, 40));
        nurikabeNapis.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(10, 0, 10, 0),
                BorderFactory.createLineBorder(kremowy)));
        p1.add(nurikabeNapis);

        JPanel westBlankSpace = new JPanel();
        westBlankSpace.setBackground(kremowy);
        westBlankSpace.setPreferredSize(new Dimension(150, 200));
        p.add(westBlankSpace, BorderLayout.WEST);

        mistakeNumberLabel = new JLabel("");
        mistakeNumberLabel.setBorder(BorderFactory.createEmptyBorder(100, 0, 0, 0));
        mistakeNumberLabel.setFont(new Font("Verdana", Font.BOLD, 15));
        westBlankSpace.add(mistakeNumberLabel);

        JPanel eastBlankSpace = new JPanel();
        eastBlankSpace.setBackground(kremowy);
        eastBlankSpace.setPreferredSize(new Dimension(150, 200));
        p.add(eastBlankSpace, BorderLayout.EAST);

        p2.setLayout(new GridLayout(ROWS, COLUMNS));
        p2.setBackground(kremowy);
        p.add(p2, BorderLayout.CENTER);

        int index_list = 0;
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLUMNS; col++) {
                JPanel cell = new JPanel();
                cell.setBackground(COLORS[0]);
                int colorValue = LoadFile.getDataList().get(index_list);
                if (colorValue == 1) {
                    cell.setBackground(COLORS[1]);
                }
                index_list++;

                cell.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                final int r = row, c = col;

                if (row == 0 && col == 2) {
                    JLabel wartosc = new JLabel("1");
                    wartosc.setFont(czcionkaWKomorce);
                    wartosc.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
                    cell.add(wartosc, BorderLayout.CENTER);
                }

                if (row == 2 && col == 0 || row == 2 && col == 4 || row == 3 && col == 2) {
                    JLabel wartosc = new JLabel("2");
                    wartosc.setFont(czcionkaWKomorce);
                    wartosc.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
                    cell.add(wartosc, BorderLayout.CENTER);
                }

                cell.addMouseListener(new MouseAdapter() {
                    int index = 0;

                    final boolean warunek1 = (r == 0 && c == 0 || r == 0 && c == 1 || r == 0 && c == 3 ||
                            r == 0 && c == 4 || r == 1 && c == 1 || r == 1 && c == 2 || r == 1 && c == 3 ||
                            r == 2 && c == 1 || r == 2 && c == 3 || r == 3 && c == 0 || r == 3 && c == 1 ||
                            r == 3 && c == 3 || r == 3 && c == 4);
                    final boolean warunek2 = (r == 1 && c == 0 || r == 1 && c == 4 || r == 2 && c == 2);

                    @Override
                    public void mouseClicked(MouseEvent e) {
                        Component[] components = cell.getComponents();
                        boolean hasLabel = false;
                        for (Component component : components) {
                            if (component instanceof JLabel) {
                                hasLabel = true;
                                break;
                            }
                        }

                        if (hasLabel) {
                            cell.setEnabled(false);
                        } else {
                            if (cellColors[r][c] == COLORS[0]){
                                index = 1;
                            } else {
                                index = 0;
                            }
                            cellColors[r][c] = COLORS[index];
                            tablicaTestowa[r][c] = index;
                            Color startColor = cell.getBackground();
                            Color endColor = COLORS[index];
                            applyColorTransition(cell, startColor, endColor);
                            if (COLORS[index] != COLORS[0]) {

                                if (warunek1) {
                                    finalSum++;
                                }
                                if (warunek2) {
                                    finalSum--;
                                    mistakesNum++;
                                }
                            } else {

                                if (warunek1) {
                                    finalSum--;
                                }
                                if (warunek2) {
                                    finalSum++;
                                    mistakesNum--;
                                }
                            }
                        }

                        if (finalSum == 13) {
                            WinInformation winInformationGuiWindow = new WinInformation();
                            try {
                                winInformationGuiWindow.launchFrame();
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                    }
                });
                p2.add(cell);
            }
        }

        JPanel p3 = new JPanel();
        p3.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        p3.setBackground(kremowy);
        p.add(p3, BorderLayout.SOUTH);

        JButton check = new JButton("Sprawdź ilość błędów");
        p3.add(check);
        check.setBackground(new Color(59, 89, 182));
        check.setForeground(Color.WHITE);
        check.setPreferredSize(new Dimension(200, 35));
        check.setFont(new Font("Tahoma", Font.BOLD, 15));
        check.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                mistakeNumberLabel.setText("<html>Liczba <br/>błędów: <br/>" + mistakesNum + "</html>");
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                mistakeNumberLabel.setText("");
            }
        });

        JButton zmien = new JButton("Menu");
        p3.add(zmien);
        zmien.setBackground(new Color(59, 89, 182));
        zmien.setForeground(Color.WHITE);
        zmien.setPreferredSize(new Dimension(90, 35));
        zmien.setFont(new Font("Tahoma", Font.BOLD, 15));
        zmien.addActionListener(zmienClick -> {
            MainFrame mainFrameGuiWindow = new MainFrame();
            mainFrameGuiWindow.launchFrame();
            dispose();
        });

        JButton reset = new JButton("Reset");
        p3.add(reset);
        reset.setBackground(new Color(59, 89, 182));
        reset.setForeground(Color.WHITE);
        reset.setPreferredSize(new Dimension(100, 35));
        reset.setFont(new Font("Tahoma", Font.BOLD, 15));
        reset.addActionListener(resetClick -> {
            EasyLevel easyLevelFrameGuiWindow = new EasyLevel();
            easyLevelFrameGuiWindow.launchFrame();
            dispose();
        });

        JButton zapisz = new JButton("Zapisz jako obraz");
        p3.add(zapisz);
        zapisz.setBackground(new Color(59, 89, 182));
        zapisz.setForeground(Color.WHITE);
        zapisz.setPreferredSize(new Dimension(180, 35));
        zapisz.setFont(new Font("Tahoma", Font.BOLD, 15));
        zapisz.addActionListener(zapiszClick -> saveAsPhoto());

        JButton zapiszGre = new JButton("Zapisz grę");
        p3.add(zapiszGre);
        zapiszGre.setBackground(new Color(59, 89, 182));
        zapiszGre.setForeground(Color.WHITE);
        zapiszGre.setPreferredSize(new Dimension(125, 35));
        zapiszGre.setFont(new Font("Tahoma", Font.BOLD, 15));
        zapiszGre.addActionListener(zapiszGreClick -> saveToFile());

        JButton wyjdz = new JButton("Wyjdź");
        p3.add(wyjdz);
        wyjdz.setBackground(new Color(59, 89, 182));
        wyjdz.setForeground(Color.WHITE);
        wyjdz.setPreferredSize(new Dimension(100, 35));
        wyjdz.setFont(new Font("Tahoma", Font.BOLD, 15));
        wyjdz.addActionListener(wyjdzClick -> System.exit(1));

        add(p);
        pack();
        setBounds((Toolkit.getDefaultToolkit().getScreenSize().width >> 1) - (getSize().width >> 1),
                (Toolkit.getDefaultToolkit().getScreenSize().height >> 1) - (getSize().height >> 1), getSize().width, getSize().height);
        setBounds(400, 100, 900, 550);
        EventQueue.invokeLater(() -> setVisible(true));
        setResizable(false);
    }
}
