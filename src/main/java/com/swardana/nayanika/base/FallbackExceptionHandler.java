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

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Catch and log any unhandled exception.
 *
 * @author Sukma Wardana
 * @version 1.0.0
 * @since 1.0.0
 */
public class FallbackExceptionHandler implements Thread.UncaughtExceptionHandler {

    private static final Logger LOG = Logger.getLogger(FallbackExceptionHandler.class.getName());

    @Override
    public final void uncaughtException(final Thread thread, final Throwable throwable) {
        LOG.log(Level.WARNING, "Uncaught exception occurred in {0}.", new Object[]{thread});
    }
}
