@echo off
REM nece moci ova jbat fajl posto kada telnet preuzme komandu nad terminalom nisam siguran da ce da prihvati moju komandu tj nisam uspeo da proguram ovu komandu. Ali neka ga ovaj bat fajl kako bih znao koriscenje telnet-a. Kada se unese sajt i port 80 onda se samo unese GET komanda i enter se lupi 2 puta i onda ce se log smestiti u fajl koji sam naveo posle -f
REM kao prvi argument se navodi sajt (pocev sa www)
SET SAJT="www.b92.net"
REM kao drugi argument se navodi fajl u koji ce da se smesta log komunikacije (pocev sa www)
SET LOG_FAJL=".\CMD_HTTP_logs.txt"
cd/
c:
cd c:\Users\vlastimir\Desktop\
REM sa -f se zadaje fajl gde ce da se cuva log
telnet %SAJT% 80 -f %LOG_FAJL%
GET /index.html HTTP/1.0
pause
REM https://radware1.novabanka.com:9080/servlet/TransactionDispatcherSingleISO?isomsg=08002000010000C000009900000123353939393030305441524955532D54455354205452474F037B

REM GET /servlet/TransactionDispatcherSingleISO isomsg=08002000010000C000009900000123353939393030305441524955532D54455354205452474F037B

rem listanje task-ova se dobija komadnom tasklist a gasenje taskkill
rem gasnje preko imena TASKKILL /F /IM cmd.exe a preko PID-a TASKKILL /F /PID pid_number  - a ovo f je da me nista ne pita; plus valjalo bi     
rem /T                     Terminates the specified process and any child processes which were started by it.