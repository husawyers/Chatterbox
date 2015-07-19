/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package iteration3.phonetics;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.Timer;
import javax.swing.JPanel;

/**
 *
 * @author Hebron
 */
public class PhonemeAnimation extends JPanel implements Runnable {

    private ArrayList<BufferedImage> images;
    private BufferedImage face;
    private int frame;
    
    public PhonemeAnimation() {
        super();
        setVisible(true);
        
        this.images = null;
        face = null;
        frame = 0;
    }
    
    public void setFace(BufferedImage image) {
        face = image;
    }
    
    public void setPhonemes(ArrayList<BufferedImage> images) {
        this.images = images;
        frame = 0;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        // If there are images to be animated, then draw the current frame
        if(this.images != null)
        {
            g.drawImage(images.get(frame++), 134, 160, null); // centre ~= (400 - 128) / 2 OR 136
            g.drawImage(face, 0, 0, null);
        }
    }

    @Override
    public void run() {
        // Iterate through the animation images to be drawn
        while(frame < images.size())
        {
            try {
                repaint();
                //System.out.println(frame);
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(PhonemeAnimation.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
