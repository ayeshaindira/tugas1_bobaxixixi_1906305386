package apap.tugasindividu.BobaXIXIXI.repository;

import apap.tugasindividu.BobaXIXIXI.model.BobaTeaModel;
import apap.tugasindividu.BobaXIXIXI.model.ToppingModel;
import apap.tugasindividu.BobaXIXIXI.service.ToppingService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface ToppingDb extends JpaRepository<ToppingModel, Long> {
    Optional<ToppingModel> findAllByIdTopping(Long idTopping);
    ToppingModel findByToppingName(String toppingName);

}
