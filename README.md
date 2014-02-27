Inference-in-Location-Based-Social-Networks
===========================================

Given real anonymized datasets containing friendship information, as well as the home location of a susbset of the users - some but not all the users have shared the location of their homes on the social network. This program leverages the available information to infer the home location of users who have chosen not to share it. 

The simple algorithm looks at the friends of a user who does not share their home location. It utilizes the friends shared home locations to compute the centroid on a flat rectangular projection as an approximation of the users home location.

The custom algorithm tries to improve the accuracy of the simple algorithm. For more information regarding the custom algorithm, you can read the report.pdf document. 
