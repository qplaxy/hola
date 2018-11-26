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

public class GenerarXML {
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
