package Administrador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.text.AbstractDocument.Content;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author Luis DC
 */
public class FrameMenu extends javax.swing.JFrame {
    
    
    
    private javax.swing.Timer timer;
    private int idUsuarioActual;
    // Colores para el diseño minimalista
    private final Color COLOR_FONDO = new Color(245, 247, 250);
    private final Color COLOR_MENU = new Color(255, 255, 255);
    private final Color COLOR_ACTIVO = new Color(74, 111, 165);
    private final Color COLOR_TEXTO = new Color(60, 60, 60);
    private final Color COLOR_TEXTO_ACTIVO = new Color(255, 255, 255);
    private final Color COLOR_BORDE = new Color(230, 230, 230);
    
    public void setIdUsuarioActual(int idUsuario) {
        this.idUsuarioActual = idUsuario;
    }
    
    public FrameMenu() {
        initComponents();
        aplicarEstiloMinimalista();
    ajustarTamañoPanel();
    
    jLabel16.setFont(new Font("Segoe UI", Font.BOLD, 12));
    jLabel16.setHorizontalAlignment(SwingConstants.CENTER);
    initDateTimeUpdater();
    }
    
    private void initDateTimeUpdater() {
    timer = new Timer(1000, e -> actualizarFechaHora()); // Actualiza cada segundo
    timer.start();
    actualizarFechaHora(); // Mostrar inmediatamente
}
    private void actualizarFechaHora() {
    // Formato día/mes/año hora:minuto:segundo AM/PM
    //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss a");
     DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE"+"\n"+", dd/MM/yyyy hh:mm:ss a");
    String fechaHora = LocalDateTime.now().format(formatter);
    
    // Opcional: con día de la semana
    
     jLabel16.setText("<html><div style='text-align: center;'>" + 
                    fechaHora + "<br>" + 
                      "</div></html>");
    
    
    // Opcional: cambiar color según la hora del día
    LocalTime now = LocalTime.now();
    if (now.isAfter(LocalTime.of(18, 0))) {
        jLabel16.setForeground(new Color(255, 150, 150)); // Noche
    } else if (now.isAfter(LocalTime.of(12, 0))) {
        jLabel16.setForeground(new Color(100, 150, 255)); // Tarde
    } else {
        jLabel16.setForeground(new Color(80, 180, 80)); // Mañana
    }
}
    // En la clase FrameMenu
public void mostrarInfoUsuario(String nombreUsuario, String puesto) {
    // Muestra el nombre en jLabel2
    jLabel2.setText(nombreUsuario);
    
    // Muestra el puesto en jLabel14 (opcional)
    jLabel14.setText(puesto);
    
    // Personalización visual opcional
    jLabel2.setFont(new Font("Segoe UI", Font.BOLD, 12));
    jLabel2.setForeground(new Color(70, 70, 70));
}

    // Añade este método a tu clase FrameMenu:
public void ajustarTamañoPanel() {
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int ancho = screenSize.width - 170; // Restamos el ancho del menú
    int alto = screenSize.height;
    
    paneli.setPreferredSize(new Dimension(ancho, alto));
    paneli.revalidate();
}

// Llama a este método después de inicializar los componentes:
    private void aplicarEstiloMinimalista() {
        // Fondo principal
        getContentPane().setBackground(COLOR_FONDO);
        
        // Panel del menú
        panelop.setBackground(COLOR_MENU);
        panelop.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, COLOR_BORDE));
        
        // Estilo para los botones del menú
        estiloBotonMenu(jpanelb1, jLabel4);
        estiloBotonMenu(jpanelb2, jLabel7);
        estiloBotonMenu(jpanelb3, jLabel9);
        estiloBotonMenu(jpanelb4, jLabel10);
        
        // Títulos
        jLabel3.setFont(new Font("Segoe UI", Font.BOLD, 18));
        jLabel3.setForeground(COLOR_TEXTO);
        jLabel14.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        jLabel14.setForeground(new Color(120, 120, 120));
        
        // Botón de cerrar sesión
        jButton4.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        jButton4.setForeground(new Color(100, 100, 100));
        jButton4.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        jButton4.setHorizontalAlignment(SwingConstants.LEFT);
        
        // Panel de contenido
        paneli.setBackground(COLOR_FONDO);
        paneli.setBackground(COLOR_FONDO);
        
        // Logo/Imagen central
        jLabel12.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));
    }

    private void estiloBotonMenu(JPanel panel, JLabel label) {
        panel.setBackground(COLOR_MENU);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        
        label.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        label.setForeground(COLOR_TEXTO);
        label.setIconTextGap(15);
        
        panel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    // Métodos setColor y resetColor actualizados
    void setColor(JPanel jpanelb) {
        jpanelb.setBackground(COLOR_ACTIVO);
        for (Component comp : jpanelb.getComponents()) {
            if (comp instanceof JLabel) {
                JLabel label = (JLabel) comp;
                label.setForeground(COLOR_TEXTO_ACTIVO);
            }
        }
    }
    
    void resetColor(JPanel jpanelb) {
        jpanelb.setBackground(COLOR_MENU);
        for (Component comp : jpanelb.getComponents()) {
            if (comp instanceof JLabel) {
                JLabel label = (JLabel) comp;
                label.setForeground(COLOR_TEXTO);
            }
        }
    }
 
    
     /*void setColor(JPanel jpanelb){
    jpanelb.setBackground(new Color(181,218,240));
    }
    
    void resetColor(JPanel jpanelb){
    jpanelb.setBackground(new Color(255,255,255));
    } 
*/
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel7 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        panelinicio = new javax.swing.JPanel();
        panelop = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jpanelb1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jpanelb2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jpanelb3 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jpanelb4 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jpanelb5 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jpanelb6 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        paneli = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();

        jPanel7.setBackground(new java.awt.Color(0, 0, 255));

        jLabel5.setFont(new java.awt.Font("Manrope ExtraBold", 0, 36)); // NOI18N
        jLabel5.setText("LISTA DE PEDIDOS ");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addGap(71, 71, 71))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(131, 131, 131)
                .addComponent(jLabel5)
                .addContainerGap(149, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(50, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(10, 10, 10)
                .addComponent(jLabel8)
                .addGap(23, 23, 23))
        );

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Imagen_de_WhatsApp_2024-11-21_a_las_16.51.39_2c052284-removebg-preview.png"))); // NOI18N

        panelinicio.setBackground(new java.awt.Color(118, 120, 237));

        javax.swing.GroupLayout panelinicioLayout = new javax.swing.GroupLayout(panelinicio);
        panelinicio.setLayout(panelinicioLayout);
        panelinicioLayout.setHorizontalGroup(
            panelinicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 560, Short.MAX_VALUE)
        );
        panelinicioLayout.setVerticalGroup(
            panelinicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 480, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelop.setBackground(new java.awt.Color(255, 255, 255));
        panelop.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setBackground(new java.awt.Color(118, 120, 237));
        jLabel2.setForeground(new java.awt.Color(118, 120, 237));
        jLabel2.setText("Nombre Empleado");
        panelop.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, 150, 20));

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Bienvenido");
        panelop.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, 110, 20));
        panelop.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, 60, -1));

        jButton4.setFont(new java.awt.Font("Roboto Bk", 1, 14)); // NOI18N
        jButton4.setForeground(new java.awt.Color(0, 0, 0));
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/arrow (1).png"))); // NOI18N
        jButton4.setText("Cerrar sesión");
        jButton4.setBorder(null);
        jButton4.setContentAreaFilled(false);
        jButton4.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        panelop.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 430, 200, 50));

        jpanelb1.setBackground(new java.awt.Color(255, 255, 255));
        jpanelb1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jpanelb1MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jpanelb1MousePressed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Roboto Bk", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/business_13641453.png"))); // NOI18N
        jLabel4.setText("Empleados");

        javax.swing.GroupLayout jpanelb1Layout = new javax.swing.GroupLayout(jpanelb1);
        jpanelb1.setLayout(jpanelb1Layout);
        jpanelb1Layout.setHorizontalGroup(
            jpanelb1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpanelb1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(58, Short.MAX_VALUE))
        );
        jpanelb1Layout.setVerticalGroup(
            jpanelb1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
        );

        panelop.add(jpanelb1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 170, 200, 60));

        jpanelb2.setBackground(new java.awt.Color(255, 255, 255));
        jpanelb2.setForeground(new java.awt.Color(0, 0, 0));
        jpanelb2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jpanelb2MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jpanelb2MousePressed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Roboto Bk", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/washing-machine_8081669.png"))); // NOI18N
        jLabel7.setText("Pedidos");

        javax.swing.GroupLayout jpanelb2Layout = new javax.swing.GroupLayout(jpanelb2);
        jpanelb2.setLayout(jpanelb2Layout);
        jpanelb2Layout.setHorizontalGroup(
            jpanelb2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpanelb2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(61, Short.MAX_VALUE))
        );
        jpanelb2Layout.setVerticalGroup(
            jpanelb2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpanelb2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(14, 14, 14))
        );

        panelop.add(jpanelb2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 200, 60));

        jpanelb3.setBackground(new java.awt.Color(255, 255, 255));
        jpanelb3.setForeground(new java.awt.Color(0, 0, 0));
        jpanelb3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jpanelb3MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jpanelb3MousePressed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Roboto Bk", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/list_654116 (1).png"))); // NOI18N
        jLabel9.setText("Servicios");

        javax.swing.GroupLayout jpanelb3Layout = new javax.swing.GroupLayout(jpanelb3);
        jpanelb3.setLayout(jpanelb3Layout);
        jpanelb3Layout.setHorizontalGroup(
            jpanelb3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpanelb3Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(75, Short.MAX_VALUE))
        );
        jpanelb3Layout.setVerticalGroup(
            jpanelb3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpanelb3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE))
        );

        panelop.add(jpanelb3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 220, 200, 60));

        jpanelb4.setBackground(new java.awt.Color(255, 255, 255));
        jpanelb4.setForeground(new java.awt.Color(0, 0, 0));
        jpanelb4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jpanelb4MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jpanelb4MousePressed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Roboto Bk", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/support.png"))); // NOI18N
        jLabel10.setText("Clientes");

        javax.swing.GroupLayout jpanelb4Layout = new javax.swing.GroupLayout(jpanelb4);
        jpanelb4.setLayout(jpanelb4Layout);
        jpanelb4Layout.setHorizontalGroup(
            jpanelb4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpanelb4Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(69, Short.MAX_VALUE))
        );
        jpanelb4Layout.setVerticalGroup(
            jpanelb4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        panelop.add(jpanelb4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 280, 200, 50));

        jLabel14.setBackground(new java.awt.Color(255, 255, 255));
        jLabel14.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Administrador");
        panelop.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 150, 20));

        jpanelb5.setBackground(new java.awt.Color(255, 255, 255));
        jpanelb5.setForeground(new java.awt.Color(0, 0, 0));
        jpanelb5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jpanelb5MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jpanelb5MousePressed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Roboto Bk", 0, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/support.png"))); // NOI18N
        jLabel15.setText("Historial Pedidos");

        javax.swing.GroupLayout jpanelb5Layout = new javax.swing.GroupLayout(jpanelb5);
        jpanelb5.setLayout(jpanelb5Layout);
        jpanelb5Layout.setHorizontalGroup(
            jpanelb5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpanelb5Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jpanelb5Layout.setVerticalGroup(
            jpanelb5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpanelb5Layout.createSequentialGroup()
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        panelop.add(jpanelb5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 330, -1, -1));

        jpanelb6.setBackground(new java.awt.Color(255, 255, 255));
        jpanelb6.setForeground(new java.awt.Color(0, 0, 0));
        jpanelb6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jpanelb6MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jpanelb6MousePressed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Roboto Bk", 0, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("                   HORA");

        javax.swing.GroupLayout jpanelb6Layout = new javax.swing.GroupLayout(jpanelb6);
        jpanelb6.setLayout(jpanelb6Layout);
        jpanelb6Layout.setHorizontalGroup(
            jpanelb6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpanelb6Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );
        jpanelb6Layout.setVerticalGroup(
            jpanelb6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpanelb6Layout.createSequentialGroup()
                .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                .addContainerGap())
        );

        panelop.add(jpanelb6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 390, -1, 40));

        getContentPane().add(panelop, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 200, 490));

        paneli.setBackground(new java.awt.Color(181, 218, 240));
        paneli.setMinimumSize(new java.awt.Dimension(800, 700));
        paneli.setPreferredSize(new java.awt.Dimension(700, 700));

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Imagen_de_WhatsApp_2024-11-21_a_las_16.51.39_2c052284-removebg-preview.png"))); // NOI18N

        javax.swing.GroupLayout paneliLayout = new javax.swing.GroupLayout(paneli);
        paneli.setLayout(paneliLayout);
        paneliLayout.setHorizontalGroup(
            paneliLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paneliLayout.createSequentialGroup()
                .addGroup(paneliLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(paneliLayout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(jLabel13))
                    .addGroup(paneliLayout.createSequentialGroup()
                        .addGap(226, 226, 226)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 444, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        paneliLayout.setVerticalGroup(
            paneliLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paneliLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel13)
                .addGap(86, 86, 86)
                .addComponent(jLabel12)
                .addContainerGap())
        );

        getContentPane().add(paneli, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 0, 900, 490));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        IniciaSesion is=new IniciaSesion();
        is.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jpanelb1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpanelb1MouseClicked
        // TODO add your handling code here:
        panelop.setVisible(true);
        
        
         panelempleados p1 = new panelempleados();
    p1.setSize(700, 700); // Sugerir tamaño preferido
    p1.setLocation(0, 0); // Configurar posición (opcional si usas un layout)

    paneli.removeAll();
    paneli.setLayout(new BorderLayout());
    paneli.add(p1, BorderLayout.CENTER);
    paneli.revalidate();
        
       
    }//GEN-LAST:event_jpanelb1MouseClicked

    private void jpanelb1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpanelb1MousePressed
        // TODO add your handling code here:
        
        setColor(jpanelb1);
        resetColor(jpanelb2);
        resetColor(jpanelb3);
        resetColor(jpanelb4);
         resetColor(jpanelb5);

       
    }//GEN-LAST:event_jpanelb1MousePressed

    private void jpanelb2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpanelb2MouseClicked
        // TODO add your handling code here:
           panelpedidosadmin p1 = new panelpedidosadmin();
    p1.setSize(560, 480); // Sugerir tamaño preferido
    p1.setLocation(0, 0); // Configurar posición (opcional si usas un layout)

    paneli.removeAll();
    paneli.setLayout(new BorderLayout());
    paneli.add(p1, BorderLayout.CENTER);
    paneli.revalidate();
    }//GEN-LAST:event_jpanelb2MouseClicked

    private void jpanelb2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpanelb2MousePressed
        // TODO add your handling code here:
        setColor(jpanelb2);

        resetColor(jpanelb1);
        resetColor(jpanelb3);
        resetColor(jpanelb4);
         resetColor(jpanelb5);

    }//GEN-LAST:event_jpanelb2MousePressed

    private void jpanelb3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpanelb3MouseClicked
        // TODO add your handling code here:
          panelop.setVisible(true);
          
       panelserviciosadmin p1 = new panelserviciosadmin();
    p1.setSize(560, 480); // Sugerir tamaño preferido
    p1.setLocation(0, 0); // Configurar posición (opcional si usas un layout)

    paneli.removeAll();
    paneli.setLayout(new BorderLayout());
    paneli.add(p1, BorderLayout.CENTER);
    paneli.revalidate();
    }//GEN-LAST:event_jpanelb3MouseClicked

    private void jpanelb3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpanelb3MousePressed
        // TODO add your handling code here:
        setColor(jpanelb3);
        resetColor(jpanelb1);
        resetColor(jpanelb2);
        resetColor(jpanelb4);
         resetColor(jpanelb5);

    }//GEN-LAST:event_jpanelb3MousePressed

    private void jpanelb4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpanelb4MouseClicked
        // TODO add your handling code here:
          panelop.setVisible(true);
     panelclientesadmin p1 = new panelclientesadmin();
    p1.setSize(800, 800); // Sugerir tamaño preferido
    p1.setLocation(0, 0); // Configurar posición (opcional si usas un layout)

    paneli.removeAll();
    paneli.setLayout(new BorderLayout());
    paneli.add(p1, BorderLayout.CENTER);
    paneli.revalidate();
    }//GEN-LAST:event_jpanelb4MouseClicked

    private void jpanelb4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpanelb4MousePressed
        // TODO add your handling code here:
        setColor(jpanelb4);
        resetColor(jpanelb1);
        resetColor(jpanelb2);
        resetColor(jpanelb3);
         resetColor(jpanelb5);

    }//GEN-LAST:event_jpanelb4MousePressed

    private void jpanelb5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpanelb5MouseClicked
        // TODO add your handling code here:
        panelop.setVisible(true);
     panelHistorialPedidos p1 = new panelHistorialPedidos();
    p1.setSize(800, 800); // Sugerir tamaño preferido
    p1.setLocation(0, 0); // Configurar posición (opcional si usas un layout)

    paneli.removeAll();
    paneli.setLayout(new BorderLayout());
    paneli.add(p1, BorderLayout.CENTER);
    paneli.revalidate();
    }//GEN-LAST:event_jpanelb5MouseClicked

    private void jpanelb5MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpanelb5MousePressed
        // TODO add your handling code here:
        setColor(jpanelb5);
        resetColor(jpanelb1);
        resetColor(jpanelb2);
        resetColor(jpanelb3);
         resetColor(jpanelb4);

    }//GEN-LAST:event_jpanelb5MousePressed

    private void jpanelb6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpanelb6MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jpanelb6MouseClicked

    private void jpanelb6MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpanelb6MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jpanelb6MousePressed
    
      public void deshabilitarBotonesParaEmpleado() {
        jLabel4.setEnabled(false); // Deshabilitar botón de Empleados
        jLabel4.setVisible(false);
        jpanelb1.setEnabled(false);
        jpanelb1.setVisible(false);
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws ClassNotFoundException {
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
            java.util.logging.Logger.getLogger(FrameMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrameMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrameMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrameMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
         try (Connection conn = Conexion.getConnection()) {
            System.out.println("Conexión exitosa:D.");
        } catch (SQLException e) {
            System.out.println("Error de conexión");
            e.printStackTrace();
        }
            
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrameMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jpanelb1;
    private javax.swing.JPanel jpanelb2;
    private javax.swing.JPanel jpanelb3;
    private javax.swing.JPanel jpanelb4;
    private javax.swing.JPanel jpanelb5;
    private javax.swing.JPanel jpanelb6;
    private javax.swing.JPanel paneli;
    private javax.swing.JPanel panelinicio;
    private javax.swing.JPanel panelop;
    // End of variables declaration//GEN-END:variables
}
