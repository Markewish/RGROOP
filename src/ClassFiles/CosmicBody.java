package ClassFiles;
/**
 * АБСТРАКТНЫЙ КЛАСС ОБЩИХ ДАННЫХ
 * */
public abstract class CosmicBody {
    protected String name;
    protected double mass;
    protected double averageTemperature; //средняя температура

    @Override
    public String toString() {
        return name ;
    }

    public String getName() {
        return name;
    }

    public double getMass() {
        return mass;
    }

    public double getAverageTemperature() {
        return averageTemperature;
    }
}
