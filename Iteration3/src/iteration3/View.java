/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package iteration3;

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
        View view = new View();
        Model model = new Model(view);
        Controller controller = new Controller();

        // Run
        while(true)
        {
            String input = controller.ask("Input a message (or Q to quit): ");
            if(input.equalsIgnoreCase("Q"))
            {
                System.exit(0);
            }
            
            String reply = model.generateReply(input);
            
            Thread threadRef = model.inputToPhonemes(reply);
            model.textToSpeech(reply); // HACK: Start speech after phonemes to sync them (works for 1-2 words)
        }
    }
}
