package MainApp;

public class Crop {
	private String seedName;
	private String cropType;
	
	private int cropAge = 0; // Current age of crop before harvest time.
	private int harvestTime;
	
	private int currentWater = 0;
	private int waterReq;
	private int waterLimit; // Also pertains to the required times a crop should be watered to get the bonus.
	
	private int currentFertilizer = 0;
	private int fertilizerReq;
	private int fertilizerLimit; // Also pertains to the required times a crop should be fertilized to get the bonus.
	
	private int minProduce;
	private int maxProduce;
	
	private int seedCost;
	private int baseSellingPrice;
	private double expYield;
	
	
	Crop(String seedName, String cropType, int harvestTime, int waterReq, int waterLimit, int fertilizerReq, int fertilizerLimit, 
			int minProduce, int maxProduce, int seedCost, int baseSellingPrice, double expYield) {

		this.seedName = seedName;
		this.cropType = cropType;
		this.harvestTime = harvestTime;
		this.waterReq = waterReq;
		this.waterLimit = waterLimit;
		this.fertilizerReq = fertilizerReq;
		this.fertilizerLimit = fertilizerLimit;
		this.minProduce = minProduce;
		this.maxProduce = maxProduce;
		this.seedCost = seedCost;
		this.baseSellingPrice = baseSellingPrice;
		this.expYield = expYield;
	}
	
	//*****************************************************************************************
	//GETTER for seedName
	public String getSeedName() {
		return seedName;
	}
	//GETTER for cropType
	public String getCropType() {
		return cropType;
	}
	//*****************************************************************************************
	// GETTER AND SETTER for cropAge
	public int getCropAge() {
		return cropAge;
	}
	
	public void setCropAge(int cropAge) {
		this.cropAge = cropAge;
	}
	//*****************************************************************************************
	
	// GETTER for harvestTime
	public int getHarvestTime() {
		return harvestTime;
	}
	//*****************************************************************************************
	
	// GETTER AND SETTER for currentWater
	public int getCurrentWater() {
		return currentWater;
	}

	public void setCurrentWater(int currentWater) {
		this.currentWater = currentWater;
	}
	//*****************************************************************************************
	
	// GETTER for waterReq
	public int getWaterReq() {
		return waterReq;
	}
	//*****************************************************************************************
	
	// GETTER for waterLimit
	public int getWaterLimit() {
		return waterLimit;
	}
	//*****************************************************************************************

	// GETTER AND SETTER for currentFertilizer
	public int getCurrentFertilizer() {
		return currentFertilizer;
	}

	public void setCurrentFertilizer(int currentFertilizer) {
		this.currentFertilizer = currentFertilizer;
	}
	//*****************************************************************************************
	
	// GETTER for fertilizerReq
	public int getFertilizerReq() {
		return fertilizerReq;
	}
	//*****************************************************************************************
	
	// GETTER for fertilizerLimit
	public int getFertilizerLimit() {
		return fertilizerLimit;
	}
	//*****************************************************************************************
	
	// GETTERS for minProduce and maxProduce
	public int getMinProduce() {
		return minProduce;
	}
	
	public int getMaxProduce() {
		return maxProduce;
	}
	//*****************************************************************************************
	
	// GETTER for seedCost
	public int getSeedCost() {
		return seedCost;
	}
	//*****************************************************************************************
	
	// GETTER for baseSellingPrice
	public int getBaseSellingPrice() {
		return baseSellingPrice;
	}
	//*****************************************************************************************
	
	// GETTER for expYield
	public double getExpYield() {
		return expYield;
	}
	//*****************************************************************************************
	
	@Override
	public String toString() {
		return seedName + " | " + cropType + " | " + harvestTime + " | " + waterReq + "(" + waterLimit + ")" + " | " + fertilizerReq + "(" + fertilizerLimit + ")" + " | " + 
				minProduce + "-" + maxProduce + " | " + seedCost + " | " + baseSellingPrice + " | " + expYield;
	}
}
