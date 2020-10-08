public class Main {
    public static void main(String[] args) {
        Echiquier echiquier=new Echiquier();
        //echiquier[2][4] = new piece(5,10,1);
        echiquier.affichage();
        //System.out.println( Arrays.deepToString(echiquier[2][4].deplacementPossible(2,4,echiquier)) );
        echiquier.deplacer(1,2,3,2);
        echiquier.affichage();
        echiquier.deplacer(6,1,4,1);
        echiquier.affichage();
        echiquier.deplacer(1,3,2,3);
        echiquier.affichage();
        echiquier.deplacer(4,1,3,2);
        echiquier.affichage();
        //System.out.println( Arrays.deepToString(echiquier[2][3].deplacementPossible(2,3,echiquier)) );
        echiquier.deplacer(2,3,3,2);
        echiquier.affichage();
/*
        Pile p=new Pile(new int[]{0,0});
        p.empiler(new int[]{1,2});
        p.empiler(new int[]{1,3});
        Pile p1=new Pile(new int[]{0,0});
        p1.empiler(new int[]{1,4});
        p.union(p1);
        p.affichage();

 */

    }
}
