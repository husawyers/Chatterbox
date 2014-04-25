/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package iteration1;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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

        String word = controller.ask();
        ArrayList<BufferedImage> images = model.processInput(word);
        animation.initialize(images);
        Thread thread = new Thread(animation);
        thread.start();
    }
}
