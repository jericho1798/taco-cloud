package vsb.tacocloud;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
public class Taco {
   // @NotNull
  //  @Size(min = 5, message = "Name must be at least 5 characters long")
    @NotBlank(message = "Name is required")
    private String name;

   // @Size(min = 1, message = "You must choose at least 1 ingredient")
   // @NotNull(message = "You must choose at least 1 ingredient")
   // @NotEmpty
    private List<String> ingredients;
}
