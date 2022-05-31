@ECHO OFF

rem Nayanika is a picture viewer application.
rem Copyright (C) 2022  Sukma Wardana
rem
rem This program is free software: you can redistribute it and/or modify
rem it under the terms of the GNU General Public License as published by
rem the Free Software Foundation, either version 3 of the License, or
rem (at your option) any later version.
rem
rem This program is distributed in the hope that it will be useful,
rem but WITHOUT ANY WARRANTY; without even the implied warranty of
rem MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
rem GNU General Public License for more details.
rem
rem You should have received a copy of the GNU General Public License
rem along with this program.  If not, see <https://www.gnu.org/licenses/>.

rem ------ ENVIRONMENT --------------------------------------------------------
rem The script depends on various environment variables to exist in order to
rem run properly. The java version we want to use, the location of the java
rem binaries (java home), and the project version as defined inside the pom.xml
rem file, e.g. 1.0-SNAPSHOT.

set JAVA_VERSION=17
set APP_VERSION=1.0.001

echo java version: %JAVA_VERSION%
echo java home: %JAVA_HOME%
echo project module: %MAIN_MODULE%/%MAIN_CLASS%
echo project icon: %ICON_PATH%
echo project license: %LICENSE_FILE%
echo main JAR file: %MAIN_JAR%

rem ------ SETUP DIRECTORIES AND FILES ----------------------------------------
rem Remove previously generated java runtime and installers. Copy all required
rem jar files into the input/libs folder.

IF EXIST target\installer rmdir /S /Q target\installer
IF EXIST target\mods rmdir /S /Q target\mods
IF EXIST target\java-runtime rmdir /S /Q target\java-runtime

xcopy /S /Q target\dependency\* target\installer\input\libs\

rem ------ AUTOMATIC MODULES -------------------------------------------------
rem To create custom Java runtime, all the dependencies should be in modular
rem automatic modules is not supported to create custom Java runtime. So, we need
rem patch dependencies that still using automatic modules.

rem no automatic modules yet!

rem ------ REQUIRED MODULES ---------------------------------------------------
rem Use jlink to detect all modules that are required to run the application.
rem Starting point for the jdep analysis is the set of jars being used by the
rem application.

echo detecting required modules

"%JAVA_HOME%\bin\jdeps" ^
  -q ^
  --multi-release %JAVA_VERSION% ^
  --ignore-missing-deps ^
  --print-module-deps ^
  --module-path target/installer/input/libs ^
  --add-modules %MAIN_MODULE% target\installer\input\libs\%MAIN_JAR% > temp.txt

set /p detected_modules=<temp.txt

echo detected modules: %detected_modules%

rem ------ MANUAL MODULES -----------------------------------------------------
rem jdk.crypto.ec has to be added manually bound via --bind-services or
rem otherwise HTTPS does not work.
rem
rem See: https://bugs.openjdk.java.net/browse/JDK-8221674

set manual_modules=jdk.localedata
echo manual modules: %manual_modules%

rem ------ RUNTIME IMAGE ------------------------------------------------------
rem Use the jlink tool to create a runtime image for our application. We are
rem doing this is a separate step instead of letting jlink do the work as part
rem of the jpackage tool. This approach allows for finer configuration and also
rem works with dependencies that are not fully modularized, yet.

echo creating java runtime image
call "%JAVA_HOME%\bin\jlink" ^
  --strip-native-commands ^
  --no-header-files ^
  --no-man-pages ^
  --compress=2 ^
  --strip-debug ^
  --include-locales=en,id ^
  --module-path target\installer\input\libs ^
  --add-modules %detected_modules%,%manual_modules%,%MAIN_MODULE% ^
  --output target\java-runtime
echo success creating custom java-runtime on target/java-runtime

rem ------ PACKAGING ----------------------------------------------------------
rem In the end we will find the package inside the target/installer directory.

echo creating application image

for %%s in ("msi" "exe") do call "%JAVA_HOME%\bin\jpackage" ^
  --type %%s ^
  --app-version %APP_VERSION% ^
  --copyright "Copyright © 2022 Sukma Wardana" ^
  --description "Nayanika Picture Viewer" ^
  --icon %ICON_PATH% ^
  --name Nayanika ^
  --dest target\installer ^
  --vendor "Nayanika" ^
  --runtime-image target\java-runtime ^
  --java-options -Xms64m ^
  --java-options -Xmx256m ^
  --java-options -XX:+UseSerialGC ^
  --module %MAIN_MODULE%/%MAIN_CLASS% ^
  --file-associations pkg\properties\bmp.properties ^
  --file-associations pkg\properties\jpg.properties ^
  --file-associations pkg\properties\jpeg.properties ^
  --file-associations pkg\properties\png.properties ^
  --license-file %LICENSE_FILE% ^
  --win-upgrade-uuid "%UUID%" ^
  --win-dir-chooser ^
  --win-shortcut ^
  --win-menu ^
  --win-menu-group "Nayanika" ^

rem ------ CLEAN --------------------------------------------------------------
rem Clean not required resources.

echo cleaning workspace
IF EXIST temp.txt DEL /S /Q temp.txt
echo success cleaning workspace
