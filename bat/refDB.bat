@echo off
cd\
d:
rem ovo je lokacija naseg projekta
rem cd d:\Ingenico\Projects\IngeDev7\
rem ovo je lokacija curl projekta (kod u kome je ralizovano koriscenje komunikacija u C-u)
cd d:\Projekti\XGD-ADS\curl\
rem ako postoji ovaj dfajl brisi ga
IF EXIST tags (
del /q tags
) 
rem ako postoji ovaj fajl brisi ga
IF EXIST cscope.out (
del /q cscope.out
) 
rem moguece je postaviti i else deo
rem ELSE (
REM Do another thing
rem )
rem ctags -R .
rem bice da gornja komanda ne radi kreiranje tags fajla sa svim podfolderima (tj ne radi rekurzivno) ali sa * radi...e sada...kako je ranije radilo nisam siguran...tj cini mi se da ipak nisam ranije isao preko ctags-a nego preko csope-a...
rem posto mi je na kraju i * pravila probleme jednostavno sam samo posavio -R!!!!!!!!!!!!!i ovo sada radi!!!!!!!!!!a kako je jedno vreme radilo sa . a onda jedno vreme sa * nemam pojma!!!!!!!!!!!!
ctags -R
rem trenutno cu da koristim ovakvo kreiranje baze podataka ali ako budem zeleo da radim detaljnije tj neki komplikovaniji projekat da importujem u DB onda bih prvo morao kreiram files fajl gde se nalaze svi fajlovi koje zelim da tagujem pa se u odnosu na njih kreira DB za skakanje;inace ovaj cscope radi kod c like languages tj c, c++ i java-u!!!!!!!!!!!!!!!
cscope -bqR

rem ZANIMLJIVO:
REM sa tasklist se izlistavaju novi procesi
REM taskkill /pid 1230 /pid 1241 /pid 1253  - ubija procese sa pid-om 1230, 1241, 1253
REM findstr "computer help" myfile.txt - In the above example, any lines containing "computer help" would be printed to the screen.
REM ono sto meni terba je findstr "cscope.exe" myfile.txt - myfile.txt je fajl u koji sam smestio sve procitane procese
REM findstr /s "computer help" *.txt - Similar to the first example, the above example would find any lines containing "computer help" in any txt file in the current directory and all sub directories.
REM 