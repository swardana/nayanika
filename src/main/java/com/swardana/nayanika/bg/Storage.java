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

import com.swardana.nayanika.base.PictureImage;
import com.swardana.nayanika.base.PictureSource;
import com.swardana.nayanika.base.SortedPictures;

import javafx.concurrent.Task;

import java.util.List;
import java.util.stream.Collectors;

/**
 * A storage.
 * <p>
 *     Place where the pictures reside. Automatically
 *     convert from {@link com.swardana.nayanika.base.Picture}
 *     to {@link PictureImage}.
 * </p>
 *
 * @author Sukma Wardana
 * @version 1.0.0
 * @since 1.0.0
 */
public class Storage extends Task<List<PictureImage>> {

    private final PictureSource source;
    private final SortedPictures.Strategy strategy;

    /**
     * Creates new Storage.
     * <p>
     *     The default strategy is to not sort the
     *     pictures.
     * </p>
     *
     * @param src the {@link PictureSource}.
     */
    public Storage(final PictureSource src) {
        this(src, SortedPictures.Strategy.NONE);
    }

    /**
     * Creates new Storage.
     *
     * @param src the {@link PictureSource}.
     * @param strategy the sorted pictures strategy.
     */
    public Storage(final PictureSource src, final SortedPictures.Strategy strategy) {
        this.source = src;
        this.strategy = strategy;
    }

    @Override
    protected final List<PictureImage> call() throws Exception {
        return this.strategy.sorted(this.source.pictures())
            .sorted().stream()
            .map(PictureImage::new)
            .collect(Collectors.toList());
    }
}
