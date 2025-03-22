package model;

public class Plot {
	private String cropType;
	private double width;
	private double length;
	private boolean inMetric;

	public Plot(String cropType, double width, double length) {
		this.cropType = cropType;
		this.width = width;
		this.length = length;
		this.inMetric = false;
	}

	public Plot(Plot other) {
		this.cropType = other.cropType;
		this.width = other.width;
		this.length = other.length;
		this.inMetric = other.inMetric;
	}

	public void toggleUnit() {
		this.inMetric = !this.inMetric;
	}

	public String getCropType() {
		return cropType;
	}

	public double getArea() {
		return width * length;
	}

	public double getWidth() {
		return width;
	}

	public double getLength() {
		return length;
	}

	public String toString() {
		if (inMetric) {
			double widthM = width * 0.3048;
			double lengthM = length * 0.3048;
			double areaM = widthM * lengthM;
			return String.format("A plot of %.2f square meters (%.2f m by %.2f m) for %s", 
					areaM, widthM, lengthM, cropType);
		} else {
			double areaFt = width * length;
			return String.format("A plot of %.0f square feet (%.0f' by %.0f') for %s", 
					areaFt, width, length, cropType);
		}
	}

	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;
		Plot other = (Plot) obj;
		return cropType.equals(other.cropType) && getArea() == other.getArea();
	}
}