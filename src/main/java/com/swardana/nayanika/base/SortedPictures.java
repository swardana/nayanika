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

import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * A list of sorted pictures.
 *
 * @author Sukma Wardana
 * @version 1.0.0
 * @since 1.0.0
 */
public interface SortedPictures {

    /**
     * The sorted pictures.
     *
     * @return the {@link Picture}s in sorted order.
     */
    List<Picture> sorted();

    /**
     * The default un-sortened pictures.
     * <p>
     *     This implementation of {@link SortedPictures} will not
     *     doing anything with the given pictures.
     * </p>
     *
     * @author Sukma Wardana
     * @version 1.0.0
     * @since 1.0.0
     */
    class NotSortedPictures implements SortedPictures {

        private final List<Picture> pictures;

        /**
         * Creates new NotSortedPictures.
         *
         * @param pics the un-sortened {@link Picture}s.
         */
        public NotSortedPictures(final List<Picture> pics) {
            this.pictures = pics;
        }

        @Override
        public final List<Picture> sorted() {
            return this.pictures;
        }

    }

    /**
     * Sort the pictures in ascending order.
     *
     * @author Sukma Wardana
     * @version 1.0.0
     * @since 1.0.0
     */
    class AscSortedPictures implements SortedPictures {

        private static final Logger LOG = Logger.getLogger(AscSortedPictures.class.getName());

        private final List<Picture> pictures;

        /**
         * Creates new AscSortedPictures.
         *
         * @param pics the un-sortened {@link Picture}s.
         */
        public AscSortedPictures(final List<Picture> pics) {
            this.pictures = pics;
        }

        @Override
        public final List<Picture> sorted() {
            var comparator = new AscComparator();
            var sorted = this.pictures.stream().sorted(comparator).collect(Collectors.toList());
            if (LOG.isLoggable(Level.FINER)) {
                LOG.log(Level.FINER, "Before sort the pictures in ascending order:");
                this.pictures.forEach(
                    p -> LOG.log(Level.FINER, "The picture. [{0}]", new Object[]{p.name()})
                );
                LOG.log(Level.FINER, "After sort the pictures in ascending order:");
                sorted.forEach(
                    p -> LOG.log(Level.FINER, "The picture. [{0}]", new Object[]{p.name()})
                );
            }
            return sorted;
        }

        private class AscComparator extends NumberAsNameComparator {

            @Override
            public int compare(final Picture first, final Picture second) {
                final int result;
                if (this.isBothPictureNameAsNumber(first, second)) {
                    result = this.number(first.name()) - this.number(second.name());
                } else {
                    result = first.name().compareTo(second.name());
                }
                return result;
            }

        }

    }

    /**
     * Comparator for {@link Picture}.
     * <p>
     *     A base class for comparing two pictures based on its name.
     *     Sometimes the picture name is a number and sometime a word,
     *     so always use {@link #isBothPictureNameAsNumber(Picture, Picture)}
     *     to check the picture name.
     * </p>
     *
     * @author Sukma Wardana
     * @version 1.0.0
     * @since 1.0.0
     */
    abstract class NumberAsNameComparator implements Comparator<Picture> {

        private static final String DIGIT_AND_DECIMAL_REGEX = "[^\\d]";

        private static final Logger LOG = Logger.getLogger(NumberAsNameComparator.class.getName());

        /**
         * Check whether both picture name is a number.
         *
         * @param first the first {@link Picture}.
         * @param second the second {@link Picture}.
         * @return {@code true} if only both {@link Picture} name is a number.
         */
        protected boolean isBothPictureNameAsNumber(final Picture first, final Picture second) {
            return this.isNumber(first.name()) && this.isNumber(second.name());
        }

        protected int number(final String name) {
            var number = this.fetchNumber(name);
            int result = 1;
            if (!number.isEmpty()) {
                result = Integer.parseInt(number);
            }
            return result;
        }

        private boolean isNumber(final String name) {
            boolean result = false;
            var temp = this.fetchNumber(name);
            if (!"".equals(temp)) {
                try {
                    Integer.parseInt(temp);
                    result = true;
                } catch (final NumberFormatException ex) {
                    LOG.log(
                        Level.WARNING,
                        "Picture name is not a number, ignore checking picture name is number!"
                    );
                    result = false;
                }
            }
            return result;
        }

        private String fetchNumber(final String name) {
            return name.replaceAll(DIGIT_AND_DECIMAL_REGEX, "");
        }
    }

}
