package Sources.GameState;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import Sources.GamePanel;
import Sources.Tool.KeyHandler;

public class GuideState extends GameState {
    private BufferedImage background;
    private BufferedImage guide;
    private boolean isBackgroundMusicEnabled;
    private int counter = 0;
    private boolean couterToggle = false;

    public GuideState(GamePanel gamepanel) {
        super(gamepanel);
        try {
            background = ImageIO.read(getClass().getResourceAsStream("/Image/background.png"));
            guide = ImageIO.read(getClass().getResourceAsStream("/Image/guide.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        isBackgroundMusicEnabled = true;
        
    }

    @Override
    public void input(KeyHandler keyHandler) {
        if (keyHandler.getkeypresses()[KeyEvent.VK_ESCAPE]) {
            counter++;
            if (counter < 2) {
                this.getGamepanel().getGamestatemanager().popState();
                if(couterToggle == true){
                    this.getGamepanel().getGamestatemanager().popState();
                    couterToggle = false;
                }
                this.getGamepanel().getGamestatemanager().addState(new GameMenu(this.getGamepanel()));
                
                
            } else {
                if (counter > 9) {
                    if (counter % 10 == 0)
                        this.getGamepanel().getGamestatemanager().popState();
                        if (couterToggle == true) {
                            this.getGamepanel().getGamestatemanager().popState();
                            couterToggle = false;
                        }
                    this.getGamepanel().getGamestatemanager().addState(new GameMenu(this.getGamepanel()));
                    
                    
                }
            }
        } else if (keyHandler.getkeypresses()[KeyEvent.VK_T]) {
            counter++;
            if (counter < 2) {
                if (isBackgroundMusicEnabled == true) {
                    this.getGamepanel().stopSoundEffect();
                    isBackgroundMusicEnabled = false;
                } else {
                    this.getGamepanel().playSoundEffect(0);
                    isBackgroundMusicEnabled = true;
                }
            } else {
                if (counter > 19) {
                    if (counter % 20 == 0) {
                        if (isBackgroundMusicEnabled == true) {
                            this.getGamepanel().stopSoundEffect();
                            isBackgroundMusicEnabled = false;
                            couterToggle = true;
                        } else {
                            this.getGamepanel().playSoundEffect(0);
                            isBackgroundMusicEnabled = true;
                            couterToggle = true;
                        }

                    }
                }
            }
        } 


    }


    public boolean isBackgroundMusicEnabled() {
        return isBackgroundMusicEnabled;
    }
    public void setBackgroundMusicEnabled(boolean isBackgroundMusicEnabled) {
        this.isBackgroundMusicEnabled = isBackgroundMusicEnabled;
    }

    @Override
    public void render(Graphics2D g) {
        g.drawImage(this.background, 0, 0, 1344, 768, null);
        g.setFont(GameStateManager.font_bong);
        g.setFont(g.getFont().deriveFont(30F));
        g.drawString("--How to Play--", 100, 100);
        g.setFont(g.getFont().deriveFont(20F));
        g.drawString("You are the warehouse keeper. Clean up the warehouse by ", 100, 150);
        g.drawString("controlling your character to push the boxes into the correct position.", 100, 200);
        g.drawString("Use W A S D to move your character or adjust selections.", 100, 250);
        g.drawString("Use Enter to select.", 100, 300);
        g.drawString("Use ESC to return.", 100, 350);
        g.setFont(g.getFont().deriveFont(30F));
        g.drawString("--Music--", 100, 400);
        g.setFont(g.getFont().deriveFont(20F));
        g.drawString("Press T to toggle background music.", 100, 450);
        if (isBackgroundMusicEnabled == true) {
            g.drawString("Background Music :", 100, 500);
            g.drawString("Enabled", 400, 500);
        } else {
            g.drawString("Background Music :", 100, 500);
            g.drawString("Disabled", 400, 500);
        }
        g.drawImage(this.guide, 800, 400, 450, 250, null);
    }

    @Override
    public void update() {
    }

}
