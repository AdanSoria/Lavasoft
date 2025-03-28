package Administrador;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */

/**
 *
 * @author Luis DC
 */
public class panelpedidosadmin extends javax.swing.JPanel {
private int idClienteSeleccionado = -1;
   private int idUsuarioActual;
    /**
     * Creates new form panelpedidos
     */
    public panelpedidosadmin() {
    initComponents();
    cargarServicios(); // Cargar servicios al iniciar
    actualizarTablaPedidos();
}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        txtusu1 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtusu3 = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        btnEditar = new javax.swing.JButton();
        btnRealizarPedido = new javax.swing.JButton();
        btnGenerarTicketP = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnBusqueda = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        txtusu2 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jComboBoxTipo = new javax.swing.JComboBox<>();
        txtusu5 = new javax.swing.JTextField();

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(181, 218, 240));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Roboto Bk", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Cliente");
        jPanel4.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 60, 24));

        jLabel5.setFont(new java.awt.Font("Roboto Bk", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Servicio");
        jPanel4.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, -1, 20));

        jLabel6.setFont(new java.awt.Font("Roboto Bk", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Total");
        jPanel4.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 300, 60, -1));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Cliente", "Fecha", "idServicio", "Peso", "Total"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jPanel4.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 100, 390, 370));

        jLabel3.setBackground(new java.awt.Color(181, 218, 240));
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("______________");
        jPanel4.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 110, 120, 20));

        txtusu1.setBackground(new java.awt.Color(181, 218, 240));
        txtusu1.setBorder(null);
        jPanel4.add(txtusu1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 100, 100, 20));

        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("______________");
        jPanel4.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 300, 100, -1));

        jLabel8.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Pedido");
        jPanel4.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 40, -1, -1));

        txtusu3.setBackground(new java.awt.Color(181, 218, 240));
        txtusu3.setBorder(null);
        txtusu3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtusu3ActionPerformed(evt);
            }
        });
        jPanel4.add(txtusu3, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 40, 220, -1));

        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("____________________________________");
        jPanel4.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 50, 250, -1));

        btnEditar.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        btnEditar.setForeground(new java.awt.Color(0, 0, 0));
        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/editar.png"))); // NOI18N
        btnEditar.setText("Editar");
        btnEditar.setBorder(null);
        btnEditar.setContentAreaFilled(false);
        btnEditar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnEditar.setVerifyInputWhenFocusTarget(false);
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });
        jPanel4.add(btnEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 430, 90, 40));

        btnRealizarPedido.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        btnRealizarPedido.setForeground(new java.awt.Color(0, 0, 0));
        btnRealizarPedido.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/tarea-realizada.png"))); // NOI18N
        btnRealizarPedido.setText("Realizar pedido");
        btnRealizarPedido.setBorder(null);
        btnRealizarPedido.setContentAreaFilled(false);
        btnRealizarPedido.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnRealizarPedido.setVerifyInputWhenFocusTarget(false);
        btnRealizarPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRealizarPedidoActionPerformed(evt);
            }
        });
        jPanel4.add(btnRealizarPedido, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 340, 140, 40));

        btnGenerarTicketP.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        btnGenerarTicketP.setForeground(new java.awt.Color(0, 0, 0));
        btnGenerarTicketP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/factura.png"))); // NOI18N
        btnGenerarTicketP.setText("Generar ticket");
        btnGenerarTicketP.setBorder(null);
        btnGenerarTicketP.setContentAreaFilled(false);
        btnGenerarTicketP.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnGenerarTicketP.setVerifyInputWhenFocusTarget(false);
        btnGenerarTicketP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarTicketPActionPerformed(evt);
            }
        });
        jPanel4.add(btnGenerarTicketP, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 380, 170, 40));

        btnEliminar.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        btnEliminar.setForeground(new java.awt.Color(0, 0, 0));
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/eliminar.png"))); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.setBorder(null);
        btnEliminar.setContentAreaFilled(false);
        btnEliminar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnEliminar.setVerifyInputWhenFocusTarget(false);
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        jPanel4.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 430, 100, 40));

        btnBusqueda.setFont(new java.awt.Font("Roboto Bk", 0, 14)); // NOI18N
        btnBusqueda.setForeground(new java.awt.Color(0, 0, 0));
        btnBusqueda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/buscar (1).png"))); // NOI18N
        btnBusqueda.setBorder(null);
        btnBusqueda.setContentAreaFilled(false);
        btnBusqueda.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnBusqueda.setVerifyInputWhenFocusTarget(false);
        btnBusqueda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBusquedaActionPerformed(evt);
            }
        });
        jPanel4.add(btnBusqueda, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 50, -1, -1));

        jLabel7.setFont(new java.awt.Font("Roboto Bk", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Peso");
        jPanel4.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, 50, 20));

        txtusu2.setBackground(new java.awt.Color(181, 218, 240));
        txtusu2.setBorder(null);
        txtusu2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtusu2ActionPerformed(evt);
            }
        });
        jPanel4.add(txtusu2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 240, 100, 20));

        jLabel12.setBackground(new java.awt.Color(181, 218, 240));
        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("______________");
        jPanel4.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 260, 100, -1));

        jComboBoxTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3" }));
        jComboBoxTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxTipoActionPerformed(evt);
            }
        });
        jPanel4.add(jComboBoxTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 180, -1, -1));

        txtusu5.setBackground(new java.awt.Color(181, 218, 240));
        txtusu5.setBorder(null);
        txtusu5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtusu5ActionPerformed(evt);
            }
        });
        jPanel4.add(txtusu5, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 290, 100, 20));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 590, 470));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtusu3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtusu3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtusu3ActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnRealizarPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRealizarPedidoActionPerformed
      
    try {
    int cliente = Integer.parseInt(txtusu1.getText());
    int idServicio = Integer.parseInt(jComboBoxTipo.getSelectedItem().toString());
    int peso = Integer.parseInt(txtusu2.getText());
    int total = Integer.parseInt(txtusu5.getText());

    try (Connection conn = Conexion.getConnection()) {
        String sql = "INSERT INTO dbo.Pedido (idCliente, idServicio, FechaCreacion, Peso, CostoTotal) " +
                     "VALUES (?, ?, GETDATE(), ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, cliente);
            stmt.setInt(2, idServicio);
            stmt.setDouble(3, peso);
            stmt.setDouble(4, total);
            
            int affectedRows = stmt.executeUpdate();
            
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int idPedido = generatedKeys.getInt(1);
                        JOptionPane.showMessageDialog(this, "Pedido realizado con éxito. ID: " + idPedido);
                        actualizarTablaPedidos();
                    }
                }
            }
        }
    }
} catch (SQLException e) {
    JOptionPane.showMessageDialog(this, "Error al realizar pedido: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    e.printStackTrace();
} catch (NumberFormatException e) {
    JOptionPane.showMessageDialog(this, "Por favor ingrese valores numéricos válidos", "Error", JOptionPane.ERROR_MESSAGE);
}
}    
    
    
    private void actualizarTablaPedidos() {
    DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
    model.setRowCount(0); // Limpiar la tabla
    
    try (Connection conn = Conexion.getConnection()) {
        String sql = "SELECT p.idPedido, c.Nombre AS Cliente, p.FechaRealizacion, " +
                     "p.idServicio, p.Peso, p.Total " +
                     "FROM dbo.Pedido p " +
                     "JOIN dbo.Cliente c ON p.idCliente = c.idCliente " +
                     "WHERE p.idCliente = ? " +
                     "ORDER BY p.FechaRealizacion DESC";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idClienteSeleccionado);
            ResultSet rs = stmt.executeQuery();
            
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            
            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("idPedido"),
                    rs.getString("Cliente"),
                    dateFormat.format(rs.getTimestamp("FechaRealizacion")),
                    rs.getInt("idServicio"),
                    rs.getDouble("Peso"),
                    rs.getDouble("Total")
                });
            }
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error al cargar pedidos: " + e.getMessage(), 
                                    "Error", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
    }
}
/*private double calcularTotal(int idServicio, double peso) {
    // Aquí implementa la lógica para calcular el total basado en el servicio y peso
    // Esto es un ejemplo - debes adaptarlo a tu modelo de negocio
    double precioBase = 0;
    double precioPorKilo = 0;
    
    // Obtener información del servicio de la base de datos
    try (Connection conn = Conexion.getConnection()) {
        String sql = "SELECT PrecioBase, PrecioPorKilo FROM dbo.Servicio WHERE idServicio = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idServicio);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                precioBase = rs.getDouble("PrecioBase");
                precioPorKilo = rs.getDouble("PrecioPorKilo");
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    
    return precioBase + (peso * precioPorKilo);

    }//GEN-LAST:event_btnRealizarPedidoActionPerformed
*/
    private void btnGenerarTicketPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarTicketPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnGenerarTicketPActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
     
    }//GEN-LAST:event_btnEliminarActionPerformed
    // En el constructor o un método de inicialización
/*private void cargarServicios() {
    try (Connection conn = Conexion.getConnection()) {
        String sql = "SELECT idServicio, Descripcion FROM dbo.Servicio";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            
            jComboBoxTipo.removeAllItems();
            while (rs.next()) {
                jComboBoxTipo.addItem(rs.getString("Descripcion"));
                // Opcional: guardar el idServicio como valor del item
                // jComboBoxTipo.addItem(new ServicioItem(rs.getInt("idServicio"), rs.getString("Descripcion")));
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}*/
    private void btnBusquedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBusquedaActionPerformed
        // TODO add your handling code here:
        String busqueda = JOptionPane.showInputDialog(this, "Ingrese ID o nombre del cliente:");
    
    if (busqueda != null && !busqueda.trim().isEmpty()) {
        try (Connection conn = Conexion.getConnection()) {
            String sql = "SELECT IdCliente, Nombre FROM dbo.Cliente WHERE IdCliente = ? OR Nombre LIKE ?";
            
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                // Intentar buscar por ID
                try {
                    int id = Integer.parseInt(busqueda);
                    stmt.setInt(1, id);
                    stmt.setString(2, "%" + busqueda + "%");
                } catch (NumberFormatException e) {
                    stmt.setInt(1, -1); // Valor que no coincidirá
                    stmt.setString(2, "%" + busqueda + "%");
                }
                
                ResultSet rs = stmt.executeQuery();
                
                if (rs.next()) {
                    idClienteSeleccionado = rs.getInt("IdCliente");
                    txtusu1.setText(rs.getString("Nombre"));
                } else {
                    JOptionPane.showMessageDialog(this, "Cliente no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al buscar cliente: " + e.getMessage(), 
                                        "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    }//GEN-LAST:event_btnBusquedaActionPerformed
    private void cargarServicios() {
    try (Connection conn = Conexion.getConnection()) {
        String sql = "SELECT idServicio, Nombre FROM dbo.Servicio WHERE Estado = 1"; // Asumiendo que hay un campo Estado para servicios activos
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            
            jComboBoxTipo.removeAllItems();
            while (rs.next()) {
                jComboBoxTipo.addItem(rs.getString("Nombre"));
            }
            
            // Validar que hay servicios disponibles
            if (jComboBoxTipo.getItemCount() == 0) {
                JOptionPane.showMessageDialog(this, "No hay servicios disponibles", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error al cargar servicios: " + e.getMessage(), 
                                    "Error", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
    }
}
    private void txtusu2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtusu2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtusu2ActionPerformed

    private void txtusu5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtusu5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtusu5ActionPerformed

    private void jComboBoxTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxTipoActionPerformed
        // TODO add your handling code here:
        //cargarServicios();                                           
    if (jComboBoxTipo.getSelectedItem() != null) {
        String servicioSeleccionado = jComboBoxTipo.getSelectedItem().toString();
        // Aquí podrías actualizar el precio base o mostrar información del servicio seleccionado
    }
    }//GEN-LAST:event_jComboBoxTipoActionPerformed
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBusqueda;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGenerarTicketP;
    private javax.swing.JButton btnRealizarPedido;
    private javax.swing.JComboBox<String> jComboBoxTipo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txtusu1;
    private javax.swing.JTextField txtusu2;
    private javax.swing.JTextField txtusu3;
    private javax.swing.JTextField txtusu5;
    // End of variables declaration//GEN-END:variables
}
