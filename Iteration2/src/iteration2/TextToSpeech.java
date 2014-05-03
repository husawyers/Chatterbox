/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package iteration2;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hebron
 */
public class TextToSpeech {
    private final String voiceName;
    private String text;
    Voice voice;

    public TextToSpeech() {
        voiceName = "kevin";
        this.text = text;
        
        // Initialize TTS
        voice = null;
        VoiceManager voiceManager = VoiceManager.getInstance();
        voice = voiceManager.getVoice(voiceName);
        // Loads the voice
        voice.allocate();
        
        /*try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(TextToSpeech.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }
    
    public void speak(String text)
    {
        voice.speak(text);
    }
}
