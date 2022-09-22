package dispatcher;

import entitys.Elevator;
import entitys.Floor;
import entitys.Writer;

import java.util.Arrays;
import java.util.Deque;


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

    private int setNumberOfFloors() {
        int nOf = (int) ((Math.random() * 20) + 2);
        return nOf > 20 ? 20 : nOf;
    }

    public void elevatorStartManager() {
        int start = elevatorCallForFloor();
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
        int destinationFloor;
        if (emptyElevator()){
            destinationFloor = elevatorCallForFloor();}
       else {
           destinationFloor = elevator.sortPassengers()[0];}
       int[] elevatorTMP = elevator.sortPassengers();
       int tmpPassenger=destinationFloor;
       if (destinationFloor<1){return;}
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
        if (searchForPassengersWaitingForTransportation()) {
            elevatorControl();
        }
            }

    private boolean searchForPassengersWaitingForTransportation() {
        var f = elevator.sortPassengers().length;
        if (!(elevator.sortPassengers().length == 0)) {
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
        String [] result= new String [numberOfFloors];
        for (int i = 0; i < numberOfFloors; i++) {
        result[i]=house[i].toString(elevator.toString());}
        return result;
    }


     private boolean emptyElevator(){
        boolean w=Arrays.stream(elevator.elevatorPassengers).allMatch(x->x==0);
      if (w){ return true;}
         return false;
     }

}

