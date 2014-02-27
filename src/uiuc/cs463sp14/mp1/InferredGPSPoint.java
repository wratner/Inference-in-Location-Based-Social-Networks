package uiuc.cs463sp14.mp1;

public class InferredGPSPoint extends GPSPoint {
	public InferredGPSPoint(GPSPoint homeLocation) {
		super(homeLocation);
	}

	public InferredGPSPoint(double latitude, double longitude) {
		super(latitude, longitude);
	}
}
