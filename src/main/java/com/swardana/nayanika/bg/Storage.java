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

package com.swardana.nayanika.bg;

import com.swardana.nayanika.base.Picture;
import com.swardana.nayanika.base.PictureSource;
import javafx.concurrent.Task;

import java.util.List;

/**
 * A storage.
 * <p>
 *     Place where the pictures reside.
 * </p>
 *
 * @author Sukma Wardana
 * @version 1.0.0
 * @since 1.0.0
 */
public class Storage extends Task<List<Picture>> {

    private final PictureSource source;

    /**
     * Creates new Storage.
     *
     * @param src the {@link PictureSource}.
     */
    public Storage(final PictureSource src) {
        this.source = src;
    }

    @Override
    protected final List<Picture> call() throws Exception {
        return this.source.pictures();
    }
}
