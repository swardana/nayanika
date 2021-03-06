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

package com.swardana.nayanika.gui.topbar.menu;

import com.swardana.nayanika.base.PaginationStatusControl;
import com.swardana.nayanika.base.Slide;
import com.swardana.nayanika.gui.event.ExhibitionEvent;
import com.swardana.nayanika.gui.event.PaginationEvent;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

/**
 * A menu-bar visual.
 *
 * @author Sukma Wardana
 * @version 1.0.0
 * @since 1.0.0
 */
public class MenuBarVisual extends MenuBar implements MenuView {

    private static final int SLIDE_MENU_INDEX = 0;

    private final Stage owner;

    // File Menu
    private final Menu fileMenu = new Menu();
    private final MenuItem openPicture = new MenuItem();
    private final MenuItem openDirectory = new MenuItem();
    private final MenuItem exit = new MenuItem();

    // Navigate Menu
    private final Menu navigateMenu = new Menu();
    private final MenuItem goNext = new MenuItem();
    private final MenuItem goPrev = new MenuItem();
    private final MenuItem goBeginning = new MenuItem();
    private final MenuItem goLast = new MenuItem();

    // View Menu
    private final Menu viewMenu = new Menu();
    private final MenuItem startShow = new MenuItem();
    private final MenuItem stopShow = new MenuItem();

    private final PaginationStatusControl pagination;

    private final MenuBehavior behavior;

    /**
     * Creates new MenuBarVisual.
     *
     * @param stage the primary stage owner.
     * @param control the pagination observable status control.
     * @param slide the slide-show observable state control.
     */
    public MenuBarVisual(
        final Stage stage,
        final PaginationStatusControl control,
        final Slide slide
    ) {
        this.owner = stage;
        this.pagination = control;

        this.behavior = new MenuBehavior(this, slide);

        this.initGraphics();
        this.registerListeners();
    }

    @Override
    public final Parent parent() {
        return this;
    }

    @Override
    public final void enableSlideMenu() {
        this.startShow.setDisable(false);
    }

    @Override
    public final void showStartSlideMenu() {
        this.viewMenu.getItems().remove(SLIDE_MENU_INDEX);
        this.viewMenu.getItems().add(SLIDE_MENU_INDEX, this.startShow);
    }

    @Override
    public final void showStopSlideMenu() {
        this.viewMenu.getItems().remove(SLIDE_MENU_INDEX);
        this.viewMenu.getItems().add(SLIDE_MENU_INDEX, this.stopShow);
    }

    private void initGraphics() {
        this.fileMenu.setText("_File");
        this.navigateMenu.setText("_Navigate");
        this.viewMenu.setText("_View");

        this.initFileMenuGraphics();
        this.initNavigateMenuGraphics();
        this.initViewMenuGraphics();

        this.getMenus().setAll(
            this.fileMenu,
            this.navigateMenu,
            this.viewMenu
        );
    }

    private void initFileMenuGraphics() {
        this.openPicture.setText("Open Picture...");
        this.openDirectory.setText("Open Directory...");
        this.exit.setText("Exit");

        this.fileMenu.getItems().setAll(
            this.openPicture,
            this.openDirectory,
            this.exit
        );
    }

    private void initNavigateMenuGraphics() {
        this.goNext.setText("Go Next");
        this.goPrev.setText("Go Previous");
        this.goBeginning.setText("Go Beginning");
        this.goLast.setText("Go Last");

        this.navigateMenu.getItems().setAll(
            this.goNext,
            this.goPrev,
            new SeparatorMenuItem(),
            this.goBeginning,
            this.goLast
        );
    }

    private void initViewMenuGraphics() {
        this.startShow.setText("Start slide-show");
        this.startShow.setDisable(true);
        this.stopShow.setText("Stop slide-show");

        this.viewMenu.getItems().setAll(this.startShow);
    }

    private void registerListeners() {
        this.registerFileMenuListeners();
        this.registerNavigateMenuListeners();
        this.registerViewListeners();
    }

    private void registerFileMenuListeners() {
        this.openPicture.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                FileChooser chooser = new FileChooser();
                File pic = chooser.showOpenDialog(owner);
                MenuBarVisual.this.fireEvent(new ExhibitionEvent(pic));
            }
        });
        this.openDirectory.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                DirectoryChooser chooser = new DirectoryChooser();
                File dir = chooser.showDialog(owner);
                MenuBarVisual.this.fireEvent(new ExhibitionEvent(dir));
            }
        });
        this.exit.setOnAction(e -> Platform.exit());
    }

    private void registerNavigateMenuListeners() {
        this.goNext.disableProperty().bind(this.pagination.lastProperty());
        this.goLast.disableProperty().bind(this.pagination.lastProperty());
        this.goPrev.disableProperty().bind(this.pagination.beginningProperty());
        this.goBeginning.disableProperty().bind(this.pagination.beginningProperty());
        this.goNext.setOnAction(
            e -> this.fireEvent(
                new PaginationEvent(PaginationEvent.Movement.NEXT)
            )
        );
        this.goPrev.setOnAction(
            e -> this.fireEvent(
                new PaginationEvent(PaginationEvent.Movement.PREVIOUS)
            )
        );
        this.goBeginning.setOnAction(
            e -> this.fireEvent(
                new PaginationEvent(PaginationEvent.Movement.BEGINNING)
            )
        );
        this.goLast.setOnAction(
            e -> this.fireEvent(
                new PaginationEvent(PaginationEvent.Movement.LAST)
            )
        );
    }

    private void registerViewListeners() {
        this.startShow.setOnAction(e -> this.behavior.onStartSlideShow());
        this.stopShow.setOnAction(e -> this.behavior.onStopSlideShow());
    }

}
