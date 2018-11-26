package mapa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;

import javax.swing.JFrame;

public class Mapa {
	static int x = 20;
	static int y = 20;
	static int min = 1;
	static int max = 10;
	static Random numero = new Random();
	static HashMap<Nodo, ArrayList<Arista>> ciudadArista = new HashMap<>();

	@SuppressWarnings("unused")
	public static Grafo getCities() {

		/** Por cada ciudad la mae crea aristas aleatoriamente */
		for (Nodo ciudadOrigen : Nodo.ciudades) {
			/** Numero de aristas que va a tener la ciudad */
			int numAristas = numero.nextInt(3) + 1;
			/** Nueva lista con las ciudades */
			ArrayList<Nodo> listaAux = new ArrayList<>();
			listaAux = Nodo.ciudades;

			/** Por cada arista que va a tener cada ciudad hace lo siguiente */
			for (int i = 0; i < numAristas; i++) {
				int indice = generarIndice();/** Genera un indice aleatorio */
				int numeroAleatorio = distanciaAleatoria();/** Genera una distancia aleatoria */
				Nodo ciudadDestino = listaAux.get(indice);/** Define una ciudad destino aleatoria */

				/** Verifica que la ciudad de origen sea diferente a la de destino */
				if (ciudadOrigen != ciudadDestino && ciudadOrigen.getAristas() == null) {
					ciudadOrigen.agregarArista(new Arista(ciudadOrigen, ciudadDestino, numeroAleatorio));
					ciudadOrigen.addDestino(ciudadDestino, numeroAleatorio);

					/** Revisa que por cada ciudad no se repita un destino */
				} else if (ciudadOrigen != ciudadDestino && ciudadOrigen.getAristas() != null) {
					if (!(verificarCiudad(ciudadOrigen, ciudadDestino))) {
						ciudadOrigen.agregarArista(new Arista(ciudadOrigen, ciudadDestino, numeroAleatorio));
						ciudadOrigen.addDestino(ciudadDestino, numeroAleatorio);
					}

				} else {
					/** Le resta uno a la iteración para que se repita */
					i = i - 1;
				}
			}
		}

		/** Acá genera el grafo */
		Grafo graph = new Grafo();
		/** Agrega cada ciudad al mapa */
		for (Nodo ciu : Nodo.ciudades) {
			graph.agregarNodo(ciu);
		}

		try {
			Ayuda.generaXML();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return graph;
	}

	/** Verifica que no se repita el destino */
	public static boolean verificarCiudad(Nodo ciudadOrigen, Nodo ciudadDestino) {
		boolean contiene = true;
		for (int i = 0; i < ciudadOrigen.getAristas().size(); i++) {
			if (ciudadOrigen.getAristas().get(i).getDestino() == ciudadDestino) {
				contiene = true;
			} else {
				contiene = false;
			}
		}
		return contiene;
	}

	/** Genera un indice aleatorio */
	public static int generarIndice() {
		return numero.nextInt(30);
	}

	/** Genera una distancia aleatoria */
	public static int distanciaAleatoria() {
		return (int) numero.nextInt(max) + 1;

	}

	public static HashMap<Nodo, ArrayList<Arista>> crearHashMap() {
		for (Nodo ciudad : Nodo.ciudades) {
			ArrayList<Arista> aristas = ciudad.getAristas();
			ciudadArista.put(ciudad, aristas);
		}
		return ciudadArista;
	}

	private static void calcularDistanciaMinima(Nodo nodoEvaluado, Integer distancia, Nodo nodoOrigen) {
		Integer origenDistancia = nodoOrigen.getDistancia();
		if (origenDistancia + distancia < nodoEvaluado.getDistancia()) {
			nodoEvaluado.setDistancia(origenDistancia + distancia);
			LinkedList<Nodo> caminoMasCorto = new LinkedList<>(nodoOrigen.getCaminoMasCorto());
			caminoMasCorto.add(nodoOrigen);
			nodoEvaluado.setCaminoMasCorto(caminoMasCorto);
		}
	}

	private static Nodo buscarDistanciaMasCorta(Set<Nodo> nodosNoEvaluados) {
		Nodo nodoMenorDistancia = null;
		int menorDistancia = Integer.MAX_VALUE;
		for (Nodo nodo : nodosNoEvaluados) {
			int distanciaEntreNodos = nodo.getDistancia();
			if (distanciaEntreNodos < menorDistancia) {
				menorDistancia = distanciaEntreNodos;
				nodoMenorDistancia = nodo;
			}
		}
		return nodoMenorDistancia;
	}

	public static Grafo caminoMasCortoDesdeNodo(Grafo grafo, Nodo inicial) {
		inicial.setDistancia(0);

		Set<Nodo> nodosEvaluados = new HashSet<>();
		Set<Nodo> nodosNoEvaluados = new HashSet<>();

		nodosNoEvaluados.add(inicial);

		while (nodosNoEvaluados.size() != 0) {
			Nodo nodoActual = buscarDistanciaMasCorta(nodosNoEvaluados);
			nodosNoEvaluados.remove(nodoActual);
			for (Entry<Nodo, Integer> parDeAdyacencia : nodoActual.getAdjacentNodes().entrySet()) {
				Nodo nodoAdyacente = parDeAdyacencia.getKey();
				Integer distanciaArista = parDeAdyacencia.getValue();
				if (!nodosEvaluados.contains(nodoAdyacente)) {
					calcularDistanciaMinima(nodoAdyacente, distanciaArista, nodoActual);
					nodosNoEvaluados.add(nodoAdyacente);
				}
			}
			nodosEvaluados.add(nodoActual);
		}
		return grafo;
	}

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Nodo turrialba = new Nodo("Turrialba");
		Nodo oreamuno = new Nodo("Oreamuno");
		Nodo tec = new Nodo("TEC");
		Nodo escazu = new Nodo("Escazú");
		Nodo belen = new Nodo("Belén");
		Nodo guadalupe = new Nodo("Guadalupe");
		Nodo santaAna = new Nodo("Santa Ana");
		Nodo alajuela = new Nodo("Alajuela");
		Nodo sanJose = new Nodo("San José");
		Nodo santaLucia = new Nodo("Santa Lucía");
		Nodo paraiso = new Nodo("Paraíso");
		Nodo sanPedro = new Nodo("San Pedro");
		Nodo quepos = new Nodo("Quepos");
		Nodo jaco = new Nodo("Jacó");
		Nodo golfito = new Nodo("Golfito");
		Nodo tamarindo = new Nodo("Tamarindo");
		Nodo barva = new Nodo("Barva");
		Nodo curridabat = new Nodo("Curridabat");
		Nodo orosi = new Nodo("Orosí");
		Nodo dominical = new Nodo("Dominical");
		Nodo atenas = new Nodo("Atenas");
		Nodo grecia = new Nodo("Grecia");
		Nodo ciudadColon = new Nodo("Ciudad Colón");
		Nodo guachipelin = new Nodo("Guachipelín");
		Nodo orotina = new Nodo("Orotina");
		Nodo sanRamon = new Nodo("San Ramón");
		Nodo buenosAires = new Nodo("Buenos Aires");
		Nodo sanFranciso = new Nodo("San Francisco");
		Nodo sanRafael = new Nodo("San Rafael");
		Nodo coronado = new Nodo("Coronado");

		Grafo graph = getCities();
		crearHashMap();
		Grafo grafo = caminoMasCortoDesdeNodo(graph, escazu);

	}
}