import dispatcher.Dispatcher;
import dispatcher.Loader;

public class Controller {



    public static void main(String[] args){
	Loader loader=new Loader();
	loader.setConfigurations();
	Dispatcher dispatcher =new Dispatcher();
        dispatcher.elevatorStartManager();


   }


}
