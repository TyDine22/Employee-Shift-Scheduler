public class Employee {
    private String name;
    private String prefer;
    private String position;
    private int legalWorkHour;

    public Employee(String name, String position, String prefer, int legalWorkHour){
        this.prefer = prefer;
        this.name = name;
        this.position = position;
        this.legalWorkHour = legalWorkHour;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrefer() {
        return prefer;
    }

    public void setPrefer(String prefer) {
        this.prefer = prefer;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getLegalWorkHour() {
        return legalWorkHour;
    }

    public void setLegalWorkHour(int legalWorkHour) {
        this.legalWorkHour = legalWorkHour;
    }
}
