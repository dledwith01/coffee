import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main extends JFrame {
    private static final long serialVersionUID = 1L;
    SleepPreventionTask sleepPreventionTask;
    private TrayIconManager trayIconManager;

    public Main() {
        setTitle("coffee");
        setSize(300, 100);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(false);
        
        UI ui = new UI();
        ui.setupUI();
        add(ui.getMainPanel());

        trayIconManager = new TrayIconManager(ui.getScaledIcon());
        trayIconManager.setupSystemTray(this);

        sleepPreventionTask = new SleepPreventionTask(ui.getLogLabel());

        // Make buttons interact with SleepPreventionTask
        ui.getStartButton().addActionListener(e -> sleepPreventionTask.start());
        ui.getStopButton().addActionListener(e -> sleepPreventionTask.stop());
        ui.getExitButton().addActionListener(e -> {
            sleepPreventionTask.stop();
            System.exit(0);
        });
        
        setIconImage(ui.getScaledIcon());

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                setVisible(false);
            }
        });

        sleepPreventionTask.start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::new);
    }
}
