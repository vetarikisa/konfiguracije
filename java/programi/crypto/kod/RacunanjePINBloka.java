import javax.crypto.Cipher;
import java.util.ArrayList;
import java.util.Iterator;
import javax.crypto.spec.SecretKeySpec;


/**
* <h1>PIN block creation.</h1>
* This class create PIN block. Data for PIN block creation
* is PAN (without LRC), PIN, session key (encrypted with 
* master key) and clear master key.
* <p>
* <b>Note:</b>  It is feasible to change code to accept PAN with LRC.
* cini mi se da je potrebno izmeniti 2 linije koda...
* <p>
* <b>Note:</b>  Example:
* <ul>
* <li>PAN 00 00 33 31 08 90 10 44
* <li>PIN 1234
* <li>encrypted session "\xB4\xAB\xA2\xBB\x79\x1C\x50\xE7\xB4\xAB\xA2\xBB\x79\x1C\x50\xE7"
* <li>clear master 0...FF...0
* </ul>
* Calculated PIN block B3 FD 90 90 05 1A B9 FE.
* @author  Vlastimir Djokic
* @version 1.0
* @since   2016-02-29   
*/
public class RacunanjePINBloka {
        /**
         * Converting HEX string to byte array.
         * @param in HEX String.
         * @return byte Array
         */
        public static byte[] convertHexStringtoByteArray(String in) {
                String ref = "0123456789";//resicu problme slicno kako je resen u Utils klasi
                int len = in.length();
                int i = 0;
                ArrayList<Integer> al = new ArrayList<Integer>();
                //System.out.println(len);
                //System.out.println(in);
                for (i = 0;i<len;i++) {
                        //sa pozicije i u ulaznom stringu uzmi karakter i onda mi vrati index tog karakera iz niza ref - cime dobija vrednost bajta za niz koji cuda vratim
                        //System.out.println(ref.indexOf(in.charAt(i)));
                        al.add(ref.indexOf(in.charAt(i)));
                }
                //System.out.println(al);
                //sada imam ArrayList INteger-a a treba mi niz bajtova
                len = al.size();
                byte[] out = new byte[len];
                Iterator<Integer> itr = al.iterator();
                i = 0;
                Integer intg = new Integer(0);
                //konvertovati niz Integer-a u niz bajtova
                while(itr.hasNext()) {
                        intg = itr.next();
                        out[i++] = intg.byteValue();
                }
                return out;
        }
        /**
         * Main method.
         * @param args PAN (without LRC), PIN, encrypted session, clear master.
         * @exception Exception vidi Exception
         * @see Exception
         */
        public static void main (String[] args) throws Exception {
                byte[] temp = new byte[16];//ovdecu trenutno da smestam povratne vrednosti funkcije za konvertovanje
                //od PAN-a uzeti poslednjih 12 cifara - jedan bajt jedna cifra; a prve 4 cifre postaviti na 0
                int len = args[1].length();//prvo uzimam duzinu ulaznog PAN-a
                //System.out.println(args[1]);
                int start = len - 12;//od ovog index-a treba da prevedem PAN u niz bajtova
                //iskljucuje se poslednja cifra iz PAN-a!!!!!!!!!!!LRC ili tako nesto
                //String strPan = args[1].substring(start-1, len-1);//verzija kada nije skinut lrc sa PAN-a - LRC
                String strPan = args[1].substring(start-1, len);//ovaj pan je potrebno konvertovati u niz bajtova
                //System.out.println("PAN: "+strPan);
                temp = convertHexStringtoByteArray(strPan);
                byte[] pan = {0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00};
                //System.arraycopy( src, 0, dest, 0, src.length );
                //source array, start index, destination array, start index, number of bytes for copying
                System.arraycopy( temp, 0, pan, start-1, temp.length );//ovaj temp.length bi trebalo da bude 12
                //System.arraycopy( temp, 0, pan, 4, temp.length );//verzija kada nije skinut lrc sa PAN-a - LRC
                //konvertovati ovaj string u niz bajtova 
                //zatim uzeti PIN, njegovu duzinu;formirati niz od 16 bajtova gde je priv 0, drugi duzina PIN-a, zatim ide sam PIN i onda F-ovi do 16-og bajta
                byte[] pin = {0x00,(byte)0xff,(byte)0x0f,(byte)0x0f,(byte)0x0f,(byte)0x0f,(byte)0x0f,(byte)0x0f,(byte)0x0f,(byte)0x0f,(byte)0x0f,(byte)0x0f,(byte)0x0f,(byte)0x0f,(byte)0x0f,(byte)0x0f};
                String strPin = args[0];//ovaj pin je potrebno konvertovati u niz bajtova
                pin[1] = (byte)strPin.length();//smesti duzinu u drugi bajt
                temp = convertHexStringtoByteArray(strPin);
                System.arraycopy( temp, 0, pin, 2, strPin.length());//ovaj temp.length bi trebalo da bude 12
                //zatim XOR-ovati ovadva niza od 16 bajtova
                byte[] clearPINBlok = new byte[16];
                //System.out.println("PAN PIN XOR:");
                String ret = new String();
                for(int i=0;i<16;i++) {
                        clearPINBlok[i] = (byte)((byte)pan[i] ^ (byte)pin[i]);
                        //ret = String.format("%2x %2x %2x.",pan[i] ,pin[i], clearPINBlok[i]);
                        //System.out.println(ret);
                }
                //clear oblik PIN blok-a je 8 bajtova (kao i kriptovani)
                byte[] pinBlok = {0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00};
                {
                        int j = 0;
                        for(int i=0;i<16;i+=2,j++) {
                                pinBlok[j]=(byte)((byte)(clearPINBlok[i]<<4)|(byte)clearPINBlok[i+1]);        
                        }
                }
                //dodato naknadno kako bi se resio problem kreiranja PIN blok-a
                String strClearPINBlok = Utils.toHex(pinBlok);
                System.out.println("Clear PIN blok: " + strClearPINBlok);//cini mi se da definitivno dobijam dobar cleatr PIN blok
                //IPAK CU OVDE STATI PA CU SUTRA SMESTITI KLASU ZA KRIPTOVANJE KAKO TREBA DA BIH MOGA ODAVDE DA JE POZOVEM!!!!!!!
                Crypto c = new Crypto();
                String clSession = c.encrypt("0", args[2], args[3]);
                //dekriptovati sesijski kljuc clear oblikom master kljuca - ZA SADA CU KORISTITI CLEAR OBLIK SESIJSKOG KLJUCA KAKO BIH PROVERIO DA LI OVO DO SADA RADI!!!!!!!!!!!!
                //nakon toga kriptovati ovaj podatak sa clear oblikom sesijskog kljuca 
                c = new Crypto();
                String PINBlok = c.encrypt("1", strClearPINBlok, clSession);
                //String ret = Utils.toHex(PINBlok);//u slucaju da zelim da vratim i ovu vrednost
                System.out.println("PIN blok : " + PINBlok);
        }
}
