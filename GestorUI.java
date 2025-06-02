package mx.uam;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class GestorUI extends JFrame {
    private final Gestor gestor;

    private final JTextField idField;
    private final JTextField generoField;
    private final JTextField nombreField;
    private final JTextField edadField;
    private final JTextField pesoField;
    private final JTextField estaturaField;
    private final JTextArea salidaArea;

    public GestorUI() {
        gestor = new Gestor();
        setTitle("Gestor de Personas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(7, 2));
        inputPanel.add(new JLabel("ID:"));
        idField = new JTextField();
        inputPanel.add(idField);

        inputPanel.add(new JLabel("Género (M/F):"));
        generoField = new JTextField();
        inputPanel.add(generoField);

        inputPanel.add(new JLabel("Nombre:"));
        nombreField = new JTextField();
        inputPanel.add(nombreField);

        inputPanel.add(new JLabel("Edad:"));
        edadField = new JTextField();
        inputPanel.add(edadField);

        inputPanel.add(new JLabel("Peso (kg):"));
        pesoField = new JTextField();
        inputPanel.add(pesoField);

        inputPanel.add(new JLabel("Estatura (m):"));
        estaturaField = new JTextField();
        inputPanel.add(estaturaField);

        JButton agregarBtn = new JButton("Agregar");
        agregarBtn.addActionListener(this::agregarPersona);
        inputPanel.add(agregarBtn);

        JButton actualizarBtn = new JButton("Actualizar");
        actualizarBtn.addActionListener(this::actualizarPersona);
        inputPanel.add(actualizarBtn);

        JButton eliminarBtn = new JButton("Eliminar");
        eliminarBtn.addActionListener(this::eliminarPersona);
        inputPanel.add(eliminarBtn);

        JButton listarBtn = new JButton("Listar");
        listarBtn.addActionListener(this::listarPersonas);
        inputPanel.add(listarBtn);

        add(inputPanel, BorderLayout.NORTH);

        salidaArea = new JTextArea();
        salidaArea.setEditable(false);
        add(new JScrollPane(salidaArea), BorderLayout.CENTER);

        setVisible(true);
    }

    private void agregarPersona(ActionEvent e) {
        if (!camposValidos()) return;

        int id = Integer.parseInt(idField.getText());
        char genero = generoField.getText().toUpperCase().charAt(0);
        String nombre = nombreField.getText().trim();
        int edad = Integer.parseInt(edadField.getText());
        float peso = Float.parseFloat(pesoField.getText());
        float estatura = Float.parseFloat(estaturaField.getText());

        Persona p = new Persona(id, genero, nombre, edad, peso, estatura);
        if (gestor.agregarPersona(p)) {
            salidaArea.setText("Persona agregada.");
        } else {
            salidaArea.setText("Ya existe una persona con ese ID.");
        }
    }

    private void actualizarPersona(ActionEvent e) {
        if (!camposValidos()) return;

        int id = Integer.parseInt(idField.getText());
        char genero = generoField.getText().toUpperCase().charAt(0);
        String nombre = nombreField.getText().trim();
        int edad = Integer.parseInt(edadField.getText());
        float peso = Float.parseFloat(pesoField.getText());
        float estatura = Float.parseFloat(estaturaField.getText());

        Persona nueva = new Persona(id, genero, nombre, edad, peso, estatura);
        if (gestor.actualizarPersona(id, nueva)) {
            salidaArea.setText("Persona actualizada.");
        } else {
            salidaArea.setText("No se encontró la persona con ID: " + id);
        }
    }

    private void eliminarPersona(ActionEvent e) {
        try {
            int id = Integer.parseInt(idField.getText());
            if (gestor.eliminarPersona(id)) {
                salidaArea.setText("Persona eliminada.");
            } else {
                salidaArea.setText("ID no encontrado.");
            }
        } catch (NumberFormatException ex) {
            salidaArea.setText("ID inválido.");
        }
    }

    private void listarPersonas(ActionEvent e) {
        StringBuilder sb = new StringBuilder();
        for (Persona p : gestor.listarPersonas().values()) {
            sb.append(p).append("\n");
        }
        salidaArea.setText(sb.toString());
    }

    private boolean camposValidos() {
        if (idField.getText().isEmpty() || generoField.getText().isEmpty() || nombreField.getText().trim().isEmpty()
                || edadField.getText().isEmpty() || pesoField.getText().isEmpty() || estaturaField.getText().isEmpty()) {
            salidaArea.setText("Todos los campos son obligatorios.");
            return false;
        }
        try {
            Integer.parseInt(idField.getText());
            Integer.parseInt(edadField.getText());
            Float.parseFloat(pesoField.getText());
            Float.parseFloat(estaturaField.getText());

            String genero = generoField.getText().toUpperCase();
            if (genero.length() != 1 || !(genero.equals("M") || genero.equals("F"))) {
                salidaArea.setText("Género debe ser 'M' o 'F'.");
                return false;
            }
        } catch (NumberFormatException e) {
            salidaArea.setText("Edad, ID, Peso y Estatura deben ser valores numéricos.");
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GestorUI::new);
    }
}
