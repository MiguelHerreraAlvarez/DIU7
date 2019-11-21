import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.swing.JInternalFrame;

public final class DemoInternalFrame extends JInternalFrame {
    //Contador estático que aumenta cada vez que instanciamos una ventana.
    private static int openFrameCount = 0;
    private boolean saved;
    private final int id;
    //Posición de la ventana interna.
    private static final int xOffset = 50, yOffset = 50;
    private static int posX = 0, posY = 0;
    private BufferedImage image;
    public DemoInternalFrame(String titulo,BufferedImage image) throws IOException {
        super(titulo,
                true, //Resizable
                true, //Closable
                true, //Maximizable
                true);//Iconifiable
        openFrameCount++;
        this.id = openFrameCount;
        initComponents(image);
    }
    public void initComponents(BufferedImage img){
        image = img;
        setVisible(true);
        setResizable(true);
        saved=false;
        //Ponemos la localición de la ventana.
        setLocation(posX, posY);
        posX = xOffset * openFrameCount;
        posY = yOffset * openFrameCount;

        try {
            practica7JPanel panel = new practica7JPanel();
            panel.setImage(image);
            add(panel);
            pack();
        } catch (Exception e) {
            System.out.print(e);
        }
    }
    
    public BufferedImage getImage(){
        return image;
    }
    
    public static void closed(){
        if(openFrameCount > 1 ){
            posX -= xOffset ;
            posY -= yOffset ;
        }
        openFrameCount--;
    }
    
    public static void reset(){
        posX = 0; posY = 0;
        openFrameCount= 0;
    }
    
    public boolean isSaved(){
        return saved;
    }
    
    public void setSaved(){
        saved = true;
    }
    
    public int getId(){
        return id;
    }
}
