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

import com.swardana.nayanika.base.FallbackExceptionHandler;
import com.swardana.nayanika.base.NayanikaExecutorService;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The JavaFX {@link Application} bootstrap class.
 * <p>
 *     Responsible for initializing the JavaFX application
 *     and launching the <i>Graphical User Interface</i> (GUI).
 * </p>
 *
 * @author Sukma Wardana
 * @version 1.0.0
 * @since 1.0.0
 */
public class Nayanika extends Application {

    private static final Logger LOG = Logger.getLogger(Nayanika.class.getName());

    private final NayanikaExecutorService nes = new NayanikaExecutorService();

    @Override
    public void init() throws Exception {
        Thread.setDefaultUncaughtExceptionHandler(new FallbackExceptionHandler());
    }

    @Override
    public void start(final Stage stage) throws Exception {
        var lbl = new Label("Hello World");
        var scene = new Scene(new StackPane(lbl), 680, 400);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        this.nes.gracefullyShutDown();
    }

    /**
     * The JavaFX start up entry point.
     *
     * @param args the command-line arguments.
     */
    public static void main(final String[] args) {
        LOG.log(Level.INFO, "Start up Nayanika Application.");
        if (LOG.isLoggable(Level.FINE) && args.length != 0) {
            LOG.log(Level.FINE, "Start up application arguments:");
            for (final var arg : args) {
                LOG.log(Level.FINE, "arg: {0}", new Object[]{arg});
            }
        }
        Application.launch(args);
    }

}
