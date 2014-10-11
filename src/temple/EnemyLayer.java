/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package temple;

import temple.objects.Enemy;

/**
 *
 * @author Ламита
 */
    public class EnemyLayer extends Layer<Enemy> {

    @Override
    public Enemy get(int row, int col) {
        int id=elements[row][col];
        return GameStore.getEnemy(id);
    }
 /* ничего нет */}