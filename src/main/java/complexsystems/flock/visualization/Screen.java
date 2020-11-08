package complexsystems.flock.visualization;

import complexsystems.flock.components.Flock;
import complexsystems.flock.components.Bird;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Screen extends JPanel {

    private final List<Flock> flockList;

    public Screen(int width, int height, List<Flock> flocks) {
        this.flockList = flocks;
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
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        g2d.setColor(Color.black);
        g2d.fillRect(0, 0, screenSize.width, screenSize.height);

        Flock[] flocks = flockList.toArray(new Flock[flockList.size()]);
        for (Flock flock : flocks) {

            g2d.setColor(flock.color());

            Bird[] birds = flock.get();
            for (Bird b : birds) {
                g2d.fillOval(pixelX(b.position.x) - 3, pixelY(b.position.y) - 3, 2, 2);
            }
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
