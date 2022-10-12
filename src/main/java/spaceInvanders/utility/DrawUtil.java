package spaceInvanders.utility;

import com.googlecode.lanterna.screen.TerminalScreen;

public class DrawUtil {
    static int col = Constants.WIDTH/9;

    public static void menuText(TerminalScreen screen) {
        screen.newTextGraphics().putString(col, 3, "MENU");
        screen.newTextGraphics().putString(col, 9, "Press ENTER to process to the game");
        screen.newTextGraphics().putString(col, 11, "Press I for instructions");
        screen.newTextGraphics().putString(col, 15, "Press DELETE to exit");
        screen.newTextGraphics().putString(col, 20, "NOTE: To return to MainMenu press 'Q'");


    }

    public static void instructionsText(TerminalScreen screen) {

        screen.newTextGraphics().putString(col, 2, "************* I N S T R U C T I O N S **************");
        screen.newTextGraphics().putString(col, 3, "*                                                  *");
        screen.newTextGraphics().putString(col, 4, "*                GAME OBJECTIVE:                   *");
        screen.newTextGraphics().putString(col, 5, "*                                                  *");
        screen.newTextGraphics().putString(col, 6, "*    The objective of the game is for the player   *");
        screen.newTextGraphics().putString(col, 7, "* to destroy every alien spaceship.                *");
        screen.newTextGraphics().putString(col, 8, "*    The player controls a laser and can move it   *");
        screen.newTextGraphics().putString(col, 9, "* left and right at the bottom of the screen.      *");
        screen.newTextGraphics().putString(col, 10, "*    When the game begins, the alien ships are     *");
        screen.newTextGraphics().putString(col, 11, "* located at the top of the screen. They move      *");
        screen.newTextGraphics().putString(col, 12, "* horizontally and, upon reaching the edge of the  *");
        screen.newTextGraphics().putString(col,13,"* playing field,descend to the next row, gradually *");
        screen.newTextGraphics().putString(col, 14, "* getting closer to the player. Note that the      *");
        screen.newTextGraphics().putString(col, 15, "* the alien ship with the color red needs to be    *");
        screen.newTextGraphics().putString(col, 16, "* shoot TWICE to die.                              *");
        screen.newTextGraphics().putString(col, 17, "*   After all the aliens are destroyed, the player *");
        screen.newTextGraphics().putString(col, 18, "* the player WINS.                                 *");
        screen.newTextGraphics().putString(col, 19, "*   The aliens try to destroy the player's cannon  *");
        screen.newTextGraphics().putString(col, 20, "* by shooting at it. If an alien hits the cannon,  *");
        screen.newTextGraphics().putString(col,21,"* the player LOSES.                                *");
        screen.newTextGraphics().putString(col, 22, "*                                                  *");
        screen.newTextGraphics().putString(col, 23, "*               GAMEPLAY CONTROLS:                 *");
        screen.newTextGraphics().putString(col, 24, "*                                                  *");
        screen.newTextGraphics().putString(col, 25, "*            ARROW UP    --> shooting              *");
        screen.newTextGraphics().putString(col, 26, "*            ARROW LEFT  --> moves left            *");
        screen.newTextGraphics().putString(col, 27, "*            ARROW RIGHT --> moves right           *");
        screen.newTextGraphics().putString(col, 28, "*            Q           --> quit             *");
        screen.newTextGraphics().putString(col, 29, "*                                                  *");
        screen.newTextGraphics().putString(col, 30, "*               press Q to go back                 *");
        screen.newTextGraphics().putString(col, 31, "****************************************************");


    }


}
