//##generalno pravilo za vracanje objekata iz metode (objekat je kreiran u samoj metodi)
//???!!!kada metoda vrati pokazivac na objekat koji je kreiran u samoj metodi to je bezbedno jer se samo reference sa steka brisu ali se sam objekat iz dinamicke memorije ne bris
 //e!!!!!!!!!!da li je dobra praksa da se tako radi???da se objekat koji je formiran u samoj metodi prosledjuje van???smatracu da jeste!!!!!!!!!
//ako hocu da radim sa stream-ovim onda ovako treba da postavim try&catch strukturu
try {
	//referenci na stream dodeljujem objekat
${1:ime} = new ${2:KlasaStreama}(${3:pocetni});
//##kod koji radi sa objektom stream-a
} catch(IOException ex) {//ovde bi trebao neki stack trace tj stampa svih pozvanih funkcija sa steka
		//obrada greske
		ex.printStackTrace();//kapiram da ovog nacina prikazivanja greske treba da se drzim
} finally {
	//u slucaju da nije zatvoren stream odradice se ovaj finally blok
	//ovakav uslov treba da se postavi za svaki otvoren stream
		if (${4:ime}!=null) {
			//a posto se opet moze desiti sa je zatvaranje objekta stream-a neuspesno onda se opet hvata izuzetak
					try {${5:ime}.close();} catch(IOException ex) { System.out.println("Neuspelo zatvaranje ${6:ime} stream!"); }
		}
}
//##rad sa fajlovima
//rad sa tekstualnim fajlovima
//kod teksta cu da kreiram prvo fajl pa sam onda, FileReader jer ne moze da se kreira BufferedReader sa File-om!
//File
//FileReader, FileWriter
//BufferedReader
//citanje
//preporucena struktura
//sa velikom napomenom naravno da sve reference na stream-ove moraju biti definisane van try bloka kako bi se koristile i u catch bloku
//pored toga je moguce (preporuceno ne ostavljati napr FileReader referencu vidljivu)
try {
	//File reads text files in the default encoding - prvo cu kreirati objekat fajl jer preko njega mogu da istrazujem gde se fajl nalazi, 
	File file = new File(fileName);

	// FileReader reads text files in the default encoding - zato sto BufferedReader moze da se kreira sa Reader-om
    FileReader fileReader = new FileReader(file);
	
	// Always wrap File in BufferedReader - It is therefore advisable to wrap a BufferedReader around any Reader whose read() operations may be costly, such as FileReaders and InputStreamReaders.
	BufferedReader bufferedReader = new BufferedReader(fileReader);

	while((line = bufferedReader.readLine()) != null) {
		System.out.println(line);
		//uradi sta hoces sa linijom
}
}
//napomena: postoji reset bufferedreader-a li nije preporucen posto on sluzi da se vratim do poslednjeg postavljenog mark-a a po default-u nije postavljen mark pa ako ga pozovem bacice se exception!!!
//tako da je bolja opcija da se zatvori stream pa da se ponovo otvori!!!!!!!!!
//pisanje
//e sada za upis u tekstualni fajl nema baferovanja
//ali imam dve opcije PrintWriter ili FileWriter
//razlike:
//Although both of these internally uses a FileOutputStream , the main difference is that PrintWriter offers some additional methods for formatting like println and printf.
//Major Differences :
//FileWriter throws IOException in case of any IO failure.
//None of the PrintWriter methods throws IOException, instead they set a boolean flag which can be obtained using checkError().
//PrintWriter automatically invokes flush after every byte of data is written. In case of FileWriter, caller has to take care of invoking flush.
//ukratko FileWriter je bezbedniji zato sto baca izuzetke tako da cu njega koristiti
//preporucena struktura
    File fpw = new File("printwriter.txt");
    File fwp = new File("filewriter.txt");
        PrintWriter pw = new PrintWriter(fpw);//ne baac izuzetke, ne mora da se radi flush rucno i ima opciju sprintf
        FileWriter fw = new FileWriter(fwp);//koristicu najcesce ovu varijantu jer baca izuzetke!
		//nakon sto otvorim fajl - imam opciju da ga otvorim u append modu ili ne to znaci da li ce dase unisti neki fajl koji vec postoji ili ce da se doda na fajl ako fajl vec postoji
		//nakon sto se fajl otvori onda se sa write dodaju redovi
        pw.write("printwriter text\r\n");
        fw.write("filewriter text\r\n");
		//e sada koja je razlka izmedju append-a i write-a:
	//	There are minor differences between append() and write(). All of which you can work out by reading the Javadocs. Hint. ;)
//write will only take a String which must not be null and returns void
//append will take any CharSequence which can be null and return the Writer so it can be chained.
//analogno ide za upis 
//jos jednom, razlika izmedju PrintWriter i FileWriter - ukratko FileWriter se koristi za kalsicne ispise ali ako imam neke specijalne zahteve onda cu koristiti PrintWriter!
//FileWriter is a convenience class for writing character files. The constructors of this class assume that the default character encoding and the default byte-buffer size are acceptable. To specify these values yourself, construct an OutputStreamWriter on a FileOutputStream.
//PrintWriter prints formatted representations of objects to a text-output stream. This class implements all of the print methods found in PrintStream. It does not contain methods for writing raw bytes, for which a program should use unencoded byte streams.
//If you want to write files, basically all the same stuff applies, except you'll deal with classes named FileWriter with BufferedFileWriter for text files, or FileOutputStream for binary files. 
//rad sa obicnim fajlovima - citanje bajt po bajt
//##za map-u cu uzeti HashMap.jedina napomena je da nije synchronized tj ne moze bezbedno da se koristi u multithread aplikacijama!
//## ArrayList cu koristiti umesto nizova. index-i idu od 0!!!!!!!!!!!
new ArrayList<Element>(Arrays.asList(array));//konvertovanje niz u ArrayList
//## kada hocu da koristim Set koristicu HashSet
// sa add dodajem elemente u set a kao povratnu vrednost dobijam da li je elemnt unesen (a bice ako vec ne postoji) - true ili false ako vec element ne postoji!!!!!!
// ne postoji max ili min funkcija pa bih ako mi treba podatka o elementima koji se odnosi na njihov medjusobni odnos morao da generisem Iterator - postoji metoda iterator pa onda nad iteratorom da odradim sta mi je potrebno!!!!!!!
//ne postoji uoste funkcija koja dohvata element iz set-a!!!!jednostavno mozgu samo da procverim da li je neki elemnt deo set-a ili nije!!!!!!!!!!ali je set itearble pa moze da se ide kroz njega sa foreach!
//## Iterator - samo koriscenje iteratora je pravolinijsko. Kada hocu da se krecem kroz elemente kolekcije sa foreach komandom onda je potrebno prvo da kreiram iterator nad kolekcijom
      Iterator itr = al.iterator();
      while(itr.hasNext()) {
         Object element = itr.next();
         System.out.print(element + " ");
      }
//jedine funkcije koje su mipotrebne su (nakon sto dobijem iterator)	hasNext i next!!!!!!!!!!!!!!
//inace svaka class-a koja implementira interface iterable moze da koristi foreach a ako class-a nema onda se moze generisati iterator te class i onda se kretati kroz elemente class-e!
Implementing this interface allows an object to be the target of the "foreach" statement.
//Mada moze da se koristi i sa nizovima!!!!!!!mislim na foreach!!!!!!!!!
int[] niz = new int[10];
//inicijalizacija svih 10 promenljivih -inace su za sada samo postavljeni na 0!!!!!!!!!!
for(int el:niz) {
	//rad nad pojedinacnim elementima niza koji se nalaze u promenljivoj el
}
//##regeex
//kada se radi sa regurnim izrazima pored poznavanja samih pravilnosti kreiranja pattern-a potrebne su i ove dve class-e
Pattern p = Pattern.compile("(mouse|cat|dog|wolf|bear|human)");//ovde se smesta pattern
Matcher m = p.matcher(input);//a ovde tekst u odnosu na koji se radi match-ovanje
//e sada kako se koriste metode u matcher-u.SUSTINA -ZAPAMTITI:
An engine that performs match operations on a character sequence by interpreting a Pattern. A matcher is created from a pattern by invoking the pattern's matcher method. Once created, a matcher can be used to perform three different kinds of match operations:
The matches method attempts to match the entire input sequence against the pattern.
The lookingAt method attempts to match the input sequence, starting at the beginning, against the pattern.
The find method scans the input sequence looking for the next subsequence that matches the pattern.
//u kombinaciji sa find se koristi:
group() - Returns the input subsequence matched by the previous match.
//ii naravno da bi se resetovao matcher:
reset() - Resets this matcher.
//##String class
//postoji kranje logicna metoda za parsiranje String objekta:
split(String regex) - Splits this string around matches of the given regular expression.
//##konvertovanje String-a u Intiger i obratno HEX
//For String to Hexadecimal,
Integer.decode("0x4d2"); // output is 1234
//("0x" should be added (prefix) to String for decoding to Hexadecimal)
//Hexadecimal to String,
Integer.toHexString(1234); // output is 4d2
//##ovde cu da navedem linije koda koje se najcesce koriste!
//konvertovanej String-a u Integer
int foo = Integer.parseInt("1234");
//##cesto pitanje: konvertovanje Array to ArrayList???
new ArrayList<Element>(Arrays.asList(array))
//## abstract class-a construct:
//Yes, an abstract class can have a constructor. Consider this:
abstract class Product { 
    int multiplyBy;
    public Product( int multiplyBy ) {
        this.multiplyBy = multiplyBy;
    }

    public int mutiply(int val) {
       return multiplyBy * val;
    }
}

class TimesTwo extends Product {
    public TimesTwo() {
        super(2);
    }
}

class TimesWhat extends Product {
    public TimesWhat(int what) {
        super(what);
    }
}
//The superclass Product is abstract and has a constructor. The concrete class TimesTwo has a constructor that just hardcodes the value 2. The concrete class TimesWhat has a constructor that allows the caller to specify the value.
//Abstract constructors will frequently be used to enforce class constraints or invariants such as the minimum fields required to setup the class.
//NOTE: As there is no default (or no-arg) constructor in the parent abstract class the constructor used in subclasses must be specified