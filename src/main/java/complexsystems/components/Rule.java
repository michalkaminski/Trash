package complexsystems.components;


public abstract class Rule {

    double weight;

    public Rule() {
        weight = 1;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public VectorState getChange(Turtle turtle, Turtle... flock) {
        return VectorState.multScalar(change(turtle, flock), weight);
    }

    public abstract VectorState change(Turtle turtle, Turtle[] flock);
}
