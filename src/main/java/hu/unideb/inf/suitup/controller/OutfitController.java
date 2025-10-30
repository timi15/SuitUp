package hu.unideb.inf.suitup.controller;

import hu.unideb.inf.suitup.entity.OutfitEntity;
import hu.unideb.inf.suitup.service.OutfitService;
import hu.unideb.inf.suitup.service.UserService;
import hu.unideb.inf.suitup.service.WardrobeItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("outfits")
@RequiredArgsConstructor
public class OutfitController {

    private final WardrobeItemService wardrobeItemService;
    private final OutfitService outfitService;
    private final UserService userService;

    @GetMapping("")
    public String showOutfitsPage(Model model) {
        Long userId = userService.getCurrentUserId();
        model.addAttribute("outfits", outfitService.findAll(userId));
        return "outfits";
    }

    @GetMapping("/save")
    public String showOutfitForm(Model model) {
        Long userId = userService.getCurrentUserId();

        model.addAttribute("tops", wardrobeItemService.findByType(userId, "felső"));
        model.addAttribute("pants", wardrobeItemService.findByType(userId, "nadrág/szoknya"));
        model.addAttribute("dresses", wardrobeItemService.findByType(userId, "egyrészes"));
        model.addAttribute("jackets", wardrobeItemService.findByType(userId, "kabát"));
        model.addAttribute("shoes", wardrobeItemService.findByType(userId, "cipő"));
        model.addAttribute("accessories", wardrobeItemService.findByType(userId, "kiegészítő"));

        model.addAttribute("outfit", new OutfitEntity());

        return "outfit-form";
    }

    @PostMapping("/save")
    public String saveOutfit(
            @ModelAttribute OutfitEntity outfit,
            @RequestParam(required = false) Long topId,
            @RequestParam(required = false) Long pantsId,
            @RequestParam(required = false) Long dressId,
            @RequestParam(required = false) Long jacketId,
            @RequestParam(required = false) Long shoesId,
            @RequestParam(required = false) Long accessoryId
    ) {
        Long userId = userService.getCurrentUserId();
        outfitService.save(userId, outfit, topId, pantsId, dressId, jacketId, shoesId, accessoryId);
        return "redirect:/outfits";
    }

    @PostMapping("/delete/{id}")
    public String deleteOutfit(@PathVariable Long id) {
        Long userId = userService.getCurrentUserId();
        outfitService.deleteById(userId, id);
        return "redirect:/outfits";
    }
}