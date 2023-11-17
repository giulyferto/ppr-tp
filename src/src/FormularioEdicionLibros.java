import Objetos.Libro;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormularioEdicionLibros extends JFrame {
    private JTextField textFieldNombre;
    private JTextField textFieldAutor;
    private JTextField textFieldGenero;
    private JTextField textFieldAnioPublicacion;
    private JButton btnGuardar;

    public FormularioEdicionLibros(Libro libro) {
        // Configuración del formulario de edición
        setTitle("Editar Libro");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Cierra solo este formulario, no toda la aplicación

        // Inicialización de componentes
        textFieldNombre = new JTextField(libro.getNombre());
        textFieldAutor = new JTextField(libro.getAutor());
        textFieldGenero = new JTextField(libro.getGenero());
        textFieldAnioPublicacion = new JTextField(String.valueOf(libro.getAnioPublicacion()));
        btnGuardar = new JButton("Guardar");

        // Configuración del diseño del formulario
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        add(new JLabel("Nombre:"));
        add(textFieldNombre);
        add(new JLabel("Autor:"));
        add(textFieldAutor);
        add(new JLabel("Género:"));
        add(textFieldGenero);
        add(new JLabel("Año de Publicación:"));
        add(textFieldAnioPublicacion);
        add(btnGuardar);

        // Manejo del botón "Guardar"
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Aquí puedes obtener los valores editados y hacer lo que necesites
                String nuevoNombre = textFieldNombre.getText();
                String nuevoAutor = textFieldAutor.getText();
                String nuevoGenero = textFieldGenero.getText();
                int nuevoAnioPublicacion = Integer.parseInt(textFieldAnioPublicacion.getText());

                // Aquí puedes actualizar el libro con los nuevos valores
                libro.setNombre(nuevoNombre);
                libro.setAutor(nuevoAutor);
                libro.setGenero(nuevoGenero);
                libro.setAnioPublicacion(nuevoAnioPublicacion);

                // Aquí puedes hacer lo que necesites con el libro actualizado (puede ser guardar en la base de datos, etc.)

                // Cierra el formulario de edición
                dispose();
            }
        });

        // Hacer visible el formulario
        setVisible(true);
    }
}
