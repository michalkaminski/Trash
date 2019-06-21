package n.draw.f;

/**
 * Created by michal on 23.05.2019.
 */
public interface Function {

    double valueOf(double ... arg);
    double derivative(double ... arg);

}
