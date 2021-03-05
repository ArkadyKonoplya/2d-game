package Game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class LifeBody extends StaticBody { //////// this class is used for showing how many lifes the player has and how many
    //gems he already collected,player just destroys some lifebody when he loses a life
    //and adds a gem to him collection,when collects it,it has two statc counter variables which traces the number of object of two types

    static int amount_life=0;
    static int amount_gem=0;
    static int score = 0;

    public static int getAmount_life() {
        return amount_life;
    }

    public static void setAmount_life(int amount_life) {
        LifeBody.amount_life = amount_life;
    }

    public static int getAmount_gem() {
        return amount_gem;
    }
    public static int getScore() {
        return score;
    }
    public static void setScore(int score)
    {
    	LifeBody.score =score;
    }
    public static void setAmount_gem(int amount_gem) {
        LifeBody.amount_gem = amount_gem;
    }

    public LifeBody(World w, int type) { ////world,where the game will be played,type=0 for life ,type=1 for gem
        super(w, new BoxShape(0.7f,0.7f));

        if(type==0)
        {
            amount_life++;
            setPosition(new Vec2(-40+amount_life*4,48)); //origin 48
            addImage(new BodyImage("data/love.png",1.4f));
        }
        else
        {
            amount_gem++;
            setPosition(new Vec2(30-amount_gem*4,48)); //origin 48
            addImage(new BodyImage("data/gem11.png",1.4f));
        }
    }

}
