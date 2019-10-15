package is.hi.hbv501.bokamarkadur.bokamarkadur.Repositories;

import is.hi.hbv501.bokamarkadur.bokamarkadur.Entities.RentalLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RentalLogRepository extends JpaRepository<RentalLog, Long> {

    RentalLog save(RentalLog rentalLog);
    void delete(RentalLog rentalLog);
    List<RentalLog> findAll();
}
