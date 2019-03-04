// Based on a B specification from Marie-Laure Potet.

public class Explosives{
    public int nb_inc = 0;
    public String [][] incomp = new String[50][2];
    public int nb_assign = 0;
    public String [][] assign = new String[30][2];
 
    // -- P1: On ne peut pas avoir plus de 25 paires de produits incompatibles (et on ne peut pas avoir un nombre négatif) --
    /*@ public invariant
    @ (0 <= nb_inc && nb_inc < 50);
    @*/
    
    // -- P2: On ne peut pas ajouter plus de 30 produits dans les batiments (et on ne peut pas avoir un nombre négatif) --
    /*@ public invariant 
    @ (0 <= nb_assign && nb_assign < 30);
    @*/

    // -- P3: Les incompatibilités doivent concerner deux produits --
    /*@ public invariant
    @ (\forall int i; 0 <= i && i < nb_inc; 
    @         incomp[i][0].startsWith("Prod") && incomp[i][1].startsWith("Prod"));
    @*/

    // -- P4: Les assignations concerne d'abord un batiment puis un produit --
    /*@ public invariant 
    @ (\forall int i; 0 <= i && i < nb_assign; 
    @         assign[i][0].startsWith("Bat") && assign[i][1].startsWith("Prod"));
    @*/

    // -- P5: L'incompatibilité doit concerner deux produits différents --
    /*@ public invariant 
    @ (\forall int i; 0 <= i && i < nb_inc; !(incomp[i][0]).equals(incomp[i][1]));
    @*/

    // -- P6: Les incompatibilités mirroirs doivent exister (I: A /!\ B && J: B /!\ A) --
    /*@ public invariant 
    @ (\forall int i; 0 <= i && i < nb_inc; 
    @        (\exists int j; 0 <= j && j < nb_inc; 
    @           (incomp[i][0]).equals(incomp[j][1]) 
    @              && (incomp[j][0]).equals(incomp[i][1]))); 
    @*/

    // -- P7: Pour toutes les assignations, les produits incompatibles à un produit présent dans le batiment ne doivent pas être présents dans ce batiment (Si au moins 2 produits sont présents dans le batiment) --
    /*@ public invariant 
    @ (\forall int i; 0 <= i &&  i < nb_assign; 
    @     (\forall int j; 0 <= j && j < nb_assign; 
    @        (i != j && (assign[i][0]).equals(assign [j][0])) ==>
    @        (\forall int k; 0 <= k && k < nb_inc;
    @           (!(assign[i][1]).equals(incomp[k][0])) 
    @              || (!(assign[j][1]).equals(incomp[k][1])))));
    @*/

    //@requires nb_inc + 2 < 50;
    //@requires prod1.startsWith("Prod") && prod2.startsWith("Prod");
    //@requires ! prod1.equals(prod2);
    public void add_incomp(String prod1, String prod2){
      incomp[nb_inc][0] = prod1;
      incomp[nb_inc][1] = prod2;
      incomp[nb_inc+1][1] = prod1;
      incomp[nb_inc+1][0] = prod2;
      nb_inc = nb_inc+2;
    }

    //@requires nb_assign + 1 < 30;
    //@requires bat.startsWith("Bat") && prod.startsWith("Prod");
    public void add_assign(String bat, String prod){
      assign[nb_assign][0] = bat;
      assign[nb_assign][1] = prod;
      nb_assign = nb_assign+1;
    }

    public void skip(){
    }
}
