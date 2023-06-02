package MainApp;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import java.util.Arrays;

public class Main {

	private static final Scanner input = new Scanner(System.in); // user input

	private static boolean isRunning = true; // Condition to keep the game running.
	private static int currentDay = 1;
	
	private static final List<Crop> seedList = new ArrayList<Crop>(8);
	private static final Tile tile[][] = new Tile[10][5]; // represents the 10x5 lot farm
	private static Tile chosenTile = null;
	
	private static final Farmer farmer = new Farmer();
	


	public static void main(String[] args) {
		seedList.add(new Crop("Turnip", "Root crop", 2, 1, 2, 0, 1, 1, 2, 5, 6, 5));
		seedList.add(new Crop("Carrot", "Root crop", 3, 1, 2, 0, 1, 1, 2, 10, 9, 7.5));
		seedList.add(new Crop("Potato", "Root crop", 5, 3, 4, 1, 2, 1, 10, 20, 3, 12.5));
		seedList.add(new Crop("Rose", "Flower", 1, 1, 2, 0, 1, 1, 0, 5, 5, 2.5));
		seedList.add(new Crop("Tulips", "Flower", 2, 2, 3, 0, 1, 1, 0, 10, 9, 5));
		seedList.add(new Crop("Sunflower", "Flower", 3, 2, 3, 1, 2, 1, 0, 20, 19, 7.5));
		seedList.add(new Crop("Mango", "Fruit tree", 10, 7, 7, 4, 4, 5, 15, 100, 8, 25));
		seedList.add(new Crop("Apple", "Fruit tree", 10, 7, 7, 5, 5, 10, 15, 200, 5, 25));
		
		//initializeLotFarm();
		
		new FarmGame();
		/*
		tile[0][0] = new Tile();
		chosenTile = tile[0][0];

		while (isRunning) {	
			printState();
			String cmd = userInput();
			performCommand(cmd);
			checkConditions();
		}
		System.out.println("objectCoins: " + farmer.getObjectCoins() + " | exp: " + farmer.getExp()); // final result
		input.close();	
	}
	
	public static void initializeLotFarm() {
		int i, j;
		int rows = 10;
		int columns = 5;
		
		for (i = 0; i < rows; i++) {
			for (j = 0; j < columns; j++) {
				tile[i][j] = new Tile();
			}			
		}
	}

	public static void printState() {
		System.out.println("____________________________________________________________________________________________________________________________________________________________");
		System.out.println("DAY " + currentDay);
		System.out.println("objectCoins: " + farmer.getObjectCoins() + " | exp: " + farmer.getExp());
		System.out.println();
		if (chosenTile.getPlantedCrop() == null) // If there is no crop planted in the tile.
			if (chosenTile.getIsPlowed()) // If the tile is plowed.
				System.out.println("There is a plowed tile available.");
			else // If the tile is unplowed.
				System.out.println("There is an unplowed tile available.");
		else { // Prints out the whereabouts of the crop currently planted in the tile.
			System.out.println(chosenTile.getPlantedCrop().getSeedName() + " is occupying the tile.");
			System.out.println("\nWater Requirement: " + chosenTile.getPlantedCrop().getCurrentWater() + "/" + chosenTile.getPlantedCrop().getWaterReq() + "(" + chosenTile.getPlantedCrop().getWaterLimit() + ")");
			System.out.println("Fertilizer Requirement: " + chosenTile.getPlantedCrop().getCurrentFertilizer() + "/" + chosenTile.getPlantedCrop().getFertilizerReq() + "(" + chosenTile.getPlantedCrop().getFertilizerLimit() + ")");
			System.out.println("Crop days before harvest: " + chosenTile.getPlantedCrop().getCropAge() + "/" + chosenTile.getPlantedCrop().getHarvestTime() + "\n");
		}
	}
	
	public static String userInput() { // Prompts the user about the available commands, and scans the user's input.
		System.out.println("[S] - Plant Seed | [P] - Plow | [W] - Water | [F] - Fertilize | [H] - Harvest | [N] - Advance to next day | [Q] - Quit"); 
		System.out.println("____________________________________________________________________________________________________________________________________________________________");
		return input.next();
	}
	
	public static void performCommand(String cmd) {
		switch(cmd) {
		case "S": case "s":
			System.out.println("Available seeds to plant: \n" + seedList);
			System.out.println("[0] - Turnip\n[1] - Carrot\n[2] - Potato\n[3] - Rose\n[4] - Tulips\n[5] - Sunflower\n[6] - Mango tree\n[7] - Apple tree");

			int index = input.nextInt();			
			String feedback = chosenTile.plant(seedList.get(index), farmer); // Plants the seed onto the tile, and returns a String whether it was a success or a failure along with its reason.
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
	
	public static void checkConditions() {
		chosenTile.checkWither(); 
		if (seedList.isEmpty() && chosenTile.getPlantedCrop() == null) // If there are no seeds left from the seedList and the tile has no crop planted in it, the game ends.
			isRunning = false;
	}
	
	public static void nextDay() {
		currentDay++;
		System.out.println("A day passes...");
		if (chosenTile.getPlantedCrop() != null)
			chosenTile.getPlantedCrop().setCropAge(chosenTile.getPlantedCrop().getCropAge() + 1);
	}
	*/
}}
