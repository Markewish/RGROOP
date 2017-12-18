package ClassFiles;

/**
 *  ОПИСЫВАЕТ ЗВЕЗДУ
 * */

public class Stars extends CosmicBody {

    private String starSystem;
    private int numberPlanets;


    public Stars(String name, String starSystem, double mass, double averageTemperature, int numberPlanets) throws Exception {
        if(mass < 0 || averageTemperature < -273 || numberPlanets < 0) throw new Exception("ERROR! Invalid Input in star");
        else {
            this.name = name;
            this.mass = mass;
            this.averageTemperature = averageTemperature;
            this.starSystem = starSystem;
            this.numberPlanets = numberPlanets;
        }
    }
    public String getStarSystem() {
        return starSystem;
    }

    public int getNumberPlanets() {
        return numberPlanets;
    }
}
