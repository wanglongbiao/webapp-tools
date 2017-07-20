package cn.wang.gis;

public class Position {
	public double x;
	public double y;

	public Position() {

	}

	public Position(double x, double y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public String toString() {
		return "x:" + x + "\ny:" + y;
	}
}
