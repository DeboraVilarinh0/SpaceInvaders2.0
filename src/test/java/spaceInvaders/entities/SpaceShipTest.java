package spaceInvaders.entities;

import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import spaceInvaders.model.Position;

import java.util.List;

import static org.mockito.Mockito.*;

class SpaceShipTest {
    @Mock
    List<SpaceShipHP> spaceShipHP;
    @Mock
    Position position;
    @InjectMocks
    SpaceShip spaceShip = new SpaceShip(0, 0);

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testMoveRight() {
        when(position.getX()).thenReturn(0);
        when(position.getY()).thenReturn(0);

        Position result = spaceShip.moveRight();
        Assertions.assertEquals(new Position(1, 0).getX(), result.getX());
    }

    @Test
    void testMoveLeft() {
        when(position.getX()).thenReturn(0);
        when(position.getY()).thenReturn(0);

        Position result = spaceShip.moveLeft();
        Assertions.assertEquals(new Position(-1, 0).getX(), result.getX());
    }

    @Test
    void testDrawElements() {
        when(position.getX()).thenReturn(0);
        when(position.getY()).thenReturn(0);

        TextGraphics graphics = mock(TextGraphics.class);

        spaceShip.drawElements(graphics, "#ff0000", "/");

    }
}

