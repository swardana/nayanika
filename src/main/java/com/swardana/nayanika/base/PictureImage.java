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

import javafx.scene.image.Image;

/**
 * An adapter for {@link Picture} with JavaFX
 * {@link javafx.scene.image.Image}.
 *
 * @author Sukma Wardana
 * @version 1.0.0
 * @since 1.0.0
 */
public final class PictureImage {

    private final Picture picture;

    /**
     * Creates new PictureImage.
     *
     * @param pic the {@link Picture} that will convert to {@link Image}.
     */
    public PictureImage(final Picture pic) {
        this.picture = pic;
    }

    /**
     * The name of this picture image.
     *
     * @return {@link String} of this picture name.
     */
    public String name() {
        return this.picture.name();
    }

    /**
     * Converting the {@link Picture} to {@link Image}.
     *
     * @return {@link Image} of this picture.
     */
    public Image image() {
        return new Image(this.picture.stream());
    }

}
