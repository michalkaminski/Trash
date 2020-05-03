package complexsystems.coronavirus.rules;

import complexsystems.coronavirus.components.Patient;
import complexsystems.coronavirus.components.VectorState;

public abstract class Rule {

    double weight;

    public Rule() {
        weight = 1;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
    
    public VectorState getChange(Patient patient, Patient[] flock) {
        return VectorState.multScalar(change(patient, flock), weight);
    }

    public abstract VectorState change(Patient patient, Patient[] flock);
}
