package Sources.Tool;

import java.util.Vector;
import Sources.Entity.Entity;

// Định nghĩa lớp LoadMap : load map từ file
public class LoadMap {
    protected Load load;
    protected Vector<Entity> maplayer;
    
    public LoadMap(String path){
        this.load = new Load(path);
        load.LoadMap();
        this.maplayer = this.load.getEntities();
    }
    public Vector<Entity> getMaplayer() {
        return maplayer;
    }
    public void setMaplayer1(Vector<Entity> maplayer) {
        this.maplayer = maplayer;
    }
    public Vector<Entity> getEntities(){
        return this.load.getEntities();
    }
    public Vector<Integer> getPlayerPosition(){
        return load.getPlayerPosition();
    }
    public Vector<Entity> getCrystals(){
        return load.crystals;
    }

    
    public Vector<Entity> getCrystalRed(){
        return load.getCrystalRed();
    }
    public Vector<Entity> getCrystalBlue(){
        return load.getCrystalBlue();
    }
    public Vector<Entity> getCrystalGreen(){
        return load.getCrystalGreen();
    }
    public Vector<Entity> getCrystalYellow(){
        return load.getCrystalYellow();
    }
    public Vector<Entity> getCrystalSilver(){
        return load.getCrystalSilver();
    }


    public Vector<Entity> getBoxRed(){
        return load.getBoxRed();
    }
    public Vector<Entity> getBoxBlue(){
        return load.getBoxBlue();
    }
    public Vector<Entity> getBoxGreen(){
        return load.getBoxGreen();
    }
    public Vector<Entity> getBoxYellow(){
        return load.getBoxYellow();
    }
    public Vector<Entity> getBoxSilver(){
        return load.getBoxSilver();
    }

}
