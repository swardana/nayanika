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

package com.swardana.nayanika.gui.frame;

import javafx.beans.NamedArg;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;

/**
 * A frame pane.
 * <p>
 *     Work as {@link ImageView} control, this will resize
 *     the {@code ImageView} to the area available to the Region.
 * </p>
 * <p>
 *     This refer to the JavaFX Feature Request:
 *     <a href="https://bugs.openjdk.java.net/browse/JDK-8091216">RT-21337</a>.
 * </p>
 * <p>
 *     WARNING: ImageView attached to this FramePane, it's not allowed to bind
 *     the {@link ImageView#fitWidthProperty()} and
 *     {@link ImageView#fitHeightProperty()} to other Pane.
 *     Otherwise it will throw an error!
 * </p>
 *
 * @author Sukma Wardana
 * @version 0.1.0
 * @since 0.1.0
 */
class FramePane extends Region {

    private final ObjectProperty<ImageView> view;

    /**
     * Creates new FramePane.
     */
    FramePane() {
        this(new ImageView());
    }

    /**
     * Creates new FramePane.
     *
     * @param imageView the {@link ImageView} object.
     */
    FramePane(@NamedArg("imageView") final ImageView imageView) {
        this.view = new SimpleObjectProperty<>(this, "image", null);

        this.view.addListener(new ChangeListener<ImageView>() {
            @Override
            public void changed(
                final ObservableValue<? extends ImageView> observable,
                final ImageView oldView,
                final ImageView newView
            ) {
                if (oldView != null) {
                    getChildren().remove(oldView);
                }
                if (newView != null) {
                    getChildren().add(newView);
                }
            }
        });

        this.setView(imageView);
    }

    final ObjectProperty<ImageView> viewProperty() {
        return this.view;
    }

    final void setView(final ImageView img) {
        this.viewProperty().set(img);
    }

    final ImageView getView() {
        return this.viewProperty().get();
    }

    @Override
    protected void layoutChildren() {
        var imgView = this.getView();
        if (imgView != null) {
            imgView.setFitWidth(getWidth());
            imgView.setFitHeight(getHeight());
            layoutInArea(
                imgView, 0, 0, getWidth(), getHeight(), 0, HPos.CENTER, VPos.CENTER
            );
        }
        super.layoutChildren();
    }

}
