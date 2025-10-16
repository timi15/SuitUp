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

@Controller
@RequiredArgsConstructor
public class WardrobeItemController {

    final WardrobeItemService wardrobeItemService;

    @GetMapping("/wardrobe-item/save-form")
    public String showUploadWardrobeItemForm(Model model){
        model.addAttribute("wardrobeItem", new WardrobeItemEntity());
        //TODO: HTML page
        return "wardrobe-item-form.html";
    }

    @PostMapping("/wardrobe-item/save")
    public String saveWardrobeItem(
            @Valid @ModelAttribute("wardrobeItem") WardrobeItemEntity wardrobeItemEntity,
            BindingResult bindingResult
    ){
        if(bindingResult.hasErrors()){
            //TODO: HTML page
            return "wardrobe-item-form.html";
        }
        wardrobeItemService.save(wardrobeItemEntity);
        //TODO: HTML page
        return "redirect:/";
    }
}
