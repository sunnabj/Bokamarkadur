package is.hi.hbv501.bokamarkadur.bokamarkadur;

import is.hi.hbv501.bokamarkadur.bokamarkadur.Entities.Book;
import is.hi.hbv501.bokamarkadur.bokamarkadur.Entities.Subjects;
import is.hi.hbv501.bokamarkadur.bokamarkadur.Repositories.BookRepository;
import is.hi.hbv501.bokamarkadur.bokamarkadur.Services.BookService;
import is.hi.hbv501.bokamarkadur.bokamarkadur.Services.Implementations.BookServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;

import java.util.Collections;

@SpringBootApplication
@EnableJpaRepositories

public class BokamarkadurApplication {

	public static void main(String[] args) {
		SpringApplication.run(BokamarkadurApplication.class, args);
	}
	@Component
	class DemoCommandLineRunner implements CommandLineRunner {

		@Autowired
		BookRepository bookRepository;
		BookService bookService;
		BookServiceImplementation bookServiceImplementation;

		@Override
		public void run(String... args) throws Exception {

			Book REI502M = new Book();
			REI502M.setTitle("Introduction to Data Mining");
			REI502M.setAuthor("Tan, Pang-Ning");
			REI502M.setEdition(2);
			REI502M.setCondition("Gott");
			REI502M.setSubjects(Collections.singleton(Subjects.COMPUTERSCIENCE));
			REI502M.setPrice(12995);
			REI502M.setStatus("Óseld");
			bookRepository.save(REI502M);

			Book REI502M_2 = new Book();
			REI502M_2.setTitle("Data Mining: Practical Machine Learning");
			REI502M_2.setAuthor("Witter, Ian H.");
			REI502M_2.setEdition(4);
			REI502M_2.setSubjects(Collections.singleton(Subjects.COMPUTERSCIENCE));
			REI502M_2.setPrice(9995);
			REI502M_2.setStatus("Óseld");
			bookRepository.save(REI502M_2);

			Book TÖL101G = new Book();
			TÖL101G.setTitle("Introduction To Programming In Java");
			TÖL101G.setAuthor("SEDGEWICK, ROBERT.");
			TÖL101G.setEdition(2);
			TÖL101G.setCondition("New");
			TÖL101G.setSubjects(Collections.singleton(Subjects.COMPUTERSCIENCE));
			TÖL101G.setPrice(14995);
			TÖL101G.setStatus("Óseld");
			bookRepository.save(TÖL101G);

			Book TÖL104G = new Book();  TÖL104G.setTitle("Discrete Mathematics and It’s Applications");  TÖL104G.setAuthor("Rosen, Kenneth");  TÖL104G.setEdition(8);  TÖL104G.setCondition("New");  TÖL104G.setSubjects(Collections.singleton(Subjects.COMPUTERSCIENCE));  TÖL104G.setPrice(11495);  TÖL104G.setStatus("Óseld");bookRepository.save(TÖL104G);

			Book TOL105G = new Book();  TOL105G.setTitle("Think Python: How to Think Like a Computer Scientist");  TOL105G.setAuthor("Downey, Allen B.");  TOL105G.setEdition(2);  TOL105G.setCondition("New");  TOL105G.setSubjects(Collections.singleton(Subjects.COMPUTERSCIENCE));  TOL105G.setPrice(5995);  TOL105G.setStatus("Óseld");bookRepository.save(TOL105G);
			Book TOL303G = new Book();  TOL303G.setTitle("FIRST COURSE IN DATABASE SYSTEMS");  TOL303G.setAuthor("ULLMAN, JEFFREY D.");  TOL303G.setEdition(3);  TOL303G.setCondition("New");  TOL303G.setSubjects(Collections.singleton(Subjects.COMPUTERSCIENCE));  TOL303G.setPrice(11995);  TOL303G.setStatus("Óseld");bookRepository.save(TOL303G);
			Book TOL304G = new Book();  TOL304G.setTitle("CONCEPTS OF PROGRAMMING LANGUAGES");  TOL304G.setAuthor("SEBESTA, ROBERT W.");  TOL304G.setEdition(11);  TOL304G.setCondition("New");  TOL304G.setSubjects(Collections.singleton(Subjects.COMPUTERSCIENCE));  TOL304G.setPrice(10395);  TOL304G.setStatus("Óseld");bookRepository.save(TOL304G);
			Book TOL305G = new Book();  TOL305G.setTitle("COMPUTER NETWORKING");  TOL305G.setAuthor("KUROSE, JAMES F.");  TOL305G.setEdition(7);  TOL305G.setCondition("New");  TOL305G.setSubjects(Collections.singleton(Subjects.COMPUTERSCIENCE));  TOL305G.setPrice(12995);  TOL305G.setStatus("Óseld");bookRepository.save(TOL305G);
			Book TOL101G = new Book();  TOL101G.setTitle("Computer Systems: A Programmer’s Perspective");  TOL101G.setAuthor("Bryant, Randal E.");  TOL101G.setEdition(3);  TOL101G.setCondition("New");  TOL101G.setSubjects(Collections.singleton(Subjects.COMPUTERSCIENCE));  TOL101G.setPrice(13795);  TOL101G.setStatus("Óseld");bookRepository.save(TOL101G);
			Book BYG201G = new Book();  BYG201G.setTitle("STRUCTURAL AND STRESS ANALYSIS");  BYG201G.setAuthor("MEGSON, T.H.G");  BYG201G.setEdition(3);  BYG201G.setCondition("New");  BYG201G.setSubjects(Collections.singleton(Subjects.CIVILENGINEERING));  BYG201G.setPrice(12195);  BYG201G.setStatus("Óseld");bookRepository.save(BYG201G);
			Book BYG501G = new Book();  BYG501G.setTitle("Principles of Geotechnical Engineering");  BYG501G.setAuthor("Das, Braja");  BYG501G.setEdition(9);  BYG501G.setCondition("New");  BYG501G.setSubjects(Collections.singleton(Subjects.CIVILENGINEERING));  BYG501G.setPrice(12995);  BYG501G.setStatus("Óseld");bookRepository.save(BYG501G);
			Book BYG503G = new Book();  BYG503G.setTitle("PRINCIPLES OF HIGHWAY ENGINEERING AND TRAFFIC ANALYSIS");  BYG503G.setAuthor("MANNERING, FRED L. & WASHBURN");  BYG503G.setEdition(5);  BYG503G.setCondition("New");  BYG503G.setSubjects(Collections.singleton(Subjects.CIVILENGINEERING));  BYG503G.setPrice(10995);  BYG503G.setStatus("Óseld");bookRepository.save(BYG503G);
			Book IDN101M = new Book();  IDN101M.setTitle("JURAN’S QUALITY MANAGEMENT AND ANALYSIS");  IDN101M.setAuthor("DE FEO, JOSEPH A.");  IDN101M.setEdition(6);  IDN101M.setCondition("New");  IDN101M.setSubjects(Collections.singleton(Subjects.Industrial_and_Mechanical_Engineering));  IDN101M.setPrice(13995);  IDN101M.setStatus("Óseld");bookRepository.save(IDN101M);
			Book IDN103G = new Book();  IDN103G.setTitle("Modern Industrial Management");  IDN103G.setAuthor("Engwall, Mats");  IDN103G.setEdition(1);  IDN103G.setCondition("New");  IDN103G.setSubjects(Collections.singleton(Subjects.Industrial_and_Mechanical_Engineering));  IDN103G.setPrice(11995);  IDN103G.setStatus("Óseld");bookRepository.save(IDN103G);
			Book IDN113F = new Book();  IDN113F.setTitle("TIME SERIES ANALYSIS: TEXTS IN STATISTIC");  IDN113F.setAuthor("MADSEN, HENRIK");  IDN113F.setEdition(1);  IDN113F.setCondition("New");  IDN113F.setSubjects(Collections.singleton(Subjects.Industrial_and_Mechanical_Engineering));  IDN113F.setPrice(16995);  IDN113F.setStatus("Óseld");bookRepository.save(IDN113F);
			Book IDN122F = new Book();  IDN122F.setTitle("Method for Engineering Students");  IDN122F.setAuthor("Blomkvist, Par");  IDN122F.setEdition(1);  IDN122F.setCondition("New");  IDN122F.setSubjects(Collections.singleton(Subjects.Industrial_and_Mechanical_Engineering));  IDN122F.setPrice(7695);  IDN122F.setStatus("Óseld");bookRepository.save(IDN122F);
			Book IDN210F = new Book();  IDN210F.setTitle("MANAGEMENT");  IDN210F.setAuthor("DRUCKER, PETER F.");  IDN210F.setEdition(0);  IDN210F.setCondition("New");  IDN210F.setSubjects(Collections.singleton(Subjects.Industrial_and_Mechanical_Engineering));  IDN210F.setPrice(3895);  IDN210F.setStatus("Óseld");bookRepository.save(IDN210F);
			Book IDN301G = new Book();  IDN301G.setTitle("Fundamentals of Business Process");  IDN301G.setAuthor("Dumas, Marlon");  IDN301G.setEdition(2);  IDN301G.setCondition("New");  IDN301G.setSubjects(Collections.singleton(Subjects.Industrial_and_Mechanical_Engineering));  IDN301G.setPrice(11995);  IDN301G.setStatus("Óseld");bookRepository.save(IDN301G);
			Book RAF201G = new Book();  RAF201G.setTitle("ELECTRIC CIRCUITS");  RAF201G.setAuthor("ELECTRIC CIRCUITS");  RAF201G.setEdition(1);  RAF201G.setCondition("New");  RAF201G.setSubjects(Collections.singleton(Subjects.Industrial_and_Mechanical_Engineering));  RAF201G.setPrice(13995);  RAF201G.setStatus("Óseld");bookRepository.save(RAF201G);
			Book RAF403G = new Book();  RAF403G.setTitle("MICROELECTRONIC CIRCUITS");  RAF403G.setAuthor("SEDRA, ADEL S.");  RAF403G.setEdition(7);  RAF403G.setCondition("New");  RAF403G.setSubjects(Collections.singleton(Subjects.Industrial_and_Mechanical_Engineering));  RAF403G.setPrice(12995);  RAF403G.setStatus("Óseld");bookRepository.save(RAF403G);
			Book RAF501G = new Book();  RAF501G.setTitle("INTRODUCTION TO COMMUNICATION SYSTEMS");  RAF501G.setAuthor("MADHOW, UPAMANYU");  RAF501G.setEdition(1);  RAF501G.setCondition("New");  RAF501G.setSubjects(Collections.singleton(Subjects.Electrical_Computer_Engineering));  RAF501G.setPrice(9295);  RAF501G.setStatus("Óseld");bookRepository.save(RAF501G);
			Book RAF504G = new Book();  RAF504G.setTitle("MICROELECTRONIC CIRCUITS");  RAF504G.setAuthor("SEDRA, ADEL S.");  RAF504G.setEdition(7);  RAF504G.setCondition("New");  RAF504G.setSubjects(Collections.singleton(Subjects.Electrical_Computer_Engineering));  RAF504G.setPrice(12995);  RAF504G.setStatus("Óseld");bookRepository.save(RAF504G);
			Book RAF507M = new Book();  RAF507M.setTitle("Medical Imaging Signals and Systems");  RAF507M.setAuthor("Prince, Jerry L.");  RAF507M.setEdition(2);  RAF507M.setCondition("New");  RAF507M.setSubjects(Collections.singleton(Subjects.Electrical_Computer_Engineering));  RAF507M.setPrice(12995);  RAF507M.setStatus("Óseld");bookRepository.save(RAF507M);
			Book TOV301G = new Book();  TOV301G.setTitle("Computer Organization and Design");  TOV301G.setAuthor("Patterson, David A.");  TOV301G.setEdition(5);  TOV301G.setCondition("New");  TOV301G.setSubjects(Collections.singleton(Subjects.Electrical_Computer_Engineering));  TOV301G.setPrice(12495);  TOV301G.setStatus("Óseld");bookRepository.save(TOV301G);
			Book TOV501M = new Book();  TOV501M.setTitle("Computer Architecture: A Quantitaive Approach");  TOV501M.setAuthor("Hennessy, John L.");  TOV501M.setEdition(6);  TOV501M.setCondition("New");  TOV501M.setSubjects(Collections.singleton(Subjects.Electrical_Computer_Engineering));  TOV501M.setPrice(16995);  TOV501M.setStatus("Óseld");bookRepository.save(TOV501M);
			Book VEL103M = new Book();  VEL103M.setTitle("First Course in the Finite Element");  VEL103M.setAuthor("Logan, Daryl");  VEL103M.setEdition(6);  VEL103M.setCondition("New");  VEL103M.setSubjects(Collections.singleton(Subjects.Industrial_and_Mechanical_Engineering));  VEL103M.setPrice(13995);  VEL103M.setStatus("Óseld");bookRepository.save(VEL103M);
			Book VEL301G = new Book();  VEL301G.setTitle("MATERIALS SCIENCE AND ENGINEERING");  VEL301G.setAuthor("CALLISTER, WILLIAM D. & RETHWI");  VEL301G.setEdition(9);  VEL301G.setCondition("New");  VEL301G.setSubjects(Collections.singleton(Subjects.Industrial_and_Mechanical_Engineering));  VEL301G.setPrice(11495);  VEL301G.setStatus("Óseld");bookRepository.save(VEL301G);
			Book VEL302G = new Book();  VEL302G.setTitle("FUNDAMENTALS OF MECHATRONICS");  VEL302G.setAuthor("JOUANEH, M.");  VEL302G.setEdition(1);  VEL302G.setCondition("New");  VEL302G.setSubjects(Collections.singleton(Subjects.Industrial_and_Mechanical_Engineering));  VEL302G.setPrice(14495);  VEL302G.setStatus("Óseld");bookRepository.save(VEL302G);
			Book VEL501M = new Book();  VEL501M.setTitle("Principles and Prevention of Corrosion");  VEL501M.setAuthor("Jones, Denny A.");  VEL501M.setEdition(2);  VEL501M.setCondition("New");  VEL501M.setSubjects(Collections.singleton(Subjects.Industrial_and_Mechanical_Engineering));  VEL501M.setPrice(12995);  VEL501M.setStatus("Óseld");bookRepository.save(VEL501M);
			Book VEL502G = new Book();  VEL502G.setTitle("FLUID MECHANICS");  VEL502G.setAuthor("WHITE, FRANK M.");  VEL502G.setEdition(8);  VEL502G.setSubjects(Collections.singleton(Subjects.Industrial_and_Mechanical_Engineering));  VEL502G.setPrice(11495);  VEL502G.setStatus("Óseld");bookRepository.save(VEL502G);

		}
	}
}


