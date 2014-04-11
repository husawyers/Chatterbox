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
    
    public ArrayList<BufferedImage> processInput(String input)
    {
        if (input.isEmpty()) {
            JOptionPane.showMessageDialog(null, "ERROR: Empty input!");
            return null;
        }

        ArrayList<BufferedImage> tempImages = new ArrayList<>();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            boolean found = false;
            
            // " " gives mmm/pee/bee
            if(c == ' ')
            {
                tempImages.add(images.get("mmm.png")); System.out.println("mmm.png");
                continue;
            }
            
            // "e->l" gives eee->luh/laa
            if(c == 'e' &&
               i+1 < input.length() &&
               input.charAt(i+1) == 'l')
            {
                tempImages.add(images.get("eee.png")); System.out.println("eee.png");
                continue;
            }
            
            // Others
            for (Map.Entry e : images.entrySet()) {
                String key = (String) e.getKey();
                // others
                if (key.charAt(0) == c) {
                    tempImages.add((BufferedImage) e.getValue()); System.out.println(e.getKey());
                    found = true;
                    break;
                }
            }

            // Consonants
            if (!found) {
                tempImages.add(images.get("consonants.png")); System.out.println("cconsonants.png");
             }
        }
        // Shut the mouth
        tempImages.add(images.get("mmm.png"));
        
        return tempImages;
    }
    
    public BufferedImage testImage()
    {
        return images.get("ess ssss stum dee gee eeem consonants.png");
    }
}
