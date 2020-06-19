package complexsystems.coronavirus.rules;

import complexsystems.components.Rule;
import complexsystems.components.Turtle;
import complexsystems.components.VectorState;
import complexsystems.coronavirus.components.Patient;

public class KeepDistance extends Rule {

    private static final double EPSILONV = 0.01;
    private final double DISTANCE;

    public KeepDistance(double distance) {
        DISTANCE = distance;
    }

    @Override
    public VectorState change(Turtle turtle, Turtle[] turtles) {
        Patient patient = (Patient) turtle;
        VectorState c = new VectorState(0, 0);

        for (Patient p : (Patient[]) turtles) {
            if (p != patient) {
                double distance = VectorState.getDistance(VectorState.sub(p.position, patient.position));
                if (distance <= 2*DISTANCE) {
                    if (distance < EPSILONV) {
                        distance = EPSILONV;
                    }
                    c = VectorState.sub(patient.position, p.position);
                    c= VectorState.multScalar(c, 1/distance);
                }
            }
        }
        return c;
    }

}
