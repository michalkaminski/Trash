package odedorado.demos;

import org.jzy3d.chart.AWTChart;
import org.jzy3d.chart.Chart;
import org.jzy3d.colors.Color;
import org.jzy3d.colors.ColorMapper;
import org.jzy3d.colors.colormaps.ColorMapRainbow;
import org.jzy3d.maths.Range;
import org.jzy3d.plot3d.builder.Builder;
import org.jzy3d.plot3d.builder.Mapper;
import org.jzy3d.plot3d.builder.concrete.OrthonormalGrid;
import org.jzy3d.plot3d.primitives.Shape;
import org.jzy3d.plot3d.rendering.canvas.Quality;



/**
 * Created by michal on 24.05.2019.
 */
public class D3   {

    public static void main(String[] args)
    {
        // Define a function to plot
        Mapper mapper = new Mapper() {
            public double f(double x, double y) {
                return Math.pow(x, 2) * Math.pow(y,2)*Math.cos(y / 20);
            }
        };

// Define range and precision for the function to plot
        Range range = new Range(-150, 150);
        int steps = 50;

// Create a surface drawing that function
        Shape surface = Builder.buildOrthonormal(new OrthonormalGrid(range, steps), mapper);
        surface.setColorMapper(new ColorMapper(new ColorMapRainbow(), surface.getBounds().getZmin(), surface.getBounds().getZmax(), new org.jzy3d.colors.Color(1, 1, 1, .5f)));

        surface.setFaceDisplayed(true);
        surface.setWireframeDisplayed(true);
        surface.setWireframeColor(Color.GRAY);

// Create a chart and add the surface
        Chart chart = new AWTChart(Quality.Nicest);
        chart.add(surface);
        chart.open("Jzy3d Demo", 800, 800);
    }


}
