import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;


/**
* <h1>Decrypt/encrypt data (with triple DES algorithm)</h1>
* Decrypting/encrypting data using Bouncy castle.
* <p>
* <b>Note:</b> when first parametar is 1 encrypting is performed
* when it is 0 then decrypting is performed.
* <b>Note:</b> OStavio sam polja i metode public cisto zbog preglednosti u javadoc-u!
*
* @author  Vlastimir Djokic
* @version 1.0
* @since   2016-02-26
*/
public class Crypto {   
        //private static String TRIPLE_DES_TRANSFORMATION = "DESede/ECB/PKCS7Padding";
        /**
         * Does we use ing or not (no in this use).
         */
        private static String TRIPLE_DES_TRANSFORMATION = "DESede/ECB/NoPadding";//ipak sam iskjlucio padding jer mi trenutno ne treba (radim sa celim blokovima) a i ne mogu znati koji je padding koriscen za samo kriptovanje!!!!!!!!!!!!!!
        /**
         * Which algorithm is used (Triple DES).
         */
        private static String ALGORITHM = "DESede";
        /**
         * Provider which is used (BC - Bouncy Castle).
         */
        private static String BOUNCY_CASTLE_PROVIDER = "BC";
        /**
         * This method for convert HEX representation of data to byte array.
         * @param s converting HEX string (HEX representation of data)
         * @return array of bytes
         */
        public static byte[] hexStringToByteArray(String s) {
                int len = s.length();
                //System.out.println("len : " + len);
                byte[] data = new byte[len / 2];
                for (int i = 0; i < len; i += 2) {
                        //System.out.println("i : " + i);
                        data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i+1), 16));
                }
                return data;
        }
        /**
         * This method is used to decrypt/encrypt data.
         * @param command 0 - decrypt, 1 - encrypt
         * @param data  This is data have to be decrypted/encrypted
         * @param inputKey  Key for decrypted/encrypted
         * @exception Exception On input error.
         * @see Exception
         * @return String Decrypted/encrypted value
         */
        public String encrypt(String command, String data, String inputKey) throws Exception {
                byte[] input = hexStringToByteArray(data);
                byte[] secret = hexStringToByteArray(inputKey);
                String ret = new String();
                //staviti ovde da se baca exception ako su lose duzine kljuceva!!!!!!!!!!!!!
                System.out.println("input : " + data);
                System.out.println("key   : " + inputKey);
                //kreiram kljuc
                SecretKeySpec key = new SecretKeySpec(secret, ALGORITHM);
                //sada kreiram objekat za samo kriptovanje/dekriptovanje a kao drugi argument navodim provajder koji cu da koristim - u ovom slucaju to je bc tj bouncy castle!!!!!!!!!!!!!!!!!!!!!!
                Cipher        cipher = Cipher.getInstance(TRIPLE_DES_TRANSFORMATION, BOUNCY_CASTLE_PROVIDER);
                //system.out.println("input text : " + utils.tohex(input));
                // encryption pass - videcu jos oko ove duzine niza???ne znam zasto duzina morada bude24???
                byte[] ciphertext = new byte[input.length];//za odredjeni niz bajtova kreirace se isti toliki niz bajtova kriptovanog teksta!!!!!!!!
                if(command.equals("1")) {
                        cipher.init(cipher.ENCRYPT_MODE, key);//inicijalizujem objekat za sifrovanje tako sto navodim mod u kome cuda radim i kljuc sa kojim cu da radim!!!!!!!!!!!!!!!!!!!!!!!!!!
                        int ctlength = cipher.update(input, 0, input.length, ciphertext, 0);//
                        ctlength += cipher.doFinal(ciphertext, ctlength);
                        ret = Utils.toHex(ciphertext);
                        System.out.println("cipher: " + ret);
                } else {
                        cipher.init(cipher.DECRYPT_MODE, key);//postavi se u mod za dekriptovanje
                        //int x = cipher.getblocksize();
                        //system.out.println("cipher.getblocksize : " + x);
                        //byte[] plaintext = new byte[ctlength];
                        byte[] plaintext = new byte[input.length];
                        int ptlength = cipher.update(input, 0, input.length, plaintext, 0);
                        //system.out.println("input.length : " + input.length);
                        //system.out.println("ptlength : " + ptlength);
                        ptlength += cipher.doFinal(plaintext, ptlength);
                        ret = Utils.toHex(plaintext);
                        System.out.println("plain : " + ret);
                }
                return ret;
                // decryption pass
                //System.out.println("plain text : " + Utils.toHex(plainText) + " bytes: " + ptLength);
        }
        /**
         * This is the main method which makes use of encrypt method.
         * @param args Accept 3 arguments. First is 0 - decrypt, 1 - encrypt, second is data for encrypting, third is key which have to be used.
         * @exception Exception On input error.
         * @see Exception
         */
        public static void main(String[] args) throws Exception {
                Crypto c = new Crypto();
                c.encrypt(args[0], args[1], args[2]);
        }
} 
