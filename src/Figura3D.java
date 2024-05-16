import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JComponent;

public class Figura3D extends JComponent {
    Point middle;
    double theta = 0;

    public Figura3D(int w, int h) {
        super();
        middle = new Point(w/2, h/2);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D graphics = (Graphics2D)g;

        graphics.setStroke(new BasicStroke(2));

        double outer_radius = 200;
        double inner_radius = outer_radius/2;

        int teeth_count = 10;
        double teeth_len = 30;
        double teet_width = 30;



        double dTheta = (2 * Math.PI / teeth_count);
        double angle_center = theta;

        ArrayList<CogTeeth> list = new ArrayList<>();

        for (int i = 0; i< teeth_count; i++) {
            double a1 = angle_center - (teet_width / 2 * Math.PI / 180);
            double a2 = angle_center + (teet_width / 2 * Math.PI / 180);

            double vx1 = outer_radius * Math.cos(a1);      
            double vy1 = outer_radius * Math.sin(a1);
            double vx2 = outer_radius * Math.cos(a2);      
            double vy2 = outer_radius * Math.sin(a2);

            double x1a = angle_center - a1;
            double x2a = a2 - angle_center;
            
            CogTeeth t = new CogTeeth();

            t.x[0] = (int)(middle.getX() + vx1);
            t.y[0] = (int)(middle.getY() + vy1);

            t.x[1] = t.x[0] + (int)(teeth_len * Math.cos(x1a));
            t.y[1] = t.y[0] + (int)(teeth_len * Math.sin(x1a));

            t.x[3] = (int)(middle.getX() + vx2);
            t.y[3] = (int)(middle.getY() + vy2);

            t.x[2] = t.x[3] + (int)(teeth_len * Math.cos(x2a));
            t.y[2] = t.y[3] + (int)(teeth_len * Math.sin(x2a));

            angle_center +=  dTheta;
            
            list.add(t);
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

        for (CogTeeth t : list) {
            graphics.drawOval(t.x[0], t.y[0], 4, 4);
            graphics.drawOval(t.x[3], t.y[3], 4, 4);
            graphics.drawPolyline(t.x, t.y, 4);
        }

        theta += 0.0005;
        
    }
}
