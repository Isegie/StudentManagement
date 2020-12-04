package hr.tvz.segota.studapp.student;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.*;
import java.time.LocalDate;

public class StudentCommand {
    @NotBlank(message = "First name must not be empty")
    @JsonProperty("firstName")
    private String firstName;
    @NotBlank(message = "Last name must not be empty")
    @JsonProperty("lastName")
    private String lastName;
    @NotBlank(message = "JMBAG must not be empty")
    @Pattern(message = "JMBAG must have 10 digits", regexp = "[\\d]{10}")
    @JsonProperty("jmbag")
    private String jmbag;
    @NotNull(message = "Date of birth is obligatory")
    @Past(message = "Date of birth must be in the past")
    @JsonProperty("dateOfBirth")
    private LocalDate dateOfBirth;
    @NotNull(message = "Number of ECTS points must be entered")
    @PositiveOrZero(message = "Number of ECTS must be emtered as a positive integer")
    @Max(message = "Maximum number of ECTS points is 480", value = 480)
    @JsonProperty("numberOfECTS")
    private Integer numberOfECTS;

    //@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getJmbag() {
        return jmbag;
    }

    public void setJmbag(String jmbag) {
        this.jmbag = jmbag;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Integer getNumberOfECTS() {
        return numberOfECTS;
    }

    public void setNumberOfECTS(Integer numberOfECTS) {
        this.numberOfECTS = numberOfECTS;
    }
}
