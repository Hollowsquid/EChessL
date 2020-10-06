public class Test {
    public static void main(String[] args) {
        Pile p=new Pile(new int[]{1,2});
        p.empiler(new int[]{1,2});
        p.empiler(new int[]{1,3});
        p.empiler(p.depiler());
        p.affichage();
    }
}
