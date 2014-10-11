/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package temple;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Properties;
import java.util.StringTokenizer;
import temple.workWithLevels.Level;

/**
 *
 * @author Ламита
 */
public class Temple {

    static int loadLayer;

    public static class LoadLevelThread extends Thread {

        int number;
        int width;
        int height;
        Level level;

        public LoadLevelThread(int number, Level level) {
            this.number = number;
            this.width = level.getWidth();
            this.height = level.getHeight();
            this.level = level;
        }

        public void run() {
            //код загрузки уровня
            try (FileInputStream input = new FileInputStream(number + ".txt")) {
                Properties properties = new Properties();
                properties.load(input);
                String elements = properties.getProperty("elements");
                EnemyLayer layer = new EnemyLayer();
                int[][] layerArray = new int[width][height];
                StringTokenizer st = new StringTokenizer(elements);
                for (int k = 0; k < width; k++) {
                    for (int j = 0; j < height; j++) {
                        layerArray[k][j] = Integer.parseInt(st.nextToken());
                    }
                }
                layer.elements = layerArray;
                level.setLayer(number-1, layer);

            } catch (Exception e) {
            }
            loadLayer++;
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        readLevel();
    }

    static void readLevel() throws Exception {
        int height;
        int width;
        int countLayer;
        try (FileInputStream input = new FileInputStream("level.txt")) {
            Properties levelProperties = new Properties();
            levelProperties.load(input);
            height = Integer.parseInt(levelProperties.getProperty("height"));
            width = Integer.parseInt(levelProperties.getProperty("width"));
            countLayer = Integer.parseInt(levelProperties.getProperty("layerNum"));
        }
        Level level = new Level(width, height, countLayer);
        for (int i = 1; i <= countLayer; i++) {
            LoadLevelThread levelTread = new LoadLevelThread(i, level);
            levelTread.start();
        }
        while (loadLayer < countLayer) {//барьер
        }
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                System.out.print(level.getLayer(0).elements[i][j] + " ");
            }
            System.out.println("");
        }
    }

    static void createLevel() throws Exception {
        int height = 10;
        int width = 10;
        int countLayer = 1;
        int[][] layerArray = new int[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                layerArray[i][j] = (int) (Math.random() * 50);
            }
        }
        EnemyLayer layer = new EnemyLayer();
        layer.elements = layerArray;
        Level level = new Level(width, height, countLayer);
        level.setLayer(countLayer - 1, layer);
        Properties levelProperties = new Properties();
        levelProperties.setProperty("width", Integer.toString(width));
        levelProperties.setProperty("height", Integer.toString(height));
        levelProperties.setProperty("layerNum", Integer.toString(level.getLayers().size()));

        try (FileOutputStream output = new FileOutputStream("level.txt")) {
            levelProperties.store(output, "Level parameters");
        }
        for (Layer currentEnemyLayer : level.getLayers()) {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    builder.append(currentEnemyLayer.elements[i][j]).append(" ");
                }
            }
            Properties layerProperties = new Properties();
            layerProperties.setProperty("elements", builder.toString());
            try (FileOutputStream output = new FileOutputStream(countLayer + ".txt")) {
                layerProperties.store(output, "EnemyLayer parameters");
            }
            countLayer++;
        }

    }

}
