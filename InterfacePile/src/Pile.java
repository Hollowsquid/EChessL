public class Pile {
    private int[][] pile;
    private int capacite = 1;
    private int nombreElements = 0;

    Pile(int[] element){
        pile= new int[][]{element};
    }

    public void empiler(int[] element){
        if(nombreElements == capacite){
            int[][] newPile = new int[capacite*2][pile[0].length];
            if (capacite >= 0) System.arraycopy(pile, 0, newPile, 0, capacite);
            pile = newPile;
            capacite*=2;
        }
        pile[nombreElements] = element;
        nombreElements++;
        random1erElt();
    }

    public boolean pileVide(){return nombreElements <= 0;}

    public int[] depiler(){
        if(pileVide()){
            System.out.println("ERROR_pile_vide");
            return pile[0];
        }
        else{
            nombreElements-=1;
            return pile[nombreElements];
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
        int j=(int) (Math.random()*nombreElements);
        int[] a=pile[j];
        pile[j]=pile[0];
        pile[0]=a;
    }

    public void union(Pile p){
        while(!p.pileVide()){
            empiler(p.depiler());
        }
        random1erElt();
    }

    public void affichage(){
        for(int i = nombreElements-1; i >= 0; i--){
            for(int j = 0; j < pile[i].length; j++){
                System.out.print(pile[i][j] + " ");
            }
            System.out.print("\n");
        }
    }
}
