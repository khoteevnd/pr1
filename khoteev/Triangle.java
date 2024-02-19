package khoteev;

import java.util.Objects;

public class Triangle {
	private Point A; private Point B; private Point C;
	private Float sideAB; private Float sideBC; private Float sideCA;
	private Float perimetr; private Float area;

	public Triangle(Point A, Point B, Point C) { 
		this.A = A; this.B = B; this.C = C;
		this.setSideAB(); this.setSideBC(); this.setSideCA();
		this.setPerimetr(); this.setArea();
	}

	public Triangle(Float min, Float max){
		this.A = new Point(this.generateRandomFloatBetween(min, max), generateRandomFloatBetween(min, max));
		this.B = new Point(this.generateRandomFloatBetween(min, max), generateRandomFloatBetween(min, max));
		this.C = new Point(this.generateRandomFloatBetween(min, max), generateRandomFloatBetween(min, max));
		this.setSideAB(); this.setSideBC(); this.setSideCA();
		this.setPerimetr(); this.setArea();
	}

	public void setA(Point A) { this.A = A; recalculateAfterSetPoint();}
	public void setB(Point B) { this.B = B; recalculateAfterSetPoint();}
	public void setC(Point C) { this.C = C; recalculateAfterSetPoint();}

	public Point getA() { return A; }
	public Point getB() { return B; }
	public Point getC() { return C; }

	protected void recalculateAfterSetPoint() {
		StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
		StackTraceElement stackTraceElement = stackTrace[2];
		switch (stackTraceElement.getMethodName()) {
			case ("setA"): this.setSideAB(); this.setSideCA(); this.setPerimetr(); this.setArea(); break;
			case ("setB"): this.setSideAB(); this.setSideBC(); this.setPerimetr(); this.setArea(); break;
			case ("setC"): this.setSideBC(); this.setSideCA(); this.setPerimetr(); this.setArea(); break;
			default: break;
		}
	}

	@Override
	public String toString() {
		return getClass().getName() + "\n"
			+ "A = { " + this.getA().toString() + " }, " + "\n"
			+ "B = { " + this.getB().toString() + " }, " + "\n"
			+ "C = { " + this.getC().toString() + " }, " + "\n"
			+ "Side AB = " + this.getSideAB() + ", " + "\n"
			+ "Side BC = " + this.getSideBC() + ", " + "\n"
			+ "Side CA = " + this.getSideCA() + ", " + "\n"
			+ "perimetr = " + this.getPerimetr() + ", " + "\n"
			+ "area = " + this.getArea() + "." + "\n"
			;
	}
	@Override
	public boolean equals(Object obj) {
		if(this == obj){
			return true;
		}
		if(obj instanceof Triangle){
			Triangle t = (Triangle) obj;
			if(
				Objects.equals(this.getA(), t.getA()) &&
				Objects.equals(this.getB(), t.getB()) &&
				Objects.equals(this.getC(), t.getC()) &&
				Objects.equals(this.getSideAB(), t.getSideAB()) &&
				Objects.equals(this.getSideBC(), t.getSideBC()) &&
				Objects.equals(this.getSideCA(), t.getSideCA()) &&
				Objects.equals(this.getPerimetr(), t.getPerimetr()) &&
				Objects.equals(this.getArea(), t.getArea())
			) {
				return true;
			}
			return false;
		}
		return false;
	}
	@Override
	public int hashCode(){
		final int prime = 31;
		int result = 1;
		result = prime * result + this.getA().hashCode();
		result = prime * result + this.getB().hashCode();
		result = prime * result + this.getC().hashCode();
		result = prime * result + ((Float)this.getSideAB()).hashCode();
		result = prime * result + ((Float)this.getSideBC()).hashCode();
		result = prime * result + ((Float)this.getSideCA()).hashCode();
		result = prime * result + ((Float)this.getPerimetr()).hashCode();
		result = prime * result + ((Float)this.getArea()).hashCode();
		return result;
	}

	protected void setSideAB() { this.sideAB = calculateSideAB(); }
	protected void setSideBC() { this.sideBC = calculateSideBC(); }
	protected void setSideCA() { this.sideCA = calculateSideCA(); }

	public Float getSideAB() { return this.sideAB; }
	public Float getSideBC() { return this.sideBC; }
	public Float getSideCA() { return this.sideCA; }

	protected Float calculateSideAB() { return calculateSide(A, B); }
	protected Float calculateSideBC() { return calculateSide(B, C); }
	protected Float calculateSideCA() { return calculateSide(C, A); }

	protected Float calculateSide(Point p1, Point p2) { return (float)Math.sqrt( Math.pow((p2.getX() - p1.getX()), 2) + Math.pow((p2.getY() - p1.getY()), 2)); }
	
	protected void setPerimetr() { this.perimetr = calculatePerimetr(); }
	public Float getPerimetr() { return perimetr; }

	protected Float calculatePerimetr() { return getSideAB() + getSideBC() + getSideCA(); }

	protected void setArea() { this.area = calculateArea(); }
	public Float getArea() { return area; }

	protected Float calculateArea() {
		Float semiperimeter = (this.getPerimetr()/2);
		return (float) Math.sqrt( semiperimeter * (semiperimeter-getSideAB()) * (semiperimeter-getSideBC()) * (semiperimeter-getSideCA()) );
	}

	protected Float generateRandomFloatBetween(Float min, Float max) {
		return (float) ((Math.random() * (max - min)) + min);
	}

	public boolean isValid() { return (this.getArea() == 0) ? false : true; }
}
