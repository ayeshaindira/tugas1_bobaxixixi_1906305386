package apap.tugasindividu.BobaXIXIXI.service;

import apap.tugasindividu.BobaXIXIXI.model.ManagerModel;
import apap.tugasindividu.BobaXIXIXI.model.StoreModel;
import apap.tugasindividu.BobaXIXIXI.model.BobaTeaModel;
import apap.tugasindividu.BobaXIXIXI.model.StoreBobaTeaModel;
import apap.tugasindividu.BobaXIXIXI.repository.BobaTeaDb;
import apap.tugasindividu.BobaXIXIXI.repository.StoreBobaTeaDb;
import apap.tugasindividu.BobaXIXIXI.repository.StoreDb;
import org.apache.catalina.Store;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Service
@Transactional
public class StoreBobaTeaServiceImpl implements StoreBobaTeaService{

    @Autowired
    StoreBobaTeaDb storeBobaTeaDb;

    @Override
    public StoreBobaTeaModel getStoreByProductionCode(String productionCode) {
        Optional<StoreBobaTeaModel> storeBobaTea = storeBobaTeaDb.findByProductionCode(productionCode);
        if(storeBobaTea.isPresent())
            return storeBobaTea.get();
        else return null;
    }

    @Override
    public List<StoreBobaTeaModel> findByBobaTeaAndTopping(String bobaTeaName, String toppingName) {
        List<StoreBobaTeaModel> listStoreBoba = storeBobaTeaDb.findAll();
        List<StoreBobaTeaModel> listResult = new ArrayList<>();
        LocalTime now = LocalTime.now();
        for (int i = 0; i < listStoreBoba.size(); i++){
            String bobatea = listStoreBoba.get(i).getBobaTea().getBobaTeaName();
            LocalTime buka = listStoreBoba.get(i).getStore().getOpenHour();
            LocalTime tutup = listStoreBoba.get(i).getStore().getCloseHour();
            String topping = listStoreBoba.get(i).getBobaTea().getTopping().getToppingName();
            if(bobaTeaName.equals(bobatea) && toppingName.equals(topping)){
                if(now.isAfter(buka) && now.isBefore(tutup)){
                    listResult.add(listStoreBoba.get(i));
                }
            }
        }
        return listResult;
    }

    @Override
    public List<StoreBobaTeaModel> findManagerByBobaTea(String bobaTeaName) {
        List<StoreBobaTeaModel> listStoreBoba = storeBobaTeaDb.findAll();
        List<StoreBobaTeaModel> listResult = new ArrayList<>();
        for (int i = 0; i < listStoreBoba.size(); i++){
            String bobatea = listStoreBoba.get(i).getBobaTea().getBobaTeaName();
            if(bobaTeaName.equals(bobatea)){
                listResult.add(listStoreBoba.get(i));
            }
        }
        return listResult;
    }

}
