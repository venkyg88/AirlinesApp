package venkat.com.kayakapp.model;

/**
 * Created by venkatgonuguntala on 3/20/16.
 */
public class AirlinesListItem {

    public String getAirlineName() {
        return airlineName;
    }

    public void setAirlineName(String airlineName) {
        this.airlineName = airlineName;
    }

    public String getAirlineImage() {
        return airlineImage;
    }

    public void setAirlineImage(String airlineImage) {
        this.airlineImage = airlineImage;
    }

    private String airlineName;
    private String airlineImage;
}
