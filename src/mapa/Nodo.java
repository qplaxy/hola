package mapa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Nodo extends JPanel {
	private String ciudad;
	public static ArrayList<Nodo> ciudades = new ArrayList<>();
	private ArrayList<Arista> aristas;
	private List<Nodo> shortestPath = new LinkedList<>();
	private Integer distance = Integer.MAX_VALUE;
	Map<Nodo, Integer> adjacentNodes = new HashMap<>();

	public Nodo(String ciudad) {
		this.ciudad = ciudad;
		ciudades.add(this);
	}

	public void addDestino(Nodo destino, int distancia) {
		adjacentNodes.put(destino, distancia);
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public ArrayList<Arista> getAristas() {
		return aristas;
	}

	public void agregarArista(Arista arista) {
		if (aristas == null) {
			aristas = new ArrayList<>();
		}
		aristas.add(arista);
	}

	public List<Nodo> getCaminoMasCorto() {
		return shortestPath;
	}

	public void setCaminoMasCorto(List<Nodo> shortestPath) {
		this.shortestPath = shortestPath;
	}

	public Integer getDistancia() {
		return distance;
	}

	public void setDistancia(Integer distance) {
		this.distance = distance;
	}

	public Map<Nodo, Integer> getAdjacentNodes() {
		return adjacentNodes;
	}

	public void setAdjacentNodes(Map<Nodo, Integer> adjacentNodes) {
		this.adjacentNodes = adjacentNodes;
	}

	public void setAristas(ArrayList<Arista> aristas) {
		this.aristas = aristas;
	}
	
	@Override
	public String toString() {
		return "\n \n \tNODO [Ciudad: " + ciudad + "]\nARISTAS: " + aristas + "]";
	}
}
