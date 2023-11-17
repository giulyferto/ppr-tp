package ObjetosDAO;

import Interface.ILibroDAO;
import Objetos.Libro;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LibroDAPImpl implements ILibroDAO {
    @Override
    public void crearTablaLibros(Statement stmt) {

        String crearTablaSQL = "CREATE TABLE IF NOT EXISTS Libros ("
                + "id INT AUTO_INCREMENT PRIMARY KEY,"
                + "nombre VARCHAR(255) NOT NULL,"
                + "autor VARCHAR(255) NOT NULL,"
                + "genero VARCHAR(255) NOT NULL,"
                + "anioPublicacion INT NOT NULL)";
        try {
            stmt.executeUpdate(crearTablaSQL);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void crearLibro(Statement stmt, String nombre, String autor, String genero, int anioPublicacion) {
        try {
            // Construir la consulta SQL para insertar un nuevo libro
            String crearLibro = "INSERT INTO Libros (nombre, autor, genero, anioPublicacion) " +
                    "VALUES ('" + nombre + "', '" + autor + "', '" + genero + "', " + anioPublicacion + ")";

            // Ejecutar la consulta SQL y obtener el ID generado automáticamente
            stmt.executeUpdate(crearLibro, Statement.RETURN_GENERATED_KEYS);

            // Obtener el ID generado automáticamente
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                int idGenerado = generatedKeys.getInt(1);
                System.out.println("Libro creado con ID: " + idGenerado);
            } else {
                System.out.println("No se pudo obtener el ID del libro creado.");
            }

        } catch (SQLException e) {
            System.out.println("Error al crear el libro: " + e.getMessage());
        }
    }

    @Override
    public Libro[] crearObjetosLibro(Statement stmt, ResultSet rs) {
        //Se crea la cantidad de objetos en el array
        int i = 0;
        try {
            rs = stmt.executeQuery("SELECT count(*)		 FROM LIBROS");
            rs.next();
            i = rs.getInt(1);
            Libro ArryLibros[] = new Libro[i];

            //Se rellenan los objetos del array
            rs = stmt.executeQuery("SELECT * FROM LIBROS;");
            rs.next();

            for (int a = 0; a <= i - 1; a++) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String autor = rs.getString("autor");
                String genero = rs.getString("genero");
                int anioPublicacion = rs.getInt("anioPublicacion");
                ArryLibros[a] = new Libro(id, nombre, autor, genero, anioPublicacion);
                rs.next();

            }
            return ArryLibros;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Libro[] buscarLibrosPorAtributo(Statement stmt, String atributo, String valor) {
        try {
            // Consulta SQL dinámica basada en el atributo y valor proporcionados
            String query = "SELECT * FROM Libros WHERE " + atributo + " = '" + valor + "'";
            ResultSet rs = stmt.executeQuery(query);

            // Crear un array para almacenar los libros encontrados
            List<Libro> librosEncontrados = new ArrayList<>();

            // Llenar el array con los libros encontrados
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String autor = rs.getString("autor");
                String genero = rs.getString("genero");
                int anioPublicacion = rs.getInt("anioPublicacion");

                Libro libro = new Libro(id, nombre, autor, genero, anioPublicacion);
                librosEncontrados.add(libro);
            }

            // Convertir la lista a un array
            return librosEncontrados.toArray(new Libro[0]);
        } catch (SQLException e) {
            // Manejo de excepciones
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public void actualizarLibro(Statement stmt, Libro libro) {
        try {
            // Crear la sentencia de actualización
            String sql = "UPDATE Libros SET nombre='" + libro.getNombre() +
                    "', autor='" + libro.getAutor() +
                    "', genero='" + libro.getGenero() +
                    "', anioPublicacion=" + libro.getAnioPublicacion() +
                    " WHERE id=" + libro.getId();

            // Ejecutar la actualización
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            // Manejar excepciones
            e.printStackTrace();
        }
    }

    @Override
    public void eliminarLibro(Statement stmt, int id) {
        try {
            // Sentencia SQL para eliminar el libro con el ID proporcionado
            String sql = "DELETE FROM Libros WHERE id = " + id;

            // Ejecutar la sentencia SQL
            stmt.executeUpdate(sql);

            System.out.println("Libro eliminado con éxito.");

        } catch (SQLException e) {
            System.out.println("Error al eliminar el libro: " + e.getMessage());
        }
    }
}
