public class Point {
	public static Integer[] types = {0, 1, 2};
	public int type;
	public Point nNeighbor;
	public Point wNeighbor;
	public Point eNeighbor;
	public Point sNeighbor;
	public float nVel;
	public float eVel;
	public float wVel;
	public float sVel;
	public float pressure;
	public int sinInput = 30;

	public Point() {
		this.type = 0;
		clear();
	}

	public void clicked() {
		pressure = 1;
	}
	
	public void clear() {
		this.pressure = 0;
		this.nVel = 0;
		this.eVel = 0;
		this.wVel = 0;
		this.sVel = 0;
	}

	public void updateVelocity() {
		if(this.type == 0) {
				this.nVel = this.nVel - (this.nNeighbor.pressure - this.pressure);
				this.eVel = this.eVel - (this.eNeighbor.pressure - this.pressure);
				this.sVel = this.sVel - (this.sNeighbor.pressure - this.pressure);
				this.wVel = this.wVel - (this.wNeighbor.pressure - this.pressure);
			}
	}

	public void updatePressure() {
		if(this.type == 0) {
			this.pressure = (float) (this.pressure - 0.5 * (this.nVel + this.eVel + this.sVel + this.wVel));
		}else if (this.type == 2){
			sinInput += 30;
			if(sinInput >= 360) sinInput = 0;
			double radians = Math.toRadians(sinInput);
			this.pressure = (float) (Math.sin(radians));
		}
	}

	public float getPressure() {
		return pressure;
	}
}