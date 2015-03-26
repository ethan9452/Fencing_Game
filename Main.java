import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;



public class Main extends JFrame{

	public static void main(String[] args){
		
		new Main();
	}
	
	Main(){
		
		setSize(700, 300);
		setVisible(true);
		setTitle("Fencing Game");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		add(new Piste());
		validate();
		
		setBackground(Color.blue);
		
	}
	
}
