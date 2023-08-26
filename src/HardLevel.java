import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class HardLevel extends Frame {

    public static int levelId = 3;
    private static final int ROWS = 10;
    private static final int COLUMNS = 10;
    private static final Color[] COLORS = {Color.WHITE, Color.DARK_GRAY};
    private static final Color[][] cellColors = new Color[ROWS][COLUMNS];
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

    public HardLevel() {
        super("Nurikabe - Poziom Trudny");
    }

    public void launchFrame() {
        finalSum = 0;
        mistakesNum = 0;
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLUMNS; col++) {
                cellColors[row][col] = COLORS[0];
            }
        }

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(1);
            }
        });

        Font czcionkaWKomorce = new Font("Verdana", Font.BOLD, 30);
        Color kremowy = new Color(222, 229, 184);

        Panel p = new Panel(new BorderLayout());
        p.setBackground(kremowy);

        Panel p1 = new Panel();
        p.add(p1, BorderLayout.NORTH);

        JLabel sredniNapis = new JLabel("Poziom Trudny\n");
        sredniNapis.setFont(new Font("Verdana", Font.BOLD, 40));
        sredniNapis.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(10, 10, 10, 10),
                BorderFactory.createLineBorder(kremowy)));
        p1.add(sredniNapis);

        JPanel westBlankSpace = new JPanel();
        westBlankSpace.setBackground(kremowy);
        westBlankSpace.setPreferredSize(new Dimension(100,200));
        p.add(westBlankSpace, BorderLayout.WEST);

        mistakeNumberLabel = new JLabel("");
        mistakeNumberLabel.setBorder(BorderFactory.createEmptyBorder(300,0,0,0));
        mistakeNumberLabel.setFont(new Font("Verdana", Font.BOLD, 15));
        westBlankSpace.add(mistakeNumberLabel);

        JPanel eastBlankSpace = new JPanel();
        eastBlankSpace.setBackground(kremowy);
        eastBlankSpace.setPreferredSize(new Dimension(100,200));
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

                if (row == 8 && col == 0 || row == 8 && col == 7) {
                    JLabel wartosc = new JLabel("1");
                    wartosc.setFont(czcionkaWKomorce);
                    wartosc.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
                    cell.add(wartosc, BorderLayout.CENTER);
                }
                if (row == 1 && col == 2 || row == 1 && col == 7 || row == 2 && col == 4 ||
                        row == 3 && col == 3 || row == 6 && col == 0 || row == 8 && col == 2) {
                    JLabel wartosc = new JLabel("2");
                    wartosc.setFont(czcionkaWKomorce);
                    wartosc.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
                    cell.add(wartosc, BorderLayout.CENTER);
                }
                if (row == 1 && col == 9 || row == 2 && col == 0 || row == 5 && col == 2 ||
                        row == 7 && col == 5) {
                    JLabel wartosc = new JLabel("3");
                    wartosc.setFont(czcionkaWKomorce);
                    wartosc.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
                    cell.add(wartosc, BorderLayout.CENTER);
                }
                if (row == 3 && col == 9 || row == 4 && col == 7 || row == 6 && col == 6 ||
                        row == 7 && col == 9) {
                    JLabel wartosc = new JLabel("4");
                    wartosc.setFont(czcionkaWKomorce);
                    wartosc.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
                    cell.add(wartosc, BorderLayout.CENTER);
                }

                cell.addMouseListener(new MouseAdapter() {
                    private int index = 0;

                    final boolean warunek1 = (r == 0 && c == 0 || r == 0 && c == 1 || r == 0 && c == 3 ||
                            r == 0 && c == 4 || r == 0 && c == 5 || r == 0 && c == 6 || r == 0 && c == 7 ||
                            r == 1 && c == 1 || r == 1 && c == 3 || r == 1 && c == 5 || r == 1 && c == 8 ||
                            r == 2 && c == 1 || r == 2 && c == 2 || r == 2 && c == 3 || r == 2 && c == 5 ||
                            r == 2 && c == 6 || r == 2 && c == 7 || r == 2 && c == 8 || r == 2 && c == 9 ||
                            r == 3 && c == 1 || r == 3 && c == 4 || r == 3 && c == 8 || r == 4 && c == 0 ||
                            r == 4 && c == 1 || r == 4 && c == 2 || r == 4 && c == 3 || r == 4 && c == 4 ||
                            r == 4 && c == 5 || r == 4 && c == 6 || r == 4 && c == 8 || r == 5 && c == 1 ||
                            r == 5 && c == 3 || r == 5 && c == 7 || r == 6 && c == 1 || r == 6 && c == 4 ||
                            r == 6 && c == 5 || r == 6 && c == 7 || r == 6 && c == 8 || r == 6 && c == 9 ||
                            r == 7 && c == 0 || r == 7 && c == 1 || r == 7 && c == 2 || r == 7 && c == 3 ||
                            r == 7 && c == 4 || r == 7 && c == 6 || r == 7 && c == 7 || r == 8 && c == 1 ||
                            r == 8 && c == 3 || r == 8 && c == 6 || r == 8 && c == 8 || r == 9 && c == 0 ||
                            r == 9 && c == 1 || r == 9 && c == 3 || r == 9 && c == 4 || r == 9 && c == 5 ||
                            r == 9 && c == 6 || r == 9 && c == 7 || r == 9 && c == 8);
                    final boolean warunek2 = (r == 0 && c == 2 || r == 0 && c == 8 || r == 0 && c == 9 ||
                            r == 1 && c == 0 || r == 1 && c == 4 || r == 1 && c == 6 || r == 3 && c == 0 ||
                            r == 3 && c == 2 || r == 3 && c == 5 || r == 3 && c == 6 || r == 3 && c == 7 ||
                            r == 4 && c == 9 || r == 5 && c == 0 || r == 5 && c == 4 || r == 5 && c == 5 ||
                            r == 5 && c == 6 || r == 5 && c == 8 || r == 5 && c == 9 || r == 6 && c == 2 ||
                            r == 6 && c == 3 || r == 7 && c == 8 || r == 8 && c == 4 || r == 8 && c == 5 ||
                            r == 8 && c == 9 || r == 9 && c == 2 || r == 9 && c == 9);

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
                            index = (index + 1) % COLORS.length;
                            cellColors[r][c] = COLORS[index];
                            tablicaTestowa[r][c] = index % COLORS.length;
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

                        if (finalSum == 58) {
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

        Panel p3 = new Panel();
        p.add(p3, BorderLayout.SOUTH);

        JButton check = new JButton("Sprawdź ilość błędów");
        p3.add(check);
        check.setBackground(new Color(59, 89, 182));
        check.setForeground(Color.WHITE);
        check.setPreferredSize(new Dimension(200,35));
        check.setFont(new Font("Tahoma", Font.BOLD, 15));
        check.addActionListener(resetClick -> mistakeNumberLabel.setText("<html>Liczba <br/>błędów: <br/>" + mistakesNum + "</html>"));

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
        reset.setPreferredSize(new Dimension(90, 35));
        reset.setFont(new Font("Tahoma", Font.BOLD, 15));
        reset.addActionListener(resetClick -> {
            HardLevel hardLevelFrameGuiWindow = new HardLevel();
            hardLevelFrameGuiWindow.launchFrame();
            dispose();
        });

        JButton zapisz = new JButton("Zapisz jako obraz");
        p3.add(zapisz);
        zapisz.setBackground(new Color(59, 89, 182));
        zapisz.setForeground(Color.WHITE);
        zapisz.setPreferredSize(new Dimension(175, 35));
        zapisz.setFont(new Font("Tahoma", Font.BOLD, 15));
        zapisz.addActionListener(zapiszClick -> saveAsPhoto());

        JButton zapiszGre = new JButton("Zapisz grę");
        p3.add(zapiszGre);
        zapiszGre.setBackground(new Color(59, 89, 182));
        zapiszGre.setForeground(Color.WHITE);
        zapiszGre.setPreferredSize(new Dimension(130, 35));
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
        setBounds(370, 0, 900, 820);
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

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(1);
            }
        });

        Font czcionkaWKomorce = new Font("Verdana", Font.BOLD, 30);
        Color kremowy = new Color(222, 229, 184);

        Panel p = new Panel(new BorderLayout());
        p.setBackground(kremowy);

        Panel p1 = new Panel();
        p.add(p1, BorderLayout.NORTH);

        JLabel sredniNapis = new JLabel("Poziom Trudny\n");
        sredniNapis.setFont(new Font("Verdana", Font.BOLD, 40));
        sredniNapis.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(10, 10, 10, 10),
                BorderFactory.createLineBorder(kremowy)));
        p1.add(sredniNapis);

        JPanel westBlankSpace = new JPanel();
        westBlankSpace.setBackground(kremowy);
        westBlankSpace.setPreferredSize(new Dimension(100, 200));
        p.add(westBlankSpace, BorderLayout.WEST);

        mistakeNumberLabel = new JLabel("");
        mistakeNumberLabel.setBorder(BorderFactory.createEmptyBorder(300, 0, 0, 0));
        mistakeNumberLabel.setFont(new Font("Verdana", Font.BOLD, 15));
        westBlankSpace.add(mistakeNumberLabel);

        JPanel eastBlankSpace = new JPanel();
        eastBlankSpace.setBackground(kremowy);
        eastBlankSpace.setPreferredSize(new Dimension(100, 200));
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

                if (row == 8 && col == 0 || row == 8 && col == 7) {
                    JLabel wartosc = new JLabel("1");
                    wartosc.setFont(czcionkaWKomorce);
                    wartosc.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
                    cell.add(wartosc, BorderLayout.CENTER);
                }
                if (row == 1 && col == 2 || row == 1 && col == 7 || row == 2 && col == 4 ||
                        row == 3 && col == 3 || row == 6 && col == 0 || row == 8 && col == 2) {
                    JLabel wartosc = new JLabel("2");
                    wartosc.setFont(czcionkaWKomorce);
                    wartosc.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
                    cell.add(wartosc, BorderLayout.CENTER);
                }
                if (row == 1 && col == 9 || row == 2 && col == 0 || row == 5 && col == 2 ||
                        row == 7 && col == 5) {
                    JLabel wartosc = new JLabel("3");
                    wartosc.setFont(czcionkaWKomorce);
                    wartosc.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
                    cell.add(wartosc, BorderLayout.CENTER);
                }
                if (row == 3 && col == 9 || row == 4 && col == 7 || row == 6 && col == 6 ||
                        row == 7 && col == 9) {
                    JLabel wartosc = new JLabel("4");
                    wartosc.setFont(czcionkaWKomorce);
                    wartosc.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
                    cell.add(wartosc, BorderLayout.CENTER);
                }

                cell.addMouseListener(new MouseAdapter() {
                    int index = 0;

                    final boolean warunek1 = (r == 0 && c == 0 || r == 0 && c == 1 || r == 0 && c == 3 ||
                            r == 0 && c == 4 || r == 0 && c == 5 || r == 0 && c == 6 || r == 0 && c == 7 ||
                            r == 1 && c == 1 || r == 1 && c == 3 || r == 1 && c == 5 || r == 1 && c == 8 ||
                            r == 2 && c == 1 || r == 2 && c == 2 || r == 2 && c == 3 || r == 2 && c == 5 ||
                            r == 2 && c == 6 || r == 2 && c == 7 || r == 2 && c == 8 || r == 2 && c == 9 ||
                            r == 3 && c == 1 || r == 3 && c == 4 || r == 3 && c == 8 || r == 4 && c == 0 ||
                            r == 4 && c == 1 || r == 4 && c == 2 || r == 4 && c == 3 || r == 4 && c == 4 ||
                            r == 4 && c == 5 || r == 4 && c == 6 || r == 4 && c == 8 || r == 5 && c == 1 ||
                            r == 5 && c == 3 || r == 5 && c == 7 || r == 6 && c == 1 || r == 6 && c == 4 ||
                            r == 6 && c == 5 || r == 6 && c == 7 || r == 6 && c == 8 || r == 6 && c == 9 ||
                            r == 7 && c == 0 || r == 7 && c == 1 || r == 7 && c == 2 || r == 7 && c == 3 ||
                            r == 7 && c == 4 || r == 7 && c == 6 || r == 7 && c == 7 || r == 8 && c == 1 ||
                            r == 8 && c == 3 || r == 8 && c == 6 || r == 8 && c == 8 || r == 9 && c == 0 ||
                            r == 9 && c == 1 || r == 9 && c == 3 || r == 9 && c == 4 || r == 9 && c == 5 ||
                            r == 9 && c == 6 || r == 9 && c == 7 || r == 9 && c == 8);
                    final boolean warunek2 = (r == 0 && c == 2 || r == 0 && c == 8 || r == 0 && c == 9 ||
                            r == 1 && c == 0 || r == 1 && c == 4 || r == 1 && c == 6 || r == 3 && c == 0 ||
                            r == 3 && c == 2 || r == 3 && c == 5 || r == 3 && c == 6 || r == 3 && c == 7 ||
                            r == 4 && c == 9 || r == 5 && c == 0 || r == 5 && c == 4 || r == 5 && c == 5 ||
                            r == 5 && c == 6 || r == 5 && c == 8 || r == 5 && c == 9 || r == 6 && c == 2 ||
                            r == 6 && c == 3 || r == 7 && c == 8 || r == 8 && c == 4 || r == 8 && c == 5 ||
                            r == 8 && c == 9 || r == 9 && c == 2 || r == 9 && c == 9);

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
                            index = (index + 1) % COLORS.length;
                            cellColors[r][c] = COLORS[index];
                            tablicaTestowa[r][c] = index % COLORS.length;
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

                        if (finalSum == 58) {
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

        Panel p3 = new Panel();
        p.add(p3, BorderLayout.SOUTH);

        JButton check = new JButton("Sprawdź ilość błędów");
        p3.add(check);
        check.setBackground(new Color(59, 89, 182));
        check.setForeground(Color.WHITE);
        check.setPreferredSize(new Dimension(200, 35));
        check.setFont(new Font("Tahoma", Font.BOLD, 15));
        check.addActionListener(resetClick -> mistakeNumberLabel.setText("<html>Liczba <br/>błędów: <br/>" + mistakesNum + "</html>"));

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
            HardLevel hardLevelFrameGuiWindow = new HardLevel();
            hardLevelFrameGuiWindow.launchFrame();
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
        setBounds(370, 0, 900, 820);
        EventQueue.invokeLater(() -> setVisible(true));
        setResizable(false);
    }
}
