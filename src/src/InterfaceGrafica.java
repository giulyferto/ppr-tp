import Objetos.Libro;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class InterfaceGrafica extends JFrame {
    private JPanel MainPanel;
    private JLabel LibrosLabel;
    private JTable tablaLibros;

    public InterfaceGrafica(Libro[] libros) {
        MainPanel.setBackground(new Color(176, 125, 55));
        setTitle("Gesti\u00F3n Biblioteca");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 957, 517);
        setContentPane(MainPanel);
//        // Tabla de libros
        Object[][] librosData = new Object[libros.length][5]; // 5 columnas por los 5 attr de libro

        for (int i = 0; i < libros.length; i++) {
            Libro libro = libros[i];
            librosData[i][0] = libro.getId();
            librosData[i][1] = libro.getNombre();
            librosData[i][2] = libro.getAutor();
            librosData[i][3] = libro.getGenero();
            librosData[i][4] = libro.getAnioPublicacion();
        }

        String[] columnasLibros = {
                "IDLibro", "Nombre", "Autor", "Genero", "Año de Publicación"
        };


// Crear el modelo de la tabla con los datos y nombres de las columnas
        DefaultTableModel modeloTablaLibros = new DefaultTableModel(librosData, columnasLibros);
        tablaLibros.setModel(modeloTablaLibros);

        tablaLibros.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.out.println("e" + e);
                int filaSeleccionada = tablaLibros.getSelectedRow();
                System.out.println("filaSeleccionada" + filaSeleccionada);
                if (filaSeleccionada >= 0) {
                    Libro libroSeleccionado = obtenerLibroDesdeFila(filaSeleccionada);
                    abrirFormularioEdicion(libroSeleccionado);
                }
            }
        });


        setVisible(true);


    }

    private Libro obtenerLibroDesdeFila(int fila) {
        // Obtener los datos de la fila seleccionada
        int id = (int) tablaLibros.getValueAt(fila, 0);
        String nombre = (String) tablaLibros.getValueAt(fila, 1);
        String autor = (String) tablaLibros.getValueAt(fila, 2);
        String genero = (String) tablaLibros.getValueAt(fila, 3);
        int anioPublicacion = (int) tablaLibros.getValueAt(fila, 4);

        // Crear y devolver un objeto Libro con los datos de la fila
        return new Libro(id, nombre, autor, genero, anioPublicacion);
    }

    private void abrirFormularioEdicion(Libro libro) {
        // Aquí puedes usar el objeto Libro para abrir el formulario de edición
        // Puedes pasar este objeto a tu formulario de edición y trabajar con él.
        // Por ejemplo, podrías abrir un nuevo JFrame o un cuadro de diálogo modal para la edición.
        // ...
        System.out.println("Abrir formulario de edición para el libro con ID: " + libro.getNombre());
        new FormularioEdicionLibros(libro);
    }
}

