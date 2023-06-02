package MainApp;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

import javax.swing.*;

public class FarmGame{
	
	private boolean isRunning = true; // Condition to keep the game running.
	private int currentDay = 1;
	
	private final List<Crop> seedList = new ArrayList<Crop>(8);
	private final Tile tile[][] = new Tile[10][5]; // represents the 10x5 lot farm
	
	private Tile chosenTile = null;
	private int chosenTileX;
	private int chosenTileY;
	private String feedback;
	
	private static final Farmer farmer = new Farmer();
	
	JFrame frame;
	//Container container;
	JPanel titlePanel, startButtonPanel, farmerPanel, dayPanel, cmdButtonPanel, farmLotPanel, nextDayPanel, promptPanel,
	       userInputPanel;
	JLabel titleLabel, levelLabel, farmerLvlLabel, objectCoinsLabel, farmerObjCoinsLabel, expLabel, farmerExpLabel
	       , dayLabel, dayNumberLabel;
	JLabel[][] farmLotLabel = new JLabel[10][5];
	JTextArea promptArea;
	JTextField userInputX, userInputY;
	Font normalFont = new Font("Times New Roman", Font.PLAIN, 30);
	Font smallFont = new Font("Times New Roman", Font.PLAIN, 20);
	Font promptFont = new Font("Times New Roman", Font.PLAIN, 16);
	JButton startButton, plantButton, plowButton, waterButton, fertilizeButton, harvestButton, nextDayButton, userInputButton;
	//JTextArea playerInfoText;
	Color lightBrown = new Color(188, 158, 130);
	Color darkBrown = new Color(92, 64, 51);
	TitleScreenHandler tsHandler = new TitleScreenHandler();
	CmdHandler cmdHandler = new CmdHandler();
	String position;
	/*
	JPanel info_panel = new JPanel();
	JLabel info_label = new JLabel();
	
	JPanel cmd_panel = new JPanel();
	JTextField user_input = new JTextField();
	*/
	
	public FarmGame() {
		frame = new JFrame();
		frame.setSize(1000, 800);
		//frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(lightBrown);
		frame.setLayout(null);
		
		titlePanel = new JPanel();
		titlePanel.setBounds(150, 200, 700, 100);
		titlePanel.setBackground(lightBrown);
		titleLabel = new JLabel("Best Farm Game Ever");
		titleLabel.setForeground(Color.BLACK);
		titleLabel.setFont(new Font("Ink Free", Font.BOLD, 60));
		
		startButtonPanel = new JPanel();
		startButtonPanel.setBounds(350, 365, 250, 100);
		startButtonPanel.setBackground(lightBrown);
		
		startButton = new JButton("START");
		startButton.setBackground(Color.BLACK);
		startButton.setForeground(Color.WHITE);
		startButton.setFont(normalFont);
		startButton.addActionListener(tsHandler);
		startButton.setFocusPainted(false);
		
		titlePanel.add(titleLabel);
		startButtonPanel.add(startButton);
		frame.add(titlePanel);
		frame.add(startButtonPanel);
	/*	
		day_label.setFont(new Font("Times New Roman", Font.BOLD, 40));
		day_label.setText("DAY: " + currentDay);
		
		info_label.setFont(new Font("Times New Roman", Font.BOLD, 25));
		info_label.setHorizontalAlignment(JLabel.CENTER);
		info_label.setText("Level " + farmer.getLevel() + " | " + "ObjectCoins: " + farmer.getObjectCoins() + " | " + "Exp: " + farmer.getExp());
		
	//	day_panel.setLayout(new BorderLayout());
	//	day_panel.setBounds(0, 0, 100, 20);
	//	day_panel.add(day_label);
		info_panel.add(info_label);
		
		//frame.add(day_panel, BorderLayout.NORTH);
		frame.add(info_panel);
	*/	initializeFarmLot();
		frame.setVisible(true);
		
	}
	
	public void initializeFarmLot() {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 5; j++) {
				tile[i][j] = new Tile();
			}
		}
	}

	public void createGameScreen() {
		titlePanel.setVisible(false);
		startButtonPanel.setVisible(false);
		
		/*
		playerInfoText = new JTextArea("Damn I need to get some sleep. lorem ipsum dimsum haha go brrrrt.");
		playerInfoText.setBounds(90, 10, 750, 150);
		playerInfoText.setBackground(Color.black);
		playerInfoText.setForeground(Color.white);
		playerInfoText.setFont(normalFont);
		playerInfoText.setLineWrap(true);
		playerInfoPanel.add(playerInfoText);
		*/
		cmdButtonPanel = new JPanel();
		cmdButtonPanel.setBounds(180, 700, 600, 250);
		cmdButtonPanel.setBackground(lightBrown);
		frame.add(cmdButtonPanel);
		
		plantButton = new JButton("Plant");
		plantButton.setBackground(Color.BLACK);
		plantButton.setForeground(Color.white);
		plantButton.setFont(normalFont);
		plantButton.setFocusPainted(false);
		cmdButtonPanel.add(plantButton);
		plantButton.addActionListener(cmdHandler);
		plantButton.setActionCommand("cmd1");
		
		plowButton = new JButton("Plow");
		plowButton.setBackground(Color.BLACK);
		plowButton.setForeground(Color.white);
		plowButton.setFont(normalFont);
		plowButton.setFocusPainted(false);
		cmdButtonPanel.add(plowButton);
		plowButton.addActionListener(cmdHandler);
		plowButton.setActionCommand("cmd2");
		
		waterButton = new JButton("Water");
		waterButton.setBackground(Color.BLACK);
		waterButton.setForeground(Color.white);
		waterButton.setFont(normalFont);
		waterButton.setFocusPainted(false);
		cmdButtonPanel.add(waterButton);
		waterButton.addActionListener(cmdHandler);
		waterButton.setActionCommand("cmd3");
		
		fertilizeButton = new JButton("Fertilize");
		fertilizeButton.setBackground(Color.BLACK);
		fertilizeButton.setForeground(Color.white);
		fertilizeButton.setFont(normalFont);
		fertilizeButton.setFocusPainted(false);
		cmdButtonPanel.add(fertilizeButton);
		fertilizeButton.addActionListener(cmdHandler);
		fertilizeButton.setActionCommand("cmd4");
		
		harvestButton = new JButton("Harvest");
		harvestButton.setBackground(Color.BLACK);
		harvestButton.setForeground(Color.white);
		harvestButton.setFont(normalFont);
		harvestButton.setFocusPainted(false);
		cmdButtonPanel.add(harvestButton);
		harvestButton.addActionListener(cmdHandler);
		harvestButton.setActionCommand("cmd5");
		
		farmerPanel = new JPanel();
		farmerPanel.setBounds(5, 8, 975, 50);
		farmerPanel.setBackground(lightBrown);
		farmerPanel.setLayout(new GridLayout(1, 6));
		frame.add(farmerPanel);
		
		levelLabel = new JLabel("Level: ");
		levelLabel.setFont(smallFont);
		levelLabel.setForeground(Color.BLACK);
		farmerPanel.add(levelLabel);
		
		farmerLvlLabel = new JLabel();
		farmerLvlLabel.setFont(smallFont);
		farmerLvlLabel.setForeground(Color.BLACK);
		farmerPanel.add(farmerLvlLabel);
		
		objectCoinsLabel = new JLabel("ObjectCoins: ");
		objectCoinsLabel.setFont(smallFont);
		objectCoinsLabel.setForeground(Color.BLACK);
		farmerPanel.add(objectCoinsLabel);
		
		farmerObjCoinsLabel = new JLabel();
		farmerObjCoinsLabel.setFont(smallFont);
		farmerObjCoinsLabel.setForeground(Color.BLACK);
		farmerPanel.add(farmerObjCoinsLabel);
		
		expLabel = new JLabel("Exp: ");
		expLabel.setFont(smallFont);
		expLabel.setForeground(Color.BLACK);
		farmerPanel.add(expLabel);
		
		farmerExpLabel = new JLabel();
		farmerExpLabel.setFont(smallFont);
		farmerExpLabel.setForeground(Color.BLACK);
		farmerPanel.add(farmerExpLabel);
		
		dayPanel = new JPanel();
		dayPanel.setBackground(lightBrown);
		dayPanel.setLayout(new GridLayout(1, 2));
		dayPanel.setBounds(5, 50, 150, 38);
		frame.add(dayPanel);
		
		dayLabel = new JLabel("Day: ");
		dayLabel.setFont(smallFont);
		dayLabel.setForeground(Color.black);
		dayPanel.add(dayLabel);
		
		dayNumberLabel = new JLabel();
		dayNumberLabel.setFont(smallFont);
		dayNumberLabel.setForeground(Color.black);
		dayNumberLabel.setText("" + currentDay);
		dayPanel.add(dayNumberLabel);
		
		farmLotPanel = new JPanel();
		farmLotPanel.setBounds(95, 95, 775, 600);
		farmLotPanel.setBackground(new Color(92, 64, 51));
		farmLotPanel.setLayout(new GridLayout(10, 5));
		frame.add(farmLotPanel);
		
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 5; j++) {
				farmLotLabel[i][j] = new JLabel("x" + i + "y" + j);
				farmLotLabel[i][j].setFont(smallFont);
				farmLotLabel[i][j].setForeground(Color.white);
				farmLotLabel[i][j].setHorizontalAlignment(JLabel.CENTER);
			}
		}
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 5; j++) {
				farmLotPanel.add(farmLotLabel[i][j]);
			}
		}
		
		nextDayPanel = new JPanel();
		nextDayPanel.setBackground(lightBrown);
		nextDayPanel.setBounds(47, 47, 280, 50);
		frame.add(nextDayPanel);
	
		nextDayButton = new JButton("Next Day");
		nextDayButton.setBackground(Color.black);
		nextDayButton.setForeground(Color.white);
		nextDayButton.setFont(smallFont);
		nextDayButton.setFocusPainted(false);
		nextDayButton.setAlignmentX(JButton.RIGHT_ALIGNMENT);
		nextDayButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				nextDay();			
			}
		});
		nextDayPanel.add(nextDayButton);

		promptPanel = new JPanel();
		promptPanel.setBackground(lightBrown);
		promptPanel.setBounds(330, 48, 350, 120);
		frame.add(promptPanel);
		
		promptArea = new JTextArea("Welcome to The Best Farm Game Ever! Don't mean to backseat, but try plowing a tile first.");
		promptArea.setBounds(330, 48, 350, 120);
		promptArea.setBackground(lightBrown);
		promptArea.setForeground(Color.black);
		promptArea.setFont(promptFont);
		promptArea.setLineWrap(true);
		promptArea.setEditable(false);
		promptPanel.add(promptArea);
		
		userInputPanel = new JPanel();
		userInputPanel.setLayout(new FlowLayout());
		userInputPanel.setBackground(lightBrown);
		userInputPanel.setBounds(670, 48, 300, 150);
		frame.add(userInputPanel);
		
		farmerSetup();
	}
	public void farmerSetup() {
		farmerLvlLabel.setText("" + farmer.getLevel());
		farmerObjCoinsLabel.setText("" + farmer.getObjectCoins());
		farmerExpLabel.setText("" + farmer.getExp());
	}
	
	public void performCommand(String cmd) {
		switch(cmd) {
		case "S": case "s":
			System.out.println("Available seeds to plant: \n" + seedList);
			System.out.println("[0] - Turnip\n[1] - Carrot\n[2] - Potato\n[3] - Rose\n[4] - Tulips\n[5] - Sunflower\n[6] - Mango tree\n[7] - Apple tree");
	
			String feedback = chosenTile.plant(seedList.get(0)/* add index later*/, farmer); // Plants the seed onto the tile, and returns a String whether it was a success or a failure along with its reason.
			System.out.println(feedback);
			break;

		case "P": case "p":
			feedback = chosenTile.plow(farmer);
			System.out.println(feedback);
			break;

		case "W": case "w":
			feedback = chosenTile.water(farmer);
			System.out.println(feedback);
			break;
			
		case "F": case "f":
			feedback = chosenTile.fertilize(farmer);
			System.out.println(feedback);
			break;
			
		case "H": case "h":
			feedback = chosenTile.harvest(farmer);
			System.out.println(feedback);
			break;
			
		case "N": case "n":
			nextDay(); // calls the nextDay() method
			break;
			
		case "Q": case "q":
			isRunning = false;
			System.out.println("Exiting the game.");
			break;
		default:
			System.out.println("Unknown command: " + cmd);
		}	
	}
	
	public void checkConditions() {
		chosenTile.checkWither(); 
		if (seedList.isEmpty() && chosenTile.getPlantedCrop() == null) // If there are no seeds left from the seedList and the tile has no crop planted in it, the game ends.
			isRunning = false;
	}
	
	public void nextDay() {
		dayNumberLabel.setText("" + ++currentDay);
	//	if (chosenTile.getPlantedCrop() != null)
		//	chosenTile.getPlantedCrop().setCropAge(chosenTile.getPlantedCrop().getCropAge() + 1);
	}
	
	public class TitleScreenHandler implements ActionListener{
		
		public void actionPerformed(ActionEvent event) {
			createGameScreen();
		}
	}
	public class CmdHandler implements ActionListener{
		
		public void actionPerformed(ActionEvent event) {
			
			String playerCmd = event.getActionCommand();
			
			switch(playerCmd) {
			case "cmd1":
				promptArea.setText("Choose a seed to plant."); break;
			case "cmd2":
				promptArea.setText("Choose a tile to plow. ");
	
				userInputX = new JTextField("x coordinate", 8);
				userInputX.setBackground(darkBrown);
				userInputX.setForeground(Color.white);
				userInputX.setFont(promptFont);
				userInputPanel.add(userInputX);
				
				userInputY = new JTextField("y coordinate", 8);
				userInputY.setBackground(darkBrown);
				userInputY.setForeground(Color.white);
				userInputY.setFont(promptFont);
				userInputPanel.add(userInputY);
				
				userInputButton = new JButton("Go!");
				userInputButton.setBackground(Color.black);
				userInputButton.setForeground(Color.white);
				userInputButton.setFont(smallFont);
				userInputButton.setFocusPainted(false);
				userInputPanel.add(userInputButton);
				userInputButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						String s = e.getActionCommand();
						if (s.equals("Go!")) {
							chosenTileX = Integer.parseInt(userInputX.getText());
							chosenTileY = Integer.parseInt(userInputY.getText());
							
							chosenTile = tile[chosenTileX][chosenTileY];
							feedback = chosenTile.plow(farmer);
							promptArea.setText(feedback);
							if (feedback.equals("You have successfully plowed the tile! You gained 0.5 exp! \\(^_^)/")) {
								farmLotLabel[chosenTileX][chosenTileY].setText("x" + chosenTileX + "y" + chosenTileY + "(plowed)");
								farmerExpLabel.setText("" + farmer.getExp());
							}
							userInputX.setVisible(false);
							userInputY.setVisible(false);
							userInputButton.setVisible(false);
							userInputPanel.remove(userInputX);
							userInputPanel.remove(userInputY);
							userInputPanel.remove(userInputButton);
							
						}
					}
								
				}
			
				);
				break;
			case "cmd3":
				promptArea.setText("Choose a tile to water."); break;
			case "cmd4":
				promptArea.setText("Choose a tile to fertilize."); break;
			case "cmd5":
				promptArea.setText("Choose a tile to harvest."); break;
			}
		}
	}

}
