package hu.unideb.inf.suitup.controller;

import hu.unideb.inf.suitup.dto.WardrobeItemFilter;
import hu.unideb.inf.suitup.entity.OutfitEntity;
import hu.unideb.inf.suitup.entity.WardrobeItemEntity;
import hu.unideb.inf.suitup.service.UserService;
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

    private final WardrobeItemService wardrobeItemService;
    private final UserService userService;

    @GetMapping("")
    public String wardrobeItemsPage(Model model) {
        Long userId = userService.getCurrentUserId();
        List<WardrobeItemEntity> items = wardrobeItemService.findAll(userId);
        items.forEach(WardrobeItemEntity::prepareTopicList);

        model.addAttribute("wardrobeItems", wardrobeItemService.findAll(userId));
        return "wardrobe-items";
    }

    @GetMapping("/save")
    public String showUploadWardrobeItemForm(Model model) {
        model.addAttribute("wardrobeItem", new WardrobeItemEntity());
        return "wardrobe-item-form";
    }

    @PostMapping("/save")
    public String saveWardrobeItem(
            @Valid @ModelAttribute("wardrobeItem") WardrobeItemEntity wardrobeItemEntity,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return "wardrobe-item-form";
        }
        Long userId = userService.getCurrentUserId();
        wardrobeItemService.save(userId, wardrobeItemEntity);
        return "redirect:/wardrobe-items";
    }

    @GetMapping("/edit/{id}")
    public String editWardrobeItem(@PathVariable Long id, Model model) {
        Long userId = userService.getCurrentUserId();
        WardrobeItemEntity wardrobeItem = wardrobeItemService.findById(userId, id);
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
        Long userId = userService.getCurrentUserId();
        wardrobeItemService.update(userId, id, wardrobeItemEntity);
        return "redirect:/wardrobe-items";
    }

    @PostMapping("/delete/{id}")
    public String deleteWardrobeItem(@PathVariable Long id) {
        Long userId = userService.getCurrentUserId();
        System.out.println(">>> Controller reached, id=" + id + ", userId=" + userId);
        wardrobeItemService.deleteById(userId, id);
        return "redirect:/wardrobe-items";
    }

    @GetMapping("/filter")
    public String filterWardrobeItems(
            @ModelAttribute("filter") WardrobeItemFilter filter,
            Model model
    ) {
        Long userId = userService.getCurrentUserId();
        model.addAttribute("wardrobeItems", wardrobeItemService.filter(userId, filter));
        return "wardrobe-items";
    }
}
