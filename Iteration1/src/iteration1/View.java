/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package iteration1;

import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        PhonemeAnimation animation = new PhonemeAnimation();
        view.getContentPane().add(animation);
        view.show();

        while (true) {
            String word = "";
            while (true) {
                if (animation.done()) { // Works the second time round with Thread.sleep()/Debug mode
                    word = controller.ask();
                    break;
                }
            }
            ArrayList<BufferedImage> images = model.processInput(word);
            animation.init(images);
            Thread thread = new Thread(animation);
            thread.start();
        }
    }
}
