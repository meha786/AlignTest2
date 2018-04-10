package PublicServiceTest;

import javax.ws.rs.core.Response;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mehaexample.asdDemo.alignWebsite.StudentFacingService;
import org.mehaexample.asdDemo.dao.alignprivate.CoursesDao;
import org.mehaexample.asdDemo.dao.alignprivate.ElectivesDao;
import org.mehaexample.asdDemo.dao.alignprivate.PrivaciesDao;
import org.mehaexample.asdDemo.dao.alignprivate.ProjectsDao;
import org.mehaexample.asdDemo.dao.alignprivate.StudentsDao;
import org.mehaexample.asdDemo.dao.alignprivate.WorkExperiencesDao;
import org.mehaexample.asdDemo.dao.alignpublic.UndergraduatesPublicDao;
import org.mehaexample.asdDemo.enums.Campus;
import org.mehaexample.asdDemo.enums.DegreeCandidacy;
import org.mehaexample.asdDemo.enums.EnrollmentStatus;
import org.mehaexample.asdDemo.enums.Gender;
import org.mehaexample.asdDemo.enums.Term;
import org.mehaexample.asdDemo.model.alignprivate.Students;
import org.mehaexample.asdDemo.restModels.EmailToRegister;

import junit.framework.Assert;

public class Student22Test {
	private static String NEUIDTEST = "0000000";
	private static String ENDDATE = "2017-01-04";
	private static String STARTDATE = "2018-01-04";

	private static StudentFacingService studentFacing;
	private static StudentsDao studentsDao;
	private static ElectivesDao electivesDao;
	private static CoursesDao coursesDao;
	private static WorkExperiencesDao workExperiencesDao;
	private static PrivaciesDao privaciesDao;
	private static ProjectsDao projectsDao;

	UndergraduatesPublicDao undergraduatesPublicDao = new UndergraduatesPublicDao(true);

	@BeforeClass
	public static void init() {
		studentsDao = new StudentsDao();
		studentFacing = new StudentFacingService();
		electivesDao = new ElectivesDao();
		coursesDao = new CoursesDao();
		workExperiencesDao = new WorkExperiencesDao();
		studentsDao = new StudentsDao();
		privaciesDao = new PrivaciesDao();
		projectsDao = new ProjectsDao(true); 
	}

	@Before
	public void setupAddRecords()throws Exception {
		Students newStudent = new Students("111", "tomcat@gmail.com", "Tom", "",
				"Cat", Gender.M, "F1", "1111111111",
				"401 Terry Ave", "WA", "Seattle", "98109", Term.FALL, 2015,
				Term.SPRING, 2017,
				EnrollmentStatus.FULL_TIME, Campus.SEATTLE, DegreeCandidacy.MASTERS, null, true);
		Students newStudent2 = new Students("112", "jerrymouse@gmail.com", "Jerry", "",
				"Mouse", Gender.F, "F1", "1111111111",
				"225 Terry Ave", "MA", "Seattle", "98109", Term.FALL, 2014,
				Term.SPRING, 2016,
				EnrollmentStatus.PART_TIME, Campus.BOSTON, DegreeCandidacy.MASTERS, null, true);
		Students newStudent3 = new Students("113", "tomcat3@gmail.com", "Tom", "",
				"Dog", Gender.M, "F1", "1111111111",
				"401 Terry Ave", "WA", "Seattle", "98109", Term.FALL, 2015,
				Term.FALL, 2017,
				EnrollmentStatus.DROPPED_OUT, Campus.CHARLOTTE, DegreeCandidacy.MASTERS, null, true);

		newStudent.setScholarship(true);
		newStudent.setRace("White");
		newStudent2.setRace("Black");
		newStudent3.setRace("White");

		studentsDao.addStudent(newStudent);
		studentsDao.addStudent(newStudent2);
		studentsDao.addStudent(newStudent3);

	}

	@After
	public void deleteForDuplicateDatabase() {
		studentsDao.deleteStudent("111");
		studentsDao.deleteStudent("112");
		studentsDao.deleteStudent("113");
	}

	@Test
	public void registerStudent3(){
		EmailToRegister emailToRegister = new EmailToRegister("test.alignstudent1231@gmail.com");
		Response res = studentFacing.sendRegistrationEmail(emailToRegister);

		String response = (String) res.getEntity();

		Assert.assertEquals("To Register should be an Align Student!" , response); 
	}

	@Test
	public void registerStudent4(){
		EmailToRegister emailToRegister = new EmailToRegister("");
		Response res = studentFacing.sendRegistrationEmail(emailToRegister);

		String response = (String) res.getEntity();

		Assert.assertEquals("Email Id can't be null or empty" , response); 
	}
}