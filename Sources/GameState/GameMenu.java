package Sources.GameState;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import javax.imageio.ImageIO;
import java.awt.image.*;
import Sources.GamePanel;
import Sources.Tool.KeyHandler;

// Trạng thái của game khi mở menu
public class GameMenu extends GameState {
    public BufferedImage background;
    public BufferedImage playerFace;
    private int choice; // Lựa chọn
    private int counter;    // Đếm thời gian
    public GameMenu(GamePanel gamepanel) {
        super(gamepanel);
        this.choice = 1;    // Mặc định lựa chọn là 1(New Game)
        this.counter = 0;
        try {
            this.background = ImageIO.read(getClass().getResourceAsStream("/Image/background.png"));    
            this.playerFace = ImageIO.read(getClass().getResourceAsStream("/Image/playerFace.png")); 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    @Override
    public void input(KeyHandler keyHandler) {
        if(keyHandler.getkeypresses()[(int) 'S']){  // Nếu nhấn phím S thì di chuyển xuống
            counter++;  
            if(counter<2){  
                choice++;
            }
            else{
                if(counter>19){     
                    if(counter%20==0)
                    choice++;
                }
            }
            if(choice>5){
                choice = 5;
            }
        }
        else if(keyHandler.getkeypresses()[(int) 'W']){ // Nếu nhấn phím W thì di chuyển lên
            counter++;
            if(counter<2){
                choice--;
            }
            else{
                if(counter>19){
                    if(counter%20==0)
                    choice--;
                }
            }
            if(choice>5){
                choice = 5;
            }
            if(choice<1){
                choice = 1;
            }
        }
        else if(keyHandler.getkeypresses()[KeyEvent.VK_ENTER]){ 
            keyHandler.getkeypresses()[KeyEvent.VK_ENTER] = false;
            if(counter<2){
                if(this.choice==1){ // Nếu chọn 1 (New game) thì mở trạng thái MapSelection
                    this.getGamepanel().getGamestatemanager().popState();   // Xóa trạng thái trước đó
                    this.getGamepanel().getGameDataStore().setMapunlock(1);
                    this.getGamepanel().getSaveandload().save();    // Lưu trạng thái
                    this.getGamepanel().getGamestatemanager().addState( new MapSelection(this.getGamepanel())); // Mở trạng thái MapSelection
                }
                if(this.choice==2){ // Nếu chọn 2 (Continue) thì mở trạng thái MapSelection
                    this.getGamepanel().getGamestatemanager().popState();   // Xóa trạng thái trước đó
                    this.getGamepanel().getGamestatemanager().addState( new MapSelection(this.getGamepanel())); // Mở trạng thái MapSelection
                }
                if(this.choice==3){ // Nếu chọn 3 (Event Mode) thì mở trạng thái EventMode
                    this.getGamepanel().getGamestatemanager().popState();   // Xóa trạng thái trước đó
                    this.getGamepanel().getGamestatemanager().addState( new MapSelectionEvent(this.getGamepanel()));
                }
                if(this.choice==4){ // Nếu chọn 3 (Guide) thì mở trạng thái Guide
                    this.getGamepanel().getGamestatemanager().popState();   // Xóa trạng thái trước đó
                    this.getGamepanel().getGamestatemanager().addState( new GuideState(this.getGamepanel())); // Mở trạng thái Guide
                }
                if(this.choice==5){ // Nếu chọn 3 (Exit) thì thoát game
                    this.getGamepanel().setGamethread(null);
                    System.exit(1);
                }
            }
            else{
                if(counter>19){
                    if(counter%20==0)
                    if(this.choice==1){
                        this.getGamepanel().getGamestatemanager().popState();
                        this.getGamepanel().getGameDataStore().setMapunlock(1);
                        this.getGamepanel().getSaveandload().save();
                        this.getGamepanel().getGamestatemanager().addState( new MapSelection(this.getGamepanel()));
                    }
                    if(this.choice==2){
                        this.getGamepanel().getGamestatemanager().popState();
                        this.getGamepanel().getGamestatemanager().addState( new MapSelection(this.getGamepanel()));
                    }
                    if(this.choice==3){
                        this.getGamepanel().getGamestatemanager().popState();
                        this.getGamepanel().getGamestatemanager().addState( new MapSelectionEvent(this.getGamepanel()));
                    }
                    if(this.choice==4){
                        this.getGamepanel().getGamestatemanager().popState();
                        this.getGamepanel().getGamestatemanager().addState( new GuideState(this.getGamepanel()));
                    }
                    if(this.choice==5){
                        this.getGamepanel().setGamethread(null);
                        System.exit(1);
                    }
                }
            }
            
        }
        else{
            counter = 0;
        }
    }

    

    // Vẽ menu
    @Override
    public void render(Graphics2D g) {
        g.drawImage(this.background,0,0,1344,768,null);
        g.drawImage(this.playerFace, 608, 0,null);
        g.setFont(GameStateManager.font_bong);
        g.setColor(new Color(40,177,98,255));
        g.setFont(g.getFont().deriveFont(80F));
        g.drawString("Sokoban",672-getStringLenth(g, "Sokoban")/2,200);
        g.setColor(new Color(105,76,57,255));
        g.setFont(g.getFont().deriveFont(40F));
        g.drawString("New Game",672-getStringLenth(g, "New Game")/2,300);
        g.drawString("Continue",672-getStringLenth(g, "Continue")/2,400);
        g.drawString("Event Mode",672-getStringLenth(g, "Event Mode")/2,500);
        g.drawString("Guide",672-getStringLenth(g, "Guide")/2,600);
        g.drawString("Exit",672-getStringLenth(g, "Exit")/2,700);
        g.setColor(new Color(192,160,124,255));
        g.drawString("=>",400,(choice+2)*100);
    }
    public int getStringLenth(Graphics2D g, String input){
        int txtLength = (int) g.getFontMetrics().getStringBounds(input, g).getWidth();
        return txtLength;
    }
    @Override
    public void update() {
    }
    
}
