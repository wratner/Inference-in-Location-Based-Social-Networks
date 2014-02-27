package uiuc.cs463sp14.mp1;

import java.util.HashSet;
import java.util.Set;

public class SimpleInferenceAlgorithm extends InferenceAlgorithm {
	public SimpleInferenceAlgorithm(SocialNetwork network) {
		super(network);
	}

	public InferredGPSPoint inferHomeLocation(long userId) {
		InferredGPSPoint h;
		Set<User> f = new HashSet<User>();
		if (getNetwork().getNodeById(userId).isHomeLocationKnown()) {
			return h = new InferredGPSPoint(getNetwork().getNodeById(userId)
					.getHomeLocation());
		} else {
			f = getNetwork().friendsWithSharedHomeLocations(
					getNetwork().getNodeById(userId));
			h = getNetwork().geographicCenterOfHomes(f);
			return h;
		}

	}

}
