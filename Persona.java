package mx.uam;

import java.io.Serializable;

public class Persona implements  Serializable{
    private int id;
    private char genero;
    private String nombre;
    private int edad;
    private float peso;
    private float estatura;

    // Contructores
    public Persona(int id1, char genero1, String nombre1, int edad1, float peso1, float estatura1){
        this.id = 1;
        this.genero = 'f';
        this.nombre = null;
        this.edad = 0;
        this.estatura =0.1f;
        this.peso = 2f;
    }

    public Persona(int id, char genero, String nombre, int edad, float peso){
        this.id = id;
        this.genero = genero;
        this.nombre = nombre;
        this.edad = edad;
        this.peso = peso;
    }

    // Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public char getGenero() {
        return genero;
    }

    public void setGenero(char genero) {
        this.genero = genero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public float getEstatura(){
        return this.estatura;
    }

    public void setEstatura(float estatura){
        this.estatura = estatura;
    }
    
    /**
     * Muestra los elementos de la clase persona en una cadena imprimible.
     */
    @Override
    public String toString(){
        StringBuilder str = new StringBuilder();
        String sep = ",";
        str.append(id);
        str.append(sep);
        str.append(genero);
        str.append(sep);
        str.append(nombre);
        str.append(sep);
        str.append(edad);
        str.append(sep);
        str.append(estatura);
        str.append(sep);
        str.append(peso);
        str.append("\n");
        return str.toString();
    }
    

}
