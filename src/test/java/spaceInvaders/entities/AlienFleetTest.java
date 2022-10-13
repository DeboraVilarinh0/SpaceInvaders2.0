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

class AlienFleetTest {
    @Mock
    Position position;
    @InjectMocks
    AlienFleet alienFleet = new AlienFleet(0,0,1);

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testMoveDown() {
        when(position.getX()).thenReturn(0);
        when(position.getY()).thenReturn(0);

        Position result = alienFleet.moveDown();
        Assertions.assertEquals(new Position(0, 1).getY(), result.getY());
    }

    @Test
    void testMoveRight() {
        when(position.getX()).thenReturn(0);
        when(position.getY()).thenReturn(0);

        Position result = alienFleet.moveRight();
        Assertions.assertEquals(new Position(1, 0).getX(), result.getX());
    }

    @Test
    void testMoveLeft() {
        when(position.getX()).thenReturn(0);
        when(position.getY()).thenReturn(0);

        Position result = alienFleet.moveLeft();
        Assertions.assertEquals(new Position(-1, 0).getX(), result.getX());
    }

    @Test
    void testDrawElements() {
        when(position.getX()).thenReturn(0);
        when(position.getY()).thenReturn(0);
        TextGraphics graphics = mock(TextGraphics.class);

        alienFleet.drawElements(graphics, "#ff0000", "/");
    }
}
