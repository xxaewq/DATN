package Sources;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.*;
import javax.swing.JPanel;

import Sound.Sound;
import Sources.GameState.GameMenu;
import Sources.GameState.GameStateManager;
import Sources.Map.MapManager;
import Sources.Tool.KeyHandler;


public class GamePanel extends JPanel implements Runnable {

	private final int originaltilesize = 8;
    private int scale;
    private int tilesize;
    private int screencol;
    private int screenrow;
    private int screenheight;
    private int screenwidth;
    private Thread gamethread;
    private KeyHandler keyhandler;
    private GameStateManager gamestatemanager;
    private MapManager MapManager;
    private GameDataStore gameDataStore;
    private SaveAndLoad saveandload;
    //private GuideState guideState;
    private GameMenu gameMenu;
    private Sound sound;
    private Sound soundCrytal;
    private Sound soundRunning;





    public GamePanel(){
        this.scale = 8;
        this.tilesize = this.originaltilesize*this.scale;
        this.screencol = 21;
        this.screenrow = 12;
        this.screenheight = this.screenrow*tilesize;
        this.screenwidth = this.screencol*tilesize;
        this.keyhandler = new KeyHandler();
        this.setPreferredSize(new Dimension(this.screenwidth,this.screenheight));
        this.setBackground(new Color(137,136,136));
        this.setDoubleBuffered(true);   
        this.setFocusable(true);
        this.addKeyListener(this.keyhandler);
        this.gamestatemanager = new GameStateManager(this);
        this.MapManager = new MapManager();
        this.gameDataStore = new GameDataStore();
        this.saveandload = new SaveAndLoad(this);
        this.saveandload.load();
        this.sound = new Sound();
        this.soundCrytal = new Sound();
        this.soundRunning = new Sound();
        gamethread = new Thread(this);
        //this.guideState = new GuideState(this);
        this.gameMenu = new GameMenu(this);
        this.gamethread.start();
        this.playSoundEffect(0);
    }

    @Override
    public void run() {
        double timeperwindow = 1e9/60;
        double delta = 0;
        long lastime = System.nanoTime();
        long currentime = 0;
        while(gamethread!=null){
            currentime = System.nanoTime();
            delta = (currentime - lastime)/timeperwindow;
            if(delta >= 1){
                input();
                update();
                repaint();
                lastime = currentime;
                delta = 0;
            }
        }
    }
    public void update(){
        this.gamestatemanager.update();
    }
    public void input(){
        this.gamestatemanager.input(this.keyhandler);
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        this.gamestatemanager.render(g2);
    }


    public KeyHandler getKeyHandler(){
        return this.keyhandler;
    }
    public GameStateManager getGamestatemanager() {
        return gamestatemanager;
    }

    public void setGamestatemanager(GameStateManager gamestatemanager) {
        this.gamestatemanager = gamestatemanager;
    }
    public MapManager getMapManager() {
        return MapManager;
    }

    public void setMapManager(MapManager mapManager) {
        MapManager = mapManager;
    }
    public Thread getGamethread() {
        return gamethread;
    }
    public void setGamethread(Thread gamethread) {
        this.gamethread = gamethread;
    }
    
    public GameDataStore getGameDataStore() {
        return gameDataStore;
    }
    public void setGameDataStore(GameDataStore gameDataStore) {
        this.gameDataStore = gameDataStore;
    }
    public SaveAndLoad getSaveandload() {
        return saveandload;
    }

    public void setSaveandload(SaveAndLoad saveandload) {
        this.saveandload = saveandload;
    }
    public void setGameMenu(GameMenu gameMenu){
        this.gameMenu = gameMenu;
    }
    public GameMenu getGameMenu(){
        return gameMenu;
    }

    public void playSoundEffect(int i){
        this.sound.setFile(i);
        this.sound.play();
        this.sound.loop();
    }
    public void playSoundRunning(int i){
        this.soundRunning.setFile(i);
        this.soundRunning.play();
        this.soundRunning.loop();
    }
    public void stopSoundEffect(){
        this.sound.stop();
    }
    public void stopSoundRunning(){
        this.soundRunning.stop();
    }
    
    public void playSoundCrytal(int i){
        this.soundCrytal.setFile(i);
        this.soundCrytal.play();
    }
    public void stopSoundCrytal(){
        this.soundCrytal.stop();
    }




    //public void playSoundEffect1(int i){
        //     this.sound1.setFile(i);
        //     this.sound1.play();
        //     this.sound1.loop();
        // }
    // public void stopSoundEffect1(){
    //     this.sound1.stop();
    // }
    // public void stopSoundEffect1(int i){
    //     this.sound1.setFile(i);
    //     this.sound1.stop();
    // }
    // public void stopSoundEffect(int i){
    //     this.sound.setFile(i);
    //     this.sound.stop();
    // }
    // public void playSound(int i){
    //     this.sound.setFile(i);
    //     this.sound.play();
    // }
}
    
