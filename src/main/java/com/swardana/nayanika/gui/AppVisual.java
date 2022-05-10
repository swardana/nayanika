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

package com.swardana.nayanika.gui;

import com.swardana.nayanika.base.NayanikaExecutorService;
import com.swardana.nayanika.base.Picture;
import com.swardana.nayanika.base.PictureImage;
import com.swardana.nayanika.base.PictureSource;
import com.swardana.nayanika.base.Slide;
import com.swardana.nayanika.bg.Storage;
import com.swardana.nayanika.gui.event.ExhibitionEvent;
import com.swardana.nayanika.gui.event.PaginationEvent;
import com.swardana.nayanika.gui.event.PaginationEventHandler;
import com.swardana.nayanika.gui.frame.FrameVisual;
import com.swardana.nayanika.gui.topbar.TopBarView;
import com.swardana.nayanika.gui.topbar.TopBarVisual;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.nio.file.Files;

/**
 * An application visual.
 *
 * @author Sukma Wardana
 * @version 1.0.0
 * @since 1.0.0
 */
public class AppVisual extends BorderPane implements AppView {

    private final Stage owner;

    private final TopBarView topbar;
    private final FrameVisual frame;

    private final Slide slide;

    private final PaginationEventHandler paginationHandler;
    private final NayanikaExecutorService nes;

    public AppVisual(final Stage stage, final NayanikaExecutorService nes) {
        this.owner = stage;
        this.nes = nes;
        this.slide = new Slide.Control();
        this.frame = new FrameVisual();
        this.topbar = new TopBarVisual(stage, this.frame, this.slide);

        this.paginationHandler = new PaginationEventHandler(this.frame);

        this.initGraphics();
        this.registerListeners();
    }

    @Override
    public final Parent parent() {
        return this;
    }

    private void initGraphics() {
        this.slide.updateControl(this.frame);
        this.setTop(this.topbar.parent());
        this.setCenter(this.frame.parent());
    }

    @SuppressWarnings("checkstyle:AnonInnerLength")
    private void registerListeners() {
        this.addEventFilter(ExhibitionEvent.EXHIBIT, new EventHandler<ExhibitionEvent>() {
            @Override
            public void handle(final ExhibitionEvent event) {
                event.source().ifPresent(src -> {
                    var path = src.toPath();
                    final Storage storage;
                    if (Files.isDirectory(path)) {
                        storage = new Storage(new PictureSource.Directory(path));
                        storage.setOnSucceeded(ev -> frame.exhibit(storage.getValue()));
                    } else {
                        final Picture pic;
                        pic = new Picture.Of(path.getFileName().toString(), src);
                        storage = new Storage(
                            new PictureSource.Directory(src.toPath().getParent())
                        );
                        storage.setOnSucceeded(
                            ev -> frame.exhibit(storage.getValue(), new PictureImage(pic))
                        );
                    }
                    nes.cachedExecutor().execute(storage);
                });
                event.consume();
            }
        });
        this.addEventFilter(PaginationEvent.MOVE, this.paginationHandler);
        this.addEventHandler(PaginationEvent.MOVE, this.paginationHandler);
    }

}
