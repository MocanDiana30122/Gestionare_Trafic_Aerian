import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        ATC atc = new ATC();
        SwingUtilities.invokeLater(() -> {
            ATCFrame frame = new ATCFrame(atc);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
