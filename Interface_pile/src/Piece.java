public class Piece {

    private int type; //0 : case vide | 1 : pion | 2 : tour | 3 : cavalier | 4 : fou | 5 : reine | 6 : roi
    private int equipe;// equipe de la pièce | haut : 1 | bas : -1 | case vide : 0
    private boolean castle=false;

    public int getEquipe(){
        return equipe;
    }

    public int getType(){
        return type;
    }

    public boolean getCastle(){
        return castle;
    }

    public void giveCastle(){
        castle=false;
    }
/*
    public void changePiece(int type,int equipe){
        this.equipe=equipe;
        this.type=type;
    }

 */

    Piece(int type1, int equipe1) {
        type = type1;
        equipe = equipe1;
        if(type==2){
            castle=true;
        }
    }
}
