


import java.awt.*;
import java.awt.image.BufferStrategy;

public class EchessL extends Canvas implements Runnable{

    public static final int WIDTH = 700, HEIGHT = 700;
    private Thread thread;
    private boolean running = false;
    public static int pieceSelectLigne = -1;
    public static int pieceSelectColonne = -1;
    public static int dimRect = WIDTH/8-1;

    piece[][] echiquier = initialisationEchiquier();

    public EchessL(){
        addMouseListener(new Evenement());
        new Window(WIDTH,HEIGHT,"Let's go",this);
    }

    public synchronized void start(){
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop(){
        try{
            thread.join();
            running = false;
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }


    public void run(){
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000/amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while(running){
            long now = System.nanoTime();
            delta += (now-lastTime)/ns;
            lastTime = now;
            while(delta >= 1){
                tick();
                delta --;

            }
            if(running){
                render();
            }
            frames ++;

            if(System.currentTimeMillis()-timer > 1000){
                timer += 1000;
                //System.out.println("FPS : "+frames);
                frames = 0;
            }
        }
        stop();
    }

    private void tick(){

    }

    private void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
//        g.setColor(new Color(100,75,250));
//        g.fillRect(0,0,WIDTH,HEIGHT);



        for(int i = 0; i < 8 ; i ++){
            for(int j = 0 ; j < 8 ; j ++){
                if( (j*7+i)%2 == 0 ){
                    g.setColor(Color.black);
                }else{
                    g.setColor(Color.white);
                }
                if(pieceSelectLigne == j && pieceSelectColonne == i){
                    g.setColor(Color.green);
                }
                g.fillRect(i*dimRect,j*dimRect,dimRect,dimRect);

            }
        }
        g.setColor(new Color(100,75,250));
        Font fonte = new Font(" TimesRoman ",Font.BOLD,30);
        g.setFont(fonte);
        for(int i = 0; i < 8 ; i ++){
            for(int j = 0; j < 8 ; j ++){
                String aff = "";
                switch (echiquier[i][j].type) {
                    case (0) -> aff += '·';
                    case (1) -> aff += 'P';
                    case (2) -> aff += 'T';
                    case (3) -> aff += 'C';
                    case (4) -> aff += 'F';
                    case (5) -> aff += 'Q';
                    case (6) -> aff += 'K';
                }
                if(echiquier[i][j].type == 0){
                    aff += " ";
                }else{
                    if(echiquier[i][j].equipe > 0){
                        aff += "+";
                    }else{
                        aff += "-";
                    }
                }
                g.drawString(aff,j*dimRect+dimRect/2,i*dimRect+dimRect/2);
            }
        }

        g.dispose();
        bs.show();
    }

    public static void main(String[] args){

        new EchessL();
    }

    static piece [][]initialisationEchiquier(){
        piece[][] echiquier = new piece[8][8];
        for(int i = 0; i < 8 ; i ++){
            for(int j = 0; j < 8 ; j ++){
                echiquier[i][j] = new piece(0,0,0);
            }
        }
        for(int i = 0; i < 8 ; i ++){
            echiquier[1][i] = new piece(1,10,1);
            echiquier[6][i] = new piece(1,10,-1);
        }
        for(int i = 0; i < 3 ; i ++){
            echiquier[0][i] = new piece(2+i,10*(2+i),1);
            echiquier[0][7-i] = new piece(2+i,10*(2+i),1);
            echiquier[7][i] = new piece(2+i,10*(2+i),-1);
            echiquier[7][7-i] = new piece(2+i,10*(2+i),-1);
        }
        echiquier[0][3] = new piece(5,100,1);
        echiquier[0][4] = new piece(6,1000,1);
        echiquier[7][3] = new piece(6,1000,-1);
        echiquier[7][4] = new piece(5,100,-1);

        return echiquier;
    }

}