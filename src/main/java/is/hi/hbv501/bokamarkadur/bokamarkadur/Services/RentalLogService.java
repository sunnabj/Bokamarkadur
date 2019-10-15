package is.hi.hbv501.bokamarkadur.bokamarkadur.Services;

import is.hi.hbv501.bokamarkadur.bokamarkadur.Entities.RentalLog;

import java.util.List;

public interface RentalLogService {

    RentalLog save(RentalLog rentalLog);
    void delete(RentalLog rentalLog);
    List<RentalLog> findAll();
}
