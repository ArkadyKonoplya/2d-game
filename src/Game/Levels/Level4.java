package Game.Levels;
/*
 * LEVEL 4
 * This is the level 4 and final level of our game. The play has only 30 seconds to catch all the gems. 
 * 
 * */
import static java.lang.Math.abs;
import static java.lang.Math.max;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Random;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.net.URL;

import org.jbox2d.common.Vec2;

import Game.GameLauncher;
import Game.LifeBody;
import Game.Me;
import Game.MovingSword;
import city.cs.engine.BodyImage;
import city.cs.engine.BoxShape;
import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import city.cs.engine.PolygonShape;
import city.cs.engine.Shape;
import city.cs.engine.StaticBody;
import city.cs.engine.UserView;
import city.cs.engine.World;

public class Level4 extends JFrame implements ActionListener {
	    /** The World in which the bodies move and interact. */
	Timer timer = new Timer(1000,this);
	int totalTime =30000;// Total time to find all the gems
	 JLabel lblScore = new JLabel("Score : "+0);// The total number of gems collected
	 JLabel lblTimer = new JLabel("Time Remaining : "+0);// The time remaining
	 JLabel lblLifes  = new JLabel("Life Remaining : "+0);// The lives remaining
	 Clip audioClip;
	 AudioInputStream audioStream;
	    private World world;

	    /** A graphical display of the world (a specialised JPanel). */
	    private UserView view;

	    Me student; ////player who will play the game as a character

	    StaticBody platform[],ground,gems[],attackers[];  ///platform is the array which makes the environment,it contains some static body like bricks,
	    ///ground is the land ,gems are targeted object,player will collect those,attackers are some static sword,which attacks player
	    /** Initialise a new Game. */
	    public Level4() {
	    	this.setTitle("LEVEL 4");
	        // make the world
	        platform=new StaticBody[40];
	        world = new World();
	        //world.setGravity(0);

	        makePlatform();  ///making the platform
	        // make a character
	        makeMe();
	        // add more bodies here
	        makeGem(); //makes the gems
	        makeMovingEnemy(); /// there are also some moving swords ,which attack the player,these are created here,all there functionalities are done here,like: when they
	        //attack the player ,one of the player's life ends,it is done by collision listener

	        makePoisonSword();
	        ///makes the posions and static sword

	        //makes the display
	        makeDisplayView();
	        // start our game world simulation!

	        System.out.println("In this game ,There are three gems at three different places.\n" +
	                "Player will find each and solve a puzzle.If all the items are found and puzzles " +
	                "\nsolved," +
	                "player wins.There are also some static and dynamic enemies which attack the \nplayer." +
	                "If the player is attacked two times,game ends and he loses the game.");

	        world.start();
	        timer.start();
	    }

	    private void makeMe() {
	        Shape studentShape = new PolygonShape(
	                -0.11f,1.8f,
	                0.87f,1.48f,
	                0.99f,0.29f,
	                0.44f,-2.32f,
	                -1.32f,-2.27f,
	                -1.24f,1.21f);
	        student = new Me(world, studentShape);
	        //student.setPosition(new Vec2(0, 4));
	    }

	    private void makePlatform() {

	        // make the ground
	        Shape shape = new BoxShape(10000, 2);
	        ground = new StaticBody(world, shape);
	        ground.setPosition(new Vec2(-50, 0));

	        // make a platform
	        Shape platform1Shape = new BoxShape(2, 0.25f);
	        platform[0] = new StaticBody(world, platform1Shape);
	        platform[0].setPosition(new Vec2(-42, 4.0f));


	        // make a platform
	        Shape platform2Shape = new BoxShape(4, 0.25f);
	        platform[1] = new StaticBody(world, platform2Shape);
	        platform[1].setPosition(new Vec2(-10, 5.3f));



	        // make a platform
	        Shape platform3Shape = new BoxShape(4, 0.25f);
	        platform[2] = new StaticBody(world, platform3Shape);
	        platform[2].setPosition(new Vec2(-35, 0.1f));


	        // make a platform

	        Shape platform4Shape = new BoxShape(4, 0.25f);
	        platform[3] = new StaticBody(world, platform4Shape);
	        platform[3].setPosition(new Vec2(-5, 0.1f));


	        // make a platform
	        Shape platform5Shape = new BoxShape(5, 0.35f);
	        platform[4] = new StaticBody(world, platform5Shape);
	        platform[4].setPosition(new Vec2(42, 0.1f));
	        //platform[4].setFillColor();

	        // make a platform

	        Shape platform6Shape = new BoxShape(5, 0.35f);
	        platform[5] = new StaticBody(world, platform6Shape);
	        platform[5].setPosition(new Vec2(22, 0.1f));


	        Shape platform7Shape = new BoxShape(5, 0.35f);
	        platform[6] = new StaticBody(world, platform7Shape);
	        platform[6].setPosition(new Vec2(7, 10.1f));


	 


	        platform[9] = new StaticBody(world, platform5Shape);
	        platform[9].setPosition(new Vec2(25, 40f));


	        platform[10] = new StaticBody(world, platform5Shape);
	        platform[10].setPosition(new Vec2(34, 5.1f));



	        playSong("data/Level2.wav");
	        
	       
//	        platform[23] = new StaticBody(world, platform8Shape);
	  //      platform[23].setPosition(new Vec2(-13, 18.1f));

	    }
	    private void makeMovingEnemy()
	    {

	        /*Shape bridgeshape1=new PolygonShape(
	                -2,1,-2,-1,2,-1,2,1,1.8f,1,1.8f,0.8f,-1.8f,0.8f,-1.8f,1
	        );*/

	        Shape mswordshape=new BoxShape(0.5f,2.5f);
	        MovingSword movingSword1=new MovingSword(world,mswordshape,-40,80,ground);
	        MovingSword movingSword2=new MovingSword(world,mswordshape,-20,80,ground);
	        MovingSword movingSword3=new MovingSword(world,mswordshape,50,80,ground);
	        MovingSword movingSword4=new MovingSword(world,mswordshape,30,80,ground);
	        MovingSword movingSword5=new MovingSword(world,mswordshape,10,80,ground);

	        movingSword1.addImage(new BodyImage("data/sword1.png",5f));
	        movingSword2.addImage(new BodyImage("data/sword1.png",5f));
	        movingSword3.addImage(new BodyImage("data/sword1.png",5f));
	        movingSword4.addImage(new BodyImage("data/sword1.png",5f));
	        movingSword5.addImage(new BodyImage("data/sword1.png",5f));

	        movingSword1.setPlayer(student);
	        movingSword1.setCollisionWithPlayer();
	        movingSword2.setPlayer(student);
	        movingSword2.setCollisionWithPlayer();
	        movingSword3.setPlayer(student);
	        movingSword3.setCollisionWithPlayer();
	        movingSword4.setPlayer(student);
	        movingSword4.setCollisionWithPlayer();
	        movingSword5.setPlayer(student);
	        movingSword5.setCollisionWithPlayer();

	        /*Carrier bridge=new Carrier(world,bridgeshape1,1,-40,6,student);

	        bridge.addImage(new BodyImage("data/bridge.png",1f));
	        bridge.setColliderListener1(platform[0]);
	        bridge.setColliderListener2(platform[2]);
	        bridge.setPosition(new Vec2(-40,6));
	        bridge.setLinearVelocity(new Vec2(0,5));*/



	    }
	    private void makePoisonSword()
	    {
	        attackers=new StaticBody[5];
	        Shape poisonshape1=new BoxShape(
	                1,1.5f
	        );
	        Shape poisonshape2=new BoxShape(
	            1,1.5f
	        );
	        Shape swordshape1=new BoxShape(
	                0.5f,2.5f
	        );

	        Shape swordshape2=new BoxShape(
	                2.5f,0.5f
	        );

	        attackers[0]=new StaticBody(world,poisonshape1);
	        attackers[1]=new StaticBody(world,poisonshape2);
	        attackers[2]=new StaticBody(world,swordshape2);

	        attackers[0].addImage(new BodyImage("data/poison2.png",3f));
	        attackers[1].addImage(new BodyImage("data/poison1.png",2f));
	        attackers[2].addImage(new BodyImage("data/sword2.png",1f));

	        attackers[3]=new StaticBody(world,swordshape1);
	        attackers[3].addImage(new BodyImage("data/sword1.png",5f));

	        attackers[4]=new StaticBody(world,swordshape1);
	        attackers[4].addImage(new BodyImage("data/sword1.png",5f));


	        int distance=new Random().nextInt()%5+10;

	        attackers[0].setPosition(new Vec2(-8-1*distance+0,3));
	        attackers[1].setPosition(new Vec2(-10+2*distance+0,3));
	        attackers[2].setPosition(new Vec2(-10+4*distance+0,3));


	        attackers[3].setPosition(new Vec2(21,18));
	        attackers[4].setPosition(new Vec2(-38,5));

	        for (int i=0;i<5;i++)
	        {
	            int index=i;
	            attackers[index].addCollisionListener(new CollisionListener() {
	                @Override
	                public void collide(CollisionEvent collisionEvent) {
	                    if(collisionEvent.getOtherBody()==student)
	                    {
	                        student.initialize();
	                    }
	                }
	            });
	        }

	    }
	    private void makeGem()
	    {
	        gems=new StaticBody[3];
	        Random random1=new Random();
	      /*  int indicator1=random1.nextInt()%4+1;
	        Random random2=new Random();
	        int indicator2=random2.nextInt()%4+1;
	        while (indicator2==indicator1)
	        {
	            indicator2=random2.nextInt()%4+1;
	        }
	        int indicator3=abs(indicator2-indicator1);
	        if(indicator3==indicator1||indicator3==indicator2)
	        {
	            indicator3=max(indicator1,indicator2)+1;
	        }
*/
	        /*Shape gemshape1 = new PolygonShape(
	                -0.1f,0.1f,
	                -0.2f,0.0f,
	                0.0f,-0.2f,
	                0.2f,0.0f,
	                0.1f,0.1f,
	                -0.1f,0.1f);
	        Shape gemshape2=new PolygonShape(
	                -0.1f,0.1f,
	                -0.2f,0.0f,
	                -0.1f,-0.1f,
	                0.1f,0.1f,
	                0.2f,0.0f,
	                0.1f,0.1f,
	                -0.1f,0.1f
	                );*/ ////getting error in polygon isn't convex
	        Shape gemshape1=new BoxShape(1,1);
	        Shape gemshape2=new BoxShape(2,1);

	      //  if(indicator1<3)
	       // {
	            gems[0]=new StaticBody(world,gemshape1);
	            gems[0].addImage(new BodyImage("data/gem11.png", 2f));
	       // }
	      //  else
	       // {
	            gems[0]=new StaticBody(world,gemshape2);
	            gems[0].addImage(new BodyImage("data/gem44.png", 2f));
	     //   }

	        gems[1]=new StaticBody(world,gemshape1);
	        gems[1].addImage(new BodyImage("data/gem22.png", 2f));
	        gems[2]=new StaticBody(world,gemshape1);
	        gems[2].addImage(new BodyImage("data/gem33.png", 2f));

	        gems[0].setPosition(new Vec2(platform[1].getPosition().x-3,platform[1].getPosition().y+2));
	        gems[1].setPosition(new Vec2(platform[6].getPosition().x-3,platform[6].getPosition().y+2));
	        gems[2].setPosition(new Vec2(platform[10].getPosition().x-3,platform[10].getPosition().y+2));

	        for(int i=0;i<3;i++)
	        {
	            final int index=i;
	            gems[i].addCollisionListener(new CollisionListener() {
	                StaticBody mygem=gems[index];
	                @Override
	                public void collide(CollisionEvent collisionEvent) {
	                    if(collisionEvent.getOtherBody()==student)
	                    {
	                        mygem.destroy();
	                        student.addPoint();
	                        
	                    }
	                }
	            });
	        }

	    }
	    public void makeDisplayView()
	    {
	        // make a view with device width and height
	        Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
	        System.out.println(screensize);
	        view = new UserView(world, screensize.width, screensize.height * 2 -  160); //(2000, 2000)
	       
	        lblScore.setBounds(400, 500, 50, 30);
	        lblLifes.setBounds(200, 500,50,30);
	       
	        lblTimer.setBounds(300, 500, 50, 30);
	        view.add(lblScore);
	        view.add(lblTimer);
	        view.add(lblLifes);
	        //view = new UserView(world, 2000, 2000); //(2000, 2000)
	        try {
	            view.setBackground((Color.cyan));
	        }catch (Exception e) {
	            e.printStackTrace();
	        }

	        // uncomment this to draw a 1-metre grid over the view
	         //view.setGridResolution(5);

	        // add some mouse actions
	        // add this to the view, so coordinates are relative to the view
	        //view.addMouseListener(new MouseHandler(view));
	        //view.addKeyListener(new KeyHandler(view));
	        // add the view to a frame (Java top level window)
	        //frame = new JFrame("Basic world");

	        this.add(view);
	        // enable the frame to quit the application
	        // when the x button is pressed
	        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        this.setLocationByPlatform(true);
	        // don't let the frame be resized
	        
	        // size the frame to fit the world view
	        this.pack();
	        this.setExtendedState( this.getExtendedState()|JFrame.MAXIMIZED_BOTH );
	        this.setLocationRelativeTo(null);
	        this.setResizable(false);
	        // finally, make the frame visible
	        addKeyboardListener(this);

	        this.setVisible(true);
	        //System.out.println("ewrer");
	    }

	    private void addKeyboardListener(JFrame frame) {
	        frame.addKeyListener(new KeyListener() {
	            @Override
	            public void keyTyped(KeyEvent keyEvent) {

	            }

	            @Override
	            public void keyPressed(KeyEvent keyEvent) {
	                //System.out.println(""+keyEvent.getKeyCode());
	                if(keyEvent.getKeyCode()==37)
	                {
	                    student.leftCommand(5);
	                    //student.setPosition(new Vec2(student.getPosition().x-1,student.getPosition().y));//float)(view.getAlignmentX()-1.00));
	                }
	                else if(keyEvent.getKeyCode()==39)
	                {
	                    student.rightCommand(5);
	                    //student.setPosition(new Vec2(student.getPosition().x+1,student.getPosition().y));
	                }
	                else if(keyEvent.getKeyCode()==40)
	                {
	                    //student.jump(10);
	                    //student.setPosition(new Vec2(student.getPosition().x,student.getPosition().y-1));//float)(view.getAlignmentX()-1.00));
	                }

	                if(keyEvent.getKeyCode()==38||keyEvent.getKeyCode()==32)
	                {
	                    //student.setJumpSpeed(8);
	                    student.setLinearVelocity(new Vec2(student.getLinearVelocity().x,11));
	                    //System.out.println(student.getAngleDegrees()+"angles");
	                    //student.jump(student.getJumpSpeed());
	                    //student.jump(0);
	                    //student.setPosition(new Vec2(student.getPosition().x,student.getPosition().y+1));
	                }
	            }

	            @Override
	            public void keyReleased(KeyEvent keyEvent) {
	                if(keyEvent.getKeyCode()==37)
	                {
	                    student.stopWalkingLeft();
	                }
	                else if (keyEvent.getKeyCode()==39)
	                {
	                    student.stopWalkingRight();
	                }
	            }
	        });

	    }

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			totalTime =totalTime - 1000;
			lblTimer.setText("Time Remaining : "+(totalTime/1000) + " secs");
			lblScore.setText("Your Score : "+String.valueOf(LifeBody.getAmount_gem())); 
			lblLifes.setText("Your Lives : " + LifeBody.getAmount_life());
			//The player has collected all the gems
			if(LifeBody.getAmount_gem() == 3)
			{
				LifeBody.setAmount_gem(0);
				LifeBody.setAmount_life(3);
				audioClip.close();
				try {
					audioStream.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				new Level2();
				this.dispose();
			}
			//Time is finished before the player could complete collecting all the gems
			if(totalTime==0)
			{
				timer.stop();
				this.dispose();
				new GameLauncher("Your time is finished");
			}
			//The player runs out of lives
			if(LifeBody.getAmount_life()<0)
			{
				timer.stop();
				this.dispose();
				new GameLauncher("No Life Remaining");
			}
		}
		public void playSong(String FilePath) {
			//Load the audio file
			File audioFile = new File(FilePath);
			 
			try {
				//read the audio file
				audioStream = AudioSystem.getAudioInputStream(audioFile);
				AudioFormat format = audioStream.getFormat();
				 
				DataLine.Info info = new DataLine.Info(Clip.class, format);
				try {
					audioClip = (Clip) AudioSystem.getLine(info);
					audioClip.open(audioStream);
					audioClip.start();
				
				} catch (LineUnavailableException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			} catch (UnsupportedAudioFileException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
}
