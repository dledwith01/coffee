import java.awt.BorderLayout;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class UI {
    private JPanel mainPanel;
    private JLabel logLabel;
    private JButton startButton;
    private JButton stopButton;
    private JButton exitButton;
    private Image scaledIcon;

    public UI() {
        setupUI();
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public JLabel getLogLabel() {
        return logLabel;
    }

    public JButton getStartButton() {
        return startButton;
    }

    public JButton getStopButton() {
        return stopButton;
    }
    
    public JButton getExitButton() {
    	return exitButton;
    }

    public Image getScaledIcon() {
        return scaledIcon;
    }

    void setupUI() {
        logLabel = new JLabel("press 'start' to drink coffee", JLabel.CENTER);

        startButton = new JButton("start");
        stopButton = new JButton("stop");
        exitButton = new JButton("exit");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(startButton);
        buttonPanel.add(stopButton);
        buttonPanel.add(exitButton);

        mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        mainPanel.add(logLabel, BorderLayout.CENTER);

        setupIcon();
    }

    private void setupIcon() {
        ImageIcon originalIcon = new ImageIcon("C:/Users/hepat/eclipse-workspace/Coffee/res/coffee.png");
        Image originalImage = originalIcon.getImage();
        scaledIcon = originalImage.getScaledInstance(originalImage.getWidth(null), originalImage.getHeight(null), Image.SCALE_SMOOTH);
    }
}
