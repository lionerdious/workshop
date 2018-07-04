/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.psmi.workShop.common.controller;

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
import zw.co.psmi.workShop.auth.entity.Login;
import zw.co.psmi.workShop.common.entity.LocationName;
import zw.co.psmi.workShop.common.service.LocationNameService;

/**
 *
 * @author lionel
 */
@Controller
public class LocationNameController {

    @Autowired
    private LocationNameService locationNameService;
    //LocationName

    @RequestMapping(value = "/common/locationname", method = RequestMethod.GET)
    public String locationName(@AuthenticationPrincipal Login userLogin, Model model) {
        model.addAttribute("locationNames", this.locationNameService.findAll());
        return "common/locationname";
    }

    @RequestMapping(value = "/common/locationnameaction/{Id}", method = RequestMethod.GET)
    public String locationNameAction(@AuthenticationPrincipal Login userLogin, @PathVariable("Id") Long Id, Model model) {
        LocationName locationName = this.locationNameService.getByID(Id);
        model.addAttribute("locationName", locationName);
        return "common/locationnameaction";
    }

    @RequestMapping(value = "/common/locationnameform", method = RequestMethod.POST)
    public String locationNameform(@AuthenticationPrincipal Login userLogin, @ModelAttribute LocationName locationName, Model model, RedirectAttributes redirectAttributes) {
        String msg = this.locationNameService.save(locationName);
        redirectAttributes.addFlashAttribute("msg", "setMsg('" + msg + "')");
        return "redirect:/common/locationname";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setAutoGrowCollectionLimit(500000);
    }

}
