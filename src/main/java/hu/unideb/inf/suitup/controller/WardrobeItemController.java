package hu.unideb.inf.suitup.controller;

import hu.unideb.inf.suitup.entity.WardrobeItemEntity;
import hu.unideb.inf.suitup.service.WardrobeItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/wardrobe-items")
public class WardrobeItemController {

    final WardrobeItemService wardrobeItemService;

    @GetMapping("")
    public String wardrobeItemsPage(Model model){
        model.addAttribute("wardrobeItems", wardrobeItemService.findAll());
        return "wardrobe-items";
    }

    @GetMapping("/save")
    public String showUploadWardrobeItemForm(Model model){
        model.addAttribute("wardrobeItem", new WardrobeItemEntity());
        return "wardrobe-item-form";
    }

    @PostMapping("/save")
    public String saveWardrobeItem(
            @Valid @ModelAttribute("wardrobeItem") WardrobeItemEntity wardrobeItemEntity,
            BindingResult bindingResult
    ){
        if(bindingResult.hasErrors()){
            return "wardrobe-item-form";
        }
        wardrobeItemService.save(wardrobeItemEntity);
        return "redirect:/wardrobe-items";
    }
}
