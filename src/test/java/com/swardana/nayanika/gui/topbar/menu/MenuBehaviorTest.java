package com.swardana.nayanika.gui.topbar.menu;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.swardana.nayanika.base.Slide;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javafx.beans.property.SimpleBooleanProperty;

/**
 * Unit test for {@link MenuBehavior}.
 *
 * @author Sukma Wardana
 * @version 1.0.0
 * @since 1.0.0
 */
class MenuBehaviorTest {

    @Test
    @DisplayName("verify when on start slide-show menu executed")
    public void testOnStartSlideShowMenuExecuted() {
        var mockView = mock(MenuView.class);
        var mockSlide = mock(Slide.class);
        when(mockSlide.isRunningProperty()).thenReturn(new SimpleBooleanProperty(true));
        var behavior = new MenuBehavior(mockView, mockSlide);
        behavior.onStartSlideShow();
        verify(mockSlide).play();
    }

    @Test
    @DisplayName("verify when on stop slide-show menu executed")
    public void testOnStopSlideShowMenuExecuted() {
        var mockView = mock(MenuView.class);
        var mockSlide = mock(Slide.class);
        when(mockSlide.isRunningProperty()).thenReturn(new SimpleBooleanProperty(true));
        var behavior = new MenuBehavior(mockView, mockSlide);
        behavior.onStopSlideShow();
        verify(mockSlide).stop();
    }

}
