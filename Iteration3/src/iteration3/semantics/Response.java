/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iteration3.semantics;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

/**
 *
 * @author Hebron
 */
public class Response {
    private Map responses;
    private String nonSequitur[];

    public Response() {
        responses = new HashMap<String, String[]>();

        // Simple reponses
        responses.put(new String("hello"), new String[]{"hello", "hi", "howdy"});
        responses.put(new String("how are you"), new String[]{"i am a machine, i don't feel", "great", "good"});
        responses.put(new String("are you intelligent"), new String[]{"yes", "are you?"});

        // Non sequiturs
        nonSequitur = new String[]{"i'm not a robot", "i'll go find out", "could you repeat the question"};

        // More complex responses
        // Time i.e. GMT:
        String date[] = new Date().toGMTString().split(":");
        responses.put(new String("time"), new String[]{date[0] + " " + date[1]});
        
        // Weather i.e. Bristol:
        try {
            // Get the data from OpenWeatherMap
            URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q=Bristol&mode=xml");
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            String inputLine = "";
            inputLine = reader.readLine();
            reader.close();
            
            // Extract the temperature
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            ByteArrayInputStream input = new ByteArrayInputStream(inputLine.getBytes("UTF-8"));
            Document document = builder.parse(input);
            Element element = document.getDocumentElement();
            
            Element temp = (Element)element.getElementsByTagName("temperature").item(0);
            String attribute = temp.getAttribute("value");
            
            // Convert from kelvin to celsius
            int answer =  (int)Double.parseDouble(attribute);
            answer -= 273.15;
            
            responses.put(new String("weather"), new String[]{answer + " degrees celsius"});
        } catch (Exception ex) {
            Logger.getLogger(Response.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String generateResponse(String input) {
        String response = null;
        
        // If "bye", then quit
        if(input.equals("bye"))
        {
            System.exit(0);
        }
        
        // If known words, then generate a response
        for (Object key : responses.keySet()) {
            if(input.contains((String)key)) {
                // Get response
                String val[] = (String[])responses.get(key);
                
                // If it's the time response, refresh before turning a response
                String date[] = new Date().toGMTString().split(":");
                responses.put(new String("time"), new String[]{date[0] + " " + date[1]});
                //--------------------------------------------------------------
                
                // Return response
                Random rand = new Random();
                response = val[ rand.nextInt(val.length) ];
            }
        }
        
        // Else, non-sequitur
        if(response == null) {
            Random rand = new Random();
            response = nonSequitur[ rand.nextInt(nonSequitur.length) ];
        }
        
        return response;
    }
}
