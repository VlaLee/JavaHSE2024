import Classes.Elevator;
import Classes.ElevatorSystem;
import Classes.QueryElevatorGenerator;

public class Main {
    public static void main(String[] args) {
        final int numberOfFloors = 15;
        final int numberOfQueries = 5;

        Elevator elevator1 = new Elevator(1);
        Elevator elevator2 = new Elevator(2);
        ElevatorSystem elevatorSystem = new ElevatorSystem();

        elevatorSystem.addElevator(elevator1);
        elevatorSystem.addElevator(elevator2);

        Thread generator = new Thread(new QueryElevatorGenerator(elevatorSystem, numberOfFloors, numberOfQueries));
        Thread threadElevator1 = new Thread(elevator1);
        Thread threadElevator2 = new Thread(elevator2);

        generator.start();
        threadElevator1.start();
        threadElevator2.start();
    }
}