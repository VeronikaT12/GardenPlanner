package model;

public class GardenPlan {
    private int totalSections;
    private int added;
    private GardenSection[] layout;

    public GardenPlan(int totalSections) {
        this.totalSections = totalSections;
        this.layout = new GardenSection[totalSections];
        this.added = 0;
    }

    public GardenPlan(GardenPlan other) {
        this.totalSections = other.totalSections;
        this.added = other.added;
        this.layout = new GardenSection[totalSections];
        for (int i = 0; i < added; i++) {
            this.layout[i] = new GardenSection(other.layout[i]);
        }
    }

    public void addSection(GardenSection section) {
        if (added < totalSections) {
            layout[added++] = new GardenSection(section);
        }
    }

    public GardenSection[] getSections() {
        GardenSection[] result = new GardenSection[added];
        for (int i = 0; i < added; i++) {
            result[i] = new GardenSection(layout[i]);
        }
        return result;
    }

    public String toString() {
        double pct = ((double) added / totalSections) * 100;
        return String.format("%.1f%% of garden planned (%d of %d sections)", pct, added, totalSections);
    }
}

