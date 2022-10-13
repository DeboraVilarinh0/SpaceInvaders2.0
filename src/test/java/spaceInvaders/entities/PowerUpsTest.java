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

class PowerUpsTest {
    @Mock
    Position position;
    @InjectMocks
    PowerUps powerUps = new PowerUps(0,0,0);

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testMoveRight() {
        when(position.getX()).thenReturn(0);
        when(position.getY()).thenReturn(0);

        Position result = powerUps.moveRight();
        Assertions.assertEquals(new Position(1, 0).getX(), result.getX());
    }

    @Test
    void testMoveLeft() {
        when(position.getX()).thenReturn(0);
        when(position.getY()).thenReturn(0);

        Position result = powerUps.moveLeft();
        Assertions.assertEquals(new Position(-1, 0).getX(), result.getX());
    }

    @Test
    void testDrawElements() {
        when(position.getX()).thenReturn(0);
        when(position.getY()).thenReturn(0);

        TextGraphics graphics = mock(TextGraphics.class);

        powerUps.drawElements(graphics, "#ff0000", "/");

    }
}

