package complexsystems.coronavirus.rules;

import complexsystems.components.Rule;
import complexsystems.components.Turtle;
import complexsystems.coronavirus.components.Patient;
import complexsystems.coronavirus.components.PatientGroup;
import complexsystems.components.VectorState;
import complexsystems.flock.components.Bird;

public class KeepDistance extends Rule {

    private final double DISTANCE;
    private final PatientGroup PatientGroup;
    private final double divider;
    public KeepDistance(double distance, PatientGroup patientGroup, double divider) {
        DISTANCE = distance;
        PatientGroup = patientGroup;
        this.divider=divider;

    }

    @Override
    public VectorState change(Turtle turtle, Turtle[] turtles) {
        Patient patient = (Patient) turtle;
        Patient[] patients = (Patient[]) turtles;

        VectorState c = new VectorState(0, 0);

        for (Patient b : PatientGroup.get()) {
            if (b != patient) {
                if (VectorState.getDistance(VectorState.sub(b.position, patient.position)) < DISTANCE) {
                    c = VectorState.sub(c, VectorState.sub(b.position, patient.position));
                    return VectorState.divScalar(c, divider);
                }
            }
        }
        //TODO WHAT IS 8?
        return VectorState.divScalar(c, 8);

    }

}
