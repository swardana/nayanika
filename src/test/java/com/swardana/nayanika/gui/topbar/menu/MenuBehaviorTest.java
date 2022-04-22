package com.swardana.nayanika.gui.topbar.menu;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
        var behavior = new MenuBehavior(mockView);
        behavior.onStartSlideShow();
        verify(mockView).showStopSlideMenu();
    }

    @Test
    @DisplayName("verify when on stop slide-show menu executed")
    public void testOnStopSlideShowMenuExecuted() {
        var mockView = mock(MenuView.class);
        var behavior = new MenuBehavior(mockView);
        behavior.onStopSlideShow();
        verify(mockView).showStartSlideMenu();
    }

}
