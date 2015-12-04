package mainGame;

import org.newdawn.slick.Image;

/**
 * Created by Kingo on 03-Dec-15.
 */
public class Dice {
    int combinedValue = 0;
    int die1, die2;
    int rolled;
    boolean calculateNewDice;
    float[] procentValue;

    public Dice() {
        procentValue = new float[12];
    }

    //Display the text on the "Roll Dice" button
    public String getButtonText(boolean yourTurn, boolean DiceRolled) {
        //If its your turn
        if (yourTurn) {
            //If you have rolled the dice.
            if (!DiceRolled) {
                State_PlayingWindow.gameInfo = "Your turn! Press 'Roll Dice' to roll the dice";
                return "Roll Dice";
            } else {
                return "Rolled: " + combinedValue;
            }
            //If it isn't your turn
        } else {
            //If the player who turn it is, have rolled the dice
            if (!calculateNewDice) {
                return "Rolled: " + combinedValue;
            } else {
                //If he havn't
                return "Waiting for roll";
            }
        }
    }

    //Return the corrent texture for the dice that were rolled.
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

    //When the dice are rolled, this method will save the roll, add reosources and check if it a 7, and should there move the rubber.
    public void DiceRolled(GameMap map) {
            System.out.println("Die 1: " + PlayerStats.die1 + " Die 2: " + PlayerStats.die2);
            die1 = PlayerStats.die1 - 1;
            die2 = PlayerStats.die2 - 1;
            combinedValue = die1 + die2 + 2;
            rolled++;
            map.addResources(combinedValue);
            calculateNewDice = false;
            PlayerStats.diceUsed = true;
        if (combinedValue == 7 && PlayerStats.playerturn[PlayerStats.ID-1])
            GameMap.moveThief = true;
    }

    //calculate the procentages for the statistics.
    public float getDicePercentages(int index) {

        for (int i = 0; i < 12; i++) {
            float procent = (float) PlayerStats.rolledDiceStatistics[i] / (float) rolled;
            procentValue[i] = procent * 120;
        }
        return procentValue[index];
    }
}