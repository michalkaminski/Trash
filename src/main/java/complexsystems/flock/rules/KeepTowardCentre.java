/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package complexsystems.flock.rules;

import complexsystems.components.Rule;
import complexsystems.components.Turtle;
import complexsystems.components.VectorState;
import complexsystems.flock.components.Bird;

public class KeepTowardCentre extends Rule {

    private final double DISTANCE;

    public KeepTowardCentre(double distance) {
        DISTANCE = distance;
    }

    @Override
    public VectorState change(Turtle turtle, Turtle[] turtles) {
        Bird bird = (Bird) turtle;
        Bird[] flock = (Bird[]) turtles;

        VectorState p = bird.position;

        int i = 1;

        for (Bird b : flock) {
            if (b != bird && VectorState.getDistance(VectorState.sub(b.position, bird.position)) < DISTANCE) {
                p = VectorState.add(p, b.position);
                i++;
            }
        }

        p = VectorState.divScalar(p, i);

        return VectorState.divScalar(VectorState.sub(p, bird.position), 100);
    }
}
