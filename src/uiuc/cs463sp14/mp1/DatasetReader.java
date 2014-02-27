package uiuc.cs463sp14.mp1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class DatasetReader 
{
    private String edgesFilePath = null;
    private String homesFilePath = null; 
    
    private GroundTruth gt = null; 
    private SocialNetwork sn = null;

    
    public DatasetReader(String edgesFilePath, String homesFilePath)
    {
        /* TODO: implement me. */
    	this.edgesFilePath = edgesFilePath;
    	this.homesFilePath = homesFilePath;
		
    }

    /**
     * Reads, parses the specified dataset, and constructs the SocialNetwork and GroundTruth objects. 
     * The operation should correctly separate the ground truth data from the social network data, and create 'gt', and 'sn'. 
     * @return true, if the operation is successful, false otherwise.
     **/
    public boolean read()
    {
        //List<User> userInfo = new ArrayList<User>();
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ", ";
        long uId = 0;
        long temp_uId = 0;
        long temp_userNum = 0;
        double temp_lat = 0;
        double temp_long = 0;
        int temp_homeS = 0;
        sn = new SocialNetwork();
        gt = new GroundTruth();
        
        try {
        	br = new BufferedReader(new FileReader(homesFilePath));
        	while ((line = br.readLine()) != null) {
        		String[] homeLoc = line.split(cvsSplitBy);      
        		//User tempUser = new User(Long.parseLong(homeLoc[0]), Double.parseDouble(homeLoc[1]), Double.parseDouble(homeLoc[2]), Integer.parseInt(homeLoc[3]));
        		temp_userNum = Long.parseLong(homeLoc[0]);
        		if (1 >= homeLoc.length) {
        			temp_lat = -1;
        			temp_long = -1;
        			temp_homeS = 0;
        		}
        		else {
        			temp_lat = Double.parseDouble(homeLoc[1]);
        			temp_long =  Double.parseDouble(homeLoc[2]);
        			temp_homeS = Integer.parseInt(homeLoc[3]);
        		}
        		User tempUser = new User(temp_userNum, temp_lat, temp_long, temp_homeS);
        		sn.addUser(tempUser);
        		
        		//System.out.println(homeLoc[0]);
        	}
        } catch (FileNotFoundException e) {
        	e.printStackTrace();
        } catch (IOException e) {
        	e.printStackTrace();
        } finally {
        	if (br != null) {
        		try {
        			br.close();
        		} catch (IOException e) {
        			e.printStackTrace();
        		}
        	}
        }
        
        try {
        	br = new BufferedReader(new FileReader(edgesFilePath));
        	while ((line = br.readLine()) != null) {
        		String[] edgesData = line.split(cvsSplitBy);
        		uId = Long.parseLong(edgesData[0]);
        		temp_uId = Long.parseLong(edgesData[1]);
        		//userInfo.get(uId-1).addFriend(userInfo.get(temp_uId-1));
        		sn.setFriends(uId, temp_uId);
        			
        	}
        } catch (FileNotFoundException e) {
        	e.printStackTrace();
        } catch (IOException e) {
        	e.printStackTrace();
        } finally {
        	if (br != null) {
        		try {
        			br.close();
        		} catch (IOException e) {
        			e.printStackTrace();
        		}
        	}
        }
        
       
        Iterable<User> user = sn.getNodes();
        for (User i : user) {
        	//if (i.getHomeLocationGt() == null) {
        		//gt = null;
        		//break;
        	//}
        	gt.setHomeLocation(i.getId(), i.getHomeLocationGt());
        }
        
       //System.out.println(sn.getSize());
       
       // System.out.println(userInfo.get(2).getId());
       // System.out.println(userInfo.size());
        //System.out.println(userInfo.get(0).getFriendsOfFriends());
        //System.out.println(userInfo.get(0).getFriends());
        //System.out.println(userInfo.get(0).getList());
        //System.out.println(gt.getHomeLocation(1));
        return true;
    }

    public SocialNetwork getSocialNetwork()
    {
    	return sn;
    }

    public GroundTruth getGroundTruth()
    {
    	return gt;
    }
}

