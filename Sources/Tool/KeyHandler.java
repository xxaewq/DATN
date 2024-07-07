package Sources.Tool;
import java.awt.event.*;


// Định nghĩa lớp KeyHandler : xử lý sự kiện phím

public class KeyHandler implements KeyListener {
    private boolean [] key;
    public KeyHandler(){
        this.key = new boolean[100];
        for(int i = 0; i < 100;i++){
            key[i] = false;
        }
    }

    //hàm xử lý sự kiện khi phím được nhấn
    @Override
    public void keyPressed(KeyEvent e) {
        int whatkey = e.getKeyCode();
        if(whatkey == KeyEvent.VK_W){
            key[ (int) ('W')] = true;
        }
        if(whatkey == KeyEvent.VK_S){
            key[ (int) ('S')] = true;
        }
        if(whatkey == KeyEvent.VK_D){
            key[ (int) ('D')] = true;
        }

        if(whatkey == KeyEvent.VK_A){
            key[ (int) ('A')] = true;
        }
        if(whatkey == KeyEvent.VK_ESCAPE){
            key[KeyEvent.VK_ESCAPE]=true;
        }
        if(whatkey == KeyEvent.VK_ENTER){
            key[KeyEvent.VK_ENTER] = true;
        }
        if(whatkey == KeyEvent.VK_T){
            key[KeyEvent.VK_T] = true;
        }
        if(whatkey == KeyEvent.VK_Y){
            key[KeyEvent.VK_Y] = true;
        }
    }


    //hàm xử lý sự kiện khi phím được nhả ra
    @Override
    public void keyReleased(KeyEvent e) {
        int whatkey = e.getKeyCode();
        if(whatkey == KeyEvent.VK_W){
            key[ (int) ('W')] = false;
        }
        if(whatkey == KeyEvent.VK_S){
            key[ (int) ('S')] = false;
        }
        if(whatkey == KeyEvent.VK_D){
            key[ (int) ('D')] = false;
        }

        if(whatkey == KeyEvent.VK_A){
            key[ (int) ('A')] = false;
        }
        if(whatkey == KeyEvent.VK_ESCAPE){
            key[KeyEvent.VK_ESCAPE] = false;
        }
        if(whatkey == KeyEvent.VK_ENTER){
            key[KeyEvent.VK_ENTER] = false;
        }
        if(whatkey == KeyEvent.VK_T){
            key[KeyEvent.VK_T] = false;
        }
        if(whatkey == KeyEvent.VK_Y){
            key[KeyEvent.VK_Y] = false;
        }
    }
    public boolean[] getkeypresses(){
        return this.key;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
    
}
