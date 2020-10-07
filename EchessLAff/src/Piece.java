public class Piece{
        int type; //1 : pion | 2 : tour | 3 : cavalier | 4 : fou | 5 : reine | 6 : roi
        int valeur;// valeur de la pièce | pion : | tour : ...
        int equipe;// equipe de la pièce | haut : 1 | bas : -1 | case vide : 0

        Piece(int typei,int valeuri,int equipei){
            type = typei;
            valeur = valeuri;
            equipe = equipei;
        }

        int[][] deplacementPossible(int ligne, int colonne, Piece[][] echiquier){
            int [][] depPosTot = new int[8*8][2];
            int nPos = 0;

            switch(type){

                case(1)://pion

                    if( (ligne+equipe >= 0 && ligne+equipe < 8 && echiquier[ligne+equipe][colonne].equipe == 0) ){//avance
                        depPosTot[nPos][0] = ligne+equipe;
                        depPosTot[nPos][1] = colonne;
                        nPos ++;
                    }
                    if(ligne == (1-equipe)*3+(equipe+1)/2 && echiquier[ligne+2*equipe][colonne].equipe == 0){//double avance
                        depPosTot[nPos][0] = ligne+2*equipe;
                        depPosTot[nPos][1] = colonne;
                        nPos ++;
                    }
                    if( ligne+equipe >= 0 && ligne+equipe < 8 && colonne+1 >= 0 && colonne+1 < 8 && echiquier[ligne+equipe][colonne+1].equipe != equipe && echiquier[ligne+equipe][colonne+1].type != 0){
                        depPosTot[nPos][0] = ligne+equipe;
                        depPosTot[nPos][1] = colonne+1;
                        nPos ++;
                    }
                    if( ligne+equipe >= 0 && ligne+equipe < 8 && colonne-1 >= 0 && colonne-1 < 8 && echiquier[ligne+equipe][colonne-1].equipe != equipe && echiquier[ligne+equipe][colonne-1].type != 0){
                        depPosTot[nPos][0] = ligne+equipe;
                        depPosTot[nPos][1] = colonne-1;
                        nPos ++;
                    }
                break;

                case(2)://tour
                    int k = 1;
                    boolean toucher = false;
                    while (ligne-k >= 0 && echiquier[ligne-k][colonne].equipe != equipe && !toucher) {//haut
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
                    while (ligne+k < 8 && echiquier[ligne+k][colonne].equipe != equipe && !toucher) {//bas
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
                    while (colonne-k >= 0 && echiquier[ligne][colonne-k].equipe != equipe && !toucher) {//gauche
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
                    while (colonne+k < 8  && echiquier[ligne][colonne+k].equipe != equipe && !toucher) {//gauche
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
                                if(echiquier[i][j].equipe != equipe){
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
                    while (ligne-k >= 0 && colonne-k >= 0 && echiquier[ligne-k][colonne-k].equipe != equipe && !toucher) {//haut gauche
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
                    while (ligne-k >= 0 && colonne+k < 8 && echiquier[ligne-k][colonne+k].equipe != equipe && !toucher) {//haut droite
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
                    while (ligne+k < 8 && colonne-k >= 0 && echiquier[ligne+k][colonne-k].equipe != equipe && !toucher) {//bas gauche
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
                    while (ligne+k < 8 && colonne+k < 8  && echiquier[ligne+k][colonne+k].equipe != equipe && !toucher) {//bas droite
                        depPosTot[nPos][0] = ligne+k;
                        depPosTot[nPos][1] = colonne+k;
                        if(echiquier[ligne+k][colonne+k].equipe != equipe && echiquier[ligne+k][colonne+k].equipe != 0){
                            toucher = true;
                        }
                        nPos ++;
                        k ++;
                    }

                break;

                case(5): //reine
                    k = 1;
                    toucher = false;
                    while (ligne-k >= 0 && echiquier[ligne-k][colonne].equipe != equipe && !toucher) {//haut
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
                    while (ligne+k < 8 && echiquier[ligne+k][colonne].equipe != equipe && !toucher) {//bas
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
                    while (colonne-k >= 0 && echiquier[ligne][colonne-k].equipe != equipe && !toucher) {//gauche
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
                    while (colonne+k < 8  && echiquier[ligne][colonne+k].equipe != equipe && !toucher) {//gauche
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
                    while (ligne-k >= 0 && colonne-k >= 0 && echiquier[ligne-k][colonne-k].equipe != equipe && !toucher) {//haut gauche
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
                    while (ligne-k >= 0 && colonne+k < 8 && echiquier[ligne-k][colonne+k].equipe != equipe && !toucher) {//haut droite
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
                    while (ligne+k < 8 && colonne-k >= 0 && echiquier[ligne+k][colonne-k].equipe != equipe && !toucher) {//bas gauche
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
                    while (ligne+k < 8 && colonne+k < 8  && echiquier[ligne+k][colonne+k].equipe != equipe && !toucher) {//bas droite
                        depPosTot[nPos][0] = ligne+k;
                        depPosTot[nPos][1] = colonne+k;
                        if(echiquier[ligne+k][colonne+k].equipe != equipe && echiquier[ligne+k][colonne+k].equipe != 0){
                            toucher = true;
                        }
                        nPos ++;
                        k ++;
                    }

                break;

                case(6): // roi
                    int[][] cases = {{1,1},{0,1},{-1,1},{-1,0},{-1,-1},{0,-1},{1,-1},{1,0}};
                    for(int i = 0; i < 8 ; i ++){
                        if(ligne+cases[i][0] >= 0 && ligne+cases[i][0] < 8 && colonne+cases[i][1] >= 0 && colonne+cases[i][1] < 8 ){
                            if(echiquier[ligne+cases[i][0]][colonne+cases[i][1]].equipe != equipe){
                                depPosTot[nPos][0] = ligne+cases[i][0];
                                depPosTot[nPos][1] = colonne+cases[i][1];
                                nPos ++;
                            }
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
