package n;

import org.jzy3d.analysis.AbstractAnalysis;
import org.jzy3d.analysis.AnalysisLauncher;
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


public class D3Animate
        extends AbstractAnalysis
          {


    protected Chart chart;




    public static void main(String[] args) throws Exception {

        AnalysisLauncher.open( new D3Animate());
    }

    @Override
    public void init() {

        this.chart = initializeChart(Quality.Nicest);

        Mapper mapper = new Mapper() {
            public double f(double x, double y) {
                return Math.pow(x, 2) * Math.pow(y, 3) * Math.cos(y / 20);
            }
        };

        Range range = new Range(-150, 150);
        int steps = 50;

        Shape surface = Builder.buildOrthonormal(new OrthonormalGrid(range, steps), mapper);
        surface.setColorMapper(new ColorMapper(new ColorMapRainbow(), surface.getBounds().getZmin(), surface.getBounds().getZmax(), new org.jzy3d.colors.Color(1, 1, 1, .5f)));

        surface.setFaceDisplayed(true);
        surface.setWireframeDisplayed(true);
        surface.setWireframeColor(Color.GRAY);

        chart.add(surface);

    }

    public Chart getChart() {
        return chart;
    }




}