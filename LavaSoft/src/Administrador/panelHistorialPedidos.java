/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Administrador;
import java.awt.Component;
import java.awt.Font;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.sql.Timestamp;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
/**
 *
 * @author soria
 */
public class panelHistorialPedidos extends javax.swing.JPanel {

    /**
     * Creates new form panelHistorialPedidos
     */
    public panelHistorialPedidos() {
        initComponents();
        personalizarTabla();
        actualizarTablaPedidos();
        //Panel Mejorado 
         jPanel4.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    
    // Mejorar el scroll pane
    jScrollPane1.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createEmptyBorder(5, 0, 5, 0),
        BorderFactory.createLineBorder(new Color(200, 200, 200))
    ));
    
    }
    private void personalizarTabla() {
    // 1. Configuración de estilos para la tabla
    tblPedido.setFont(new Font("Segoe UI", Font.PLAIN, 12));
    tblPedido.setRowHeight(25);
    tblPedido.setShowGrid(true);
    tblPedido.setGridColor(new Color(220, 220, 220));
    tblPedido.setSelectionBackground(new Color(181, 218, 240));
    tblPedido.setSelectionForeground(Color.BLACK);
    
    // 2. Personalización del header
    JTableHeader header = tblPedido.getTableHeader();
    header.setFont(new Font("Segoe UI", Font.BOLD, 13));
    header.setBackground(new Color(70, 130, 180)); // Azul acero
    header.setForeground(Color.BLACK);
    header.setReorderingAllowed(false);
    
    // 3. Renderizado personalizado para columnas
    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
    centerRenderer.setHorizontalAlignment(JLabel.CENTER);
    
    // Aplicar alineación centrada a columnas específicas
    for(int i = 0; i < tblPedido.getColumnCount(); i++) {
        tblPedido.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
    }
    
    // 4. Ajustar ancho de columnas
    tblPedido.getColumnModel().getColumn(0).setPreferredWidth(40);  // ID
    tblPedido.getColumnModel().getColumn(1).setPreferredWidth(120); // Cliente
    tblPedido.getColumnModel().getColumn(2).setPreferredWidth(100); // Servicio
    tblPedido.getColumnModel().getColumn(3).setPreferredWidth(120); // Fecha pedido
    tblPedido.getColumnModel().getColumn(4).setPreferredWidth(120); // Fecha estimada
    tblPedido.getColumnModel().getColumn(5).setPreferredWidth(60);  // Unidad
    tblPedido.getColumnModel().getColumn(6).setPreferredWidth(80);  // Costo
    tblPedido.getColumnModel().getColumn(7).setPreferredWidth(100); // Estado
    tblPedido.getColumnModel().getColumn(8).setPreferredWidth(150); // Detalles
    
    // 5. Alternar colores de filas
    tblPedido.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
            Component c = super.getTableCellRendererComponent(table, value, 
                isSelected, hasFocus, row, column);
            
            if (!isSelected) {
                c.setBackground(row % 2 == 0 ? Color.WHITE : new Color(240, 240, 240));
            }
            return c;
        }
    });
    // Añade esto al método personalizarTabla()
tblPedido.getColumnModel().getColumn(6).setCellRenderer(new DefaultTableCellRenderer() {
    private final DecimalFormat df = new DecimalFormat("$#,##0.00");
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
        boolean isSelected, boolean hasFocus, int row, int column) {
        
        if (value instanceof Number) {
            value = df.format(value);
        }
        return super.getTableCellRendererComponent(table, value, isSelected, 
            hasFocus, row, column);
    }
});

// Para fechas
tblPedido.getColumnModel().getColumn(3).setCellRenderer(new DefaultTableCellRenderer() {
    private final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
        boolean isSelected, boolean hasFocus, int row, int column) {
        
        if (value instanceof Date) {
            value = sdf.format((Date)value);
        } else if (value instanceof Timestamp) {
            value = sdf.format(new Date(((Timestamp)value).getTime()));
        }
        return super.getTableCellRendererComponent(table, value, isSelected, 
            hasFocus, row, column);
    }
});
}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPedido = new javax.swing.JTable();
        txtNomCliente = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtConsultaPedido = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        btnGenerarTicketP = new javax.swing.JButton();
        btnBusqueda = new javax.swing.JButton();
        txtPeso = new javax.swing.JTextField();
        txtTotal = new javax.swing.JTextField();

        jPanel4.setBackground(new java.awt.Color(181, 218, 240));
        jPanel4.setMinimumSize(new java.awt.Dimension(1200, 1200));
        jPanel4.setPreferredSize(new java.awt.Dimension(700, 700));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblPedido.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Cliente", "Servicio", "Fecha del pedido", "Fecha Estimada", "Unidad", "Costo Total", "EstadoPedido", "Detalles Pedido"
            }
        ));
        tblPedido.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPedidoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblPedido);

        jPanel4.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 80, 770, 410));

        txtNomCliente.setBackground(new java.awt.Color(181, 218, 240));
        txtNomCliente.setBorder(null);
        jPanel4.add(txtNomCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 100, 100, 20));

        jLabel8.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Pedido");
        jPanel4.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, -1, -1));

        txtConsultaPedido.setBackground(new java.awt.Color(181, 218, 240));
        txtConsultaPedido.setBorder(null);
        txtConsultaPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtConsultaPedidoActionPerformed(evt);
            }
        });
        jPanel4.add(txtConsultaPedido, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 30, 390, -1));

        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("_______________________________________________________________________________");
        jPanel4.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 40, 400, -1));

        btnGenerarTicketP.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
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
        jPanel4.add(btnGenerarTicketP, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 20, 180, 50));

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
        jPanel4.add(btnBusqueda, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 40, -1, -1));

        txtPeso.setBackground(new java.awt.Color(181, 218, 240));
        txtPeso.setBorder(null);
        txtPeso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPesoActionPerformed(evt);
            }
        });
        jPanel4.add(txtPeso, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 270, 100, 20));

        txtTotal.setBackground(new java.awt.Color(181, 218, 240));
        txtTotal.setBorder(null);
        txtTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalActionPerformed(evt);
            }
        });
        jPanel4.add(txtTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 300, 100, 20));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 912, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 912, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 510, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 510, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblPedidoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPedidoMouseClicked
       ;
    }//GEN-LAST:event_tblPedidoMouseClicked

    private void txtConsultaPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtConsultaPedidoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtConsultaPedidoActionPerformed

    private void btnGenerarTicketPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarTicketPActionPerformed
     
    }//GEN-LAST:event_btnGenerarTicketPActionPerformed

    private void btnBusquedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBusquedaActionPerformed

    }//GEN-LAST:event_btnBusquedaActionPerformed

    private void txtPesoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPesoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPesoActionPerformed

    private void txtTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalActionPerformed
    private void actualizarTablaPedidos() {
     DefaultTableModel model = (DefaultTableModel) tblPedido.getModel();
    model.setRowCount(0);
    
    try (Connection conn = Conexion.getConnection()) {
        // USAR EL NOMBRE CORRECTO: FechaEntregaEstimada
        String sql = "SELECT p.idPedido, c.Nombre AS Cliente, s.Descripcion AS Servicio, " +
                     "p.FechaCreacion, p.FechaEntregaEstimada, p.Peso, p.CostoTotal, p.EstadoPedido " +
                     "FROM dbo.Pedido p " +
                     "JOIN dbo.Cliente c ON p.idCliente = c.idCliente " +
                     "JOIN dbo.Servicio s ON p.idServicio = s.idServicio " +
                     "ORDER BY p.FechaCreacion DESC";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            
            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("idPedido"),
                    rs.getString("Cliente"),
                    rs.getString("Servicio"),
                    dateFormat.format(rs.getTimestamp("FechaCreacion")),
                    // USAR EL NOMBRE CORRECTO AQUÍ TAMBIÉN
                    dateFormat.format(rs.getTimestamp("FechaEntregaEstimada")),
                    rs.getDouble("Peso"),
                    rs.getDouble("CostoTotal"),
                    rs.getString("EstadoPedido")
                });
            }
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error al cargar pedidos: " + e.getMessage(), 
                                    "Error", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
    }
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBusqueda;
    private javax.swing.JButton btnGenerarTicketP;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblPedido;
    private javax.swing.JTextField txtConsultaPedido;
    private javax.swing.JTextField txtNomCliente;
    private javax.swing.JTextField txtPeso;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}
