package tsp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class Tsp {
	
	public void printPermutation(String fileName) {
		
		FileReader f = new FileReader();
		Queue<Node> unvisitedCities = f.readFile(fileName);
		
		List<Node> currentBest = new ArrayList<Node>();
		
		List<Node> path = new ArrayList<Node>();
		path.add(unvisitedCities.poll());
		
		permute(unvisitedCities, path, currentBest);
		System.out.println("Path is: ");
		currentBest.forEach(p->System.out.print(p.getName() + "->"));
		System.out.println();
		System.out.println("Total Distance (meters) is: ");
		System.out.println(calculateCurrentDistance(currentBest));		
	}
	
	public void permute(Queue<Node> unvisiedCities, List<Node> path, List<Node> currentBest) {
		
		if(!promisingPath(unvisiedCities, path, currentBest)) {
			return;
		}		
		else if(unvisiedCities.isEmpty()) {
			if(isBetterThanCurrentBest(path, currentBest)) {
				updateCurrentBest(path, currentBest);
			}
		}
		for(int i = 0; i < unvisiedCities.size(); ++i){
			path.add(unvisiedCities.poll());
			permute(unvisiedCities, path, currentBest);
			unvisiedCities.add(path.remove(path.size() - 1));
		}

	}
	
	
	private void updateCurrentBest(List<Node> path, List<Node> currentBest) {
		currentBest.clear();
		path.forEach((node)->{
			currentBest.add(node);
		});
	}

	private boolean isBetterThanCurrentBest(List<Node> path, List<Node> currentBest) {
		if(calculateCurrentDistance(path) < calculateCurrentDistance(currentBest)) {
			return true;
		}else {
			return false;
		}

	}

	private Double calculateCurrentDistance(List<Node> path) {
		if(path.size() == 0) {
			return Double.MAX_VALUE;
		}
		Double currentDistance = 0.0;
		for(int i = 0; i < path.size() - 1; i++) {
			Integer nextCity = path.get(i + 1).getId();
			currentDistance += path.get(i).getDistanceMap().get(nextCity);
		}
		return currentDistance;
	}
	

	private boolean promisingPath(Queue<Node> unvisiedCities, List<Node> path, List<Node> currentBest) {
//		if(isBetterThanCurrentBest(path, currentBest)) {
//			return true;
//		}else {
		if(unvisiedCities.size() > 2) {
			Double lower_bound = calculateLowerBound(unvisiedCities, path);
			//System.out.println(lower_bound);
			if(calculateCurrentDistance(currentBest) < lower_bound) {
				return false;
			}else {
				return true;
			}
		}else {
			return true;
		}
		//}
	}
	
	
	public Double calculateLowerBound(Queue<Node> unvisitedCities, List<Node> path) {
		Double currentDistance = calculateCurrentDistance(path);
		Map<Integer, Node> primVector = new HashMap<Integer, Node>();
		List<Integer> indexVector = new ArrayList<Integer>();
		unvisitedCities.forEach((node)->{
			Node n = new Node(node.getId(), node.getAddress(), node.getName(), node.getDistanceMap());
			primVector.put(node.getId(), n);
			indexVector.add(node.getId());
		});
		
		Integer numEdges = 0;
		Double currentShortestDistance = 0.0;
		Integer counter = 0;
		
		Double mstDistance = 0.0;
		
		Integer currentIndex = indexVector.get(0);
		Integer shoretstIndex = currentIndex;

		
		primVector.get(currentIndex).setIsVisted(true);
		primVector.get(currentIndex).setParent(currentIndex);
		primVector.get(currentIndex).setPrimDistance(0.0);
		
		while(numEdges < primVector.size() - 1) {
			primVector.get(currentIndex).setIsVisted(true);
			for(int i = 0; i < primVector.size(); i++) {
				Integer index = indexVector.get(i);
				if(primVector.get(index).getIsVisited() || index == currentIndex) continue;
				Double distance = primVector.get(currentIndex).getDistanceMap().get(index);
				if(counter == 0) currentShortestDistance = distance;
				
				if(currentShortestDistance < primVector.get(index).getPrimDistance() && primVector.get(index).getPrimDistance() > 0
						|| primVector.get(index).getPrimDistance() == -1.0) {
					primVector.get(index).setPrimDistance(currentDistance);
					primVector.get(index).setParent(currentIndex);
				}
				if(primVector.get(index).getPrimDistance() <= currentShortestDistance) {
					currentDistance = primVector.get(index).getPrimDistance();
					shoretstIndex = index;
				}
				
				counter++;
			}
			
			mstDistance += currentShortestDistance;
			numEdges++;
			counter = 0;
			currentIndex = shoretstIndex;
		}
		
		return mstDistance;
		
	}
	
}
