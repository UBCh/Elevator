import java.util.ArrayDeque;
import java.util.Deque;

public class Floor {


  private int floorNumber;
  private int floorNumberMax;

    public Floor(int floorNumber, int floorNumberMax ) {
        this.floorNumber = floorNumber;
        this.floorNumberMax=floorNumberMax;
        this.queueForElevator=createQueue();
    }
  int queueSize= (int) (Math.random()*7);
    Deque<Integer> queueForElevator;
   Deque<Integer> queueCameOutOfTheElevator=new ArrayDeque<>();



    public Deque<Integer> getQueueCameOutOfTheElevator() {
        return queueCameOutOfTheElevator;
    }

    public void setQueueCameOutOfTheElevator(Deque<Integer> queue) {
        this.queueCameOutOfTheElevator = queue;
    }



 private Deque <Integer> createQueue(){
     Deque <Integer>  queue=new ArrayDeque<>();
     for (int i = 1; i < queueSize; i++) {
             queue.add(setFloorNumberPassenger());
     }
        return queue ;
 }

    public Deque <Integer> getQueueForElevator() {
        return queueForElevator;
    }

    public void setQueueForElevator(Deque<Integer> queueForElevator) {
        this.queueForElevator = queueForElevator;
    }

    private int setFloorNumberPassenger() {
        int fNNumber= (int) ((Math.random()*floorNumberMax)+2);
       int result= fNNumber>floorNumberMax?floorNumberMax:fNNumber;
        return result;
    }




}
