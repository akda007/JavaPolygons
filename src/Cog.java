import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JComponent;

public class Cog extends JComponent {
    Point middle;
    double theta = 0;

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



        double dTheta = (2 * Math.PI / teeth_count);
        double angle_center = theta;

        ArrayList<CogTeeth> list = new ArrayList<>();

        for (int i = 0; i< teeth_count; i++) {
            double ta1 = angle_center - angle_center / 2;
            double ta2 = angle_center + angle_center / 2;

            double vx1 = outer_radius * Math.cos(ta1);      
            double vy1 = outer_radius * Math.sin(ta1);
            double vx2 = outer_radius * Math.cos(ta2);      
            double vy2 = outer_radius * Math.sin(ta2);
            
            CogTeeth t = new CogTeeth();

            t.x[0] = (int)(middle.getX() + vx1);
            t.y[0] = (int)(middle.getY() + vy1);

            t.x[1] = t.x[0] + (int)(teeth_len * Math.cos(Math.PI / 2));
            t.y[1] = t.y[0] + (int)(teeth_len * Math.sin(Math.PI / 2));

            t.x[3] = (int)(middle.getX() + vx2);
            t.y[3] = (int)(middle.getY() + vy2);

            t.x[2] = t.x[3] + (int)(teeth_len * -Math.cos(Math.PI / 2));
            t.y[2] = t.y[3] + (int)(teeth_len * -Math.sin(Math.PI / 2));

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
            graphics.drawPolyline(t.x, t.y, 4);
        }

        theta += 0.0005;
        
    }
}
