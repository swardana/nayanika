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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;

/**
 * A tool-bar visual.
 *
 * @author Sukma Wardana
 * @version 1.0.0
 * @since 1.0.0
 */
public class ToolBarVisual extends ToolBar implements MenuView {

    private static final int SLIDE_MENU_INDEX = 1;

    private final Stage owner;

    private final Button openDirButton;
    private final Button nextBtn;
    private final Button prevBtn;
    private final Button beginningBtn;
    private final Button lastBtn;
    private final Button startBtn;
    private final Button stopBtn;

    private final PaginationStatusControl pagination;

    private final MenuBehavior behavior;

    /**
     * Creates new ToolBarVisual.
     *
     * @param stage the primary stage owner.
     * @param control the pagination observable status control.
     * @param slide the slide-show observable state control.
     */
    public ToolBarVisual(
        final Stage stage,
        final PaginationStatusControl control,
        final Slide slide
    ) {
        this.owner = stage;
        this.pagination = control;

        this.behavior = new MenuBehavior(this, slide);

        this.openDirButton = new Button();
        this.nextBtn = new Button();
        this.prevBtn = new Button();
        this.beginningBtn = new Button();
        this.lastBtn = new Button();
        this.startBtn = new Button();
        this.stopBtn = new Button();

        this.initGraphics();
        this.registerListeners();
    }

    @Override
    public final Parent parent() {
        return this;
    }



    @Override
    public final void enableSlideMenu() {
        this.startBtn.setDisable(false);
    }

    @Override
    public final void showStartSlideMenu() {
        this.getItems().remove(SLIDE_MENU_INDEX);
        this.getItems().add(SLIDE_MENU_INDEX, this.startBtn);
    }

    @Override
    public final void showStopSlideMenu() {
        this.getItems().remove(SLIDE_MENU_INDEX);
        this.getItems().add(SLIDE_MENU_INDEX, this.stopBtn);
    }

    private void initGraphics() {
        this.openDirButton.setText("Open...");

        this.startBtn.setText("Start");
        this.startBtn.setDisable(true);
        this.stopBtn.setText("Stop");

        this.nextBtn.setText("Next");
        this.prevBtn.setText("Prev");
        this.beginningBtn.setText("Beginning");
        this.lastBtn.setText("Last");

        this.getItems().setAll(
            this.openDirButton,
            this.startBtn,
            this.beginningBtn,
            this.prevBtn,
            this.nextBtn,
            this.lastBtn
        );
    }

    private void registerListeners() {
        this.openDirButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                DirectoryChooser chooser = new DirectoryChooser();
                File dir = chooser.showDialog(owner);
                ToolBarVisual.this.fireEvent(new ExhibitionEvent(dir));
            }
        });

        this.registerNavigateListeners();
        this.registerViewListeners();
    }

    private void registerNavigateListeners() {
        this.nextBtn.disableProperty().bind(this.pagination.lastProperty());
        this.lastBtn.disableProperty().bind(this.pagination.lastProperty());
        this.prevBtn.disableProperty().bind(this.pagination.beginningProperty());
        this.beginningBtn.disableProperty().bind(this.pagination.beginningProperty());

        this.nextBtn.setOnAction(
            e -> this.fireEvent(
                new PaginationEvent(PaginationEvent.Movement.NEXT)
            )
        );
        this.prevBtn.setOnAction(
            e -> this.fireEvent(
                new PaginationEvent(PaginationEvent.Movement.PREVIOUS)
            )
        );
        this.lastBtn.setOnAction(
            e -> this.fireEvent(
                new PaginationEvent(PaginationEvent.Movement.LAST)
            )
        );
        this.beginningBtn.setOnAction(
            e -> this.fireEvent(
                new PaginationEvent(PaginationEvent.Movement.BEGINNING)
            )
        );
    }

    private void registerViewListeners() {
        this.startBtn.setOnAction(e -> this.behavior.onStartSlideShow());
        this.stopBtn.setOnAction(e -> this.behavior.onStopSlideShow());
    }

}
