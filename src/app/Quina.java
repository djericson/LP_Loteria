package app;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.security.SecureRandom;
import java.security.NoSuchAlgorithmException;

public class Quina extends Loteria{
	ArrayList<String[]> arrL_jugadas; 
	String[] nros_apuestas;
	int[] nros_sorteados;

	List<Integer> nro_aciertos_jugadas;
	List<Integer> nro_selecciones_jugarores;
	double total_recaudado;
	double total_premio;
	
	int[] ganadoresXaciertos;
	double[] montosXaciertos;
	
	Quina(){
		arrL_jugadas = new ArrayList<String[]>();
		nros_sorteados = new int[5];
		nro_aciertos_jugadas = new ArrayList<Integer>();
		nro_selecciones_jugarores = new ArrayList<Integer>();
	
		total_recaudado = total_premio = 0.00;
		ganadoresXaciertos = new int[3]; //posicion 0, #winners quina, pos 1-quadra, pos 2-terna
		ganadoresXaciertos[0] = ganadoresXaciertos[1] = ganadoresXaciertos[2] = 0;
		montosXaciertos = new double[3]; //posicion 0:premios x jugador ganador quina, pos 1:..-quadra, pos 2:..-terna
		montosXaciertos[0] = montosXaciertos[1] = montosXaciertos[2] = 0.00;
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
			
			String curr_dir = miDir.getCanonicalPath();
			System.out.println ("Directorio actual: " + curr_dir);	
			
			archivo = new File (curr_dir +"\\src\\app\\"+dir_name+"\\apuestas.txt");
			fr = new FileReader (archivo);
			br = new BufferedReader(fr);
			
			// Lectura del fichero + precarga de nro d selecciones de nros x cada jugada:
			String linea;
			while((linea=br.readLine()) != null){
				System.out.println(linea);
				nros_apuestas = linea.split(" ");
				nro_selecciones_jugarores.add(nros_apuestas.length); //de paso obtiene el nro de seleccciones de la jugada y lo almacena
				arrL_jugadas.add(nros_apuestas);	
			}
			//System.out.println("arrL_jugadas SIZE: "+ arrL_jugadas.size());
			//System.out.println("arrL_jugadas.get(1).length " +arrL_jugadas.get(0).length );
			
			//para comprobar que almaceno bien:
			for(int i=0; i<arrL_jugadas.size(); i++){
				//System.out.println("arrL_jugadas.get(i).length: "+arrL_jugadas.get(i).length);
				for(int j=0; j< arrL_jugadas.get(i).length; j++){
					System.out.println(arrL_jugadas.get(i)[j]);
				}
			}
			
			System.out.println("generando datos");
			//genera los 5 nros sorteados:
			generarNrosSorteados();
			//genera datos de las jugadas contando y comparando la lista de jugadas con los valores sorteados:
			generarDatosJugadas();
			//Finalmente muestra los resultados
			mostrarResultados();
			
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
	
	public void generarDatosJugadas(){
		//para generar el total recaudado a partir del nro de selecciones por cada jugada guardada en nro_selecciones_jugarores
		for(int j=0; j< nro_selecciones_jugarores.size(); j++){
			//System.out.println(nro_selecciones_jugarores.get(j));
			if(nro_selecciones_jugarores.get(j)==5) total_recaudado += 0.75;
			else if(nro_selecciones_jugarores.get(j)==6) total_recaudado += 3.00;
			else if(nro_selecciones_jugarores.get(j)==7) total_recaudado += 7.50;
		}
		//System.out.println("total_recaudado: "+ total_recaudado);
		total_premio = (total_recaudado*32.20)/100;
		//System.out.println("total_premio: "+ total_premio);
		
		//cuenta el nro de coincidencias por jugada
		for(int i=0; i<arrL_jugadas.size(); i++){
			int nro_aciertos = contarCoincidencias(arrL_jugadas.get(i));
			System.out.println("# aciertos jugada["+i+"]: "+ nro_aciertos);
			if(nro_aciertos>2) //si tiene 3 o mas aciertos
				nro_aciertos_jugadas.add(nro_aciertos); //añade solo las jugadas ganadoras
		}
		
		System.out.println("tam de nro_aciertos_jugadas: "+ nro_aciertos_jugadas.size() );
		
		for(int i=0; i<nro_aciertos_jugadas.size(); i++){
			System.out.println("#aciertos["+i+"]: "+nro_aciertos_jugadas.get(i));
			if(nro_aciertos_jugadas.get(i)>=5) ganadoresXaciertos[0]++;
			else if(nro_aciertos_jugadas.get(i)==4) ganadoresXaciertos[1]++;
			else if(nro_aciertos_jugadas.get(i)==3) ganadoresXaciertos[2]++;
		}
		/*
		//mostrando el contenido de ganadoresXaciertos
		for(int i=0; i<ganadoresXaciertos.length; i++){
			System.out.println("nro d ganadores x aciertos["+i+"]: "+ ganadoresXaciertos[i]);
		}
		*/
		//calcular finalmente el monto que obtiene cada jugador ganador, segun el nro d aciertos(3, 4, 5), y el porcentaje del mismo
		if(ganadoresXaciertos[0]>0) montosXaciertos[0] = ((total_premio*35)/100)/ganadoresXaciertos[0];
		if(ganadoresXaciertos[1]>0) montosXaciertos[1] = ((total_premio*25)/100)/ganadoresXaciertos[1];
		if(ganadoresXaciertos[2]>0) montosXaciertos[2] = ((total_premio*25)/100)/ganadoresXaciertos[2];
		
	}
	
	public void mostrarResultados(){
		System.out.println();
		System.out.println("RESULTADOS QUINA:");
		System.out.println("total_recaudado: "+ total_recaudado);
		System.out.println("total_premio: "+ total_premio);
		System.out.println("QUINA "+ ganadoresXaciertos[0] +" "+ montosXaciertos[0]);
		System.out.println("QUADRA "+ ganadoresXaciertos[1] +" "+ montosXaciertos[1]);
		System.out.println("TERNO "+ ganadoresXaciertos[2] +" "+ montosXaciertos[2]);
	
	}
	
	public int contarCoincidencias(String[] elems2cmp){ //se pasa un arreglo d string con los elementos a buscar en el arreglo d nros. sorteados
		Boolean es_igual = false;
		int j = 0;
		int cont = 0;
		for(int i=0; i< elems2cmp.length; i++){
			es_igual = false;
			j = 0;
			while ((!es_igual) && (j < nros_sorteados.length)) {
				if (nros_sorteados[j] == Integer.parseInt(elems2cmp[i]) ) {
					es_igual = true;
					cont++;
				}
				j++;
			}
			
			/*
			if (es_igual)
				System.out.println (elems2cmp[i] + " es un número repetido. Encontrado en " + j + " búsquedas");
			else
				System.out.println (elems2cmp[i] + " no está en la lista. No encontrado en " + j + " búsquedas");
			*/
		}
		return cont;
		
	}
	
	public void generarNrosSorteados(){
		
		int[] rnd = {8, 25, 36, 37, 60};
		System.out.println("Nros Sorteados:");
		try {
			SecureRandom sec_num = SecureRandom.getInstance("SHA1PRNG");
			for(int i=0; i < 5; i++){
				
				//nros_sorteados[i] = sec_num.nextInt(81);
				nros_sorteados[i] = rnd[i];
			}
		} catch (NoSuchAlgorithmException nsae) { 
	     // Forward to handler
	   }
		
		for(int i=0; i< nros_sorteados.length; i++){
			System.out.print(nros_sorteados[i]+" ");
			
		}
		System.out.println();
	}
}






