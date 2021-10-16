package apap.tugasindividu.BobaXIXIXI.service;

import apap.tugasindividu.BobaXIXIXI.model.BobaTeaModel;
import apap.tugasindividu.BobaXIXIXI.model.StoreModel;

import java.util.List;

public interface StoreService {
    void addStore(StoreModel store);
    String getStoreCode(StoreModel store);
    List <StoreModel> getStoreList();
    StoreModel getStoreByIdStore(Long idStore);
    StoreModel updateStore(StoreModel store);
    void removeStore(StoreModel store);

}
