package data;

import java.util.List;

/**
 * Created by michal on 03.10.2017.
 */
public class DataRow {
    private String label;
    private List<Float> variables;
    private List<Float> results;

    public List<Float> getVariables() {
        return variables;
    }

    public void setVariables(List<Float> variables) {
        this.variables = variables;
    }

    public List<Float> getResults() {
        return results;
    }

    public void setResults(List<Float> results) {
        this.results = results;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}


