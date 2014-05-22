/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package iteration2;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;
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
        Model model = new Model();
        View view = new View();
        Controller controller = new Controller();

        /*
         // Test
         BufferedImage image = model.testImage();
         PhonemeAnimation animation = new PhonemeAnimation(image);
         view.getContentPane().add(animation);
         view.show();
         */
        /*
         // Test 2
         PhonemeAnimation animation = new PhonemeAnimation();
         view.getContentPane().add(animation);
         view.show();

         String word = controller.ask();
         ArrayList<BufferedImage> images = model.processInput(word);
         animation.initialize(images);
         Thread thread = new Thread(animation);
         thread.start();
         */
        /*        
         // Test 3
         PhonemeAnimation animation = new PhonemeAnimation();
         view.getContentPane().add(animation);
         view.show();

         while (true) {
         // Get word
         String word = controller.ask();
         // Initialize speech
         TextToSpeech speech = new TextToSpeech(word);
         // Initialize mouth phonemes
         ArrayList<BufferedImage> images = model.processInput(word);
         animation.initialize(images);
         // Start mouth phonemes
         Thread thread = new Thread(animation);
         thread.start();
         // Start speech
         speech.speak();

         while (!animation.isFinished()) {
         }
         }
         */
        // Test 4
        PhonemeAnimation animation = new PhonemeAnimation(); // Could be accessed from the Model
        view.getContentPane().add(animation);
        view.show();
        
        // Randomly slect a word to turn into speech/mouth phonemes
        String strings[] = new String[]{"hello", "no", "yes", "can I", "uh", "do you", "please"};
        Random r = new Random();
        while (true) {
            // Get word
            String word = strings[r.nextInt(strings.length)];
            // Initialize mouth phonemes
            ArrayList<BufferedImage> images = model.processInput(word);
            animation.initialize(images);
            // Start mouth phonemes
            Thread thread = new Thread(animation);
            thread.start();
            // Start speech
            model.textToSpeech(word);

            while (thread.isAlive()) {
            }
        }
    }
}
