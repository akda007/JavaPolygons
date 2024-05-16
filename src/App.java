import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(600, 600);

            frame.setLayout(new BorderLayout());

            // Star star = new Star();
            // Hexagon hex = new Hexagon();
            // StarPolygon sp = new StarPolygon(600, 600);
            // Cog cog = new Cog(600, 600);
            // CogMKII cog = new CogMKII(600, 600);
            Heart heart = new Heart();
            // Coisa cog = new Coisa(600, 600);
            // frame.add(hex, BorderLayout.CENTER);
            // frame.add(star, BorderLayout.CENTER);
            // frame.add(sp, BorderLayout.CENTER);
            frame.add(heart, BorderLayout.CENTER);
            // frame.add(cog, BorderLayout.CENTER);

            frame.setVisible(true);

            ExecutorService service = Executors.newSingleThreadExecutor();

            service.execute(() -> {
                try {
                    while (true) {
                        Thread.sleep(1);
                        // hex.repaint();
                        // star.repaint();
                        // sp.updateCenter(frame.getWidth(), frame.getHeight());
                        // sp.repaint();
                        // cog.updateCenter(frame.getWidth(), frame.getHeight());
                        // cog.repaint();
                        heart.repaint();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            // Shutdown the ExecutorService when the frame is closed
            frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent windowEvent) {
                    service.shutdownNow();
                }
            });
        });
    }
}



