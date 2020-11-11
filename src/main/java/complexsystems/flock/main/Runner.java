package complexsystems.flock.main;


import complexsystems.flock.components.Flock;
import complexsystems.flock.rules.*;
import complexsystems.flock.visualization.Screen;

import java.util.ArrayList;
import java.util.List;

public class Runner {

    private static final double BIRD_MAGNET_FORCE = 40;
    private static final double DISTANCE = 0.5;

    public static void main(String[] args) {

        List<Flock> flocks =new ArrayList<>();
        Screen screen = new Screen(1000, 1000, flocks);
        Flock f1 = new Flock(1000, 10, 500, -500, 500, -500);

        f1.addRule(new KeepDistance(DISTANCE, f1, 8));
        f1.addRule(new MatchVelocity(BIRD_MAGNET_FORCE));
        flocks.add(f1);

//        f2.addRule(new KeepDistance(2, f2, 2));
//        KeepDistance rule = new KeepDistance(60, f1, 8);
//        rule.setWeight(-1);
//        f2.addRule(rule);


        while (true) {
            flocks.forEach(flock -> flock.update());
            screen.repaint();
        }
    }
}
