package Classes;

import java.util.ArrayList;

public class Elevator implements Runnable {

    private final int id;
    private int currentFloor = 1;
    private char direction = 'N';
    private ArrayList<QueryElevator> queries;
    private boolean isArrivedToSource = false;

    public Elevator(int id) {
        this.id = id;
        queries = new ArrayList<>();
    }

    public void addQuery(QueryElevator query) {
        queries.add(query);
    }

    public void move() {
        if (isInactive()) {
            direction = 'N';
            System.out.printf("[ELEVATOR]. Elevator #%d is standing on the floor %d\n", id, currentFloor);
            return;
        }

        int querySourceFloor = queries.getFirst().getFloorSource();
        int queryDestinationFloor = queries.getFirst().getFloorDestination();

        if (!isArrivedToSource) {
            if (currentFloor < querySourceFloor) {
                direction = 'U';
                ++currentFloor;
            }
            else if (currentFloor > querySourceFloor) {
                direction = 'D';
                --currentFloor;
            }
            else {
                System.out.printf("[ELEVATOR]. Elevator #%d took passenger from %d floor\n",
                        id, querySourceFloor);
                isArrivedToSource = true;
            }
        }
        else {
            if (currentFloor < queryDestinationFloor) {
                direction = 'U';
                ++currentFloor;
            }
            else if (currentFloor > queryDestinationFloor) {
                direction = 'D';
                --currentFloor;
            }
            else {
                System.out.printf("[ELEVATOR]. Elevator #%d delivered passenger from %d to %d floor\n",
                        id, querySourceFloor, queryDestinationFloor);
                queries.removeFirst();
                isArrivedToSource = false;
            }
        }

        System.out.printf("[ELEVATOR]. Elevator #%d on the floor %d [%c]\n", id, currentFloor, direction);
    }

    public boolean isInactive() {
        return queries.isEmpty();
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public char getDirection() {
        return direction;
    }

    @Override
    public void run() {
        try {
            while (true) {
                move();
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
