import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;

public class TrayIconManager {
    private TrayIcon trayIcon;
    private Image trayImage;

    public TrayIconManager(Image trayImage) {
        this.trayImage = trayImage;
    }

    public void setupSystemTray(final Main mainApp) {
        if (!SystemTray.isSupported()) {
            System.out.println("system tray is not supported");
            return;
        }

        PopupMenu popup = new PopupMenu();

        MenuItem openItem = new MenuItem("open");
        openItem.addActionListener(e -> mainApp.setVisible(true));
        popup.add(openItem);

        MenuItem startItem = new MenuItem("start");
        startItem.addActionListener(e -> mainApp.sleepPreventionTask.start());
        popup.add(startItem);

        MenuItem stopItem = new MenuItem("stop");
        stopItem.addActionListener(e -> mainApp.sleepPreventionTask.stop());
        popup.add(stopItem);

        MenuItem exitItem = new MenuItem("exit");
        exitItem.addActionListener(e -> {
            mainApp.sleepPreventionTask.stop();
            System.exit(0);
        });
        popup.add(exitItem);

        trayIcon = new TrayIcon(trayImage, "coffee", popup);
        trayIcon.setImageAutoSize(true);

        try {
            SystemTray.getSystemTray().add(trayIcon);
        } catch (AWTException e) {
            System.out.println("error creating tray icon");
        }
    }
}
