public class AffichageEchiquier {

    public static void main(String[] args) {
        Piece[][] echiquier = initialisationEchiquier();
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

    static Piece[][] initialisationEchiquier(){
        Piece[][] echiquier = new Piece[8][8];
        for(int i = 1; i < 7 ; i ++){
            for(int j = 0; j < 8 ; j ++){
                 echiquier[i][j]= new Piece("vide",0);
            }
        }
        for(int i = 0; i < 8 ; i ++){
            echiquier[1][i] = new Piece("pion",1);
            echiquier[6][i] = new Piece("pion",-1);
        }
        for(int i = 0; i < 10; i+=7){
            echiquier[i][0] = new Piece("tour",(i==0) ? 1 : -1);
            echiquier[i][1] = new Piece("cavalier",(i==0) ? 1 : -1);
            echiquier[i][2] = new Piece("fou",(i==0) ? 1 : -1);
            echiquier[i][5] = new Piece("fou",(i==0) ? 1 : -1);
            echiquier[i][6] = new Piece("cavalier",(i==0) ? 1 : -1);
            echiquier[i][7] = new Piece("tour",(i==0) ? 1 : -1);
        }
        echiquier[0][3] = new Piece("reine",1);
        echiquier[0][4] = new Piece("roi",1);
        echiquier[7][3] = new Piece("roi",-1);
        echiquier[7][4] = new Piece("reine",-1);

        return echiquier;
    }

    public static void affichage(Piece[][] echiquier){
        System.out.println("Echiquier : ");
        for(int i = 0; i < 8 ; i ++){
            for(int j = 0; j < 8 ; j ++){
                switch (echiquier[i][j].type) {
                    case ("vide") -> System.out.print('·');
                    case ("pion") -> System.out.print('P');
                    case ("tour") -> System.out.print('T');
                    case ("cavalier") -> System.out.print('C');
                    case ("fou") -> System.out.print('F');
                    case ("reine") -> System.out.print('Q');
                    case ("roi") -> System.out.print('K');
                    default -> System.out.println("ERROR_type");
                }
                switch (echiquier[i][j].equipe) {
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

    static Piece[][]deplacer(int ligneDep,int colonneDep,int ligneArr,int colonneArr,Piece[][] echiquier){
        Pile depPos = echiquier[ligneDep][colonneDep].deplacementPossiblePosition(ligneDep,colonneDep,echiquier);
        boolean depPossible = false;
        while(!depPos.pileVide()) {
            int[] t = depPos.depiler();
            if (t[0] == ligneArr && t[1] == colonneArr) {
                depPossible = true;
                break;
            }
        }
        if(depPossible){
            Piece chgm = echiquier[ligneDep][colonneDep];
            echiquier[ligneDep][colonneDep] = new Piece("vide",0);
            echiquier[ligneArr][colonneArr].mort();
            echiquier[ligneArr][colonneArr] = chgm;
        }else{
            System.out.println("Cette position n'est pas possible");
        }
        return echiquier;
    }

}
