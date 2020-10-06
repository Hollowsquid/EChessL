public class Piece {

    String type; //0 : case vide | 1 : pion | 2 : tour | 3 : cavalier | 4 : fou | 5 : reine | 6 : roi
    int equipe;// equipe de la pièce | haut : 1 | bas : -1 | case vide : 0

    Piece(String type, int equipe) {
        this.type = type;
        this.equipe = equipe;
    }

    Pile deplacementPossiblePosition(int ligne, int colonne, Piece[][] echiquier){
        Pile depPosTot = new Pile(new int[]{0, 0});
        switch(type){

            case("pion")://pion
                if( (ligne+equipe > 0 && ligne+equipe < 8 && echiquier[ligne+equipe][colonne].equipe != equipe) ){//avance
                    depPosTot.empiler(new int[] {ligne+equipe,colonne});
                }
                if(ligne == (1-equipe)*3+(equipe+1)/2 && echiquier[ligne+2*equipe][colonne].equipe != equipe){//double avance
                    depPosTot.empiler(new int[] {ligne+2*equipe,colonne});
                }
                if( ligne+equipe > 0 && ligne+equipe < 8 && colonne+1 > 0 && colonne+1 < 8 && echiquier[ligne+equipe][colonne+1].equipe != equipe && "vide".equals(echiquier[ligne+equipe][colonne+1].type)){
                    depPosTot.empiler(new int[] {ligne+equipe,colonne+1});
                }
                if( ligne+equipe > 0 && ligne+equipe < 8 && colonne-1 > 0 && colonne-1 < 8 && echiquier[ligne+equipe][colonne-1].equipe != equipe && "vide".equals(echiquier[ligne+equipe][colonne-1].type)){
                    depPosTot.empiler(new int[] {ligne+equipe,colonne-1});
                }
                break;

            case("tour")://tour
                int k = 1;
                boolean toucher = false;
                while (ligne-k >= 0 && echiquier[ligne-k][colonne].equipe != equipe && !toucher) {//haut
                    depPosTot.empiler(new int[] {ligne-k,colonne});
                    if(echiquier[ligne-k][colonne].equipe != equipe && echiquier[ligne-k][colonne].equipe != 0){
                        toucher = true;
                    }
                    k ++;
                }

                k = 1;
                toucher = false;
                while (ligne+k < 8 && echiquier[ligne+k][colonne].equipe != equipe && !toucher) {//bas
                    depPosTot.empiler(new int[] {ligne+k,colonne});
                    if(echiquier[ligne+k][colonne].equipe != equipe && echiquier[ligne+k][colonne].equipe != 0){
                        toucher = true;
                    }
                    k ++;
                }

                k = 1;
                toucher = false;
                while (colonne-k >= 0 && echiquier[ligne][colonne-k].equipe != equipe && !toucher) {//gauche
                    depPosTot.empiler(new int[] {ligne,colonne-k});
                    if(echiquier[ligne][colonne-k].equipe != equipe && echiquier[ligne][colonne-k].equipe != 0){
                        toucher = true;
                    }
                    k ++;
                }

                k = 1;
                toucher = false;
                while (colonne+k < 8  && echiquier[ligne][colonne+k].equipe != equipe && !toucher) {//gauche
                    depPosTot.empiler(new int[] {ligne,colonne+k});
                    if(echiquier[ligne][colonne+k].equipe != equipe && echiquier[ligne][colonne+k].equipe != 0){
                        toucher = true;
                    }
                    k ++;
                }
                break;

            case("cavalier")://cavalier
                for(int i = 0; i < 8 ; i ++){
                    for(int j = 0; j  < 8 ; j ++){
                        if( !(i == ligne || j == colonne) && Math.abs(ligne-i)+Math.abs(j-colonne) == 3 ){
                            if("vide".equals(echiquier[i][j].type) || echiquier[i][j].equipe != equipe){
                                depPosTot.empiler(new int[] {i,j});
                            }
                        }
                    }
                }
                break;

            case("fou")://fou
                k = 1;
                toucher = false;
                while (ligne-k >= 0 && colonne-k >= 0 && echiquier[ligne-k][colonne-k].equipe != equipe && !toucher) {//haut gauche
                    depPosTot.empiler(new int[] {ligne-k,colonne-k});
                    if(echiquier[ligne-k][colonne-k].equipe != equipe && echiquier[ligne-k][colonne-k].equipe != 0){
                        toucher = true;
                    }
                    k ++;
                }

                k = 1;
                toucher = false;
                while (ligne-k >= 0 && colonne+k < 8 && echiquier[ligne-k][colonne+k].equipe != equipe && !toucher) {//haut droite
                    depPosTot.empiler(new int[] {ligne-k,colonne+k});
                    if(echiquier[ligne-k][colonne+k].equipe != equipe && echiquier[ligne-k][colonne+k].equipe != 0){
                        toucher = true;
                    }
                    k ++;
                }

                k = 1;
                toucher = false;
                while (ligne+k < 8 && colonne-k >= 0 && echiquier[ligne+k][colonne-k].equipe != equipe && !toucher) {//bas gauche
                    if(echiquier[ligne+k][colonne-k].equipe != equipe && echiquier[ligne+k][colonne-k].equipe != 0){
                        toucher = true;
                    }
                    k ++;
                }

                k = 1;
                toucher = false;
                while (ligne+k < 8 && colonne+k < 8  && echiquier[ligne+k][colonne+k].equipe != equipe && !toucher) {//bas droite
                    depPosTot.empiler(new int[] {ligne+k,colonne+k});
                    if(echiquier[ligne+k][colonne+k].equipe != equipe && echiquier[ligne+k][colonne+k].equipe != 0){
                        toucher = true;
                    }
                    k ++;
                }
                break;

            case("reine"): //reine
                k = 1;
                toucher = false;
                while (ligne-k >= 0 && echiquier[ligne-k][colonne].equipe != equipe && !toucher) {//haut
                    depPosTot.empiler(new int[] {ligne-k,colonne});
                    if(echiquier[ligne-k][colonne].equipe != equipe && echiquier[ligne-k][colonne].equipe != 0){
                        toucher = true;
                    }
                    k ++;
                }

                k = 1;
                toucher = false;
                while (ligne+k < 8 && echiquier[ligne+k][colonne].equipe != equipe && !toucher) {//bas
                    depPosTot.empiler(new int[] {ligne+k,colonne});
                    if(echiquier[ligne+k][colonne].equipe != equipe && echiquier[ligne+k][colonne].equipe != 0){
                        toucher = true;
                    }
                    k ++;
                }

                k = 1;
                toucher = false;
                while (colonne-k >= 0 && echiquier[ligne][colonne-k].equipe != equipe && !toucher) {//gauche
                    depPosTot.empiler(new int[] {ligne,colonne-k});
                    if(echiquier[ligne][colonne-k].equipe != equipe && echiquier[ligne][colonne-k].equipe != 0){
                        toucher = true;
                    }
                    k ++;
                }

                k = 1;
                toucher = false;
                while (colonne+k < 8  && echiquier[ligne][colonne+k].equipe != equipe && !toucher) {//gauche
                    depPosTot.empiler(new int[] {ligne,colonne+k});
                    if(echiquier[ligne][colonne+k].equipe != equipe && echiquier[ligne][colonne+k].equipe != 0){
                        toucher = true;
                    }
                    k ++;
                }

                k = 1;
                toucher = false;
                while (ligne-k >= 0 && colonne-k >= 0 && echiquier[ligne-k][colonne-k].equipe != equipe && !toucher) {//haut gauche
                    depPosTot.empiler(new int[] {ligne-k,colonne-k});
                    if(echiquier[ligne-k][colonne-k].equipe != equipe && echiquier[ligne-k][colonne-k].equipe != 0){
                        toucher = true;
                    }
                    k ++;
                }

                k = 1;
                toucher = false;
                while (ligne-k >= 0 && colonne+k < 8 && echiquier[ligne-k][colonne+k].equipe != equipe && !toucher) {//haut droite
                    depPosTot.empiler(new int[] {ligne-k,colonne+k});
                    if(echiquier[ligne-k][colonne+k].equipe != equipe && echiquier[ligne-k][colonne+k].equipe != 0){
                        toucher = true;
                    }
                    k ++;
                }

                k = 1;
                toucher = false;
                while (ligne+k < 8 && colonne-k >= 0 && echiquier[ligne+k][colonne-k].equipe != equipe && !toucher) {//bas gauche
                    depPosTot.empiler(new int[] {ligne+k,colonne-k});
                    if(echiquier[ligne+k][colonne-k].equipe != equipe && echiquier[ligne+k][colonne-k].equipe != 0){
                        toucher = true;
                    }
                    k ++;
                }

                k = 1;
                toucher = false;
                while (ligne+k < 8 && colonne+k < 8  && echiquier[ligne+k][colonne+k].equipe != equipe && !toucher) {//bas droite
                    depPosTot.empiler(new int[] {ligne+k,colonne+k});
                    if(echiquier[ligne+k][colonne+k].equipe != equipe && echiquier[ligne+k][colonne+k].equipe != 0){
                        toucher = true;
                    }
                    k ++;
                }

                break;

            case("roi"): // roi
                int[][] cases = {{1,1},{0,1},{-1,1},{-1,0},{-1,-1},{0,-1},{1,-1},{1,0}};
                for(int i = 0; i < 8 ; i ++){
                    if(echiquier[ligne+cases[i][0]][colonne+cases[i][1]].equipe != equipe){
                        depPosTot.empiler(new int[] {ligne+cases[i][0],colonne+cases[i][1]});
                    }
                }

                break;

        }
        return depPosTot;
    }

    void mort(){
        if(!"vide".equals(type)){
            System.out.println("La pièce "+type+" de l'équipe "+equipe+" est morte !");
        }
    }
}
