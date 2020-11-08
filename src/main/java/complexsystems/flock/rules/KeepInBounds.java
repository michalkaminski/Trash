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

public class KeepInBounds extends Rule {

    private final int MAX_X, MIN_X, MAX_Y, MIN_Y;
    private final double RETURN_SPEED;
    private final double BACK_POSITION = 1d;

    public KeepInBounds(int maxX, int minX, int maxY, int minY, double returnSpeed) {
        MAX_X = maxX;
        MIN_X = minX;
        MAX_Y = maxY;
        MIN_Y = minY;
        RETURN_SPEED = returnSpeed;
    }

    @Override
    public VectorState change(Turtle turtle, Turtle[] turtles) {
        Bird bird = (Bird) turtle;

        VectorState v = new VectorState(0, 0);

        if (bird.position.x > MAX_X) {
            v.x = -BACK_POSITION;
        } else if (bird.position.x < MIN_X) {
            v.x = RETURN_SPEED;
        }

        if (bird.position.y > MAX_Y) {
            v.y = -RETURN_SPEED;
        } else if (bird.position.y < MIN_Y) {
            v.y = RETURN_SPEED;
        }

        return v;
    }
}
