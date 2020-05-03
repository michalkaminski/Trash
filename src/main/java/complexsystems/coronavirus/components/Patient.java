package complexsystems.coronavirus.components;

public class Patient {

    public VectorState position;
    public VectorState velocity;

    public Patient(VectorState position, VectorState velocity) {
        this.position = position;
        this.velocity = velocity;
    }

    public void move() {
        position = VectorState.add(position, velocity);
    }

    public static VectorState randomPos(int maxX, int minX, int maxY, int minY) {
        double x = (Math.random() * (maxX - minX)) + minX;
        double y = (Math.random() * (maxY - minY)) + minY;
        return new VectorState(x, y);
    }

    public static VectorState randomVel(double maxSpeed) {
        double minSpeed = maxSpeed * -1;
        double x = (Math.random() * (maxSpeed - minSpeed)) + minSpeed;
        double y = (Math.random() * (maxSpeed - minSpeed)) + minSpeed;
        return new VectorState(x, y);
    }
}
