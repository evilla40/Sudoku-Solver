import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.BorderFactory;
import javax.swing.border.BevelBorder;


public class SudokuGUI extends JFrame implements ActionListener{
	
	private Container container;
	private JPanel mainGrid;
	private GridLayout grid, miniGrid;
	private JPanel sideBar, help, gridPanel, subGrid;
	private MyJButton gridButtons[][], buttons[];
	private final String names[] = 
	      { "1", "2", "3", "4", "5", "6", "7", "8", "9", "C" };
	
	public SudokuGUI() {
		mainGrid = new JPanel(new GridLayout(3, 3, 2, 2));
		mainGrid.setBorder(BorderFactory.createEmptyBorder(2, 4, 2, 2));
		
		container = getContentPane();
	    container.setLayout(new BorderLayout());
	    container.add(mainGrid, BorderLayout.CENTER);

		//Nine subGrids in mainGrid
	    gridButtons = new MyJButton[9][9];
	    for (int i = 0; i < 9; ++i) {
			subGrid = new JPanel(new GridLayout(3, 3));
			//Initialize each button to zero
			for ( int j = 0; j < 9; j++ ) {
				gridButtons[i][j] = new MyJButton(" ");
				gridButtons[i][j].setNumber(0);
				//gridButtons[i][j].addActionListener(this /*change this?*/);
				subGrid.add( gridButtons[i][j] );
			}
			mainGrid.add(subGrid);
		}
	    
	    //Side bar
	    buttons = new MyJButton[10];
	    sideBar = new JPanel(new GridLayout(10, 1));
	    sideBar.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		for (int count = 0; count < 10; count++) {
			buttons[count] = new MyJButton(names[count]);
			//buttons[count].addActionListener(this /*change this?*/);
			sideBar.add(buttons[count]);
			
		}	    
	    container.add(sideBar, BorderLayout.EAST);
		
	      
	      

		
		setSize( 500, 500 );
	    setVisible( true );
		
	}
	
	public static void main( String args[] ) { 
		SudokuGUI application = new SudokuGUI();
	    application.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
