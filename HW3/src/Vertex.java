
public class Vertex implements Comparable<Vertex> {
	private String label;
	private int adjDegree;
	private int satDegree;
	private int color;

	public Vertex(String label){
		this.label = label;
		adjDegree=0;
		satDegree=0;
		color =0;
		
	}


	@Override
	public int compareTo(Vertex o) {
			if(this.satDegree == o.satDegree){
				if(this.adjDegree > o.adjDegree){
					return -1;
				} else if (this.adjDegree < o.adjDegree) {
					return 1;
				}
				else return 0;
			}
			else if(this.satDegree >  o.satDegree){
				return -1;
			}
			else{
				return 1;
			}
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public int getAdjDegree() {
		return adjDegree;
	}
	public void setAdjDegree(int adjDegree) {
		this.adjDegree = adjDegree;
		
	}
	public int getSatDegree() {
		return satDegree;
	}
	public void setSatDegree(int satDegree) {
		this.satDegree = satDegree;
	}
	public int getColor() {
		return color;
	}
	public void setColor(int color) {
		this.color = color;
	}

	public String toString() {
		return label;
	}
}
