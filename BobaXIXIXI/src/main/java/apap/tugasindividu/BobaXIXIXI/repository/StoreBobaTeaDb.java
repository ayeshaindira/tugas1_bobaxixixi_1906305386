package apap.tugasindividu.BobaXIXIXI.repository;

import apap.tugasindividu.BobaXIXIXI.model.StoreBobaTeaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface StoreBobaTeaDb extends JpaRepository<StoreBobaTeaModel, Long> {
    Optional<StoreBobaTeaModel> findByProductionCode(String productionCode);
}
