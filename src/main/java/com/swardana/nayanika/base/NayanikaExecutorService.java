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

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Thread pool management.
 *
 * @author Sukma Wardana
 * @version 1.0.0
 * @since 1.0.0
 */
public final class NayanikaExecutorService {

    private static final Logger LOG = Logger.getLogger(NayanikaExecutorService.class.getName());

    private final ExecutorService executor;

    /**
     * Creates new NayanikaExecutorService.
     */
    public NayanikaExecutorService() {
        this.executor = new CachedThread().executor();
    }

    /**
     * The cached {@link ExecutorService}.
     *
     * @return the cached executor;
     */
    public ExecutorService cachedExecutor() {
        return this.executor;
    }

    /**
     * Shuts down the {@link ExecutorService}.
     * <p>
     *     Will try to shutdown normally, if by certain period time not shutdown
     *     then forcibly shutting it down.
     * </p>
     */
    public void gracefullyShutDown() {
        LOG.log(Level.FINE, "Disable new task from being submitted.");
        this.executor.shutdown();
        try {
            LOG.log(Level.FINE, "Wait a while for existing task to terminate.");
            if (this.executor.awaitTermination(60, TimeUnit.SECONDS)) {
                LOG.log(
                    Level.FINE,
                    "One minute passed, {0} still not completed. Trying force shutdown.",
                    new Object[]{this.executor.toString()}
                );
                // those threads will be interrupted in their current task.
                this.executor.shutdownNow();
                LOG.log(Level.FINE, "Wait a while for tasks to respond to being cancelled.");
                if (this.executor.awaitTermination(60, TimeUnit.SECONDS)) {
                    LOG.log(
                        Level.WARNING,
                        "One minute pased again - forced shutdown of {0} worked",
                        new Object[]{this.executor.toString()}
                    );
                } else {
                    LOG.log(
                        Level.SEVERE,
                        "{0} did not terminate!",
                        new Object[]{this.executor.toString()}
                    );
                }
            }
        } catch (final InterruptedException ex) {
            LOG.log(Level.FINE, "(Re-)Cancel if current thread also interrupted.");
            this.executor.shutdown();
            LOG.log(Level.FINE, "Preserve interrupt status.");
            Thread.currentThread().interrupt();
        }
    }

    private class CachedThread {

        ExecutorService executor() {
            return Executors.newCachedThreadPool(r -> {
                var thread = new Thread(r);
                thread.setName("Nayanika CachedThreadPool");
                thread.setUncaughtExceptionHandler(new FallbackExceptionHandler());
                return thread;
            });
        }

    }

}
