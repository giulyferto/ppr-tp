package Interface;

import Objetos.Libro;

import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public interface ILibroDAO {

   // Este método crea la tabla libros en la base de datos.
    void crearTablaLibros(Statement stmt);

    // Este método le da los valores a la tabla libros de la base de datos.
    void crearLibro(Statement stmt, String nombre, String autor, String genero, int anioPublicacion);

    // Este método pasa la información de la tabla libros a un array de objetos tipo Libro y retorna dicho array.
    Libro[] crearObjetosLibro(Statement stmt, ResultSet rs);

    // Obtiene un libro por su ID.
    Libro[] buscarLibrosPorAtributo(Statement stmt, String atributo, String valor);

    // Actualiza la información de un libro.
    void actualizarLibro(Statement stmt, Libro libro);

    // Elimina un libro por su ID.
    void eliminarLibro(Statement stmt, int id);
}
