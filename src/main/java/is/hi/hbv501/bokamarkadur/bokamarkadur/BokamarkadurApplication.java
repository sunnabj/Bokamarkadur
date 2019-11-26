package is.hi.hbv501.bokamarkadur.bokamarkadur;

import is.hi.hbv501.bokamarkadur.bokamarkadur.Entities.Book;
import is.hi.hbv501.bokamarkadur.bokamarkadur.Entities.Subjects;
import is.hi.hbv501.bokamarkadur.bokamarkadur.Entities.User;
import is.hi.hbv501.bokamarkadur.bokamarkadur.Repositories.BookRepository;
import is.hi.hbv501.bokamarkadur.bokamarkadur.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

@SpringBootApplication
@EnableJpaRepositories

public class BokamarkadurApplication {

	Date d1 = new Date(2323223232L);
	Date d2 = new Date();

	Date randomDate = new Date(ThreadLocalRandom.current()
			.nextLong(d1.getTime(), d2.getTime()));
	Date randomDate0 = new Date(ThreadLocalRandom.current()
			.nextLong(d1.getTime(), d2.getTime()));
	Date randomDate1 = new Date(ThreadLocalRandom.current()
			.nextLong(d1.getTime(), d2.getTime()));
	Date randomDate2 = new Date(ThreadLocalRandom.current()
			.nextLong(d1.getTime(), d2.getTime()));
	Date randomDate3 = new Date(ThreadLocalRandom.current()
			.nextLong(d1.getTime(), d2.getTime()));
	Date randomDate4 = new Date(ThreadLocalRandom.current()
			.nextLong(d1.getTime(), d2.getTime()));
	Date randomDate5 = new Date(ThreadLocalRandom.current()
			.nextLong(d1.getTime(), d2.getTime()));
	Date randomDate6 = new Date(ThreadLocalRandom.current()
			.nextLong(d1.getTime(), d2.getTime()));
	Date randomDate7 = new Date(ThreadLocalRandom.current()
			.nextLong(d1.getTime(), d2.getTime()));
	Date randomDate8 = new Date(ThreadLocalRandom.current()
			.nextLong(d1.getTime(), d2.getTime()));
	Date randomDate9 = new Date(ThreadLocalRandom.current()
			.nextLong(d1.getTime(), d2.getTime()));
	Date randomDate10 = new Date(ThreadLocalRandom.current()
			.nextLong(d1.getTime(), d2.getTime()));

	public static void main(String[] args) {
		SpringApplication.run(BokamarkadurApplication.class, args);
	}
	@Component
	class DemoCommandLineRunner implements CommandLineRunner {

		@Autowired
		BookRepository bookRepository;

		@Autowired
		UserRepository userRepository;

		@Override
		public void run(String... args) throws Exception {
			/*
			 * Some dummy data is inserted into the database when
			 * application is started.
			 */
			User Tester = new User();
			Tester.setId(1);
			Tester.setEmail("tester@testing.com");
			Tester.setInfo("I'm the best Tester ever!");
			Tester.setName("Tester");
			Tester.setPassword("12345678");
			Tester.setUsername("Tester");
			userRepository.save(Tester);

			Book REI502M = new Book();
			REI502M.setTitle("Introduction to Data Mining");
			REI502M.setAuthor("Tan, Pang-Ning");
			REI502M.setEdition(2);
			REI502M.setCondition("Gott");
			REI502M.setSubjects(Subjects.COMPUTERSCIENCE);
			REI502M.setPrice(12995);
			REI502M.setStatus("For sale");
			REI502M.setImage("1_REI502M.jpg");
			REI502M.setDate(randomDate);
			REI502M.setUser(Tester);
			bookRepository.save(REI502M);

			Book REI502M_2 = new Book();
			REI502M_2.setTitle("Data Mining: Practical Machine Learning");
			REI502M_2.setAuthor("Witter, Ian H.");
			REI502M_2.setEdition(4);
			REI502M_2.setSubjects(Subjects.COMPUTERSCIENCE);
			REI502M_2.setPrice(9995);
			REI502M_2.setStatus("For sale");
			REI502M_2.setImage("2_REI502M_2.jpg");
			REI502M_2.setDate(randomDate0);
			REI502M_2.setUser(Tester);
			bookRepository.save(REI502M_2);

			Book TÖL101G = new Book();
			TÖL101G.setTitle("Introduction To Programming In Java");
			TÖL101G.setAuthor("SEDGEWICK, ROBERT.");
			TÖL101G.setEdition(2);
			TÖL101G.setCondition("New");
			TÖL101G.setSubjects(Subjects.COMPUTERSCIENCE);
			TÖL101G.setPrice(14995);
			TÖL101G.setStatus("For sale");
			//TÖL101G.setImage("3_TÖL101G.jpg");
			TÖL101G.setImage("3_TÖL101G.png");
			TÖL101G.setDate(randomDate1);
			TÖL101G.setUser(Tester);
			bookRepository.save(TÖL101G);

			Book TÖL104G = new Book();
			TÖL104G.setTitle("Discrete Mathematics and It’s Applications");
			TÖL104G.setAuthor("Rosen, Kenneth");
			TÖL104G.setEdition(8);  TÖL104G.setCondition("New");
			TÖL104G.setSubjects(Subjects.COMPUTERSCIENCE);
			TÖL104G.setPrice(11495);
			TÖL104G.setStatus("For sale");
			TÖL104G.setImage("4_TÖL104G.jpg");
			TÖL104G.setDate(randomDate2);
			TÖL104G.setUser(Tester);
			bookRepository.save(TÖL104G);

			Book TOL105G = new Book();
			TOL105G.setTitle("Think Python: How to Think Like a Computer Scientist");
			TOL105G.setAuthor("Downey, Allen B.");
			TOL105G.setEdition(2);
			TOL105G.setCondition("New");
			TOL105G.setSubjects(Subjects.COMPUTERSCIENCE);
			TOL105G.setPrice(5995);
			TOL105G.setStatus("For sale");
			TOL105G.setImage("5_TOL105G.jpg");
			TOL105G.setDate(randomDate3);
			TOL105G.setUser(Tester);
			bookRepository.save(TOL105G);

			Book TOL303G = new Book();
			TOL303G.setTitle("FIRST COURSE IN DATABASE SYSTEMS");
			TOL303G.setAuthor("ULLMAN, JEFFREY D.");
			TOL303G.setEdition(3);
			TOL303G.setCondition("New");
			TOL303G.setSubjects(Subjects.COMPUTERSCIENCE);
			TOL303G.setPrice(11995);
			TOL303G.setStatus("For sale");
			TOL303G.setImage("6_TOL303G.jpg");
			TOL303G.setDate(randomDate4);
			TOL303G.setUser(Tester);
			bookRepository.save(TOL303G);

			Book TOL304G = new Book();
			TOL304G.setTitle("CONCEPTS OF PROGRAMMING LANGUAGES");
			TOL304G.setAuthor("SEBESTA, ROBERT W.");
			TOL304G.setEdition(11);
			TOL304G.setCondition("New");
			TOL304G.setSubjects(Subjects.COMPUTERSCIENCE);
			TOL304G.setPrice(10395);
			TOL304G.setStatus("For sale");
			TOL304G.setImage("7_TOL304G.jpg");
			TOL304G.setDate(randomDate5);
			TOL304G.setUser(Tester);
			bookRepository.save(TOL304G);

			Book TOL305G = new Book();
			TOL305G.setTitle("COMPUTER NETWORKING");
			TOL305G.setAuthor("KUROSE, JAMES F.");
			TOL305G.setEdition(7);
			TOL305G.setCondition("New");
			TOL305G.setSubjects(Subjects.COMPUTERSCIENCE);
			TOL305G.setPrice(12995);
			TOL305G.setStatus("For sale");
			TOL305G.setImage("8_TOL305G.jpg");
			TOL305G.setDate(randomDate6);
			TOL305G.setUser(Tester);
			bookRepository.save(TOL305G);

			Book TOL101G = new Book();
			TOL101G.setTitle("Computer Systems: A Programmer’s Perspective");
			TOL101G.setAuthor("Bryant, Randal E.");
			TOL101G.setEdition(3);
			TOL101G.setCondition("New");
			TOL101G.setSubjects(Subjects.COMPUTERSCIENCE);
			TOL101G.setPrice(13795);
			TOL101G.setStatus("For sale");
			TOL101G.setImage("9_TOL101G.jpg");
			TOL101G.setDate(randomDate7);
			TOL101G.setUser(Tester);
			bookRepository.save(TOL101G);

			Book BYG201G = new Book();
			BYG201G.setTitle("STRUCTURAL AND STRESS ANALYSIS");
			BYG201G.setAuthor("MEGSON, T.H.G");
			BYG201G.setEdition(3);
			BYG201G.setCondition("New");
			BYG201G.setSubjects(Subjects.CIVILENGINEERING);
			BYG201G.setPrice(12195);
			BYG201G.setStatus("For sale");
			BYG201G.setImage("10_BYG201G.jpg");
			BYG201G.setDate(randomDate8);
			BYG201G.setUser(Tester);
			bookRepository.save(BYG201G);

			Book BYG501G = new Book();
			BYG501G.setTitle("Principles of Geotechnical Engineering");
			BYG501G.setAuthor("Das, Braja");
			BYG501G.setEdition(9);
			BYG501G.setCondition("New");
			BYG501G.setSubjects(Subjects.CIVILENGINEERING);
			BYG501G.setPrice(12995);
			BYG501G.setStatus("For sale");
			BYG501G.setImage("11_BYG501G.jpg");
			BYG501G.setDate(randomDate);
			BYG501G.setUser(Tester);
			bookRepository.save(BYG501G);

			Book BYG503G = new Book();
			BYG503G.setTitle("PRINCIPLES OF HIGHWAY ENGINEERING AND TRAFFIC ANALYSIS");
			BYG503G.setAuthor("MANNERING, FRED L. & WASHBURN");
			BYG503G.setEdition(5);  BYG503G.setCondition("New");
			BYG503G.setSubjects(Subjects.CIVILENGINEERING);
			BYG503G.setPrice(10995);
			BYG503G.setStatus("For sale");
			BYG503G.setImage("12_BYG503G.jpg");
			BYG503G.setDate(randomDate9);
			BYG503G.setUser(Tester);
			bookRepository.save(BYG503G);

			Book IDN101M = new Book();
			IDN101M.setTitle("JURAN’S QUALITY MANAGEMENT AND ANALYSIS");
			IDN101M.setAuthor("DE FEO, JOSEPH A.");
			IDN101M.setEdition(6);
			IDN101M.setCondition("New");
			IDN101M.setSubjects(Subjects.Industrial_and_Mechanical_Engineering);
			IDN101M.setPrice(13995);
			IDN101M.setStatus("For sale");
			IDN101M.setImage("13_IDN101M.jpg");
			IDN101M.setDate(randomDate);
			IDN101M.setUser(Tester);
			bookRepository.save(IDN101M);

			Book IDN103G = new Book();
			IDN103G.setTitle("Modern Industrial Management");
			IDN103G.setAuthor("Engwall, Mats");
			IDN103G.setEdition(1);
			IDN103G.setCondition("New");
			IDN103G.setSubjects(Subjects.Industrial_and_Mechanical_Engineering);
			IDN103G.setPrice(11995);
			IDN103G.setStatus("For sale");
			IDN103G.setImage("14_IDN103G.jpg");
			IDN103G.setDate(randomDate10);
			IDN103G.setUser(Tester);
			bookRepository.save(IDN103G);

			Book IDN113F = new Book();
			IDN113F.setTitle("TIME SERIES ANALYSIS: TEXTS IN STATISTIC");
			IDN113F.setAuthor("MADSEN, HENRIK");
			IDN113F.setEdition(1);
			IDN113F.setCondition("New");
			IDN113F.setSubjects(Subjects.Industrial_and_Mechanical_Engineering);
			IDN113F.setPrice(16995);
			IDN113F.setStatus("Requested");
			IDN113F.setImage("15_IDN113F.jpg");
			IDN113F.setDate(randomDate8);
			IDN113F.setUser(Tester);
			bookRepository.save(IDN113F);

			Book IDN122F = new Book();
			IDN122F.setTitle("Method for Engineering Students");
			IDN122F.setAuthor("Blomkvist, Par");
			IDN122F.setEdition(1);
			IDN122F.setCondition("New");
			IDN122F.setSubjects(Subjects.Industrial_and_Mechanical_Engineering);
			IDN122F.setPrice(7695);
			IDN122F.setStatus("Requested");
			IDN122F.setImage("16_IDN122F.jpg");
			IDN122F.setDate(randomDate5);
			IDN122F.setUser(Tester);
			bookRepository.save(IDN122F);

			Book IDN210F = new Book();
			IDN210F.setTitle("MANAGEMENT");
			IDN210F.setAuthor("DRUCKER, PETER F.");
			IDN210F.setEdition(0);
			IDN210F.setCondition("New");
			IDN210F.setSubjects(Subjects.Industrial_and_Mechanical_Engineering);
			IDN210F.setPrice(3895);
			IDN210F.setStatus("Requested");
			IDN210F.setImage("17_IDN210F.jpg");
			IDN210F.setDate(randomDate1);
			IDN210F.setUser(Tester);
			bookRepository.save(IDN210F);

			Book IDN301G = new Book();
			IDN301G.setTitle("Fundamentals of Business Process");
			IDN301G.setAuthor("Dumas, Marlon");
			IDN301G.setEdition(2);
			IDN301G.setCondition("New");
			IDN301G.setSubjects(Subjects.Industrial_and_Mechanical_Engineering);
			IDN301G.setPrice(11995);
			IDN301G.setStatus("Requested");
			IDN301G.setImage("18_IDN301G.jpg");
			IDN301G.setDate(randomDate0);
			IDN301G.setUser(Tester);
			bookRepository.save(IDN301G);

			Book RAF201G = new Book();
			RAF201G.setTitle("ELECTRIC CIRCUITS");
			RAF201G.setAuthor("ELECTRIC CIRCUITS");
			RAF201G.setEdition(1);
			RAF201G.setCondition("New");
			RAF201G.setSubjects(Subjects.Industrial_and_Mechanical_Engineering);
			RAF201G.setPrice(13995);
			RAF201G.setStatus("Requested");
			RAF201G.setImage("19_RAF201G.jpg");
			RAF201G.setDate(randomDate10);
			RAF201G.setUser(Tester);
			bookRepository.save(RAF201G);

			Book RAF403G = new Book();
			RAF403G.setTitle("MICROELECTRONIC CIRCUITS");
			RAF403G.setAuthor("SEDRA, ADEL S.");
			RAF403G.setEdition(7);
			RAF403G.setCondition("New");
			RAF403G.setSubjects(Subjects.Industrial_and_Mechanical_Engineering);
			RAF403G.setPrice(12995);
			RAF403G.setStatus("Requested");
			RAF403G.setImage("20_RAF403G.jpg");
			RAF403G.setDate(randomDate9);
			RAF403G.setUser(Tester);
			bookRepository.save(RAF403G);

			Book RAF501G = new Book();
			RAF501G.setTitle("INTRODUCTION TO COMMUNICATION SYSTEMS");
			RAF501G.setAuthor("MADHOW, UPAMANYU");
			RAF501G.setEdition(1);
			RAF501G.setCondition("New");
			RAF501G.setSubjects(Subjects.Electrical_Computer_Engineering);
			RAF501G.setPrice(9295);
			RAF501G.setStatus("Requested");
			RAF501G.setImage("");
			RAF501G.setDate(randomDate8);
			RAF501G.setUser(Tester);
			bookRepository.save(RAF501G);

			Book RAF504G = new Book();
			RAF504G.setTitle("MICROELECTRONIC CIRCUITS");
			RAF504G.setAuthor("SEDRA, ADEL S.");
			RAF504G.setEdition(7);
			RAF504G.setCondition("New");
			RAF504G.setSubjects(Subjects.Electrical_Computer_Engineering);
			RAF504G.setPrice(12995);
			RAF504G.setStatus("Requested");
			RAF504G.setImage("");
			RAF504G.setDate(randomDate7);
			RAF504G.setUser(Tester);
			bookRepository.save(RAF504G);

			Book RAF507M = new Book();
			RAF507M.setTitle("Medical Imaging Signals and Systems");
			RAF507M.setAuthor("Prince, Jerry L.");
			RAF507M.setEdition(2);
			RAF507M.setCondition("New");
			RAF507M.setSubjects(Subjects.Electrical_Computer_Engineering);
			RAF507M.setPrice(12995);
			RAF507M.setStatus("Requested");
			RAF507M.setImage("23_RAF507M.jpg");
			RAF507M.setDate(randomDate6);
			RAF507M.setUser(Tester);
			bookRepository.save(RAF507M);

			Book TOV301G = new Book();
			TOV301G.setTitle("Computer Organization and Design");
			TOV301G.setAuthor("Patterson, David A.");
			TOV301G.setEdition(5);
			TOV301G.setCondition("New");
			TOV301G.setSubjects(Subjects.Electrical_Computer_Engineering);
			TOV301G.setPrice(12495);
			TOV301G.setStatus("Requested");
			TOV301G.setImage("24_TOV301G.jpg");
			TOV301G.setDate(randomDate5);
			TOV301G.setUser(Tester);
			bookRepository.save(TOV301G);

			Book TOV501M = new Book();
			TOV501M.setTitle("Computer Architecture: A Quantitaive Approach");
			TOV501M.setAuthor("Hennessy, John L.");
			TOV501M.setEdition(6);
			TOV501M.setCondition("New");
			TOV501M.setSubjects(Subjects.Electrical_Computer_Engineering);
			TOV501M.setPrice(16995);
			TOV501M.setStatus("Requested");
			TOV501M.setImage("25_TOV501M.jpg");
			TOV501M.setDate(randomDate4);
			TOV501M.setUser(Tester);
			bookRepository.save(TOV501M);

			Book VEL103M = new Book();
			VEL103M.setTitle("First Course in the Finite Element");
			VEL103M.setAuthor("Logan, Daryl");
			VEL103M.setEdition(6);
			VEL103M.setCondition("New");
			VEL103M.setSubjects(Subjects.Industrial_and_Mechanical_Engineering);
			VEL103M.setPrice(13995);
			VEL103M.setStatus("For sale");
			VEL103M.setImage("26_VEL103M.jpg");
			VEL103M.setDate(randomDate3);
			VEL103M.setUser(Tester);
			bookRepository.save(VEL103M);

			Book VEL301G = new Book();
			VEL301G.setTitle("MATERIALS SCIENCE AND ENGINEERING");
			VEL301G.setAuthor("CALLISTER, WILLIAM D. & RETHWI");
			VEL301G.setEdition(9);  VEL301G.setCondition("New");
			VEL301G.setSubjects(Subjects.Industrial_and_Mechanical_Engineering);
			VEL301G.setPrice(11495);
			VEL301G.setStatus("Requested");
			VEL301G.setImage("27_VEL301G.jpg");
			VEL301G.setDate(randomDate2);
			VEL301G.setUser(Tester);
			bookRepository.save(VEL301G);

			Book VEL302G = new Book();
			VEL302G.setTitle("FUNDAMENTALS OF MECHATRONICS");
			VEL302G.setAuthor("JOUANEH, M.");
			VEL302G.setEdition(1);
			VEL302G.setCondition("New");
			VEL302G.setSubjects(Subjects.Industrial_and_Mechanical_Engineering);
			VEL302G.setPrice(14495);
			VEL302G.setStatus("Requested");
			VEL302G.setImage("28_VEL302G.jpg");
			VEL302G.setDate(randomDate1);
			VEL302G.setUser(Tester);
			bookRepository.save(VEL302G);

			Book VEL501M = new Book();
			VEL501M.setTitle("Principles and Prevention of Corrosion");
			VEL501M.setAuthor("Jones, Denny A.");  VEL501M.setEdition(2);
			VEL501M.setCondition("New");
			VEL501M.setSubjects(Subjects.Industrial_and_Mechanical_Engineering);
			VEL501M.setPrice(12995);
			VEL501M.setStatus("Requested");
			VEL501M.setImage("29_VEL501M.jpg");
			VEL501M.setDate(randomDate0);
			VEL501M.setUser(Tester);
			bookRepository.save(VEL501M);

			Book VEL502G = new Book();
			VEL502G.setTitle("FLUID MECHANICS");
			VEL502G.setAuthor("WHITE, FRANK M.");
			VEL502G.setEdition(8);
			VEL502G.setSubjects(Subjects.Industrial_and_Mechanical_Engineering);
			VEL502G.setPrice(11495);
			VEL502G.setStatus("Requested");
			VEL502G.setImage("30_VEL502G.jpg");
			VEL502G.setDate(randomDate);
			VEL502G.setUser(Tester);
			bookRepository.save(VEL502G);

		}
	}
}


