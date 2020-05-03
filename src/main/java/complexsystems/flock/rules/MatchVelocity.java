package complexsystems.flock.rules;

import complexsystems.flock.components.Bird;
import complexsystems.flock.components.VectorState;

public class MatchVelocity extends Rule {

    private final double DISTANCE;

    public MatchVelocity(double distance) {
        DISTANCE = distance;
    }

    @Override
    public VectorState change(Bird bird, Bird[] flock) {

        int i = 1;

        for (Bird b : flock) {
            double dist = VectorState.getDistance(VectorState.sub(b.position, bird.position));
            if (b != bird ) {
                bird.velocity = VectorState.addWeighted(bird.velocity, b.velocity, DISTANCE/dist);
                i++;
            }
        }
        bird.velocity = VectorState.divScalar(bird.velocity, i);
        return VectorState.divScalar(VectorState.sub(bird.velocity, bird.velocity), 8);
    }
}
