package Classes;

import Exceptions.EmptyArrayElevatorException;

import java.util.ArrayList;

public class ElevatorSystem {

    private ArrayList<Elevator> elevators;

    public ElevatorSystem() {
        elevators = new ArrayList<>();
    }

    public void addQuery(QueryElevator query) {
        if (elevators.isEmpty()) {
            throw new EmptyArrayElevatorException("Array of elevators is empty");
        }

        Elevator bestElevator = null;
        int minDistance = Integer.MAX_VALUE;

        // ищем сначала среди незанятых лифтов
        for (Elevator elevator : elevators) {
            if (elevator.isInactive()) {
                int currentDistance = Math.abs(elevator.getCurrentFloor() - query.getFloorSource());
                if (currentDistance < minDistance) {
                    minDistance = currentDistance;
                    bestElevator = elevator;
                }
            }
        }

        // если нашли незанятый лифт, то сразу добавляем запрос к лифту
        if (bestElevator != null) {
            bestElevator.addQuery(query);
            return;
        }

        // если все лифты заняты, то ищем тот, который быстрее обслужит запрос
        for (Elevator elevator : elevators) {
            int currentDistance = calcCurrentDistanceWhenElevatorIsActive(elevator, query);

            if (currentDistance < minDistance) {
                minDistance = currentDistance;
                bestElevator = elevator;
            }
        }

        if (bestElevator == null) {
            bestElevator = elevators.getFirst();
        }

        bestElevator.addQuery(query);
    }

    public void addElevator(Elevator elevator) {
        elevators.add(elevator);
    }

    private int calcCurrentDistanceWhenElevatorIsActive(Elevator elevator, QueryElevator query) {
        int currentDistance = Integer.MAX_VALUE;
        char elevatorDirection = elevator.getDirection();
        int elevatorFloor = elevator.getCurrentFloor();
        int querySourceFloor = query.getFloorSource();

        if (elevatorDirection == 'U' && querySourceFloor >= elevatorFloor) {
            currentDistance = querySourceFloor - elevatorFloor;
        }
        if (elevatorDirection == 'D' && querySourceFloor < elevatorFloor) {
            currentDistance = elevatorFloor - querySourceFloor;
        }

        return currentDistance;
    }
}
