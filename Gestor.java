package mx.uam;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map;
import java.util.TreeMap;

public class Gestor {
    private TreeMap<Integer, Persona> personas;

    private final String archivo = "personas.ser";

    public Gestor() {
        personas = cargar();
    }

    /**
     * Este método agrega un objeto persona a una árbol
     * @param p Objeto Persona
     * @return True si el objeto fue correctamente agregado, falso de lo contrario.
     */
    public boolean agregarPersona(Persona p) {
        if (personas.containsKey(p.getId())) return false;
        personas.put(p.getId(), p);
        guardar();
        return true;
    }

    public boolean eliminarPersona(int id) {
        if (personas.remove(id) != null) {
            guardar();
            return true;
        }
        return false;
    }

    public boolean actualizarPersona(int id, Persona nueva) {
        if (personas.containsKey(id)) {
            personas.put(id, nueva);
            guardar();
            return true;
        }
        return false;
    }


    public Map<Integer, Persona> listarPersonas() {
        return personas;
    }
    
    /**
     * 
     */
    private void guardar() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
            oos.writeObject(personas);
        } catch (IOException e) {
            System.err.println("Error al guardar: " + e.getMessage());
        }
    }

    /**
     * 
     * @return
     */
    private TreeMap<Integer, Persona> cargar() {
        File f = new File(archivo);
        if (!f.exists()) return new TreeMap<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            return (TreeMap<Integer, Persona>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al cargar: " + e.getMessage());
            return new TreeMap<>();
        }
    }
}


