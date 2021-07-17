package pdes.components;

public interface IpdeOneIndigrient {

    /**
     * partial derivative du/dt value x with respect to t time
     * https://www.youtube.com/watch?v=ly4S0oi3Yz8&t=6s&ab_channel=3Blue1Brown
     */
    public double getdUdT();

    /**
     * change in x value with respect to x dimension's second derivative
     * https://www.youtube.com/watch?v=ly4S0oi3Yz8&t=6s&ab_channel=3Blue1Brown
     */

//    public double getDx(double[][] space2d, int x);
//    public double getDy(double[][] space2d, int y);

    public void calculateDuDt(double[][] u, int x, int y);
    public void calculateDuDt(double[][][]u, int x, int y, int z);
    public void calculateDuDt(double[][] u, double[][] v, int x, int y);
    public void calculateDuDt(double[][][] u, double[][][] v, int x, int y, int z);

    }
