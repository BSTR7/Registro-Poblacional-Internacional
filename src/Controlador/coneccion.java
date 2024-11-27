package Controlador;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class coneccion {

    private static final String URL = "jdbc:mysql://localhost:3306/mundo"; // Verifica que la base de datos esté correctamente escrita
    private static final String USER = "root";
    private static final String PASS = "";

    private Connection conn;

    // Constructor
    public coneccion() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Clase del driver corregida
            conn = DriverManager.getConnection(URL, USER, PASS); // Conexión a la base de datos
            System.out.println("Conexión exitosa");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(coneccion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Método para probar la conexión
    public boolean testConexion() {
        try {
            if (conn != null && !conn.isClosed()) {
                return true; // Conexión válida
            }
        } catch (SQLException ex) {
            Logger.getLogger(coneccion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false; // Conexión inválida
    }

    // Método para cerrar la conexión
    public void cerrarConexion() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("Conexión cerrada");
            }
        } catch (SQLException ex) {
            Logger.getLogger(coneccion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Método para obetener Idiomas
    public List<String[]> obtenerIdiomas(String pais) {
        List<String[]> idiomas = new ArrayList<>();
        String sql = "SELECT idioma.nombreIdioma AS idioma, idioma.oficial AS es_oficial "
                + "FROM idioma "
                + "INNER JOIN pais ON idioma.codigoPais = pais.codigoPais "
                + "WHERE pais.nombrePais = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, pais);  // Asignamos el país a la consulta
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String nombreIdioma = rs.getString("idioma");

                    // Si 'es_oficial' es 1, es "Sí", si es 0, es "No"
                    String esOficial = rs.getInt("es_oficial") == 1 ? "Sí" : "No";

                    // Agregar el idioma y su estatus a la lista
                    idiomas.add(new String[]{nombreIdioma, esOficial});
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(coneccion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return idiomas;
    }

    // Método para obetener Cuidades
    public List<String[]> obtenerCiudades(String pais) {
        List<String[]> ciudades = new ArrayList<>();
        String sql = "SELECT ciudad.nombreCiudad AS nombre, ciudad.poblacionCiudad AS poblacion "
                + "FROM ciudad "
                + "INNER JOIN pais ON ciudad.codigoPais = pais.codigoPais "
                + "WHERE pais.nombrePais = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, pais);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String nombreCiudad = rs.getString("nombre");
                    String poblacionCiudad = String.valueOf(rs.getInt("poblacion"));
                    ciudades.add(new String[]{nombreCiudad, poblacionCiudad});
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(coneccion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ciudades;
    }

    public void agregarPais(String codigo, String nombre, String continente, int poblacion, boolean tipoGobierno) {
        String sql = "INSERT INTO pais (codigoPais, nombrePais, continentePais, poblacionPais, tipoGobierno) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, codigo);
            stmt.setString(2, nombre);
            stmt.setString(3, continente);
            stmt.setInt(4, poblacion);
            stmt.setBoolean(5, tipoGobierno);
            stmt.executeUpdate();
            System.out.println("País agregado exitosamente.");
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Error: El país con el código '" + codigo + "' ya existe.");
        } catch (SQLException e) {
            Logger.getLogger(coneccion.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public List<String[]> obtenerPaises() {
        List<String[]> paises = new ArrayList<>();
        String sql = "SELECT * FROM pais";

        try (PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                String codigo = rs.getString("codigoPais");
                String nombre = rs.getString("nombrePais");
                String continente = rs.getString("continentePais");
                String poblacion = String.valueOf(rs.getInt("poblacionPais"));
                String tipoGobierno = rs.getBoolean("tipoGobierno") ? "Democracia" : "Otro";
                paises.add(new String[]{codigo, nombre, continente, poblacion, tipoGobierno});
            }
            System.out.println("Datos obtenidos exitosamente.");
        } catch (SQLException e) {
            Logger.getLogger(coneccion.class.getName()).log(Level.SEVERE, null, e);
        }
        return paises;
    }

    public void modificarPais(String codigo, String nombre, String continente, int poblacion, boolean tipoGobierno) {
        String sql = "UPDATE pais SET nombrePais = ?, continentePais = ?, poblacionPais = ?, tipoGobierno = ? WHERE codigoPais = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nombre);
            stmt.setString(2, continente);
            stmt.setInt(3, poblacion);
            stmt.setBoolean(4, tipoGobierno);
            stmt.setString(5, codigo);
            stmt.executeUpdate();
            System.out.println("País modificado exitosamente.");
        } catch (SQLException e) {
            Logger.getLogger(coneccion.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void eliminarPais(String codigo) {
        String sql = "DELETE FROM pais WHERE codigoPais = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, codigo);
            stmt.executeUpdate();
            System.out.println("País eliminado exitosamente.");
        } catch (SQLException e) {
            Logger.getLogger(coneccion.class.getName()).log(Level.SEVERE, null, e);
        }
    }

}
