package app;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Lotogol extends Loteria{
	String[] nros_apuestas;
	int nro_jugadores;
	double total_acu;
	double total_premio;
	int nro_ganadores;
	
	Lotogol(){
		nro_jugadores = 0;
		total_acu = 0.00;
	}
	
	@Override
	public void execute(String dir_name){
		System.out.println("Toy en la clase lotogol con el parametro: "+ dir_name);
		System.out.println("Toy en la clase quina  con el parametro: "+ dir_name);
		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;
		try {
			// Apertura del fichero y creacion de BufferedReader para poder
			// hacer una lectura comoda (disponer del metodo readLine()).
			String r = System.getProperty("dir_quina.dir");
			System.out.println(r);
			
			File miDir = new File (".");
			
			String curr_dir = miDir.getCanonicalPath();
			System.out.println ("Directorio actual: " + curr_dir);	
			
			archivo = new File (curr_dir +"\\src\\app\\"+dir_name+"\\apuestas.txt");
			fr = new FileReader (archivo);
			br = new BufferedReader(fr);
			
			// Lectura del fichero  
			String linea;
			while((linea=br.readLine()) != null){
				System.out.println(linea);
				
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if( null != fr ){
					fr.close();     
		        }                 
	        }catch (Exception e2){
	        	e2.printStackTrace();
	        }
		}
	}
	
}
