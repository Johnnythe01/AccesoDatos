import java.util.jar.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class Main {

    public class Employee {
    private int id;
    private String name;
    private String gender;
    private int age;
    private String role;
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
    
    @Override
    public String toString() {
        return "Employee:: ID="+this.id+" Name=" + this.name + " Age=" + this.age + " Gender=" + this.gender +
                " Role=" + this.role;
    }
    
}
}


/*
U1EX02B

Ejercicio: Mapeo de un documento XML a objetos Java utilizando SAX
(por todos)
Objetivo: Crear un programa Java que utilice SAX para leer un documento XML que contiene
información sobre libros y convertirlo en objetos Java.
Imprime por consola el resultado de todos los campos de XML:

(Sólo por No-DUAL)
Haz un archivo de texto para cada uno de los años de publicación. Añade dentro de cada archivo lo mismo
que sacas por la consola.
*/