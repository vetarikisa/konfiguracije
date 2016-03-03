//package chapter2;

/**
 * Class used in book Java cryptography for data presentation.
 */
public class Utils
{
        /**
         * Data used for HEX string generation
         */
        private static String	digits = "0123456789ABCDEF";
        /**
         * Return length many bytes of the passed in byte array as a hex string.
         * 
         * @param data the bytes to be converted.
         * @param length the number of bytes in the data block to be converted.
         * @return a hex representation of length bytes of data.
         */
        public static String toHex(byte[] data, int length)
        {
                StringBuffer	buf = new StringBuffer();

                for (int i = 0; i != length; i++)
                {
                        int	v = data[i] & 0xff;//ne znam iz kog razloga se dodaje ovo odsecanje pocetnih bitova, jer mi se cini da su ovi karakteri koji su mi ovde potrebni identicni ASCII karakterima tj sadrzaj je samo u nizem bajtu

                        //byte[]        input = new byte[] { 0x00, 0x11, 0x22, 0x33, 0x44, 0x55, 0x66, 0x77, (byte)0x88, (byte)0x99, (byte)0xaa, (byte)0xbb,
                        //(byte)0xcc, (byte)0xdd, (byte)0xee, (byte)0xff };
                        //System.out.println(v >> 4);
                        buf.append(digits.charAt(v >> 4));//ne vrsi se izmena same vrednosti v, samo se koristi trenutni rezultat i stampa se ta vrednost
                        //System.out.println(v);
                        buf.append(digits.charAt(v & 0xf));//stampa se niza nibla
                        //System.out.println(v);
                }

                return buf.toString();
        }
        /**
         * Return the passed in byte array as a hex string.
         * 
         * @param data the bytes to be converted.
         * @return a hex representation of data.
         */
        public static String toHex(byte[] data)
        {
                return toHex(data, data.length);
        }
}
