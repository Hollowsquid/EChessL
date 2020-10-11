public class Echiquier {
    private Piece[][] tableau=new Piece[8][8];
    private PilePiece coutTot;

    Echiquier(){
        for(int i=2; i<6; i++){
            for(int j=0; j<8; j++){
                tableau[i][j] = new Piece();
            }
        }
        for(int i = 0; i < 8 ; i ++){
            tableau[1][i] = new Piece(1,1);
            tableau[6][i] = new Piece(1,-1);
        }
        for(int i = 0; i < 10; i+=7){
            int epsilon=(i==0) ? 1 : -1;
            tableau[i][0] = new Piece(2,epsilon);
            tableau[i][1] = new Piece(3,epsilon);
            tableau[i][2] = new Piece(4,epsilon);
            tableau[i][5] = new Piece(4,epsilon);
            tableau[i][6] = new Piece(3,epsilon);
            tableau[i][7] = new Piece(2,epsilon);
        }
        tableau[0][3] = new Piece(5,1);
        tableau[0][4] = new Piece(6,1);
        tableau[7][4] = new Piece(6,-1);
        tableau[7][3] = new Piece(5,-1);

    }
/*
    public int getEquipe(int ligne,int colonne){
        return tableau[ligne][colonne].getEquipe();
    }

    public int getType(int ligne,int colonne){
        return tableau[ligne][colonne].getType();
    }

 */
/*
    public void changeBack(){
        Piece chg=new Piece(coupAvant[0][1],coupAvant[0][0]);
        Piece chg2=new Piece(coupAvant[1][1],coupAvant[1][0]);
        int ligneDep=coupAvant[0][2],colonneDep=coupAvant[0][3],ligneArr=coupAvant[1][2],colonneArr=coupAvant[1][3];

    }

 */

    public void change(int ligneDep,int colonneDep,int ligneArr,int colonneArr) {
        Piece[] coupAvantPiece={tableau[ligneDep][colonneDep], tableau[ligneArr][colonneArr]};
        int[] coupAvantIndice={ligneDep,colonneDep,ligneArr,colonneArr};
        if((ligneArr==0 || ligneArr==7) && colonneArr==2 && coupAvantPiece[0].getType()==6){
            tableau[ligneArr][3] = tableau[ligneArr][0];
            tableau[ligneArr][2] = tableau[ligneArr][4];
            tableau[ligneArr][0] = new Piece();
            tableau[ligneArr][4] = new Piece();
            tableau[ligneArr][3].setCastle(false);
            tableau[ligneArr][2].setCastle(false);
        } else if((ligneArr==0 || ligneArr==7) && colonneArr==6 && coupAvantPiece[0].getType()==6) {
            tableau[ligneArr][5] = tableau[ligneArr][7];
            tableau[ligneArr][6] = tableau[ligneArr][4];
            tableau[ligneArr][7] = new Piece();
            tableau[ligneArr][4] = new Piece();
            tableau[ligneArr][5].setCastle(false);
            tableau[ligneArr][6].setCastle(false);
        } else if(ligneDep==ligneArr && coupAvantPiece[0].getType()==1 && (colonneDep==colonneArr-1 || colonneDep==colonneArr+1)){//check en passant
            tableau[ligneArr+coupAvantPiece[0].getEquipe()][colonneArr]=coupAvantPiece[0];
            tableau[ligneDep][colonneDep]=new Piece();
            tableau[ligneDep][colonneArr]=new Piece();
        } else {
            if(coupAvantPiece[0].getType()==1 && Math.abs(ligneArr-ligneDep)==2)//pion avance 2 case
                coupAvantPiece[0].setAvance2case(true);
            tableau[ligneArr][colonneArr]=coupAvantPiece[0];
            tableau[ligneDep][colonneDep]=new Piece();
        }
        coutTot.empiler(coupAvantIndice,coupAvantPiece);
    }

    public void changeBack(){
        if(coutTot.pileVide()){
            System.out.println("ERROR_pilePieceVide");
        }
        else {
            Object[] t= coutTot.depiler();
            Piece[] coupAvantPiece=(Piece[])t[1];
            int[] coupAvantIndice=(int[])t[0];

            int ligneDep=coupAvantIndice[0];
            int colonneDep=coupAvantIndice[1];
            int ligneArr = coupAvantIndice[2];
            int colonneArr=coupAvantIndice[3];

            tableau[ligneDep][colonneDep]=coupAvantPiece[0];
            tableau[ligneArr][colonneArr]=coupAvantPiece[1];

            if((ligneArr==0 || ligneArr==7) && colonneArr==2 && coupAvantPiece[0].getType()==6){
                tableau[ligneArr][0]=tableau[ligneArr][3];
                tableau[ligneArr][4]=tableau[ligneArr][2];
                tableau[ligneArr][3] = new Piece();
                tableau[ligneArr][2] = new Piece();
                tableau[ligneArr][0].setCastle(true);
                tableau[ligneArr][4].setCastle(true);
            } else if((ligneArr==0 || ligneArr==7) && colonneArr==6 && coupAvantPiece[0].getType()==6){
                tableau[ligneArr][7]=tableau[ligneArr][5];
                tableau[ligneArr][4]=tableau[ligneArr][6];
                tableau[ligneArr][5] = new Piece();
                tableau[ligneArr][6] = new Piece();
                tableau[ligneArr][4].setCastle(true);
                tableau[ligneArr][7].setCastle(true);
            } else if(ligneDep==ligneArr && coupAvantPiece[0].getType()==1 && (colonneDep==colonneArr-1 || colonneDep==colonneArr+1)){//check en passant
                tableau[ligneArr+coupAvantPiece[0].getEquipe()][colonneArr]=new Piece();
                tableau[ligneDep][colonneDep]=coupAvantPiece[0];
                tableau[ligneDep][colonneArr]=coupAvantPiece[1];
            }else {
                if(coupAvantPiece[0].getType()==1 && Math.abs(ligneArr-ligneDep)==2)//pion avance 2 case
                    coupAvantPiece[0].setAvance2case(false);
                tableau[ligneArr][colonneArr] = coupAvantPiece[1];
                tableau[ligneDep][colonneDep] = coupAvantPiece[0];
            }
        }
    }

    public boolean testCastle(int i,int j) {//position le roi va être après le rock
        if ((i == 0 || i == 7) && j == 2 && tableau[i][4].getCastle() && tableau[i][0].getCastle() && tableau[i][1].getType() == 0 && tableau[i][2].getType() == 0 && tableau[i][3].getType() == 0) {
            tableau[i][3] = tableau[i][0];
            tableau[i][2] = tableau[i][4];
            tableau[i][0] = new Piece();
            tableau[i][4] = new Piece();
            int b = checkMate();
            tableau[i][0] = tableau[i][3];
            tableau[i][4] = tableau[i][2];
            tableau[i][2] = new Piece();
            tableau[i][3] = new Piece();
            return b == tableau[i][4].getEquipe() || b == 2;
        } else if (i == 0 && j == 6 && tableau[i][4].getCastle() && tableau[i][7].getCastle() && tableau[i][5].getType() == 0 && tableau[i][6].getType() == 0) {
            tableau[i][5] = tableau[i][7];
            tableau[i][6] = tableau[i][4];
            tableau[i][7] = new Piece();
            tableau[i][4] = new Piece();
            int b = checkMate();
            tableau[i][7] = tableau[i][5];
            tableau[i][4] = tableau[i][6];
            tableau[i][5] = new Piece();
            tableau[i][6] = new Piece();
            return b == tableau[i][4].getEquipe() || b == 2;
        } else
            return false;
    }

    public void affichage(){
        System.out.println("Echiquier : ");
        for(int i = 0; i < 8 ; i ++){
            for(int j = 0; j < 8 ; j ++){
                switch (tableau[i][j].getType()) {
                    case (0) -> System.out.print('·');
                    case (1) -> System.out.print('P');
                    case (2) -> System.out.print('T');
                    case (3) -> System.out.print('C');
                    case (4) -> System.out.print('F');
                    case (5) -> System.out.print('Q');
                    case (6) -> System.out.print('K');
                    default -> System.out.println("ERROR_type");
                }
                switch (tableau[i][j].getEquipe()) {
                    case (0) -> System.out.print(" ");
                    case (1) -> System.out.print("+");
                    case (-1) -> System.out.print("-");
                    default -> System.out.println("ERROR_equipe");
                }
                System.out.print(" ");
            }
            System.out.print("\n");
        }
    }

    public void deplacer(int ligneDep,int colonneDep,int ligneArr,int colonneArr){//check si le move est bon avant de déplacer la pièce
        Pile depPos = deplacementPossiblePosition(ligneDep,colonneDep);
        boolean depPossible = false;
        while(!depPos.pileVide()) {
            int[] t = depPos.depiler();
            if (t[0] == ligneArr && t[1] == colonneArr) {
                depPossible = true;
                break;
            }
        }
        if(depPossible){
            Piece chgm = tableau[ligneDep][colonneDep];
            tableau[ligneDep][colonneDep] = new Piece(0,0);
            if(tableau[ligneArr][colonneArr].getType()!=0){
                System.out.println("La pièce "+tableau[ligneArr][colonneArr].getType()+" de l'équipe "+tableau[ligneArr][colonneArr].getEquipe()+" est morte !");
            }
            tableau[ligneArr][colonneArr] = chgm;
        }else{
            System.out.println("Cette position n'est pas possible");
        }
    }

    public int checkMate(){//retourn 1 si le roi du joueur 1 est en échec, -1 si joueur -1, 2 si les 2 les sont,0 sinon
        int echec=0;
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if(tableau[i][j].getType() == 6){
                    int equipe=tableau[i][j].getType();

                    int l=i-1,c=j;//check tour ou reine
                    while (l >= 0 && tableau[l][c].getEquipe() == 0) {
                        l--;
                    }
                    if(l >= 0 && tableau[l][c].getEquipe() != equipe && (5 == tableau[l][c].getType() || 2 == tableau[l][c].getType())){
                        if(echec==-equipe)
                            echec = 2;
                        else
                            echec=equipe;
                    }

                    l=i+1;
                    c=j;
                    while (l < 8 && tableau[l][c].getEquipe() == 0) {
                        l++;
                    }
                    if(l < 8 && tableau[l][c].getEquipe() != equipe && (5 == tableau[l][c].getType() || 2 == tableau[l][c].getType())){
                        if(echec==-equipe)
                            echec = 2;
                        else
                            echec=equipe;
                    }

                    l=i;
                    c=j+1;
                    while (c < 8 && tableau[l][c].getEquipe() == 0) {
                        c ++;
                    }
                    if(c < 8 && tableau[l][c].getEquipe() != equipe && (5 == tableau[l][c].getType() || 2 == tableau[l][c].getType())){
                        if(echec==-equipe)
                            echec = 2;
                        else
                            echec=equipe;
                    }

                    l=i;
                    c=j-1;
                    while (c>= 0 && tableau[l][c].getEquipe() == 0) {
                        c--;
                    }
                    if(c >= 0 && tableau[l][c].getEquipe() != equipe && (5 == tableau[l][c].getType() || 2 == tableau[l][c].getType())){
                        if(echec==-equipe)
                            echec = 2;
                        else
                            echec=equipe;
                    }


                    l=i-1;//check fou ou reine
                    c=j-1;
                    while (l >= 0 && c >= 0 && tableau[l][c].getEquipe() == 0) {
                        l--;
                        c--;
                    }
                    if(l >= 0 && c >= 0 && tableau[l][c].getEquipe() != equipe && (5 == tableau[l][c].getType() || 4 == tableau[l][c].getType())){
                        if(echec==-equipe)
                            echec = 2;
                        else
                            echec=equipe;
                    }

                    l=i+1;
                    c=j-1;
                    while (l < 8 && c>=0 && tableau[l][c].getEquipe() == 0) {
                        l++;
                        c--;
                    }
                    if(l < 8 && c>=0 && tableau[l][c].getEquipe() != equipe && (5 == tableau[l][c].getType() || 4 == tableau[l][c].getType())){
                        if(echec==-equipe)
                            echec = 2;
                        else
                            echec=equipe;
                    }

                    l=i+1;
                    c=j+1;
                    while (l < 8 && c < 8 && tableau[l][c].getEquipe() == 0) {
                        l++;
                        c++;
                    }
                    if(l < 8 && c < 8 && tableau[l][c].getEquipe() != equipe && (5 == tableau[l][c].getType() || 4 == tableau[l][c].getType())){
                        if(echec==-equipe)
                            echec = 2;
                        else
                            echec=equipe;
                    }

                    l=i-1;
                    c=j+1;
                    while (l >= 0 && c<8 && tableau[l][c].getEquipe() == 0) {
                        l--;
                        c++;
                    }
                    if(l >= 0 && c<8 && tableau[l][c].getEquipe() != equipe && (5 == tableau[l][c].getType() || 4 == tableau[l][c].getType())){
                        if(echec==-equipe)
                            echec = 2;
                        else
                            echec=equipe;
                    }

                    int[][] cases = {{2,1},{1,2},{-2,1},{1,-2},{-1,2},{2,-1},{-2,-1},{-1,-2}}; //check cavalier
                    for(int k = 0; k < 8 ; k ++) {
                        l = i + cases[i][0];
                        c = j + cases[i][1];
                        if (l < 8 && l >= 0 && c < 8 && c >= 0 && tableau[l][c].getType() == 3 && tableau[l][c].getEquipe() != equipe) {
                            if (echec==-equipe)
                                echec = 2;
                            else
                                echec = equipe;
                        }
                    }

                    l=i-equipe;
                    if(l>=0 && l<8) {
                        c=j+1;
                        if(c<8 && tableau[c][l].getType()==1 && tableau[c][l].getEquipe()==-equipe) {
                            if (echec == -equipe)
                                echec = 2;
                            else
                                echec = equipe;
                        }
                        c=j-1;
                        if(c<8 && tableau[c][l].getType()==1 && tableau[c][l].getEquipe()==-equipe) {
                            if (echec == -equipe)
                                echec = 2;
                            else
                                echec = equipe;
                        }
                    }
                }
            }
        }
        return echec;
    }

    public Pile deplacementPossiblePosition(int ligne, int colonne){
        int type=tableau[ligne][colonne].getType(),equipe=tableau[ligne][colonne].getEquipe();
        Pile depPosTot = new Pile(new int[]{0, 0});
        switch(type){

            case(1)://pion
                int l=ligne+equipe, c=colonne;
                if( (l >= 0 && l < 8 && tableau[l][c].getEquipe() == 0) ){//avance
                    depPosTot.empiler(new int[] {l,c});
                }
                l=ligne+2*equipe;
                if(ligne == (1-equipe)*3+(equipe+1)/2 && tableau[l][c].getEquipe() == 0){//double avance
                    depPosTot.empiler(new int[] {l,c});
                }
                l=ligne+equipe;
                c=colonne+1;
                if( l >= 0 && l < 8 && c < 8 && tableau[l][c].getEquipe() == -equipe){
                    depPosTot.empiler(new int[] {l,c});
                    if(tableau[l][c].getEquipe() == 0 && tableau[ligne][c].getEquipe() == -equipe && tableau[ligne][c].getAvance2case() && ligne==3+ ((equipe==1) ? 1 : 0))//en passant droit
                        depPosTot.empiler(new int[] {ligne,c});
                }
                l=ligne+equipe;
                c=colonne-1;
                if( l > 0 && l < 8 && c > 0 && c < 8){
                    if(tableau[l][c].getEquipe() == -equipe)
                        depPosTot.empiler(new int[] {l,c});
                    if(tableau[l][c].getEquipe() == 0 && tableau[ligne][c].getEquipe() == -equipe && tableau[ligne][c].getAvance2case() && ligne==3+ ((equipe==1) ? 1 : 0))//en passant gauche
                        depPosTot.empiler(new int[] {ligne,c});
                }


                break;

            case(2)://tour
                if(testCastle(ligne,colonne)){//rock codé par Tour -> Roi
                    if(equipe==1)
                        depPosTot.empiler(new int[] {0,4});
                    else
                        depPosTot.empiler(new int[] {7,3});
                }
                int k = 1;
                boolean toucher = false;
                l=ligne-k;
                c=colonne;
                while (l >= 0 && tableau[l][c].getEquipe() != equipe && !toucher) {//haut
                    depPosTot.empiler(new int[] {ligne-k,colonne});
                    if(tableau[l][c].getEquipe() == -equipe){
                        toucher = true;
                    }
                    k ++;
                }

                k = 1;
                toucher = false;
                l=ligne+k;
                c=colonne;
                while (l < 8 && tableau[l][c].getEquipe() != equipe && !toucher) {//bas
                    depPosTot.empiler(new int[] {ligne+k,colonne});
                    if(tableau[l][c].getEquipe() == -equipe){
                        toucher = true;
                    }
                    k ++;
                }

                k = 1;
                toucher = false;
                l=ligne;
                c=colonne-k;
                while (c >= 0 && tableau[l][c].getEquipe() != equipe && !toucher) {//gauche
                    depPosTot.empiler(new int[] {ligne,colonne-k});
                    if(tableau[l][c].getEquipe() == -equipe){
                        toucher = true;
                    }
                    k ++;
                }

                k = 1;
                toucher = false;
                l=ligne;
                c=colonne+k;
                while (c < 8  && tableau[l][c].getEquipe() != equipe && !toucher) {//droite
                    depPosTot.empiler(new int[] {ligne,colonne+k});
                    if(tableau[l][c].getEquipe() == -equipe){
                        toucher = true;
                    }
                    k ++;
                }
                break;

            case(3)://cavalier
                int[][] cases = {{2,1},{1,2},{-2,1},{1,-2},{-1,2},{2,-1},{-2,-1},{-1,-2}};
                for(int i = 0; i < 8 ; i ++){
                    l=ligne+cases[i][0];
                    c=colonne+cases[i][1];
                    if(l<8 && l>=0 && c<8 && c>=0 && tableau[l][c].getEquipe() != equipe){
                        depPosTot.empiler(new int[] {l,c});
                    }
                }
                break;

            case(4)://fou
                k = 1;
                toucher = false;
                l=ligne-k;
                c=colonne-k;
                while (l >= 0 && c >= 0 && tableau[l][c].getEquipe() != equipe && !toucher) {//haut gauche
                    depPosTot.empiler(new int[] {ligne-k,colonne-k});
                    if(tableau[l][c].getEquipe() == -equipe){
                        toucher = true;
                    }
                    k ++;
                }

                k = 1;
                toucher = false;
                l=ligne-k;
                c=colonne+k;
                while (l >= 0 && c < 8 && tableau[l][c].getEquipe() != equipe && !toucher) {//haut droite
                    depPosTot.empiler(new int[] {ligne-k,colonne+k});
                    if(tableau[l][c].getEquipe() == -equipe){
                        toucher = true;
                    }
                    k ++;
                }

                k = 1;
                toucher = false;
                l=ligne+k;
                c=colonne-k;
                while (l < 8 && c >= 0 && tableau[l][c].getEquipe() != equipe && !toucher) {//bas gauche
                    if(tableau[l][c].getEquipe() == -equipe){
                        toucher = true;
                    }
                    k ++;
                }

                k = 1;
                toucher = false;
                l=ligne+k;
                c=colonne+k;
                while (l < 8 && c < 8  && tableau[l][c].getEquipe() != equipe && !toucher) {//bas droite
                    depPosTot.empiler(new int[] {ligne+k,colonne+k});
                    if(tableau[l][c].getEquipe() == -equipe){
                        toucher = true;
                    }
                    k ++;
                }
                break;

            case(5): //reine
                k = 1;
                toucher = false;
                l=ligne-k;
                c=colonne;
                while (l >= 0 && tableau[l][c].getEquipe() != equipe && !toucher) {//haut
                    depPosTot.empiler(new int[] {ligne-k,colonne});
                    if(tableau[l][c].getEquipe() == -equipe){
                        toucher = true;
                    }
                    k ++;
                }

                k = 1;
                toucher = false;
                l=ligne+k;
                c=colonne;
                while (l < 8 && tableau[l][c].getEquipe() != equipe && !toucher) {//bas
                    depPosTot.empiler(new int[] {ligne+k,colonne});
                    if(tableau[l][c].getEquipe() == -equipe){
                        toucher = true;
                    }
                    k ++;
                }

                k = 1;
                toucher = false;
                l=ligne;
                c=colonne-k;
                while (c >= 0 && tableau[l][c].getEquipe() != equipe && !toucher) {//gauche
                    depPosTot.empiler(new int[] {ligne,colonne-k});
                    if(tableau[l][c].getEquipe() == -equipe){
                        toucher = true;
                    }
                    k ++;
                }

                k = 1;
                toucher = false;
                l=ligne;
                c=colonne+k;
                while (c < 8  && tableau[l][c].getEquipe() != equipe && !toucher) {//droite
                    depPosTot.empiler(new int[] {ligne,colonne+k});
                    if(tableau[l][c].getEquipe() == -equipe){
                        toucher = true;
                    }
                    k ++;
                }

                k = 1;
                toucher = false;
                l=ligne-k;
                c=colonne-k;
                while (l >= 0 && c >= 0 && tableau[l][c].getEquipe() != equipe && !toucher) {//haut gauche
                    depPosTot.empiler(new int[] {ligne-k,colonne-k});
                    if(tableau[l][c].getEquipe() == -equipe){
                        toucher = true;
                    }
                    k ++;
                }

                k = 1;
                toucher = false;
                l=ligne-k;
                c=colonne+k;
                while (l >= 0 && c < 8 && tableau[l][c].getEquipe() != equipe && !toucher) {//haut droite
                    depPosTot.empiler(new int[] {ligne-k,colonne+k});
                    if(tableau[l][c].getEquipe() == -equipe){
                        toucher = true;
                    }
                    k ++;
                }

                k = 1;
                toucher = false;
                l=ligne+k;
                c=colonne-k;
                while (l < 8 && c >= 0 && tableau[l][c].getEquipe() != equipe && !toucher) {//bas gauche
                    if(tableau[l][c].getEquipe() == -equipe){
                        toucher = true;
                    }
                    k ++;
                }

                k = 1;
                toucher = false;
                l=ligne+k;
                c=colonne+k;
                while (l < 8 && c < 8  && tableau[l][c].getEquipe() != equipe && !toucher) {//bas droite
                    depPosTot.empiler(new int[] {ligne+k,colonne+k});
                    if(tableau[l][c].getEquipe() == -equipe){
                        toucher = true;
                    }
                    k ++;
                }
                break;

            case(6): // roi
                cases = new int[][]{{1,1},{0,1},{-1,1},{-1,0},{-1,-1},{0,-1},{1,-1},{1,0}};
                for(int i = 0; i < 8 ; i ++) {
                    l = ligne + cases[i][0];
                    c = colonne + cases[i][1];
                    if (l < 8 && l >= 0 && c < 8 && c >= 0 && tableau[l][c].getEquipe() != equipe) {
                        depPosTot.empiler(new int[]{l, c});
                    }
                }

                l=ligne;
                c=2;
                if(testCastle(l,c)){
                    depPosTot.empiler(new int[]{l, c});
                }

                c=6;
                if(testCastle(l,c)){
                    depPosTot.empiler(new int[]{l, c});
                }
                break;

        }
        return depPosTot;
    }
}
