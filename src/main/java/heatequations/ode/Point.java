package heatequations.ode;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import odes.components.functions.IFunction;

@Getter
@Setter
@AllArgsConstructor
public class Point {
    double x;
    double y;
    double z;

    double Tx;

    double dTx; //change in T caused by dx      dT/dx (x, t)
    double dTt; //change in T caused by dt      dT/dt (x, t)


    public Point(double x, double Tx) {
        this.x = x;
        this.Tx = Tx;
    }

    public Point(double x, double y, double Tx) {
        this.x = x;
        this.y = y;
        this.Tx = Tx;
    }


    //zaleznosc miedzy pierwsza pochodna czasu a druga pochodna polozenia, sa proporcjonalne
    //  dT/dt(x,t)=alfa*(d2T/dx2)(x,t)
    // intuicja jest taka: w miejscach gdzie jest bardziej wygieta krzywa bardziej sie zmienia temperatura
    // w kierunku tej krzywizny
    // dla wielu wymiarow dT/dt(x,y,z,t)=alfa*((d2T/dx2)(x,t) + (d2T/dy2)(z,y,z,t) + (d2T/dz2)(z,y,z,t))
    // dal punktu T2 dT2/dt=(alfa/2)([T3-T2]-[T2-T1])
    // dla wartosci dyskretnych punktow na precie definiuje sie pojecie roznica roznic dT2/dt = (alpha/2) * delta delta T1
    // roznica roznic dla ciaglych wartosci jest po prostu druga pochodna
    // dT/t = alfa * d2T/dx2
    // jak szybko zmienia sie sie rate of change
    // w Calculus zapisuje sie druga pochodna nastepujaco d(dT/dx)/dx = d2T/dx2
    //

}
