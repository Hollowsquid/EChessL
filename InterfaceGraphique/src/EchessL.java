


import java.awt.*;
import java.awt.image.BufferStrategy;

public class EchessL extends Canvas implements Runnable{

    public static final int WIDTH = 700, HEIGHT = 700;
    private Thread thread;
    private boolean running = false;

    public EchessL(){
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
                System.out.println("FPS : "+frames);
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


        int dimRect = WIDTH/8-1;
        for(int i = 0; i < 8 ; i ++){
            for(int j = 0 ; j < 8 ; j ++){
                if( (j*7+i)%2 == 0 ){
                    g.setColor(Color.black);
                }else{
                    g.setColor(Color.white);
                }
                g.fillRect(i*dimRect,j*dimRect,dimRect,dimRect);

            }
        }
        System.out.println(dimRect);

        g.dispose();
        bs.show();
    }



    public static void main(String args[]){
        new EchessL();
    }
}
