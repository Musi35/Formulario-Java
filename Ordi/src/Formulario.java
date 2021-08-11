import java.awt.*;
import javax.swing.*;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.awt.event.*;
import java.io.*;
import java.util.HashMap;
import java.util.Base64;
import org.json.simple.*;


public class Formulario extends JFrame implements ActionListener {
    JPanel p;
    private JButton btn_path, btn_data, btn_micro, btn_acep, btn_limpiar, btn_cancelar;
    private JTextField txt_path, txt_server, txt_usuario, txt_data, txt_forma, txt_remi, txt_micro, txt_cliente, 
    txt_version, txt_idSucursal, txt_idSoporte;
    private JPasswordField txt_contra;
    private JLabel lbl_anuncio;
    private String jason, contraOG, contraCRY, password, errores;
    JFileChooser fileChooser = new JFileChooser();
    File direc = new File("C:\\Formulario\\bot factura global"); 
    File archie = new File("C:\\Formulario\\bot factura global\\Registro.json");   
    
    public Formulario() {
    setSize(790, 400);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setTitle("Configuración");
    setLocationRelativeTo(null);  
    setResizable(false);
    etiquetas();
    texto();
    boton();
    if(!direc.exists()) {
        direc.mkdirs();
    }
    if(!archie.exists()){
        try {
            archie.createNewFile();
        } catch (IOException e) {
            System.out.println("Hubo pedo del archivo");
        }
    }else{
        try {
            cargar();
        } catch (Exception e) {
            System.out.println("Hubo pedo");
        }
    }
   }

   private void etiquetas() {
    p = new JPanel();
    this.getContentPane().add(p).setBackground(new Color(227, 227, 227));
    p.setLayout(null);

    JLabel lbl_path = new JLabel("Programa: ");
    lbl_path.setBounds(10, -23, 300, 100);
    lbl_path.setFont(new Font("arial", 1, 15));
    lbl_path.setHorizontalAlignment(SwingConstants.LEFT);
    p.add(lbl_path);

    JLabel lbl_server = new JLabel("Server: ");
    lbl_server.setBounds(10, 23, 300, 100); 
    lbl_server.setFont(new Font("arial", 1, 15));
    lbl_server.setHorizontalAlignment(SwingConstants.LEFT);
    p.add(lbl_server);
    
    JLabel lbl_usuario = new JLabel("Usuario: ");
    lbl_usuario.setBounds(10, 69, 300, 100);
    lbl_usuario.setFont(new Font("arial", 1, 15));
    lbl_usuario.setHorizontalAlignment(SwingConstants.LEFT);
    p.add(lbl_usuario);

    JLabel lbl_contra = new JLabel("Contraseña: ");
    lbl_contra.setBounds(10, 115, 300, 100);
    lbl_contra.setFont(new Font("arial", 1, 15));
    lbl_contra.setHorizontalAlignment(SwingConstants.LEFT);
    p.add(lbl_contra);

    JLabel lbl_data = new JLabel("Database: ");
    lbl_data.setBounds(10, 161, 300, 100);
    lbl_data.setFont(new Font("arial", 1, 15));
    lbl_data.setHorizontalAlignment(SwingConstants.LEFT);
    p.add(lbl_data);

    JLabel lbl_remi = new JLabel("Remitente: ");
    lbl_remi.setBounds(10, 207, 300, 100);
    lbl_remi.setFont(new Font("arial", 1, 15));
    lbl_remi.setHorizontalAlignment(SwingConstants.LEFT);
    p.add(lbl_remi);
    
    JLabel lbl_forma = new JLabel("Forma: ");
    lbl_forma.setBounds(390, -23, 300, 100);
    lbl_forma.setFont(new Font("arial", 1, 15));
    lbl_forma.setHorizontalAlignment(SwingConstants.LEFT);
    p.add(lbl_forma);

    JLabel lbl_micro = new JLabel("Microsip: ");
    lbl_micro.setBounds(390, 23, 300, 100);
    lbl_micro.setFont(new Font("arial", 1, 15));
    lbl_micro.setHorizontalAlignment(SwingConstants.LEFT);
    p.add(lbl_micro);

    JLabel lbl_cliente = new JLabel("N° de Cliente: ");
    lbl_cliente.setBounds(390, 69, 300, 100);
    lbl_cliente.setFont(new Font("arial", 1, 15));
    lbl_cliente.setHorizontalAlignment(SwingConstants.LEFT);
    p.add(lbl_cliente);

    JLabel lbl_version = new JLabel("Versión: ");
    lbl_version.setBounds(390, 115, 300, 100);
    lbl_version.setFont(new Font("arial", 1, 15));
    lbl_version.setHorizontalAlignment(SwingConstants.LEFT);
    p.add(lbl_version);

    JLabel lbl_idSucursal = new JLabel("ID Sucursal: ");
    lbl_idSucursal.setBounds(390, 161, 300, 100);
    lbl_idSucursal.setFont(new Font("arial", 1, 15));
    lbl_idSucursal.setHorizontalAlignment(SwingConstants.LEFT);
    p.add(lbl_idSucursal);

    JLabel lbl_idSoporte = new JLabel("ID Soporte: ");
    lbl_idSoporte.setBounds(390, 207, 300, 100);
    lbl_idSoporte.setFont(new Font("arial", 1, 15));
    lbl_idSoporte.setHorizontalAlignment(SwingConstants.LEFT);
    p.add(lbl_idSoporte);

    lbl_anuncio = new JLabel();
    lbl_anuncio.setBounds(500, 250, 300, 100);
    lbl_anuncio.setFont(new Font("arial", 1, 15));
    lbl_anuncio.setHorizontalAlignment(SwingConstants.LEFT);
    p.add(lbl_anuncio);
    
   }

   private void texto() {
        txt_path = new JTextField();
        txt_path.setBackground(Color.WHITE);
        txt_path.setBorder(null);
        txt_path.setEditable(false);
        txt_path.setBounds(110, 15, 200, 25);
        txt_path.setFont(new Font("Arial", 1, 15));
        p.add(txt_path); 

        txt_server = new JTextField("");
        txt_server.setBorder(null);
        txt_server.setBounds(110, 60, 200, 25); 
        txt_server.setFont(new Font("Arial", 1, 15)); 
        p.add(txt_server); 

        txt_usuario = new JTextField("");
        txt_usuario.setBorder(null);
        txt_usuario.setBounds(110, 105, 200, 25);
        txt_usuario.setFont(new Font("Arial", 1, 15));
        p.add(txt_usuario); 

        txt_contra = new JPasswordField("");
        txt_contra.setBorder(null);
        txt_contra.setBounds(110, 150, 200, 25);
        txt_contra.setFont(new Font("Arial", 1, 15));
        txt_contra.setBorder(null);
        p.add(txt_contra); 

        txt_data = new JTextField("");
        txt_data.setBackground(Color.white);
        txt_data.setBorder(null);
        txt_data.setEditable(false);
        txt_data.setBounds(110, 195, 200, 25); 
        txt_data.setFont(new Font("Arial", 1, 15)); 
        p.add(txt_data); 

        txt_remi = new JTextField("Express");
        txt_remi.setBackground(Color.white);
        txt_remi.setBorder(null);
        txt_remi.setEditable(false);
        txt_remi.setBounds(110, 240, 200, 25);
        txt_remi.setFont(new Font("Arial", 1, 15));
        p.add(txt_remi); 

        txt_forma = new JTextField();
        txt_forma.setBorder(null);
        txt_forma.setBounds(500, 15, 200, 25);
        txt_forma.setFont(new Font("Arial", 1, 15));
        p.add(txt_forma); 
        
        txt_micro = new JTextField();
        txt_micro.setBackground(Color.white);
        txt_micro.setBorder(null);
        txt_micro.setEditable(false);
        txt_micro.setBounds(500, 60, 200, 25);
        txt_micro.setFont(new Font("Arial", 1, 15));
        p.add(txt_micro); 

        txt_cliente = new JTextField();
        txt_cliente.setBorder(null);
        txt_cliente.setBounds(500, 105, 200, 25);
        txt_cliente.setFont(new Font("Arial", 1, 15));
        p.add(txt_cliente); 

        txt_version = new JTextField();
        txt_version.setBorder(null);
        txt_version.setBounds(500, 150, 200, 25);
        txt_version.setFont(new Font("Arial", 1, 15));
        p.add(txt_version); 

        txt_idSucursal = new JTextField();
        txt_idSucursal.setBorder(null);
        txt_idSucursal.setBounds(500, 195, 200, 25);
        txt_idSucursal.setFont(new Font("Arial", 1, 15));
        p.add(txt_idSucursal); 

        txt_idSoporte = new JTextField();
        txt_idSoporte.setBorder(null);
        txt_idSoporte.setBounds(500, 240, 200, 25);
        txt_idSoporte.setFont(new Font("Arial", 1, 15));
        p.add(txt_idSoporte); 
   }

   private void boton() {
       btn_path = new JButton("...");
       btn_path.setBounds(315, 15, 25, 25);
       btn_path.setBackground(new Color(191, 47, 36));
       btn_path.setForeground(Color.WHITE);
       btn_path.setBorder(null);
       btn_path.addActionListener(this);
       p.add(btn_path);

       btn_data = new JButton("...");
       btn_data.setBounds(315, 195, 25, 25);
       btn_data.setBackground(new Color(191, 47, 36));
       btn_data.setForeground(Color.white);
       btn_data.setBorder(null);
       btn_data.addActionListener(this);
       p.add(btn_data);
       
       btn_micro = new JButton("...");
       btn_micro.setBounds(710, 60, 25, 25);
       btn_micro.setBackground(new Color(191, 47, 36));
       btn_micro.setForeground(Color.WHITE);
       btn_micro.setBorder(null);
       btn_micro.addActionListener(this);
       p.add(btn_micro); 

       btn_acep = new JButton("Aceptar");
       btn_acep.setBounds(230, 290, 90, 40);
       btn_acep.setBackground(new Color(191, 47, 36));
       btn_acep.setFont(new Font("arial", 1, 15));
       btn_acep.setForeground(Color.WHITE);
       btn_acep.setBorder(null);
       btn_acep.addActionListener(this);
       p.add(btn_acep); 

       btn_limpiar = new JButton("Limpiar");
       btn_limpiar.setBounds(330, 290, 90, 40);
       btn_limpiar.setBackground(new Color(191, 47, 36));
       btn_limpiar.setFont(new Font("arial", 1, 15));
       btn_limpiar.setForeground(Color.WHITE);
       btn_limpiar.setBorder(null);
       btn_limpiar.addActionListener(this);
       p.add(btn_limpiar); 

       btn_cancelar = new JButton("Cancelar");
       btn_cancelar.setBounds(430, 290, 90, 40);
       btn_cancelar.setBackground(new Color(191, 47, 36));
       btn_cancelar.setFont(new Font("arial", 1, 15));
       btn_cancelar.setForeground(Color.WHITE);
       btn_cancelar.setBorder(null);
       btn_cancelar.addActionListener(this);
       p.add(btn_cancelar); 
   }
   
   @Override
   public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btn_acep) {
           check();
           if (errores.length() == 0) {
            contraOG = String.valueOf(txt_contra.getPassword());
            contraCRY = Base64.getEncoder().encodeToString(contraOG.getBytes());
            
            HashMap<String,Object> datsHM = new HashMap<>();
            datsHM.put("botPath", txt_path.getText());
            datsHM.put("Server", txt_server.getText());
            datsHM.put("User", txt_usuario.getText());
            datsHM.put("Database", txt_data.getText());
            datsHM.put("userName", txt_usuario.getText());
            datsHM.put("password", contraCRY);
            datsHM.put("remitente", txt_remi.getText());
            datsHM.put("forma", txt_forma.getText());
            datsHM.put("remitente", txt_remi.getText());
            datsHM.put("rutamicrosip", txt_micro.getText());
            datsHM.put("nocliente", txt_cliente.getText());
            datsHM.put("versionMicrosip", txt_version.getText());
            datsHM.put("idSucursal", txt_idSucursal.getText());
            datsHM.put("idSoporte", txt_idSoporte.getText());

            JSONObject dats = new JSONObject(datsHM);
            jason = dats.toJSONString();
            actualizar();
           }
        }
        if (e.getSource() == btn_path){ 
            int seleccion = fileChooser.showOpenDialog(this);   
            if (seleccion != JFileChooser.CANCEL_OPTION) {
                File fileName = fileChooser.getSelectedFile();
        
                if ((fileName == null) || (fileName.getName().equals(""))) {
                    txt_path.setText("Hubo un error, intente de nuevo.");
                } else {
                    txt_path.setText(fileName.getAbsolutePath());
                }
            }
        }
        if (e.getSource() == btn_data) {
            int seleccion = fileChooser.showOpenDialog(this);   
            if (seleccion != JFileChooser.CANCEL_OPTION) {
                File fileName = fileChooser.getSelectedFile();
            
                if ((fileName == null) || (fileName.getName().equals(""))) {
                    txt_data.setText("Hubo un error, intente de nuevo.");
                } else {
                    txt_data.setText(fileName.getAbsolutePath());
                }
            }
        }
        if (e.getSource() == btn_micro) {
            int seleccion = fileChooser.showOpenDialog(this);   
            if (seleccion != JFileChooser.CANCEL_OPTION) {
                File fileName = fileChooser.getSelectedFile();
            
                if ((fileName == null) || (fileName.getName().equals(""))) {
                    txt_micro.setText("Hubo un error, intente de nuevo.");
                } else {
                    txt_micro.setText(fileName.getAbsolutePath());
                }
            }
        }
        if (e.getSource() == btn_limpiar) {
            txt_cliente.setText("");
            txt_data.setText("");
            txt_forma.setText("");
            txt_idSoporte.setText("");
            txt_idSucursal.setText("");
            txt_micro.setText("");
            txt_path.setText("");
            txt_remi.setText("");
            txt_server.setText("");
            txt_usuario.setText("");
            txt_version.setText("");
        }
        if (e.getSource() == btn_cancelar) {
            System.exit(0);
        }
    }
   
   public void check() {
        errores = "";

        //Server
        String server = txt_path.getText();
        if (server.trim().isEmpty()) {
            errores = errores + "\n" + "Hay espacios en blanco en Server.";
        }
        
        //Usuario
        String usuario = txt_usuario.getText();
        if (usuario.trim().isEmpty()) {
            errores = errores + "\n" + "Hay un espacio en blanco en Usuario.";
        }
        
        //Contraseña
        String contraseña = String.valueOf(txt_contra.getPassword());
        if (contraseña.trim().isEmpty()) {
            byte[] decodedBytes = Base64.getDecoder().decode(password);
            String decodedString = new String(decodedBytes);
            txt_contra.setText(decodedString);
            if (password.trim().isEmpty()) {
               errores = errores + "\n" + "Hay un espacio en blanco en Contraseña.";
            }
        }

        //Database
        String database = txt_data.getText();
        if (database.trim().isEmpty()) {
            errores = errores + "\n" + "Hay un espacio en balnco en Database.";
        }

        //Forma
        String forma = txt_forma.getText();
        if (forma.trim().isEmpty()) {
            errores = errores + "\n" + "Hay un espacio en balnco en Forma.";
        }

        //Microsip
        String microsip = txt_micro.getText();
        if (microsip.trim().isEmpty()) {
            errores = errores + "\n" + "Hay un espacio en balnco en Microsip.";
        }

        //N° de Cliente
        String cliente = txt_cliente.getText();
        int num = cliente.length();
        if (cliente.trim().isEmpty()) {
            errores = errores + "\n" + "Hay un espacio en balnco en N° de Cliente.";
        } else {
            for (int i = 0; i < cliente.length(); i++) {
                if (!Character.isDigit(cliente.charAt(i))) {
                    errores = errores + "\n" + "Solo se pueden poner números en Cliente.";
                    cliente = "";
                }
            }
        }if(num <= 1){
            cliente = "0" + cliente;
            txt_cliente.setText(cliente);
        }
        //Version
        String version = txt_version.getText();
        if (version.trim().isEmpty()) {
            errores = errores + "\n" + "Hay un espacio en balnco en Versión.";
        }

        //Id Sucursal
        String sucursal = txt_idSucursal.getText();
        if (sucursal.trim().isEmpty()) {
            errores = errores + "\n" + "Hay un espacio en balnco en ID Sucursal.";
        } else {
            for (int i = 0; i < sucursal.length(); i++) {
                if (!Character.isDigit(sucursal.charAt(i))) {
                    errores = errores + "\n" + "Solo se pueden poner números en ID Sucursal.";
                }
            }
        }

        //ID Soporte
        String soporte = txt_idSoporte.getText();
        if (soporte.trim().isEmpty()) {
            errores = errores + "\n" + "Hay un espacio en balnco en ID Soporte.";
        } else {
            for (int i = 0; i < sucursal.length(); i++) {
                if (!Character.isDigit(sucursal.charAt(i))) {
                    errores = errores + "\n" + "Solo se pueden poner números en ID Soporte.";
                }
            }
        }

        if (errores.length() > 1) {
            JTextArea jta = new JTextArea(errores);
            jta.setBackground(null);
            jta.setForeground(Color.black);
            JOptionPane.showMessageDialog(null, jta, "Errores", JOptionPane.ERROR_MESSAGE);
        }else{
            JTextArea jta = new JTextArea("Todo está correcto.");
            jta.setBackground(null);
            jta.setForeground(Color.black);
            JOptionPane.showMessageDialog(null, jta, "Reporte", JOptionPane.PLAIN_MESSAGE);
        }
        
   }

   public void actualizar() {
        try {
            try (FileWriter escribe = new FileWriter(archie)) {
                escribe.write(jason);
            } 
            JTextArea jta = new JTextArea("La información se actualizó correctamente.");
            jta.setBackground(null);
            jta.setForeground(Color.black);
            JOptionPane.showMessageDialog(null, jta, "Aviso", JOptionPane.PLAIN_MESSAGE); 
        } catch (IOException e) {
            JTextArea jta = new JTextArea("No se pudo subir la información, vuelva a intentarlo.");
            jta.setBackground(null);
            jta.setForeground(Color.black);
            JOptionPane.showMessageDialog(null, jta, "Error", JOptionPane.ERROR_MESSAGE);
        }
   }
    
   private void cargar() throws Exception, JsonMappingException {
        try (BufferedReader buffie = new BufferedReader(new FileReader(archie))) {
            String salidaJson;
            StringBuilder response = new StringBuilder();
            while ((salidaJson = buffie.readLine()) != null) {
                response.append(salidaJson);
            }   }
    ObjectMapper mapper = new ObjectMapper();
    //Json Node: lee el archivo y el solo corta la información entre las comillas
    JsonNode jsonNode = mapper.readTree(archie);

    txt_usuario.setText(jsonNode.get("User").asText());
    txt_server.setText(jsonNode.get("Server").asText());
    txt_version.setText(jsonNode.get("versionMicrosip").asText());
    txt_remi.setText(jsonNode.get("remitente").asText());
    txt_micro.setText(jsonNode.get("rutamicrosip").asText());
    txt_forma.setText(jsonNode.get("forma").asText());
    txt_cliente.setText(jsonNode.get("nocliente").asText());
    txt_data.setText(jsonNode.get("Database").asText());
    txt_path.setText(jsonNode.get("botPath").asText());
    txt_idSucursal.setText(jsonNode.get("idSucursal").asText());
    txt_idSoporte.setText(jsonNode.get("idSoporte").asText());
    password = jsonNode.get("password").asText();
   }

    public static void main(String[] args) {
       Formulario form = new Formulario();
       form.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       form.setVisible(true);
    }
    
}

