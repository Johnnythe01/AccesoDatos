package com.example;

import java.io.File;
import java.io.FileWriter;
import java.util.Collections;
import java.util.Comparator;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;


public class Main {

    public static void main(String[] args) {
        try {
            // Crear un contexto JAXB para la clase Llibres
            JAXBContext context = JAXBContext.newInstance(Llibres.class);

            // Crear un unmarshaller
            Unmarshaller unmarshaller = context.createUnmarshaller();

            // Deserializar el archivo XML
            Llibres llibres = (Llibres) unmarshaller.unmarshal(new File("llibres.xml"));

            // Ordenar los libros por año de publicación
            Collections.sort(llibres.getLlibre(), Comparator.comparingInt(Llibre::getAny));

            // Imprimir los libros por consola
            for (Llibre llibre : llibres.getLlibre()) {
                System.out.println("Autor: " + llibre.getAutor());
                System.out.println("Título: " + llibre.getTitol());
                System.out.println("Año: " + llibre.getAny());
                System.out.println("Resumen: " + llibre.getResum());
                System.out.println("----");
            }

            // Escribir los libros en un archivo de texto
            try (FileWriter writer = new FileWriter("resultado.txt")) {
                for (Llibre llibre : llibres.getLlibre()) {
                    writer.write("Autor: " + llibre.getAutor() + "\n");
                    writer.write("Título: " + llibre.getTitol() + "\n");
                    writer.write("Año: " + llibre.getAny() + "\n");
                    writer.write("Resumen: " + llibre.getResum() + "\n");
                    writer.write("----\n");
                }
            }

        } catch (JAXBException | java.io.IOException e) {
        }
    }
}


/*
Ejercicio: Mapeo de un documento XML a objetos Java utilizando JAXB
(por todos)
Objetivo: Crear un programa Java que utilice JAXB para leer un documento XML que contiene información sobre libros y convertirlo en objetos Java.
Imprime por consola el resultado de todos los campos de XML:


(Sólo por No-DUAL)
Haz un archivo de texto con el resultado de la consola. Ordena los resultados por orden cronológico del año de publicación.
*/