package complexsystems.visualisation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Point {

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    double x;
    double y;
    double color;
}
