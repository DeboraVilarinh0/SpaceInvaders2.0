package spaceInvaders.entities;

import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import spaceInvaders.model.Position;

import static org.mockito.Mockito.*;

class ElementTest {
    @Mock
    Position position;
    @InjectMocks
    Element element = new Element(0,0) {

    };

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void testDrawElements() {
        when(position.getX()).thenReturn(0);
        when(position.getY()).thenReturn(0);

        TextGraphics graphics = mock(TextGraphics.class);

        element.drawElements(graphics, "#ff0000", "/");


    }
}
