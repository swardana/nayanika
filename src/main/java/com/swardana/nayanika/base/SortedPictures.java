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
