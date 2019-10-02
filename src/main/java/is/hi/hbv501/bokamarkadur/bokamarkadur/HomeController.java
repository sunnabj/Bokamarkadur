package is.hi.hbv501.bokamarkadur.bokamarkadur;

import is.hi.hbv501.bokamarkadur.bokamarkadur.Entities.Movie;
import is.hi.hbv501.bokamarkadur.bokamarkadur.Services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import javax.xml.ws.RequestWrapper;

@Controller
public class HomeController {

    private MovieService movieService;
    @Autowired
    public HomeController(MovieService movieService){this.movieService = movieService;}


    //Skilar home síðunni.
    //Model geymir gögnin okkar sem eiga að birtast.
    @RequestMapping("/")
    public String Home(Model model) {
        model.addAttribute("movies", movieService.findAll()); //Binda listann af myndum við movies taggið í módelinu
        return "Velkomin";
    }

    @RequestMapping(value ="/addmovie", method = RequestMethod.POST)
    public String addMovie(@Valid Movie movie, BindingResult result, Model model) {
        if(result.hasErrors()) {
            //Gætum haft villuskilaboð hér - ens og model.addAttribute("error") - eitthvað svona.
            return "add-movie"; //Inni í gæsalöppum: HTML skrá.
        }
        movieService.save(movie);
        model.addAttribute("movies", movieService.findAll());
        return "Velkomin";
    }

    @RequestMapping(value="/addmovie", method = RequestMethod.GET)
    public String addMovieForm(Movie movie) {
        return "add-movie";
    }
    // id inni í {} - þýðir að þetta er variable.
    @RequestMapping(value="/delete/{id}", method = RequestMethod.GET)
    public String deleteMovie(@PathVariable("id") long id, Model model) {
        //Reynir að sækja movie með þetta id í gagnagrunninn - ef ekki til - kastar villu
        Movie movie = movieService.findById(id).orElseThrow(()-> new IllegalArgumentException("Invalid movie ID"));
        //Sækja mynd, eyða - sækja listann eftir það.
        movieService.delete(movie);
        model.addAttribute("movies", movieService.findAll());
        return "Velkomin";
    }

}
