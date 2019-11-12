package libra.odes.visual;

import javafx.util.Pair;
import libra.functions.IFunction;
import org.jzy3d.analysis.AbstractAnalysis;
import org.jzy3d.chart.factories.AWTChartComponentFactory;
import org.jzy3d.colors.Color;
import org.jzy3d.maths.Coord3d;
import org.jzy3d.plot3d.primitives.LineStrip;
import org.jzy3d.plot3d.primitives.Point;
import org.jzy3d.plot3d.primitives.Scatter;
import org.jzy3d.plot3d.rendering.canvas.Quality;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;


public class ScatterVisualization2D extends AbstractAnalysis{

    IFunction functionX;
    IFunction functionY;

    Pair<Double, Double> initialCondition[];

    double SCALE;
    double h;
    double START;
    private LinkedBlockingQueue<LineStrip> lineStrips=new LinkedBlockingQueue<>();


    public ScatterVisualization2D(
            IFunction functionX,
            IFunction functionY,
            Pair<Double, Double> initialCondition[],
            double SCALE,
            double STEP,
            double START
    ) {
        this.functionX = functionX;
        this.functionY=functionY;
        this.initialCondition = initialCondition;
        this.SCALE = SCALE;
        this.h = STEP;
        this.START = START;
    }

    @Override
    public void init(){
        float a = 0.25f;
//        List<Coord3d> pointsList=new ArrayList<>();
//        List<Color> colorsList=new ArrayList<>();

        double sX;
        double sY;

        for (int i = 0; i <= initialCondition.length-1; i++) {
            float x = this.initialCondition[i].getKey().floatValue();
            float y = this.initialCondition[i].getValue().floatValue();

            for (double t = -START; t <= SCALE; t += h) {

                sX = h*functionX.derivative(x,y);
                sY = h*functionY.derivative(x,y);

           //     pointsList.add(new Coord3d((float)(x+sX), (float)(y+sY), 0.0f));
            //    colorsList.add(new Color((float)(x+sX), (float)(y+sY), 0.0f, a));

                LineStrip ls = new LineStrip();
                ls.add(new Point(new Coord3d(x, y, 0), Color.GRAY));
                ls.add(new Point(new Coord3d((float)(x+sX), (float)(y+sY),0), new Color((float)(x+sX), (float)(y+sY), 0.0f, a)));
                ls.setDisplayed(true);
                this.lineStrips.add(ls);

                x+=sX;
                y+=sY;
            }




        }

//        Scatter scatter = new Scatter(pointsList.toArray(new Coord3d[pointsList.size()]), colorsList.toArray(new Color[colorsList.size()]));
        chart = AWTChartComponentFactory.chart(Quality.Advanced, "newt");

        Iterator<LineStrip> lsIterator = this.lineStrips.iterator();
        while (lsIterator.hasNext()) {
            chart.getScene().add(lsIterator.next());
        }

  //      chart.add(scatter);
    }
}