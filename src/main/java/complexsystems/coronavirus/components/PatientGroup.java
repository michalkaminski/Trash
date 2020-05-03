package complexsystems.coronavirus.components;

import complexsystems.components.Rule;
import complexsystems.components.VectorState;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class PatientGroup {

    private final Patient[] patients;
    private final ArrayList<Rule> rules;
    private double maxSpeed;

    public PatientGroup(int numberOfPatients, double maxSpeed, int maxX, int minX, int maxY, int minY) {
        this.maxSpeed = maxSpeed;
        patients = new Patient[numberOfPatients];
        for (int i = 0; i < numberOfPatients; i++) {
            patients[i] = new Patient(Patient.randomPos(maxX, minX, maxY, minY), Patient.randomVel(maxSpeed));
        }

        rules = new ArrayList<>();

    }

    public Patient[] get() {
        return patients;
    }

    public void addRule(Rule rule) {
        rules.add(rule);
    }

    public void update() {
        for (Patient b : patients) {
            calculateVelocity(b);
            limitSpeed(b);
        }
        for (Patient b : patients) {
            b.move();
        }
    }

    private void calculateVelocity(Patient b) {
        VectorState change = new VectorState(0, 0);
        for (Rule rule : rules) {
            change = VectorState.add(change, rule.getChange(b, patients));
        }
        b.velocity = VectorState.add(b.velocity, change);
    }

    private void limitSpeed(Patient b) {
        if (VectorState.getDistance(b.velocity) > maxSpeed) {
//            b.velocity = VectorState.multScalar(VectorState.divScalar(b.velocity, VectorState.getDistance(b.velocity)), maxSpeed);
        }
    }
}
