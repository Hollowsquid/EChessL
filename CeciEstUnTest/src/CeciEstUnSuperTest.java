public class CeciEstUnSuperTest {



    public static void main(String[] args) {
        System.out.println("Ceci est un affichage de test");
        nbCpl z = new nbCpl(3,5);
        System.out.println(z.norme());
        compterDeAaB(1,10,false);
        System.out.println(exampleIf(1,100));
        testTableau();
    }




    static void testTableau(){
        float t0[] = {0,10,1390};
        float t1[] = new float[200];
        float t2[][];

        System.out.println(t0[1]);
        t0 = new float[]{1,2,10};
        System.out.println(t0[1]);

        System.out.println(t1[0]);
        for(int i = 0; i < 200 ; i ++){
            t1[i] = i*i;
        }
        System.out.println(t1[7]);

        t2 = new float[][]{{1,2,3},{7,8,9},{11,12,13}};
        System.out.println(t2[2][1]);
    }

    static class nbCpl{
        float r,i;
        nbCpl(float rIni,float iIni){
            r = rIni;
            i = iIni;
        }
        double norme(){
            double norme = Math.sqrt(r*r+i*i);
            return norme;
        }
    }

    static void compterDeAaB(int a,int b,boolean f){
        if(f){//Si f est vrai
            for(int i = a; i <= b ; i ++){
                System.out.println(i);
            }
        }else{
            int i = a;
            while(i <= b){
                System.out.println(i);
                i ++;
            }
        }
    }

    static String exampleIf(float a,float b){
        String rep = "";
        if(a == 1 && b > 10){
            rep = "a vaut 1 et b est supérieur à 10";
        }else if(b != 10 && a < 0){
            rep = "b est différent de 10 et a est négatif";
        }else{
            rep = "Rien de tout ça";
        }

        return rep;
    }



}

