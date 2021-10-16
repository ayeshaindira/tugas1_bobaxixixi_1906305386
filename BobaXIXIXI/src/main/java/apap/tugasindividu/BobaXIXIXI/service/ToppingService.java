package apap.tugasindividu.BobaXIXIXI.service;

import apap.tugasindividu.BobaXIXIXI.model.ToppingModel;

import java.util.List;

public interface ToppingService {
    List<ToppingModel> getListTopping();
    Long getIdToppingByName(String toppingName);
}
