@echo off
REM ===================================================
REM  Quick Start Script for Google OAuth2 Testing
REM  
REM  BEFORE RUNNING: Set your Google credentials below
REM ===================================================

REM ========== EDIT THESE VALUES ==========
REM Get your credentials from: https://console.cloud.google.com/apis/credentials
set GOOGLE_CLIENT_ID=YOUR_CLIENT_ID_HERE.apps.googleusercontent.com
set GOOGLE_CLIENT_SECRET=YOUR_CLIENT_SECRET_HERE
REM =======================================

echo.
echo ========================================
echo  Starting Sorting Visualizer Backend
echo  with Google OAuth2 enabled
echo ========================================
echo.

if "%GOOGLE_CLIENT_ID%"=="YOUR_CLIENT_ID_HERE.apps.googleusercontent.com" (
    echo [WARNING] You need to set your Google credentials first!
    echo.
    echo Steps:
    echo 1. Go to https://console.cloud.google.com/
    echo 2. Create a new project or select existing
    echo 3. Go to APIs and Services -^> Credentials
    echo 4. Create OAuth 2.0 Client ID ^(Web application^)
    echo 5. Add redirect URI: http://localhost:8080/login/oauth2/code/google
    echo 6. Copy Client ID and Client Secret
    echo 7. Edit this file and replace the values above
    echo.
    pause
    exit /b 1
)

echo Using Client ID: %GOOGLE_CLIENT_ID:~0,30%...
echo.

cd /d "%~dp0"
mvn spring-boot:run
