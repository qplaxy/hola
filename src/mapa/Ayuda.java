package mapa;

import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

public class Ayuda {

	public static void generaXML() {
		String nombre_archivo = "xml-ciudades";
		ArrayList<String> ciudadOrigen = new ArrayList<>();
		ArrayList<String> ciudadDestino = new ArrayList<>();
		ArrayList<String> distancia = new ArrayList<>();

		ciudadOrigen.add("Ciudad Colón");
		ciudadDestino.add("Alajuela");
		distancia.add("9");

		ciudadOrigen.add("Coronado");
		ciudadDestino.add("San Francisco");
		distancia.add("8");

		ciudadOrigen.add("San Pedro");
		ciudadDestino.add("Paraíso");
		distancia.add("2");

		ciudadOrigen.add("Escazú");
		ciudadDestino.add("Belén");
		distancia.add("3");

		ciudadOrigen.add("Turrialba");
		ciudadDestino.add("Oreamuno");
		distancia.add("5");

		ciudadOrigen.add("Guadalupe");
		ciudadDestino.add("TEC");
		distancia.add("8");

		ciudadOrigen.add("Jacó");
		ciudadDestino.add("Barva");
		distancia.add("2");

		ciudadOrigen.add("Orosí");
		ciudadDestino.add("Curridabat");
		distancia.add("1");

		ciudadOrigen.add("Tamarindo");
		ciudadDestino.add("Dominical");
		distancia.add("8");

		ciudadOrigen.add("Guachipelín");
		ciudadDestino.add("Atenas");
		distancia.add("5");

		ciudadOrigen.add("TEC");
		ciudadDestino.add("TEC");
		distancia.add("0");

		ciudadOrigen.add("Quepos");
		ciudadDestino.add("Barva");
		distancia.add("2");

		ciudadOrigen.add("Grecia");
		ciudadDestino.add("Alajuela");
		distancia.add("10");

		ciudadOrigen.add("Guadalupe");
		ciudadDestino.add("TEC");
		distancia.add("3");

		ciudadOrigen.add("San José");
		ciudadDestino.add("Escazú");
		distancia.add("4");

		ciudadOrigen.add("Santa Lucía");
		ciudadDestino.add("Oreamuno");
		distancia.add("3");

		ciudadOrigen.add("Ciudad Colón");
		ciudadDestino.add("TEC");
		distancia.add("6");

		ciudadOrigen.add("Ciudad Colón");
		ciudadDestino.add("Quepos");
		distancia.add("8");

		ciudadOrigen.add("San Pedro");
		ciudadDestino.add("Orotina");
		distancia.add("1");

		ciudadOrigen.add("Orotina");
		ciudadDestino.add("Paraíso");
		distancia.add("2");
		try {
			generaR(nombre_archivo, ciudadOrigen, distancia, ciudadDestino);
		} catch (Exception e) {
			System.out.println("Error al generar el archivo XML");
		}
	}

	public static void main(String[] args) {
	}

	public static void generaR(String nombreDocumento, ArrayList<String> ciudadOrigen, ArrayList<String> valorDistancia,
			ArrayList<String> ciudadDestino) throws Exception {

		if (ciudadOrigen.isEmpty() || valorDistancia.isEmpty() || ciudadOrigen.size() != valorDistancia.size()) {
			System.out.println("ERROR");
			return;
		} else {

			// No sé qué carajos es un factory pero así funciona
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder constructor = factory.newDocumentBuilder();
			DOMImplementation implementacion = constructor.getDOMImplementation();
			Document documento = implementacion.createDocument(null, nombreDocumento, null);
			documento.setXmlVersion("1.0");

			// Nodo principal
			Element raiz = documento.getDocumentElement();
			// Por cada key creamos un item que contendrá la key y el value
			for (int i = 0; i < ciudadOrigen.size(); i++) {
				// Nodo del objeto
				Element nodosMapa = documento.createElement("NODO");

				// Key del nodo ciudadOrigen
				Element nodoCiudad = documento.createElement("CIUDAD");
				Text ciudad = documento.createTextNode(ciudadOrigen.get(i));
				nodoCiudad.appendChild(ciudad);

				// Key del nodo ciudadDestino
				Element nodoDestino = documento.createElement("DESTINO");
				Text destino = documento.createTextNode(ciudadDestino.get(i));
				nodoDestino.appendChild(destino);

				// Valor de la distancia entre ciudades

				// crea el elemento distancia
				Element nodoDistancia = documento.createElement("DISTANCIA");
				// recorre el array list con los valores
				Text distancia = documento.createTextNode(valorDistancia.get(i));
				// agrega el valor de la distancia al nodo principal de distancia
				nodoDistancia.appendChild(distancia);

				// agrega el keyNodo (ciudad) y la distancia a este
				nodosMapa.appendChild(nodoCiudad);
				nodosMapa.appendChild(nodoDestino);
				nodosMapa.appendChild(nodoDistancia);
				// Se hace un append de general de "NODO" a la raíz
				raiz.appendChild(nodosMapa); // pegamos el elemento a la raiz "Documento"
			}
			// ACÁ GENERA EL XML
			Source source = new DOMSource(documento);
			// Indicamos donde lo queremos almacenar
			Result result = new StreamResult(new java.io.File(nombreDocumento + ".xml")); // nombre del archivo
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			transformer.transform(source, result);
		}
	}

}