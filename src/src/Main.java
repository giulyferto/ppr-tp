import Interface.ILibroDAO;
import Objetos.Libro;
import ObjetosDAO.LibroDAPImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {

        ResultSet rs = null;
        Libro[] librosArr;
        //creamos un objeto de tipo conexion.
        Conexion conexion = new Conexion();

//        //dentro de conexion llamamos al `método conextar.
        Connection connection = conexion.conectar();

        try {
            // Creamos un objeto Statement a partir de la conexión.
            Statement stmt = connection.createStatement();

            // Se crea la tabla libros
            ILibroDAO libroDAO = new LibroDAPImpl();
            libroDAO.crearTablaLibros(stmt);

            // Se crean libros
            // Correr solo la primera vez que se levanta
//            libroDAO.crearLibro(stmt, "El señor de los anillos", "J R R Tolkien", "Ficcion", 1954);
//            libroDAO.crearLibro(stmt, "El gran Gatsby", "F Scott Fitzgerald", "Ficcion", 1925);
//            libroDAO.crearLibro(stmt, "Matar a un Ruiseñor", " Harper Lee", "Ficcion", 1930);

            librosArr = libroDAO.crearObjetosLibro(stmt, rs);
            System.out.println("Libros existentes: \n");
            for (int i = 0; i < librosArr.length; i++) {
                System.out.println(librosArr[i].getNombre());
            }

            Libro[] librosFiltrado = libroDAO.buscarLibrosPorAtributo(stmt, "anioPublicacion", "1930");

            System.out.println("\nLibros encontrados: \n");
            for (int i = 0; i < librosFiltrado.length; i++) {
                System.out.println(librosFiltrado[i].getNombre());
            }
//
//            librosArr[0].setNombre("Nuevo Nombre");
//            libroDAO.actualizarLibro(stmt,librosArr[0]);
//            libroDAO.eliminarLibro(stmt,librosArr[0].getId());

            System.out.println("Libros existentes: \n");
            for (int i = 0; i < librosArr.length; i++) {
                System.out.println(librosArr[i].getNombre());
            }


            // Cerramos la conexión
            conexion.desconectar();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        new InterfaceGrafica(librosArr);
    }


}