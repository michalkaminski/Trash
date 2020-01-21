package odedorado.components.functions;

/**
 * Created by michal on 23.05.2019.
 */
public interface IFunction {

    double valueOf(double ... arg);
    double derivative(double ... arg);

}
