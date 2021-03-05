package Game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import java.util.Random;

public class MovingSword extends Walker {
    Me player;
    StaticBody ground; ///this class performs the function of moving enemy,when it attackes the player,players life reduces,when it touches ground,this item is recycled and thrown again from top
    float x,y;
    public MovingSword(World world, Shape shape, float x,float y,StaticBody ground) {
        super(world, shape);
        this.x=x;
        this.y=y;
        this.ground=ground;
        setPosition(new Vec2(x,y));
        setGravityScale(0.5f);
        setupPositionandVelocity();

    }

    public Me getPlayer() {
        return player;
    }

    public void setPlayer(Me player) {
        this.player = player;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public  void setCollisionWithPlayer()   ///performs the fucntionality when it touches player
    {
        addCollisionListener(new CollisionListener() {
            @Override
            public void collide(CollisionEvent collisionEvent) {
                if(collisionEvent.getOtherBody()==getPlayer())
                {
                    getPlayer().initialize();
                }
                else if(collisionEvent.getOtherBody()==ground)
                {
                    setupPositionandVelocity();
                }
            }
        });
    }

    /*
        public StaticBody getCollider1() {
            return collider1;
        }

        public void setCollider1(StaticBody collider1) {
            this.collider1 = collider1;
        }

        public StaticBody getCollider2() {
            return collider2;
        }

        public void setCollider2(StaticBody collider2) {
            this.collider2 = collider2;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
        public void setColliderListener1(StaticBody staticBody)///sets collider listener,when collides with 1st collider,it makes appropriate direction to move
        {
            setCollider1(staticBody);
            addCollisionListener(new CollisionListener() {
                @Override
                public void collide(CollisionEvent collisionEvent) {
                    if(collisionEvent.getOtherBody()==player) {
                        setupPositionandVelocity();
                        return;
                    }
                    if(collisionEvent.getOtherBody()==getCollider1())
                    {
                        if(type==0)
                        {
                            startWalking(5);
                        }
                        else
                            jump(5);
                    }
                    else if( (getCollider2()!=null)&&collisionEvent.getOtherBody()==getCollider2())
                    {
                        if(type==0)
                        {
                            startWalking(-5);
                            vx=-5;
                            vy=0;
                        }
                        else
                        {
                            jump(-5);
                            vx=0;
                            vy=-5;
                        }
                    }
                }
            });
        }

        public void setColliderListener2(StaticBody staticBody)///sets collider listener,when collides with 2nd collider,it makes appropriate direction to move
        {
            setCollider2(staticBody);

            addCollisionListener(new CollisionListener() {
                @Override
                public void collide(CollisionEvent collisionEvent) {
                    if(collisionEvent.getOtherBody()==player)
                    {
                        setLinearVelocity(new Vec2(vx,vy));
                        setupPositionandVelocity();
                        return;
                    }

                    if((getCollider1()!=null)&&collisionEvent.getOtherBody()==getCollider1())
                    {
                        if(type==0)
                        {
                            startWalking(5);
                            vx=5;
                            vy=0;
                        }
                        else
                        {
                            jump(5);
                            vx=0;
                            vy=5;
                        }
                    }
                    else if( collisionEvent.getOtherBody()==getCollider2())
                    {
                        if(type==0)
                        {
                            startWalking(-5);
                            vx=-5;
                            vy=0;
                        }
                        else
                        {
                            jump(-5);
                            vx=0;
                            vy=-5;
                        }
                    }
                }
            });
        }
    */
    private void setupPositionandVelocity() {  ///when the sword or,this classes object touches ground,it is recycled,this recycling is done here.
        setPosition(new Vec2(x,y));
        setAngleDegrees(180.0f);
        Random random=new Random();
        int int_val=random.nextInt()%100;
        float float_val=(float) (((float)int_val)/12.00);
        float_val-=4.00;
        setLinearVelocity(new Vec2(float_val,-5));
        setAngularVelocity(0.5f);
    }


}
