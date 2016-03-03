import java.io.*;
import java.util.*;

public class Konvertor {
        public static void main(String[] args) {
                String separator = System.getProperty("file.separator");
                File direktorijum = new File(args[0]);
                File izlaz = new File("soping_lista.txt");//ovde cu da smestim sve fajlove
                FileOutputStream fos = null;
                OutputStreamWriter osw = null;
                FileInputStream fis = null;
                try {
                        fos = new FileOutputStream(izlaz);
                        osw = new OutputStreamWriter(fos, "utf-8");
                        String[] nizFajlova = direktorijum.list();//izlistavanje fajlova koje cu da konvertujem
                        osw.write(String.format("%02x", nizFajlova.length));
                        for(String fajl:nizFajlova) {
                                byte[] stringUBajtovima = fajl.getBytes();
                                for(byte jedanBajt:stringUBajtovima) {
                                        osw.write(String.format("%02x", jedanBajt));
                                }
                                //sada je uneto ime fajla
                                osw.write("ff173ab4");//granicnik za kraj imena - kapiram d
                                String relativnaPutanja = direktorijum+separator+fajl;
                                fis = new FileInputStream(relativnaPutanja);
                                int krajNiza = 0;
                                int procitanBajt = 0;
                                while(krajNiza!=1) {
                                        procitanBajt = fis.read();
                                        if (procitanBajt==-1) krajNiza=1;
                                        else {
                                                //pretvaram broj u string oblika hexa sa 2 broja duzine tj sa vodecom 0
                                                String brojUOblikuStringa = String.format("%02x", procitanBajt);
                                                osw.write(brojUOblikuStringa);
                                        }
                                }
                                osw.write("ff173ab4");//granicnik za kraj imena - kapiram da ce ovo biti ok!
                                fis.close();//zatvoriti fajl iz koga sam citao podatke
                        }
                        osw.flush();
                } catch(IOException ex) {//ovde bi trebao neki stack trace tj stampa svih pozvanih funkcija sa steka
                        ex.printStackTrace();//kapiram da ovog nacina prikazivanja greske treba da se drzim
                } finally {
                        if (fis!=null) {
                                try {
                                        fis.close();
                                        System.out.println("Zatvoren fis fajl!");
                                } catch(IOException ex) {
                                        System.out.println("Neuspelo zatvaranje fis fajla!");
                                }
                        }
                        if (osw!=null) {
                                try {
                                        osw.close();
                                        System.out.println("Zatvoren osw fajl!");
                                } catch(IOException ex) {
                                        System.out.println("Neuspelo zatvaranje osw fajla!");
                                }
                        }
                }
        }

}

