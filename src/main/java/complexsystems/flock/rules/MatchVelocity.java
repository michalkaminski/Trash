package complexsystems.flock.rules;

import complexsystems.components.Rule;
import complexsystems.components.Turtle;
import complexsystems.components.VectorState;
import complexsystems.flock.components.Bird;

public class MatchVelocity extends Rule {

    private double BIRDS_MAGNET_FORCE;

    public MatchVelocity(double magnetForce) {
        BIRDS_MAGNET_FORCE = magnetForce;
    }

    @Override
    public VectorState change(Turtle turtle, Turtle[] turtles) {
        Bird currentBird = (Bird) turtle;
        Bird[] flock = (Bird[]) turtles;

        for (Bird otherBird : flock) {
            if (otherBird != currentBird) {
                double currentDistance = VectorState.getDistance(VectorState.sub(otherBird.position, currentBird.position));
                currentDistance = currentDistance == 0 ? 0.1 : currentDistance;
                double ratio = BIRDS_MAGNET_FORCE / currentDistance;
                ratio = ratio == 0 ? 0.01 : ratio;
                currentBird.velocity =
                        VectorState.addWeighted(currentBird.velocity, otherBird.velocity, ratio);
            }
        }
        limitSpeed(currentBird);
        return currentBird.velocity;
    }


    private void limitSpeed(Bird b) {
        b.velocity = VectorState.divScalar(b.velocity, VectorState.getDistance(b.velocity));
    }

}
