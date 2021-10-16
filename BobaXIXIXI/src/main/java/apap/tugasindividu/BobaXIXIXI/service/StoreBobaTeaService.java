package apap.tugasindividu.BobaXIXIXI.service;

import apap.tugasindividu.BobaXIXIXI.model.StoreBobaTeaModel;
import apap.tugasindividu.BobaXIXIXI.model.StoreModel;

import java.util.List;

public interface StoreBobaTeaService {
    StoreBobaTeaModel getStoreByProductionCode(String productionCode);
    List<StoreBobaTeaModel> findByBobaTeaAndTopping(String bobaTeaName, String toppingName);
    List<StoreBobaTeaModel> findManagerByBobaTea(String bobaTeaName);
}
