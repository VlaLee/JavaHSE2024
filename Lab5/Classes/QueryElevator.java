package Classes;

public class QueryElevator {

    private final int floorSource;
    private final int floorDestination;

    public QueryElevator(int floorSource, int floorDestination) {
        this.floorSource = floorSource;
        this.floorDestination = floorDestination;
    }

    public int getFloorSource() {
        return floorSource;
    }

    public int getFloorDestination() {
        return floorDestination;
    }
}
