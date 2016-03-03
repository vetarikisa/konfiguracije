import java.io.*;
import java.util.*;


//ff173ab4 je niz koji je granicnik; decimalno 255, 23, 58, 180
public class Dekonvertor {
	private static int drugiBroj = 0;
	private static int treciBroj = 0;
	private static int cetvrtiBroj = 0;
	private static int flegDrugi = 0;
	private static int flegTreci = 0;
	private static int flegCetvrti = 0;
	
	//konverzija UTF-8 karaktera u decimalni broj
        public static int charToDecimal(int karakter) {
                if(karakter<58) return karakter-48;//0, 1, 2...9
                else return karakter-87;//vracam vrednost a, b, c, d, e, f - 97 decimalno je a!!!!!!!
        }
        //citaj jedan bajt
        public static int citajJedanBajt(InputStreamReader isr) throws IOException{
    		int teziKarakter = isr.read();//citam po jedan karakter, vraca se -1 kada se dodje do kraja stream-a
                int laksiKarakter = isr.read();//citam po jedan karakter, vraca se -1 kada se dodje do kraja stream-a
                int rezultat = charToDecimal(laksiKarakter)+(16*charToDecimal(teziKarakter));//kapiram da je ovo ok!
                return rezultat;
        }
        public static int daLiSamNaisaoNaGranicnik(InputStreamReader isr) throws IOException{
                //pitam da li je drugi broj 23
                int broj = citajJedanBajt(isr);
                drugiBroj = broj;
                flegDrugi++;
                if (broj!=23) {
                	return 255;//ako niti jedan od ovih uslova nije ispunjen vrati 255!
               	}
                //pitam da li je treci broj 58
                broj = citajJedanBajt(isr);
                treciBroj = broj;
                flegTreci++;
                if (broj!=58) {
                	return 255;//ako niti jedan od ovih uslova nije ispunjen vrati 255!
                }
                //pitam da li je cetvrti broj 180
                broj = citajJedanBajt(isr);
                cetvrtiBroj = broj;
                flegCetvrti++;
                if (broj!=180) {
                	return 255;//ako niti jedan od ovih uslova nije ispunjen vrati 255!
                }
                drugiBroj = 0;
		treciBroj = 0;
		cetvrtiBroj = 0;
		flegDrugi = 0;
		flegTreci = 0;
		flegCetvrti = 0;
        	return -1;//ako niti jedan od ovih uslova nije ispunjen vrati 255!
        }
        //vracam -1 ako sam naisao na granicnik
        private static int citaj(InputStreamReader isr) throws IOException{
                if (flegDrugi!=0) {
                	flegDrugi--;
                	int povratnaVrednost = drugiBroj;
                	drugiBroj = 0;
                	return povratnaVrednost;//ako niti jedan od ovih uslova nije ispunjen vrati 255!
               	}
                //pitam da li je treci broj 58
                if (flegTreci!=0) {
                	flegTreci--;
                	int povratnaVrednost = treciBroj;
                	//System.out.println(treciBroj);
                	treciBroj = 0;
                	return povratnaVrednost;//ako niti jedan od ovih uslova nije ispunjen vrati 255!
               	}
                //pitam da li je cetvrti broj 180
                if (flegCetvrti!=0) {
                	flegCetvrti--;
                	int povratnaVrednost = cetvrtiBroj;
                	cetvrtiBroj = 0;
                	return povratnaVrednost;//ako niti jedan od ovih uslova nije ispunjen vrati 255!
               	}
                int rezultat = citajJedanBajt(isr);
                if (rezultat == 255) rezultat = daLiSamNaisaoNaGranicnik(isr);
                return rezultat;
        }
        public static void main (String[] args) {
                String imeFolderaGdeCuDaSmesitimFajlove = args[0];
                File dekonvertujOvajFajl = new File("soping_lista.txt");
                File napraviFajlSaOvimImenom = null;
                FileInputStream fis = null;
                InputStreamReader isr = null;
                FileOutputStream fos = null;//ovaj stream je ok - uvek cu ga iznova otvarati kada treba da kreiram fajl
                try {
                        fis = new FileInputStream(dekonvertujOvajFajl);
                        isr = new InputStreamReader(fis);
                        int brojFajlova = citaj(isr);//prvo citam broj fajlova koje treba da dekonvertujem
                        for(byte i = 0;i<brojFajlova;i++) {//sada cu da 'izvucem' jedan po jedan fajl
                                //citam ime fajl (dok ne naidjem na ff)
                                int slovo = 0;
                                ArrayList<Integer> imeFajla = new ArrayList<Integer>();
                                slovo = citaj(isr);
                                while (slovo!=-1) {//ako se naidje na granicnik
                                        Integer slovoObjekat = new Integer(slovo);
                                        imeFajla.add(slovoObjekat);
                                        slovo = citaj(isr);
                                }
                                //moracu ipak da alociram niz i da ga onda popunim
                                byte[] imeFajlaUOblikuNiza = new byte[imeFajla.size()];
                                int j = 0;
                                for(Integer jednoSlovo: imeFajla) {
                                        imeFajlaUOblikuNiza[j++] = jednoSlovo.byteValue();//kapiram da cu ovde od Integer objekta dobiti byte vrednost
                                }
                                String iKonacnoImeFajla = new String(imeFajlaUOblikuNiza, "utf-8");//ako do ovde prodjem gotov posao!
                                System.out.println(iKonacnoImeFajla);
                               // String iKonacnoImeFajla = new String(imeFajlaUOblikuNiza);//ako do ovde prodjem gotov posao!
                                napraviFajlSaOvimImenom = new File(iKonacnoImeFajla);//kapiram da ce ovo raditi
                                fos = new FileOutputStream(napraviFajlSaOvimImenom);
                                slovo = citaj(isr);
                                while (slovo!=-1) {
                                        fos.write(slovo);//i definitivno unosim ovaj bajt u fajl!!!!!!!!!!
                                        slovo = citaj(isr);
                                }
                                fos.flush();
                                fos.close();//zatvaram fajl u koji sam upisivao
                        }
                } catch (IOException ex) { 
                        ex.printStackTrace();
                } finally {
                        if (isr!=null){
                                try {
                                        isr.close();
                                        System.out.println("Zatvorio sam isr!");
                                } catch (IOException ex) {
                                }
                        }
                        if (fos!=null){
                                try {
                                        fos.close();
                                        System.out.println("Zatvorio sam fos!");
                                } catch (IOException ex) {
                                }
                        }
                }
        }
}
