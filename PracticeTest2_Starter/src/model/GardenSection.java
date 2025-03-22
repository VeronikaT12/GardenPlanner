package model;

public class GardenSection {
	private int maxCapacity;
	private Plot[] plots;
	private int usedCapacity;
	private int nop;

	public GardenSection(int maxCapacity) {
		this.maxCapacity = maxCapacity;
		this.plots = new Plot[20];
		this.usedCapacity = 0;
		this.nop = 0;
	}

	public GardenSection(GardenSection other) {
		this.maxCapacity = other.maxCapacity;
		this.usedCapacity = other.usedCapacity;
		this.nop = other.nop;
		this.plots = new Plot[other.plots.length];
		for (int i = 0; i < nop; i++) {
			this.plots[i] = new Plot(other.plots[i]);
		}
	}

	public void addPlot(String crop, int width, int length) throws OvercrowdedGardenSpaceException {
		int area = width * length;
		if (usedCapacity + area > maxCapacity) {
			throw new OvercrowdedGardenSpaceException("Too much crop area for this section.");
		}
		plots[nop++] = new Plot(crop, width, length);
					usedCapacity += area;
	}

	public String toString() {
		String result = "[";
		for (int i = 0; i < nop; i++) {
			Plot p = plots[i];
			result += String.format("%s: %.0f sq ft (%.0f' by %.0f')", 
					p.getCropType(), p.getArea(), p.getWidth(), p.getLength());
			if (i < nop - 1) {
				result += ", ";
			}
		}
		result += "]";
		return String.format("Garden section used: %.0f sq ft (%d sq ft left): %s", 
				(double) usedCapacity, maxCapacity - usedCapacity, result);
	}

	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;
		GardenSection other = (GardenSection) obj;

		if (this.maxCapacity != other.maxCapacity || this.usedCapacity != other.usedCapacity) return false;

		for (int i = 0; i < this.nop; i++) {
			int thisCount = 0;
			int otherCount = 0;
			for (int j = 0; j < this.nop; j++) {
				if (this.plots[i].equals(this.plots[j])) thisCount++;
				if (this.plots[i].equals(other.plots[j])) otherCount++;
			}
			if (thisCount != otherCount) return false;
		}
		return true;
	}
}

