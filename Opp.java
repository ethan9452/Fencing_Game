import java.awt.event.KeyEvent;


public class Opp {

	private int points = 0; //points player has

	private int maskX; //mask is a circle
	private int maskY;
	private int bodyX;// body is a rect
	private int bodyY;
	//shoulder, elbow, hand, and tip are all points, the lines connecting the points will be arm and epee.
	private int shoulderX;//shoulder is a point
	private int shoulderY;
	private int elbowX;//elbow is point
	private int elbowY;
	private int handX;//hand is points
	private int handY;
	private int tipX; // x coord or tip; tip is end of epee
	private int tipY;
	//hip, knees, and foot are points connected by lines. lines are thigh and calf.
	private int hipX;
	private int hipY;
	private int frontKneeX;
	private int frontKneeY;
	private int backKneeX;
	private int backKneeY;
	private int frontFootX;
	private int frontFootY;
	private int backFootX;
	private int backFootY;

	private int ttt = 0;//timer counter

	private String action = "none"; //says what action fencer is currently doing. (none, advance, retreat, parry, lunge)
	private boolean parry = false; //if parry is true and opp's blade is in reach, opp's blade deflected, otherwise, doesnt work

	Opp(){

		points = 0;	

		maskX = 450;
		maskY = 100;
		bodyX = 450;
		bodyY = 130;
		shoulderX = 460;
		shoulderY = 135;
		elbowX = 460;
		elbowY = 155;
		handX = 440;
		handY = 155;
		tipX = 390;	
		tipY = 155;
		hipX = 460;
		hipY = 180;
		frontKneeX = 450;
		frontKneeY = 200;
		backKneeX = 460;
		backKneeY = 200;
		frontFootX = 450;
		frontFootY =210;
		backFootX = 470;
		backFootY = 210;
	}

	public void resetPosition(){
		maskX = 450;
		maskY = 100;
		bodyX = 450;
		bodyY = 130;
		shoulderX = 460;
		shoulderY = 135;
		elbowX = 460;
		elbowY = 155;
		handX = 440;
		handY = 155;
		tipX = 390;	
		tipY = 155;
		hipX = 460;
		hipY = 180;
		frontKneeX = 450;
		frontKneeY = 200;
		backKneeX = 460;
		backKneeY = 200;
		frontFootX = 450;
		frontFootY =210;
		backFootX = 470;
		backFootY = 210;
	}

	///////////////////////////////////
	////////////Key Listener////////////////
	///////////////////////////////////
	public void keyPressed(KeyEvent e) {


		int key = e.getKeyCode();

		if(action.equals("none") == true){
			ttt = 0;

			if(key == KeyEvent.VK_LEFT){
				action = "advance";
				//System.out.println("retreat");
			}

			else if(key == KeyEvent.VK_RIGHT){
				action = "retreat";
			}

			else if(key == KeyEvent.VK_UP){
				action = "lunge";
			}

			else if(key == KeyEvent.VK_DOWN){
				action = "parry";
			}
		}

	}

	///////////////////////////////////
	////////////Action////////////////
	///////////////////////////////////
	public void move(){
		if(action.equals("advance")){
			if(ttt == 1){
				frontKneeX = frontKneeX + -10;
				frontFootX = frontFootX + -10;

			}

			else if(ttt == 50){
				maskX = maskX + -10;
				bodyX = bodyX + -10;
				shoulderX = shoulderX + -10;;
				elbowX = elbowX + -10;
				handX = handX + -10;
				tipX = tipX + -10;	
				hipX = hipX + -10;;
				backKneeX = backKneeX + -10;
				backFootX = backFootX + -10;

			}

			else if(ttt == 60){
				action = "none";
			}
		}

		else if(action.equals("retreat")){

			if(ttt == 1){
				backKneeX = backKneeX - -10;
				backFootX = backFootX - -10;
				maskX = maskX - -10;
				bodyX = bodyX - -10;
				shoulderX = shoulderX - -10;;
				elbowX = elbowX - -10;
				handX = handX - -10;
				tipX = tipX - -10;	
				hipX = hipX - -10;

			}

			else if(ttt == 50){
				frontKneeX = frontKneeX - -10;
				frontFootX = frontFootX - -10;


			}

			else if(ttt == 60){
				action = "none";

			}

		}

		else if(action.equals("parry")){
			if(ttt == 1){
				tipX = tipX - -5;
				tipY = tipY - 25;
				parry = true;

			}

			else if(ttt == 5){
				parry = false;
			}

			else if(ttt == 20){
				elbowX = elbowX + -20;
				elbowY = elbowY - 20;
				handX = handX + -20;
				handY = handY - 20;
				tipX = tipX + -20;	
				tipY = tipY - 20;

				tipX = tipX + -5;
				tipY = tipY + 25;

			}

			else if(ttt == 100){
				elbowX = elbowX + 20;
				elbowY = elbowY - -20;
				handX = handX + 20;
				handY = handY - -20;
				tipX = tipX + 20;	
				tipY = tipY - -20;

			}

			else if(ttt == 140){
				action = "none";

			}
		}

		else if(action.equals("lunge")){
			if(ttt == 1){
				elbowX = elbowX + -20;
				elbowY = elbowY - 20;
				handX = handX + -20;
				handY = handY - 20;
				tipX = tipX + -20;	
				tipY = tipY - 20;

				frontKneeX = frontKneeX + -5;
				frontKneeY = frontKneeY - 10;
				frontFootX = frontFootX + -5;
				frontFootY = frontFootY - 10;
			}

			else if(ttt == 50){ //drop 20 foward 40
				maskX = maskX + -40;
				maskY = maskY + 10;
				bodyX = bodyX + -40;
				bodyY = bodyY + 10;
				shoulderX = shoulderX + -40;
				shoulderY = shoulderY + 10;
				hipX = hipX + -40;
				hipY = hipY + 10;
				backKneeX = backKneeX + -10;
				backKneeY = backKneeY + 5;
				elbowX = elbowX + -40;
				elbowY = elbowY + 10;
				handX = handX + -40;
				handY = handY + 10;
				tipX = tipX + -40;	
				tipY = tipY + 10;
				frontKneeX = frontKneeX + -40;
				frontKneeY = frontKneeY + 10;
				frontFootX = frontFootX + -40;
				frontFootY = frontFootY + 10;

			}

			else if(ttt == 100){
				elbowX = elbowX + 20;
				elbowY = elbowY - -20;
				handX = handX + 20;
				handY = handY - -20;
				tipX = tipX + 20;	
				tipY = tipY - -20;
				frontKneeX = frontKneeX + 5;
				frontKneeY = frontKneeY - -10;
				frontFootX = frontFootX + 5;
				frontFootY = frontFootY - -10;
				maskX = maskX + 40;
				maskY = maskY + -10;
				bodyX = bodyX + 40;
				bodyY = bodyY + -10;
				shoulderX = shoulderX + 40;
				shoulderY = shoulderY + -10;
				hipX = hipX + 40;
				hipY = hipY + -10;
				backKneeX = backKneeX + 10;
				backKneeY = backKneeY + -5;
				elbowX = elbowX + 40;
				elbowY = elbowY + -10;
				handX = handX + 40;
				handY = handY + -10;
				tipX = tipX + 40;	
				tipY = tipY + -10;
				frontKneeX = frontKneeX + 40;
				frontKneeY = frontKneeY + -10;
				frontFootX = frontFootX + 40;
				frontFootY = frontFootY + -10;
			}

			else if (ttt == 150){
				action = "none";
			}

		}

	}



	///////////////////////////////////
	////////////Getters////////////////
	///////////////////////////////////
	public int getPoints(){
		return points;
	}

	public String getAction(){
		return action;	
	}

	public boolean getParry(){
		return parry;
	}

	public int getMaskX(){
		return maskX;
	}

	public int getMaskY(){
		return maskY;
	}

	public int getBodyX(){
		return bodyX;
	}

	public int getBodyY(){
		return bodyY;
	}

	public int getShoulderX(){
		return shoulderX;
	}

	public int getShoulderY(){
		return shoulderY;
	}

	public int getElbowX(){
		return elbowX;
	}

	public int getElbowY(){
		return elbowY;
	}

	public int getHandX(){
		return handX;
	}

	public int getHandY(){
		return handY;
	}

	public int getTipX(){
		return tipX;
	}

	public int getTipY(){
		return tipY;
	}

	public int getHipX(){
		return hipX;
	}

	public int getHipY(){
		return hipY;
	}

	public int getFrontKneeX(){
		return frontKneeX;
	}

	public int getFrontKneeY(){
		return frontKneeY;
	}

	public int getBackKneeX(){
		return backKneeX;
	}

	public int getBackKneeY(){
		return backKneeY;
	}

	public int getFrontFootX(){
		return frontFootX;
	}

	public int getFrontFootY(){
		return frontFootY;
	}

	public int getBackFootX(){
		return backFootX;
	}

	public int getBackFootY(){
		return backFootY;
	}


	/////////////////////////////////
	//////////////Setters//////////////
	/////////////////////////////////
	public void setAction(String n){
		action = n;
	}

	public void tttPlusOne(){
		ttt++;
	}

	public void addOnePoint(){
		points++;
	}

	public void addToMaskX(int n){
		maskX = maskX + n;
	}

	public void addToMaskY(int n){
		maskY = maskY + n;
	}

	public void addToBodyX(int n){
		bodyX = bodyX + n;
	}

	public void addToBodyY(int n){
		bodyY = bodyY + n;
	}

	public void addToShoulderX(int n){
		shoulderX = shoulderX + n;
	}

	public void addToShoulderY(int n){
		shoulderY = shoulderY + n;
	}

	public void addToElbowX(int n){
		elbowX = elbowX + n;
	}

	public void addToElbowY(int n){
		elbowY = elbowY + n;
	}

	public void addToHandX(int n){
		handX = handX + n;
	}

	public void addToHandY(int n){
		handY = handY + n;
	}

	public void addToTipX(int n){
		tipX = tipX + n;
	}

	public void addToTipY(int n){
		tipY = tipY + n;
	}

	public void addToHipX(int n){
		hipX = hipX + n;
	}

	public void addToHipY(int n){
		hipY = hipY + n;
	}

	public void addToFrontKneeX(int n){
		frontKneeX = frontKneeX + n;
	}

	public void addToFrontKneeY(int n){
		frontKneeY = frontKneeY + n;
	}

	public void addToBackKneeX(int n){
		backKneeX = backKneeX + n;
	}

	public void addToBackKneeY(int n){
		backKneeY = backKneeY + n;
	}

	public void addToFrontFootX(int n){
		frontFootX = frontFootX + n;
	}

	public void addToFrontFootY(int n){
		frontFootY = frontFootY + n;
	}

	public void addToBackFootX(int n){
		backFootX = backFootX + n;
	}

	public void addToBackFootY(int n){
		backFootY = backFootY + n;
	}




}
