
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.font.GraphicAttribute;

import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicIconFactory;

public class Hexagon extends JComponent {
    double theta = 0;


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        int sides = 6;

        int[] x_points = new int[sides];
        int[] y_points = new int[sides];

        Point middle = new Point(300, 300);
        double xTheta = theta;
        double dTheta = (360 * Math.PI / 180 / sides);
        int dist = 170;


        for (int i = 0; i < sides; i++) {
            double vx = dist * Math.cos(xTheta), vy = dist * Math.sin(xTheta);

            xTheta += dTheta;

            x_points[i] = (int)(vx + middle.getX());
            y_points[i] = (int)(vy + middle.getY());
        }

        g2d.setStroke(new BasicStroke(2));
        g2d.drawPolygon(x_points, y_points, sides);

        theta -= 0.0010;
    }
}


