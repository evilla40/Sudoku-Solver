import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class SudokuGUI extends JFrame{
	
	private Container container;
	private GridLayout mainGrid;
	private GridLayout grid, miniGrid;
	private Container largeContainer;
	private JPanel bar, help, gridPanel, subGrid;
	private MyJButton buttons[];
	private final String names[] = 
	      { "1", "2", "3", "4", "5", "6", "7", "8", "9" };
	
	public SudokuGUI() {
		mainGrid = new GridLayout(3, 3, 5, 5);
		
		container = getContentPane();
	    container.setLayout(mainGrid);

		//Nine subGrids in mainGrid
	    for (int i = 0; i < 9; ++i) {
			subGrid = new JPanel(new GridLayout(3, 3));
			buttons = new MyJButton[ names.length ];
	
			for ( int count = 0; count < names.length; count++ ) {
				buttons[ count ] = new MyJButton( names[ count ] );
				buttons[count].setNumber ( 100 + count );
	      
				subGrid.add( buttons[ count ] );
			}
			container.add(subGrid);
		}
		
	      
	      

		
		setSize( 500, 500 );
	    setVisible( true );
		
	}
	
	public static void main( String args[] ) { 
		SudokuGUI application = new SudokuGUI();
	    application.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
	}
}
