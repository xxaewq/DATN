package Sources.Map;
import java.awt.image.*;
import javax.imageio.ImageIO;
import Sources.Tool.LoadMap;


//Định nghĩa 1 map
public class Map {
    private LoadMap loadMap;
    private String source;
    private BufferedImage minimap;
    private BufferedImage minimapEvent;
    private int x;
    private int y;
    private int nomap;
    private int nomapevent;  
    private boolean done;   
    public String getSource() { 
        return source;
    }
    public void setSource(String source) {
        this.source = source;
    }
    public Map(String name,int x,int y,int nomap){
        this.source = name;
        this.x = x;
        this.y = y;
        this.nomap = nomap;
        this.nomapevent = nomap;
        this.done = false;
        try {
            this.minimap = ImageIO.read(getClass().getResourceAsStream("/Image/map0"+this.nomap+".png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            this.minimapEvent = ImageIO.read(getClass().getResourceAsStream("/ImageModeEvent/map0"+this.nomapevent+".png"));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public LoadMap getLoadAndLoadMap() {
        this.loadMap = new LoadMap(this.source);
        return loadMap;
    }
    public LoadMap getLoadMap(){
        return this.loadMap;
    }
    public void setLoadMap(LoadMap loadMap) {
        this.loadMap = loadMap;
    }
    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }
    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
    public int getNomap() {
        return nomap;
    }
    public int getNomapEvent() {
        return nomapevent;
    }

    public void setNomap(int nomap) {
        this.nomap = nomap;
    }
    public void setNomapEvent(int nomapevent) {
        this.nomapevent = nomapevent;
    }
    public BufferedImage getMinimap() {
        return minimap;
    }
    public BufferedImage getMinimapEvent() {
        return minimapEvent;
    }
    public void setMinimap(BufferedImage minimap) {
        this.minimap = minimap;
    }
    public void setMinimapEvent(BufferedImage minimapEvent) {
        this.minimapEvent = minimapEvent;
    }
}
