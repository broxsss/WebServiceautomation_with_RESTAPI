
@echo off
REM Next command opens Microsoft Word
start "C:\Program Files\MongoDB\Server\3.4\bin" mongod.exe
timeout /t 5
start  "C:\Program Files\MongoDB\Server\3.4\bin" mongo.exe
timeout /t 5
start /d "C:\Users\aksaini\Downloads\elasticsearch-5.5.2\elasticsearch-5.5.2\bin" elasticsearch.bat
timeout /t 5
sc start mysql57
