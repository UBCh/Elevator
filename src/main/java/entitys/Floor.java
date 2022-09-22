package entitys;

import java.util.ArrayDeque;
import java.util.Deque;

public class Floor {


    private int floorNumberMax;
    private int queueSize = (int) (Math.random() * 7);
    private Deque<Integer> queueForElevator;
    private Deque<Integer> queueCameOutOfTheElevator = new ArrayDeque<>();

    public Floor(int floorNumberMax) {
        this.floorNumberMax = floorNumberMax;
        this.queueForElevator = createQueue();
    }


    public Deque<Integer> getQueueCameOutOfTheElevator() {
        return queueCameOutOfTheElevator;
    }

    public void setQueueCameOutOfTheElevator(Deque<Integer> queue) {
        this.queueCameOutOfTheElevator = queue;
    }

    public Deque <Integer> getQueueForElevator() {
        return queueForElevator;
    }

    public void setQueueForElevator(Deque<Integer> queueForElevator) {
        this.queueForElevator = queueForElevator;
    }



    private Deque <Integer> createQueue(){
        Deque <Integer>  queue=new ArrayDeque<>();
        for (int i = 1; i < queueSize; i++) {
             queue.add(setFloorNumberPassenger());
        }
        return queue ;
     }


    private int setFloorNumberPassenger() {
        int fNNumber= (int) ((Math.random()*floorNumberMax)+2);
       int result= fNNumber>floorNumberMax?floorNumberMax:fNNumber;
        return result;
    }

      public String toString(String elevator) {
          String left = String.valueOf(getQueueCameOutOfTheElevator().size());
          String center = elevator;
          String right = editLine(getQueueForElevator());
          return left + center + right;
      }

    private String editLine(Deque<Integer> deque) {
        String result = deque.toString();
        result = result.replace('[', ' ');
        result = result.replace(']', ' ');
        result = result.replace(',', ' ');
        result.trim();
       return result;
   }

}