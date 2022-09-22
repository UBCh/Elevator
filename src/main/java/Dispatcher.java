import java.util.Arrays;
import java.util.Deque;
import java.util.stream.IntStream;


public class Dispatcher {

    Elevator elevator;
    private int numberOfFloors;
    Floor[] house;
    Writer writer=new Writer();

    public Dispatcher() {
        this.numberOfFloors = setNumberOfFloors();
        this.house = createAHouse();
        this.elevator = new Elevator();

    }


    private Floor[] createAHouse() {
        Floor[] house = new Floor[numberOfFloors];
        for (int i = 0; i < house.length; i++) {
            house[i] = new Floor(numberOfFloors);
        }
              return house;
    }

    public int setNumberOfFloors() {
        int nOf = (int) ((Math.random() * 20) + 2);
        return nOf > 20 ? 20 : nOf;
    }

    public void elevatorStartManager() {
        int start = elevatorCall();
        Deque<Integer> firstFloor = house[start - 1].getQueueForElevator();
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
        writer.writingToFile(toStringForWrite());
        if (elevatorCall()==0){
           return;}
        int destinationFloor;
        if (elevator.sortPassengers().length<1){
            destinationFloor =elevatorCall();}
       else {
           destinationFloor = elevator.sortPassengers()[0];}
       int[] elevatorTMP = elevator.sortPassengers();
        Deque<Integer> queueOnTheFloor = house[destinationFloor - 1].getQueueForElevator();
        Deque<Integer> queueTheExited = house[destinationFloor - 1].getQueueCameOutOfTheElevator();
        writer.writingToFile(toStringForWrite());
        Arrays.stream(elevatorTMP).
                forEach((x) ->
                {
                    if (x == destinationFloor) {
                        queueTheExited.add(destinationFloor);
                        elevator.deletePassenger(destinationFloor);
                    }
                });
        writer.writingToFile(toStringForWrite());
        while (elevator.sortPassengers().length < 5) {
            if (queueOnTheFloor.isEmpty()) {
                         elevatorControl(); }
            Integer passFirst=queueOnTheFloor.pollFirst();
            if (passFirst!=null) {
               elevator.addPassenger(passFirst);
                         }
           else{
               break;}
                    }
        house[destinationFloor-1].setQueueCameOutOfTheElevator(queueTheExited);
        int sizeElevator = elevator.sortPassengers().length;
        if (sizeElevator == 5) {
                             elevatorControl();
        }
        while (!(queueOnTheFloor.isEmpty()) & elevator.sortPassengers().length <= 5) {
            Integer firstInLine = queueOnTheFloor.pollFirst();
            if (firstInLine == null) {
                house[destinationFloor-1].setQueueForElevator(queueOnTheFloor);
                writer.writingToFile(toStringForWrite());
                elevatorControl();
            }
            elevator.addPassenger(firstInLine);
        }
        house[destinationFloor-1].setQueueForElevator(queueOnTheFloor);
        writer.writingToFile(toStringForWrite());
        if (!(searchForPassengersWaitingForTransportation())) {
            return;
        }
         elevatorControl();
    }

    private boolean searchForPassengersWaitingForTransportation() {
        var f = elevator.sortPassengers().length;
        if (!(elevator.sortPassengers().length == 0)) {
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


    public String[] toStringForWrite() {
        String [] result= new String [numberOfFloors];
        for (int i = 0; i < numberOfFloors; i++) {
          result[i]=house[i].toString(elevator.toString());
        }

        return result;
    }
}

