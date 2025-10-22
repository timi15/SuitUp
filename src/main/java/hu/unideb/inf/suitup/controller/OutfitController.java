package hu.unideb.inf.suitup.controller;

import hu.unideb.inf.suitup.entity.OutfitEntity;
import hu.unideb.inf.suitup.service.OutfitService;
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

    @GetMapping("")
    public String showOutfitsPage(Model model){
        model.addAttribute("outfits", outfitService.findAll());
        return "outfits";
    }

    @GetMapping("/save")
    public String showOutfitForm(Model model) {
        model.addAttribute("tops", wardrobeItemService.findByType("felső"));
        model.addAttribute("pants", wardrobeItemService.findByType("nadrág/szoknya"));
        model.addAttribute("dresses", wardrobeItemService.findByType("egyrészes"));
        model.addAttribute("jackets", wardrobeItemService.findByType("kabát"));
        model.addAttribute("shoes", wardrobeItemService.findByType("cipő"));
        model.addAttribute("accessories", wardrobeItemService.findByType("kiegészítő"));

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
        outfitService.save(outfit, topId, pantsId, dressId, jacketId, shoesId, accessoryId);
        return "redirect:/outfits";
    }
}
