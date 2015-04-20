package app;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import org.jcp.xml.dsig.internal.dom.Utils;


public class Quina extends Loteria{
	String r2;
	public Quina(){
		r2 = "";
		
	}
	
	@Override
	public void execute(String dir_name){
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
			
			String s = miDir.getCanonicalPath();
			System.out.println ("Directorio actual: " + s);	
			
			archivo = new File ("\\src\\app\\"+dir_name+"\\apuestas.txt");
			fr = new FileReader (archivo);
			br = new BufferedReader(fr);
			
			// Lectura del fichero
			String linea;
			while((linea=br.readLine()) != null)
				System.out.println(linea);
			
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
