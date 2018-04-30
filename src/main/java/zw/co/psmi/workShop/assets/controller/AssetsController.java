/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.psmi.workShop.assets.controller;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import zw.co.psmi.workShop.assets.entity.Assets;
import zw.co.psmi.workShop.assets.service.AssetsService;
import zw.co.psmi.workShop.auth.entity.Login;

/**
 *
 * @author lionel
 */
@Controller
@Slf4j
public class AssetsController {

    @Autowired
    private AssetsService assetsService;

    @Autowired
    public AssetsController(AssetsService assetsService) {
        this.assetsService = assetsService;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setAutoGrowCollectionLimit(500000);
    }

    @RequestMapping(value = "/assets/assetmanagement", method = RequestMethod.GET)
    public String assetmanagement(@AuthenticationPrincipal Login userLogin, Model model, @ModelAttribute Assets assets) {
        assets = assets == null ? new Assets() : assets;
        model.addAttribute("assetss", assetsService.findByAllBYSearchCriteria(assets));
        model.addAttribute("regions", assets);
        model.addAttribute("assets", assets);
        return "stocks/bookstock";
    }

    @RequestMapping(value = "/assets/assetmanagementaction/{Id}", method = RequestMethod.GET)
    public String assetmanagementaction(@AuthenticationPrincipal Login userLogin, @PathVariable("Id") Long Id, Model model) {
        Assets assets = assetsService.getByID(Id);
        assets = assets == null ? new Assets() : assets;
        model.addAttribute("assets", assets);
        return "stocks/bookstockaction";
    }

    @RequestMapping(value = "/assets/assetmanagementform", method = RequestMethod.POST)
    public String assetmanagementform(@AuthenticationPrincipal Login userLogin, @ModelAttribute Assets assets, Model model, RedirectAttributes redirectAttributes) {
        String msg = assetsService.save(assets);
        redirectAttributes.addFlashAttribute("msg", "setMsg('" + msg + "')");
        return "redirect:/stocks/bookstock";
    }

}
