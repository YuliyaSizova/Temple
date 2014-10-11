/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package temple;

import java.util.ArrayList;
import java.util.List;
import temple.objects.DynamicObject;
import temple.objects.Enemy;
import temple.objects.Ladder;
import temple.objects.SolidObject;

/**
 *
 * @author ASUS
 */
public class GameStore {
    private static int height = 32;
    private static int width = 32;

    /**
     * @return the height
     */
    public static int getHeight() {
        return height;
    }

    /**
     * @return the width
     */
    public static int getWidth() {
        return width;
    }

    List<Enemy> enemies = new ArrayList<>();
    public static Enemy getEnemy(int id) {
        return null;
    }
    public static SolidObject getSolid(int id) {
        return null;
    }
    public static Ladder getLadder(int id) {
        return null;
    }
    public static DynamicObject getDynamicObject(int id) {
        return null;
    }
}
