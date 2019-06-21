package n.draw;

/**
 * Created by michal on 24.05.2019.
 */

import org.jzy3d.plot3d.builder.Mapper;

public abstract class ParametrizedMapper extends Mapper{
    public ParametrizedMapper(double p){
        this.p = p;
    }

    public void setParam(double p){
        this.p = p;
    }

    public double getParam(){
        return p;
    }

    protected double p;
}