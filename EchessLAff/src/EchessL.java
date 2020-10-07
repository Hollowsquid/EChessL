import java.awt.*;
import java.awt.image.BufferStrategy;

public class EchessL extends Canvas implements Runnable{

    public static final int WIDTH = 700, HEIGHT = 720;//Dimensions de la fenetre
    private Thread thread;
    private boolean running = false;
    public static int pieceSelectLigne = -1;//Case selectionnée, si en dehors de l'echiquier : aucune case selectionnée
    public static int pieceSelectColonne = -1;
    public static int dimRect = WIDTH/8-1;//Dimension des cases de l'echiquier
    static int tourActuel = 1;//1 -> tour de l'equipe du haut | -1 -> tour de l'equipe du bas
    static boolean deplacementAccepter = true;//Lorsqu'on déplace une pièce, vrai si la position final est valide

    static Piece[][] echiquier = initialisationEchiquier();//echiquier du tour en cours

    // Initialisation de la classe (peu important)
    public EchessL(){
        addMouseListener(new Evenement());
        new Window(WIDTH,HEIGHT,"Let's go",this);
    }

    //Demarrage du jeu (peu important)
    public synchronized void start(){
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    //Fermeture du jeu (peu important)
    public synchronized void stop(){
        try{
            thread.join();
            running = false;
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    // (peu important)
    public void run(){
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000/amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        //int frames = 0;

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
            //frames ++;

            if(System.currentTimeMillis()-timer > 1000){
                timer += 1000;
                //System.out.println("FPS : "+frames);
                //frames = 0;
            }
        }
        stop();
    }

    //vide
    private void tick(){

    }

    //Fonction principale
    public void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
//        g.setColor(new Color(100,75,250));
//        g.fillRect(0,0,WIDTH,HEIGHT);

        //Affiche les cases du damier et la case selectionnée
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

        //Affiche les cases accessible par la case selectionnée
        if (pieceSelectColonne >= 0 && pieceSelectLigne >= 0 && echiquier[pieceSelectLigne][pieceSelectColonne].equipe == tourActuel){
            int[][] CasesAccessible = echiquier[pieceSelectLigne][pieceSelectColonne].deplacementPossible(pieceSelectLigne,pieceSelectColonne,echiquier);
            g.setColor(Color.red);
            for (int[] ints : CasesAccessible) {
                g.fillRect(ints[1] * dimRect, ints[0] * dimRect, dimRect, dimRect);
            }
        }

        //Affiche les pièces
        g.setColor(new Color(100,75,250));
        Font fonte = new Font(" TimesRoman ",Font.BOLD,30);
        g.setFont(fonte);
        for(int i = 0; i < 8 ; i ++){
            for(int j = 0; j < 8 ; j ++){
                String aff = "";
                switch (echiquier[i][j].type) {
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

    //Deplace la pièce de la case "Dep" (départ) à la case "Arr" (arrivé)
    static Piece[][]deplacer(int ligneDep,int colonneDep,int ligneArr,int colonneArr,Piece[][] echiquierDepl){
        int [][] depPos = echiquierDepl[ligneDep][colonneDep].deplacementPossible(ligneDep,colonneDep,echiquierDepl); // tableau des deplacements possible
        boolean depPossible = false;//est-ce que le deplacement proposé est possible
        for (int[] depPo : depPos) {//on test tout les deplacement possible et on regarde si l'un correspond au deplacement proposé
            //on peut remplacer ça par une boucle while
            if (depPo[0] == ligneArr && depPo[1] == colonneArr) {
                depPossible = true;
                break;
            }
        }
        if(depPossible){//Changement de l'echiquier
            Piece chgm = echiquierDepl[ligneDep][colonneDep];
            echiquierDepl[ligneDep][colonneDep] = new Piece(0,0,0);
            echiquierDepl[ligneArr][colonneArr].mort();
            echiquierDepl[ligneArr][colonneArr] = chgm;
        }else{
            System.out.println("Cette position n'est pas possible");
            deplacementAccepter = false;
        }
        return echiquierDepl;
    }

    public static void main(String[] args){
        new EchessL();
    }

    //Permet d'initialiser un echiquier avec les pièces de base
    static Piece [][]initialisationEchiquier(){
        echiquier = new Piece[8][8];
        for(int i = 0; i < 8 ; i ++){
            for(int j = 0; j < 8 ; j ++){
                echiquier[i][j] = new Piece(0,0,0);
            }
        }
        for(int i = 0; i < 8 ; i ++){
            echiquier[1][i] = new Piece(1,10,1);
            echiquier[6][i] = new Piece(1,10,-1);
        }
        for(int i = 0; i < 3 ; i ++){
            echiquier[0][i] = new Piece(2+i,10*(2+i),1);
            echiquier[0][7-i] = new Piece(2+i,10*(2+i),1);
            echiquier[7][i] = new Piece(2+i,10*(2+i),-1);
            echiquier[7][7-i] = new Piece(2+i,10*(2+i),-1);
        }
        echiquier[0][3] = new Piece(5,100,1);
        echiquier[0][4] = new Piece(6,1000,1);
        echiquier[7][3] = new Piece(6,1000,-1);
        echiquier[7][4] = new Piece(5,100,-1);

        return echiquier;
    }

    //Effectue les actions lorsqu'on fait clic droite (deplacement de pièce / verification de victoire ...)
    public static void clicDroit(int mouseX,int mouseY){
        if(pieceSelectColonne >= 0 && pieceSelectLigne >= 0 && echiquier[pieceSelectLigne][pieceSelectColonne].equipe == tourActuel){
            deplacementAccepter = true;
            Piece pieceManger = echiquier[mouseY/dimRect][mouseX/dimRect];
            Piece pieceDeplacer = echiquier[pieceSelectLigne][pieceSelectColonne];
            deplacer(pieceSelectLigne, pieceSelectColonne,mouseY/dimRect,mouseX/dimRect,echiquier);
            if(deplacementAccepter){
                //deplacementFait(pieceManger,pieceDeplacer);
                tourActuel *= -1;
                if(pieceManger.type == 6){
                    System.out.println("Victoire de l'équipe "+pieceManger.equipe+" !");
                    initialisationEchiquier();
                }
                if(pieceDeplacer.type == 1){
                    if(mouseY/dimRect == 0 && pieceDeplacer.equipe == -1){
                        echiquier[0][mouseX/dimRect] = new Piece(5,100,-1);
                    }else if(mouseY/dimRect == 7 && pieceDeplacer.equipe == 1){
                        echiquier[7][mouseX/dimRect] = new Piece(5,100,1);
                    }
                }

            }
            pieceSelectColonne = -1;
            pieceSelectLigne = -1;
        }
    }


}
