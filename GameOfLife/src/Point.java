import java.util.ArrayList;

public class Point {
	private ArrayList<Point> neighbors;
	private int currentState;
	private int nextState;
	private int numStates = 6;
	private int mode;
	
	public Point(int mode) {
		this.currentState = 0;
		this.nextState = 0;
		this.mode = mode;
		this.neighbors = new ArrayList<Point>();
	}

	public void clicked() {
		currentState=(++currentState)%numStates;	
	}
	
	public int getState() {
		return currentState;
	}

	public void setState(int s) {
		currentState = s;
	}

	public void calculateNewState() {
		//TODO: insert logic which updates according to currentState and
		if(mode == 0){
			if(this.getState() == 0 && this.countNumberOfAliveNeighbours() == 3){
				this.nextState = 1;
			}else if(this.getState() == 1 && this.countNumberOfAliveNeighbours() != 3 && this.countNumberOfAliveNeighbours() != 2){
				this.nextState = 0;
			}else{
				this.nextState = this.currentState;
			}
		}else if(mode == 1){
			if(this.currentState > 0){
				this.nextState = this.currentState - 1;
			}else if(this.currentState == 0 && this.neighbors.size() > 0 && this.neighbors.get(0).currentState > 0){
				this.nextState = 6;
			}
		}
	}

	public void changeState() {
		currentState = nextState;
	}
	
	public void addNeighbor(Point nei) {
		neighbors.add(nei);
	}
	
	//TODO: write method counting all active neighbors of THIS point
	public int countNumberOfAliveNeighbours(){
		int count = 0;
		for (Point neighbor: neighbors) {
			if(neighbor.getState() != 0){
				count ++;
			}
		}
		return count;
	}

	public void drop(){
		if(Math.random() < 0.05){
			this.nextState = 6;
		}
	}
}
