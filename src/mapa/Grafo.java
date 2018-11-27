package mapa;

import java.util.ArrayList;
import java.util.List;

public class Grafo {

	public static ArrayList<Nodo> nodos;
	public static ArrayList<Nodo> listaNodos;

	public void agregarNodo(Nodo nodo) {
		if (nodos == null) {
			nodos = new ArrayList<>();
		}
		nodos.add(nodo);
	}

	public List<Nodo> getNodos() {
		return nodos;
	}

	@Override
	public String toString() {
		return "Grafo (Mapa) \nNodos:\n" + nodos + "]";
	}

}