package apap.tugasindividu.BobaXIXIXI.repository;

import apap.tugasindividu.BobaXIXIXI.model.BobaTeaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface BobaTeaDb extends JpaRepository<BobaTeaModel, Long>{
    @Override
    void delete(BobaTeaModel bobatea);
    Optional<BobaTeaModel> findAllByIdBobaTea(Long idBobaTea);
    BobaTeaModel findByBobaTeaName(String bobaTeaName);
}
