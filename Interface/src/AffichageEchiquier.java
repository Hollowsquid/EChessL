import java.util.Arrays;
public class AffichageEchiquier {

    public static void main(String[] args) {
        piece echiquier[][] = initialisationEchiquier();
        //echiquier[2][4] = new piece(5,10,1);
        affichage(echiquier);
        //System.out.println( Arrays.deepToString(echiquier[2][4].deplacementPossible(2,4,echiquier)) );
        deplacer(1,2,3,2,echiquier);
        affichage(echiquier);
        deplacer(6,1,4,1,echiquier);
        affichage(echiquier);
        deplacer(1,3,2,3,echiquier);
        affichage(echiquier);
        deplacer(4,1,3,2,echiquier);
        affichage(echiquier);
        //System.out.println( Arrays.deepToString(echiquier[2][3].deplacementPossible(2,3,echiquier)) );
        deplacer(2,3,3,2,echiquier);
        affichage(echiquier);
    }

    public static void affichage(piece[][] echiquier){
        System.out.println("Echiquier : ");
        for(int i = 0; i < 8 ; i ++){
            for(int j = 0; j < 8 ; j ++){
                switch(echiquier[i][j].type){
                    case(0):
                        System.out.print('·');
                        break;
                    case(1):
                        System.out.print('P');
                        break;
                    case(2):
                        System.out.print('T');
                        break;
                    case(3):
                        System.out.print('C');
                        break;
                    case(4):
                        System.out.print('F');
                        break;
                    case(5):
                        System.out.print('Q');
                        break;
                    case(6):
                        System.out.print('K');
                        break;
                }
                if(echiquier[i][j].type == 0){
                    System.out.print(" ");
                }else{
                    if(echiquier[i][j].equipe > 0){
                        System.out.print("+");
                    }else{
                        System.out.print("-");
                    }
                }
                System.out.print(" ");
            }
            System.out.println("");
        }
    }

    static piece [][]initialisationEchiquier(){
        piece echiquier[][] = new piece[8][8];
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

    static class piece{
        int type; int valeur;int equipe;

        piece(int typei,int valeuri,int equipei){
            type = typei;
            valeur = valeuri;
            equipe = equipei;
        }

        int[][] deplacementPossible(int ligne, int colonne, piece[][] echiquier){
            int [][] depPosTot = new int[8*8][2];
            int nPos = 0;

            switch(type){

                case(1)://pion
                    if( (ligne+equipe > 0 && ligne+equipe < 8 && echiquier[ligne+equipe][colonne].equipe != equipe) ){//avance
                        depPosTot[nPos][0] = ligne+equipe;
                        depPosTot[nPos][1] = colonne;
                        nPos ++;
                    }
                    if(ligne == (1-equipe)*3+(equipe+1)/2 && echiquier[ligne+2*equipe][colonne].equipe != equipe){//double avance
                        depPosTot[nPos][0] = ligne+2*equipe;
                        depPosTot[nPos][1] = colonne;
                        nPos ++;
                    }
                    if( ligne+equipe > 0 && ligne+equipe < 8 && colonne+1 > 0 && colonne+1 < 8 && echiquier[ligne+equipe][colonne+1].equipe != equipe && echiquier[ligne+equipe][colonne+1].type != 0){
                        depPosTot[nPos][0] = ligne+equipe;
                        depPosTot[nPos][1] = colonne+1;
                        nPos ++;
                    }
                    if( ligne+equipe > 0 && ligne+equipe < 8 && colonne-1 > 0 && colonne-1 < 8 && echiquier[ligne+equipe][colonne-1].equipe != equipe && echiquier[ligne+equipe][colonne-1].type != 0){
                        depPosTot[nPos][0] = ligne+equipe;
                        depPosTot[nPos][1] = colonne-1;
                        nPos ++;
                    }
                break;

                case(2)://tour
                    int k = 1;
                    boolean toucher = false;
                    while (ligne-k >= 0 && echiquier[ligne-k][colonne].equipe != equipe && toucher == false) {//haut
                        depPosTot[nPos][0] = ligne-k;
                        depPosTot[nPos][1] = colonne;
                        if(echiquier[ligne-k][colonne].equipe != equipe && echiquier[ligne-k][colonne].equipe != 0){
                            toucher = true;
                        }
                        nPos ++;
                        k ++;
                    }

                    k = 1;
                    toucher = false;
                    while (ligne+k < 8 && echiquier[ligne+k][colonne].equipe != equipe && toucher == false) {//bas
                        depPosTot[nPos][0] = ligne+k;
                        depPosTot[nPos][1] = colonne;
                        if(echiquier[ligne+k][colonne].equipe != equipe && echiquier[ligne+k][colonne].equipe != 0){
                            toucher = true;
                        }
                        nPos ++;
                        k ++;
                    }

                    k = 1;
                    toucher = false;
                    while (colonne-k >= 0 && echiquier[ligne][colonne-k].equipe != equipe && toucher == false ) {//gauche
                        depPosTot[nPos][0] = ligne;
                        depPosTot[nPos][1] = colonne-k;
                        if(echiquier[ligne][colonne-k].equipe != equipe && echiquier[ligne][colonne-k].equipe != 0){
                            toucher = true;
                        }
                        nPos ++;
                        k ++;
                    }

                    k = 1;
                    toucher = false;
                    while (colonne+k < 8  && echiquier[ligne][colonne+k].equipe != equipe && toucher == false ) {//gauche
                        depPosTot[nPos][0] = ligne;
                        depPosTot[nPos][1] = colonne+k;
                        if(echiquier[ligne][colonne+k].equipe != equipe && echiquier[ligne][colonne+k].equipe != 0){
                            toucher = true;
                        }
                        nPos ++;
                        k ++;
                    }
                break;

                case(3)://cavalier
                    for(int i = 0; i < 8 ; i ++){
                        for(int j = 0; j  < 8 ; j ++){
                            if( !(i == ligne || j == colonne) && Math.abs(ligne-i)+Math.abs(j-colonne) == 3 ){
                                if(echiquier[i][j].type == 0 || echiquier[i][j].equipe != equipe){
                                    depPosTot[nPos][0] = i;
                                    depPosTot[nPos][1] = j;
                                    nPos ++;
                                }
                            }
                        }
                    }
                break;

                case(4)://fou
                    k = 1;
                    toucher = false;
                    while (ligne-k >= 0 && colonne-k > 0 && echiquier[ligne-k][colonne-k].equipe != equipe && toucher == false) {//haut gauche
                        depPosTot[nPos][0] = ligne-k;
                        depPosTot[nPos][1] = colonne-k;
                        if(echiquier[ligne-k][colonne-k].equipe != equipe && echiquier[ligne-k][colonne-k].equipe != 0){
                            toucher = true;
                        }
                        nPos ++;
                        k ++;
                    }

                    k = 1;
                    toucher = false;
                    while (ligne-k >= 0 && colonne+k < 8 && echiquier[ligne-k][colonne+k].equipe != equipe && toucher == false) {//haut droite
                        depPosTot[nPos][0] = ligne-k;
                        depPosTot[nPos][1] = colonne+k;
                        if(echiquier[ligne-k][colonne+k].equipe != equipe && echiquier[ligne-k][colonne+k].equipe != 0){
                            toucher = true;
                        }
                        nPos ++;
                        k ++;
                    }

                    k = 1;
                    toucher = false;
                    while (ligne+k < 8 && colonne-k >= 0 && echiquier[ligne+k][colonne-k].equipe != equipe && toucher == false ) {//bas gauche
                        depPosTot[nPos][0] = ligne+k;
                        depPosTot[nPos][1] = colonne-k;
                        if(echiquier[ligne+k][colonne-k].equipe != equipe && echiquier[ligne+k][colonne-k].equipe != 0){
                            toucher = true;
                        }
                        nPos ++;
                        k ++;
                    }

                    k = 1;
                    toucher = false;
                    while (ligne+k < 8 && colonne+k < 8  && echiquier[ligne+k][colonne+k].equipe != equipe && toucher == false ) {//bas droite
                        depPosTot[nPos][0] = ligne+k;
                        depPosTot[nPos][1] = colonne+k;
                        if(echiquier[ligne+k][colonne+k].equipe != equipe && echiquier[ligne+k][colonne+k].equipe != 0){
                            toucher = true;
                        }
                        nPos ++;
                        k ++;
                    }

                break;

                case(5):
                    k = 1;
                    toucher = false;
                    while (ligne-k >= 0 && echiquier[ligne-k][colonne].equipe != equipe && toucher == false) {//haut
                        depPosTot[nPos][0] = ligne-k;
                        depPosTot[nPos][1] = colonne;
                        if(echiquier[ligne-k][colonne].equipe != equipe && echiquier[ligne-k][colonne].equipe != 0){
                            toucher = true;
                        }
                        nPos ++;
                        k ++;
                    }

                    k = 1;
                    toucher = false;
                    while (ligne+k < 8 && echiquier[ligne+k][colonne].equipe != equipe && toucher == false) {//bas
                        depPosTot[nPos][0] = ligne+k;
                        depPosTot[nPos][1] = colonne;
                        if(echiquier[ligne+k][colonne].equipe != equipe && echiquier[ligne+k][colonne].equipe != 0){
                            toucher = true;
                        }
                        nPos ++;
                        k ++;
                    }

                    k = 1;
                    toucher = false;
                    while (colonne-k >= 0 && echiquier[ligne][colonne-k].equipe != equipe && toucher == false ) {//gauche
                        depPosTot[nPos][0] = ligne;
                        depPosTot[nPos][1] = colonne-k;
                        if(echiquier[ligne][colonne-k].equipe != equipe && echiquier[ligne][colonne-k].equipe != 0){
                            toucher = true;
                        }
                        nPos ++;
                        k ++;
                    }

                    k = 1;
                    toucher = false;
                    while (colonne+k < 8  && echiquier[ligne][colonne+k].equipe != equipe && toucher == false ) {//gauche
                        depPosTot[nPos][0] = ligne;
                        depPosTot[nPos][1] = colonne+k;
                        if(echiquier[ligne][colonne+k].equipe != equipe && echiquier[ligne][colonne+k].equipe != 0){
                            toucher = true;
                        }
                        nPos ++;
                        k ++;
                    }

                    k = 1;
                    toucher = false;
                    while (ligne-k >= 0 && colonne-k > 0 && echiquier[ligne-k][colonne-k].equipe != equipe && toucher == false) {//haut gauche
                        depPosTot[nPos][0] = ligne-k;
                        depPosTot[nPos][1] = colonne-k;
                        if(echiquier[ligne-k][colonne-k].equipe != equipe && echiquier[ligne-k][colonne-k].equipe != 0){
                            toucher = true;
                        }
                        nPos ++;
                        k ++;
                    }

                    k = 1;
                    toucher = false;
                    while (ligne-k >= 0 && colonne+k < 8 && echiquier[ligne-k][colonne+k].equipe != equipe && toucher == false) {//haut droite
                        depPosTot[nPos][0] = ligne-k;
                        depPosTot[nPos][1] = colonne+k;
                        if(echiquier[ligne-k][colonne+k].equipe != equipe && echiquier[ligne-k][colonne+k].equipe != 0){
                            toucher = true;
                        }
                        nPos ++;
                        k ++;
                    }

                    k = 1;
                    toucher = false;
                    while (ligne+k < 8 && colonne-k >= 0 && echiquier[ligne+k][colonne-k].equipe != equipe && toucher == false ) {//bas gauche
                        depPosTot[nPos][0] = ligne+k;
                        depPosTot[nPos][1] = colonne-k;
                        if(echiquier[ligne+k][colonne-k].equipe != equipe && echiquier[ligne+k][colonne-k].equipe != 0){
                            toucher = true;
                        }
                        nPos ++;
                        k ++;
                    }

                    k = 1;
                    toucher = false;
                    while (ligne+k < 8 && colonne+k < 8  && echiquier[ligne+k][colonne+k].equipe != equipe && toucher == false ) {//bas droite
                        depPosTot[nPos][0] = ligne+k;
                        depPosTot[nPos][1] = colonne+k;
                        if(echiquier[ligne+k][colonne+k].equipe != equipe && echiquier[ligne+k][colonne+k].equipe != 0){
                            toucher = true;
                        }
                        nPos ++;
                        k ++;
                    }

                break;

                case(6):
                    int cases[][] = {{1,1},{0,1},{-1,1},{-1,0},{-1,-1},{0,-1},{1,-1},{1,0}};
                    for(int i = 0; i < 8 ; i ++){
                        if(echiquier[ligne+cases[i][0]][colonne+cases[i][1]].equipe != equipe){
                            depPosTot[nPos][0] = ligne+cases[i][0];
                            depPosTot[nPos][1] = colonne+cases[i][1];
                            nPos ++;
                        }
                    }

                break;

            }

            int [][] depPosRep = new int[nPos][2];
            if(nPos > 0){
                for(int i = 0 ; i < nPos ; i ++){
                    depPosRep[i][0] = depPosTot[i][0];
                    depPosRep[i][1] = depPosTot[i][1];
                }
            }
            return depPosRep;
        }

        void mort(){
            if(type != 0){
                System.out.println("La pièce "+type+" de l'équipe "+equipe+" est morte !");
            }
        }
    }

    static piece[][]deplacer(int ligneDep,int colonneDep,int ligneArr,int colonneArr,piece[][] echiquier){
        int [][] depPos = echiquier[ligneDep][colonneDep].deplacementPossible(ligneDep,colonneDep,echiquier);
        boolean depPossible = false;
        for(int i = 0; i < depPos.length ; i ++){
            if(depPos[i][0] == ligneArr && depPos[i][1] == colonneArr){
                depPossible = true;
            }
        }
        if(depPossible){
            piece chgm = echiquier[ligneDep][colonneDep];
            echiquier[ligneDep][colonneDep] = new piece(0,0,0);
            echiquier[ligneArr][colonneArr].mort();
            echiquier[ligneArr][colonneArr] = chgm;
        }else{
            System.out.println("Cette position n'est pas possible");
        }
        return echiquier;
    }

}
