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

class AlienTest {
    @Mock
    Position position;
    @InjectMocks
    Alien alien =new Alien(0,0,1);

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testMoveDown() {
        when(position.getX()).thenReturn(0);
        when(position.getY()).thenReturn(0);

        Position result = alien.moveDown();
        Assertions.assertEquals(new Position(0, 1).getX(), result.getX());
    }

    @Test
    void testMoveRight() {
        when(position.getX()).thenReturn(0);
        when(position.getY()).thenReturn(0);

        Position result = alien.moveRight();
        Assertions.assertEquals(new Position(1, 0).getX(), result.getX());
    }

    @Test
    void testMoveLeft() {
        when(position.getX()).thenReturn(0);
        when(position.getY()).thenReturn(0);

        Position result = alien.moveLeft();
        Assertions.assertEquals(new Position(-1, 0).getX(), result.getX());
    }

    @Test
    void testDrawElements() {
        when(position.getX()).thenReturn(0);
        when(position.getY()).thenReturn(0);
        TextGraphics graphics = mock(TextGraphics.class);

        alien.drawElements(graphics, "#ff0000", "/");
    }
}

