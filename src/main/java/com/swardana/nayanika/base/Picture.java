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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
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
     * Byte stream of this picture.
     *
     * @return {@link InputStream} of the picture input byte stream.
     * @throws RuntimeException if fail to read the picture byt data stream.
     */
    InputStream stream();

    /**
     * A picture with static data.
     *
     * @author Sukma Wardana
     * @version 1.0.0
     * @since 1.0.0
     */
    class Of implements Picture {

        private final String name;
        private final File file;

        /**
         * Creates new Picture::Of.
         *
         * @param name the picture name.
         * @param src the picture source path data.
         */
        public Of(final String name, final Path src) {
            this(name, src.toFile());
        }

        /**
         * Creates new Picture::Of.
         *
         * @param name the picture name.
         * @param src the picture source file data.
         * @throws IOException if the picture source file data is not
         * found.
         */
        public Of(final String name, final File src) {
            this.name = name;
            this.file = src;
        }


        @Override
        public final String name() {
            return this.name;
        }

        @Override
        public final InputStream stream() {
            try {
                return new FileInputStream(this.file);
            } catch (final FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        }

    }

}
