/*
 * Nayanika is a picture viewer application.
 * Copyright (C) 2022  Sukma Wardana
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.swardana.nayanika.base;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Application build version information.
 * <p>
 *     The version and build number is retrieve from
 *     {@code version.properties} file bundled with
 *     the application.
 * </p>
 *
 * @author Sukma Wardana
 * @version 1.0.0
 * @since 1.0.0
 */
public final class BuildVersion {

    private static final Logger LOG = Logger.getLogger(BuildVersion.class.getName());

    private final Properties properties;

    /**
     * Creates new BuildVersion.
     * <p>
     *     The default properties file is {@code version.properties}
     *     which bundled with the application on the same package
     *     with {@link BuildVersion}.
     * </p>
     */
    public BuildVersion() {
        this("version.properties");
    }

    /**
     * Creates new BuildVersion.
     *
     * @param file the version information file.
     */
    public BuildVersion(final String file) {
        this.properties = new VersionFile(file).properties();
    }

    /**
     * The application version number.
     *
     * @return the app version.
     */
    public String buildVersion() {
        return this.properties.getProperty("build.version", "UNSET");
    }

    private class VersionFile {

        private final Properties props;
        private final String file;

        VersionFile(final String file) {
            this.props = new Properties();
            this.file = file;
        }

        Properties properties() {
            try (var stream = VersionFile.class.getResourceAsStream(this.file)) {
                props.load(stream);
            } catch (final IOException | NullPointerException ex) {
                LOG.log(
                    Level.WARNING,
                    "Fail to process file! Goes with default value.",
                    ex
                );
            }
            return this.props;
        }

    }

}
