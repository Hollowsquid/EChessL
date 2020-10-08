package Interface_Pile;

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

    /*
    public static int[] minimax(Echiquier echiquier,int equipe,int profondeur){//retourne la position de départ et d'arrivée du meilleur coût et sa valeur
        int[] m = new int[0];//m={valeur coût,ligne départ,colonne départ,ligne arrivée,colonne arrivée}
        if(equipe==1) {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (echiquier.getEquipe(i,j) == equipe) {
                        Pile deplacements = echiquier.deplacementPossiblePosition(i, j);
                        int[] t = deplacements.depiler();
                        if (profondeur > 0) {
                            echiquier.change(i, j, t[0], t[1]);
                            m = minimax(echiquier, -equipe, profondeur - 1);
                            m = new int[]{m[0], i, j, t[0], t[1]};
                            echiquier.changeBack();
                            while (!deplacements.pileVide()) {
                                t = deplacements.depiler();
                                int[] a = minimax(change(echiquier, i, j, t[0], t[1]), -equipe, profondeur - 1);
                                if (a[0] > m[0]) {
                                    m = new int[]{a[0], i, j, t[0], t[1]};
                                }
                            }
                        } else {
                            m = new int[]{fonctionDevalBete(change(echiquier, i, j, t[0], t[1])), i, j, t[0], t[1]};
                            while (!deplacements.pileVide()) {
                                t = deplacements.depiler();
                                int a = fonctionDevalBete(change(echiquier, i, j, t[0], t[1]));
                                if (a > m[0]) {
                                    m = new int[]{a, i, j, t[0], t[1]};
                                }
                            }
                        }
                    }
                }
            }
        }
        else {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (echiquier[i][j].equipe == equipe) {
                        Pile deplacements = echiquier[i][j].deplacementPossiblePosition(i, j, echiquier);
                        int[] t = deplacements.depiler();
                        if (profondeur > 0) {
                            m = minimax(change(echiquier, i, j, t[0], t[1]), -equipe, profondeur - 1);
                            m = new int[]{m[0], i, j, t[0], t[1]};
                            while (!deplacements.pileVide()) {
                                t = deplacements.depiler();
                                int[] a = minimax(change(echiquier, i, j, t[0], t[1]), -equipe, profondeur - 1);
                                if (a[0] < m[0]) {
                                    m = new int[]{a[0], i, j, t[0], t[1]};
                                }
                            }
                        } else {
                            m = new int[]{fonctionDevalBete(change(echiquier, i, j, t[0], t[1])), i, j, t[0], t[1]};
                            while (!deplacements.pileVide()) {
                                t = deplacements.depiler();
                                int a = fonctionDevalBete(change(echiquier, i, j, t[0], t[1]));
                                if (a < m[0]) {
                                    m = new int[]{a, i, j, t[0], t[1]};
                                }
                            }
                        }
                    }
                }
            }

        }
        return m;
    }

     */

        /*
    public static Pile tousDeplacementsPossible(Piece[][] echiquier,int equipe){
        Pile deplacements=new Pile(new int[]{0,0});
        for(int i=0; i<8; i++){
            for(int j=0; j<8; j++){
                if(echiquier[i][j].equipe == equipe){
                    deplacements.empiler(new int[]{i,j});
                }
            }
        }
        deplacements;
    }

         */

    /*
    public static int fonctionDevalBete(Piece[][] echiquier){
        int s=0;
        for(int i=0; i<8; i++){
            for(int j=0; j<8; j++){
                int epsilon=echiquier[i][j].equipe;
                switch(echiquier[i][j].type){
                    case(0) -> s+=epsilon;
                    case(1) -> s+=5*epsilon;
                    case(2), (3) -> s+=3*epsilon;
                    case(4) -> s+=10*epsilon;
                }
            }
        }
        return s;
    }

     */
}
