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

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Source location of the pictures.
 *
 * @author Sukma Wardana
 * @version 1.0.0
 * @author 1.0.0
 */
public interface PictureSource {

    /**
     * The supported picture MIME type.
     *
     * @author Sukma Wardana
     * @version 1.0.0
     * @since 1.0.0
     */
    enum Supported {
        /**
         * All supported media format.
         */
        ALL(
            "*.{png,PNG,JPG,jpg,JPEG,jpeg,GIF,gif,BMP,bmp}",
            Arrays.asList("*.bmp", "*.gif", "*.jpg", "*.jpeg", "*.png")
        ),
        /**
         * Only PNG file.
         */
        PNG("*.{png,PNG}", Arrays.asList("*.png")),
        /**
         * Only JPG file.
         */
        JPG("*.{JPG,jpg,JPEG,jpeg}", Arrays.asList("*.jpg", "*.jpeg")),
        /**
         * Only GIF file.
         */
        GIF("*.{GIF,gif}", Arrays.asList("*.gif")),
        /**
         * Only BMP file.
         */
        BMP("*.{BMP,bmp}", Arrays.asList("*.bmp"));

        private final String regex;
        private final List<String> extensions;

        Supported(final String regex, final List<String> ext) {
            this.regex = regex;
            this.extensions = ext;
        }

        /**
         * The regex pattern.
         * <p>
         *     This regex pattern could be applied
         *     for {@link java.nio.file.PathMatcher}.
         * </p>
         *
         * @see  java.nio.file.PathMatcher
         * @return the regex pattern for supported picture media type.
         */
        public String pattern() {
            return this.regex;
        }

        /**
         * File extension for supported media type.
         *
         * @return the list of supported extension.
         */
        public List<String> extensions() {
            return this.extensions;
        }
    }

    /**
     * The pictures from this source.
     *
     * @return {@link List} of pictures.
     */
    List<Picture> pictures();

    /**
     * A directory which contain the pictures.
     * <p>
     *     Looking for {@link Supported} pictures
     *     within directory.
     * </p>
     *
     * @author Sukma Wardana
     * @version 1.0.0
     * @since 1.0.0
     */
    class Directory implements PictureSource {

        private static final Logger LOG = Logger.getLogger(Directory.class.getName());

        private final Path source;
        private final Supported supported;

        /**
         * Creates new Directory.
         * <p>
         *     Search all available {@link Supported} pictures within
         *     directory.
         * </p>
         *
         * @param src the {@link Path} of directory source.
         */
        public Directory(final Path src) {
            this(src, Supported.ALL);
        }

        /**
         * Creates new Directory.
         *
         * @param src the {@link Path} of directory source.
         * @param filter the filtered {@link Supported} picture.
         */
        public Directory(final Path src, final Supported filter) {
            this.source = src;
            this.supported = filter;
        }

        @Override
        public final List<Picture> pictures() {
            var dir = this.source.toString();
            final List<Picture> pictures = new ArrayList<>();
            try (
                var stream = Files.newDirectoryStream(
                    this.source, this.supported.pattern()
                )
            ) {
                LOG.log(
                    Level.FINE,
                    "Successfully streamed the directory. [dir={0}]",
                    new Object[]{dir}
                );
                for (final var path : stream) {
                    var picName = path.getFileName().toString();
                    pictures.add(
                        new Picture.Of(picName, path.toFile())
                    );
                    LOG.log(Level.FINE, "Add picture into list. [pic={0}]", new Object[]{picName});
                }
                LOG.log(
                    Level.INFO,
                    "Search pictures in directory. [dir={0}, size={1}]",
                    new Object[]{dir, pictures.size()}
                );
                return pictures;
            } catch (final IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

}
