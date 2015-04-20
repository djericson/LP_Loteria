package app;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	
	public void binarySearch()
	{
		System.out.println("f1");
	}
	
	public void f2()
	{
		System.out.println("f2");
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<Loteria> my_arrList = new ArrayList<Loteria>(); //crea una arraylist de la clase abstracta Loteria
		Quina obj1 = new Quina(); //inicializa un objeto de la Clase Quina
		my_arrList.add(obj1); //añade la clase Quina a la lista
		Lotogol obj2 = new Lotogol(); //inicializa un objeto de la Clase Lotogol
		my_arrList.add(obj2); //añade la clase Lotogol a la lista
		
		
		Scanner sc = new Scanner(System.in);
		System.out.println("\t\tSIST. DE LOTERIAS");
		System.out.println("MENU DE OPCIONES:");
		System.out.println("1. EJECUTAR PARAMETROS DE LOTERIA.");
		System.out.println("3. SALIR DEL PROGRAMA.");
		System.out.println("INGRESE OPCION:");
		int opc = sc.nextInt();
		if(opc == 1){
			System.out.println("ingrese Tipo-Loteria y Dir-name");
			String p1 = sc.next();
			String p2 = sc.next();
			System.out.println("nombre de la loteria: "+ p1);
			System.out.println("directorio: "+ p2);
			for(int i=0; i<my_arrList.size(); i++){

				String curr_clName = my_arrList.get(i).getClass().getName();
				//System.out.println("curr_clName.toLowerCase(): "+ curr_clName);
				if(curr_clName.toLowerCase().equals("app."+ p1)){ //compara el nombre de la clase con el param. formateado
					my_arrList.get(i).execute(p2); //ejecuta el metodo execute del objeto que este en la posicion al que hizo match 
				}
			}

		}
		else{
			System.out.println("fin programa");
			return;
		}
		
		
	}

}
