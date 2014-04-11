/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package iteration1;

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

    private BufferedImage image;
    private ArrayList<BufferedImage> images;
    private int frame;
    
    public PhonemeAnimation(BufferedImage image) {
        super();
        setVisible(true);
        
        this.image = image;
    }
    
    public PhonemeAnimation(ArrayList<BufferedImage> images) {
        super();
        setVisible(true);
        
        this.images = images;
        frame = 0;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(images.get(frame++), 0, 0, null);
    }

    @Override
    public void run() {
        while(frame < images.size())
        {
            try {
                repaint(); System.out.println(frame);
                Thread.sleep(300);
            } catch (InterruptedException ex) {
                Logger.getLogger(PhonemeAnimation.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
