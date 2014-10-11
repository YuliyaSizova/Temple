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
import temple.objects.*;

/**
 *
 * @author ASUS
 */
public class XmlReader {

    static int loadObjects;

    enum Objects {

        ENEMY, DYNAMIC_OBJECTS, LADDER, SOLID_OBJECTS
    };

    public static class LoadObjectThread extends Thread {

        String file;
        String alias;
        Objects type;

        private LoadObjectThread(String file, String alias, Objects type) {
            this.alias = alias;
            this.file = file;
            this.type = type;
        }

        public void run() {
            try {
                switch (type) {
                    case ENEMY: {
                        XStream xstream = new XStream(new PureJavaReflectionProvider(), new Dom4JDriver());
                        xstream.processAnnotations(Enemy.class);
                        Enemy[] en = new Enemy[0];
                        xstream.alias(alias, en.getClass());
                        Reader reader = new FileReader(file);
                        Enemy[] enemies = (Enemy[]) xstream.fromXML(reader);
                        reader.close();
                        for (Enemy enemy : enemies) {
                            System.out.println(enemy.getId() + enemy.getName());
                        }
                        break;
                    }
                    case DYNAMIC_OBJECTS: {
                        XStream xstream = new XStream(new PureJavaReflectionProvider(), new Dom4JDriver());
                        xstream.processAnnotations(DynamicObject.class);
                        DynamicObject[] en = new DynamicObject[0];
                        xstream.alias(alias, en.getClass());
                        Reader reader = new FileReader(file);
                        DynamicObject[] objects = (DynamicObject[]) xstream.fromXML(reader);
                        reader.close();
                        for (DynamicObject object : objects) {
                            System.out.println(object.getId() + object.getName());
                        }
                        break;
                    }
                    case LADDER: {
                        XStream xstream = new XStream(new PureJavaReflectionProvider(), new Dom4JDriver());
                        xstream.processAnnotations(Ladder.class);
                        Ladder[] en = new Ladder[0];
                        xstream.alias(alias, en.getClass());
                        Reader reader = new FileReader(file);
                        Ladder[] ladders = (Ladder[]) xstream.fromXML(reader);
                        reader.close();
                        for (Ladder ladder : ladders) {
                            System.out.println(ladder.getId() + ladder.getName());
                        }
                        break;
                    }
                    case SOLID_OBJECTS: {
                        XStream xstream = new XStream(new PureJavaReflectionProvider(), new Dom4JDriver());
                        xstream.processAnnotations(SolidObject.class);
                        SolidObject[] en = new SolidObject[0];
                        xstream.alias(alias, en.getClass());
                        Reader reader = new FileReader(file);
                        SolidObject[] solids = (SolidObject[]) xstream.fromXML(reader);
                        reader.close();
                        for (SolidObject solid : solids) {
                            System.out.println(solid.getId() + solid.getName());
                        }
                        break;
                    }
                }

            } catch (Exception ex) {
            }
        }
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        LoadObjectThread objectThreadLadder = new LoadObjectThread("Ladders.xml", "Ladders", Objects.LADDER);
        LoadObjectThread objectThreadEnemy = new LoadObjectThread("Enemies.xml", "Enemies", Objects.ENEMY);
        LoadObjectThread objectThreadSolid = new LoadObjectThread("SolidObjects.xml", "Objects", Objects.SOLID_OBJECTS);
        LoadObjectThread objectThreadDynamic = new LoadObjectThread("DynamicObjects.xml", "Objects", Objects.DYNAMIC_OBJECTS);
        objectThreadDynamic.start();
        objectThreadSolid.start();
        objectThreadEnemy.start();
        objectThreadLadder.start();
    }
}
