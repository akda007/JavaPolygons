

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;

import javax.swing.JComponent;

public class Star extends JComponent {
    double theta = 0;

    private Polygon getStar(Graphics2D g2d) {

        int sides = 30;
        int inner = 50, outer = 250;

        int[] x_points = new int[sides];
        int[] y_points = new int[sides];

        Point middle = new Point(300, 300);
        double xTheta = theta;
        double dTheta = (360 * Math.PI / 180 / sides);


        for (int i = 0; i < sides; i++) {
            int dist = i % 2 == 0 ? inner : outer;
            double vx = dist * Math.cos(xTheta), vy = dist * Math.sin(xTheta);

            xTheta += dTheta;

            x_points[i] = (int)(vx + middle.getX());
            y_points[i] = (int)(vy + middle.getY());
        }

        g2d.setColor(Color.black);
        g2d.setStroke(new BasicStroke(2));
        Polygon p = new Polygon(x_points, y_points, sides);

        return p;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D)g;
        g2d.setColor(Color.white);
        g2d.fillRect(0, 0, 600, 600);

        // g2d.drawPolygon(x_points, y_points, sides);
        g2d.drawPolygon(getStar(g2d));

        theta += 0.0010;
    }
}