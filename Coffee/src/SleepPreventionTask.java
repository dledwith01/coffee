import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import java.awt.event.KeyEvent;
import java.awt.AWTException;
import java.awt.Robot;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;


public class SleepPreventionTask {
    private boolean running = false;
    private Thread thread;
    private JLabel logLabel;
    private int minInterval = 5; // seconds
    private int maxInterval = 300; // seconds

    public SleepPreventionTask(JLabel logLabel) {
        this.logLabel = logLabel;
    }

    public void start() {
        if (running) return;

        running = true;
        thread = new Thread(() -> {
            try {
                Robot robot = new Robot();
                Random random = new Random();
                SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");

                while (running) {
                    robot.keyPress(KeyEvent.VK_SHIFT);
                    robot.keyRelease(KeyEvent.VK_SHIFT);

                    String timestamp = formatter.format(new Date());
                    SwingUtilities.invokeLater(() -> logLabel.setText("took a sip at " + timestamp));

                    int sleepTime = (minInterval * 1000) + random.nextInt((maxInterval - minInterval) * 1000);
                    Thread.sleep(sleepTime);
                }
            } catch (AWTException | InterruptedException e) {
                SwingUtilities.invokeLater(() -> logLabel.setText("press 'start' to drink coffee"));
            }
        });
        thread.start();
    }

    public void stop() {
        running = false;
        if (thread != null) {
            thread.interrupt();
        }
    }
}
