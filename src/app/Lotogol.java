package app;

public class Lotogol extends Loteria{
	String r1;
	Lotogol(){
		r1 = "";
		
	}
	
	@Override
	public void execute(String dir_name){
		System.out.println("Toy en la clase lotogol con el parametro: "+ dir_name);
	}
	
}
