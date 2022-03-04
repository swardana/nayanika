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
import java.io.IOException;
import java.io.InputStream;

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
        private final InputStream stream;

        /**
         * Creates new Picture::Of.
         *
         * @param name the picture name.
         * @param src the picture source file data.
         * @throws IOException if the picture source file data is not
         * found.
         */
        public Of(final String name, final File src) throws IOException {
            this(name, new FileInputStream(src));
        }

        /**
         * Creates new Picture::Of.
         *
         * @param name the picture name.
         * @param stream the picture byte stream data.
         */
        public Of(final String name, final InputStream stream) {
            this.name = name;
            this.stream = stream;
        }

        @Override
        public final String name() {
            return this.name;
        }

        @Override
        public final InputStream stream() {
            return this.stream;
        }

    }

}
