package tsp;

import java.util.Map;

public class Node {
	
	private Integer id;
	private String address;
	private String name;
	private Map<Integer, Double> distance;
	private boolean isVisited;
	private Integer parent;
	private Double primDistance;
	
	public Node() {
		
	}
	
	public Node(Integer id_in, String address_in, String name_in, Map<Integer, Double> map_in) {
		this.id = id_in;
		this.address = address_in;
		this.name = name_in;
		this.distance = map_in;
		this.primDistance = -1.0;
		this.isVisited = false;
		this.parent = null;
	}
	public String getAddress() {
		return this.address;
	}
	public String getName() {
		return this.name;
	}
	public Integer getId() {
		return this.id;
	}
	
	public Map<Integer, Double> getDistanceMap() {
		return distance;
	}

	public void setIsVisted(boolean flag) {
		this.isVisited = flag;
	}
	
	public boolean getIsVisited() {
		return this.isVisited;
	}
	
	public void setParent(Integer p) {
		this.parent = p;
	}
	
	public Integer getParent() {
		return this.parent;
	}
	
	public Double getPrimDistance() {
		return this.primDistance;
	}
	
	public void setPrimDistance(Double d) {
		this.primDistance = d;
	}
}
