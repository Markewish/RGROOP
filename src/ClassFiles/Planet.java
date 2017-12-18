package ClassFiles;

public class Planet  extends CosmicBody {

    private int population; //население
    private double oxygenLevel; //уровень кислорода
    private int numberSatellites;  //количество спутников

    public Planet(String name, double mass, int population, double oxygenLevel, double averageTemperature, int numberSatellites) throws Exception {
        if(mass < 0 || population < 0 || oxygenLevel < 0 || numberSatellites < 0 || averageTemperature < -273){
            throw new Exception("ERROR! Invalid Input in planet");
        }else{
            this.name = name;
            this.mass = mass;
            this.population = population;
            this.oxygenLevel = oxygenLevel;
            this.averageTemperature = averageTemperature;
            this.numberSatellites = numberSatellites;
        }
    }

    public int getPopulation() {
        return population;
    }

    public double getOxygenLevel() {
        return oxygenLevel;
    }

    public int getNumberSatellites() {
        return numberSatellites;
    }
}
