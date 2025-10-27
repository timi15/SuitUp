package hu.unideb.inf.suitup.controller;

import hu.unideb.inf.suitup.entity.WardrobeItemEntity;
import hu.unideb.inf.suitup.service.WardrobeItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/edit/{id}")
    public String editWardrobeItem(@PathVariable Long id, Model model) {
        WardrobeItemEntity wardrobeItem = wardrobeItemService.findById(id);
        model.addAttribute("wardrobeItem", wardrobeItem);
        return "wardrobe-item-form";
    }

    @PostMapping("/update/{id}")
    public String updateWardrobeItem(
            @PathVariable Long id,
            @Valid @ModelAttribute("wardrobeItem") WardrobeItemEntity wardrobeItemEntity,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return "wardrobe-item-form";
        }
        wardrobeItemService.update(id, wardrobeItemEntity);
        return "redirect:/wardrobe-items";
    }

    @PostMapping("/delete/{id}")
    public String deleteWardrobeItem(@PathVariable Long id) {
        wardrobeItemService.deleteById(id);
        return "redirect:/wardrobe-items";
    }

}
