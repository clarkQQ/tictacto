import javax.swing.*;
//import java.awt.BorderLayout;
//import java.awt.GridLayout;
//import java.awt.FlowLayout;
//import java.awt.Color;
import java.awt.event.*;
import java.awt.*;

//has custom button class 
public class TicTacTo extends JFrame{
	public static final int WIDTH = 800;
	public static final int HEIGHT = 800;
	private JButton one,two,three,four,five,six,seven,eight,nine;
	
	
	private String[][] placements = new String[3][3];

	
	private JTextField displayBar;
	private JLabel displayLabel;
	int turns;
	int curTurn;
	
	protected boolean finished;
	
	public TicTacTo(int rows, int columns)
	{		
		super("Tic Tac Toe");
		setSize(WIDTH,HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		turns = 1;
		curTurn = 0;
		
		JPanel biggerPanel = new JPanel();
		biggerPanel.setLayout(new GridLayout(3,3));
		
		one = new TicButton(0,0);
		one.setBackground(Color.LIGHT_GRAY);		
		biggerPanel.add(one);
		
		two = new TicButton(0,1);
		two.setBackground(Color.LIGHT_GRAY);
		biggerPanel.add(two);
		
		three = new TicButton(0,2);
		three.setBackground(Color.LIGHT_GRAY);
		biggerPanel.add(three);
		
		four = new TicButton(1,0);
		four.setBackground(Color.LIGHT_GRAY);
		biggerPanel.add(four);
		
		five = new TicButton(1,1);
		five.setBackground(Color.LIGHT_GRAY);
		biggerPanel.add(five);
		
		six = new TicButton(1,2);
		six.setBackground(Color.LIGHT_GRAY);
		biggerPanel.add(six);
		
		seven = new TicButton(2,0);
		seven.setBackground(Color.LIGHT_GRAY);
		biggerPanel.add(seven);
		
		eight = new TicButton(2,1);
		eight.setBackground(Color.LIGHT_GRAY);
		biggerPanel.add(eight);
		
		nine = new TicButton(2,2);
		nine.setBackground(Color.LIGHT_GRAY);
		biggerPanel.add(nine);
		
		add(biggerPanel,BorderLayout.CENTER);
		
		//adding display portion
		 displayBar = new JTextField(50);
		add(displayBar,BorderLayout.NORTH);
		displayLabel = new JLabel("Beginning game!");
		add(displayLabel,BorderLayout.NORTH);
		
		
		
		
		
	}
	
	//need more info from buttons
	private class TicButton extends JButton implements ActionListener{
		String marker;
		int aRow, aCol;
		
		public TicButton(int row, int col){			
			super();
			//calling super for the button
			marker = "";
			placements[row][col] = marker;
			aRow = row;
			aCol = col;
			setText(marker);
			addActionListener(this);			
		}
		public void actionPerformed(ActionEvent e) {
			int val = curTurn%2;
			
			if( val==0 && !finished && getText().equals(""))
			{
				marker="X";
				placements[aRow][aCol] = marker;
				curTurn++;				
				//for console testing
				this.setBackground(new Color(175,216,193));
				System.out.println(marker + "curTurn=="+curTurn); // debugging
				
				// stop from pressing twice if applicable
				this.setEnabled(false);
			}
			else if(val!=0 && !finished && getText().equals(""))
			{
				marker="O";
				curTurn++;
				placements[aRow][aCol] = marker;
				this.setBackground(new Color(233,195,158));
				System.out.println(marker + "curTurn is "+curTurn); // debugging
		
				
				// stop from pressing twice if applicable
				this.setEnabled(false);
			}
			setText(marker);	
			
			
			checkWin();
			
		}
		
		
		
		public void checkWin()
		{	
				//checking horizontals
			for(int i = 0; i < 3; i++)
			{	
				if(!placements[i][0].equals("")  )
				{
					if(	(placements[i][0].equals(placements[i][1])) &&
						(placements[i][1].equals(placements[i][2])) )
					{
						finished = true;
						System.out.println("Winner found @ row "+i); // mostly for debugging
					}
				}
			}
			//checking columns
			for(int j = 0; j < 3; j++)
			{				
				if( !(placements[0][j]).equals("")  )
				{
						if(	(placements[0][j].equals(placements[1][j]))&&
							(placements[1][j].equals(placements[2][j])) )						
						{
							finished = true;
							System.out.println("Column winner @ col "+j+"!"); //mostly for debugging
						}
				}
			}
			//checking diagonals
			if( !(placements[1][1]).equals("")  )
			{
				if( (placements[0][0].equals(placements[1][1])) && placements[0][0].equals(placements[2][2]) )
					{
				finished = true;
				System.out.println("Diagonal win (right)");
					}
			
				if( (placements[2][0].equals(placements[1][1])) && placements[2][0].equals(placements[0][2]) )
				{
					finished = true;
					System.out.println("Diagonal Win! (left)");
				}
			}
			
			int val = curTurn%2;
			if(finished) //checking winners
			{
				
				if( val== 0 )
				{
					displayLabel.setText("Player O just won!");
				}
				else if(val!=0 )
				{
					displayLabel.setText("Player X just won!");
				}
					
			}
			
			else if(curTurn == 9)
			{
				displayLabel.setText("Tie!");
//				System.exit(0);
				
			}
			else
			{
				
				if( val== 0 )
				{
					displayLabel.setText("Player O just placed.");
				}
				else if(val!=0 )
				{
					displayLabel.setText("Player X just placed. ");
				}
			}
		}
				
	
				
			
	}//end of ticbutton
	
	
}//end of class
