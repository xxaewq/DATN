package Sources.GameState;

import Sources.Tool.KeyHandler;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import Sources.GamePanel;
import Sources.Map.Map;
//import Sources.Map.MapManager;

import java.awt.event.*;
import java.awt.image.*;

import javax.imageio.ImageIO;

public class MapSelection extends GameState {

    private int unlockmap;
    private int counter;

    private int currentPage;
    private int selectedMapIndexInPage;
    private int mapsPerPage;
    private BufferedImage background;

    public MapSelection(GamePanel gamepanel) {
        super(gamepanel);
        // this.unlockmap = 18;
        this.unlockmap = this.getGamepanel().getGameDataStore().getMapunlock();
        this.counter = 0;
        this.currentPage = 1;
        this.selectedMapIndexInPage = 0;
        this.mapsPerPage = 9;

        try {
            this.background = ImageIO.read(getClass().getResourceAsStream("/Image/background.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < unlockmap; ++i) {
            this.getGamepanel().getMapManager().getVectormap().elementAt(i).setDone(true);
        }
        
        for (int i = unlockmap; i < this.getGamepanel().getMapManager().getVectormap().size(); ++i) {
            this.getGamepanel().getMapManager().getVectormap().elementAt(i).setDone(false);
        }
        
    }

    public int getMapunlock() {
        return unlockmap;
    }

    @Override
    public void input(KeyHandler keyHandler) {
        if (keyHandler.getkeypresses()[(int) 'S']) {
            counter++;
            if (counter < 2) {
                if (currentPage < (unlockmap / mapsPerPage) + (unlockmap % mapsPerPage > 0 ? 1 : 0)) {
                    currentPage++;
                    selectedMapIndexInPage = 0;
                }
            } else {
                if (counter > 9) {
                    if (counter % 10 == 0)
                        if (currentPage < (unlockmap / mapsPerPage) + (unlockmap % mapsPerPage > 0 ? 1 : 0)) {
                            currentPage++;
                            selectedMapIndexInPage = 0;
                        }
                }
            }
        } else if (keyHandler.getkeypresses()[(int) 'W']) {
            counter++;
            if (counter < 2) {
                if (currentPage > 1) {
                    currentPage--;
                    // selectedMapIndexInPage = Math.min(selectedMapIndexInPage, mapsPerPage - 1);
                    selectedMapIndexInPage = 0;
                }
            } else {
                if (counter > 9) {
                    if (counter % 10 == 0)
                        if (currentPage > 1) {
                            currentPage--;
                            // selectedMapIndexInPage = Math.min(selectedMapIndexInPage, mapsPerPage - 1);
                            selectedMapIndexInPage = 0;
                        }
                }
            }
        } else if (keyHandler.getkeypresses()[(int) 'D']) {
            counter++;
            if (counter < 2) {
                selectedMapIndexInPage++;
                selectedMapIndexInPage = selectedMapIndexInPage % mapsPerPage;
                selectedMapIndexInPage = Math.min(selectedMapIndexInPage, unlockmap - 1);
            } else {
                if (counter > 9) {
                    if (counter % 10 == 0) {
                        selectedMapIndexInPage = (selectedMapIndexInPage + 1) % mapsPerPage;
                        selectedMapIndexInPage = Math.min(selectedMapIndexInPage, unlockmap - 1);
                    }
                }
            }
        } else if (keyHandler.getkeypresses()[(int) 'A']) {
            counter++;
            if (counter < 2) {
                selectedMapIndexInPage = (selectedMapIndexInPage - 1 + mapsPerPage) % mapsPerPage;
                selectedMapIndexInPage = Math.min(selectedMapIndexInPage, unlockmap - 1);
            } else {
                if (counter > 9) {
                    if (counter % 10 == 0)
                        selectedMapIndexInPage = (selectedMapIndexInPage - 1 + mapsPerPage) % mapsPerPage;
                    selectedMapIndexInPage = Math.min(selectedMapIndexInPage, unlockmap - 1);
                }
            }
        } else if (keyHandler.getkeypresses()[KeyEvent.VK_ENTER]) {
            int selectedMap = calculateSelectedMapIndex();
            if (selectedMap <= unlockmap) {
                this.getGamepanel().getGamestatemanager().popState();
                this.getGamepanel().getMapManager().setCurrentMap(selectedMap);
                this.getGamepanel().getGamestatemanager().addState(new PlayState(this.getGamepanel(),
                        this.getGamepanel().getMapManager().getVectormap().elementAt(selectedMap - 1),false));
            }
        } else if (keyHandler.getkeypresses()[KeyEvent.VK_ESCAPE]) {
            counter++;
            if (counter < 2) {
                this.getGamepanel().getGamestatemanager().popState();
                this.getGamepanel().getGamestatemanager().addState(new GameMenu(this.getGamepanel()));

            } else {
                if (counter > 9) {
                    if (counter % 10 == 0) {
                        this.getGamepanel().getGamestatemanager().popState();
                        this.getGamepanel().getGamestatemanager().addState(new GameMenu(this.getGamepanel()));
                    }

                }
            }
        }
    }

    public int calculateSelectedMapIndex() {
        return (currentPage - 1) * mapsPerPage + selectedMapIndexInPage + 1;
    }

    public void drawMapPerPage(Graphics2D g) {
        int startIndex = (currentPage - 1) * mapsPerPage + 1;
        for (int i = 0; i < mapsPerPage; ++i) {
            int mapIndex = startIndex + i;
            Map input = this.getGamepanel().getMapManager().getVectormap().elementAt(mapIndex - 1);
            g.setColor(new Color(105, 76, 57, 255));
            g.setStroke(new BasicStroke(1));
            g.drawString("Map " + (input.getX() + input.getY() * 3 + 1), input.getX() * 411 + 220,
                    (input.getY() - 3 * (currentPage - 1)) * 230 + 255);
            g.drawRect(input.getX() * 411 + 111, (input.getY() - 3 * (currentPage - 1)) * 230 + 80, 300, 150);
            g.drawImage(input.getMinimap(), input.getX() * 411 + 111, (input.getY() - 3 * (currentPage - 1)) * 230 + 80,
                    300, 150, null);
            
            if (!input.isDone()) {
                g.setColor(Color.black);
                g.drawString("Locked", input.getX() * 411 + 261-getStringLenth(g,"Locked")/2, input.getY() * 230 + 160);
                g.setStroke(new BasicStroke(10));
            
            }
            
            g.setColor(new Color(105, 76, 57, 25));
            g.setStroke(new BasicStroke(10));
            g.drawRect(((selectedMapIndexInPage) % 3) * 411 + 111, ((selectedMapIndexInPage) / 3) * 230 + 80, 300, 150);
            g.fillRect(((selectedMapIndexInPage) % 3) * 411 + 111, ((selectedMapIndexInPage) / 3) * 230 + 80, 300, 150);
            
        }
    }
    
    public int getStringLenth(Graphics2D g, String input){
        int txtLength = (int) g.getFontMetrics().getStringBounds(input, g).getWidth();
        return txtLength;
    }
    

    @Override
    public void update() {
    }

    @Override
    public void render(Graphics2D g) {
        g.drawImage(this.background, 0, 0, 1344, 768, null);
        g.setFont(GameStateManager.font_bong);
        g.setColor(new Color(105, 76, 57, 255));
        g.setFont(g.getFont().deriveFont(20F));
        drawMapPerPage(g);
    }
}