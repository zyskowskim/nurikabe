import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SaveAsPhoto {

    public void Save(Component component) {
        BufferedImage image = new BufferedImage(component.getWidth(), component.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g = image.createGraphics();
        component.paint(g);
        g.dispose();

        JFileChooser saveFile = new JFileChooser();
        String projectDirectory = System.getProperty("user.dir")+ "/screenshots";
        saveFile.setCurrentDirectory(new File(projectDirectory));
        int response = saveFile.showSaveDialog(null);
        if (response == JFileChooser.APPROVE_OPTION) {
            File file = new File(saveFile.getSelectedFile().getAbsolutePath());
            try {
                ImageIO.write(image, "png", new File(file + ".png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
