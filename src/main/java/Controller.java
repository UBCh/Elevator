import java.util.*;

public class Controller {



    public static void main(String[] args){
	Meson meson=new Meson();
	Elevator elevator=new Elevator();
	int startFloor=0;
	   Deque <Passenger> queue =meson.house[startFloor].getQueueForElevator();
	    if(queue.peek()==null) {startFloor=startFloor+1; queue=meson.house[startFloor].getQueueForElevator();}
	    while (elevator.elevatorPassengers.size()<6) {
		elevator.elevatorPassengers.add(queue.removeFirst());
		meson.house[startFloor].setQueueForElevator(queue);
		if (queue.peek()==null)
		{break;}
	    }
	System.out.println("________floor__"+1+"start");
	unloadThePassengers(meson,elevator);
    }


   private static void unloadThePassengers( Meson meson, Elevator elevator){
       	var pass=elevator.elevatorPassengers.first();
       int letSGo=pass.getFloorNumber();
	  while (elevator.elevatorPassengers.first().getFloorNumber()==letSGo)
	  {   elevator.elevatorPassengers.remove(pass);
	      meson.house[letSGo-1].queueCameOutOfTheElevator.addLast(pass);
	  if (elevator.elevatorPassengers.isEmpty()){ break;}
	  }
        while ((5-elevator.elevatorPassengers.size())>0){
	    int q=5-elevator.elevatorPassengers.size();
	    var w=meson.house[letSGo-1].queueForElevator.pollFirst();
        if (meson.house[letSGo-1].queueForElevator.pollFirst()!=null)
	{  var r=meson.house[letSGo-1].queueForElevator.pollFirst();
	    elevator.elevatorPassengers.add(meson.house[letSGo-1].queueForElevator.pollFirst());
	    System.out.println("________floor__"+letSGo);
	    System.out.println(meson.house[letSGo-1].queueCameOutOfTheElevator.toString()+ "||"+elevator.elevatorPassengers.toString()+"||"+meson.house[letSGo-1].queueForElevator.toString());
	}
	else { unloadThePassengers(meson,elevator);}

       }

   }


}
