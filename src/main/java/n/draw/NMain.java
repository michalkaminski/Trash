package n.draw;

import lombok.extern.slf4j.Slf4j;
import libra.functions.IFunction;
import libra.functions.FunctionXSinX2;

/**
 * Created by michal on 23.05.2019.
 */

@Slf4j
public class NMain {

    public static void main(String[] args)
    {
        double SCALE=2.0d;
        double STEP=0.005d;
        double FORWARD=0.01d;

        IFunction f =new FunctionXSinX2();

        StdDraw.setXscale(-SCALE,SCALE);
        StdDraw.setYscale(-SCALE,SCALE);
        StdDraw.enableDoubleBuffering();

        for(double n=-SCALE;n<=SCALE;n+=FORWARD) {
            StdDraw.clear();

            for (double t = -SCALE; t <= SCALE; t += STEP) {

                StdDraw.setPenColor(StdDraw.BLACK);
                StdDraw.point(t, f.valueOf(t));

                StdDraw.setPenColor(StdDraw.RED);
                FORWARD=-f.derivative(n)/1000;
                StdDraw.point(t, f.derivative(n) * (t - n) + f.valueOf(n));
                log.info("FORWARD: {}",FORWARD);

            }

            StdDraw.show();
        }
    }
}
