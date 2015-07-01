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
    public static void main(String[] args) throws InterruptedException {
        // Initialize
        View view = new View();
        Model model = new Model(view);
        Controller controller = new Controller();
        
        // Run
        ArrayList<String> wordQueue = new ArrayList<>();
        Thread threadRef = null;
        while(true)
        {
            // If the AI has no more words to say, they ask for more input
            String input = "";
            String reply = "";
            if(wordQueue.isEmpty())
            {
                input = controller.ask("Input a message (or Q to quit): ").toLowerCase();
                
                if(input.equals("q"))
                {
                    System.exit(0);
                }
                
                reply = model.generateReply(input);
                
                String words[] = reply.split(" ");
                for (String word : words) {
                    wordQueue.add(word);
                }
            }
            
            // Make sure each word is spoken before moving to the next, for sync
            if(threadRef == null || !threadRef.isAlive())
            {
                threadRef = model.inputToPhonemes(wordQueue.get(0));
                model.textToSpeech(wordQueue.get(0));
                
                wordQueue.remove(0);
            }
        }
    }
}
