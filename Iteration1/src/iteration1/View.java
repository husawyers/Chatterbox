/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package iteration1;

import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JFrame;

/**
 *
 * @author Hebron
 */
public class View extends JFrame {

    public View() {
        super();
        
        setTitle("Chatterbox");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setVisible(true);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Initialize
        View view = new View();
        Model model = new Model();
        Controller controller = new Controller();
        
        /*
        // Test
        BufferedImage image = model.testImage();
        PhonemeAnimation animation = new PhonemeAnimation(image);
        view.getContentPane().add(animation);
        view.show();
        */
        // Test 2
        String word = controller.ask();
        ArrayList<BufferedImage> images = model.processInput(word);
        PhonemeAnimation animation = new PhonemeAnimation(images);
        Thread thread = new Thread(animation);
        thread.start();
        view.getContentPane().add(animation);
        view.show();
    }
}
