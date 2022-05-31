#
# Nayanika is a picture viewer application.
# Copyright (C) 2022  Sukma Wardana
#
# This program is free software: you can redistribute it and/or modify
# it under the terms of the GNU General Public License as published by
# the Free Software Foundation, either version 3 of the License, or
# (at your option) any later version.
#
# This program is distributed in the hope that it will be useful,
# but WITHOUT ANY WARRANTY; without even the implied warranty of
# MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
# GNU General Public License for more details.
#
# You should have received a copy of the GNU General Public License
# along with this program.  If not, see <https://www.gnu.org/licenses/>.
#

#!/bin/bash
# ------ ENVIRONMENT --------------------------------------------------------
# The script depends on various environment variables to exist in order to
# run properly. The java version we want to use, the location of the java
# binaries (java home), and the project version as defined inside the pom.xml
# file, e.g. 1.0-SNAPSHOT.
#

JAVA_VERSION=17
APP_VERSION=1.0.001

echo "java version: ${JAVA_VERSION}"
echo "java home: ${JAVA_HOME}"
echo "project module: ${MAIN_MODULE}/${MAIN_CLASS}"
echo "project icon: ${ICON_PATH}"
echo "project license: ${LICENSE_FILE}"
echo "main JAR file: ${MAIN_JAR}"

# ------ SETUP DIRECTORIES AND FILES ----------------------------------------
# Remove previously generated java runtime and installers. Copy all required
# jar files into the input/libs folder.
rm -rfd ./target/installer/
rm -rfd ./target/mods
rm -rfd ./target/java-runtime

mkdir -p ./target/installer/input/libs/
mkdir -p ./target/mods

cp target/dependency/* target/installer/input/libs/

# ------ AUTOMATIC MODULES -------------------------------------------------
# To create custom Java runtime, all the dependencies should be in modular
# automatic modules is not supported to create custom Java runtime. So, we need
# patch dependencies that still using automatic modules.

echo "no dependency with automatic module"

# ------ REQUIRED MODULES ---------------------------------------------------
# Use jlink to detect all modules that are required to run the application.
# Starting point for the jdep analysis is the set of jars being used by the
# application.

echo "detecting required modules"
detected_modules=`$JAVA_HOME/bin/jdeps \
  -q \
  --multi-release ${JAVA_VERSION} \
  --ignore-missing-deps \
  --print-module-deps \
  --module-path target/installer/input/libs \
  --add-modules ${MAIN_MODULE} target/installer/input/libs/${MAIN_JAR}`
echo "detected modules: ${detected_modules}"

# ------ MANUAL MODULES -----------------------------------------------------
# jdk.crypto.ec has to be added manually bound via --bind-services or
# otherwise HTTPS does not work.
#
# See: https://bugs.openjdk.java.net/browse/JDK-8221674
#
# In addition we need jdk.localedata if the application is localized.
# This can be reduced to the actually needed locales via a jlink paramter,
# e.g., --include-locales=en,de.

# manual_modules=jdk.crypto.ec,jdk.localedata
manual_modules=jdk.localedata
echo "manual modules: ${manual_modules}"

# ------ RUNTIME IMAGE ------------------------------------------------------
# Use the jlink tool to create a runtime image for our application. We are
# doing this is a separate step instead of letting jlink do the work as part
# of the jpackage tool. This approach allows for finer configuration and also
# works with dependencies that are not fully modularized, yet.

echo "creating java runtime image"
$JAVA_HOME/bin/jlink \
  --strip-native-commands \
  --no-header-files \
  --no-man-pages  \
  --compress=2  \
  --strip-debug \
  --include-locales=en,id \
  --module-path target/installer/input/libs \
  --add-modules "${detected_modules},${manual_modules},${MAIN_MODULE}" \
  --output target/java-runtime
echo "success creating custom java-runtime on target/java-runtime"

# ------ PACKAGING ----------------------------------------------------------
# In the end we will find the package inside the target/installer directory.

for type in "deb" "rpm"
do
  echo "Creating installer of type ... $type"

  $JAVA_HOME/bin/jpackage \
    --type $type \
    --app-version ${APP_VERSION} \
    --copyright "Copyright © 2022 Sukma Wardana" \
    --description "Nayanika Picture Viewer" \
    --icon ${ICON_PATH} \
    --name Nayanika \
    --dest target/installer \
    --vendor "Nayanika" \
    --runtime-image target/java-runtime \
    --java-options -Xms64m \
    --java-options -Xmx256m \
    --java-options -XX:+UseSerialGC \
    --module ${MAIN_MODULE}/${MAIN_CLASS} \
    --file-associations pkg/properties/bmp.properties \
    --file-associations pkg/properties/jpg.properties \
    --file-associations pkg/properties/jpeg.properties \
    --file-associations pkg/properties/png.properties \
    --license-file ${LICENSE_FILE} \
    --linux-package-name Nayanika \
    --linux-menu-group Graphics \
    --linux-app-category graphics \
    --linux-app-release ${APP_VERSION} \
    --linux-shortcut \
    --linux-deb-maintainer swardana@tutanota.com \
    --linux-rpm-license-type GPL-3.0-or-later

done
