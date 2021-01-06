import java.util.ArrayList;

public class RandomGraphGenerator {
	public static Graph BuildRandomGraph(int numVertices, double density) {

		ArrayList<String> input = new ArrayList<>();
		for (Integer i = 1; i <= numVertices; i++) {
			input.add(i.toString());
		}

		Graph myGraph = new Graph(input.iterator());

		for (Integer i = 1; i <= numVertices; i++) {
			for (Integer j = i + 1; j <= numVertices; j++) {

				double randomNum = Math.random();
				randomNum = (double) Math.round(randomNum * 100) / 100;

				if (randomNum < density) {
					myGraph.addEdge(i.toString(), j.toString());

				}
			}
		}
		myGraph.computeAdjacentDegree();

		return myGraph;

	}

}
