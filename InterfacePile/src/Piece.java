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

    public void setCastle(boolean castle){this.castle = castle;}
/*
    public void changePiece(int type,int equipe){
        this.equipe=equipe;
        this.type=type;
    }

 */

    Piece(int type, int equipe) {
        if(type>=0 && type<7 && equipe<=1 && equipe>=-1) {
            this.type = type;
            this.equipe = equipe;
            if (type == 2 || type == 0) {
                castle = true;
            }
        } else
            System.out.println("ERROR_PieceInvalide");
    }
    Piece(){
        type=0;
        equipe=0;
    }
}

