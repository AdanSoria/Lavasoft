package Administrador;
//import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
//import javax.swing.text.Document;

// Importaciones esenciales para iText
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Chunk;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

// Otras importaciones necesarias
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.text.DecimalFormat;
import javax.swing.*;
import java.text.SimpleDateFormat;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */

/**
 *
 * @author Luis DC
 */
public class panelpedidosadmin extends javax.swing.JPanel {
     public static Connection getConexion() {
    String conexionUrl = "jdbc:sqlserver://adnsoria.database.windows.net:1433;"
            + "database=LavaSoft;"
            + "user=admin1@adnsoria;"
            + "password=Ad@ncode123;"
            + "loginTimeout=30;"
            + "TrustServerCertificate=True;";

    try {
        con = DriverManager.getConnection(conexionUrl);  
       // System.out.println("Conectado");
        return con;
    } catch (SQLException ex) {
        System.out.println(ex.toString());
        return null;
    }
}
    private int idClienteSeleccionado = -1;
    private int idUsuarioActual;
    private JPopupMenu popupClientes = new JPopupMenu();
    private JList<String> listaClientes = new JList<>();
    private DefaultListModel<String> modeloClientes = new DefaultListModel<>();

    /**
     * Creates new form panelpedidos
     */
     private Statement stm;
    private static Connection con;
    private DefaultTableModel m;
    
    
    
    public panelpedidosadmin() {
    initComponents();
    m=(DefaultTableModel) tblPedido.getModel();
    getConexion();
    cargarServicios();
    actualizarTablaPedidos();
    personalizarTabla();
    configurarAutocompletadoClientes();
    // Agregar listeners para el cálculo automático
    txtUnidad.getDocument().addDocumentListener(new DocumentListener() {
        @Override
        public void insertUpdate(DocumentEvent e) { calcularTotalAutomatico(); }
        public void removeUpdate(DocumentEvent e) { calcularTotalAutomatico(); }
        public void changedUpdate(DocumentEvent e) { calcularTotalAutomatico(); }
    });
    
    jcbServicios.addActionListener(e -> calcularTotalAutomatico());
    
    txtConsultaPedido.addKeyListener(new KeyAdapter() {
        public void keyPressed(KeyEvent evt) {
            String busqueda = txtConsultaPedido.getText(); 
            buscarPedidos(busqueda);
        }
    });
}
    
    
    // 3. Agrega estos métodos para el autocompletado
private void configurarAutocompletadoClientes() {
    // Configurar la lista de clientes en el popup
    listaClientes.setModel(modeloClientes);
    listaClientes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    listaClientes.setVisibleRowCount(5);
    popupClientes.add(new JScrollPane(listaClientes));
    
    // Configurar el campo de nombre de cliente
    txtNomCliente.getDocument().addDocumentListener(new DocumentListener() {
        @Override
        public void insertUpdate(DocumentEvent e) {
            buscarClientes();
        }
        
        @Override
        public void removeUpdate(DocumentEvent e) {
            buscarClientes();
        }
        
        @Override
        public void changedUpdate(DocumentEvent e) {
            buscarClientes();
        }
    });
    
    // Manejar selección de cliente
    listaClientes.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getClickCount() == 1) {
                seleccionarCliente();
            }
        }
    });
    
    // Manejar teclas en el campo de búsqueda
    txtNomCliente.addKeyListener(new KeyAdapter() {
        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                listaClientes.requestFocus();
                listaClientes.setSelectedIndex(0);
            } else if (e.getKeyCode() == KeyEvent.VK_ENTER && popupClientes.isVisible()) {
                seleccionarCliente();
            }
        }
    });
    
    // Manejar teclas en la lista de sugerencias
    listaClientes.addKeyListener(new KeyAdapter() {
        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                seleccionarCliente();
            } else if (e.getKeyCode() == KeyEvent.VK_UP && listaClientes.getSelectedIndex() == 0) {
                txtNomCliente.requestFocus();
            }
        }
    });
}


private void buscarClientes() {
    String textoBusqueda = txtNomCliente.getText().trim();
    modeloClientes.clear();
    
    if (textoBusqueda.length() >= 2) { // Buscar solo con 2+ caracteres
        try {
            // Usando los nombres exactos de tu base de datos: idClerne, Mombie, Tablono
            String sql = "SELECT idCliente, Nombre, Telefono FROM Cliente WHERE nombre LIKE ? ORDER BY Nombre";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, "%" + textoBusqueda + "%");
            
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String clienteInfo = String.format("%s (Tel: %s) - ID: %d",
                    rs.getString("Nombre"),
                    rs.getString("Telefono"),
                    rs.getInt("idCliente"));
                modeloClientes.addElement(clienteInfo);
            }
            
            if (modeloClientes.size() > 0) {
                mostrarPopupClientes();
            } else {
                popupClientes.setVisible(false);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al buscar clientes: " + ex.getMessage());
        }
    } else {
        popupClientes.setVisible(false);
    }
}

private void mostrarPopupClientes() {
    if (!popupClientes.isVisible()) {
        popupClientes.show(txtNomCliente, 0, txtNomCliente.getHeight());
        popupClientes.setSize(txtNomCliente.getWidth(), 150);
    }
    listaClientes.setSelectedIndex(0); // Seleccionar primer elemento
}
private void seleccionarCliente() {
    String seleccion = listaClientes.getSelectedValue();
    if (seleccion != null) {
        // Extraer solo el nombre para mostrarlo en el campo
        String nombreCliente = seleccion.split(" \\(")[0];
        txtNomCliente.setText(nombreCliente);
        
        // Extraer el ID del cliente para usarlo después
        String idStr = seleccion.substring(seleccion.lastIndexOf("ID: ") + 4);
        idClienteSeleccionado = Integer.parseInt(idStr.replace(")", ""));
        
        popupClientes.setVisible(false);
    }
}

    private void personalizarTabla() {
    // 1. Configuración de estilos para la tabla
    tblPedido.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 12));
    tblPedido.setRowHeight(25);
    tblPedido.setShowGrid(true);
    tblPedido.setGridColor(new Color(220, 220, 220));
    tblPedido.setSelectionBackground(new Color(181, 218, 240));
    tblPedido.setSelectionForeground(Color.BLACK);
    
    // 2. Personalización del header
    JTableHeader header = tblPedido.getTableHeader();
    header.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 13));
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
    // Renderizador para columna de estado
tblPedido.getColumnModel().getColumn(7).setCellRenderer(new DefaultTableCellRenderer() {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
        boolean isSelected, boolean hasFocus, int row, int column) {
        
        Component c = super.getTableCellRendererComponent(table, value, 
            isSelected, hasFocus, row, column);
        
        String estado = (String) value;
        
        if (!isSelected) {
            if ("Pendiente".equals(estado)) {
                c.setForeground(Color.RED);
                c.setFont(c.getFont().deriveFont(Font.BOLD));
            } else if ("En Proceso".equals(estado)) {
                c.setForeground(new Color(0, 100, 0)); // Verde oscuro
                c.setFont(c.getFont().deriveFont(Font.BOLD));
            }
        } else {
            c.setForeground(Color.WHITE);
        }
        
        ((JLabel)c).setHorizontalAlignment(SwingConstants.CENTER);
        
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
    
    
   private void calcularTotalAutomatico() {
    try {
        // Verificar que haya un servicio seleccionado y peso válido
        if (jcbServicios.getSelectedIndex() <= 0 || txtUnidad.getText().trim().isEmpty()) {
            txtTotal.setText("");
            return;
        }
        
        // Obtener el precio del servicio seleccionado
        String servicioSeleccionado = jcbServicios.getSelectedItem().toString();
        String[] partes = servicioSeleccionado.split(" - ");
        String precioStr = partes[partes.length - 1].replaceAll("[^0-9.]", "");
        double precioPorKilo = Double.parseDouble(precioStr);
        
        // Obtener el peso ingresado
        double peso = Double.parseDouble(txtUnidad.getText().trim());
        
        // Calcular el total
        double total = precioPorKilo * peso;
        
        // Mostrar el total formateado
        txtTotal.setText(String.format("%.2f", total));
        
    } catch (NumberFormatException ex) {
        txtTotal.setText("");
    } catch (Exception ex) {
        System.out.println("Error al calcular total: " + ex.getMessage());
        txtTotal.setText("");
    }
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
        lblCliente = new javax.swing.JLabel();
        lblFecha = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPedido = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        txtNomCliente = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtConsultaPedido = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        btnEditar = new javax.swing.JButton();
        btnRealizarPedido = new javax.swing.JButton();
        btnGenerarTicketP = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnBusqueda = new javax.swing.JButton();
        lblPeso = new javax.swing.JLabel();
        txtUnidad = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jcbServicios = new javax.swing.JComboBox<>();
        txtTotal = new javax.swing.JTextField();
        lblEstado = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jcbEstado = new javax.swing.JComboBox<>();
        lblServicio = new javax.swing.JLabel();
        jcbFechaE = new com.toedter.calendar.JDateChooser();
        jButtonWhatsapp = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(900, 490));

        jPanel1.setMinimumSize(new java.awt.Dimension(750, 750));
        jPanel1.setPreferredSize(new java.awt.Dimension(790, 500));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(181, 218, 240));
        jPanel4.setMinimumSize(new java.awt.Dimension(1200, 1200));
        jPanel4.setPreferredSize(new java.awt.Dimension(700, 700));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblCliente.setFont(new java.awt.Font("Roboto Bk", 0, 14)); // NOI18N
        lblCliente.setForeground(new java.awt.Color(0, 0, 0));
        lblCliente.setText("Cliente");
        jPanel4.add(lblCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 60, 24));

        lblFecha.setFont(new java.awt.Font("Roboto Bk", 0, 14)); // NOI18N
        lblFecha.setForeground(new java.awt.Color(0, 0, 0));
        lblFecha.setText("Fecha Entrega Estimada");
        jPanel4.add(lblFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, -1, 20));

        lblTotal.setFont(new java.awt.Font("Roboto Bk", 0, 14)); // NOI18N
        lblTotal.setForeground(new java.awt.Color(0, 0, 0));
        lblTotal.setText("Total");
        jPanel4.add(lblTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, 60, -1));

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

        jPanel4.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 100, 680, 380));

        jLabel3.setBackground(new java.awt.Color(181, 218, 240));
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("_________________");
        jPanel4.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 110, 120, 20));

        txtNomCliente.setBackground(new java.awt.Color(181, 218, 240));
        txtNomCliente.setBorder(null);
        jPanel4.add(txtNomCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 100, 100, 20));

        jLabel8.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Pedido");
        jPanel4.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 40, -1, -1));

        txtConsultaPedido.setBackground(new java.awt.Color(181, 218, 240));
        txtConsultaPedido.setBorder(null);
        txtConsultaPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtConsultaPedidoActionPerformed(evt);
            }
        });
        jPanel4.add(txtConsultaPedido, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 40, 390, -1));

        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("_______________________________________________________________________________");
        jPanel4.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 50, 400, -1));

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
        jPanel4.add(btnBusqueda, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 40, -1, -1));

        lblPeso.setFont(new java.awt.Font("Roboto Bk", 0, 14)); // NOI18N
        lblPeso.setForeground(new java.awt.Color(0, 0, 0));
        lblPeso.setText("Unidad");
        jPanel4.add(lblPeso, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 280, 50, 20));

        txtUnidad.setBackground(new java.awt.Color(181, 218, 240));
        txtUnidad.setBorder(null);
        txtUnidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUnidadActionPerformed(evt);
            }
        });
        jPanel4.add(txtUnidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 270, 100, 20));

        jLabel12.setBackground(new java.awt.Color(181, 218, 240));
        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("__________________");
        jPanel4.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 280, 110, -1));

        jcbServicios.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione" }));
        jcbServicios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jcbServiciosMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jcbServiciosMouseEntered(evt);
            }
        });
        jcbServicios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbServiciosActionPerformed(evt);
            }
        });
        jPanel4.add(jcbServicios, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 170, -1));

        txtTotal.setBackground(new java.awt.Color(181, 218, 240));
        txtTotal.setBorder(null);
        txtTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalActionPerformed(evt);
            }
        });
        jPanel4.add(txtTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 300, 100, 20));

        lblEstado.setFont(new java.awt.Font("Roboto Bk", 0, 14)); // NOI18N
        lblEstado.setForeground(new java.awt.Color(0, 0, 0));
        lblEstado.setText("Estado");
        jPanel4.add(lblEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, -1, 20));

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("__________________");
        jPanel4.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 310, 100, -1));

        jcbEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione", "Pendiente", "Proceso", "Listo" }));
        jcbEstado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jcbEstadoMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jcbEstadoMouseEntered(evt);
            }
        });
        jcbEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbEstadoActionPerformed(evt);
            }
        });
        jPanel4.add(jcbEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 240, 110, -1));

        lblServicio.setFont(new java.awt.Font("Roboto Bk", 0, 14)); // NOI18N
        lblServicio.setForeground(new java.awt.Color(0, 0, 0));
        lblServicio.setText("Servicio");
        jPanel4.add(lblServicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 190, -1, 20));
        jPanel4.add(jcbFechaE, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, 130, -1));

        jButtonWhatsapp.setText("Comunicacion");
        jButtonWhatsapp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonWhatsappActionPerformed(evt);
            }
        });
        jPanel4.add(jButtonWhatsapp, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 110, -1));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 510));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 902, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtConsultaPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtConsultaPedidoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtConsultaPedidoActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        // TODO add your handling code here:
                                           
     int r = tblPedido.getSelectedRow();
if (r == -1) {
    JOptionPane.showMessageDialog(this, "Debe seleccionar un pedido para editar.");
    return;
}

if (JOptionPane.showConfirmDialog(this, "¿Seguro que deseas editar este pedido?") == 0) {
    try {
        // Obtener datos del formulario
       /* int idCliente = Integer.parseInt(txtNomCliente.getText());
        int peso = Integer.parseInt(txtPeso.getText());
        int total = Integer.parseInt(txtTotal.getText());*/
        Date fecha = jcbFechaE.getDate();
        String ser = (String) jcbServicios.getSelectedItem();
        String es = (String) jcbEstado.getSelectedItem();

        // Validar fecha
        if (fecha == null) {
            JOptionPane.showMessageDialog(this, "Falta seleccionar una Fecha.");
            lblFecha.setForeground(Color.red);
            jcbFechaE.requestFocus();
            return;
        } else {
            lblFecha.setForeground(Color.black);
        }

        // Validar servicio
        if (jcbServicios.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Falta seleccionar Servicio.");
            lblServicio.setForeground(Color.red);
            jcbServicios.requestFocus();
            return;
        }

        // Validar estado
        if (jcbEstado.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Falta seleccionar Estado.");
            lblEstado.setForeground(Color.red);
            jcbEstado.requestFocus();
            return;
        }

        // Convertir fecha
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String fec = sdf.format(fecha);

        // Obtener el ID del servicio
        int idServicio = Integer.parseInt(ser.split(" - ")[0]);

        String idPedido = tblPedido.getValueAt(r, 0).toString();// Obtener ID del pedido desde la tabla
        Statement stm = con.createStatement();
        String query = "UPDATE Pedido SET FechaEntregaEstimada = '" + fec + 
                       "', IdServicio = " + idServicio + 
                       ", EstadoPedido = '" + es + 
                       "' WHERE IdPedido = '" + idPedido + "'";
         
        stm.executeUpdate(query);
String s=jcbServicios.getSelectedItem().toString();

        // Actualizar la tabla
        m.setValueAt(s, r, 2);
        m.setValueAt(fec, r, 4);
        m.setValueAt(es, r, 7);

        JOptionPane.showMessageDialog(this, "Pedido editado con exito.");
    } catch (NumberFormatException nfe) {
        JOptionPane.showMessageDialog(this, "Error numerico. Verifica los datos ingresados.");
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Error al editar el pedido. Intenta de nuevo.");
        System.out.println("Error al ejecutar el query: " + ex.getMessage());
    }
}

        
        
        
    }//GEN-LAST:event_btnEditarActionPerformed
     private void buscarPedidos(String busqueda) {
    try {
        stm = con.createStatement();
        String query = "SELECT P.idPedido, C.idCliente, C.Nombre, U.Nombre AS usuario, " +
                       "P.FechaCreacion, P.FechaEntregaEstimada, S.TipoServicio AS servicio, P.CostoTotal, P.Peso,P.EstadoPedido " +
                       "FROM Cliente C " +
                       "INNER JOIN Pedido P ON C.idCliente = P.idCliente " +
                       "INNER JOIN Usuario U ON P.idUsuario = U.idUsuario " +
                       "INNER JOIN Servicio S ON P.idServicio = S.idServicio " +
                       "WHERE C.Nombre LIKE '%" + busqueda + "%' OR " +
                       "P.FechaCreacion LIKE '%" + busqueda + "%' OR " +
                       "P.EstadoPedido LIKE '%" + busqueda + "%' OR " +
                       "S.TipoServicio LIKE '%" + busqueda + "%'";
        
        ResultSet res = stm.executeQuery(query);
        m.setRowCount(0);  
        
        if (!res.isBeforeFirst()) {  
            return;  
        }

        while (res.next()) {
            Object[] fila = {
                res.getInt("idPedido"),
                res.getString("Nombre"),
                res.getString("Servicio"),  
                res.getDate("FechaCreacion").toString(),
                res.getDate("FechaEntregaEstimada").toString(),
                res.getFloat("CostoTotal"),
                res.getInt("Peso"),
                res.getString("EstadoPedido")
            };
            m.addRow(fila); 
        }
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Error al buscar los pedidos: " + ex.getMessage());
    }
}

    private void btnRealizarPedidoActionPerformed(java.awt.event.ActionEvent evt) {                                                  
                                                         
    // Validación del Cliente
    if (idClienteSeleccionado <= 0) {
        JOptionPane.showMessageDialog(this, "Debe seleccionar un cliente válido", "Error", JOptionPane.ERROR_MESSAGE);
        lblCliente.setForeground(Color.red);
        txtNomCliente.requestFocus();
        return;
    }
    lblCliente.setForeground(Color.black);
    
    
    
        // 1. Validación del Cliente (ID o Nombre)
    final String clienteInput = txtNomCliente.getText().trim();
    if(clienteInput.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Debe ingresar un ID o nombre de cliente", "Error", JOptionPane.ERROR_MESSAGE);
        lblCliente.setForeground(Color.red);
        txtNomCliente.requestFocus();
        return;
    }

    int idCliente = 0;
    try {
        // Primero intentamos buscar por ID
        idCliente = Integer.parseInt(clienteInput);
        if(idCliente <= 0) {
            throw new NumberFormatException();
        }
        
        // Validar que exista el ID en la BD
        if(!existeClientePorID(idCliente)) {
            JOptionPane.showMessageDialog(this, "No se encontró un cliente con el ID: " + idCliente, "Error", JOptionPane.ERROR_MESSAGE);
            lblCliente.setForeground(Color.red);
            txtNomCliente.requestFocus();
            return;
        }
    } catch(NumberFormatException err) {
        // Si no es número, buscamos por nombre
        idCliente = obtenerIdClientePorNombre(clienteInput);
        if(idCliente <= 0) {
            JOptionPane.showMessageDialog(this, "No se encontró el cliente: " + clienteInput, "Error", JOptionPane.ERROR_MESSAGE);
            lblCliente.setForeground(Color.red);
            txtNomCliente.requestFocus();
            return;
        }
    }
    lblCliente.setForeground(Color.black);
    
    // 2. Validación del Peso
    final String pesoText = txtUnidad.getText().trim();
    if(pesoText.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Debe ingresar el peso del paquete", "Error", JOptionPane.ERROR_MESSAGE);
        lblPeso.setForeground(Color.red);
        txtUnidad.requestFocus();
        return;
    }
    
    double peso;
    try {
        peso = Double.parseDouble(pesoText);
        if(peso <= 0 || peso > 1000) { // Suponiendo un peso máximo de 1000 kg
            throw new NumberFormatException();
        }
    } catch(NumberFormatException err) {
        JOptionPane.showMessageDialog(this, "El peso debe ser un número entre 0.1 y 1000 kg", "Error", JOptionPane.ERROR_MESSAGE);
        lblPeso.setForeground(Color.red);
        txtUnidad.requestFocus();
        return;
    }
    lblPeso.setForeground(Color.black);

    // 3. Validación del Total
    final String totalText = txtTotal.getText().trim();
    if(totalText.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Debe ingresar el total del pedido", "Error", JOptionPane.ERROR_MESSAGE);
        lblTotal.setForeground(Color.red);
        txtTotal.requestFocus();
        return;
    }
    
    double total;
    try {
        total = Double.parseDouble(totalText);
        if(total <= 0) {
            throw new NumberFormatException();
        }
    } catch(NumberFormatException err) {
        JOptionPane.showMessageDialog(this, "El total debe ser un número mayor a 0", "Error", JOptionPane.ERROR_MESSAGE);
        lblTotal.setForeground(Color.red);
        txtTotal.requestFocus();
        return;
    }
    lblTotal.setForeground(Color.black);

    // 4. Validación de Fecha
    Date fecha = jcbFechaE.getDate();
    if(fecha == null) {
        JOptionPane.showMessageDialog(this, "Debe seleccionar una fecha de entrega estimada", "Error", JOptionPane.ERROR_MESSAGE);
        lblFecha.setForeground(Color.red);
        jcbFechaE.requestFocus();
        return;
    }
    
    // Validar que la fecha no sea anterior a la fecha actual
    Date fechaActual = new Date();
    if(fecha.before(fechaActual)) {
        JOptionPane.showMessageDialog(this, "La fecha de entrega no puede ser anterior a la fecha actual", "Error", JOptionPane.ERROR_MESSAGE);
        lblFecha.setForeground(Color.red);
        jcbFechaE.requestFocus();
        return;
    }
    lblFecha.setForeground(Color.black);

    // 5. Validación de Servicio
    if(jcbServicios.getSelectedIndex() <= 0) {
        JOptionPane.showMessageDialog(this, "Debe seleccionar un servicio válido", "Error", JOptionPane.ERROR_MESSAGE);
        lblServicio.setForeground(Color.red);
        jcbServicios.requestFocus();
        return;
    }
    String ser = jcbServicios.getSelectedItem().toString();
    lblServicio.setForeground(Color.black);

    // 6. Validación de Estado
    if(jcbEstado.getSelectedIndex() <= 0) {
        JOptionPane.showMessageDialog(this, "Debe seleccionar un estado válido", "Error", JOptionPane.ERROR_MESSAGE);
        lblEstado.setForeground(Color.red);
        jcbEstado.requestFocus();
        return;
    }
    String es = jcbEstado.getSelectedItem().toString();
    lblEstado.setForeground(Color.black);

    // Formatear fecha para SQL
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String fec = sdf.format(fecha);

    // Obtener ID del servicio (asumiendo formato "ID - Descripción")
    int idServicio;
    try {
        idServicio = Integer.parseInt(ser.split(" - ")[0]);
        if(idServicio <= 0) {
            throw new NumberFormatException();
        }
    } catch(Exception ex) {
        JOptionPane.showMessageDialog(this, "Error al obtener el ID del servicio", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Insertar en la base de datos
    try {
        Statement stm = con.createStatement();
        String query = "INSERT INTO Pedido (IdCliente, FechaCreacion, FechaEntregaEstimada, IdServicio, Peso, CostoTotal, EstadoPedido) " +
                       "VALUES (" + idCliente + ", GETDATE(), '" + fec + "', " + idServicio + ", " + peso + ", " + total + ", '" + es + "')";
        
        System.out.println("Query ejecutado: " + query);
        int filasAfectadas = stm.executeUpdate(query);

        if(filasAfectadas > 0) {
            JOptionPane.showMessageDialog(this, "Pedido registrado con éxito para el cliente ID: " + idCliente, "Éxito", JOptionPane.INFORMATION_MESSAGE);
            actualizarTablaPedidos();
            limpiarCampos();
        }
    } catch (SQLException ex) {
        System.err.println("Error al registrar el pedido: " + ex.getMessage());
        JOptionPane.showMessageDialog(this, "Error al registrar el pedido.", "Error", JOptionPane.ERROR_MESSAGE);
    }
}                                                 
        
                             
    private boolean existeClientePorID(int idCliente) {
    try {
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT 1 FROM Cliente WHERE IdCliente = " + idCliente);
        return rs.next();
    } catch (SQLException ex) {
        System.err.println("Error al validar cliente: " + ex.getMessage());
        return false;
    }
}

private int obtenerIdClientePorNombre(String nombre) {
    try {
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT IdCliente FROM Cliente WHERE Nombre LIKE '%" + nombre + "%'");
        if(rs.next()) {
            return rs.getInt("IdCliente");
        }
    } catch (SQLException ex) {
        System.err.println("Error al buscar cliente por nombre: " + ex.getMessage());
    }
    return 0;
}
    private void limpiarCampos() {
    txtUnidad.setText("");
    txtTotal.setText("");
    jcbServicios.setSelectedIndex(0);
}
    
  private void actualizarTablaPedidos() {
    DefaultTableModel model = (DefaultTableModel) tblPedido.getModel();
    model.setRowCount(0); // Limpiar la tabla
    
    try (Connection conn = Conexion.getConnection()) {
        String sql = "SELECT p.idPedido, c.Nombre AS Cliente, s.Descripcion AS Servicio, " +
                     "p.FechaCreacion, p.FechaEntregaEstimada, p.Peso, p.CostoTotal, p.EstadoPedido " +
                     "FROM dbo.Pedido p " +
                     "JOIN dbo.Cliente c ON p.idCliente = c.idCliente " +
                     "JOIN dbo.Servicio s ON p.idServicio = s.idServicio " +
                     "WHERE p.EstadoPedido IN ('Pendiente', 'En Proceso') " +
                     "ORDER BY p.FechaCreacion DESC";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            DecimalFormat df = new DecimalFormat("$#,##0.00");
            
            while (rs.next()) {
                String estado = rs.getString("EstadoPedido");
                
                model.addRow(new Object[]{
                    rs.getInt("idPedido"),
                    rs.getString("Cliente"),
                    rs.getString("Servicio"),
                    dateFormat.format(rs.getTimestamp("FechaCreacion")),
                    dateFormat.format(rs.getTimestamp("FechaEntregaEstimada")),
                    rs.getDouble("Peso") + " kg",
                    df.format(rs.getDouble("CostoTotal")),
                    estado
                });
            }
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error al cargar pedidos: " + e.getMessage(), 
                                    "Error", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
    }
}

    private void btnGenerarTicketPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarTicketPActionPerformed
        // TODO add your handling code here:
        int filaSeleccionada = tblPedido.getSelectedRow();
    
    if (filaSeleccionada == -1) {
        JOptionPane.showMessageDialog(this, "Selecciona un pedido para generar el ticket.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        return;
    }
    
    try (Connection conn = Conexion.getConnection()) {
        // Obtener el ID del pedido seleccionado
        int idPedido = (int) tblPedido.getValueAt(filaSeleccionada, 0);

        // Consulta para obtener los datos completos del pedido
        String sql = "SELECT p.idPedido, c.Nombre AS Cliente, s.Descripcion AS Servicio, " +
                     "p.FechaCreacion, p.FechaEntregaEstimada, p.Peso, p.CostoTotal " +
                     "FROM Pedido p " +
                     "JOIN Cliente c ON p.idCliente = c.idCliente " +
                     "JOIN Servicio s ON p.idServicio = s.idServicio " +
                     "WHERE p.idPedido = ?";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idPedido);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // Obtener los datos del pedido
                String cliente = rs.getString("Cliente");
                String servicio = rs.getString("Servicio");
                double peso = rs.getDouble("Peso");
                double costoTotal = rs.getDouble("CostoTotal");

                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                String fechaCreacion = sdf.format(rs.getTimestamp("FechaCreacion"));
                String fechaEntrega = sdf.format(rs.getTimestamp("FechaEntregaEstimada"));

                // Crear el documento PDF
                Document document = new Document();
                String fileName = "ticket_pedido_" + idPedido + ".pdf";
                PdfWriter.getInstance(document, new FileOutputStream(fileName));
                document.open();  

                Font titleFont = new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.BOLD);
                Paragraph title = new Paragraph("LavaSoft", titleFont);
                title.setAlignment(Element.ALIGN_CENTER);
                document.add(title);
                document.add(new Paragraph("****************************************************", titleFont)); 
                document.add(Chunk.NEWLINE);  

                Paragraph text = new Paragraph("Recibo de Pedido\n", titleFont);
                text.setAlignment(Element.ALIGN_CENTER);
                document.add(text);

                Font normalFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL);
                Paragraph content = new Paragraph(
                    "Pedido ID: " + idPedido + "\n" +
                    "Cliente: " + cliente + "\n" +
                    "Servicio: " + servicio + "\n" +
                    "Peso: " + peso + " kg\n" +
                    "Costo Total: $" + costoTotal + "\n" +
                    "Fecha de Creación: " + fechaCreacion + "\n" +
                    "Fecha de Entrega Estimada: " + fechaEntrega + "\n", 
                    normalFont);
                content.setAlignment(Element.ALIGN_CENTER);
                document.add(content);
                document.add(Chunk.NEWLINE);  
                document.add(new Paragraph("****************************************************", titleFont));

                document.close();

                JOptionPane.showMessageDialog(this, "Ticket generado exitosamente: " + fileName);
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró información del pedido.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    } catch (SQLException | DocumentException | FileNotFoundException ex) {
        JOptionPane.showMessageDialog(this, "Error al generar el ticket: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        ex.printStackTrace();
    }
    }//GEN-LAST:event_btnGenerarTicketPActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
      int r  = tblPedido.getSelectedRow();
        if (r == -1) {
            JOptionPane.showMessageDialog(this, "Por favor, selecciona un Pedido para eliminar.");
            return;
        }
        int idPedido = (Integer) tblPedido.getValueAt(r, 0);
    String query = "DELETE FROM Pedido WHERE IdPedido = '" + idPedido + "'";
    try (Statement stmt = con.createStatement()) {
        int filasAfectadas = stmt.executeUpdate(query);
        if (filasAfectadas > 0) {
            JOptionPane.showMessageDialog(this, "Servicio borrado exitosamente.");
            txtNomCliente.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Servicio no encontrado.");
            txtUnidad.setText("");
            txtTotal.setText("");
            jcbServicios.setSelectedIndex(0);
        }
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Error de base de datos: " + ex.getMessage());
    }
    actualizarTablaPedidos();
            limpiarCampos();
     
    }//GEN-LAST:event_btnEliminarActionPerformed
    // En el constructor o un método de inicialización
 private void cargarServicios() {
    try {
        Statement stm = con.createStatement();
        String query = "SELECT IdServicio, TipoServicio, PrecioUnitario FROM Servicio"; 
        ResultSet res = stm.executeQuery(query);
        
        jcbServicios.removeAllItems();
        jcbServicios.addItem("Selecciona servicio");  
        while (res.next()) {
            jcbServicios.addItem(res.getInt("IdServicio") + " - " + 
                               res.getString("TipoServicio") + 
                               " - $" + res.getDouble("PrecioUnitario"));
        }
    } catch (SQLException ex) {
        System.out.println("Error al cargar servicios: " + ex.getMessage());
    }
}
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
                    txtNomCliente.setText(rs.getString("Nombre"));
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

    private void txtUnidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUnidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUnidadActionPerformed

    private void txtTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalActionPerformed

    private void jcbServiciosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbServiciosActionPerformed
        // TODO add your handling code here:
        //cargarServicios();
    }//GEN-LAST:event_jcbServiciosActionPerformed

    private void jcbServiciosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jcbServiciosMouseClicked
        // TODO add your handling code here:
        //cargarServicios();
    }//GEN-LAST:event_jcbServiciosMouseClicked

    private void jcbServiciosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jcbServiciosMouseEntered
        // TODO add your handling code here:
        cargarServicios();
    }//GEN-LAST:event_jcbServiciosMouseEntered

    private void jcbEstadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jcbEstadoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jcbEstadoMouseClicked

    private void jcbEstadoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jcbEstadoMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jcbEstadoMouseEntered

    private void jcbEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbEstadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcbEstadoActionPerformed
    
    private void tblPedidoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPedidoMouseClicked
        // TODO add your handling code here:
        int r = tblPedido.getSelectedRow();
    txtNomCliente.setText(tblPedido.getValueAt(r, 0).toString());

    String Servicio = tblPedido.getValueAt(r, 2).toString();
    jcbServicios.setSelectedItem(Servicio);
    
   try {
    Object objFecha = tblPedido.getValueAt(r, 3);
    if (objFecha != null) {
        String fechaStr = objFecha.toString();
        
        SimpleDateFormat formatoEntrada;
        if (fechaStr.contains("/")) {
            formatoEntrada = new SimpleDateFormat("dd/MM/yyyy HH:mm"); 
        } else {
            formatoEntrada = new SimpleDateFormat("yyyy-MM-dd");
        }

        Date fecha = formatoEntrada.parse(fechaStr);
        jcbFechaE.setDate(fecha); 
    } else {
        jcbFechaE.setDate(null); 
    }
} catch (Exception e) {
    System.out.println("Error al convertir la fecha: " + e.getMessage());
}
      
    txtUnidad.setText(tblPedido.getValueAt(r, 5).toString());
    
    txtTotal.setText(tblPedido.getValueAt(r, 6).toString());
    }//GEN-LAST:event_tblPedidoMouseClicked

    private void jButtonWhatsappActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonWhatsappActionPerformed
            JFrame ventanaWhatsapp = new JFrame("Mensajería WhatsApp");
    
    // Crear una instancia de tu panel
    panelWhatsappadmin panelWhatsapp = new panelWhatsappadmin();
    
    // Configurar el JFrame
    ventanaWhatsapp.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Cierra solo esta ventana
    ventanaWhatsapp.setSize(900, 490); // Tamaño adecuado (ajústalo según necesidad)
    ventanaWhatsapp.setLocationRelativeTo(null); // Centrar en pantalla
    
    // Agregar el panel al JFrame
    ventanaWhatsapp.getContentPane().add(panelWhatsapp);
    
    // Mostrar la ventana
    ventanaWhatsapp.setVisible(true);
        
    }//GEN-LAST:event_jButtonWhatsappActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBusqueda;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGenerarTicketP;
    private javax.swing.JButton btnRealizarPedido;
    private javax.swing.JButton jButtonWhatsapp;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> jcbEstado;
    private com.toedter.calendar.JDateChooser jcbFechaE;
    private javax.swing.JComboBox<String> jcbServicios;
    private javax.swing.JLabel lblCliente;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblPeso;
    private javax.swing.JLabel lblServicio;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JTable tblPedido;
    private javax.swing.JTextField txtConsultaPedido;
    private javax.swing.JTextField txtNomCliente;
    private javax.swing.JTextField txtTotal;
    private javax.swing.JTextField txtUnidad;
    // End of variables declaration//GEN-END:variables
}
