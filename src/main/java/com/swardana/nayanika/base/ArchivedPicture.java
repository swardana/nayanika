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
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipFile;

/**
 * A picture from archive.
 *
 * @author Sukma Wardana
 * @version 1.0.0
 * @since 1.0.0
 */
public class ArchivedPicture implements Picture {

    private static final Logger LOG = Logger.getLogger(ArchivedPicture.class.getName());

    private final File archive;
    private final String name;

    /**
     * Creates new ArchivedPicture.
     * @param name the archived picture name.
     * @param archive the comic book file.
     */
    public ArchivedPicture(final String name, final File archive) {
        this.archive = archive;
        this.name = name;
    }

    @Override
    public final String name() {
        return this.name;
    }

    @Override
    public final InputStream stream() {
        try {
            var zip = new ZipFile(this.archive);
            LOG.log(
                Level.FINE,
                "Successfully found the archive. [file={0}]",
                new Object[]{this.archive.getName()}
            );
            var entry = zip.getEntry(this.name);
            if (entry == null) {
                zip.close();
                throw new IOException("Can't find the picture!");
            }
            LOG.log(
                Level.FINE,
                "Prepare to stream picture byte data. [pic={0}]",
                new Object[]{this.name}
            );
            return new Wrapper(zip.getInputStream(entry), zip);
        } catch (final IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * A wrapper class for closing a ZipFile object when the InputStream derived
     * from it is closed.
     */
    private class Wrapper extends FilterInputStream {

        private final ZipFile zip;

        /**
         * Creates new Wrapper.
         *
         * @param item content within zip file.
         * @param zip zip archive file.
         */
        Wrapper(final InputStream item, final ZipFile zip) {
            super(item);
            this.zip = zip;
        }

        @Override
        public void close() throws IOException {
            super.close();
            this.zip.close();
        }
    }

}
