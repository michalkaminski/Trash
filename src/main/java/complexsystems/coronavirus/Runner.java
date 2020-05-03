package complexsystems.coronavirus;


import complexsystems.coronavirus.components.Patient;
import complexsystems.coronavirus.components.PatientGroup;
import complexsystems.coronavirus.rules.KeepDistance;
import complexsystems.coronavirus.rules.KeepInBounds;
import complexsystems.coronavirus.rules.MatchVelocity;
import complexsystems.visualisation.Point;
import complexsystems.visualisation.Visualization2D;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Runner {

    private static final int VIS_SIZE = 2000;

    public static void main(String[] args) {

        List<Point> values;

        Visualization2D visualization2D = new Visualization2D(
                0.001d,
                VIS_SIZE,
                VIS_SIZE,
                VIS_SIZE);


        PatientGroup patientsGroup1 = new PatientGroup(2000, 10, 500, -500, 500, -500);

        patientsGroup1.addRule(new KeepInBounds(VIS_SIZE/2, -VIS_SIZE/2, VIS_SIZE/2, -VIS_SIZE/2, 10));
        patientsGroup1.addRule(new KeepDistance(1, patientsGroup1, 8));
        patientsGroup1.addRule(new MatchVelocity(50));

        while (true) {
            patientsGroup1.update();

            List<Double> orderedValues = getOrderedValues(patientsGroup1);

            values = Arrays.stream(patientsGroup1.getPatients())
                    .parallel()
                    .map(patient -> convertPatientToPoint(patient))
                    .collect(Collectors.toList());
            visualization2D.visualize(values);
        }
    }


    private static Point convertPatientToPoint(Patient patient) {
        return new Point(patient.position.getX(), patient.position.getY(), patient.velocity.getScalarSpeed()/21.5);
    }


    private static List<Double> getOrderedValues(PatientGroup patientsGroup1) {
        return Arrays.stream(patientsGroup1.getPatients())
                .map(patient -> patient.velocity.getScalarSpeed())
                .sorted(Double::compareTo)
                .collect(Collectors.toList());
    }

}
