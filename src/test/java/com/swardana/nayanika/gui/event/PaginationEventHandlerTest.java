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

package com.swardana.nayanika.gui.event;

import com.swardana.nayanika.base.PicturesNavigation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

/**
 * Unit test for {@link PaginationEventHandler}.
 *
 * @author Sukma Wardana
 * @version 1.0.0
 * @since 1.0.0
 */
class PaginationEventHandlerTest {

    @Test
    @DisplayName("verify perform pagination movement to next")
    public void testVerifyPerformPaginationMovementToNext() {
        var mockPagination = mock(PicturesNavigation.class);
        var event = new PaginationEvent(PaginationEvent.Movement.NEXT);
        var handler = new PaginationEventHandler(mockPagination);
        handler.handle(event);

        verify(mockPagination).next();
    }

    @Test
    @DisplayName("verify perform pagination movement to previous")
    public void testVerifyPerformPaginationMovementToPrevious() {
        var mockPagination = mock(PicturesNavigation.class);
        var event = new PaginationEvent(PaginationEvent.Movement.PREVIOUS);
        var handler = new PaginationEventHandler(mockPagination);
        handler.handle(event);

        verify(mockPagination).previous();
    }

    @Test
    @DisplayName("verify perform pagination movement to beginning")
    public void testVerifyPerformPaginationMovementToBeginning() {
        var mockPagination = mock(PicturesNavigation.class);
        var event = new PaginationEvent(PaginationEvent.Movement.BEGINNING);
        var handler = new PaginationEventHandler(mockPagination);
        handler.handle(event);

        verify(mockPagination).beginning();
    }

    @Test
    @DisplayName("verify perform pagination movement to last")
    public void testVerifyPerformPaginationMovementToLast() {
        var mockPagination = mock(PicturesNavigation.class);
        var event = new PaginationEvent(PaginationEvent.Movement.LAST);
        var handler = new PaginationEventHandler(mockPagination);
        handler.handle(event);

        verify(mockPagination).last();
    }

    @Test
    @DisplayName("verify not perform pagination when movement is null")
    public void testVerifyNotPerformPagination() {
        var mockPagination = mock(PicturesNavigation.class);
        var event = new PaginationEvent(null);
        var handler = new PaginationEventHandler(mockPagination);
        handler.handle(event);

        verify(mockPagination, never()).next();
        verify(mockPagination, never()).previous();
        verify(mockPagination, never()).beginning();
        verify(mockPagination, never()).last();
    }
}