package complexsystems.coronavirus.components;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VectorState {

    public double x, y;

    public VectorState(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public static VectorState add(VectorState v1, VectorState v2) {
        double x = v1.x + v2.x;
        double y = v1.y + v2.y;
        return new VectorState(x, y);
    }

    public static VectorState addWeighted(VectorState v1, VectorState v2, double weight) {
        double x = v1.x + (v1.x - v2.x) * weight;
        double y = v1.y + (v1.y - v2.y) * weight;
        return new VectorState(x, y);
    }

    public static VectorState addAllPositions(VectorState[] all) {
        VectorState v = new VectorState(0, 0);
        for (VectorState a : all) {
            v = VectorState.add(v, a);
        }
        return v;
    }

    public static VectorState sub(VectorState v1, VectorState v2) {
        double x = v1.x - v2.x;
        double y = v1.y - v2.y;
        return new VectorState(x, y);
    }

    public static VectorState distance(VectorState v1, VectorState v2) {
        double x = v1.x - v2.x;
        double y = v1.y - v2.y;
        return new VectorState(x, y);
    }


    public static VectorState multScalar(VectorState v1, double s1) {
        double x = v1.x * s1;
        double y = v1.y * s1;
        return new VectorState(x, y);
    }

    public static VectorState divScalar(VectorState v1, double s1) {
        double x = v1.x / s1;
        double y = v1.y / s1;
        return new VectorState(x, y);
    }

    public static double getDistance(VectorState v1) {
        return Math.sqrt(Math.pow(v1.x, 2) + Math.pow(v1.y, 2));
    }

    public double getScalarSpeed() {
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }

}
