package apap.tugasindividu.BobaXIXIXI.repository;

import apap.tugasindividu.BobaXIXIXI.model.StoreModel;
import org.apache.catalina.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface StoreDb extends JpaRepository<StoreModel, Long>{
    Optional<StoreModel> findByIdStore(Long idStore);
    StoreModel findByStorePhone(String storePhone);
}
