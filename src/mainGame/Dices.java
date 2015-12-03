package mainGame;

import mapClasses.GameMap;
import org.newdawn.slick.Color;
import org.newdawn.slick.Image;

/**
 * Created by Kingo on 03-Dec-15.
 */
public class Dices {
    int combinedValue = 0;
    int die1, die2;
    int rolled;
    boolean calculateNewDice;

    public Dices() {
    }

    public String getButtonText(boolean yourTurn, boolean DiceRolled) {
        if (yourTurn) {
            if (!DiceRolled) {
                State_PlayingWindow.gameInfo = "Your turn! Press 'Roll Dice' to roll the dice";
                return "Roll Dice";
            } else {
                return "Rolled: " + combinedValue;
            }
        } else {
            if (!calculateNewDice) {
                return "Rolled: " + combinedValue;
            } else {
                return "Waiting for roll";
            }
        }
    }

    public Image[] getDice(boolean yourTurn) {
        Image[] tmp_Images = new Image[2];
            if (PlayerStats.die1 != 0 && PlayerStats.die2 != 0 ) {
                tmp_Images[0] = Texture.diceSprites.getSprite(die1, 0);
                tmp_Images[1] = Texture.diceSprites.getSprite(die2, 0);
            } else {
                tmp_Images[0] = Texture.butt;
                tmp_Images[1] = Texture.butt;
            }
        return tmp_Images;
    }

    public void DiceRolled(GameMap map) {
            System.out.println("Die 1: " + PlayerStats.die1 + " Die 2: " + PlayerStats.die2);
            die1 = PlayerStats.die1 - 1;
            die2 = PlayerStats.die2 - 1;
            combinedValue = die1 + die2 + 2;
            rolled++;
            map.addResources(die1+die2);
            calculateNewDice = false;
            PlayerStats.diceUsed = true;
    }
}

/*
if (yourTurn) {
            //If it your turn
            if (!DiceRolled) {
                State_PlayingWindow.gameInfo = "Your turn! Press 'Roll Dice' to roll the dice";
                rollDice.AddText("Roll Dice", Color.black);
            } else {
                rollDice.AddText("Rolled: " + combinedValue, Color.black);
            }
            if (PlayerStats.die1 != 0 && PlayerStats.die2 != 0) {
                Texture.diceSprites.getSprite(die1, 0).draw(x + 5, y + 5, 50, 50);
                Texture.diceSprites.getSprite(die2, 0).draw(x + 60, y + 5, 50, 50);
            } else {
                Texture.butt.draw(x + 5, y + 5, 50, 50);
                Texture.butt.draw(x + 60, y + 5, 50, 50);
            }
            if (rollDice.isWithin(gc)) { //Add " && !DiceRolled" after testing!!!!

                System.out.println("Dice rolled");
                PlayerStats.diceRoll = true;
                DiceRolled = true;
            }
            if (!PlayerStats.diceUsed && PlayerStats.die1 > 0 && PlayerStats.die2 > 0) {
                System.out.println("Die 1: " + PlayerStats.die1 + " Die 2: " + PlayerStats.die2);
                die1 = PlayerStats.die1 - 1;
                die2 = PlayerStats.die2 - 1;
                combinedValue = die1 + die2 + 2;
                rolled++;
                map.addResources(die1+die2);
                calculateNewDice = false;
                PlayerStats.diceUsed = true;
            }
        } else {
            //If it isn't your turn
            if (!calculateNewDice) {
                rollDice.AddText("Rolled: " + combinedValue, Color.black);
            } else {
                rollDice.AddText("Waiting for roll", Color.black);
            }
            if (PlayerStats.die1 != 0 && PlayerStats.die2 != 0) {
                Texture.diceSprites.getSprite(die1, 0).draw(x + 5, y + 5, 50, 50);
                Texture.diceSprites.getSprite(die2, 0).draw(x + 60, y + 5, 50, 50);
            } else {
                Texture.butt.draw(x + 5, y + 5, 50, 50);
                Texture.butt.draw(x + 60, y + 5, 50, 50);
            }
            if (!PlayerStats.diceUsed && PlayerStats.die1 > 0 && PlayerStats.die2 > 0) {
                System.out.println("Die 1: " + PlayerStats.die1 + " Die 2: " + PlayerStats.die2);
                die1 = PlayerStats.die1-1;
                die2 = PlayerStats.die2-1;
                combinedValue = die1 + die2 + 2;
                rolled++;
                map.addResources(die1+die2);
                calculateNewDice = false;
                PlayerStats.diceUsed = true;
            }
        }
 */
