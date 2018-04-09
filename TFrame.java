import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class TFrame {
	
	private static TButton button[] = {new TButton(), new TButton(), new TButton(), 
			new TButton(), new TButton(), new TButton(), 
			new TButton(), new TButton(), new TButton()};
	
	private static JFrame frame = new JFrame("Tic Tac Toe");
	private static Container pane = frame.getContentPane();
	private static Font font = new Font("Arial", 1, 50);
	private static Random random = new Random();
	
	private static int r = 0;
	private static int winner = 0;
	
	private static boolean isSingleplayer = true;
	
	public TFrame() {
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300, 200);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
	}
	
	public void loadGamePanel() {
		
		GridLayout gameLayout = new GridLayout(3, 3);
		JPanel gamePanel = new JPanel(gameLayout);
		
		for(int i=0;i<9;i++) {
			
			button[i].addActionListener(getActionListener(button[i]));
			button[i].setFont(font);
			gamePanel.add(button[i]);
			
		}
		
		pane.add(gamePanel);
		
	}
	
	private ActionListener getActionListener(TButton b) {
		
		TButton button[] = getButton();
		
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				b.setEnabled(false);
				
				if(r%2==0 && !isSingleplayer) {
					b.setValue(1);
					b.setText("X");
				} else if(r%2!=0 && !isSingleplayer) {
					b.setValue(2);
					b.setText("O");
				} else if(r%2==0 && isSingleplayer) {
					b.setValue(1);
					b.setText("X");
					if(r<8) calculateNextTurn();
				}
				
				r++;
				
				if(testForWinner(button) && r<=9) {
					
					for(int i=0;i<9;i++) {
						
						button[i].setEnabled(false);
						button[i].setText("");
							
					}
					
					if(winner == 1) button[4].setText("X");
					else if(winner == 2) button[4].setText("O");
					
				} else if(!testForWinner(button) && r==9) {
					
					for(int i=0;i<9;i++) {
						
						button[i].setText("");
							
					}
					
				}
				
			}
		};
		
	}
	
	public static TButton[] getButton() {
		return button;
	}
	
	private static boolean testForWinner(TButton[] b) {
		
		if(r<5) {
			
			return false;
			
		} else if(b[0].getValue()==b[1].getValue() && b[1].getValue()==b[2].getValue() && b[2].getValue()==1 ||
				  b[3].getValue()==b[4].getValue() && b[4].getValue()==b[5].getValue() && b[5].getValue()==1 ||
				  b[6].getValue()==b[7].getValue() && b[7].getValue()==b[8].getValue() && b[8].getValue()==1 ||
				  b[0].getValue()==b[3].getValue() && b[3].getValue()==b[6].getValue() && b[6].getValue()==1 ||
				  b[1].getValue()==b[4].getValue() && b[4].getValue()==b[7].getValue() && b[7].getValue()==1 ||
				  b[2].getValue()==b[5].getValue() && b[5].getValue()==b[8].getValue() && b[8].getValue()==1 ||
				  b[0].getValue()==b[4].getValue() && b[4].getValue()==b[8].getValue() && b[8].getValue()==1 ||
				  b[2].getValue()==b[4].getValue() && b[4].getValue()==b[6].getValue() && b[6].getValue()==1) {
			
			winner = 1;
			return true;
			
		} else if(b[0].getValue()==b[1].getValue() && b[1].getValue()==b[2].getValue() && b[2].getValue()==2 ||
				  b[3].getValue()==b[4].getValue() && b[4].getValue()==b[5].getValue() && b[5].getValue()==2 ||
				  b[6].getValue()==b[7].getValue() && b[7].getValue()==b[8].getValue() && b[8].getValue()==2 ||
				  b[0].getValue()==b[3].getValue() && b[3].getValue()==b[6].getValue() && b[6].getValue()==2 ||
				  b[1].getValue()==b[4].getValue() && b[4].getValue()==b[7].getValue() && b[7].getValue()==2 ||
				  b[2].getValue()==b[5].getValue() && b[5].getValue()==b[8].getValue() && b[8].getValue()==2 ||
				  b[0].getValue()==b[4].getValue() && b[4].getValue()==b[8].getValue() && b[8].getValue()==2 ||
				  b[2].getValue()==b[4].getValue() && b[4].getValue()==b[6].getValue() && b[6].getValue()==2) {
			
			winner = 2;
			return true;
			
		} else {
			
			winner = 0;
			return false;
			
		}
		
	}

	public void resetFrame(Dimension d) {
		
		frame.setVisible(false);
		
		pane.removeAll();
		pane.revalidate();
		pane.repaint();
		
		if(d!=null) frame.setSize(d);
		
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
	}
	
	public void loadSettingsPanel() {
		
		GridLayout settingsLayout = new GridLayout(2, 1);
		JPanel settingsPanel = new JPanel(settingsLayout);
		
		JButton btnSingleplayer = new JButton("Einzelspieler Modus");
		JButton btnMultiplayer = new JButton("Mehrspieler Modus");
		
		btnSingleplayer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				isSingleplayer = true;
				resetFrame(new Dimension(450, 450));
				loadGamePanel();
			}
		});
		
		btnMultiplayer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				isSingleplayer = false;
				resetFrame(new Dimension(450, 450));
				loadGamePanel();
			}
		});
		
		settingsPanel.add(btnSingleplayer);
		settingsPanel.add(btnMultiplayer);
		
		pane.add(settingsPanel);
		
	}
	
	private void calculateNextTurn() {
		
		TButton button[] = getButton();
		
		boolean success = false;
		
		while(!success) {
			
			int rdm = random.nextInt(8);
			
			if(button[rdm].getValue()==0) {
				
				button[rdm].setEnabled(false);
				button[rdm].setValue(2);
				button[rdm].setText("O");
				success = true;
				r++;
				
			}
			
		}
		
	}

}
