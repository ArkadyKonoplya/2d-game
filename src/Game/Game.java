package Game;

import city.cs.engine.*;
import city.cs.engine.Shape;
import org.jbox2d.common.Vec2;

import Game.Levels.Level;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import static java.lang.Math.abs;
import static java.lang.Math.max;

/**
 * A world with some bodies.
 */
public class Game {

 
    /** Run the game. */
    public static void main(String[] args) {
    	new Level();
    }
}


