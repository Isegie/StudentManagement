package hr.tvz.segota.studapp.course;


public class CourseDTO {
    private String name;
    private Integer numberOfECTS;

    public CourseDTO(String name, Integer numberOfECTS) {
        this.name = name;
        this.numberOfECTS = numberOfECTS;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumberOfECTS(Integer numberOfECTS) {
        this.numberOfECTS = numberOfECTS;
    }

    public String getName() {
        return name;
    }

    public Integer getNumberOfECTS() {
        return numberOfECTS;
    }

    @Override
    public String toString() {
        return "CourseDTO{" +
                "name='" + name + '\'' +
                ", numberOfECTS=" + numberOfECTS +
                '}';
    }
}
