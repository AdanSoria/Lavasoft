/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Administrador;
import java.awt.Component;
import java.awt.Font;
import java.awt.Color;
import java.io.FileWriter;
import java.io.IOException;
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
        filtrarPedidos();
        actualizarTablaPorFiltroDeMes();
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
        btnGenerarReporte = new javax.swing.JButton();
        txtPeso = new javax.swing.JTextField();
        txtTotal = new javax.swing.JTextField();
        jbcmes = new javax.swing.JComboBox<>();
        jbcEstado = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

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

        jPanel4.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 770, 410));

        txtNomCliente.setBackground(new java.awt.Color(181, 218, 240));
        txtNomCliente.setBorder(null);
        jPanel4.add(txtNomCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 100, 100, 20));

        jLabel8.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Filtrar por:");
        jPanel4.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, -1, -1));

        btnGenerarReporte.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        btnGenerarReporte.setForeground(new java.awt.Color(0, 0, 0));
        btnGenerarReporte.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/factura.png"))); // NOI18N
        btnGenerarReporte.setText("Generar reporte");
        btnGenerarReporte.setBorder(null);
        btnGenerarReporte.setContentAreaFilled(false);
        btnGenerarReporte.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnGenerarReporte.setVerifyInputWhenFocusTarget(false);
        btnGenerarReporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarReporteActionPerformed(evt);
            }
        });
        jPanel4.add(btnGenerarReporte, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 20, 180, 50));

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

        jbcmes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecciona un mes", "enero", "febrero", "marzo", "abril", "mayo", "junio", "julio", "agosto", "septiembre", "octubre", "noviembre", "diciembre" }));
        jbcmes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbcmesActionPerformed(evt);
            }
        });
        jPanel4.add(jbcmes, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 30, 190, 40));

        jbcEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione", "Pendiente", "Proceso", "Listo" }));
        jbcEstado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jbcEstadoMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jbcEstadoMouseEntered(evt);
            }
        });
        jbcEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbcEstadoActionPerformed(evt);
            }
        });
        jPanel4.add(jbcEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 30, 160, 40));

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Estado");
        jPanel4.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 30, -1, -1));

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Mes");
        jPanel4.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 30, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 800, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 510, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 510, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblPedidoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPedidoMouseClicked
      
    }//GEN-LAST:event_tblPedidoMouseClicked

    private void filtrarPorEstado() {
    String estadoSeleccionado = (String) jbcEstado.getSelectedItem();

    DefaultTableModel modelo = (DefaultTableModel) tblPedido.getModel();
    modelo.setRowCount(0); // Limpiar la tabla antes de actualizarla

    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    try {
        con = Conexion.getConnection();
        String sql = "SELECT  p.idPedido, c.Nombre AS Cliente, s.Descripcion AS Servicio, p.FechaCreacion, p.FechaEntregaEstimada, p.Peso, p.CostoTotal, p.EstadoPedido\n" +
"FROM dbo.Pedido p\n" +
"JOIN dbo.Cliente c ON p.idCliente = c.idCliente \n" +
"JOIN dbo.Servicio s ON p.idServicio = s.idServicio \n" +
"WHERE Estadopedido = ?\n" +
"ORDER BY p.FechaCreacion DESC";
        ps = con.prepareStatement(sql);
        ps.setString(1, estadoSeleccionado);

        rs = ps.executeQuery();
       SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            
            while (rs.next()) {
                modelo.addRow(new Object[]{
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

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error al filtrar por estado: " + e.getMessage());
    } finally {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (con != null) con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

    
    
    private void btnGenerarReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarReporteActionPerformed
                                                
    try {
        // Obtener la ruta personalizada
        String carpeta = "C:\\Users\\Luis DC\\Downloads\\Lavasoft-main\\";

        // Obtener la fecha y hora actual para generar un nombre único
        String fecha = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

        // Generar el nombre del archivo con la fecha actual
        String ruta = carpeta + "reporte_pedidos_" + fecha + ".csv";

        // Crear el archivo CSV
        FileWriter writer = new FileWriter(ruta);

        // Escribir encabezados
        for (int i = 0; i < tblPedido.getColumnCount(); i++) {
            writer.write(tblPedido.getColumnName(i));
            if (i < tblPedido.getColumnCount() - 1) {
                writer.write(",");
            }
        }
        writer.write("\n");

        // Escribir filas
        for (int fila = 0; fila < tblPedido.getRowCount(); fila++) {
            for (int col = 0; col < tblPedido.getColumnCount(); col++) {
                Object valor = tblPedido.getValueAt(fila, col);
                writer.write(valor != null ? valor.toString() : "");
                if (col < tblPedido.getColumnCount() - 1) {
                    writer.write(",");
                }
            }
            writer.write("\n");
        }

        writer.close();
        JOptionPane.showMessageDialog(this, "Archivo CSV generado exitosamente en:\n" + ruta, "Éxito", JOptionPane.INFORMATION_MESSAGE);

    } catch (IOException e) {
        JOptionPane.showMessageDialog(this, "Error al generar el archivo CSV:\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    } 

    }//GEN-LAST:event_btnGenerarReporteActionPerformed

    private void txtPesoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPesoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPesoActionPerformed

    private void txtTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalActionPerformed

    private void jbcEstadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbcEstadoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jbcEstadoMouseClicked

    private void jbcEstadoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbcEstadoMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jbcEstadoMouseEntered

    private int obtenerNumeroMes(String nombreMes) {
    switch (nombreMes.toLowerCase()) {
        case "enero": return 1;
        case "febrero": return 2;
        case "marzo": return 3;
        case "abril": return 4;
        case "mayo": return 5;
        case "junio": return 6;
        case "julio": return 7;
        case "agosto": return 8;
        case "septiembre": return 9;
        case "octubre": return 10;
        case "noviembre": return 11;
        case "diciembre": return 12;
        default: return -1; // por si no coincide con ningún mes
    }
}

    private void actualizarTablaPorFiltroDeMes() {
    String mesSeleccionado = jbcmes.getSelectedItem().toString();

    // Validación por si no se selecciona un mes válido
    if (mesSeleccionado.equals("Selecciona un mes")) {
        actualizarTablaPedidos(); // Mostrar todos
        return;
    }

    // Convertir el índice del JComboBox a número de mes (enero = 1)
    int mesNumero = jbcmes.getSelectedIndex();

    DefaultTableModel modelo = (DefaultTableModel) tblPedido.getModel();
    modelo.setRowCount(0); // Limpiar tabla antes de actualizar

    try (Connection con = Conexion.getConnection()) {
        String sql = "SELECT  p.idPedido, c.Nombre AS Cliente, s.Descripcion AS Servicio, p.FechaCreacion, p.FechaEntregaEstimada, p.Peso, p.CostoTotal, p.EstadoPedido\n" +
"FROM dbo.Pedido p\n" +
"JOIN dbo.Cliente c ON p.idCliente = c.idCliente \n" +
"JOIN dbo.Servicio s ON p.idServicio = s.idServicio \n" +
"WHERE MONTH(p.FechaCreacion) = ?\n" +
"ORDER BY p.FechaCreacion DESC";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, mesNumero);

        ResultSet rs = ps.executeQuery();
         SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            
            while (rs.next()) {
                modelo.addRow(new Object[]{
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

    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error al actualizar tabla por filtro de mes: " + e.getMessage());
    }
}

 private void filtrarPedidos() {
    String mesSeleccionado = jbcmes.getSelectedItem().toString();

    // Validar que se haya seleccionado un mes válido
    if (mesSeleccionado.equals("Selecciona un mes")) {
        actualizarTablaPorFiltroDeMes();
        return;
    }

    // Convertir el mes a número
    int mesNumero = jbcmes.getSelectedIndex(); // enero=1, febrero=2, etc.

    DefaultTableModel modelo = (DefaultTableModel) tblPedido.getModel();
    modelo.setRowCount(0); // Limpiar tabla

    try (Connection con = Conexion.getConnection()) {
        String sql = "SELECT  p.idPedido, c.Nombre AS Cliente, s.Descripcion AS Servicio, p.FechaCreacion, p.FechaEntregaEstimada, p.Peso, p.CostoTotal, p.EstadoPedido\n" +
"FROM dbo.Pedido p\n" +
"JOIN dbo.Cliente c ON p.idCliente = c.idCliente \n" +
"JOIN dbo.Servicio s ON p.idServicio = s.idServicio \n" +
"WHERE MONTH(p.FechaCreacion) = ?\n" +
"ORDER BY p.FechaCreacion DESC";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, mesNumero);

        ResultSet rs = ps.executeQuery();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            
            while (rs.next()) {
                modelo.addRow(new Object[]{
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

    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error al filtrar por mes: " + ex.getMessage());
    }
}

    private void jbcEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbcEstadoActionPerformed
        // TODO add your handling code here:
        filtrarPorEstado();
    }//GEN-LAST:event_jbcEstadoActionPerformed

    private void jbcmesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbcmesActionPerformed
        // TODO add your handling code here:
actualizarTablaPorFiltroDeMes();        
    }//GEN-LAST:event_jbcmesActionPerformed
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
    private javax.swing.JButton btnGenerarReporte;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> jbcEstado;
    private javax.swing.JComboBox<String> jbcmes;
    private javax.swing.JTable tblPedido;
    private javax.swing.JTextField txtNomCliente;
    private javax.swing.JTextField txtPeso;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}
