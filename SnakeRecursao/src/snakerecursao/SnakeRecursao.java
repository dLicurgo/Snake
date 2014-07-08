/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakerecursao;

import java.awt.Color;
import jplay.*;

/**
 *
 * @author Dalton
 */
public class SnakeRecursao {

    public static void main(String[] args) {

        Window w = new Window(800, 600);
        Keyboard k = w.getKeyboard();
        Sprite[] s = new Sprite[150];
        Sprite comida = new Sprite("imagens\\bloco.png");
        Sound som = new Sound("sons\\musica.wav");
        Time t = new Time(400, 10, false);
        int cont = 10, sentido = 2, delay = 50, score = 0;

        double larg = comida.width;
        double alt = comida.height;

        for (int i = 0; i < s.length; i++) {

            s[i] = new Sprite("imagens\\bloco.png");
            s[i].x = 400 - i * larg;
            s[i].y = 300;

        }

        posComida(comida);

        som.play();
        t.setMinute(1);
        t.setSecond(59);

        while (!k.keyDown(Keyboard.ESCAPE_KEY)) {

            w.clear(Color.green);
            comida.draw();

            w.drawText("SCORE   " + score, 30, 30, Color.RED);
            w.drawText("TIME    " + t, 700, 30, Color.RED);

            drawSnake(s, cont);
            
            int num = cont - 1;
            moveSnake(s, num, sentido);

            w.delay(50);
            w.update();
            
            if (k.keyDown(Keyboard.UP_KEY) && sentido != 3) {
                sentido = 1;
            } else if (k.keyDown(Keyboard.RIGHT_KEY) && sentido != 4) {
                sentido = 2;
            } else if (k.keyDown(Keyboard.DOWN_KEY) && sentido != 1) {
                sentido = 3;
            } else if (k.keyDown(Keyboard.LEFT_KEY) && sentido != 2) {
                sentido = 4;
            }

        }
        
        w.exit();

    }

    private static void posComida(Sprite comida) {

        comida.x = Math.random() * 801;
        comida.y = Math.random() * 601;
    }

    private static void drawSnake(Sprite[] s, int cont) {

        for (int i = 0; i < cont; i++) {
            s[i].draw();

        }
    }

    private static void posCabeça(Sprite sprite, int sentido) {

        if (sentido == 1) {

            sprite.y = sprite.y - sprite.height;

        } else if (sentido == 2) {

            sprite.x = sprite.x + sprite.width;

        } else if (sentido == 3) {

            sprite.y = sprite.y + sprite.height;

        } else if (sentido == 4) {

            sprite.x = sprite.x - sprite.width;

        }
    }

    private static boolean moveSnake(Sprite[] s, int num, int sentido) {
        
        int num2 = num - 1;
        if(num == 0){
            return false;
        }
        if(num2 == 0){
            posCabeça(s[0], sentido);
        }
        
        s[num].x = s[num2].x;
        s[num].y = s[num2].y;
        
        moveSnake(s, num2, sentido);
        return true;
    }
}
