package heatequations.ode;

import complexsystems.flock.components.Flock;
import complexsystems.flock.components.Bird;
import heatequations.ode.Point;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Screen extends JPanel {

    private final List<Point> rodPoints;

    public Screen(int width, int height, List<heatequations.ode.Point> rodPoints) {
        this.rodPoints = rodPoints;
        JFrame frame = new JFrame("Flock Simulation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        this.setPreferredSize(new Dimension(width, height));
        frame.getContentPane().add(this);

        frame.setResizable(true);
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        draw(g2d);
    }

    private void draw(Graphics2D g2d) {
        g2d.setColor(Color.black);
        g2d.fillRect(0, 0, 2000, 1000);

        Point[] points = rodPoints.toArray(new Point[rodPoints.size()]);
        for (Point point : points) {
            g2d.setColor(Color.WHITE);
            g2d.fillOval(pixelX(point.getX()) , pixelY(point.getTx()) , 3, 3);
        }
    }

    private int pixelX(double x) {
        double screenX = x + (this.getWidth() / 2);
        return (int) Math.round(screenX);
    }

    private int pixelY(double y) {
        double screenY = (this.getHeight() / 2) - y;
        return (int) Math.round(screenY);
    }
}
