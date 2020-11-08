package complexsystems.flock.rules;

import complexsystems.components.Rule;
import complexsystems.components.Turtle;
import complexsystems.components.VectorState;
import complexsystems.flock.components.Flock;
import complexsystems.flock.components.Bird;

public class KeepDistance extends Rule {

    private final double DISTANCE;
    private final Flock flock;
    private final double divider;

    public KeepDistance(double distance, Flock flock, double divider) {
        DISTANCE = distance;
        this.flock = flock;
        this.divider = divider;

    }

    @Override
    public VectorState change(Turtle turtle, Turtle[] turtles) {

        Bird currentBird = (Bird) turtle;
        VectorState c = new VectorState(0, 0);

        for (Bird otherBird : this.flock.get()) {
            if (otherBird != currentBird) {
                if (VectorState.getDistance(VectorState.sub(otherBird.position, currentBird.position)) < DISTANCE) {
                    c = VectorState.sub(c, VectorState.sub(otherBird.position, currentBird.position));
                }
            }
        }
        return c;

    }
}
