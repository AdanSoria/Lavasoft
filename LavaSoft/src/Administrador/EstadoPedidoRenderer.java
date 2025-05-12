/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Administrador;

/**
 *
 * @author soria
 */
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.Color;

public class EstadoPedidoRenderer extends DefaultTableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        
        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        if (value != null) {
            String estado = value.toString();
            switch (estado) {
                case "Pendiente":
                    c.setForeground(Color.RED);
                    break;
                case "Proceso":
                    c.setForeground(Color.ORANGE);
                    break;
                case "Listo":
                    c.setForeground(new Color(0, 153, 0)); // Verde oscuro
                    break;
                default:
                    c.setForeground(Color.BLACK);
                    break;
            }
        } else {
            c.setForeground(Color.BLACK);
        }

        return c;
    }
}
