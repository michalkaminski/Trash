/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package complexsystems.coronavirus.rules;

import complexsystems.coronavirus.components.Patient;
import complexsystems.coronavirus.components.VectorState;

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
    public VectorState change(Patient patient, Patient[] flock) {
        VectorState v = new VectorState(0, 0);

        if (patient.position.x > MAX_X) {
            v.x = -RETURN_SPEED;
        } else if (patient.position.x < MIN_X) {
            v.x = RETURN_SPEED;
        }

        if (patient.position.y > MAX_Y) {
            v.y = -RETURN_SPEED;
        } else if (patient.position.y < MIN_Y) {
            v.y = RETURN_SPEED;
        }

        return v;
    }
}
