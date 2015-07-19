/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iteration3.semantics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 *
 * @author Hebron
 */
public class Response {
    private Map responses;

    public Response() {
        responses = new HashMap<String, String[]>();
        
        responses.put(new String("hello"), new String[]{"hello", "hi", "howdy"});
        responses.put(new String("how are you"), new String[]{"i am a machine, i don't feel", "great", "good"});
        responses.put(new String("are you intelligent"), new String[]{"yes", "are you?"});
        
        responses.put(new String("non-sequitur"), new String[]{"why can't you?", "i'm not a robot?", "i might know that"});
    }
    
    public String generateResponse(String input) {
        String response = null;
        
        // If "bye", then quit
        if(input.equals("bye"))
        {
            System.exit(0);
        }
        
        // If other
        for (Object key : responses.keySet()) {
            if(((String)key).equals(input)) {
                String val[] = (String[])responses.get(key);
                
                Random rand = new Random();
                response = val[ rand.nextInt(val.length) ];
            }
        }
        // If unknown, then non-sequitur
        if(response == null) {
            String val[] = (String[])responses.get("non-sequitur");
            
            Random rand = new Random();
            response = val[ rand.nextInt(val.length) ];
        }
        
        return response;
    }
}
