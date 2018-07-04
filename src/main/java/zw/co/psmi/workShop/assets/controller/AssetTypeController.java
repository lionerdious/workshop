/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.psmi.workShop.assets.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import zw.co.psmi.workShop.assets.entity.AssetType;
import zw.co.psmi.workShop.assets.service.AssetTypeService;
import zw.co.psmi.workShop.auth.entity.Login;

/**
 *
 * @author lionel
 */
@Controller
public class AssetTypeController {
     @Autowired
    private AssetTypeService assetTypeService;
     
     @RequestMapping(value = "/assets/assettypes", method = RequestMethod.GET)
    public String locationName(@AuthenticationPrincipal Login userLogin, Model model) {
        model.addAttribute("assetTypes", this.assetTypeService.findAll());
        return "common/locationname";
    }

    @RequestMapping(value = "/assets/assettypesaction/{Id}", method = RequestMethod.GET)
    public String locationNameAction(@AuthenticationPrincipal Login userLogin, @PathVariable("Id") Long Id, Model model) {
        AssetType assetType = this.assetTypeService.getByID(Id);
        model.addAttribute("assetType", assetType);
        return "assets/assettypesaction";
    }

    @RequestMapping(value = "/assets/assettypesform", method = RequestMethod.POST)
    public String locationNameform(@AuthenticationPrincipal Login userLogin, @ModelAttribute AssetType assetType, Model model, RedirectAttributes redirectAttributes) {
        String msg = this.assetTypeService.save(assetType);
        redirectAttributes.addFlashAttribute("msg", "setMsg('" + msg + "')");
        return "redirect:/assets/assettypes";
    }
}
