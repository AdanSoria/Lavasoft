/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Administrador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Luis DC
 */
public class Registrarse2 extends javax.swing.JFrame {

    /**
     * Creates new form Registrarse2
     */
    public Registrarse2() {
       try (Connection conn = Conexion.getConnection()) {
            System.out.println("Conexión exitosa:D.");
            
        } catch (SQLException e) {
            System.out.println("Error de conexión");
            e.printStackTrace();
            
        }
        
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        txtnombre = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        txttelefono = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        BarraDeBusqueda6 = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        txthorario = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        comboBoxPuesto = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(181, 218, 240));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Garamond", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Registrarse");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 0, -1, -1));

        jButton2.setText("Volver");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 330, 90, -1));

        jButton1.setText("Registrarse");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 300, 90, -1));
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 250, -1, -1));

        jLabel17.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("Puesto:");
        jPanel2.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, -1, -1));

        jLabel18.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("Nombre:");
        jPanel2.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, -1, -1));

        jLabel19.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("Telefono:");
        jPanel2.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 210, -1, -1));

        txtnombre.setBackground(new java.awt.Color(181, 218, 240));
        txtnombre.setForeground(new java.awt.Color(0, 0, 0));
        txtnombre.setBorder(null);
        txtnombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnombreActionPerformed(evt);
            }
        });
        jPanel2.add(txtnombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 100, 250, 20));

        jLabel20.setForeground(new java.awt.Color(0, 0, 0));
        jLabel20.setText("________________________");
        jPanel2.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 110, -1, -1));

        jLabel21.setForeground(new java.awt.Color(0, 0, 0));
        jLabel21.setText("________________________");
        jPanel2.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 110, -1, -1));

        txttelefono.setBackground(new java.awt.Color(181, 218, 240));
        txttelefono.setForeground(new java.awt.Color(0, 0, 0));
        txttelefono.setBorder(null);
        jPanel2.add(txttelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 210, 250, 20));

        jLabel22.setForeground(new java.awt.Color(0, 0, 0));
        jLabel22.setText("________________________");
        jPanel2.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 220, -1, -1));

        jLabel23.setForeground(new java.awt.Color(0, 0, 0));
        jLabel23.setText("________________________");
        jPanel2.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 220, -1, -1));

        BarraDeBusqueda6.setBackground(new java.awt.Color(118, 120, 237));
        BarraDeBusqueda6.setForeground(new java.awt.Color(0, 0, 0));
        BarraDeBusqueda6.setBorder(null);
        jPanel2.add(BarraDeBusqueda6, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 210, 250, 20));

        jLabel24.setForeground(new java.awt.Color(0, 0, 0));
        jLabel24.setText("________________________");
        jPanel2.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 260, -1, -1));

        txthorario.setBackground(new java.awt.Color(181, 218, 240));
        txthorario.setForeground(new java.awt.Color(0, 0, 0));
        txthorario.setBorder(null);
        jPanel2.add(txthorario, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 250, 250, 20));

        jLabel25.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(0, 0, 0));
        jLabel25.setText("Horario:");
        jPanel2.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, -1, -1));

        comboBoxPuesto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Empleado", "Administrador" }));
        comboBoxPuesto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxPuestoActionPerformed(evt);
            }
        });
        jPanel2.add(comboBoxPuesto, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 150, 120, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:

        this.setVisible(false);
        IniciaSesion p=new IniciaSesion();
        p.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:

          try (Connection conn = Conexion.getConnection()) {
    String insertSql = "INSERT INTO dbo.Usuario (Nombre, Puesto, Telefono, Turno,Contraseña) VALUES (?, ?, ?, ?, ?)";
    PreparedStatement stmt = conn.prepareStatement(insertSql);
    
    String nombre = txtnombre.getText();
     String puesto = (String) comboBoxPuesto.getSelectedItem();
    String telefono = txttelefono.getText();
    String turno = txthorario.getText();
    
    String Contraseña = JOptionPane.showInputDialog("Ingresar contraseña");
    
    stmt.setString(1, nombre);
    stmt.setString(2, puesto);
    stmt.setString(3, telefono);
    stmt.setString(4, turno);
    stmt.setString(5, Contraseña);
    
    int filasAfectadas = stmt.executeUpdate();
    
    if (filasAfectadas > 0) {
        System.out.println("Empleado insertado correctamente.");
        
        String selectSql = "SELECT * FROM dbo.Usuario";
        try (PreparedStatement selectStmt = conn.prepareStatement(selectSql); var rs = selectStmt.executeQuery()) {
           while (rs.next()) {
                Object[] row = {
                rs.getInt("IdUsuario"), 
                rs.getString("Nombre"),
                rs.getString("Puesto"),
                rs.getString("Telefono"),
                rs.getString("Turno")
                    };
       
    }

        }
    } else {
        System.out.println("Error al insertar el empleado.");
    }
} catch (SQLException e) {
    e.printStackTrace();
}

        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtnombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnombreActionPerformed

    private void comboBoxPuestoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxPuestoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboBoxPuestoActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Registrarse2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Registrarse2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Registrarse2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Registrarse2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Registrarse2().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField BarraDeBusqueda6;
    private javax.swing.JComboBox<String> comboBoxPuesto;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField txthorario;
    private javax.swing.JTextField txtnombre;
    private javax.swing.JTextField txttelefono;
    // End of variables declaration//GEN-END:variables
}
