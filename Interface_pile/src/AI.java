public class AI {

    /*
    public static int monteCarlo(int equipe_joueur,Piece[][] echiquier){
        int s = 0;
        int nombreParties=1000;
        for(int i = 0; i < nombreParties; i++){
            s+=partieAleatoire(equipe_joueur,echiquier);
        }
        return s;
    }

    static int partieAleatoire(int equipe_joueur,Piece[][] echiquier){
        int nombreCoûts = 1000;
        int chess_mate = 0;
        int i = 0;
        while(i < nombreCoûts && chess_mate == 0 ){

        }
        return 1;
    }
    */

    public static int fonctionDevalBete(Piece[][] echiquier){
        int s=0;
        for(int i=0; i<8; i++){
            for(int j=0; j<8; j++){
                switch(echiquier[i][j].type){
                    case("pion") -> s+=1*ponderation
                }
            }
        }
    }

    public static int chessMate(Piece[][] echiquier){//retourn 1 si le roi du joueur 1 est en échec, -1 si joueur -1, 0 sinon
        boolean chess_mate = false;
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if(echiquier[i][j].type == "roi"){
                    int equipe=echiquier[i][j].equipe;

                    int k = 1;
                    while (i-k >= 0 && echiquier[i-k][j].equipe == 0) {
                        k ++;
                    }
                    if(i-k >= 0 && echiquier[i-k][j].equipe != equipe && ("reine".equals(echiquier[i-k][j]) || "tour".equals(echiquier[i-k][j]))){
                        return equipe;
                    }

                    k = 1;
                    while (i+k < 8 && echiquier[i-k][j].equipe == 0) {
                        k ++;
                    }
                    if(i+k < 8 && echiquier[i+k][j].equipe != equipe && ("reine".equals(echiquier[i+k][j]) || "tour".equals(echiquier[i+k][j]))){
                        return equipe;
                    }

                    k = 1;
                    while (j+k < 8 && echiquier[i][j+k].equipe == 0) {
                        k ++;
                    }
                    if(j+k < 8 && echiquier[i][j+k].equipe != equipe && ("reine".equals(echiquier[i][j+k]) || "tour".equals(echiquier[i][j+k]))){
                        return equipe;
                    }

                    k = 1;
                    while (j-k >= 0 && echiquier[i][j-k].equipe == 0) {
                        k ++;
                    }
                    if(j-k >= 0 && echiquier[i][j-k].equipe != equipe && ("reine".equals(echiquier[i][j-k]) || "tour".equals(echiquier[i][j-k]))){
                        return equipe;
                    }


                    k = 1;
                    while (j-k >= 0 && i-k >= 0 && echiquier[i-k][j-k].equipe == 0) {
                        k ++;
                    }
                    if(j-k >= 0 && i-k >= 0 && echiquier[i-k][j-k].equipe != equipe && ("reine".equals(echiquier[i-k][j-k]) || "tour".equals(echiquier[i-k][j-k]))){
                        return equipe;
                    }

                    k = 1;
                    while (j-k >= 0 && i+k < 8 && echiquier[i+k][j-k].equipe == 0) {
                        k ++;
                    }
                    if(j-k >= 0 && i+k < 8 && echiquier[i+k][j-k].equipe != equipe && ("reine".equals(echiquier[i+k][j-k]) || "tour".equals(echiquier[i+k][j-k]))){
                        return equipe;
                    }

                    k = 1;
                    while (j+k < 8 && i+k < 8 && echiquier[i+k][j+k].equipe == 0) {
                        k ++;
                    }
                    if(j+k < 8 && i+k < 8 && echiquier[i+k][j+k].equipe != equipe && ("reine".equals(echiquier[i+k][j+k]) || "tour".equals(echiquier[i+k][j+k]))){
                        return equipe;
                    }

                    k = 1;
                    while (j+k < 8 && i-k >= 0 && echiquier[i-k][j+k].equipe == 0) {
                        k ++;
                    }
                    if(j+k < 8 && i-k >= 0 && echiquier[i-k][j+k].equipe != equipe && ("reine".equals(echiquier[i-k][j+k]) || "tour".equals(echiquier[i-k][j+k]))){
                        return equipe;
                    }

                }
            }
        }
        return 0;
    }
}
