package apap.tugasindividu.BobaXIXIXI.controller;

import apap.tugasindividu.BobaXIXIXI.model.BobaTeaModel;
import apap.tugasindividu.BobaXIXIXI.model.StoreBobaTeaModel;
import apap.tugasindividu.BobaXIXIXI.model.StoreModel;
import apap.tugasindividu.BobaXIXIXI.model.ToppingModel;
import apap.tugasindividu.BobaXIXIXI.service.BobaTeaService;
import apap.tugasindividu.BobaXIXIXI.service.StoreService;
import apap.tugasindividu.BobaXIXIXI.service.StoreBobaTeaService;
import apap.tugasindividu.BobaXIXIXI.service.ToppingService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

@Controller
public class BobaTeaController {

    @Qualifier("bobaTeaServiceImpl")
    @Autowired
    private BobaTeaService bobaTeaService;

    @Qualifier("toppingServiceImpl")
    @Autowired
    private ToppingService toppingService;

    @Qualifier("storeBobaTeaServiceImpl")
    @Autowired
    private StoreBobaTeaService storeBobaTeaService;

    @Qualifier("storeServiceImpl")
    @Autowired
    private StoreService storeService;

    @GetMapping("/boba")
    public String listBobaTea(Model model){
        List<BobaTeaModel> listBobaTea = bobaTeaService.getListBobaTea();
        model.addAttribute("listBobaTea", listBobaTea);
        //model.addAttribute("toppingPrice", listBobaTea);
        return "boba";
    }

    @GetMapping("/boba/add")
    public String addBobaTeaFormPage(Model model) {
        BobaTeaModel bobatea = new BobaTeaModel();
        ArrayList<ToppingModel> existingTopping =(ArrayList<ToppingModel>) toppingService.getListTopping();
        model.addAttribute("bobatea", bobatea);
        model.addAttribute("topping", new ToppingModel());
        model.addAttribute("existingTopping", existingTopping);
        return "form-add-bobatea";
    }

    @PostMapping(value="/boba/add")
    public String addBobaTeaSubmitPage(
            @ModelAttribute BobaTeaModel bobatea,
            Model model
    ){
        bobaTeaService.addBobaTea(bobatea);
        model.addAttribute("bobatea", bobatea.getBobaTeaName());
        return "add-bobatea";
    }

    @GetMapping("/boba/remove/{idBobaTea}")
    public String removeBobaTeaByIdBobaTea(
            @PathVariable Long idBobaTea,
            Model model
    ){
        BobaTeaModel bobatea = bobaTeaService.getBobaTeaByIdBobaTea(idBobaTea);
        if(bobatea == null){
            return "error-page";
        }
        else{
            bobaTeaService.removeBobaTea(bobatea);
            model.addAttribute("bobatea", bobatea.getBobaTeaName());
            return "delete-bobatea";
        }
    }

    @GetMapping("/boba/update/{idBobaTea}")
    public String updateBobaTeaFormPage(
            @PathVariable Long idBobaTea,
            Model model
    ){
        ArrayList<ToppingModel> existingTopping =(ArrayList<ToppingModel>) toppingService.getListTopping();
        BobaTeaModel bobatea = bobaTeaService.getBobaTeaByIdBobaTea(idBobaTea);
        model.addAttribute("existingTopping", existingTopping);
        model.addAttribute("bobatea", bobatea);
        return "form-update-bobatea";
    }

    @PostMapping("/boba/update")
    public String updateBobaTeaSubmitPage(
            @ModelAttribute BobaTeaModel bobatea,
            Model model
    ){
        bobaTeaService.updateBobaTea(bobatea);
        model.addAttribute("bobatea", bobatea.getBobaTeaName());
        return "update-bobatea";
    }

    @GetMapping("/boba/{idBobaTea}/assign-store")
    public String assignBobaFormPage(
            @PathVariable Long idBobaTea,
            Model model
    ){
        List<StoreModel> listStore = storeService.getStoreList();
        BobaTeaModel bobaTea = bobaTeaService.getBobaTeaByIdBobaTea(idBobaTea);
        model.addAttribute("bobaTea", bobaTea);
        model.addAttribute("bobaTeaName", bobaTea.getBobaTeaName());
        model.addAttribute("listStore", listStore);
        return "form-assign-bobatea";
    }

    @GetMapping("/search")
    public String searchBase(Model model) {
        List<BobaTeaModel> listBobaTea = bobaTeaService.getListBobaTea();
        List<ToppingModel> listTopping = toppingService.getListTopping();
        model.addAttribute("listBobaTea", listBobaTea);
        model.addAttribute("listTopping", listTopping);
        return "search";
    }

    @GetMapping(value="/search", params={"bobaTeaName", "toppingName"})
    public String search(
            @RequestParam(required = false, value = "bobaTeaName") String bobaTeaName,
            @RequestParam(required = false,value = "toppingName") String toppingName,
            Model model
    ){
        List<BobaTeaModel> listBobaTea = bobaTeaService.getListBobaTea();
        List<ToppingModel> listTopping = toppingService.getListTopping();
        List<StoreBobaTeaModel> bobaTea = storeBobaTeaService.findByBobaTeaAndTopping(bobaTeaName, toppingName);
        model.addAttribute("listBobaTea", listBobaTea);
        model.addAttribute("listTopping", listTopping);
        model.addAttribute("bobaTea", bobaTea);
        return "search";
    }

    @GetMapping("/filter/manager")
    public String filterManagerBase(
            Model model
    ){
        List<BobaTeaModel> listBobaTea = bobaTeaService.getListBobaTea();
        model.addAttribute("listBobaTea", listBobaTea);
        return "filter";
    }


    @GetMapping(value="/filter/manager", params={"bobaTeaName"})
    public String filterManager(
            @RequestParam(required = false, value = "bobaTeaName") String bobaTeaName,
            Model model
    ){
        List<BobaTeaModel> listBobaTea = bobaTeaService.getListBobaTea();
        List<StoreBobaTeaModel> managers = storeBobaTeaService.findManagerByBobaTea(bobaTeaName);
        model.addAttribute("listBobaTea", listBobaTea);
        model.addAttribute("managers", managers);
        return "filter";
    }

}
