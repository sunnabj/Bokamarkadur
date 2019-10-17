package is.hi.hbv501.bokamarkadur.bokamarkadur;

import is.hi.hbv501.bokamarkadur.bokamarkadur.Services.RentalLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RentalLogController {

    private RentalLogService rentalLogService;


    @Autowired
    public RentalLogController(RentalLogService rentalLogService){
        this.rentalLogService = rentalLogService;
    }

    @RequestMapping("/rentals")
    public String allRentals(Model model) {
        model.addAttribute("rentalLog", rentalLogService.findAll());
        return "rentals";
    }

}
