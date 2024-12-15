package Classes;

import Exceptions.IllegalFloorNumberException;
import Exceptions.IllegalQueryNumberException;

import java.util.Random;

public class QueryElevatorGenerator implements Runnable {

    private final ElevatorSystem elevatorSystem;
    private final int numberOfFloors;
    private final int numberOfQueries;
    private final Random random;

    public QueryElevatorGenerator(ElevatorSystem elevatorSystem,
                                  int numberOfFloors, int numberOfQueries) {

        if (elevatorSystem == null) {
            throw new NullPointerException("Classes.ElevatorSystem must be not null");
        }

        if (numberOfFloors <= 1) {
            throw new IllegalFloorNumberException("Number of floors must be more than 1");
        }

        if (numberOfQueries < 1) {
            throw new IllegalQueryNumberException("Number of queries must be positive");
        }

        this.elevatorSystem = elevatorSystem;
        this.numberOfFloors = numberOfFloors;
        this.numberOfQueries = numberOfQueries;
        this.random = new Random();
    }

    private QueryElevator getQuery() {
        // генерация случайных этажей в диапазоне [1; numberOfFloors]
        int floorSource = random.nextInt(numberOfFloors) + 1;
        int floorDestination = random.nextInt(numberOfFloors) + 1;

        // если этажи совпали, то генерируем, пока они не станут различными
        while (floorSource == floorDestination) {
            floorDestination = random.nextInt(numberOfFloors) + 1;
        }

        return new QueryElevator(floorSource, floorDestination);
    }

    private void printQuery(QueryElevator query) {
        System.out.printf("[QUERY]. Floor Source: %d; Floor Destination: %d\n",
                query.getFloorSource(), query.getFloorDestination());
    }

    @Override
    public void run() {
        for (int iteration = 0; iteration < numberOfQueries; ++iteration) {
            QueryElevator query = getQuery();
            printQuery(query);
            elevatorSystem.addQuery(query);

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
