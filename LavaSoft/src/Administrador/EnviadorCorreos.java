/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Administrador;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import javax.swing.JOptionPane;

public class EnviadorCorreos {
    private final String usuario;
    private final String contraseña;
    private final Properties propiedades;

    public EnviadorCorreos(String usuario, String contraseña) {
        this.usuario = usuario;
        this.contraseña = contraseña;
        
        // Configuración de propiedades para SMTP
        propiedades = new Properties();
        propiedades.put("mail.smtp.host", "smtp.gmail.com"); // Servidor SMTP de Gmail
        propiedades.put("mail.smtp.port", "587"); // Puerto para TLS
        propiedades.put("mail.smtp.auth", "true");
        propiedades.put("mail.smtp.starttls.enable", "true"); // Habilitar TLS
    }

    public boolean enviarCorreo(String destinatario, String asunto, String cuerpo) {
        try {
            // Crear sesión
            Session sesion = Session.getInstance(propiedades, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(usuario, contraseña);
                }
            });
            
            // Crear mensaje
            Message mensaje = new MimeMessage(sesion);
            mensaje.setFrom(new InternetAddress(usuario));
            mensaje.setRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
            mensaje.setSubject(asunto);
            mensaje.setText(cuerpo);
            
            // Enviar mensaje
            Transport.send(mensaje);
            return true;
        } catch (MessagingException ex) {
            JOptionPane.showMessageDialog(null, 
                "Error al enviar correo: " + ex.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
}