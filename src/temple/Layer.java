/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package temple;

import temple.objects.SolidObject;

/**
 *
 * @author Ламита
 */
public abstract class Layer<T> {

   protected int[][] elements;

    public abstract T get(int row, int col);
    public void set(int row, int col, int element){
      elements[row][col] = element;  
    };

    public T getByPosition(int x, int y) {
        //найти элемент по координатам
        int row=y/GameStore.getHeight();
        int col=x/GameStore.getWidth();
            return get(row, col);

            
    }

    /*)T getObjectInCell(int row, int col) {
     return null;
     // код
     }*/
    // другие методы, одинаковые для всех слоёв
}
