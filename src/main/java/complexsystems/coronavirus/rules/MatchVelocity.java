package complexsystems.coronavirus.rules;

import complexsystems.coronavirus.components.Patient;
import complexsystems.coronavirus.components.VectorState;

public class MatchVelocity extends Rule {

    private final double DISTANCE;

    public MatchVelocity(double distance) {
        DISTANCE = distance;
    }

    @Override
    public VectorState change(Patient patient, Patient[] flock) {

        int i = 1;

        for (Patient b : flock) {
            double dist = VectorState.getDistance(VectorState.sub(b.position, patient.position));
            if (b != patient) {
                if (VectorState.getDistance(VectorState.distance(b.position, patient.position)) <= DISTANCE) {
                    patient.velocity = VectorState.addWeighted(patient.velocity, b.velocity, DISTANCE/dist);
                }
                i++;
            }
        }
        patient.velocity = VectorState.divScalar(patient.velocity, i);
        return VectorState.divScalar(VectorState.sub(patient.velocity, patient.velocity), 8);
    }
}
