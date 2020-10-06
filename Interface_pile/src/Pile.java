public class Pile {
    int[][] pile;
    int capacite = 1;
    int nombreElements = 0;
    Pile(int[] element){
        pile= new int[][]{element};
    }

    public void empiler(int[] element){
        if(nombreElements == capacite){
            int[][] newPile = new int[capacite*2][pile[0].length];
            for(int i = 0; i < capacite; i++){
                newPile[i] = pile[i];
            }
            pile = newPile;
            capacite*=2;
        }
        pile[nombreElements] = element;
        nombreElements++;
    }

    public boolean pileVide(){
        return nombreElements <= 0;
    }

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

    public void random(){
        int[][] newPile = new int[capacite][pile[0].length];
        int i = nombreElements;
        while(i > 0){
            newPile[nombreElements-i] = pile[(int) (Math.random()*i)];
            i--;
        }
        pile = newPile;
    }

    public void union(Pile p){
        while(!p.pileVide()){
            empiler(p.depiler());
        }
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
