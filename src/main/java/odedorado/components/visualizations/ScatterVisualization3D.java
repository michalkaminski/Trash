package odedorado.components.visualizations;

import odedorado.components.functions.IFunction;
import org.apache.commons.lang3.tuple.Triple;
import org.jzy3d.analysis.AbstractAnalysis;
import org.jzy3d.chart.factories.AWTChartComponentFactory;
import org.jzy3d.colors.Color;
import org.jzy3d.maths.Coord3d;
import org.jzy3d.plot3d.primitives.LineStrip;
import org.jzy3d.plot3d.primitives.Point;
import org.jzy3d.plot3d.rendering.canvas.Quality;

import java.util.Iterator;
import java.util.concurrent.LinkedBlockingQueue;


public class ScatterVisualization3D extends AbstractAnalysis{

    IFunction functionX;
    IFunction functionY;
    IFunction functionZ;

    Triple<Double, Double, Double> initialCondition[];

    double SCALE;
    double h;
    double START;
    private LinkedBlockingQueue<LineStrip> lineStrips=new LinkedBlockingQueue<>();


    public ScatterVisualization3D(
            IFunction functionX,
            IFunction functionY,
            IFunction functionZ,
            Triple<Double, Double, Double> initialCondition[],
            double SCALE,
            double STEP,
            double START
    ) {
        this.functionX = functionX;
        this.functionY=functionY;
        this.functionZ=functionZ;
        this.initialCondition = initialCondition;
        this.SCALE = SCALE;
        this.h = STEP;
        this.START = START;
    }

    @Override
    public void init(){
        float a = 0.25f;

        double sX;
        double sY;
        double sZ;

        for (int i = 0; i <= initialCondition.length-1; i++) {
            float x = this.initialCondition[i].getLeft().floatValue();
            float y = this.initialCondition[i].getMiddle().floatValue();
            float z = this.initialCondition[i].getRight().floatValue();

            LineStrip ls = new LineStrip();

            for (double t = -START; t <= SCALE; t += h) {

                sX = h*functionX.derivative(x,y,z);
                sY = h*functionY.derivative(x,y,z);
                sZ = h*functionZ.derivative(x,y,z);

                ls.add(new Point(new Coord3d(x, y, z), Color.GRAY));
                ls.add(new Point(new Coord3d((float)(x+sX), (float)(y+sY),(float)(z+sZ)), new Color((float)(x+sX), (float)(y+sY), (float)(z+sZ), a)));
                ls.setDisplayed(true);

                x+=sX;
                y+=sY;
                z+=sZ;
            }
            this.lineStrips.add(ls);

        }
        chart = AWTChartComponentFactory.chart(Quality.Advanced, "newt");
        Iterator<LineStrip> lsIterator = this.lineStrips.iterator();
        while (lsIterator.hasNext()) {
            chart.getScene().getGraph().add(lsIterator.next());
        }
    }
}