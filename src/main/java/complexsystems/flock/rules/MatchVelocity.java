package complexsystems.flock.rules;

import complexsystems.components.Rule;
import complexsystems.components.Turtle;
import complexsystems.components.VectorState;
import complexsystems.flock.components.Bird;

public class MatchVelocity extends Rule {

    private final double DISTANCE;

    public MatchVelocity(double distance) {
        DISTANCE = distance;
    }

    @Override
    public VectorState change(Turtle turtle, Turtle[] turtles) {
        Bird bird = (Bird) turtle;
        Bird[] flock = (Bird[]) turtles;

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
