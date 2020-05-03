package complexsystems.flock.main;


import complexsystems.flock.components.Flock;
import complexsystems.flock.rules.*;
import complexsystems.flock.visualization.Screen;

import java.util.ArrayList;
import java.util.List;

public class Runner {

    private static final double VELOCITY_MATCH = 40;

    public static void main(String[] args) {

        List<Flock> flocks =new ArrayList<>();

        Screen screen = new Screen(1000, 1000, flocks);

        Flock f1 = new Flock(2000, 10, 500, -500, 500, -500);
        Flock f2 = new Flock(2000, 10, 500, -500, 500, -500);
        
        f1.addRule(new KeepInBounds(500, -500, 500, -500, 10));
        f1.addRule(new KeepDistance(40, f1, 8));
//        f1.addRule(new KeepDistance(40, f2, 8));
        //f1.addRule(new KeepTowardCentre(80));
        f1.addRule(new MatchVelocity(VELOCITY_MATCH));
        flocks.add(f1);

        f2.addRule(new KeepInBounds(400, -400, 400, -400, 10));
        f2.addRule(new KeepDistance(20, f2, 2));
        KeepDistance rule = new KeepDistance(60, f1, 8);
        rule.setWeight(-1);
        f2.addRule(rule);
        //f2.addRule(new KeepTowardCentre(300));
        f2.addRule(new MatchVelocity(VELOCITY_MATCH));
        flocks.add(f2);

        while (true) {
            flocks.forEach(flock -> flock.update());
            screen.repaint();
        }
    }
}
