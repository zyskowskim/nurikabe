import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LoadFile {
    private static List<Integer> dataList;
    private static int gameID;
    private static int mistakesNumber;
    private static int positiveNumber;

    public static void loadFile() throws FileNotFoundException {
        JFileChooser fileChooser = new JFileChooser();
        String projectDirectory = System.getProperty("user.dir") + "/saves";
        fileChooser.setCurrentDirectory(new File(projectDirectory));
        int response = fileChooser.showOpenDialog(null);

        dataList = new ArrayList<>();
        gameID = 0;

        if(response == JFileChooser.APPROVE_OPTION){
            File selectedFile = fileChooser.getSelectedFile();

            try (BufferedReader reader = new BufferedReader(new FileReader(selectedFile))){
                String line;
                if ((line = reader.readLine()) != null) {
                    gameID = Integer.parseInt(line);
                    line = reader.readLine();
                    positiveNumber = Integer.parseInt(line);
                    line = reader.readLine();
                    mistakesNumber = Integer.parseInt(line);
                }

                while ((line = reader.readLine()) != null){
                    String[] values = line.split(" ");

                    for (int i = 0; i < values.length; i++){
                        int value = Integer.parseInt(values[i]);
                        dataList.add(value);
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println("gameID: " + gameID);
            System.out.println(dataList);
        }
    }

    public static List<Integer> getDataList() {
        return dataList;
    }

    public static int getGameID() {
        return gameID;
    }
    public static int getPositiveNumber() {
        return positiveNumber;
    }
    public static int getMistakesNumber() {
        return mistakesNumber;
    }
}
