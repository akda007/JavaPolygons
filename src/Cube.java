import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.geom.Line2D;

import javax.swing.JComponent;

public class Cube extends JComponent {

    double y_angle = 28;
    double x_angle = 100;
    int scale = 100;

    private Point getPoint(double[] vertex, int width, int height) {
        int x = (int)(width/2 + vertex[0] * scale);
        int y = (int)(height/2 - vertex[1] * scale);

        return new Point(x, y);
    }

    private double[] rotate(double[] vertex) {
        double sin_x = Math.sin(x_angle);
        double cos_x = Math.cos(x_angle);
        double sin_y = Math.sin(y_angle);
        double cos_y = Math.cos(y_angle);

        //rotate x
        double x = vertex[0];
        double y = vertex[1] * cos_x - vertex[2] * sin_x;
        double z = vertex[1] * sin_x + vertex[2] * cos_x;

        //rotate y
        double nx = x * cos_y + z * sin_y;
        double nz = -x * sin_y + z * cos_y;


        return new double[] {nx, y, nz};
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D graphics = (Graphics2D)g;
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();


        double[][] vertices = {
            {-1, -1, -1}, {1, -1, -1}, {1, 1, -1}, {-1, 1, -1},
            {-1, -1, 1}, {1, -1, 1}, {1, 1, 1}, {-1, 1, 1}
        };

        double[][] rotated = new double[8][3];

        for (int i = 0; i < 8; i++) {
            rotated[i] = rotate(vertices[i]);
        }

        Point[] points = new Point[8];

        for (int i = 0; i < 8; i++) {
            points[i] = getPoint(rotated[i], width, height);

            graphics.drawOval(points[i].x - 10, points[i].y - 10, 20, 20);
        }

        // int[][] edges = {
        //     {0, 1}, {1, 2}, {2, 3}, {3, 0},
        //     {4, 5}, {5, 6}, {6, 7}, {7, 4},
        //     {0, 4}, {1, 5}, {2, 6}, {3, 7},
        //     {0, 7}, {}
        // };

        int[][] edges = {
                { 0, 0 }, { 0, 1 }, { 0, 2 }, { 0, 3 }, { 0, 4 }, { 0, 5 },
                { 0, 6 }, { 0, 7 }, { 1, 1 }, { 1, 2 }, { 1, 3 }, { 1, 4 },
                { 1, 5 }, { 1, 6 }, { 1, 7 }, { 2, 2 }, { 2, 3 }, { 2, 4 },
                { 2, 5 }, { 2, 6 }, { 2, 7 }, { 3, 3 }, { 3, 4 }, { 3, 5 },
                { 3, 6 }, { 3, 7 }, { 4, 4 }, { 4, 5 }, { 4, 6 }, { 4, 7 },
                { 5, 6 }, { 5, 7 }, { 6, 6 }, { 5, 5 }, { 6, 7 }
            };

        for (int[] edge: edges) {
            graphics.draw(new Line2D.Double(
                    points[edge[0]].x,
                    points[edge[0]].y,
                    points[edge[1]].x,
                    points[edge[1]].y
                ));
        }
    }


    public void draw() {
        x_angle += 0.0000005;
        y_angle += 0.0000005;
        repaint();
    }
}
