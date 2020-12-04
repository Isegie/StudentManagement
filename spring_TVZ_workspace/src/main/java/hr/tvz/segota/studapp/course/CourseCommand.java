package hr.tvz.segota.studapp.course;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

public class CourseCommand {
    private Long id;
    @NotBlank(message = "Name must not be empty")
    @JsonProperty("name")
    private String name;
    @NotNull(message = "Number of ECTS points must be entered")
    @PositiveOrZero(message = "Number of ECTS must be emtered as a positive integer")
    @JsonProperty("numberOfECTS")
    private Integer numberOfECTS;

}
