public class PilePiece {
    private int[][] tablInt;
    private Piece[][] tablPiece;
    private int capacite = 1;
    private int nombreElements = 0;

    PilePiece(int[] tablint, Piece[] tablpiece) {
        tablPiece = new Piece[][]{tablpiece};
        tablInt=new int[][]{tablint};
    }

    public void empiler(int[] element, Piece[] tableauPiece) {
        if (nombreElements == capacite) {
            int[][] newPile = new int[capacite * 2][tablInt[0].length];
            Piece[][] newPile2 = new Piece[capacite * 2][tablPiece[0].length];
            if (capacite >= 0) {
                System.arraycopy(tablInt, 0, newPile, 0, capacite);
                System.arraycopy(tablPiece, 0, newPile2, 0, capacite);
            }
            tablInt = newPile;
            tablPiece = newPile2;
            capacite *= 2;
        }
        tablInt[nombreElements] = element;
        tablPiece[nombreElements] = tableauPiece;
        nombreElements++;
        random1erElt();
    }

    public boolean pileVide() {
        return nombreElements <= 0;
    }

    public Object[] depiler() {
        if (pileVide()) {
            System.out.println("ERROR_pilePiece_vide (echiquier de départ !)");
            return new Object[]{tablInt[0], tablPiece[0]};
        } else {
            nombreElements -= 1;
            return new Object[]{tablInt[nombreElements], tablPiece[nombreElements]};
        }
    }
    /*
        public void random(){
            int[][] newPile = new int[capacite][pile[0].length];
            int i = nombreElements;
            while(i > 0){
                int j=(int) (Math.random()*i);
                newPile[nombreElements-i] = pile[j];
                pile[j]=pile[i-1];
                i--;
            }
            pile = newPile;
        }

     */
    private void random1erElt(){
        int j = (int) (Math.random() * nombreElements);
        Piece[] b = tablPiece[j];
        tablPiece[j] = tablPiece[0];
        tablPiece[0] = b;
        int[] a = tablInt[j];
        tablInt[j] = tablInt[0];
        tablInt[0] = a;
    }
}

