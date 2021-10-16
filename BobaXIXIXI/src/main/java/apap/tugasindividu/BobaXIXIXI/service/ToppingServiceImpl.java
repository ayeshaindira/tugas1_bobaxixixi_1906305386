package apap.tugasindividu.BobaXIXIXI.service;

import apap.tugasindividu.BobaXIXIXI.model.ToppingModel;
import apap.tugasindividu.BobaXIXIXI.repository.ToppingDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional
public class ToppingServiceImpl implements ToppingService{
    @Autowired
    ToppingDb toppingDb;

    @Override
    public List<ToppingModel> getListTopping() {
        return toppingDb.findAll();
    }


    @Override
    public Long getIdToppingByName(String toppingName){
        return toppingDb.findByToppingName(toppingName).getIdTopping();
    }
}
