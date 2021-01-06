import java.util.Random;
import java.util.ArrayList;
import java.util.List;

public class TCSS543 {
	private static class Range {
		Range(double min, double max) {
			this.Max = max;
			this.Min = min;
		}

		double Min;
		double Max;
	}

	public static void main(String[] args) {
		List<Range> ranges = new ArrayList<Range>(4);
		ranges.add(new Range(0.73, 0.82));
		ranges.add(new Range(0.61, 0.72));
		ranges.add(new Range(0.44, 0.59));
		ranges.add(new Range(0.26, 0.34));
		int maxVertices = 350;
		System.out.println("VertexCount,GroupNumber,Colors,Time");
		
		for (int numVertices = 10; numVertices <= maxVertices; numVertices += 10) {
		
			for (int groupNum = 0; groupNum < 4; groupNum++) {
				Range groupRange = ranges.get(groupNum);
				int graphCount = 100;
				List<Graph> graphs = new ArrayList<Graph>(graphCount);
				
				for (int j = 0; j < graphCount; j++) {
					double density = randomNumberGenerator(groupRange.Min, groupRange.Max);
					graphs.add(RandomGraphGenerator.BuildRandomGraph(numVertices, density));
				}

				int numColors = 0;
				long startTime = System.currentTimeMillis();
				for (Graph graph : graphs) {
					DSatur algo = new DSatur(graph);
					algo.colorGraph();
					numColors += algo.getColorCount();

				}
				long stopTime = System.currentTimeMillis();
				long elapsedTime = stopTime - startTime;

				System.out.println(String.format("%d,%d,%.3f,%.3f", numVertices, groupNum + 1,
						1.0 * numColors / graphCount, 1.0 * elapsedTime / graphCount));
		}
	}
}

	private static double randomNumberGenerator(Double Min, Double Max) {
		double randomNum = 0;
		Random random = new Random();
		randomNum = random.nextDouble() * ((Max - Min) + 0.001) + Min;
		randomNum = (double) Math.round(randomNum * 100) / 100;
		return randomNum;

	}

}
