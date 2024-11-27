package Vista;
import Controlador.coneccion;
public class Main {

    public static void main(String[] args) {

        Vista vista = new Vista();
        vista.setVisible(true);
        
        coneccion db = new coneccion();
        if (db.testConexion()) {
            System.out.println("La conexión a la base de datos es válida.");
        } else {
            System.out.println("Error al conectar con la base de datos.");
        }
    }
}
