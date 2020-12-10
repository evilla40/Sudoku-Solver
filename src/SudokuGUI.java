import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

import javax.swing.*;
import javax.swing.BorderFactory;
import javax.swing.border.BevelBorder;


public class SudokuGUI extends JFrame implements ActionListener{
	
	private Container container;
	private JFileChooser fileChooser;
	private JPanel mainGrid;
	private JPanel sideBar, help, gridPanel, subGrid;
	private MyJButton gridButtons[][], sideButtons[];
	private MyJButton selectedBox = null;
	private int selectedNum = 0;
	private final String names[] = 
	      { "1", "2", "3", "4", "5", "6", "7", "8", "9", "C" };
	
	public SudokuGUI() {
		mainGrid = new JPanel(new GridLayout(3, 3, 2, 2));
		mainGrid.setBorder(BorderFactory.createEmptyBorder(2, 4, 2, 2));
		
		container = getContentPane();
	    container.setLayout(new BorderLayout());
	    container.add(mainGrid, BorderLayout.CENTER);

	    gridButtons = new MyJButton[9][9];
	    //Nine subGrids in mainGrid
	    for (int i = 0; i < 9; ++i) {
			subGrid = new JPanel(new GridLayout(3, 3));
			int row, col;
			//Nine buttons per subGrid
			for (int j = 0; j < 3; ++j) {
				row = (i/3)*3 + j;
				for (int k = 0; k < 3; ++k) {
					col = (i%3)*3 + k;
					gridButtons[row][col] = new MyJButton(" ");
					gridButtons[row][col].addActionListener(new GridButtonListener());
					subGrid.add( gridButtons[row][col] );
				}
			}
			mainGrid.add(subGrid);
		}
	    
	    //Side bar
	    sideButtons = new MyJButton[10];
	    sideBar = new JPanel(new GridLayout(10, 1));
	    sideBar.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		for (int count = 0; count < 10; count++) {
			sideButtons[count] = new MyJButton(names[count], count+1, true);
			sideButtons[count].addActionListener( this );
			sideBar.add(sideButtons[count]);
			
		}	    
	    container.add(sideBar, BorderLayout.EAST);
		
	      
	      

		
		setSize( 500, 500 );
	    setVisible( true );
	    
	    JMenu menu = new JMenu("Menu");
	    JMenuItem loadFile = new JMenuItem( "Load Puzzle" );
	    menu.add( loadFile );
	    loadFile.addActionListener(

	    		new ActionListener() {
	    			public void actionPerformed( ActionEvent event )
		            {
	    				JFileChooser selectedFile = new JFileChooser();
	    	    		int returnVal = selectedFile.showOpenDialog(null);
	    	    				
	    	    		if (returnVal == JFileChooser.APPROVE_OPTION) {
	    	    			File file = selectedFile.getSelectedFile();
	    	    			//Reset the board before loading new file
	    	    			for(int i = 0; i < 9; i++) {
	    	    				for(int j = 0; j < 9; j++) {
	    	    					gridButtons[i][j].setText(" ");
	    	    					gridButtons[i][j].setNumber(0);
	    	    					gridButtons[i][j].setLock(false);
	    	    				}
	    	    			}
	    	    			try {
	    	    				FileInputStream input = new FileInputStream(file);
	    	    			    int s, row, col, val;
	    	    			    while (input.available() > 0) {
	    	    			    	row = (int) input.read() - 49;
	    	    			    	s = (int) input.read();
	    	    			    	col = (int) input.read() - 49;
	    	    			    	s = (int) input.read();
	    	    			    	val = (int) input.read() - 48;
	    	    			    	s = (int) input.read();
	    	    			    	gridButtons[row][col].setText(Integer.toString(val));	
	    	    			    	gridButtons[row][col].setNumber(val);
	    	    			    	gridButtons[row][col].setLock(true);
	    	    			    }
	    	    			} catch (IOException e) {
	    	    				e.printStackTrace();
	    	    			}
	    	    		
	    	    		}
		            }
	    		}
	    		);
	    JMenuBar menuBar = new JMenuBar();  
	    setJMenuBar( menuBar );  
	    menuBar.add( menu );
	    setSize( 500, 500 );
	    setVisible( true );

		
	}
	
	public boolean checkWin(MyJButton buttons[][]) {
		boolean array[] = new boolean[9];
		boolean duplicate = false;
		boolean unfinished = false;
		
		//Check rows for duplicate numbers or unfinished
		for(int row = 0; row < 9; ++row) {
			Arrays.fill(array, false);
			for(int col = 0; col < 9; ++col) {	
				if (buttons[row][col].getNumber() > 0) {
					if (array[buttons[row][col].getNumber() - 1] == true) {		
						return false;
					}
					else array[buttons[row][col].getNumber() - 1] = true;
				}
				
			}
			//if (duplicate == true) break;
			for (boolean value : array) {
		        if (!value) {
		        	return false;
		        }
		    }		
		} 
		
		
		//Check columns for duplicate numbers or unfinished
		for(int col = 0; col < 9; ++col) {
			Arrays.fill(array, false);
			for(int row = 0; row < 9; ++row) {
				System.out.println("buttons[ " + row + " ][ " + col + " ] = " + buttons[row][col].getNumber());
				System.out.println("Text is: " + buttons[row][col].getText());
				if (buttons[row][col].getNumber() > 0) {
					if (array[buttons[row][col].getNumber() - 1] == true) {
						return false;
					}
					else array[buttons[row][col].getNumber() - 1] = true;
				}
				
			}
			for (boolean value : array) {
		        if (!value) {
		        	return false;
		        }
		    }
		} 
		
		
		//Check subgrids for duplicate numbers
		for (int grid = 0; grid < 9; ++grid) {
			Arrays.fill(array, false);
			int row, col;
			for (int i = 0; i < 3; ++i) {
				row = (grid/3)*3 + i;
				for (int j = 0; j < 3; ++j) {
					col = (grid%3)*3 + j;
					if (buttons[row][col].getNumber() > 0) {
						if (array[buttons[row][col].getNumber() - 1] == true) {
							return false;
						}
						else array[buttons[row][col].getNumber() - 1] = true;
					}
				}
			}
		}
		
		return true;
	}
	
	public static void main( String args[] ) { 
		SudokuGUI application = new SudokuGUI();
	    application.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	    MyJButton temp = (MyJButton) e.getSource();
	    selectedNum = temp.getNumber();			
		if (selectedBox != null) {
			if (selectedBox.getLock() == false) {
				if (selectedNum == 10) {
					selectedBox.setText(" ");
					selectedBox.setNumber(0);
				}
				else {
					selectedBox.setText(Integer.toString(selectedNum));
					selectedBox.setNumber(selectedNum);
					if(checkWin(gridButtons))
						JOptionPane.showMessageDialog( SudokuGUI.this, "WIN!",
							"Status", JOptionPane.PLAIN_MESSAGE );
				}	
			}
			selectedNum = 0;
			selectedBox = null;
		}
	}
	
	class GridButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        	selectedBox = (MyJButton) e.getSource();
        	if (selectedNum > 0) {
        		for ( int i = 0; i < 9; i++ ) {
        			for ( int j = 0; j < 9; j++ ) {
        				if (selectedBox.equals(gridButtons[i][j])) {
        					if (gridButtons[i][j].getLock() == false) {
        						if(selectedNum == 10) {
        							gridButtons[i][j].setText(" ");
            						gridButtons[i][j].setNumber(0);
        						}
        						else {
        							gridButtons[i][j].setText(Integer.toString(selectedNum));
            						gridButtons[i][j].setNumber(selectedNum);
            						if(checkWin(gridButtons))
            							JOptionPane.showMessageDialog( SudokuGUI.this, "WIN!",
            									"Status", JOptionPane.PLAIN_MESSAGE );
        						}
        						
        					}
        					selectedNum = 0;
    						selectedBox = null;
        					break;
        				}
        				
				    }
        			if (selectedBox == null) break;
        		}
        	}
        }
        
	}
}
