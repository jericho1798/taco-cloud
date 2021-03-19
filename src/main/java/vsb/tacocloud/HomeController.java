package vsb.tacocloud;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller                                 //The controller
public class HomeController {
    @GetMapping("/")                        //Handles request for the root path
    public String home() {
        return "home";                      //Returns the view name
    }
}
