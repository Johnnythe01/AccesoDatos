package com.example;

import java.io.File;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

 
public class Principal2 {
 
    public static void main(String[] args) {
 
        try {
            JAXBContext context = JAXBContext.newInstance( Llibre.class );
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Llibre llibre = (Llibre)unmarshaller.unmarshal(
                new File("llibres.xml") );
 
            System.out.println(llibre.getTitol());
            System.out.println(Llibre.getPaginas());
 
        } catch (JAXBException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
 
    }
 
}

/* 
public class Main {

    public static void main(String[] args) {
        try {
            // Crear el contexto de JAXB para las clases generadas
            JAXBContext context = JAXBContext.newInstance(Llibres.class);

            // Crear un Unmarshaller para convertir el XML en objetos Java
            Unmarshaller unmarshaller = context.createUnmarshaller();

            // Leer el archivo XML y hacer el unmarshalling a objetos Java
            Llibres llibres = (Llibres) unmarshaller.unmarshal(new File("llibres.xml"));

            // Ordenar la lista de libros por el año de publicación (campo 'any')
            Collections.sort(llibres.getLlibre(), Comparator.comparingInt(Llibre::getAny));

            // Imprimir los libros por consola
            for (Llibre llibre : llibres.getLlibre()) {
                System.out.println("Autor: " + llibre.getAutor());
                System.out.println("Título: " + llibre.getTitol());
                System.out.println("Año: " + llibre.getAny());
                System.out.println("Resumen: " + llibre.getResum());
                System.out.println("----");
            }

            // Guardar el resultado en un archivo de texto
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
            e.printStackTrace();
        }
    }
}
*/

/*
Ejercicio: Mapeo de un documento XML a objetos Java utilizando JAXB
(por todos)
Objetivo: Crear un programa Java que utilice JAXB para leer un documento XML que contiene información sobre libros y convertirlo en objetos Java.
Imprime por consola el resultado de todos los campos de XML:


(Sólo por No-DUAL)
Haz un archivo de texto con el resultado de la consola. Ordena los resultados por orden cronológico del año de publicación.
*/