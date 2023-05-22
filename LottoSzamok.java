
package lottodatabase;

import java.util.Set;
import java.util.Arrays;
import java.util.HashSet;

public class LottoSzamok {
    private int[] szamok= new int[5];
    private int id=-1;

    public LottoSzamok() {
    }
    
    public LottoSzamok(int[] szamok) {
        this.szamok=szamok;
    }
    public LottoSzamok(int id, int[] szamok) {
        this.szamok=szamok;
        this.id=id;
    }
    
    public LottoSzamok(String sor) throws Exception {
       // 51;81;40;24;-6
       String[] tombSzoveg= sor.trim().split(";");
       if (tombSzoveg.length==5)
       {
           Integer[] szamok = new Integer[5];
           for (int i = 0; i < 5; i++) {
               try{
                   szamok[i]=Integer.parseInt(tombSzoveg[i]);
               }
               catch (Exception e){
                   throw new Exception("Az adat nem alakítható számmá!");
               }
               if ((szamok[i]<1) || (szamok[i]>90))
                    throw new Exception("Az adat értéke nem megfelelő!");
           }
           Set<Integer> halmaz
            = new HashSet<>(Arrays.asList(szamok));
           if (halmaz.size()!=5)
               throw new Exception("Ismétlődő adatok!");
           
           for (int i = 0; i < 5; i++) 
               this.szamok[i]=szamok[i];
       }
       else{
           throw new Exception("Hibás az adatok száma!");
       }
       
    }

    @Override
    public String toString() {
         String v="ID: "+(this.id==-1?null:this.id) +"(";
        for (int i = 0; i < 5; i++) 
            v+=" "+this.szamok[i];       
        return  v+" )";
    }
    
    
    public String toSQLString() {
        // (null,1,2,3,4,5);
        String v="("+(this.id==-1?null:this.id);
        for (int i = 0; i < 5; i++) 
            v+=","+this.szamok[i];       
        return  v+");";
    }
    
    
    
}
