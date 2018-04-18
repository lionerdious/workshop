/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.psmi.workShop.common.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import zw.co.psmi.workShop.Pager;
import static zw.co.psmi.workShop.Utils.prepairString;
import zw.co.psmi.workShop.auth.entity.Login;
import zw.co.psmi.workShop.common.entity.LOB;
import zw.co.psmi.workShop.common.entity.Region;
import zw.co.psmi.workShop.common.entity.Site;
import zw.co.psmi.workShop.common.service.SiteService;

/**
 *
 * @author lionel
 */
@Controller
public class SiteController {

    private static final int BUTTONS_TO_SHOW = 5;
    private static final int INITIAL_PAGE = 0;
    private static final int INITIAL_PAGE_SIZE = 25;
    private static final int[] PAGE_SIZES = {10, 25, 50, 100, 250};

    @Autowired
    private SiteService siteService;

    @RequestMapping(value = "/common/site", method = RequestMethod.GET)
    public String site(@AuthenticationPrincipal Login userLogin,
            @RequestParam(required = false) String name, Model model, @RequestParam("pageSize") Optional<Integer> pageSize,
            @RequestParam("page") Optional<Integer> page) {

        int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
        int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;

        Pageable pageable = new PageRequest(evalPage, evalPageSize);
        Page<Site> sitePages = siteService.findByNamePageable(pageable, prepairString(name));
        Pager pager = new Pager(sitePages.getTotalPages(), sitePages.getNumber(), BUTTONS_TO_SHOW);

        List<Site> siteList = sitePages.getContent();

        sitePages.isFirst();
        sitePages.isLast();
        sitePages.getTotalPages();

        model.addAttribute("sites", sitePages);
        model.addAttribute("selectedPageSize", evalPageSize);
        model.addAttribute("pageSizes", PAGE_SIZES);
        model.addAttribute("pager", pager);

        model.addAttribute("sitess", this.siteService.findAll());
        return "/common/site";
    }

    @RequestMapping(value = "/common/siteaction/{Id}", method = RequestMethod.GET)
    public String siteAction(@AuthenticationPrincipal Login userLogin, @PathVariable("Id") Long Id, Model model) {
        Site site = this.siteService.getByID(Id);
        model.addAttribute("site", site);
        model.addAttribute("region", Region.values());
        model.addAttribute("lob", LOB.values());
        return "/common/siteaction";
    }

    @RequestMapping(value = "/common/siteform", method = RequestMethod.POST)
    public String siteform(@AuthenticationPrincipal Login userLogin, @ModelAttribute Site site, Model model, RedirectAttributes redirectAttributes) {
        String msg = this.siteService.save(site);
        redirectAttributes.addFlashAttribute("msg", "setMsg('" + msg + "')");
        return "redirect:/common/site";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setAutoGrowCollectionLimit(500000);
    }
}
