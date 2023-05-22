package lottodatabase;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LottoDatabase {

    public static void main(String[] args) throws FileNotFoundException, IOException, SQLException {
            
//        
//LottoSzamok szam=null;
//        try {
//            szam= new LottoSzamok("1;2;3;1;90");
//        } catch (Exception ex) {
//            System.out.println(ex);
//        }
//        if (szam!=null) {
//            System.out.println(szam);
//            System.out.println(szam.toSQLString());
//        }
          if (args.length==0) System.exit(0);
          Db db= new Db("5oslotto", "root","");
          db.deleteTable();
          int lineGood=0;
          int lineError=0;
          File file = new File(args[0]);
          BufferedReader br = new BufferedReader(new FileReader(file) );
          
          BufferedWriter bw = new BufferedWriter(new FileWriter(file.getParent()+"/importalasiHibak.csv"));
          //System.out.println(file.getParent());
          
                  
          
          String sor= br.readLine();
          while(sor!=null){
              LottoSzamok szam= null;
              
              try {
                  szam= new LottoSzamok(sor);
              } catch (Exception ex) {
                  //System.out.println(ex+"; "+sor);
                  bw.write(sor+" - "+ex+"\n");
                  lineError++;
              }
              if (szam!=null){
                  db.addNumbers(szam);
                  lineGood++;
              }
              
              sor= br.readLine();
          }
          br.close();
          bw.close();
          System.out.println("A importálás sikerült! A sikeresen importált sorok száma: "+lineGood+" Sikertelenül: "+lineError);
           
    }
}
        
       

