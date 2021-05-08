call runcrud
if "%ERRORLEVEL%" == "0" goto startweb
echo.
echo RUNCRUD has errors-breaking work
goto fail


:startweb
echo off
START "google" "C:\Program Files (x86)\Google\Chrome\Application\chrome.exe"
if "%ERRORLEVEL%" == "0" goto tasklist
echo.
goto fail

:tasklist
START "tasks" "http://localhost:8080/crud/v1/task/getTasks"
if "%ERRORLEVEL%" == "0" goto end
echo.
goto fail


:fail
echo.
echo There were errors

:end
echo.
echo Work is finished