package complexsystems.coronavirus.rules;

import complexsystems.components.Rule;
import complexsystems.components.Turtle;
import complexsystems.coronavirus.components.Patient;
import complexsystems.components.VectorState;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MatchVelocity extends Rule {

    private static final double EPSILON = 0.1;
    private final double DISTANCE;

    public MatchVelocity(double distance) {
        DISTANCE = distance;
    }

    @Override
    public VectorState change(Turtle turtle, Turtle[] turtles) {
        Patient patient = (Patient) turtle;

        for (Patient p : (Patient[]) turtles) {
            if (p != patient) {
                double distance = VectorState.getDistance(VectorState.sub(p.position, patient.position));
                if (distance <= DISTANCE) {
                    if (distance <=EPSILON) {
                        distance = EPSILON;
                    }
                    patient.velocity =
                            VectorState.addWeighted(
                                    patient.velocity, p.velocity, (DISTANCE * DISTANCE)  / distance);
                }

            }
        }
//        patient.velocity = VectorState.divScalar(patient.velocity, 1);
//        return VectorState.divScalar(VectorState.sub(patient.velocity, patient.velocity), 8);
        return patient.velocity;
    }

}
