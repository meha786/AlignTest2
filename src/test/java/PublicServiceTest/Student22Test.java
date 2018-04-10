package PublicServiceTest;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ws.rs.core.Response;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mehaexample.asdDemo.alignWebsite.StudentFacingService;
import org.mehaexample.asdDemo.dao.alignprivate.CoursesDao;
import org.mehaexample.asdDemo.dao.alignprivate.ElectivesDao;
import org.mehaexample.asdDemo.dao.alignprivate.ExtraExperiencesDao;
import org.mehaexample.asdDemo.dao.alignprivate.PrivaciesDao;
import org.mehaexample.asdDemo.dao.alignprivate.ProjectsDao;
import org.mehaexample.asdDemo.dao.alignprivate.StudentLoginsDao;
import org.mehaexample.asdDemo.dao.alignprivate.StudentsDao;
import org.mehaexample.asdDemo.dao.alignprivate.WorkExperiencesDao;
import org.mehaexample.asdDemo.dao.alignpublic.UndergraduatesPublicDao;
import org.mehaexample.asdDemo.enums.Campus;
import org.mehaexample.asdDemo.enums.DegreeCandidacy;
import org.mehaexample.asdDemo.enums.EnrollmentStatus;
import org.mehaexample.asdDemo.enums.Gender;
import org.mehaexample.asdDemo.enums.Term;
import org.mehaexample.asdDemo.model.alignprivate.ExtraExperiences;
import org.mehaexample.asdDemo.model.alignprivate.StudentLogins;
import org.mehaexample.asdDemo.model.alignprivate.Students;
import org.mehaexample.asdDemo.restModels.EmailToRegister;
import org.mehaexample.asdDemo.restModels.ExtraExperienceObject;
import org.mehaexample.asdDemo.restModels.PasswordChangeObject;
import org.mehaexample.asdDemo.restModels.PasswordResetObject;

import junit.framework.Assert;

public class Student22Test {
	private static String NEUIDTEST = "111";
	private static String ENDDATE = "2017-01-04";
	private static String STARTDATE = "2018-01-04";

	private static StudentFacingService studentFacing;
	private static StudentsDao studentsDao;
	private static ElectivesDao electivesDao;
	private static CoursesDao coursesDao;
	private static WorkExperiencesDao workExperiencesDao;
	private static PrivaciesDao privaciesDao;
	private static ProjectsDao projectsDao;
	private static StudentLoginsDao studentLoginsDao;
	private static ExtraExperiencesDao extraExperiencesDao;


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
		studentLoginsDao = new StudentLoginsDao();
		extraExperiencesDao = new ExtraExperiencesDao();
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

		// Adding experience
//		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
//		Date startdate = formatter.parse(STARTDATE);
//		Date enddate = formatter.parse(ENDDATE);
//		ExtraExperiences extraExperiences = new ExtraExperiences(NEUIDTEST, "companyName", startdate, 
//				enddate, "title", "description"	);

	}

	@After
	public void deleteForDuplicateDatabase() {
		studentsDao.deleteStudent("111");
		studentsDao.deleteStudent("112");
		studentsDao.deleteStudent("113");
	}

	@Test
	public void updateStudentRecordTest1(){
		Students student = studentsDao.getStudentRecord(NEUIDTEST);
		student.setCity("BOSTON");
		studentFacing.updateStudentRecord(NEUIDTEST, student);

		Students studentUpdated = studentsDao.getStudentRecord(NEUIDTEST);

		Assert.assertEquals(student.getCity(), studentUpdated.getCity());
	}

	@Test
	public void updateStudentRecordTest2(){
		Students student = studentsDao.getStudentRecord("10");
		Response response = studentFacing.updateStudentRecord("10", student);

		studentsDao.getStudentRecord(NEUIDTEST);

		Assert.assertEquals("No Student record exists with given ID", response.getEntity().toString());
	}

	@Test
	public void updateStudentRecordTest3(){
		Students student = studentsDao.getStudentRecord(NEUIDTEST);
		student.setCity("BOSTON");
		Response resp = studentFacing.updateStudentRecord(NEUIDTEST, student);

		studentsDao.getStudentRecord(NEUIDTEST);
		Assert.assertEquals(200, resp.getStatus());
	}

	@Test
	public void addExtraExperienceTest1(){
		Students student = studentsDao.getStudentRecord(NEUIDTEST);
		List<ExtraExperiences> extraExperiences =
				  extraExperiencesDao.getExtraExperiencesByNeuId(NEUIDTEST);
		
		ExtraExperienceObject extraExperiencesObject = new ExtraExperienceObject();
		
		Response resp = studentFacing.addExtraExperience("10", extraExperiencesObject);
		
		Assert.assertEquals("No Student record exists with given ID", resp.getEntity().toString());
	}
	
	@Test
	public void addExtraExperienceTest2(){
//		Students student = studentsDao.getStudentRecord(NEUIDTEST);
//		List<ExtraExperiences> extraExperiences =
//				  extraExperiencesDao.getExtraExperiencesByNeuId(NEUIDTEST);
		
		String endDate = "2017-01-04";
		String startDate = "2018-01-04";
		ExtraExperienceObject extraExperiencesObject = 
				new ExtraExperienceObject(111, NEUIDTEST, "companyName", startDate, 
						endDate, "title", "description");
		
		Response resp = studentFacing.addExtraExperience(NEUIDTEST, extraExperiencesObject);
		System.out.println("extra id" + extraExperiencesObject.getExtraExperienceId());
		
		Assert.assertEquals(200, resp.getStatus());
		
		int experirnceId = Integer.parseInt(resp.getEntity().toString());
		Response resp2 = studentFacing.deleteExtraExperience(NEUIDTEST, experirnceId);
		Assert.assertEquals("Experience deleted successfully", resp2.getEntity().toString());
	}

	@Test
	public void registerStudent1(){
		EmailToRegister emailToRegister = new EmailToRegister("test.alignstudent1231@gmail.com");
		Response res = studentFacing.sendRegistrationEmail(emailToRegister);

		String response = (String) res.getEntity();

		Assert.assertEquals("To Register should be an Align Student!" , response); 
	}

	@Test
	public void registerStudent2(){
		EmailToRegister emailToRegister = new EmailToRegister("");
		Response res = studentFacing.sendRegistrationEmail(emailToRegister);

		String response = (String) res.getEntity();

		Assert.assertEquals("Email Id can't be null or empty" , response); 
	}

	@Test
	public void sendRegistrationEmailTest3(){
		EmailToRegister emailToRegister = new EmailToRegister("tomcat@gmail.com");
		Response res = studentFacing.sendRegistrationEmail(emailToRegister);

		String response = (String) res.getEntity();

		Assert.assertEquals("Registration link sent succesfully to tomcat@gmail.com" , response); 
	}

	@Test
	public void sendEmailForPasswordResetStudentTest1(){

		StudentLogins studentLogins = new StudentLogins("tomcat3@gmail.com",
				"password",
				"key",
				Timestamp.valueOf("2017-09-23 10:10:10.0"),
				Timestamp.valueOf("2017-09-23 10:10:10.0"),
				true);
		studentLoginsDao.createStudentLogin(studentLogins);

		PasswordResetObject passwordResetObject = new PasswordResetObject("tomcat3@gmail.com");

		Response res = studentFacing.sendEmailForPasswordResetStudent(passwordResetObject);

		String response = (String) res.getEntity();

		Assert.assertEquals("Password Reset link sent succesfully!" , response); 

		studentLoginsDao.deleteStudentLogin("tomcat3@gmail.com");
	}

	@Test
	public void sendEmailForPasswordResetStudentTest2(){

		StudentLogins studentLogins = new StudentLogins("tomcat3@gmail.com",
				"password",
				"key",
				Timestamp.valueOf("2017-09-23 10:10:10.0"),
				Timestamp.valueOf("2017-09-23 10:10:10.0"),
				false);
		studentLoginsDao.createStudentLogin(studentLogins);

		PasswordResetObject passwordResetObject = new PasswordResetObject("tomcat3@gmail.com");

		Response res = studentFacing.sendEmailForPasswordResetStudent(passwordResetObject);

		String response = (String) res.getEntity();

		Assert.assertEquals("Password can't be reset....Please create password and register first: " , response); 

		studentLoginsDao.deleteStudentLogin("tomcat3@gmail.com");
	}

	@Test
	public void sendEmailForPasswordResetStudentTest3(){

		PasswordResetObject passwordResetObject = new PasswordResetObject(null);

		Response res = studentFacing.sendEmailForPasswordResetStudent(passwordResetObject);

		String response = (String) res.getEntity();

		Assert.assertEquals("Email Id can't be null" , response); 
	}

	@Test
	public void sendEmailForPasswordResetStudentTest4(){

		PasswordResetObject passwordResetObject = new PasswordResetObject("meha@gmail.com");

		Response res = studentFacing.sendEmailForPasswordResetStudent(passwordResetObject);

		String response = (String) res.getEntity();

		Assert.assertEquals("Email doesn't exist, Please enter a valid Email Id null" , response); 
	}

	@Test
	public void changeUserPassword1(){

		StudentLogins studentLogins = new StudentLogins("tomcat3@gmail.com",
				"password",
				"key",
				Timestamp.valueOf("2017-09-23 10:10:10.0"),
				Timestamp.valueOf("2017-09-23 10:10:10.0"),
				true);
		studentLoginsDao.createStudentLogin(studentLogins);

		System.out.println("password " + studentLogins.getStudentPassword()); 

		PasswordChangeObject passwordChangeObject = new PasswordChangeObject("tomcat3@gmail.com","password", "password1");

		Response res = studentFacing.changeUserPassword(passwordChangeObject);

		String response = (String) res.getEntity();

		Assert.assertEquals("Incorrect Password" , response); 

		studentLoginsDao.deleteStudentLogin("tomcat3@gmail.com");
	}

}