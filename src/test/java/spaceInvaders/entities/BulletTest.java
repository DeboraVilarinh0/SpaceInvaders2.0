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

class BulletTest {
    @Mock
    Position position;
    @InjectMocks
    Bullet bullet = new Bullet(0,0);

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testBulletMovementUP() {
        when(position.getX()).thenReturn(0);
        when(position.getY()).thenReturn(0);

        Position result = bullet.bulletMovementUP();
        Assertions.assertEquals(new Position(0, -1).getY(), result.getY());
    }

    @Test
    void testBulletMovementDOWN() {
        when(position.getX()).thenReturn(0);
        when(position.getY()).thenReturn(0);

        Position result = bullet.bulletMovementDOWN();
        Assertions.assertEquals(new Position(0, 1).getY(), result.getY());
    }


    @Test
    void testDrawElements() {
        when(position.getX()).thenReturn(0);
        when(position.getY()).thenReturn(0);
        TextGraphics graphics = mock(TextGraphics.class);

        bullet.drawElements(graphics, "#ff0000", "/");


    }
}
