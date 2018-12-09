@echo off
rem Execute the Antlr compiler/generator tool

SETLOCAL
set STARTTIME=%TIME%

rem 

rem workspace root
set root=D:\DevFiles\Eclipse\DslEditors\net.certiv.xvisitordt

rem project 
set proj=net.certiv.xvisitordt.core

rem lib jars directory:
set jlib=D:\DevFiles\Eclipse\Dsl\net.certiv.dsl\net.certiv.dsl.lib

rem grammars gen directory:
set src=%root%\%proj%\src\main\java\net\certiv\xvisitordt\core\parser

rem generated class bin
set bin=%root%\%proj%\target\classes

set antlrjar=%jlib%\antlr4-4.7.1.jar
set arntmjar=%jlib%\antlr4-runtime-4.7.1.jar
set arnt3jar=%jlib%\antlr-runtime-3.5.2.jar
set sttmpjar=%jlib%\ST4-4.0.8.jar
set xvisitor=%jlib%\xvisitor-4.7.jar
set log4japi=%jlib%\log4j-api.jar
set log4jcor=%jlib%\log4j-core.jar


set CLASSPATH=%arnt3jar%;%arntmjar%;%sttmpjar%;%antlrjar%;%xvisitor%;%log4japi%;%log4jcor%;%bin%;%CLASSPATH%

echo %src%
cd /d %src%

java org.antlr.v4.Tool  -visitor -o gen XVisitorLexer.g4 XVisitorParser.g4
echo Grammars generated

java net.certiv.antlr.xvisitor.Tool -v warn -o gen Structure.xv Validity.xv
echo XVisitors generated

set ENDTIME=%TIME%
set /A STARTTIME=(1%STARTTIME:~6,2%-100)*100 + (1%STARTTIME:~9,2%-100)
set /A ENDTIME=(1%ENDTIME:~6,2%-100)*100 + (1%ENDTIME:~9,2%-100)

if %ENDTIME% LSS %STARTTIME% (
	set /A DURATION=%STARTTIME%-%ENDTIME%
) else (
	set /A DURATION=%ENDTIME%-%STARTTIME%
)

set /A SECS=%DURATION% / 100
set /A REMAINDER=%DURATION% %% 100
echo %SECS%.%REMAINDER% s

ENDLOCAL

timeout 6
