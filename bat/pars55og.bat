@echo off
cd\
d:
cd d:\Projekti\java\pareser55\program\
For /f "tokens=2-4 delims=/ " %%a in ('date /t') do (set mydate=%%c-%%a-%%b)
For /f "tokens=1-2 delims=/:" %%a in ('time /t') do (set mytime=%%a:%%b)
echo %mydate% %mytime% >> log.txt
rem donji echo odkomentarisati i uneti komentar unos-a u log
echo U kojoj situaciji je parsitana ova poruka... >> log.txt
rem kao argument programu se prosledjuje isti niz koji se prosleduje regularnom programu za parsiranje svih polja
java pars55og poruka_koja_se_salje_parseru >> log.txt

