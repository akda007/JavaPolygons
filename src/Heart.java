import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;

import javax.swing.JComponent;

public class Heart extends JComponent {

    @Override
    protected void paintComponent(Graphics g) {

        Graphics2D graphics = (Graphics2D)g;

        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        DoublePoint center = new DoublePoint(getWidth()/2, getHeight()/2);


        double diameter = 200;
        double height =  150;
        double radius = diameter/4;
        double top_h = 30;

        DoublePoint p1 = new DoublePoint(center.getX() - diameter/2, center.getY());
        DoublePoint p2 = new DoublePoint(center.getX() + diameter/2, center.getY());

        DoublePoint c1 = new DoublePoint(center.getX() - radius, center.getY());
        DoublePoint c2 = new DoublePoint(center.getX() + radius, center.getY());

        DoublePoint bottom = new DoublePoint(center.getX(), center.getY()+height);

        graphics.setColor(Color.black);
        graphics.setStroke(new BasicStroke(2));

        g.drawLine(p1.getXInt(), p1.getYInt(), bottom.getXInt(), bottom.getYInt());
        g.drawLine(p2.getXInt(), p2.getYInt(), bottom.getXInt(), bottom.getYInt());


        g.drawArc(p1.getXInt(), p1.getYInt() - (int)radius, (int)radius*2, (int)radius*2, 180, -180);
        g.drawArc(center.getXInt(), center.getYInt() - (int)radius, (int)radius*2, (int)radius*2, 180, -180);
    }
}
