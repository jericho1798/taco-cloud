package vsb.tacocloud.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import vsb.tacocloud.Ingredient;
import vsb.tacocloud.Ingredient.Type;
import vsb.tacocloud.Order;
import vsb.tacocloud.Taco;
import vsb.tacocloud.data.IngredientRepository;
import vsb.tacocloud.data.TacoRepository;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


//import sun.text.normalizer.NormalizerBase;


@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {

    private final IngredientRepository ingredientRepository;

    private TacoRepository designRepo;

    @Autowired
    public DesignTacoController(IngredientRepository ingredientRepository, TacoRepository designRepo) {
        this.ingredientRepository = ingredientRepository;
        this.designRepo = designRepo;
    }

    @ModelAttribute(name = "order")
    public  Order order() {
        return new Order();
    }

    @ModelAttribute(name = "taco")
    public Taco taco() {
        return  new Taco();
    }

    @GetMapping
    public String showDesignForm(Model model) {
        List<Ingredient> ingredients = new ArrayList<>();
        ingredientRepository.findAll().forEach(ingredients::add);

        Type[] types = Ingredient.Type.values();
        for(Type type : types) {
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(ingredients, type));
        }
        return "design";
    }

    @PostMapping
    public String processDesign(
            @Valid Taco design, Errors errors,
            @ModelAttribute Order order) {
        if(errors.hasErrors()) {
            return "design";
        } else {
            Taco saved = designRepo.save(design);
            order.addDesign(saved);
            return "redirect:/orders/current";
        }
    }

    private List<Ingredient> filterByType(
            List<Ingredient> ingredients, Type type) {
        return ingredients
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }

}
