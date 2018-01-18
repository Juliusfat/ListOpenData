package cp.fr.listopendata;

/**
 * Created by Formation on 18/01/2018.
 */

public class User {

    private String name;
    private String adress;
    private int codepostal;
    private String ville;
    private Double lat;
    private Double lon;

    public User() {
    }


    public User(String name, String adress, int codepostal, String ville, Double lat, Double lon) {
        this.name = name;
        this.adress = adress;
        this.codepostal = codepostal;
        this.ville = ville;
        this.lat = lat;
        this.lon = lon;


    }


    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public int getCodepostal() {
        return codepostal;
    }

    public void setCodepostal(int codepostal) {
        this.codepostal = codepostal;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }
}
