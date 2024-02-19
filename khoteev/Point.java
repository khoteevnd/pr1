package khoteev;

import java.util.Objects;

public class Point {
	private Float x;
	private Float y;

	public Point(Float x, Float y) { setX(x); setY(y); }
	public void setX(Float x) { this.x = x; }
	public void setY(Float y) { this.y = y; }
	public Float getX() { return this.x; }
	public Float getY() { return this.y; }
	@Override
	public String toString() { return "x = " + getX() + ", " + "y = " + getY(); }
	@Override
	public boolean equals(Object obj) {
		if(this == obj){
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		if(obj instanceof Point) {
			Point p = (Point) obj;
			if(
				Objects.equals(this.getX(), p.getX()) && 
				Objects.equals(this.getY(), p.getY())
			) {
				return true;
			}
		}
		return false;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Float) this.getX()).hashCode();
		result = prime * result + ((Float) this.getY()).hashCode();
		return result;
	}
}
