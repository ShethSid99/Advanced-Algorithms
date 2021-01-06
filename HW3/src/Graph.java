import java.util.*;
import java.util.Map.Entry;

public class Graph {
	private Map<String, Vertex> vertices;
	private Map<String, LinkedList<Vertex>> adjVertices;

	public Graph(Iterator<String> vertexLabel) {
		vertices = new HashMap<String, Vertex>();
		adjVertices = new HashMap<String, LinkedList<Vertex>>();
		while (vertexLabel.hasNext()) {
			String labelItem = vertexLabel.next();
			Vertex v = new Vertex(labelItem);
			vertices.put(labelItem, v);
			adjVertices.put(labelItem, new LinkedList<Vertex>());
		}
	}

	public Collection<Vertex> getVertices() {
		return vertices.values();
	}

	public Collection<Vertex> getAdjacentVertices(Vertex v) {
		return getAdjacentVertices(v.getLabel());
	}

	public Collection<Vertex> getAdjacentVertices(String vertexLabel) {
		return adjVertices.get(vertexLabel);
	}

	private void validateVertex(String vertexLabel) {
		if (!vertices.containsKey(vertexLabel))
			throw new RuntimeException("Illegal vertex: " + vertexLabel);

	}

	public void addEdge(String from, String to) {
		validateVertex(from);
		validateVertex(to);
		adjVertices.get(from).add(vertices.get(to));
		adjVertices.get(to).add(vertices.get(from));
	}

	public void computeAdjacentDegree() {
		Set<Entry<String, LinkedList<Vertex>>> vertexList = adjVertices
				.entrySet();
		for (Entry<String, LinkedList<Vertex>> i : vertexList) {
			String vertexLabel = i.getKey();
			LinkedList<Vertex> adjList = i.getValue();
			int adjDegree = adjList.size();
			vertices.get(vertexLabel).setAdjDegree(adjDegree);
		}
	}

	public void displayGraph() {
		System.out.println("Graph");
		Set<Entry<String, LinkedList<Vertex>>> adjList = adjVertices.entrySet();
		for (Entry<String, LinkedList<Vertex>> i : adjList) {
			System.out.print(i.getKey());
			System.out.print("-->");
			System.out.print(i.getValue());
			Vertex v = vertices.get(i.getKey());
			System.out.print(" adj degree --> " + v.getAdjDegree());
			System.out.print(", sat degree --> " + v.getSatDegree());
			System.out.println();
		}
	}

	@Override
	public String toString() {
		return "graph";
	}

}
