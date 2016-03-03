@echo off
cd\
d:
cd d:\Projekti\proveraKljuceva\crypto\
For /f "tokens=2-4 delims=/ " %%a in ('date /t') do (set mydate=%%c-%%a-%%b)
For /f "tokens=1-2 delims=/:" %%a in ('time /t') do (set mytime=%%a:%%b)
echo %mydate% %mytime% >> log.txt
rem //byte *key = "\xB4\xAB\xA2\xBB\x79\x1C\x50\xE7\xB4\xAB\xA2\xBB\x79\x1C\x50\xE7"; // 22..22 encrypted with 0-FF-0
SET PIN="1234"
rem donji PAN je sa lrc na kraju - da bi program radio sa lrc potrebno je otkomentarisati (i zakomentarisati analognu liniju) sa LRC tagom
rem SET PAN="43219876543210987"
rem donji PAN je bez lrc na kraju - tako je trenutno kompajliarn program
SET PAN="0000333008901044"
SET CRYPT_SESSION="B4ABA2BB791C50E7B4ABA2BB791C50E7"
SET CLR_MASTER="0123456789ABCDEFFEDCBA9876543210"
rem donji echo odkomentarisati i uneti komentar unos-a u log
echo PIN blok za PIN %PIN%, PAN(bez lrc-a)  %PAN%, kriptovani sesijski %CRYPT_SESSION%, clear master %CLR_MASTER% >> log.txt
rem echo PIN blok za PIN %PIN%, PAN(sa lrc-om)  %PAN%, kriptovani sesijski %CRYPT_SESSION%, clear master %CLR_MASTER% >> log.txt
rem potrebno je naravno da dodam argumente na ovaj poziv
java RacunanjePINBloka %PIN% %PAN% %CRYPT_SESSION% %CLR_MASTER% >> log.txt
pause
rem For ISO format 0 and 3, the clear-text PIN block and the Primary Account Number block must be XOR'ed together and then Triple-DES encrypted in electronic code book (ECB) mode to form the 64-bit output cipher block (the reversibly encrypted PIN block)
