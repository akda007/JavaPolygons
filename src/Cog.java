import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

import javax.swing.JComponent;

public class Cog extends JComponent {
    Point middle;

    public Cog(int w, int h) {
        super();
        middle = new Point(w/2, h/2);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D graphics = (Graphics2D)g;

        graphics.setStroke(new BasicStroke(2));

        double outer_radius = 200;
        double inner_radius = outer_radius/2;

        int teeth_count = 40;
        double teeth_len = 45;



        double angle_center = (2 * Math.PI / teeth_count);

        for (int i = 0; i< teeth_count; i++) {
            double ta1 = angle_center - angle_center / 2;
            double ta2 = angle_center + angle_center / 2;

            
        }

        graphics.drawOval(
            (int)(middle.getX() - outer_radius),
            (int)(middle.getY() - outer_radius),
            (int)(outer_radius*2), (int)(outer_radius*2)
        );

        graphics.drawOval(
            (int)(middle.getX() - inner_radius),
            (int)(middle.getY() - inner_radius),
            (int)(inner_radius*2), (int)(inner_radius*2)
        );
    }
}
