package apap.tugasindividu.BobaXIXIXI.service;

import apap.tugasindividu.BobaXIXIXI.model.ManagerModel;
import apap.tugasindividu.BobaXIXIXI.repository.ManagerDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ManagerServiceImpl implements ManagerService{
    @Autowired
    ManagerDb managerDb;

    @Override
    public List<ManagerModel> getListManager() {
        return managerDb.findAll();
    }
}
