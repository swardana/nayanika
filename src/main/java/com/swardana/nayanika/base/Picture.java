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

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

/**
 * A picture.
 *
 * @author Sukma Wardana
 * @version 1.0.0
 * @since 1.0.0
 */
public interface Picture {

    /**
     * Name of this picture.
     *
     * @return {@link String} of the picture name.
     */
    String name();

    /**
     * Converting this picture to {@link Image}.
     *
     * @return {@link Image} of the picture input.
     */
    Image image();

    /**
     * A picture with static data.
     *
     * @author Sukma Wardana
     * @version 1.0.0
     * @since 1.0.0
     */
    class Of implements Picture {

        private final String name;
        private final Image image;

        /**
         * Creates new Picture::Of.
         *
         * @param name the picture name.
         * @param src the picture source file data.
         * @throws IOException if the picture source file data is not
         * found.
         */
        public Of(final String name, final File src) throws IOException {
            this(name, new Image(src.toURI().toURL().toExternalForm()));
        }

        /**
         * Creates new Picture::Of.
         *
         * @param name the picture name.
         * @param src the picture source path data.
         * @throws IOException if the picture source file data is not
         * found.
         */
        public Of(final String name, final Path src) throws IOException {
            this(name, new Image(src.toUri().toURL().toExternalForm()));
        }

        /**
         * Creates new Picture::Of.
         *
         * @param name the picture name.
         * @param img the picture {@link Image} .
         */
        public Of(final String name, final Image img) {
            this.name = name;
            this.image = img;
        }

        @Override
        public final String name() {
            return this.name;
        }

        @Override
        public final Image image() {
            return this.image;
        }

    }

}
