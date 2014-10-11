/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package temple.workWithLevels;

import temple.Layer;
/**
 *
 * @author Ламита
 */
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author ivko0314
 */
@XStreamAlias("level")
public class Level {

    private int width;
    private int height;
    private final Layer[] layers;
    
    public List<Layer> getLayers(){
        return Arrays.asList(layers);
    }
   public void setLayer(int index, Layer layer){
       layers[index]= layer;
   
   }
   public Layer getLayer(int index){
       return layers[index];
   
   }
    public Level(int width, int height, int countLayers) {
        this.width = width;
        this.height = height;
        layers = new Layer [countLayers];
        
    }

   // public List<Layer> layers = new ArrayList<>();

    /**
     * @return the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * @param width the width to set
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * @return the height
     */
    public int getHeight() {
        return height;
    }

    /**
     * @param height the height to set
     */
    public void setHeight(int height) {
        this.height = height;
    }
}
