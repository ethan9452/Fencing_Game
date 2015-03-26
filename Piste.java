import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.text.Style;


public class Piste extends JPanel implements ActionListener, KeyListener{
	Timer timer;

	int ttt = 0;

	Player player;

	Opp opp;

	boolean oppParried = false;
	boolean playerParried = false;

	int timeBtwnHit = 0;
	boolean hit = false;

	boolean rightLight = false;
	boolean leftLight = false;

	int bombCount = 0;
	boolean bombActive = false;

	int g = 1;//for end of game sound

	Piste(){

		addKeyListener(this);
		setFocusable(true);

		setDoubleBuffered(true);

		timer = new Timer(4, this);//100 timer fires = 1 sec
		timer.start();

		player = new Player();
		opp = new Opp();

		repaint();
	}


	public void paint(Graphics g){
		super.paint(g);
		Graphics2D g2 = (Graphics2D)g;

		/////////Paint Player/////////
		if(rightLight == false || bombActive == false){
			//mask
			g2.setStroke(new BasicStroke(1));
			g.setColor(Color.black);
			g.fillOval(player.getMaskX(),player.getMaskY(),30,30);
			g.fillRect(player.getMaskX(),player.getMaskY(),15,30);

			//body
			g.setColor(Color.white);
			g.fillRect(player.getBodyX(),player.getBodyY(),20,50);

			//arm and epee
			g2.setStroke(new BasicStroke(5));
			g.setColor(Color.black);
			g.drawLine(player.getShoulderX(),player.getShoulderY(),player.getElbowX(),player.getElbowY()); //upper arm
			g.drawLine(player.getElbowX(),player.getElbowY(), player.getHandX(), player.getHandY()); //forearm
			g.setColor(Color.gray);
			g2.setStroke(new BasicStroke(2));
			g.fillArc(player.getHandX()-10, player.getHandY()-10, 20, 20, -90, 180);//bell guard
			g.setColor(Color.black);
			g.drawLine(player.getHandX()+10, player.getHandY(), player.getTipX(), player.getTipY());//epee

			//legs
			g2.setStroke(new BasicStroke(5));
			g.drawLine(player.getHipX(), player.getHipY(), player.getFrontKneeX(), player.getFrontKneeY()); //front thigh
			g.drawLine(player.getHipX(), player.getHipY(), player.getBackKneeX(), player.getBackKneeY()); //back thigh
			g.drawLine(player.getFrontKneeX(), player.getFrontKneeY(), player.getFrontFootX(), player.getFrontFootY()); //front calv
			g.drawLine(player.getBackKneeX(), player.getBackKneeY(), player.getBackFootX(), player.getBackFootY());//back calf
		}


		////////Paint Opponent/////////////

		if(leftLight == false || bombActive == false){
			//mask
			g2.setStroke(new BasicStroke(1));
			g.setColor(Color.red);
			g.fillOval(opp.getMaskX()-15,opp.getMaskY(),30,30);
			g.fillRect(opp.getMaskX(),opp.getMaskY(),15,30);

			//body
			g.setColor(Color.white);
			g.fillRect(opp.getBodyX(),opp.getBodyY(),20,50);

			//arm and epee
			g2.setStroke(new BasicStroke(5));
			g.setColor(Color.black);
			g.drawLine(opp.getShoulderX(),opp.getShoulderY(),opp.getElbowX(),opp.getElbowY()); //upper arm
			g.drawLine(opp.getElbowX(),opp.getElbowY(), opp.getHandX(), opp.getHandY()); //forearm
			g.setColor(Color.gray);
			g2.setStroke(new BasicStroke(2));
			g.fillArc(opp.getHandX()-10, opp.getHandY()-10, 20, 20, 90, 180);//bell guard
			g.setColor(Color.black);
			g.drawLine(opp.getHandX()-10, opp.getHandY(), opp.getTipX(), opp.getTipY());//epee

			//legs
			g2.setStroke(new BasicStroke(5));
			g.drawLine(opp.getHipX(), opp.getHipY(), opp.getFrontKneeX(), opp.getFrontKneeY()); //front thigh
			g.drawLine(opp.getHipX(), opp.getHipY(), opp.getBackKneeX(), opp.getBackKneeY()); //back thigh
			g.drawLine(opp.getFrontKneeX(), opp.getFrontKneeY(), opp.getFrontFootX(), opp.getFrontFootY()); //front calv
			g.drawLine(opp.getBackKneeX(), opp.getBackKneeY(), opp.getBackFootX(), opp.getBackFootY());//back calf

		}

		///////////Paint Score and Lights/////////////////
		g.setColor(Color.black);
		g.fillRect(200, 20, 90, 40);
		g.setColor(Color.orange);
		Font font = new Font("Arial", Font.BOLD, 30);
		g.setFont(font);
		g.drawString(player.getPoints()+"", 250, 50);
		g.setColor(Color.green);
		if(leftLight == true){
			g.fillRect(210, 25, 35, 30);
		}


		g.setColor(Color.black);
		g.fillRect(445, 20, 90, 40);
		g.setColor(Color.orange);
		g.drawString(opp.getPoints()+"", 450, 50);

		g.setColor(Color.red);
		if(rightLight == true){
			g.fillRect(495, 25, 35, 30);
		}

		////////////Paint Strip////////////////
		g.setColor(Color.gray);
		g.fillRect(100, 210, 500, 70);


		//////////Paint Bomb////////////
		//TODO
		g.setColor(Color.red);;
		g.fillRect(330, -10, 40, bombCount * 3);


		///////Paint Game Over///////
		Font f = new Font("Arial", Font.PLAIN, 15);
		if(player.getPoints() == 5){//left
			g.setFont(font);
			g.setColor(Color.red);
			g.drawString("GAME OVER", 270, 90);
			g.setColor(Color.black);
			g.setFont(f);
			g.drawString("Left Side Wins", 315, 120);

		}

		if(opp.getPoints() == 5){//right
			g.setFont(font);
			g.setColor(Color.red);
			g.drawString("GAME OVER", 270, 90);
			g.setColor(Color.black);
			g.setFont(f);
			g.drawString("Right Side Wins", 310, 120);
		}


	}



	/////////////////////////////////////
	///////////Keyboard Input////////////////
	//////////////////////////////
	public void keyPressed(KeyEvent e) {






		player.keyPressed(e);
		opp.keyPressed(e);

		int key = e.getKeyCode();

		if(key == KeyEvent.VK_6){
			if(Math.abs(player.getMaskX() - 350) <20 || Math.abs(opp.getMaskX() - 350) <20 ){
				bombActive = true;
			}
		}
		//System.out.println("pressed");

	}


	public void keyReleased(KeyEvent e) {


	}



	public void keyTyped(KeyEvent e) {


	}


	/////////////////////////////////////
	///////////Timer////////////////
	//////////////////////////////
	public void actionPerformed(ActionEvent e) {

		if(player.getPoints() ==5 || opp.getPoints() == 5){

			if(g == 1){

				
				try {
					java.applet.AudioClip clip = java.applet.Applet.newAudioClip(
							new java.net.URL("file:///Users/ethanlo/applause-01.wav")); 
					clip.play();
				} catch (java.net.MalformedURLException murle) { System.out.println(murle);
				}
				g = 3;
			}
		}

		player.tttPlusOne();
		player.move();

		opp.tttPlusOne();
		opp.move();

		ttt++;

		oppParriedCheck();
		playerParriedCheck();

		if(hit == true){
			timeBtwnHit++;
		}

		if(bombActive == true){//TODO
			bomb();
			bombCount++;

		}

		checkHit();

		requestFocus();
		repaint();

		//	System.out.println(hit);
	}


	public void checkHit(){		


		if (player.getTipX() >= opp.getBodyX() || opp.getTipX() <= player.getBodyX()+20 || player.getFrontFootX() < 100 ||opp.getFrontFootX() > 600){

			if(hit == false){

				if(player.getTipX() >= opp.getBodyX() || opp.getFrontFootX() > 600){
					player.addOnePoint();
					leftLight = true;
					hit = true;

					try {
						java.applet.AudioClip clip = java.applet.Applet.newAudioClip(
								new java.net.URL("file:///Users/ethanlo/beep-01a.wav")); 
						clip.play();
					} catch (java.net.MalformedURLException murle) { System.out.println(murle);
					}
				}

				if(opp.getTipX() <= player.getBodyX()+20 || player.getFrontFootX() < 100){
					opp.addOnePoint();
					rightLight = true;
					hit = true;

					try {
						java.applet.AudioClip clip = java.applet.Applet.newAudioClip(
								new java.net.URL("file:///Users/ethanlo/beep-01a.wav")); 
						clip.play();
					} catch (java.net.MalformedURLException murle) { System.out.println(murle);
					}
				}
			}


			if(timeBtwnHit >= 20){
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {

					e.printStackTrace();
				}
				rightLight = false;
				leftLight = false;
				playerParried = false;
				oppParried = false;
				player.resetPosition();
				opp.resetPosition();
				player.setAction("none");
				opp.setAction("none");
				hit = false;
				timeBtwnHit = 0;
				bombActive = false;
				bombCount = 0;
			}

		}

	}

	public void oppParriedCheck(){//opp got parried
		if (player.getTipX()>opp.getTipX() && player.getParry() == true && oppParried == false){
			opp.addToTipX(25);
			opp.addToTipY(-33);
			oppParried = true;
			ttt = 0;

		}

		if(oppParried == true && ttt == 120){

			oppParried = false;
			opp.addToTipX(-25);
			opp.addToTipY(33);
		}

	}

	public void playerParriedCheck(){//player got parried
		if (player.getTipX()>opp.getTipX() && opp.getParry() == true && playerParried == false){
			player.addToTipX(-25);
			player.addToTipY(-33);
			playerParried = true;
			ttt = 0;

		}

		if(playerParried == true && ttt == 120){
			playerParried = false;
			player.addToTipX(25);
			player.addToTipY(33);
		}
	}


	//TODO
	public void bomb(){

		System.out.println(bombCount);

		if(Math.abs(player.getMaskX() - 350) <20 && bombCount == 120){
			opp.addOnePoint();
			rightLight = true;
			hit = true;
			bombCount = -300;


		}

		if(Math.abs(opp.getMaskX() - 350) <20 && bombCount == 120){
			player.addOnePoint();
			leftLight = true;
			hit = true;
			bombCount = -300;

		}

		if(timeBtwnHit >= 140){
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
			rightLight = false;
			leftLight = false;
			playerParried = false;
			oppParried = false;
			player.resetPosition();
			System.out.println("reset!");
			opp.resetPosition();
			player.setAction("none");
			opp.setAction("none");
			hit = false;
			timeBtwnHit = 0;
			bombActive = false;
			bombCount = 0;
		}

	}


}
