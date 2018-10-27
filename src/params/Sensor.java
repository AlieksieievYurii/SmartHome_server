package params;

public class Sensor
{
    private String name;
    private int value;

    public Sensor(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }
}
