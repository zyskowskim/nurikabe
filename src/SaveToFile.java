import javax.swing.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SaveToFile {

    private final int levelId;
    private final int positiveCounter;
    private final int mistakeCounter;
    public int[][] tablicaTestowa;

    public SaveToFile(int levelId,int positiveCounter,int mistakeCounter, int[][] tablicaTestowa) {
        this.levelId = levelId;
        this.positiveCounter = positiveCounter;
        this.mistakeCounter = mistakeCounter;
        this.tablicaTestowa = tablicaTestowa;
    }

    public void Save() {
        JFileChooser saveFile = new JFileChooser();
        String projectDirectory = System.getProperty("user.dir") + "/saves";
        saveFile.setCurrentDirectory(new File(projectDirectory));
        int response = saveFile.showSaveDialog(null);
        if (response == JFileChooser.APPROVE_OPTION) {
            File file = new File(saveFile.getSelectedFile().getAbsolutePath() + ".txt");
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write(String.valueOf(levelId));
                writer.newLine();
                writer.write(String.valueOf(positiveCounter));
                writer.newLine();
                writer.write(String.valueOf(mistakeCounter));
                writer.newLine();
                for (int[] ints : tablicaTestowa) {
                    for (int anInt : ints) {
                        writer.write(anInt + " ");
                    }
                    writer.newLine();
                }
            } catch (IOException e) {
                System.out.println("Wystąpił błąd podczas zapisu do pliku: " + e.getMessage());
            }
        }
    }
}
