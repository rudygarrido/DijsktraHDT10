import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map.Entry;


public class Manejador{
	
	/*
	 * Instanciacion: se utilizo una ArrayList para guardar
	 * la informacion necesario de las ciudades
	 * 
	 * --> Tambien la clase FloydMarshall proporcionada por 
	 * Kevin Wayne, asi como las clases que se encargan de manejar los 
	 * "edgeWeight" o pesos de las aristas del grafo.
	 * */
	
	ArrayList<String> ciudades= new ArrayList<String>();
	FloydWarshall spt=null;
	public FloydWarshall getSpt() {
		return spt;
	}
	public void setSpt(FloydWarshall spt) {
		this.spt = spt;
	}
	public ArrayList<String> getCiudades() {
		return ciudades;
	}
	public void setCiudades(ArrayList<String> ciudades) {
		this.ciudades = ciudades;
	}
	public AdjMatrixEdgeWeightedDigraph getG() {
		return g;
	}
	public void setG(AdjMatrixEdgeWeightedDigraph g) {
		this.g = g;
	}
	AdjMatrixEdgeWeightedDigraph g = null;
	public void readFile(String file) throws FileNotFoundException {

		/*
		 * Lectura de las lineas de texto
		 * */
		 BufferedReader br = new BufferedReader(new FileReader(file));
	        String line = null;
	        try {
				while ((line = br.readLine()) != null) {
					String[] datos = line.split(" ");
					
					if(!ciudades.contains(datos[0])){
						System.out.println(datos[0]);
						ciudades.add(datos[0]);
					}
					if(!ciudades.contains(datos[1])){
						System.out.println(datos[1]);
						ciudades.add(datos[1]);
					}
				}
				g = new AdjMatrixEdgeWeightedDigraph(ciudades.size());
				br = new BufferedReader(new FileReader(file));
		        line = null;
				while ((line = br.readLine()) != null) {
					String[] datos = line.split(" ");
					g.addEdge(new DirectedEdge(ciudades.indexOf(datos[0]), ciudades.indexOf(datos[1]), Integer.parseInt(datos[2])));
				}
				
				StdOut.println(g);
			} catch (IOException e) {
				e.printStackTrace();
			}
	        
	        spt = new FloydWarshall(g);

	        // print all-pairs shortest path distances
	        StdOut.printf("  ");
	        for (int v = 0; v < g.V(); v++) {
	            StdOut.printf("%6d ", v);
	        }
	        StdOut.println();
	        for (int v = 0; v < g.V(); v++) {
	            StdOut.printf("%3d: ", v);
	            for (int w = 0; w < g.V(); w++) {
	                if (spt.hasPath(v, w)) StdOut.printf("%6.2f ", spt.dist(v, w));
	                else StdOut.printf("  Inf ");
	            }
	            StdOut.println();
	        }

	        // print negative cycle
	        if (spt.hasNegativeCycle()) {
	            StdOut.println("Negative cost cycle:");
	            for (DirectedEdge e : spt.negativeCycle())
	                StdOut.println(e);
	            StdOut.println();
	        }

	        // print all-pairs shortest paths
	        else {
	            for (int v = 0; v < g.V(); v++) {
	                for (int w = 0; w < g.V(); w++) {
	                    if (spt.hasPath(v, w)) {
	                        StdOut.printf("%d to %d (%5.2f)  ", v, w, spt.dist(v, w));
	                        for (DirectedEdge e : spt.path(v, w))
	                            StdOut.print(e + "  ");
	                        StdOut.println();
	                    }
	                    else {
	                        StdOut.printf("%d to %d no path\n", v, w);
	                    }
	                }
	            }
	        }       
	}
	
	/*
	 * Se suman las distancias entre ciudades
	 * */
	public String obtenerDistancia(String ciudadInicio, String ciudadFinal){
		if(ciudades.contains(ciudadInicio) && ciudades.contains(ciudadFinal)){
			if(spt.hasPath(ciudades.indexOf(ciudadInicio), ciudades.indexOf(ciudadFinal))){
				String distancia = "El camino entre "+ciudadInicio+" y "+ciudadFinal+" de "+spt.dist(ciudades.indexOf(ciudadInicio), ciudades.indexOf(ciudadFinal))+
						" Km atraves de \n";
				boolean bandera=true;
				for (DirectedEdge e : spt.path(ciudades.indexOf(ciudadInicio), ciudades.indexOf(ciudadFinal))){
					if(!ciudades.get(e.to()).equalsIgnoreCase(ciudadFinal)){
						distancia= distancia+ ciudades.get(e.to())+" -> ";
						bandera = false;
					}
				}
				if(bandera)
					distancia = distancia + " ninguna ciudad";
                    //StdOut.print(e + "  ");
				return distancia;
			}else{
				return "No hay un camino entre estas ciudades";
			}
		}else{
			return null;
		}
	}
	
	
	/**
	 * 
	 * Se elimina la conexion por medio de la eliminacion de la 
	 * "edge" o arista que conecta los nodos
	 * 
	 * @param ciudadInicio
	 * @param ciudadFinal
	 */
	public void eliminarConexion(String ciudadInicio, String ciudadFinal){
		System.out.println("inicio "+ciudades.indexOf(ciudadInicio));
		System.out.println("final "+ciudades.indexOf(ciudadFinal));
		g.deleteEdge(g.getEdge(ciudades.indexOf(ciudadInicio), ciudades.indexOf(ciudadFinal)));
		spt = new FloydWarshall(g);
		System.out.println("Se elimino conexion de "+ciudadInicio+" a "+ciudadFinal);
	}
	/**
	 * @return matriz de adyacencia
	 */
	public String obtenerMatriz() {
		String matriz = "     ";
		for(int v = 0; v < g.V(); v++){
			matriz= matriz+v+"\t";
		}
		matriz = matriz+"\n";
		for (int v = 0; v < g.V(); v++) {
			matriz = matriz +v+":   ";
            //StdOut.printf("%3d: ", v);
            for (int w = 0; w < g.V(); w++) {
                if (g.getAdj()[v][w]!=null) {
                	matriz = matriz+"" + g.getAdj()[v][w].weight()+"\t";
                	//StdOut.printf("%6.2f ", spt.dist(v, w));
                }else {
                	matriz = matriz+"Inf\t";
                	//StdOut.printf();
                }
            }
            matriz = matriz+"\n";
            //StdOut.println();
        }
		int numero =0;
		for(String ciudad:ciudades){
			matriz = matriz+numero+"= "+ciudad+" ";
			numero++;
		}
		return matriz;
	}
	/**
	 * Crea una conexion entre nodos
	 * 
	 * @param ciudadInicio
	 * @param ciudadFinal
	 * @param peso: distancia entre nodos
	 */
	public void crearConexion(String ciudadInicio, String ciudadFinal, int peso) {
		g.addEdge(new DirectedEdge(ciudades.indexOf(ciudadInicio), ciudades.indexOf(ciudadFinal), peso));
		spt = new FloydWarshall(g);
		System.out.println("Se agrego conexion de "+ciudadInicio+" a "+ciudadFinal);
	}
	
	
	/**
	 * @return Centro del grafo
	 */
	public String calcularNodoCentral(){
		double[] nodoCentral = {0,9999999};
		for (int w = 0; w < g.V(); w++) {
			double excentricidad=0;
			for (int v = 0; v < g.V(); v++) {
				if(spt.hasPath(v, w)){
					if(excentricidad<spt.getDistTo()[v][w])
						excentricidad = spt.getDistTo()[v][w];
				}
			}
			System.out.println("La excentricidad del nodo "+w+" es "+ excentricidad);
			if(nodoCentral[1]>excentricidad && excentricidad != 0){
				nodoCentral[1]=excentricidad;
				nodoCentral[0]=w;
			}
				
		}
		return "La ciudad central es "+ciudades.get((int)nodoCentral[0]);
	}
}
