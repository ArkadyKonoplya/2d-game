package Game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import static java.lang.System.exit;

public class Me extends DynamicBody { //////////this class is the body of me,that means the player is object of this class
    private int state;////state = 0 for standing or, static;state=-1 for moving left ,
    // state=1 for moving right
    private BodyImage left_standing,standing,left_walking,walking; ///left_standing=view of standing facing left,
    ////standing = view of standing facing right,left_walking= view of left face moving left,and walking is viceversa
    private LifeBody lifeBody1,lifeBody2;
    int _no_of_life=3;
    World world;


    public Me(World w, Shape s) {
        super(w, s);
        state=0;

        this.world=w;
        left_standing=new BodyImage("data/left_standing.png", 5f);
        standing=new BodyImage("data/standing.png", 5f);
        left_walking=new BodyImage("data/left_walking.gif", 5f);
        walking=new BodyImage("data/right_walking.gif", 5f);

        addImage(standing);

        lifeBody1=new LifeBody(w,0);
        lifeBody2=new LifeBody(w,0);
        initialize();

    }
    public void startWalkingLeft(float speed)  ///makes left-ward movement,it takes speed as positive value,but moves the body to negative direction
    {
        //super.startWalking(-speed);
        //move(new Vec2( -10000,getPosition().y));
        setLinearVelocity(new Vec2(speed * -1 ,getLinearVelocity().y));
    }

    public void startWalkingRight(float speed)  ///makes right-ward movement,it takes speed as positive value,moves the body to positive direction
    {
        //super.startWalking(speed);
        //move(new Vec2( 10000,getPosition().y));
        setLinearVelocity(new Vec2(speed,getLinearVelocity().y));
    }

    public void setState(int state)
    {
        this.state=state;
    }
    public int getState()
    {
        return state;
    }
    public void leftCommand(float speed) ///this commands for moving at left direction,if the body is already in walking left,it does nothing,
            //if the body is in static,body promts for left moving ,else if body is moving rightyard,then body stops
    {
        if(state==0||state==-1)
        {
            state=-1;
            startWalkingLeft(speed);
            removeAllImages();
            addImage(left_walking);
        }
        else if(state==1)
        {
            state=0;
            //stopWalking();
            setLinearVelocity(new Vec2(0,getLinearVelocity().y));
            removeAllImages();
            addImage(left_standing);
        }
    }

    public void rightCommand(float speed) ///this commands for moving at right direction,if the body is already in walking right,it does nothing,
    //if the body is in static,body promts for right moving ,else if body is moving leftyard,then body stops
    {
        if(state==0||state==1)
        {
            state=1;
            startWalkingRight(speed);
            removeAllImages();
            addImage(walking);
        }
        else if(state==-1)
        {
            state=0;
            //stopWalking();
            setLinearVelocity(new Vec2(0,getLinearVelocity().y));
            removeAllImages();
            addImage(standing);
        }
    }

    public void stopWalkingLeft() { ///stops walking left and changes icon
        state=0;
        setLinearVelocity(new Vec2(0,getLinearVelocity().y));
        removeAllImages();
        addImage(left_standing);
    }

    public void stopWalkingRight() {  ///stops walking left and changes icon accordingly
        setLinearVelocity(new Vec2(0,getLinearVelocity().y));
        removeAllImages();
        addImage(standing);
    }

    public void initialize() {  ///initialize my position,when game starts ,or i loose a life
        setPosition(new Vec2(0,5));
        _no_of_life--;
        LifeBody.setAmount_life(_no_of_life);
    }

    public void addPoint() {  /////add points when i collides with a gem
        LifeBody point1=new LifeBody(world,1);
       /* if(point1.getAmount_gem()==3)
        {
            System.out.println("Congratulation!,You won the game.");
            exit(0);
            ////win
        }
        else ///open a mini-puzzle or,tictac toe game
        {
            
        }*/
    }
}


/*


 */