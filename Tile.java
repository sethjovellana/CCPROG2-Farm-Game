package MainApp;
import java.util.Random;
public class Tile {
	
	private Crop plantedCrop = null;
	private boolean isPlowed;
	private boolean hasRocks;
	private Random random = new Random();
	private int productsProduced;
	
	public String plow(Farmer farmer) {
		if (isPlowed) // If tile is already plowed.
			return "This tile is already plowed!";
		if (plantedCrop != null) // If a crop already exists in the tile.
			return "A crop is already planted in this tile!";
		// Success
		isPlowed = true;
		farmer.setExp(farmer.getExp() + 0.5);
		return "You have successfully plowed the tile! You gained 0.5 exp! \\(^_^)/";
	}
	
	public String plant(Crop seed, Farmer farmer) {
		if (plantedCrop != null) // If a crop already exists in the tile.
			return "A crop is already planted in this tile!";
		if (!isPlowed) // If tile is unplowed.
			return "This tile is unplowed! Make sure to plow the tile before you plant a seed.";
		// Success
		plantedCrop = seed; // assigns the seed to plantedCrop
		farmer.setObjectCoins(farmer.getObjectCoins() - plantedCrop.getSeedCost()); // Deducts from the player's objectCoins the cost of the crop planted.
		
		return "You have succesfully planted a seed! \\(^o^)/\n" + "-" + plantedCrop.getSeedCost() + " objectCoins";
	}
	
	public String water(Farmer farmer) {
		if (plantedCrop == null) // If there is no crop planted.
			return "There is no crop to water!";
		if (plantedCrop.getCurrentWater() == plantedCrop.getWaterLimit()) // If the water cap is already reached.
			return "You already reached the water limit! Stop wasting water!";
		// Success
		plantedCrop.setCurrentWater(plantedCrop.getCurrentWater() + 1);
		farmer.setExp(farmer.getExp() + 0.5);
		return "You have successfully watered the crop! You gained 0.5 exp! \\(`u`)/ \nCurrent: " + plantedCrop.getCurrentWater() + " | Required: " + plantedCrop.getWaterReq() + " | Bonus: " + plantedCrop.getWaterLimit();
	}
	
	public String fertilize(Farmer farmer) {
		if (plantedCrop == null) // If a crop does not exist in the tile.
			return "There is no crop to fertilize!";
		if (plantedCrop.getCurrentFertilizer() == plantedCrop.getFertilizerLimit()) // If the fertilizer cap is already reached.
			return "You already reached the fertilizer limit! Excessive fertilizing will do more harm than good!";
		//Success
		plantedCrop.setCurrentFertilizer(plantedCrop.getCurrentFertilizer() + 1);
		farmer.setExp(farmer.getExp() + 4);
		farmer.setObjectCoins(farmer.getObjectCoins() - 10);
		return "You have successfully fertilized the crop! You gained 4 exp! \\(`u`)/ \n-10 objectCoins\nCurrent: " + plantedCrop.getCurrentFertilizer() + " | Required: " + plantedCrop.getFertilizerReq() + " | Bonus: " + plantedCrop.getFertilizerLimit();
	}
	
	public String shovel(Farmer farmer) {
		if (plantedCrop == null || hasRocks || !isPlowed) {
			farmer.setObjectCoins(farmer.getObjectCoins() - 7);
			return "You lost 7 ObjectCoins for nothing.";
		}
		if (isPlowed) {
			isPlowed = false;
			farmer.setObjectCoins(farmer.getObjectCoins() - 7);
			return "Haha you just wasted time.";
		}
		farmer.setObjectCoins(farmer.getObjectCoins() - 7);
		farmer.setExp(farmer.getExp() + 2);
		plantedCrop = null;
		isPlowed = false;
				
		return "You successfully used the shovel.";
	}
	
	public String pickaxe(Farmer farmer) {
		if (hasRocks) {
			hasRocks = false;
			farmer.setObjectCoins(farmer.getObjectCoins() - 50);
			farmer.setExp(farmer.getExp() + 15);
			return "You removed the rock.";
		}
		
		return "Cannot use on anything besides on a rock.";
	}

	public double finalHarvestPrice() { // Computation as defined in the MP specification.
		productsProduced = random.nextInt(plantedCrop.getMinProduce(), plantedCrop.getMaxProduce() + 1);
		int harvestTotal = productsProduced + plantedCrop.getBaseSellingPrice();
		double waterBonus = harvestTotal * 0.2 * (plantedCrop.getCurrentWater() - 1);
		double fertilizerBonus = harvestTotal * 0.5 * plantedCrop.getCurrentFertilizer();
		double finalHarvestPrice = harvestTotal + waterBonus + fertilizerBonus;
		
		if (plantedCrop.getCropType().equals("Flower"))
			finalHarvestPrice = finalHarvestPrice * 1.1;
		
		return finalHarvestPrice;
	}
	
	public String harvest(Farmer farmer) {
		if (plantedCrop != null) { // The harvest method will only execute if a crop already exists in the tile. This is to prevent NullPointerException during run-time.
			if (plantedCrop.getCropAge() != plantedCrop.getHarvestTime()) // If the crop is not mature enough for harvest yet.
				return "The crop is not ready for harvest yet!";
			if (plantedCrop.getCurrentWater() < plantedCrop.getWaterReq()) // If water requirement is not met.
				return "Water requirement is not met. (~T_T~)";
			if (plantedCrop.getCurrentFertilizer() < plantedCrop.getFertilizerReq()) // If fertilizer requirement is not met.
				return "Fertilizer requirement is not met. (~T_T~)";
			// Success
			System.out.println("Harvest Successful! \\(^o^)/");
			double finalHarvestPrice = finalHarvestPrice();
			String feedback = plantedCrop.getSeedName() + " had " + productsProduced + " product(s) produced. You earned " + finalHarvestPrice + " objectCoins and " + plantedCrop.getExpYield() + " EXP! \\(*o*)/";
			farmer.setObjectCoins(farmer.getObjectCoins() + finalHarvestPrice);
			farmer.setExp(farmer.getExp() + plantedCrop.getExpYield());
			plantedCrop = null;
			isPlowed = false;
			return feedback;
		}
		else
			return "There is no crop to harvest!";
	}
	
	public void checkWither() {
		if (plantedCrop != null) {
			if (plantedCrop.getCropAge() > plantedCrop.getHarvestTime()) { // If the crop's age exceeds the harvest time, the crop is already withered. Since removal of withered crops is not yet required,
																		   //   automatically sets the plantedCrop to null, which would trigger the checkConditions() method in the Main class to terminate the game.
				System.out.println("The crop " + plantedCrop.getSeedName() + " had already withered due to failure to harvest on harvest time. /(T ~ T)\\");
				plantedCrop = null;
				isPlowed = false;
			}
		}
	}

	public Crop getPlantedCrop() {
		return plantedCrop;
	}
	
	public boolean getIsPlowed() {
		return isPlowed;
	}

	public boolean getHasRocks() {
		return hasRocks;
	}

	public void setHasRocks(boolean hasRocks) {
		this.hasRocks = hasRocks;
	}
}
