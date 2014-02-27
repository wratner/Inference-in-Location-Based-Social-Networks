package uiuc.cs463sp14.mp1;

import java.util.ArrayList;
import java.util.List;


public class GroundTruth
{
    
	private List<GPSPoint> gtPoints;
	public GroundTruth () {
		gtPoints = new ArrayList<GPSPoint>();
	}
    /**
     * @return the (true) home location of user 'userId'.
     * @remark If no ground truth is available, you must throw an exception.
     * @see {@link uiuc.cs463sp14.mp1.test.MP1Tests#testNoGroundTruthDataset2()}
     **/
    public GPSPoint getHomeLocation(long userId)
    {
        /* TODO: implement me. */
    	if (gtPoints.get((int)(userId-1)) == null)
    	{
    		throw new IllegalArgumentException("No Ground Truth in Dataset2");
    	}
        return gtPoints.get((int)(userId-1));
    }
    
    /** Sets 'home' as user 'userId' home location. **/
    public void setHomeLocation(long userId, GPSPoint home)
    {
        /* TODO: implement me. */
    	gtPoints.add(home);
    }
}
