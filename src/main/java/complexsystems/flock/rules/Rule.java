package complexsystems.flock.rules;

import complexsystems.flock.components.Bird;
import complexsystems.flock.components.VectorState;

public abstract class Rule {

    double weight;

    public Rule() {
        weight = 1;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
    
    public VectorState getChange(Bird bird, Bird[] flock) {
        return VectorState.multScalar(change(bird, flock), weight);
    }

    public abstract VectorState change(Bird bird, Bird[] flock);
}
