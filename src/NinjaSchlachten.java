public class NinjaSchlachten {
    private int id;
    private String name;
    private String stufe;
    private String description;
    private String datum;
    private double kraftPunkte;

    public NinjaSchlachten(int id, String name, String stufe, String description, String datum, double kraftPunkte) {
        this.id = id;
        this.name = name;
        this.stufe = stufe;
        this.description = description;
        this.datum = datum;
        this.kraftPunkte = kraftPunkte;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStufe() {
        return stufe;
    }

    public void setStufe(String stufe) {
        this.stufe = stufe;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public double getKraftPunkte() {
        return kraftPunkte;
    }

    public void setKraftPunkte(double kraftPunkte) {
        this.kraftPunkte = kraftPunkte;
    }

    @Override
    public String toString() {
        return "NinjaSchlachten{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", stufe='" + stufe + '\'' +
                ", description='" + description + '\'' +
                ", datum='" + datum + '\'' +
                ", kraftPunkte=" + kraftPunkte +
                '}';
    }
}
