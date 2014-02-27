package uiuc.cs463sp14.mp1;

import java.util.HashSet;
import java.util.Set;

/**
 * Represents a user in the social network. Each user has a unique non-negative
 * integer id, and a home location (i.e., GPSPoint). Not all users have chosen
 * to share their home locations, so it may or may not be known.
 * 
 * @see {@link #getId()}, {@link #getHomeLocation()},
 *      {@link #isHomeLocationKnown()}
 **/
public class User {
	private Set<User> friends;
	private long userId;
	private double latitude;
	private double longitude;
	private int homeShared;

	public User(long userId, double latitude, double longitude, int homeShared) {
		friends = new HashSet<User>();
		this.userId = userId;
		this.latitude = latitude;
		this.longitude = longitude;
		this.homeShared = homeShared;
	}

	/** @return the unique userId associated with this user. **/
	public long getId() {
		return userId;
	}

	/**
	 * @return a GPSPoint representing the home location of this user. If this
	 *         user has not shared his/her home location, <i>null</i> should be
	 *         returned
	 **/
	public GPSPoint getHomeLocation() {
		if (isHomeLocationKnown()) {
			GPSPoint homeLoc = new GPSPoint(latitude, longitude);
			return homeLoc;
		} else
			return null;
	}

	public GPSPoint getHomeLocationGt() {
		if (latitude == -1 && longitude == -1) {
			return null;
		}
		GPSPoint homeLocGt = new GPSPoint(latitude, longitude);
		return homeLocGt;
	}

	/** @return true, if this user share his/her home location, false otherwise. **/
	public boolean isHomeLocationKnown() {
		if (homeShared == 1)
			return true;
		else
			return false;
	}

	/** Returns the set of friends of friends of this user. **/
	public Set<User> getFriendsOfFriends() {
		Set<User> friendsOfFriends = new HashSet<User>();

		for (User friend : getFriends()) {
			friendsOfFriends.addAll(friend.getFriends());
		}
		return friendsOfFriends;
	}

	/**
	 * @return an unordered set of this user's friends. <br>
	 *         </br> Note: <i>null</i> should never be returned; if this user
	 *         has no friends, return an empty set.
	 **/
	public Set<User> getFriends() {
		return friends;
	}

	/**
	 * Add 'friend' to this user's friends. If 'friend' is already in the user's
	 * friends list, do nothing.
	 **/
	public void addFriend(User friend) {
		if (!friends.contains(friend)) {
			friends.add(friend);
		}
	}

}
