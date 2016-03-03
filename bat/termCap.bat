@echo off

rem pokrece dokument sa stranicom gde su definisani terminal capabilities, additional terminal capabilities (a ispod su i authorization response code-ovi)
start AcroRd32.exe /n/h /A "page=128" "d:\Projekti\EMV_Visa_MasterCard\EMV\EMV v4.1 Book 4 Other Interfaces.pdf"
rem CVM results
rem start AcroRd32.exe /n/h /A "page=221" "d:\Projekti\EMV_Visa_MasterCard\EMV\EMV v4.1 Book 3 Application Specification.pdf"
rem MasterCard proprietary elements
rem start AcroRd32.exe /n/h /A "page=168" "d:\Projekti\EMV_Visa_MasterCard\EMV\9F10\icc_card_MasterCard.pdf"
rem Visa proprietary elements
rem start AcroRd32.exe /n/h /A "page=112" "d:\Projekti\EMV_Visa_MasterCard\EMV\9F10\ICC_Card_Visa.pdf"
rem Terminal Type
rem start AcroRd32.exe /n/h /A "page=127" "d:\Projekti\EMV_Visa_MasterCard\EMV\EMV v4.1 Book 4 Other Interfaces.pdf"
