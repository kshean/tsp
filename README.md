# Finding shortest routes

Finding shortest routes for given nodes. 

## Getting Started

### Prerequisites

In order to run this, you will need Docker (https://www.docker.com/get-docker) installed on your local machine.


### Download Docker image

This will download the docker image.

```
docker pull kshean/tsp:latest
```

## Running the application

1. Create a folder called ```csv``` on your machine.
2. Copy [input.csv](https://github.com/kshean/tsp/blob/master/csv/input.csv) file from this repo and copy it to your csv folder.
3. Run below: (replace ```~/csv/``` with the full path of where your csv folder resides on your machine.)

```
docker run -it --rm -v ~/csv/:/csv kshean/tsp:latest

```
After running the application, you should see the output like below (Output Sample) on your console and there should be more csv files in your csv folder. (refereces.csv etc.)

## Output Sample
```
Driver 1, Route 1:
Path: 1->2->8->3->5->9->14->10->12->6->
Total Distance: 11218.480452110454 meters
Driver 2, Route 1:
Path: 1->32->23->16->19->31->20->26->28->24->29->
Total Distance: 10471.843706067262 meters
Driver 1, Route 2:
Path: 1->33->35->45->39->49->48->46->43->47->34->
Total Distance: 4438.113677522951 meters
...
```
## Solution
Because of the limitation of the algorithm (see Limitations section), two drivers will have to make multiple trips with total number of trips = 5;

Each Kiosk has an associated id (1 - 51). To see the mapping, refer to cvs/reference.csv or [reference.csv](https://github.com/kshean/tsp/blob/master/csv/reference.csv)  after running the application.

## Assumptions
* Input file 
	* name is input.csv.
	* The input file is formatted "correctly" => use csv/input.csv from this repo.
	* The input file already includes the starting point as the first entry in the file after the header.  
* Two drivers will take multiple trips from the starting point (see Limitations).
* There will be total of 5 trips from the starting point.
* No input validation.

## Algorithm
There are two steps involved in this process.

Step 1.
The first step is grouping all the kiosks based on the coordinates. Using clustering algorithm in R, all the coordinates are grouped in four different groups depending on how close they are from each other. The purpose of this is for two drivers to cover different areas of the map so that they can deliver in parrallel and cover more areas. Therefore, the list of Kiosks, is split into 5 groups. Refer to /csv/*_range.csv to see how they are divided after running the application.
The code for this part is under R/* folder in the repo.

This takes csv/input.csv as input and outputs serveral csv files. distance matrix (*_range.csv) mapping of Kiosk to ids (reference.csv)

Step 2.
The second step is to actually solve the TSP problem using Java. Using permutations of all the routes possible and pruning branches based on certain criteira (branch-and-bound), we can find the shortest routes from all possiblities. At each recursion, the lower bound is computed using Prim's algorithm to estimate quickly to see if the branch is worth proceeding. If the lower bound is greater than the current best path, then that branch will be ignored. The code for this is under src/* folder in the repo.


## Limitations and Optimization
The complexity of the algorithm for finding the shortest routes increases significally after 10 ~ 13 nodes. When pruning the branches, more optimization may be used to solve more number of nodes efficiently. If this can be achieved, 51 nodes can be split into 2 groups (instead of 5) in step 1 of the process based on the clustering algorithm and then two drivers will only have to make one trip each following the most optimal routes.

## Technology
Java,
R (geosphere, sp packages),
Bash (putting pieces together),
Docker