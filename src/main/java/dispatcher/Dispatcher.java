package dispatcher;

import entitys.Elevator;
import entitys.Floor;
import entitys.Writer;

import java.util.Arrays;
import java.util.Deque;



public class Dispatcher {

    private static int limitLastFloor;
    Elevator elevator;
    private int numberOfFloors;
    Floor[] house;
    Writer writer=new Writer();

    public Dispatcher() {
        this.numberOfFloors = setNumberOfFloors();
        this.house = createAHouse();
        this.elevator = new Elevator();

    }

    public static void setLimitLastFloor(int limitFloor) {
        limitLastFloor = limitFloor;
    }

    private Floor[] createAHouse() {
        Floor[] house = new Floor[numberOfFloors];
        for (int i = 0; i < house.length; i++) {
            house[i] = new Floor(numberOfFloors);
        }
              return house;
    }

    private int setNumberOfFloors() {
        int nOf = (int) ((Math.random() * limitLastFloor) + 2);
        return nOf > limitLastFloor ? limitLastFloor : nOf;
    }

    public void elevatorStartManager() {
        int start = elevatorCallForFloor();
        Deque<Integer> firstFloor = house[start - 1].getQueueForElevator();
        writer.writingToFile(toStringForWrite());
        for (int i = 0; i < 5; i++) {
            elevator.elevatorPassengers[i] = firstFloor.removeFirst();
            if (firstFloor.peek() == null) {
                break;
            }
        }
        house[0].setQueueForElevator(firstFloor);
        writer.writingToFile(toStringForWrite());
        elevatorControl();
    }

    private void elevatorControl() {
        writer.writingToFile(toStringForWrite());
        int destinationFloor;
        if (elevator.emptyElevator()) {
            destinationFloor = elevatorCallForFloor();
        } else {
            destinationFloor = elevator.sortPassengers()[0];
        }
        int[] elevatorTMP = elevator.sortPassengers();
        int tmpPassenger = destinationFloor;
        if (destinationFloor < 1) {
            return;
        }
        Deque<Integer> queueOnTheFloor = house[destinationFloor - 1].getQueueForElevator();
        Deque<Integer> queueTheExited = house[destinationFloor - 1].getQueueCameOutOfTheElevator();
        Arrays.stream(elevatorTMP).
                forEach((x) ->
                {
                    if (x == tmpPassenger) {
                        queueTheExited.add(tmpPassenger);
                        elevator.deletePassenger(tmpPassenger);
                    }
                });
        writer.writingToFile(toStringForWrite());
        while (elevator.sortPassengers().length <= 5) {
            if (queueOnTheFloor.isEmpty()) {
                         elevatorControl(); }
            Integer passFirst=queueOnTheFloor.pollFirst();
            if (passFirst!=null) {
               elevator.addPassenger(passFirst);
                         }
             else if (passFirst==null){
                 break;}
        }
        house[destinationFloor-1].setQueueCameOutOfTheElevator(queueTheExited);
        if (searchForPassengersWaitingForTransportation()) {
            elevatorControl();
        }
            return; }

    private boolean searchForPassengersWaitingForTransportation() {
        if (!(elevator.emptyElevator())) {
            return true;
        }
        for (int i = numberOfFloors; i==0; i--) {
            if (!(house[i].getQueueForElevator().isEmpty())) {
                return true;
            }
        }
        return false;
    }

    private int elevatorCallForFloor(){
        for (int i = 0; i < numberOfFloors; i++) {
            if (!(house[i].getQueueForElevator().isEmpty())) {
                return i+1;
            }
          }
        return 0;
    }


    private String[] toStringForWrite() {
        String[] result = new String[numberOfFloors];
        int start;
        if (elevator.emptyElevator()) {
            start = 0;
        } else {
            start = elevator.sortPassengers()[0];
        }
        for (int i = 0; i < numberOfFloors; i++) {
            String left = String.valueOf(house[i].getQueueCameOutOfTheElevator().size());
            String right = house[i].toString();
            String centre = start != i ? "[               ]" : elevator.toString();
            result[i] = left + centre + right;
        }
        return result;
    }

}

