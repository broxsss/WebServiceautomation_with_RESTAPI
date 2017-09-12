set mydir=%~dp0

Powershell -Command "& { Start-Process \"%mydir%b.bat\" -verb RunAs}"