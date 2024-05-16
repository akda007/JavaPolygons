import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;

import javax.swing.JComponent;


public class StarPolygon extends JComponent {
    double thetaPenta = 0;
    double thetaStar = 0;
    double d1 = -175, d2 = 200;
    boolean growing = true, speeding = true;
    Point middle;

    double speed = 0.0005;


    public StarPolygon(int w, int h) {
        super();

        middle = new Point(w/2, h/2);
    }

    public void updateCenter(int w, int h) {
        middle = new Point(w/2, h/2);
    }

    private Polygon getPentagram(Graphics2D g) {

        int sides = 5;
        double x_theta = thetaPenta;
        double dTheta = (2 * Math.PI /sides); 

        int[] x = new int[sides];
        int[] y = new int[sides];

        for (int i = 0; i < sides; i++) {
            double vx = d2 * Math.cos(x_theta);
            double vy = d2 * Math.sin(x_theta);

            x_theta += dTheta;

            x[i] = (int)(vx + middle.getX());
            y[i] = (int)(vy + middle.getY());
        }

        return new Polygon(x, y, sides);
    }

    private Polygon getStar(Graphics2D g) {

        int sides = 10;
        double x_theta = thetaStar;
        double dTheta = (360 * Math.PI / 180 /sides); 

        int[] x = new int[sides];
        int[] y = new int[sides];

        for (int i = 0; i < sides; i++) {
            double distance = i % 2 == 0 ? d1 : d2-30;

            double vx = distance * Math.cos(x_theta);
            double vy = distance * Math.sin(x_theta);

            x_theta += dTheta;

            x[i] = (int)(vx + middle.getX());
            y[i] = (int)(vy + middle.getY());
        }


        return new Polygon(x, y, sides);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        g2d.setStroke(new BasicStroke(2));

        g2d.drawPolygon(getStar(g2d));
        g2d.drawPolygon(getPentagram(g2d));

        speed += speeding ? 0.0001 : -0.0001;

        if (speed >= 0.009) {
            speeding = false;
        }

        if (speed <- 0.00005) {
            speeding = true;
        }

        thetaStar += speed * (growing ? 1 : -1);
        thetaPenta += speed * (growing ? -1 : 1);

        // d1 += growing ? 0.1 : -0.1;
        // d2 += growing ? 0.1 : -0.1;

        if (d2 >= 270) {
            growing = false;
        }

        if (d2 <= -200) {
            growing = true;
        }
    }
}
