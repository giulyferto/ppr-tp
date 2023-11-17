import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

//se crea una clase Conexion, luego las variables
public class Conexion {
    String bd="ppr_tp";
    //se crea la url con su servidor  localhost y puerto
    String url="jdbc:mysql://localhost:3306/";
    String user="root";
    String password="password";
    // se crea el nombre del driver es para la version 8.0
    String driver="com.mysql.cj.jdbc.Driver";
//    String driver="com.mysql.cj.jdbc.Driver";

    //variable global cx
    Connection cx;


    //se crea el método constructor

    public Conexion(){

    }

    public Connection conectar(){

        try {
            //comando para conectar class..
            Class.forName("com.mysql.cj.jdbc.Driver");

            //cadena de conexion url, la base de datos, usuario y contraseña
            cx = DriverManager.getConnection(url+bd,user, password);
            System.out.println("Se conecto a la base de datos " + bd);

        } catch (ClassNotFoundException | SQLException ex) {

            System.out.println("No se conecto a la base de datos" + bd);

            System.out.println("Exception " + ex);

        }
        return cx;
    }

    public void desconectar(){

        try {
            cx.close();
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }



}