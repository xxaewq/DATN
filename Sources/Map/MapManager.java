package Sources.Map;

import java.util.Vector;

public class MapManager {
    private Vector<Map> vectormap;
    private Vector<Map> vectormapEvent;
    private int maxMap;
    private int currentMap;
    private int currentMapEvent;
    public MapManager(){
        this.maxMap = 18;
        this.currentMap = 1;
        this.currentMapEvent = 1;
        this.vectormap = new Vector<>();
        this.vectormapEvent = new Vector<>();
        setUp();
        setUpEventMap();
    }

    public void setUp(){
        for(int i = 0; i < maxMap; i++){
            Map inputmap = new Map("/Map/map0"+(i+1)+".txt",i%3,i/3,i+1);
            inputmap.setNomap(i+1);
            this.vectormap.add(inputmap);
        }
    }
    public void setUpEventMap(){
        for(int i = 0; i < maxMap; i++){
            Map inputmapEvent = new Map("/MapEventMode/map0"+(i+1)+".txt",i%3,i/3,i+1);
            inputmapEvent.setNomapEvent(i+1);
            this.vectormapEvent.add(inputmapEvent);
        }
    }
    public Map getCurrentMap() {
        return this.vectormap.elementAt(currentMap-1);
    }
    public Map getCurrentMapEvent() {
        return this.vectormapEvent.elementAt(currentMapEvent-1);
    }
    public int getCurrentMapint() {
        return this.currentMap;
    }
    public int getCurrentMapEventint() {
        return this.currentMapEvent;
    }

    public void setCurrentMap(int currentMap) {
        this.currentMap = currentMap;
    }
    public void setCurrentMapEvent(int currentMapEvent) {
        this.currentMapEvent = currentMapEvent;
    }
    public Vector<Map> getVectormap() {
        return vectormap;
    }
    public Vector<Map> getVectormapEvent() {
        return vectormapEvent;
    }

    public void setVectormap(Vector<Map> vectormap) {
        this.vectormap = vectormap;
    }
    public void setVectormapEvent(Vector<Map> vectormapEvent) {
        this.vectormapEvent = vectormapEvent;
    }
}
