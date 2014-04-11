/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package iteration1;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/**
 *
 * @author Hebron
 */
public class Model {

    private Map<String, BufferedImage> images;

    public Model() {
        // Initialize
        images = new HashMap<>();

        File directory[] = new File("resources").listFiles();
        for (File file : directory) {
            images.put(file.getName(), loadImage(file));
        }
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

    public ArrayList<BufferedImage> processInput(String input) {
        if (input.isEmpty()) {
            JOptionPane.showMessageDialog(null, "ERROR: Empty input!");
            return null;
        }

        ArrayList<BufferedImage> tempImages = new ArrayList<>();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            boolean found = false;

            // " ", forming "m"
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

            // "e"
            if (c == 'e') {
                    tempImages.add(images.get("e.png"));
                    continue;
            }
            // WIP - eee

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
            
            // "o"
            if (c == 'o') {
                tempImages.add(images.get("oooh.png"));
                continue;
            }
            // WIP - oh!
            
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
            
            // consonants
            tempImages.add(images.get("consonants.png"));
        }

        // Shut the mouth, forming "m"
        tempImages.add(images.get("m.png"));

        return tempImages;
    }

    public BufferedImage testImage() {
        return images.get("ess ssss stum dee gee eeem consonants.png");
    }
}
