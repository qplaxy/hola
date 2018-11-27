package mapa;

public class Arista {
	private Nodo origen;
	private Nodo destino;
	private int distancia;

	public Arista(Nodo origen, Nodo destino, int distancia) {
		this.origen = origen;
		this.destino = destino;
		this.distancia = distancia;
	}

	public Nodo getOrigen() {
		return origen;
	}

	public void setOrigen(Nodo origin) {
		this.origen = origin;
	}

	public Nodo getDestino() {
		return destino;
	}

	public void setDestino(Nodo destino) {
		this.destino = destino;
	}

	public int getDistancia() {
		return distancia;
	}

	public void setDistancia(int distancia) {
		this.distancia = distancia;
	}

	@Override
	public String toString() {
		return "\n Arista { \norigen: " + origen.getCiudad() + ",\ndestino: " + destino.getCiudad()
				+ "\ndistancia entre las ciudades: " + distancia + " s }\n";
	}

}