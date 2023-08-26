import javax.swing.JPanel;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class Transition {
    private static final int TRANSITION_DURATION = 16; // Czas trwania przejścia w milisekundach
    private static final int NUM_STEPS = 20; // Liczba kroków przejścia

    private final JPanel cell;
    private final Color startColor;
    private final Color endColor;
    private int currentStep;
    private final Timer timer;

    public Transition(JPanel cell, Color startColor, Color endColor) {
        this.cell = cell;
        this.startColor = startColor;
        this.endColor = endColor;
        this.currentStep = 0;
        this.timer = new Timer(TRANSITION_DURATION / NUM_STEPS, new TransitionListener());
    }

    public void startTransition() {
        timer.start();
    }

    private class TransitionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (currentStep >= NUM_STEPS) {
                timer.stop();
            } else {
                float ratio = (float) currentStep / NUM_STEPS;
                int red = (int) (startColor.getRed() * (1 - ratio) + endColor.getRed() * ratio);
                int green = (int) (startColor.getGreen() * (1 - ratio) + endColor.getGreen() * ratio);
                int blue = (int) (startColor.getBlue() * (1 - ratio) + endColor.getBlue() * ratio);
                int alpha = (int) (startColor.getAlpha() * (1 - ratio) + endColor.getAlpha() * ratio);
                Color currentColor = new Color(red, green, blue, alpha);
                cell.setBackground(currentColor);
                currentStep++;
            }
        }
    }
}