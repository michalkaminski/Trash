/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package complexsystems.flock.rules;

import complexsystems.flock.components.Bird;
import complexsystems.flock.components.VectorState;

public class KeepInBounds extends Rule {

    private final int MAX_X, MIN_X, MAX_Y, MIN_Y;
    private final double RETURN_SPEED;

    public KeepInBounds(int maxX, int minX, int maxY, int minY, double returnSpeed) {
        MAX_X = maxX;
        MIN_X = minX;
        MAX_Y = maxY;
        MIN_Y = minY;
        RETURN_SPEED = returnSpeed;
    }

    @Override
    public VectorState change(Bird bird, Bird[] flock) {
        VectorState v = new VectorState(0, 0);

        if (bird.position.x > MAX_X) {
            v.x = -RETURN_SPEED;
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
