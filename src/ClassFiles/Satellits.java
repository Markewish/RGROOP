package ClassFiles;

/**
 * ОПИСЫВАЕТ СПУТНИКИ ПЛАНЕТ
 * */

public class Satellits  extends CosmicBody {

    public Satellits(String name, double mass, double averageTemperature) throws Exception {
        if(mass < 0 || averageTemperature < -273) throw new Exception("ERROR! Invalid Input in satellites");
        else {
            this.name = name;
            this.mass = mass;
            this.averageTemperature = averageTemperature;
        }
    }

}
