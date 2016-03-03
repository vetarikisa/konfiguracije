@echo off
cd\
d:
rem lokacija programa ako se program i bat ne nalaze u istom folderu
cd d:\Projekti\proveraKljuceva\crypto\
For /f "tokens=2-4 delims=/ " %%a in ('date /t') do (set mydate=%%c-%%a-%%b)
For /f "tokens=1-2 delims=/:" %%a in ('time /t') do (set mytime=%%a:%%b)
echo %mydate% %mytime% >> log.txt
rem donji echo odkomentarisati i uneti komentar unos-a u log
echo KCV naseg master kljuca kripotvanog transoprtnim >> log.txt
rem potrebno je naravno da dodam argumente na ovaj poziv
rem java Crypto 1 dataForEncrypt key
rem java Crypto 0 dataForDecrypt key
java Crypto 1 00000000000000000000000000000000 7BE3DE06DCC7B868093B966174866441 >> log.txt
rem java Crypto 0 B3FD9090051AB9FE 22222222222222222222222222222222 >> log.txt
pause
rem Exception while decrypting data org.bouncycastle.crypto.InvalidCipherTextException: pad block corrup - dogadja se ako pokusavam da dekriptujem podatak kljucem kojim nije kriptovan!!!!!!!!!!!!!
rem 7BE3DE06DCC7B868093B966174866441 - kriptovan master kljuc nasim transpornim
rem D52583BA2610192CF4A74351C79E7389 - clear master kljuc
rem AD9229BCF8E68CA2297A022386C8B964 - clear trasnportni
rem d81194228b0c61d7f710a79314d978d0 - sesijski kriptovan master kljucem
rem CC0AB97BBB9914F0FD6060673051649C - clear sesijski
rem 0123456789ABCDEFFEDCBA9876543210
rem SET CRYPT_SESSION="B4ABA2BB791C50E7B4ABA2BB791C50E7"
rem SET CLR_MASTER="0123456789ABCDEFFEDCBA9876543210"
rem crypted PIN block "B3FD9090051AB9FE"
