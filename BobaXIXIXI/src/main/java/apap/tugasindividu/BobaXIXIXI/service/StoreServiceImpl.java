package apap.tugasindividu.BobaXIXIXI.service;

import apap.tugasindividu.BobaXIXIXI.model.BobaTeaModel;
import apap.tugasindividu.BobaXIXIXI.model.StoreModel;
import apap.tugasindividu.BobaXIXIXI.repository.StoreDb;
import org.apache.catalina.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Service
@Transactional
public class StoreServiceImpl implements StoreService {
    @Autowired
    StoreDb storeDb;

    @Override
    public void addStore(StoreModel store) {
        store.setStorePhone(getStoreCode(store));
        storeDb.save(store);
    }

    @Override
    public List<StoreModel> getStoreList() {
        return storeDb.findAll();
    }

    @Override
    public String getStoreCode(StoreModel store) {
        String sc;
        sc = "SC";
        String storename = store.getStoreName().substring(0, 3);
        StringBuilder reversestorename = new StringBuilder(storename);
        reversestorename.reverse();
        String namatoko;
        namatoko = reversestorename.toString().toUpperCase();
        LocalTime openhour = store.getOpenHour();
        LocalTime closehour = store.getCloseHour();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH.mm");
        String openstring = openhour.format(formatter).substring(0, 2);    //12.38
        String closestring = closehour.format(formatter).substring(0, 2);
        String hhmm = openstring + closestring;
        String hhm = hhmm.substring(0,3);
        int waktu = Integer.valueOf(hhm);
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder sb = new StringBuilder(2);
        for (int i = 0; i < 2; i++) {
            int index
                    = (int) (AlphaNumericString.length()
                    * Math.random());
            sb.append(AlphaNumericString
                    .charAt(index));
        }
        String random = sb.toString();
        String storecode;
        storecode = sc + namatoko + waktu + random;
        return storecode;
    }

    @Override
    public StoreModel getStoreByIdStore(Long idStore){
        Optional<StoreModel> store = storeDb.findByIdStore(idStore);
        if(store.isPresent())
            return store.get();
        else return null;
    }

    @Override
    public StoreModel updateStore(StoreModel store){
        StoreModel targetStore = storeDb.findByIdStore(store.getIdStore()).get();
        try {
            targetStore.setStoreName(store.getStoreName());
            targetStore.setManager(store.getManager());
            targetStore.setStoreAddress(store.getStoreAddress());
            targetStore.setOpenHour(store.getOpenHour());
            targetStore.setCloseHour(store.getCloseHour());
            targetStore.setStorePhone(targetStore.getStorePhone());
            storeDb.save(targetStore);
            return targetStore;
        } catch (NullPointerException nullException) {
            return null;
        }
    }

    @Override
    public void removeStore(StoreModel store){
        storeDb.delete(store);
    }

}