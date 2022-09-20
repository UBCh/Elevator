import java.util.Arrays;
import java.util.Deque;


public class Meson {

    Elevator elevator;
    private int numberOfFloors;
    Floor[] house;

    public Meson() {
        this.house=createAHouse();
    }


    private Floor[] createAHouse() {
        setNumberOfFloors();
        setElevator();
        Floor[] house = new Floor[numberOfFloors];
        for (int i = 0; i < house.length; i++) {
            house[i] = new Floor( i+1,numberOfFloors);
        }
        return house;
    }

    public void setNumberOfFloors() {
        int nOf = (int) ((Math.random() * 20) + 2);
        this.numberOfFloors = nOf > 20 ? 20 : nOf;
    }

    public Floor[] getHouse() {
        return house;
    }

    public void setElevator() {
        this.elevator = new Elevator();
    }

    public void callingTheElevatorToTheFirstFloor() {
        int start=elevatorCall();
        Deque<Integer> firstFloor = house[start-1].getQueueForElevator();
          for (int i = 0; i < 5; i++) {
            elevator.elevatorPassengers[i] = firstFloor.removeFirst();
            if (firstFloor.peek() == null) {
                break;
            }
        }
        house[0].setQueueForElevator(firstFloor);
        elevatorControl();
    }

    private void elevatorControl() {
       if (elevatorCall()==0){ return;}
        int destinationFloor;
        if (elevator.getElevatorPassengers().length<1){ destinationFloor =elevatorCall();}
       else { destinationFloor = elevator.getElevatorPassengers()[0];}
        var elevatorTMP = elevator.getElevatorPassengers();
        Deque<Integer> queueOnTheFloor = house[destinationFloor - 1].getQueueForElevator();
        Deque<Integer> queueTheExited = house[destinationFloor - 1].getQueueCameOutOfTheElevator();
        Arrays.stream(elevatorTMP).
                forEach((x) ->
                {
                    if (x == destinationFloor) {
                        queueTheExited.add(destinationFloor);
                        elevator.deletePassenger(destinationFloor);
                    }
                });
        while (elevator.getElevatorPassengers().length < 5) {
            if (queueOnTheFloor.isEmpty()) {
                 elevatorControl(); }
            var u = queueOnTheFloor.pollFirst();
           if (u!=null) {
               elevator.addPassenger(u);}
           else{break;}
                    }
        house[destinationFloor-1].setQueueCameOutOfTheElevator(queueTheExited);
        int sizeElevator = elevator.getElevatorPassengers().length;
        if (sizeElevator == 5) {
            elevatorControl();
        }
        while (!(queueOnTheFloor.isEmpty()) & elevator.getElevatorPassengers().length <= 5) {
            var w = queueOnTheFloor.pollFirst();
            if (w == null) {
                house[destinationFloor-1].setQueueForElevator(queueOnTheFloor);
                elevatorControl();
            }
            elevator.addPassenger(w);
        }
        house[destinationFloor-1].setQueueForElevator(queueOnTheFloor);
        if (!(searchForPassengersWaitingForTransportation())) {
            return;
        }
        elevatorControl();
    }

    private boolean searchForPassengersWaitingForTransportation() {
        var f = elevator.getElevatorPassengers().length;
        if (!(elevator.getElevatorPassengers().length == 0)) {
            return true;
        }
        for (int i = numberOfFloors; i >= numberOfFloors; i--) {
            if (!(house[i].getQueueForElevator().isEmpty())) {
                return true;
            }
        }
        return false;
    }

    private int elevatorCall(){
        for (int i = 0; i < numberOfFloors; i++) {
            if (!(house[i].getQueueForElevator().isEmpty())) {
                return i+1;
            }
          }
        return 0;
    }

}

