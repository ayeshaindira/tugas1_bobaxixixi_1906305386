package apap.tugasindividu.BobaXIXIXI.service;

import apap.tugasindividu.BobaXIXIXI.model.BobaTeaModel;
import apap.tugasindividu.BobaXIXIXI.model.StoreModel;

import java.util.List;

public interface BobaTeaService {
    void addBobaTea(BobaTeaModel bobaTea);
    List<BobaTeaModel> getListBobaTea();
    void removeBobaTea(BobaTeaModel bobaTea);
    BobaTeaModel getBobaTeaByIdBobaTea(Long idBobaTea);
    BobaTeaModel updateBobaTea(BobaTeaModel bobaTeaModel);
    Long getIdBobaTeaByName(String bobaTeaName);
}
