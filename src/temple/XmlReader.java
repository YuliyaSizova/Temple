/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package temple;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.reflection.PureJavaReflectionProvider;
import com.thoughtworks.xstream.io.xml.Dom4JDriver;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import temple.objects.Enemy;

/**
 *
 * @author ASUS
 */

public class XmlReader {
     static int loadObjects;

    public static class LoadObjectThread extends Thread {
        
    }
     public static void main(String[] args) throws FileNotFoundException, IOException {
      XStream xstream = new XStream(new PureJavaReflectionProvider(), new Dom4JDriver()); 
      xstream.processAnnotations(Enemy.class);
      Enemy[] en = new Enemy[0];
      xstream.alias("Enemies", en.getClass());
      Reader reader = new FileReader("Enemies.xml");
      Enemy[] enemies = (Enemy[]) xstream.fromXML(reader);
      reader.close();
      for (Enemy enemy : enemies) {
          System.out.println(enemy.getId()+enemy.getName());
      }
      
    } 
}
