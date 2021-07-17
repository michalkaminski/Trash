package odes.components.visualizations;

import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.*;

import static javax.swing.JFrame.EXIT_ON_CLOSE;

@Setter
public class ArrayVis2D extends JPanel {

    private double[][] space2d;
    private double min = 0;
    private double max = 0;

    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    public ArrayVis2D(int width, int height) throws ExecutionException, InterruptedException {

        JFrame frame = new JFrame("array2d Simulation");
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        this.setPreferredSize(new Dimension(width, height));
        frame.getContentPane().add(this);

        frame.setResizable(true);
        frame.pack();
        frame.setVisible(true);

        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setAlwaysOnTop(true);
        frame.setResizable(true);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

    }

    public void visualize(int size, IterableArrayCalculation iterableArrayCalculation) throws ExecutionException, InterruptedException {


        ExecutorService executor = Executors.newWorkStealingPool(5);
        Future<double[][]> future;

        Callable<double[][]> task = () -> {
            iterableArrayCalculation.iterate();
            return iterableArrayCalculation.getU2d();
        };

        int i = 0;
        while (true) {
            future = executor.submit(task);
            setSpace2d(future.get());
            setMin(iterableArrayCalculation.getMin());
            setMax(iterableArrayCalculation.getMax());
            repaint();
            System.out.println(i++);
        }
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        draw(g2d);
    }

    private void draw(Graphics2D g2d) {

        g2d.setColor(Color.black);
        g2d.fillRect(0, 0, screenSize.width, screenSize.height);

        if (space2d == null) return;
        for (int x = 0; x <= space2d.length - 1; x++) {
            for (int y = 0; y <= space2d.length - 1; y++) {
                double colVal = space2d[x][y];
                if (colVal < 0) {
                    colVal = 0;
                }
                double OldRange = max - min;
                int NewRange = (255 - 0);
                int col = (int) ((((colVal - min) * NewRange) / OldRange) + 0);
                Color color = new Color(col, 0, 0);
                g2d.setColor(color);
                //                g2d.setColor(transitionOfHueRange(col,120,350));

                g2d.fillOval(x, y, 2, 2);
            }
        }
    }


    public static Color transitionOfHueRange(double percentage, int startHue, int endHue) {
        // From 'startHue' 'percentage'-many to 'endHue'
        // Finally map from [0°, 360°] -> [0, 1.0] by dividing
        double hue = ((percentage * (endHue - startHue)) + startHue) / 360;

        double saturation = 1.0;
        double lightness = 0.5;

        // Get the color
        return hslColorToRgb(hue, saturation, lightness);
    }

    public static Color hslColorToRgb(double hue, double saturation, double lightness) {
        if (saturation == 0.0) {
            // The color is achromatic (has no color)
            // Thus use its lightness for a grey-scale color
            int grey = (int) percToColor(lightness);
            return new Color(grey, grey, grey);
        }

        double q;
        if (lightness < 0.5) {
            q = lightness * (1 + saturation);
        } else {
            q = lightness + saturation - lightness * saturation;
        }
        double p = 2 * lightness - q;

        double oneThird = 1.0 / 3;
        double red = percToColor(hueToRgb(p, q, hue + oneThird));
        double green = percToColor(hueToRgb(p, q, hue));
        double blue = percToColor(hueToRgb(p, q, hue - oneThird));

        return new Color((int) red, (int) green, (int) blue);
    }

    public static double hueToRgb(double p, double q, double t) {
        if (t < 0) {
            t += 1;
        }
        if (t > 1) {
            t -= 1;
        }

        if (t < 1.0 / 6) {
            return p + (q - p) * 6 * t;
        }
        if (t < 1.0 / 2) {
            return q;
        }
        if (t < 2.0 / 3) {
            return p + (q - p) * (2.0 / 3 - t) * 6;
        }
        return p;
    }

    public static long percToColor(double percentage) {
        return Math.round(percentage * 255);
    }
}

