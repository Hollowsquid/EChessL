public class AffichageEchiquier {

    public static void main(String[] args) {
        piece[][] echiquier = initialisationEchiquier();
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

    static piece[][] initialisationEchiquier(){
        piece[][] echiquier = new piece[8][8];
        for(int i = 1; i < 7 ; i ++){
            for(int j = 0; j < 8 ; j ++){
                 echiquier[i][j]= new piece(0,0);
            }
        }
        for(int i = 0; i < 8 ; i ++){
            echiquier[1][i] = new piece(1,1);
            echiquier[6][i] = new piece(1,-1);
        }
        for(int i = 0; i < 3 ; i ++){
            echiquier[0][i] = new piece(2+i,1);
            echiquier[0][7-i] = new piece(2+i,1);
            echiquier[7][i] = new piece(2+i,-1);
            echiquier[7][7-i] = new piece(2+i,-1);
        }
        echiquier[0][3] = new piece(5,1);
        echiquier[0][4] = new piece(6,1);
        echiquier[7][3] = new piece(6,-1);
        echiquier[7][4] = new piece(5,-1);

        return echiquier;
    }

    public static void affichage(piece[][] echiquier){
        System.out.println("Echiquier : ");
        for(int i = 0; i < 8 ; i ++){
            for(int j = 0; j < 8 ; j ++){
                switch (echiquier[i][j].type) {
                    case (0) -> System.out.print('·');
                    case (1) -> System.out.print('P');
                    case (2) -> System.out.print('T');
                    case (3) -> System.out.print('C');
                    case (4) -> System.out.print('F');
                    case (5) -> System.out.print('Q');
                    case (6) -> System.out.print('K');
                }
                switch(echiquier[i][j].equipe){
                    case (0) -> System.out.print(" ");
                    case (1) -> System.out.print("+");
                    case (-1) -> System.out.print("-");
                }
                System.out.print(" ");
            }
            System.out.print("\n");
        }
    }

    static piece[][]deplacer(int ligneDep,int colonneDep,int ligneArr,int colonneArr,piece[][] echiquier){
        int [][] depPos = echiquier[ligneDep][colonneDep].deplacementPossiblePosition(ligneDep,colonneDep,echiquier);
        boolean depPossible = false;
        for (int[] depPo : depPos) {
            if (depPo[0] == ligneArr && depPo[1] == colonneArr) {
                depPossible = true;
                break;
            }
        }
        if(depPossible){
            piece chgm = echiquier[ligneDep][colonneDep];
            echiquier[ligneDep][colonneDep] = new piece(0,0);
            echiquier[ligneArr][colonneArr].mort();
            echiquier[ligneArr][colonneArr] = chgm;
        }else{
            System.out.println("Cette position n'est pas possible");
        }
        return echiquier;
    }

}
