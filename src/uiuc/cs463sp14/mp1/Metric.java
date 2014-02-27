package uiuc.cs463sp14.mp1;

public abstract class Metric
{
    /** must return a value in [0, 1] **/
    public abstract double distance(InferredGPSPoint inferred, GPSPoint actual);
}
