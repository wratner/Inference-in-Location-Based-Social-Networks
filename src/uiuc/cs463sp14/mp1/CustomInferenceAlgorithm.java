package uiuc.cs463sp14.mp1;

import java.util.HashSet;
import java.util.Set;

public class CustomInferenceAlgorithm extends InferenceAlgorithm {

	public CustomInferenceAlgorithm(SocialNetwork network) {
		super(network);
	}

	@Override
	public InferredGPSPoint inferHomeLocation(long userId) {

		InferredGPSPoint h;
		Set<User> f = new HashSet<User>();
		double a = 0;
		if (getNetwork().getNodeById(userId).isHomeLocationKnown()) {
			return h = new InferredGPSPoint(getNetwork().getNodeById(userId)
					.getHomeLocation());
		} else {
			// a = getNetwork().avgNumberOfFriends();
			// a = (double)Math.ceil(a);
			f = getNetwork().friendsWithSharedHomeLocationsAndWithinXKm(50,
					getNetwork().getNodeById(userId));
			// h = getNetwork().geographicCenterOfHomes(f);
			h = getNetwork().geographicCenterOfHomesCircle(f);
			return h;
		}

	}

}
