/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package iteration3;

import iteration3.phonetics.TextToSpeech;
import iteration3.phonetics.PhonemeAnimation;
import iteration3.semantics.Response;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/**
 *
 * @author Hebron
 */
public class Model {

    private Map<String, BufferedImage> images;
    private TextToSpeech tts;
    private PhonemeAnimation animation;

    private Response response;
    private ArrayList<String> wordQueue;

    public Model(View view) {
        images = new HashMap<>();
        tts = new TextToSpeech();
        animation = new PhonemeAnimation();
        response = new Response();
        wordQueue = new ArrayList<>();
        // Add the animation JPanel to the frontend JFrame
        view.getContentPane().add(animation);
        view.show();

        // Initialize phoneme images
        File directory[] = new File("resources").listFiles();
        for (File file : directory) {
            images.put(file.getName(), loadImage(file));
        }
        // Set the face
        animation.setFace(images.get("face.png"));
    }

    private BufferedImage loadImage(File file) {
        BufferedImage img = null;

        try {
            img = ImageIO.read(file);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "ERROR: Failed to load image: " + file.getName());
            System.exit(1);
        }

        return img;
    }

    public Thread textToPhonemes(String input) {
        // Error check
        if (input.isEmpty()) {
            JOptionPane.showMessageDialog(null, "ERROR: Empty input in textToPhonemes()");
            return null;
        }

        // If number convert to words before processing
        try {
            int conv = Integer.parseInt(input);
            // Remove the number
            wordQueue.remove(0);

            // Add the words to the word queue
            String newWords[] = numberToWord(conv).split(" ");
            for (int i = newWords.length-1; i >= 0; i--) {
                wordQueue.add(0, newWords[i]);
            }
        } catch (NumberFormatException e) {
            // Nothing to do...
        }

        // Processing
        input = input.toLowerCase(); // Make the input characters uniformly lower case
        ArrayList<BufferedImage> tempImages = new ArrayList<>();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);

            // "", forming "m"
            if (c == ' ') {
                tempImages.add(images.get("m.png"));
                continue;
            }

            // "a"
            if (c == 'a') {
                tempImages.add(images.get("a.png"));
                continue;
            }

            // "b"
            if (c == 'b') {
                tempImages.add(images.get("b.png"));
                continue;
            }

            // "d"
            if (c == 'd') {
                tempImages.add(images.get("d.png"));
                continue;
            }

            // "e"or "eee"
            if (c == 'e') {
                // "ee"or "ey", forming "(eee)e"or "(eee)y"
                if (i + 1 < input.length() && (input.charAt(i + 1) == 'e' || input.charAt(i + 1) == 'y')) {
                    tempImages.add(images.get("eee.png"));
                } else {
                    tempImages.add(images.get("e.png"));
                }
                continue;
            }

            // "f"
            if (c == 'f') {
                tempImages.add(images.get("f.png"));
                continue;
            }

            // "g"
            if (c == 'g') {
                tempImages.add(images.get("g.png"));
                continue;
            }

            // "i"
            if (c == 'i') {
                tempImages.add(images.get("i.png"));
                continue;
            }

            // "l"
            if (c == 'l') {
                tempImages.add(images.get("l.png"));
                continue;
            }

            // "m"
            if (c == 'm') {
                tempImages.add(images.get("m.png"));
                continue;
            }

            // "o"or "oh!"
            if (c == 'o') {
                // "hol", forming "h(oh!)l"
                if (i - 1 > -1 && input.charAt(i - 1) == 'h'
                        && i + 1 < input.length() && input.charAt(i + 1) == 'l') {
                    tempImages.add(images.get("oh!.png"));
                } else {
                    tempImages.add(images.get("oooh.png"));
                }
                continue;
            }

            // "p"
            if (c == 'p') {
                tempImages.add(images.get("p.png"));
                continue;
            }

            // "s"
            if (c == 's') {
                tempImages.add(images.get("s.png"));
                continue;
            }

            // "u"
            if (c == 'u') {
                tempImages.add(images.get("u.png"));
                continue;
            }

            // "v"
            if (c == 'v') {
                tempImages.add(images.get("v.png"));
                continue;
            }

            // "w"
            if (c == 'w') {
                tempImages.add(images.get("w.png"));
                continue;
            }

            // Punctuation not handled, forming "m"
            Pattern p = Pattern.compile("\\!|\\?|\\'|\\,|//.|-");
            Matcher m = p.matcher("" + c);
            if (m.find()) {
                tempImages.add(images.get("m.png"));
                continue;
            }

            // consonants
            tempImages.add(images.get("consonants.png"));
        }

        // Shut the mouth, forming "m"
        tempImages.add(images.get("m.png"));

        /*try {
         Thread.sleep(1000);
         } catch (InterruptedException ex) {
         Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
         }*/
        // Set the phonemes
        animation.setPhonemes(tempImages);
        // Start the mouth phoneme animation 
        Thread thread = new Thread(animation);
        thread.start();
        // Return a reference to the thread so you can check isAlive on the frontend
        return thread;
    }

    public void textToSpeech(String word) {
        tts.speak(word);
    }

    public String generateReply(String input) {
        return response.generateResponse(input);
    }

    // Getters and Setters
    public ArrayList<String> getWordQueue() {
        return wordQueue;
    }

    // Utility
    private String numberToWord(int number) {
        int size = 0;

        int thousands = number / 1000;
        int hundreds = (number - (thousands * 1000)) / 100;
        int tens = (number - (thousands * 1000) - (hundreds * 100)) / 10;
        int ones = (number - (thousands * 1000) - (hundreds * 100) - (tens * 10)) / 1;

        // 9999 or under
        String answer = "";
        String convert = "";
        if (thousands > 0) {
            switch (thousands) {
                case 1:
                    convert = "one";
                    break;
                case 2:
                    convert = "two";
                    break;
                case 3:
                    convert = "three";
                    break;
                case 4:
                    convert = "four";
                    break;
                case 5:
                    convert = "five";
                    break;
                case 6:
                    convert = "six";
                    break;
                case 7:
                    convert = "seven";
                    break;
                case 8:
                    convert = "eight";
                    break;
                case 9:
                    convert = "nine";
                    break;
            }

            convert += " thousand ";
        }

        // 999 and under
        answer += convert;
        convert = "";
        if (hundreds > 0) {
            switch (hundreds) {
                case 1:
                    convert = "one";
                    break;
                case 2:
                    convert = "two";
                    break;
                case 3:
                    convert = "three";
                    break;
                case 4:
                    convert = "four";
                    break;
                case 5:
                    convert = "five";
                    break;
                case 6:
                    convert = "six";
                    break;
                case 7:
                    convert = "seven";
                    break;
                case 8:
                    convert = "eight";
                    break;
                case 9:
                    convert = "nine";
                    break;
            }

            convert += " hundred ";
        }

        answer += convert;
        convert = "";
        if (tens > 0) {
            switch (tens) {
                case 1: {
                    switch (ones) {
                        case 0:
                            convert = "ten";
                            break;
                        case 1:
                            convert = "eleven";
                            break;
                        case 2:
                            convert = "twelve";
                            break;
                        case 3:
                            convert = "thirteen";
                            break;
                        case 4:
                            convert = "fourteen";
                            break;
                        case 5:
                            convert = "fifteen";
                            break;
                        case 6:
                            convert = "sixteen";
                            break;
                        case 7:
                            convert = "seventeen";
                            break;
                        case 8:
                            convert = "eighteen";
                            break;
                        case 9:
                            convert = "nineteen";
                            break;
                    }
                    ones = 0;//hack
                    break;
                }
                case 2:
                    convert = "twenty";
                    break;
                case 3:
                    convert = "thirty";
                    break;
                case 4:
                    convert = "forty";
                    break;
                case 5:
                    convert = "fifty";
                    break;
                case 6:
                    convert = "sixty";
                    break;
                case 7:
                    convert = "seventy";
                    break;
                case 8:
                    convert = "eighty";
                    break;
                case 9:
                    convert = "ninety";
                    break;
            }

            if (thousands > 0 || hundreds > 0) {
                String temp = convert;
                convert = "and ";
                convert += temp;
            }
        }

        answer += convert;
        convert = "";
        if (ones > 0) {
            switch (ones) {
                case 1:
                    convert = "one";
                    break;
                case 2:
                    convert = "two";
                    break;
                case 3:
                    convert = "three";
                    break;
                case 4:
                    convert = "four";
                    break;
                case 5:
                    convert = "five";
                    break;
                case 6:
                    convert = "six";
                    break;
                case 7:
                    convert = "seven";
                    break;
                case 8:
                    convert = "eight";
                    break;
                case 9:
                    convert = "nine";
                    break;
            }

            if (thousands > 0 && tens == 0 || thousands > 0 && hundreds > 0 && tens == 0 || hundreds > 0 && tens == 0) {
                String temp = convert;
                convert = "and ";
                convert += temp;
            }
        }

        answer += convert;
        return answer;
    }
}
