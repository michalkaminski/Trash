/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package complexsystems.coronavirus.rules;

import complexsystems.coronavirus.components.Patient;
import complexsystems.coronavirus.components.VectorState;

public class KeepTowardCentre extends Rule {

    private final double DISTANCE;

    public KeepTowardCentre(double distance) {
        DISTANCE = distance;
    }

    @Override
    public VectorState change(Patient patient, Patient[] flock) {
        VectorState p = patient.position;

        int i = 1;

        for (Patient b : flock) {
            if (b != patient && VectorState.getDistance(VectorState.sub(b.position, patient.position)) < DISTANCE) {
                p = VectorState.add(p, b.position);
                i++;
            }
        }

        p = VectorState.divScalar(p, i);

        return VectorState.divScalar(VectorState.sub(p, patient.position), 100);
    }
}
