import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.beans.PropertyVetoException;
import java.io.File;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class practica7JFrame extends javax.swing.JFrame {
    private final String messageAbout = "Umbralizador es una aplicación de "
            + "tratamiento de imágenes.\nA partir de una imagen cargada por el "
            + "usuario se puede umbralizar obteniendo una nueva imagen que "
            + "dependerá\ndel factor umbral introducido por el usuario, "
            + "pudiendo descargar esta imagen resultante.\nCreadores: "
            + "Víctor Herrera Delgado y Miguel Herrera Álvarez.\nVersión: 1.0\n"
            + "Versión de Java: 1.8.0_181";
    private final JFileChooser fc = new JFileChooser();
    private Mat actualImage;
    private int umbral;

    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }
    
    public practica7JFrame() {
        initComponents();
        this.setDefaultCloseOperation(javax.swing.WindowConstants.
                DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e){
                int res =JOptionPane.showConfirmDialog(null,
                        "¿Está seguro de que quiere salir de la aplicación?",
                        "Salir",JOptionPane.YES_NO_OPTION);
                if(res==JOptionPane.YES_OPTION){
                    quitarVentanas();
                    System.exit(0);
                }
            }
        });
        
        FileNameExtensionFilter filtro = new FileNameExtensionFilter
            ("ArchivosJPG","jpg","jpeg");
        fc.addChoosableFileFilter(filtro);
        FileNameExtensionFilter filtro2 = new FileNameExtensionFilter
            ("ArchivosPNG","png");
        fc.addChoosableFileFilter(filtro2);
        fc.setAcceptAllFileFilterUsed(false);
    }
    
    private void showImage(String name, BufferedImage actualBImage){
        try{
            DemoInternalFrame ifd = new DemoInternalFrame(name,actualBImage);
            ifd.setDefaultCloseOperation(javax.swing.WindowConstants.
                    DO_NOTHING_ON_CLOSE);
            ifd.addInternalFrameListener(new InternalFrameAdapter(){
                @Override
                public void internalFrameClosing(InternalFrameEvent e) {
                    if(ifd.isSaved() || ifd.getId()==1){
                        ifd.dispose();
                        if(ifd.getId()==1) quitarVentanas(); 
                    }else{
                        guardaVentanas(ifd,"¿Quiere guardar la imagen '" 
                                + ifd.getTitle() + "' antes de cerrarla?",
                                "Cerrar ventana");
                        ifd.dispose();
                    }
                    DemoInternalFrame.closed();
                }
            });
        
            ifd.setMinimumSize(new Dimension(500, 300));
            ifd.setSize(new Dimension(500, 300));
            
            jDesktopPane1.add(ifd);
            restructure();
            
            try {
                ifd.setSelected(true); //PONE EL FOCO EN EL NUEVO SELECCIONADO
            } catch (PropertyVetoException ex) {
                System.err.println("Closing Exception");
            }
        }catch(IOException ex){
            JOptionPane.showMessageDialog(null,ex,"Error",JOptionPane.
                    WARNING_MESSAGE);  
        }
    }
    
    private void quitarVentanas(){
        for (JInternalFrame ventana: jDesktopPane1.getAllFrames()) {
            try {
                ventana.setClosed(true);
            } catch (PropertyVetoException ex) {
                System.err.println("Closing Exception");
            }
        }
    }
    private void guardaVentanas(DemoInternalFrame saver,String message,String head){
        int res =JOptionPane.showConfirmDialog(null,message,head,
                JOptionPane.YES_NO_OPTION);
        if(res==JOptionPane.YES_OPTION){
            if (fc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                try {
                    if(fc.getFileFilter().getDescription().equals("ArchivosJPG")){
                        ImageIO.write(saver.getImage(), "jpg",new File
                            (fc.getSelectedFile().getAbsolutePath() + ".jpg"));
                        saver.setSaved();
                    }else{
                        ImageIO.write(saver.getImage(), "png",new File
                            (fc.getSelectedFile().getAbsolutePath() + ".png"));
                    }
                } catch (IOException e) {
                    System.err.println("Error de escritura");
                }
            }
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

        jLabel1 = new javax.swing.JLabel();
        myDialog = new javax.swing.JDialog();
        jLabel2 = new javax.swing.JLabel();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        openOption = new javax.swing.JMenuItem();
        saveOption = new javax.swing.JMenuItem();
        exitOption = new javax.swing.JMenuItem();
        editMenu = new javax.swing.JMenu();
        umbralOption = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        aboutOption = new javax.swing.JMenuItem();

        jLabel1.setText("jLabel1");

        myDialog.setTitle("Mi ventana");
        myDialog.setBounds(new java.awt.Rectangle(0, 23, 300, 200));
        myDialog.setModal(true);
        myDialog.setResizable(false);
        myDialog.setType(java.awt.Window.Type.POPUP);

        jLabel2.setText("jLabel2");

        javax.swing.GroupLayout myDialogLayout = new javax.swing.GroupLayout(myDialog.getContentPane());
        myDialog.getContentPane().setLayout(myDialogLayout);
        myDialogLayout.setHorizontalGroup(
            myDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(myDialogLayout.createSequentialGroup()
                .addGap(81, 81, 81)
                .addComponent(jLabel2)
                .addContainerGap(176, Short.MAX_VALUE))
        );
        myDialogLayout.setVerticalGroup(
            myDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(myDialogLayout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addComponent(jLabel2)
                .addContainerGap(145, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(520, 370));
        setSize(new java.awt.Dimension(700, 500));

        jDesktopPane1.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                jDesktopPane1ComponentResized(evt);
            }
        });

        fileMenu.setText("Archivo");

        openOption.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        openOption.setText("Abrir archivo");
        openOption.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openOptionActionPerformed(evt);
            }
        });
        fileMenu.add(openOption);

        saveOption.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.CTRL_MASK));
        saveOption.setText("Guardar archivo");
        saveOption.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveOptionActionPerformed(evt);
            }
        });
        fileMenu.add(saveOption);

        exitOption.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        exitOption.setText("Salir");
        exitOption.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitOptionActionPerformed(evt);
            }
        });
        fileMenu.add(exitOption);

        jMenuBar1.add(fileMenu);

        editMenu.setText("Editar");

        umbralOption.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, java.awt.event.InputEvent.CTRL_MASK));
        umbralOption.setText("Umbralizar");
        umbralOption.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                umbralOptionActionPerformed(evt);
            }
        });
        editMenu.add(umbralOption);

        jMenuBar1.add(editMenu);

        helpMenu.setText("Ayuda");

        aboutOption.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_H, java.awt.event.InputEvent.CTRL_MASK));
        aboutOption.setText("Acerca de");
        aboutOption.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutOptionActionPerformed(evt);
            }
        });
        helpMenu.add(aboutOption);

        jMenuBar1.add(helpMenu);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 674, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 467, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void openOptionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openOptionActionPerformed
        int res = fc.showOpenDialog(null);
        if (res == JFileChooser.APPROVE_OPTION) {
            File fichero = fc.getSelectedFile();
            if (fichero.exists()){
                actualImage = Imgcodecs.imread(fichero.getAbsolutePath());
                BufferedImage actualBImage = (BufferedImage) 
                        HighGui.toBufferedImage(actualImage);
                if(jDesktopPane1.getAllFrames().length != 0) quitarVentanas();
                DemoInternalFrame.reset();
                showImage(fichero.getName(), actualBImage);
            }else{
                JOptionPane.showMessageDialog(null,
                        "La imagen seleccionada no existe","Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_openOptionActionPerformed

    private void umbralOptionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_umbralOptionActionPerformed
        while(true){
            if(actualImage == null){
                JOptionPane.showMessageDialog(null,
                        "No hay ninguna imagen cargada","Error",
                        JOptionPane.ERROR_MESSAGE);
                break;
            }
            String res =JOptionPane.showInputDialog(null,
                    "Introduzca el valor del umbral (entre 0 y 255)",umbral);
            if(res == null) break;
            if(res.matches("[0-9]+") && parseInt(res) < 256 && 
                    parseInt(res) >= 0){
                showImage("Umbral=" + res,(BufferedImage) 
                        HighGui.toBufferedImage(umbralizar(parseInt(res))));
                umbral = parseInt(res);
                break;
            }else{
                JOptionPane.showMessageDialog(null,
                        "Introduzca solo números del 0 al 255","Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_umbralOptionActionPerformed

    private void saveOptionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveOptionActionPerformed
        DemoInternalFrame saver;
        if(jDesktopPane1.getSelectedFrame() instanceof DemoInternalFrame){ 
           saver =  (DemoInternalFrame) jDesktopPane1.getSelectedFrame();
        }else {
            JOptionPane.showMessageDialog(null,
                    "No hay ninguna imagen umbralizada seleccionada para guardar"
                    ,"Error",JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if(saver.getImage() == null || saver.getId() == 1 )
            JOptionPane.showMessageDialog(null,
                    "No hay ninguna imagen umbralizada seleccionada para guardar"
                    ,"Error",JOptionPane.ERROR_MESSAGE);
        
        else guardaVentanas(saver,"¿Desea guardar la imagen '" 
                + saver.getTitle()+ "'?","Guardar imagen");
    }//GEN-LAST:event_saveOptionActionPerformed

    private void jDesktopPane1ComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jDesktopPane1ComponentResized
        restructure();
    }//GEN-LAST:event_jDesktopPane1ComponentResized
    
    private void restructure(){
        for (JInternalFrame ventana : jDesktopPane1.getAllFrames()){
            if(ventana.getWidth() > jDesktopPane1.getWidth()) 
                    ventana.setSize(new Dimension(jDesktopPane1.getWidth(),
                    ventana.getHeight()) );
            if(ventana.getHeight() > jDesktopPane1.getHeight()) 
                    ventana.setSize(new Dimension(ventana.getWidth(),
                    jDesktopPane1.getHeight()) );
            if(ventana.getX() <= 0) ventana.setLocation(0,ventana.getY());
            else if (ventana.getX()+ ventana.getWidth()  >= 
                jDesktopPane1.getWidth() )
                ventana.setLocation(jDesktopPane1.getWidth()-ventana.getWidth(),
                    ventana.getY());
            if(ventana.getY() <= 0)ventana.setLocation(ventana.getX(),0);
            else if (ventana.getY()+ ventana.getHeight() >= 
                jDesktopPane1.getHeight() && ventana.getY() > 0 )
                ventana.setLocation(ventana.getX(),
                    jDesktopPane1.getHeight() - ventana.getHeight());
        }
    }
    
    private void exitOptionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitOptionActionPerformed
        int res =JOptionPane.showConfirmDialog(null,
                "¿Está seguro de que quiere salir de la aplicación?","Salir",
                JOptionPane.YES_NO_OPTION);
        if(res == JOptionPane.YES_OPTION){
            quitarVentanas();
            System.exit(0);
        }
    }//GEN-LAST:event_exitOptionActionPerformed

    private void aboutOptionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutOptionActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null,messageAbout,"Acerca de",
                JOptionPane.PLAIN_MESSAGE);
    }//GEN-LAST:event_aboutOptionActionPerformed
    private Mat umbralizar( Integer umbral) {
        Mat imagenGris = new Mat(actualImage.rows(), actualImage.cols(), 
                CvType.CV_8U);
        Mat imagenUmbralizada = new Mat(actualImage.rows(), actualImage.cols(),
                CvType.CV_8U);
         
        // convierte a niveles de grises la imagen original
        Imgproc.cvtColor(actualImage, imagenGris, Imgproc.COLOR_BGR2GRAY);
         
        // umbraliza la imagen:  
        // ‐ píxeles con nivel de gris > umbral se ponen a 1
        // ‐ píxeles con nivel de gris <= umbra se ponen a 0
        Imgproc.threshold(imagenGris, imagenUmbralizada, umbral, 255, 
                Imgproc.THRESH_BINARY);
        // se devuelve la imagen umbralizada
        return imagenUmbralizada;
    }

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : 
                    javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | 
                IllegalAccessException | 
                javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(practica7JFrame.class.getName()).
                    log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> {
            practica7JFrame app = new practica7JFrame();
            app.setTitle("Umbralizador");
            app.setLocationRelativeTo(null);
            app.setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem aboutOption;
    private javax.swing.JMenu editMenu;
    private javax.swing.JMenuItem exitOption;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JDialog myDialog;
    private javax.swing.JMenuItem openOption;
    private javax.swing.JMenuItem saveOption;
    private javax.swing.JMenuItem umbralOption;
    // End of variables declaration//GEN-END:variables
}