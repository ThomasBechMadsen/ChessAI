package game;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;


public class Frame extends JFrame{
	
	Board board = new Board();
	private Graphics g;
	int feltSize = 60;
	ImageIcon image = new ImageIcon("/Users/mohammad/Desktop/chess.png");
	JLabel imageLabel = new JLabel(image);
	
	public Frame(){
		// Initialize the view 
		setTitle("Skak Spil - Gruppe 3");
		setSize(board.board[0].length*feltSize+100,board.board.length*feltSize+100);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		imageLabel.setBounds(10, 10, 400, 400);
		imageLabel.setVisible(true);
	}
	
	@Override
	public void paint(Graphics g){
		super.paint(g);
		this.g= g;
		g.translate(50,50);
		drawMap();
	}

	void drawMap(){
		for (int row = 0; row < board.board.length; row++){
			for(int col = 0; col < board.board[0].length; col ++){
				Color color = Color.white;
				if(row%2 != 0){
					if(col%2 != 0)
						color = Color.WHITE;
					else if(col%2 == 0)
						color = Color.GRAY;
				}
				else{
					if(col%2 == 0)
						color = Color.WHITE;
					else if(col%2 != 0)
						color = Color.GRAY; 
				} 
				g.setColor(color);
				g.fillRect(feltSize*col, feltSize * row, feltSize, feltSize);
				g.setColor(Color.BLACK);
				g.drawRect(feltSize * col, feltSize*row, feltSize, feltSize);
			}
		}
		
	}
	
	
	public static void main(String[] arg){
		Frame view = new Frame();
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				view.setVisible(true);
			}
		});
	}
	
	
}
