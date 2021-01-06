import java.util.SortedSet;
import java.util.TreeSet;
import java.util.Collection;
import java.util.PriorityQueue;

public class DSatur {
	private Graph graph;
	private SortedSet<Integer> colors;
	private Collection<Vertex> colorlessVertices;

	public DSatur(Graph graph) {
		this.graph = graph;
		colors = new TreeSet<Integer>();
		colorlessVertices = graph.getVertices();
	}

	public int getColorCount() {
		return colors.size();
	}

	private void saturationDegreeCalculator(Collection<Vertex> inputVertices) {

		for (Vertex v : inputVertices) {
			Collection<Vertex> adjacentVertices = graph.getAdjacentVertices(v);
			SortedSet<Integer> adjColorCodes = new TreeSet<Integer>();
			for (Vertex adjVertex : adjacentVertices) {
				int color = adjVertex.getColor();
				if (color > 0) {
					adjColorCodes.add(adjVertex.getColor());
				}
			}
			v.setSatDegree(adjColorCodes.size());
		}
	}

	public void colorGraph() {

		while (colorlessVertices.size() > 0) {
			PriorityQueue<Vertex> PQNode = new PriorityQueue<Vertex>(colorlessVertices);
			Vertex selectedVertex = PQNode.remove();

			Collection<Vertex> adjacentVertices = graph.getAdjacentVertices(selectedVertex);
			SortedSet<Integer> adjColorCodes = new TreeSet<Integer>();
			for (Vertex adjVertex : adjacentVertices) {
				int color = adjVertex.getColor();
				if (color > 0) {
					adjColorCodes.add(adjVertex.getColor());
				}
			}

			
			SortedSet<Integer> tempColors = new TreeSet<Integer>(colors);
			tempColors.removeAll(adjColorCodes);
			int newColor;
			if (tempColors.size() > 0) {
				newColor = tempColors.first();
			} else {
				newColor = colors.size() + 1;
				colors.add(newColor);
			}
			selectedVertex.setColor(newColor);


			colorlessVertices.remove(selectedVertex);

			saturationDegreeCalculator(graph.getAdjacentVertices(selectedVertex));
		}

	}

	public void displayPQ(PriorityQueue<Vertex> PQNode) {
		while (PQNode.size() >= 1) {
			Vertex v = PQNode.remove();
			System.out.println(
					String.format("Label: %s, Adj: %d, Sat: %d", v.getLabel(), v.getAdjDegree(), v.getSatDegree()));
		}

	}

}
