package nncarina.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by michal on 03.10.2017.
 */
public class DataSet {

private List<DataRow> dataRows=new ArrayList<DataRow>();

    public List<DataRow> getDataRows() {
        return dataRows;
    }

    public void setDataRows(List<DataRow> dataRows) {
        this.dataRows = dataRows;
    }
}
