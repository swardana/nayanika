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

package com.swardana.nayanika;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Bootstrap class for Nayanika application.
 * <p>
 *     This to launch Nayanika application to workaround
 *     for JavaFX runtime presence.
 * </p>
 *
 * @author Sukma Wardana
 * @version 1.0.0
 * @since 1.0.0
 */
public class NayanikaLauncher {

    private static final Logger LOG = Logger.getLogger(NayanikaLauncher.class.getName());

    /**
     * Bootstrap starting entry point.
     *
     * @param args the command-line arguments.
     */
    public static void main(final String[] args) {
        LOG.log(Level.INFO, "Launch Nayanika application.");
        LOG.log(
            Level.FINER,
            "Operating System Name: {0}",
            new Object[]{System.getProperty("os.name")}
        );
        LOG.log(
            Level.FINER,
            "Operating System Arch: {0}",
            new Object[]{System.getProperty("os.arch")}
        );
        LOG.log(
            Level.FINER,
            "Operating System Ver : {0}",
            new Object[]{System.getProperty("os.version")}
        );
        Nayanika.main(args);
        LOG.log(Level.INFO, "Shutting down Nayanika application.");
    }

}
