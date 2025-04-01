package Administrador;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.text.AbstractDocument.Content;
import java.sql.PreparedStatement;
import javax.swing.DefaultComboBoxModel;
import java.sql.ResultSet;


public class ServicioUtil {
    public static DefaultComboBoxModel<Servicio> cargarServiciosEnModelo() {
        DefaultComboBoxModel<Servicio> model = new DefaultComboBoxModel<>();
        
        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                 "SELECT IdServicio, TipoServicio, PrecioUnitario FROM Servicio");
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                model.addElement(new Servicio(
                    rs.getInt("IdServicio"),
                    rs.getString("TipoServicio"),
                    "",
                    rs.getFloat("PrecioUnitario")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return model;
    }
    
    public static Servicio obtenerServicioPorId(int idServicio) {
        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                 "SELECT * FROM Servicio WHERE IdServicio = ?")) {
            
            stmt.setInt(1, idServicio);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return new Servicio(
                    rs.getInt("IdServicio"),
                    rs.getString("TipoServicio"),
                    rs.getString("Descripcion"),
                    rs.getFloat("PrecioUnitario")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}