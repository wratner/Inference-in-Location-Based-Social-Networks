package uiuc.cs463sp14.mp1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Represents the social network, i.e., a set of {@link User}s connected by a
 * friendship graph.
 **/
public class SocialNetwork {
	private List<User> userInfo = null;

	/** @return the number of users in the social network **/
	public SocialNetwork() {
		userInfo = new ArrayList<User>();
	}

	public long getSize() {
		return userInfo.size();
	}

	/** @return the {@link User} object of user 'userId' **/
	public User getNodeById(long id) {
		return userInfo.get((int) (id - 1));
	}

	/** @return an iterable collection of the {@link User}s of the social network **/
	public Iterable<User> getNodes() {
		return Collections.unmodifiableList(userInfo);
	}

	/** Add user 'user' to the social network. **/
	public void addUser(User user) {
		userInfo.add(user);
	}

	/** Returns set of friends of user that chose to share their location **/
	public Set<User> friendsWithSharedHomeLocations(User subject) {
		Set<User> f = new HashSet<User>();
		for (User friend : subject.getFriends()) {
			if (friend.isHomeLocationKnown()) {
				f.add(friend);
			}
		}
		return f;
	}

	public Set<User> friendsWithSharedHomeLocationsAndWithinXKm(
			double threshold, User subject) {
		Set<User> group = new HashSet<User>();
		// Set<User> group2 = new HashSet<User>();
		User temp = null;
		double smallest = threshold;
		for (User i : subject.getFriends()) {
			if (i.isHomeLocationKnown()) {
				temp = i;
				break;
			}

		}
		if (temp == null) {
			for (User j : subject.getFriendsOfFriends()) {
				if (j.isHomeLocationKnown()) {
					temp = j;
					break;
				}
			}
			for (User k : subject.getFriendsOfFriends()) {
				if (k.isHomeLocationKnown()) {
					if ((temp.getHomeLocation().distanceTo(k.getHomeLocation()) < smallest)
							&& (temp != k)) {
						smallest = temp.getHomeLocation().distanceTo(
								k.getHomeLocation());
						temp = k;
					}
				}
			}
			for (User friend : subject.getFriends()) {
				if (friend.isHomeLocationKnown()) {
					if (temp.getHomeLocation().distanceTo(
							friend.getHomeLocation()) < threshold) {
						group.add(friend);
					}
				}
				if (subject.getFriends().size() < 10) {
					for (User friend2 : subject.getFriendsOfFriends()) {
						if (friend2.isHomeLocationKnown()) {
							if (temp.getHomeLocation().distanceTo(
									friend2.getHomeLocation()) < threshold) {
								group.add(friend2);
							}
						}
					}
				}
			}
			return group;
		} else {
			for (User temp_f : subject.getFriends()) {
				if (temp_f.isHomeLocationKnown()) {
					if ((temp.getHomeLocation().distanceTo(
							temp_f.getHomeLocation()) < smallest)
							&& (temp != temp_f)) {
						smallest = temp.getHomeLocation().distanceTo(
								temp_f.getHomeLocation());
						temp = temp_f;
					}
				}
			}
			for (User friend : subject.getFriends()) {
				if (friend.isHomeLocationKnown()) {
					if (temp.getHomeLocation().distanceTo(
							friend.getHomeLocation()) < threshold) {
						group.add(friend);
					}
				}
				if (subject.getFriends().size() < 10) {
					for (User friend2 : subject.getFriendsOfFriends()) {
						if (friend2.isHomeLocationKnown()) {
							if (temp.getHomeLocation().distanceTo(
									friend2.getHomeLocation()) < threshold) {
								group.add(friend2);
							}
						}
					}
				}

			}
			return group;
		}
	}

	public double avgNumberOfFriends() {
		double avg = 0;
		for (User i : userInfo) {
			avg += i.getFriends().size();
		}
		return avg / (userInfo.size());
	}

	/** Returns geographic center of homes for simple algo **/
	public InferredGPSPoint geographicCenterOfHomes(Set<User> friends) {

		GPSPoint p;
		InferredGPSPoint center;
		double tempLat = 0;
		double tempLong = 0;

		for (User i : friends) {
			p = new GPSPoint(i.getHomeLocation());
			tempLat += p.getLatitude();
			tempLong += p.getLongitude();
		}

		center = new InferredGPSPoint(tempLat / friends.size(), tempLong
				/ friends.size());
		return center;
	}

	public InferredGPSPoint geographicCenterOfHomesCircle(Set<User> friends) {
		InferredGPSPoint center;
		double tempLat = 0;
		double tempLong = 0;
		double Lat = 0;
		double Lon = 0;
		double x = 0;
		double y = 0;
		double z = 0;

		for (User i : friends) {
			tempLat = i.getHomeLocation().getLatitudeInRads();
			tempLong = i.getHomeLocation().getLongitudeInRads();
			x += Math.cos(tempLat) * Math.cos(tempLong);
			y += Math.cos(tempLat) * Math.sin(tempLong);
			z += Math.sin(tempLat);

		}
		x = x / friends.size();
		y = y / friends.size();
		z = z / friends.size();

		Lat = Math.atan2(z, Math.sqrt((x * x) + (y * y)));
		Lon = Math.atan2(y, x);

		Lat = Lat * 180 / Math.PI;
		Lon = Lon * 180 / Math.PI;
		center = new InferredGPSPoint(Lat, Lon);
		return center;
	}

	/**
	 * Sets the friendship of two (existing) social network users (i.e.,
	 * 'userId' and 'friendId')
	 * 
	 * @return true, if the friendship was set, false otherwise.
	 **/
	public boolean setFriends(long userId, long friendId) {
		if (userId == friendId) {
			return false;
		}

		User user = (User) getNodeById(userId);
		User friend = (User) getNodeById(friendId);

		if (user == null || friend == null) {
			return false;
		}

		user.addFriend(friend);
		friend.addFriend(user);

		return true;
	}
}
