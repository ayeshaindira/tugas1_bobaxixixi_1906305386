package apap.tugasindividu.BobaXIXIXI.repository;

import apap.tugasindividu.BobaXIXIXI.model.ManagerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ManagerDb extends JpaRepository<ManagerModel, Long>{
}
