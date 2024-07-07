package Sources.GameState;

import java.awt.Graphics2D;
import java.util.Vector;
import java.awt.event.*;

import Sources.GamePanel;
import Sources.Entity.Crystal;
import Sources.Entity.Entity;
import Sources.Entity.Player;
import Sources.Map.Map;
import Sources.Render.MapRender;
import Sources.Render.PlayerRender;
import Sources.Tool.KeyHandler;

// Định nghĩa trạng thái chơi game
public class PlayState extends GameState {
    private Player player;
    private Map map;
    private MapRender mapRender;
    private PlayerRender playerRender;
    private Vector<Entity> entities;
    private Vector<Entity> crystals;
    private Vector<Entity> crystalRed;
    private Vector<Entity> crystalBlue;
    private Vector<Entity> crystalGreen;
    private Vector<Entity> crystalSilver;
    private Vector<Entity> crystalYellow;
    private Vector<Entity> boxRed;
    private Vector<Entity> boxBlue;
    private Vector<Entity> boxGreen;
    private Vector<Entity> boxSilver;
    private Vector<Entity> boxYellow;
    private boolean music;
    public int counter;
    public boolean event;

    public PlayState(GamePanel gamepanel, Map map,boolean event) {
        super(gamepanel);
        this.counter = 0;
        this.map = map;
        this.music = false;
        this.event = event;
        
        this.player = new Player(this.map.getLoadAndLoadMap().getPlayerPosition());
        this.playerRender = new PlayerRender(this); // Fix: Modify the PlayerRender constructor to accept a PlayState parameter
        this.entities = new Vector<>();
        this.crystals = new Vector<>();
        this.crystalRed = new Vector<>();
        this.crystalBlue = new Vector<>();
        this.crystalGreen = new Vector<>();
        this.crystalSilver = new Vector<>();
        this.crystalYellow = new Vector<>();
        this.boxRed = new Vector<>();
        this.boxBlue = new Vector<>();
        this.boxGreen = new Vector<>();
        this.boxSilver = new Vector<>();
        this.boxYellow = new Vector<>();
        this.mapRender = new MapRender(this);
        this.entities = this.getMap().getLoadMap().getEntities();
        this.crystals = this.getMap().getLoadMap().getCrystals();
        this.crystalRed = this.getMap().getLoadMap().getCrystalRed();
        this.crystalBlue = this.getMap().getLoadMap().getCrystalBlue();
        this.crystalGreen = this.getMap().getLoadMap().getCrystalGreen();
        this.crystalSilver = this.getMap().getLoadMap().getCrystalSilver();
        this.crystalYellow = this.getMap().getLoadMap().getCrystalYellow();
        this.boxRed = this.getMap().getLoadMap().getBoxRed();
        this.boxBlue = this.getMap().getLoadMap().getBoxBlue();
        this.boxGreen = this.getMap().getLoadMap().getBoxGreen();
        this.boxSilver = this.getMap().getLoadMap().getBoxSilver();
        this.boxYellow = this.getMap().getLoadMap().getBoxYellow();
    }

    @Override
    public void input(KeyHandler keyHandler) {
        if (keyHandler.getkeypresses()[(int) 'W'] || keyHandler.getkeypresses()[(int) 'A']
                || keyHandler.getkeypresses()[(int) 'D'] || keyHandler.getkeypresses()[(int) 'S']) {
            if (keyHandler.getkeypresses()[(int) 'S']) {
                player.setDirection(0);
            }
            if (keyHandler.getkeypresses()[(int) 'W']) {
                player.setDirection(1);
            }
            if (keyHandler.getkeypresses()[(int) 'D']) {
                player.setDirection(2);
            }
            if (keyHandler.getkeypresses()[(int) 'A']) {
                player.setDirection(3);
            }
            if (!this.player.hit(this.entities)) { // Nếu không va chạm với các entity khác
                this.player.move();
                player.setRunning(true);
            }
        } else {
            player.setRunning(false);
        }
        if (keyHandler.getkeypresses()[KeyEvent.VK_ESCAPE]) { // Nếu nhấn phím ESC thì tạm dừng game
            if(event == false){
                keyHandler.getkeypresses()[KeyEvent.VK_ESCAPE] = false;
                counter++;
                if (counter < 2) {
                    this.getGamepanel().getGamestatemanager().addState(new GamePause(this.getGamepanel())); // Tạm dừng game
                } else {
                    if (counter > 9) {
                        if (counter % 10 == 0)
                            this.getGamepanel().getGamestatemanager().addState(new GamePause(this.getGamepanel()));
                    }
                }
            }
            else if(event == true){
                keyHandler.getkeypresses()[KeyEvent.VK_ESCAPE] = false;
                counter++;
                if (counter < 2) {
                    this.getGamepanel().getGamestatemanager().addState(new GamePauseEvent(this.getGamepanel())); // Tạm dừng game
                } else {
                    if (counter > 9) {
                        if (counter % 10 == 0)
                            this.getGamepanel().getGamestatemanager().addState(new GamePauseEvent(this.getGamepanel()));
                    }
                }
            }
            
        } else {
            counter = 0;
        }
    }

    @Override
    public void render(Graphics2D g) {
        this.mapRender.Render(g);
        this.playerRender.render(g);
    }

    @Override
    public void update() {
        for (Entity entity : this.crystals) { // Duyệt qua các tinh thể
            if (((Crystal) entity).pullTheBox(entities)) {
                    this.getGamepanel().playSoundCrytal(2); // Phát âm thanh
            }
        }
        if(event == false){
            if (checkWinning()) { 
                if (music) {
                    this.getGamepanel().stopSoundRunning();
                    this.music = false;
                }
                // }
                int i = this.getGamepanel().getGameDataStore().getMapunlock(); // Lấy map đã mở
                int currentmap = this.getGamepanel().getMapManager().getCurrentMapint();
                if (currentmap < 17) {
                    currentmap++;
                }
                this.getGamepanel().getGameDataStore().setMapunlock(Math.max(i, currentmap)); // Cập nhật map đã mở
                this.getGamepanel().getSaveandload().save(); // Lưu lại
                this.getGamepanel().getMapManager().setCurrentMap(currentmap); // Chuyển sang map tiếp theo
                this.getGamepanel().getGamestatemanager().addState(new WinningState(this.getGamepanel())); // Chuyển sang
                                                                                                           // trạng thái
                                                                                                           // thắng
            } else {
                
                    if (player.isRunning()) { // Nếu nhân vật đang di chuyển thì phát nhạc bước chân
                        if (!music)
                            this.getGamepanel().playSoundRunning(1);
                        this.music = true;
                    } else {
                        if (music) {
                            this.getGamepanel().stopSoundRunning();
                            this.music = false;
                        }
                    }
            }
        }
        else if (event == true){
            
            if (checkWinningEvent()) { 
                if (music) {
                    this.getGamepanel().stopSoundRunning();
                    this.music = false;
                }
                // }
                int i = this.getGamepanel().getGameDataStore().getMapunlock(); // Lấy map đã mở
                int currentmap = this.getGamepanel().getMapManager().getCurrentMapEventint();
                if (currentmap < 17) {
                    currentmap++;
                }
                this.getGamepanel().getGameDataStore().setMapunlock(Math.max(i, currentmap)); // Cập nhật map đã mở
                //this.getGamepanel().getSaveandload().save(); // Lưu lại
                this.getGamepanel().getMapManager().setCurrentMapEvent(currentmap); // Chuyển sang map tiếp theo
                
                this.getGamepanel().getGamestatemanager().addState(new WinningStateEvent(this.getGamepanel())); // Chuyển sang
                                                                                                           // trạng thái
                                                                                                           // thắng
            } else {
                
                    if (player.isRunning()) { // Nếu nhân vật đang di chuyển thì phát nhạc bước chân
                        if (!music)
                            this.getGamepanel().playSoundRunning(1);
                        this.music = true;
                    } else {
                        if (music) {
                            this.getGamepanel().stopSoundRunning();
                            this.music = false;
                        }
                    }
            }
        }
        
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public MapRender getMapRender() {
        return mapRender;
    }

    public void setMapRender(MapRender mapRender) {
        this.mapRender = mapRender;
    }

    public PlayerRender getPlayerRender() {
        return playerRender;
    }

    public void setPlayerRender(PlayerRender playerRender) {
        this.playerRender = playerRender;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public boolean checkWinning() { // Kiểm tra điều kiện thắng
        for (Entity check : this.crystals) { // Duyệt qua các tinh thể
            Crystal input = (Crystal) check; // ép kiểu
            if (!input.hit(this.entities)) { // Nếu tinh thể không va chạm với box
                return false; // trả về false tức là chưa thắng
            }
        }
        return true;
    }

    public boolean checkWinningEvent() { 
        for (Entity check : this.crystalRed) { 
            Crystal input = (Crystal) check; 
            if (!input.hit(this.boxRed)) { 
                return false; 
            }
        }

        for (Entity check : this.crystalBlue) { 
            Crystal input = (Crystal) check; 
            if (!input.hit(this.boxBlue)) { 
                return false; 
            }
        }

        for (Entity check : this.crystalGreen) { 
            Crystal input = (Crystal) check; 
            if (!input.hit(this.boxGreen)) { 
                return false; 
            }
        }

        for (Entity check : this.crystalSilver) { 
            Crystal input = (Crystal) check; 
            if (!input.hit(this.boxSilver)) { 
                return false; 
            }
        }

        for (Entity check : this.crystalYellow) { 
            Crystal input = (Crystal) check; 
            if (!input.hit(this.boxYellow)) { 
                return false; 
            }
        }
        return true;
    }
}
