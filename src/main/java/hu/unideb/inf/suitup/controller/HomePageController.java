package hu.unideb.inf.suitup.controller;

import hu.unideb.inf.suitup.service.UserService;
import hu.unideb.inf.suitup.service.WardrobeItemService;
import hu.unideb.inf.suitup.util.SeasonUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
@RequiredArgsConstructor
public class HomePageController {

    private final WardrobeItemService wardrobeItemService;
    private final UserService userService;

    @GetMapping("")
    public String home(Model model) {
        Long userId = userService.getCurrentUserId();
        model.addAttribute("favourites", wardrobeItemService.findFavouriteWardrobeItems(userId));

        String currentSeason = SeasonUtils.getCurrentSeason();
        model.addAttribute("currentSeason", currentSeason);
        model.addAttribute("seasonalItems", wardrobeItemService.findBySeason(userId, currentSeason));

        return "landing-page";
    }

}
