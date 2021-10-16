package apap.tugasindividu.BobaXIXIXI.controller;

import apap.tugasindividu.BobaXIXIXI.model.BobaTeaModel;
import apap.tugasindividu.BobaXIXIXI.model.ManagerModel;
import apap.tugasindividu.BobaXIXIXI.model.ToppingModel;
import apap.tugasindividu.BobaXIXIXI.service.StoreService;
import apap.tugasindividu.BobaXIXIXI.service.ManagerService;
import org.apache.catalina.Manager;
import org.apache.catalina.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import apap.tugasindividu.BobaXIXIXI.model.StoreModel;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledThreadPoolExecutor;

@Controller
public class StoreController {

    @Qualifier("storeServiceImpl")
    @Autowired
    private StoreService storeService;

    @Qualifier("managerServiceImpl")
    @Autowired
    private ManagerService managerService;

    @GetMapping("/store")
    public String listStore(Model model){
        List<StoreModel> listStore = storeService.getStoreList();
        model.addAttribute("listStore", listStore);
        return "store";
    }

    @GetMapping("/store/add")
    public String addStoreFormPage(Model model) {
        StoreModel store = new StoreModel();
        ArrayList<ManagerModel> existingManager = (ArrayList<ManagerModel>) managerService.getListManager();
        model.addAttribute("store", store);
        model.addAttribute("manager", new ManagerModel());
        model.addAttribute("existingManager", existingManager);
        return "form-add-store";
    }

    @PostMapping("/store/add")
    public String addStoreSubmitPage(
            @ModelAttribute StoreModel store,
            Model model){
        store.setStorePhone(storeService.getStoreCode(store));
        storeService.addStore(store);
        model.addAttribute("store", store);
        model.addAttribute("storePhone", storeService.getStoreCode(store));
        return "add-store";
    }

    @GetMapping("/store/view/{idStore}")
    public String viewDetailStorePage(
            @PathVariable Long idStore,
            Model model
    ){
        StoreModel store = storeService.getStoreByIdStore(idStore);
        List<BobaTeaModel> listBobaTea = store.getListBobaTea();

        model.addAttribute("store", store);
        model.addAttribute("listBobaTea", listBobaTea);
        //model.addAttribute("manager", store.getManager());

        return "view-store";
    }

    @GetMapping(value="/store/update/{idStore}")
    public String updateStoreFormPage(
            @PathVariable Long idStore,
            Model model
    ) {
        ArrayList<ManagerModel> existingManager = (ArrayList<ManagerModel>) managerService.getListManager();
        StoreModel store = storeService.getStoreByIdStore(idStore);
        model.addAttribute("existingManager", existingManager);
        model.addAttribute("store", store);
        return "form-update-store";
    }

    @PostMapping("/store/update")
    public String updateStoreSubmitPage(
            @ModelAttribute StoreModel store,
            Model model
    ){
        StoreModel storeUpdated = storeService.updateStore(store);
        LocalTime now = LocalTime.now();
        LocalTime buka = store.getOpenHour();
        LocalTime tutup = store.getCloseHour();
        if(now.isAfter(buka) && now.isBefore(tutup)){
            model.addAttribute("store", storeUpdated);
            return "update-store-error";
        }
        else{
            model.addAttribute("store", storeUpdated);
            return "update-store";
        }
    }

    @GetMapping(value="/store/delete/{idStore}")
    public String removeStore(
            @PathVariable Long idStore,
            Model model
    ) {
        StoreModel store = storeService.getStoreByIdStore(idStore);
        LocalTime now = LocalTime.now();
        LocalTime buka = store.getOpenHour();
        LocalTime tutup = store.getCloseHour();
        if(store == null){
            return "error-page";
        }
        else if(now.isAfter(buka) && now.isBefore(tutup)){
            model.addAttribute("store", store);
            return "delete-store-error";
        }
        else{
            storeService.removeStore(store);
            model.addAttribute("store", store);
            return "delete-store";
        }
    }
}
