/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package iteration3;

import javax.swing.JOptionPane;

/**
 *
 * @author Hebron
 */
public class Controller {

    public Controller() {
    }
    
    public String ask()
    {
        return JOptionPane.showInputDialog("Input a word: ");
    }
    public String ask(String message)
    {
        return JOptionPane.showInputDialog(message);
    }
}
