@echo off
rem ovaj batch fajl ce da pokrece pdf dokumente koji su bili otvoreni kada sam ugasio racunar - za sada cu morati rucno da ga pravim (ovaj bat fajl)
rem <Acrobat path> /A "<parameter>=<value>" "<PDF path>"
rem treba imati u vidu da su ove stranice tako organizovane da je prva stranica stranica 1 i tako dalje tj one i, ii, ii nisu uracunate
rem savrseno - start pokrece program sa odgovarajucim parametrima a bez starta bi mi stao odmah posle otvaranja jednog fajla!!!!!!!!!!!!!!!!!!
rem pokretanje pdf fajlova
start AcroRd32.exe /n/h /A "page=749" "d:\Projekti\java\knjige\Thinking in Java 4th Ed.pdf"
start AcroRd32.exe /n/h /A "page=17" "d:\Projekti\java\knjige\Java Generics and Collections.pdf"
start AcroRd32.exe /n/h /A "page=221" "d:\Projekti\vim\Hacking.Vim.7.2.pdf"
start AcroRd32.exe /n/h /A "page=88" "d:\Projekti\vim\Practical-Vim-Edit-Text-at-the-Speed-of-Thought.pdf"
start AcroRd32.exe /n/h /A "page=108" "d:\Projekti\XGD-ADS\ads-1.08\xgd-ads-1.08\doc\ads_adi_reference.pdf"
rem AcroRd32.exe /n/h "d:\Projekti\java\knjige\Thinking in Java 4th Ed.pdf"
rem AcroRd32.exe /n/h "d:\Projekti\java\knjige\Thinking in Java 4th Ed.pdf" "d:\Projekti\java\knjige\Java Generics and Collections.pdf" "d:\Projekti\vim\Hacking.Vim.7.2.pdf" "d:\Projekti\vim\Practical-Vim-Edit-Text-at-the-Speed-of-Thought.pdf" "d:\Projekti\XGD-ADS\ads-1.08\xgd-ads-1.08\doc\ads_adi_reference.pdf"
rem startovanje sametime-a - cini mi se da ovaj poziv nije ok tj cini mi se da ne poziva ovaj argument kako treba...
rem start C:\Lotus\Notes\notes.exe "=C:\Lotus\Notes\notes.ini"
rem  "=C:\Lotus\Notes\notes.ini"
rem startovanje IngeDev-a -VELIKA NAPOMENA!!!!!!!!!MORAO SAM PROGRAM FILES DA STAVIM POD ZNAKE NAVODNIKA ALI NISAM SMEO DA STAVIM CEO PATH POD ZNAKE NAVODNIKA
start C:\"Program Files"\INGENICO\IngeDev_7\IngeDev.exe
rem startoavnje gVim-a
start c:\Users\vlastimir\vim\vim74\gvim.exe
rem startovanje chrome-a sa odgovarajucim sajtovima
start chrome "https://translate.google.com/?hl=sr&tab=TT" "http://www.b92.net/sport/" "http://www.oreilly.com/openbook/make3/book/ch01.pdf" "https://www.google.rs/webhp?source=search_app&gws_rd=cr,ssl&ei=ffClVciPMYmwsAGhoIfIDA" "http://www.cplusplus.com/" "http://www.howtogeek.com/t/features/page/37/"
rem otvoricu i komandnu liniju za pokretanje skripta za punjenje fles-a sa aplikacijmo za XGD
start cmd.exe /k "cd d:\Projekti\XGD-ADS\tools\UDiskDL2.0\UDiskDL2.0
rem jedan prozor onako otvoren
start cmd.exe /k "cd\"
start cmd.exe /k "c:"
rem pokretanje total komandera
start C:\totalcmd\TOTALCMD.EXE
rem startovanje notped-a
start C:\"Program Files"\Notepad++\notepad++.exe
rem startovanje virtuelne masine koja se koristi za rad na kompajliranju XGD koda
start C:\"Program Files"\Oracle\VirtualBox\VirtualBox.exe
rem startovanje teraterm-a
start C:\"Program Files"\teraterm\ttermpro.exe
rem cini mi se da nema smisla ovaj poslednji exit pa cu ga zakomentarisati
rem exit