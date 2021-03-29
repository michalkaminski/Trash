package odes.demos;

import org.jzy3d.analysis.AbstractAnalysis;
import org.jzy3d.analysis.AnalysisLauncher;
import org.jzy3d.chart.factories.AWTChartComponentFactory;
import org.jzy3d.colors.Color;
import org.jzy3d.maths.Coord3d;
import org.jzy3d.plot3d.primitives.Scatter;
import org.jzy3d.plot3d.rendering.canvas.Quality;

import java.util.Arrays;
import java.util.Random;

public class Vis3D2D extends AbstractAnalysis {

    private int size;

    float x;
    float y;
    float z;
    float a = 0.25f;

    private Coord3d[] points;
    private Color[] colors;
    private Scatter scatter;

    public Vis3D2D(int size) {
        this.size = size;
        points = new Coord3d[size * size];
        colors = new Color[size * size];
    }

    @Override
    public void init() {
        generate(55.0f);
        scatter = new Scatter(points, colors);
        chart = AWTChartComponentFactory.chart(Quality.Advanced, "newt");
//        Quality quality = new Quality(true, true, true, true, true, true, false);
//        GLCapabilities capabilities = org.jzy3d.global.Settings.getInstance().getGLCapabilities();
//        capabilities.setSampleBuffers(true);   // false = ragged edges around large points. true = smooth rounding.
//        capabilities.setNumSamples(2);
//        chart = new Chart(quality, "awt", capabilities);
//        chart.getAxeLayout().setMainColor(Color.WHITE);
//        chart.getView().setBackgroundColor(Color.BLACK);
//        chart.getScene().add(scatter);
//        scatter.setLegend( new ColorbarLegend(scatter, chart.getView().getAxe().getLayout(), Color.WHITE, null) );
//        scatter.setLegendDisplayed(true);
        chart.getView().setBackgroundColor(Color.BLACK);
        chart.getAxeLayout().setMainColor(Color.WHITE);


        chart.getScene().add(scatter);
    }

    public void generateFrom2dArray(double space2d[][], double min, double max) {

//
//        double[] test = Arrays.stream(space2d)
//                .flatMapToDouble(Arrays::stream)
//                .toArray();


        for (int i = 0; i <= space2d.length - 1; i++) {
            for (int j = 0; j <= space2d.length - 1; j++) {
                double colVal = space2d[i][j];

                points[i * size + j] = new Coord3d(i * .05, j * .05, 0);
                colors[i * size + j] = new Color(getColor(colVal, min, max), 0, 0, .5f);
            }
        }
    }

    public void generateFrom3dArray(double space3d[][][], double min, double max) {

        for (int i = 0; i <= space3d.length - 1; i++) {
            for (int j = 0; j <= space3d.length - 1; j++) {
                for (int k = 0; k <= space3d.length - 1; k++) {

                    double colVal = space3d[i][j][k];

                    int index = i + j * size + k * size* size;

                    points[index] = new Coord3d(i * .05, j * .05, k * .0);
                    colors[index] = new Color(getColor(colVal, min, max), 0, 0, .5f);
                }
            }
        }
    }


    public void generate(float maxGenerated) {
        Random r = new Random();
        for (int i = 0; i < size * size; i++) {
            x = (0 + (maxGenerated) * r.nextFloat());
            y = (0 + (maxGenerated) * r.nextFloat());
            z = (0 + (maxGenerated) * r.nextFloat());

            points[i] = new Coord3d(x, y, z);
            colors[i] = new Color(x, y, z, a);
        }
    }

    public void render() {
        scatter = new Scatter(points, colors);
        chart.render();
    }

    private float getColor(double colVal, double min, double max) {
        if (colVal < 0) {
            colVal = 0;
        }
        double OldRange = max - min;
        double NewRange = (1 - 0);
        return (float) ((((colVal - min) * NewRange) / OldRange) + 0);
    }
}