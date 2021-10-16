package apap.tugasindividu.BobaXIXIXI.service;

import apap.tugasindividu.BobaXIXIXI.model.BobaTeaModel;
import apap.tugasindividu.BobaXIXIXI.repository.BobaTeaDb;
import apap.tugasindividu.BobaXIXIXI.repository.ToppingDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import java.util.NoSuchElementException;

@Service
@Transactional
public class BobaTeaServiceImpl implements BobaTeaService{
    @Autowired
    BobaTeaDb bobaTeaDb;

    @Autowired
    ToppingDb toppingDb;

    @Override
    public void addBobaTea(BobaTeaModel bobatea){
        bobaTeaDb.save(bobatea);
    }

    @Override
    public List<BobaTeaModel> getListBobaTea(){
        return bobaTeaDb.findAll();
    }

    @Override
    public void removeBobaTea(BobaTeaModel bobatea){
        bobaTeaDb.delete(bobatea);
    }

    @Override
    public BobaTeaModel getBobaTeaByIdBobaTea(Long idBobaTea){
        Optional<BobaTeaModel> bobatea = bobaTeaDb.findAllByIdBobaTea(idBobaTea);
        if(bobatea.isPresent()) return bobatea.get();
        else return null;
    }

    @Override
    public BobaTeaModel updateBobaTea(BobaTeaModel bobaTea){
        bobaTeaDb.save(bobaTea);
        return bobaTea;
    }

    @Override
    public Long getIdBobaTeaByName(String bobaTeaName){
        return bobaTeaDb.findByBobaTeaName(bobaTeaName).getIdBobaTea();
    }
}
