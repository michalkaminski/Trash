package complexsystems.flock.rules;

import complexsystems.components.Rule;
import complexsystems.components.Turtle;
import complexsystems.components.VectorState;
import complexsystems.flock.components.Flock;
import complexsystems.flock.components.Bird;

public class KeepDistance extends Rule {

    private final double DISTANCE;
    private final Flock FLOCK;
    private final double divider;
    public KeepDistance(double distance, Flock flock, double divider) {
        DISTANCE = distance;
        FLOCK = flock;
        this.divider=divider;

    }

    @Override
    public VectorState change(Turtle turtle, Turtle[] turtles) {
        Bird bird = (Bird) turtle;
        Bird[] flock = (Bird[]) turtles;

        VectorState c = new VectorState(0, 0);

        for (Bird b : FLOCK.get()) {
            if (b != bird) {
                if (VectorState.getDistance(VectorState.sub(b.position, bird.position)) < DISTANCE) {
                    c = VectorState.sub(c, VectorState.sub(b.position, bird.position));
                    return VectorState.divScalar(c, divider);
                }
            }
        }
        //TODO WHAT IS 8?
        return VectorState.divScalar(c, 8);

    }
}
