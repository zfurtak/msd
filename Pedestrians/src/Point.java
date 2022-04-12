import java.util.ArrayList;

public class Point {

	public ArrayList<Point> neighbors;
	public static Integer []types ={0,1,2,3};
	public int type;
	public int staticField;
	public boolean isPedestrian;
	boolean blocked = false;


	public Point() {
		type = 0;
		staticField = 100000;
		neighbors = new ArrayList<Point>();
	}
	
	public void clear() {
		staticField = 100000;
		
	}

	public boolean calcStaticField() {
		int initial_value = this.staticField;
		for(Point nei : this.neighbors){
			if(this.staticField > nei.staticField + 1){
				this.staticField = nei.staticField + 1;
			}
		}
		return initial_value != this.staticField;
	}
	
	public void move(){
		if(this.isPedestrian){
			int mini = 10000;
			Point next = this;
			for(Point nei : this.neighbors){
				if(nei.type == 2){
					next = nei;
					break;
				}
				if(nei.type == 0 && nei.staticField < mini && !nei.blocked){
					mini = nei.staticField;
					next = nei;
				}
			}
			if(next != this){
				if(next.type != 2){
					next.type = this.type;
					next.isPedestrian = true;
				}
				this.blocked = true;
				next.blocked = true;
				this.isPedestrian = false;
				this.type = 0;
			}
		}
	}

	public void addNeighbor(Point nei) {
		neighbors.add(nei);
	}
}