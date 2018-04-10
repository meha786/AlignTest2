package PublicServiceTest;

import junit.framework.Assert;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mehaexample.asdDemo.alignWebsite.PublicFacing;
import org.mehaexample.asdDemo.alignWebsite.StudentFacingService;
import org.mehaexample.asdDemo.dao.alignprivate.CoursesDao;
import org.mehaexample.asdDemo.dao.alignprivate.ElectivesDao;
import org.mehaexample.asdDemo.dao.alignprivate.PrivaciesDao;
import org.mehaexample.asdDemo.dao.alignprivate.ProjectsDao;
import org.mehaexample.asdDemo.dao.alignprivate.StudentsDao;
import org.mehaexample.asdDemo.dao.alignprivate.WorkExperiencesDao;
import org.mehaexample.asdDemo.dao.alignpublic.StudentsPublicDao;
import org.mehaexample.asdDemo.dao.alignpublic.UndergraduatesPublicDao;
import org.mehaexample.asdDemo.dao.alignpublic.WorkExperiencesPublicDao;
import org.mehaexample.asdDemo.enums.Campus;
import org.mehaexample.asdDemo.enums.DegreeCandidacy;
import org.mehaexample.asdDemo.enums.EnrollmentStatus;
import org.mehaexample.asdDemo.enums.Gender;
import org.mehaexample.asdDemo.enums.Term;
import org.mehaexample.asdDemo.model.alignprivate.ExtraExperiences;
import org.mehaexample.asdDemo.model.alignprivate.Privacies;
import org.mehaexample.asdDemo.model.alignprivate.Projects;
import org.mehaexample.asdDemo.model.alignprivate.Students;
import org.mehaexample.asdDemo.model.alignprivate.WorkExperiences;
import org.mehaexample.asdDemo.model.alignpublic.*;
import org.mehaexample.asdDemo.restModels.TopUnderGradSchools;

import javax.ws.rs.core.Response;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class serviceTest {

    public static PublicFacing publicFacing;
    public static StudentsPublicDao studentsPublicDao;
    public static UndergraduatesPublicDao undergraduatesPublicDao;
    public static WorkExperiencesPublicDao workExperiencesPublicDao;

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

    @BeforeClass
    public static void init() {
        publicFacing = new PublicFacing();
        studentsPublicDao = new StudentsPublicDao();
        undergraduatesPublicDao = new UndergraduatesPublicDao();
        workExperiencesPublicDao = new WorkExperiencesPublicDao();
    	studentsDao = new StudentsDao();
		studentFacing = new StudentFacingService();
		electivesDao = new ElectivesDao();
		coursesDao = new CoursesDao();
		workExperiencesDao = new WorkExperiencesDao();
		studentsDao = new StudentsDao();
		privaciesDao = new PrivaciesDao();
		projectsDao = new ProjectsDao(true);
		System.out.println("jqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq");
    }

    @Before
    public void setup() {

        StudentsPublic studentsPublic1 = new StudentsPublic(21, 2015, true);
        StudentsPublic studentsPublic2 = new StudentsPublic(22, 2016, true);
        StudentsPublic studentsPublic3 = new StudentsPublic(23, 2017, true);
        StudentsPublic studentsPublic4 = new StudentsPublic(24, 2018, true);

        studentsPublicDao.createStudent(studentsPublic1);
        studentsPublicDao.createStudent(studentsPublic2);
        studentsPublicDao.createStudent(studentsPublic3);
        studentsPublicDao.createStudent(studentsPublic4);

        UndergraduatesPublic undergraduatesPublic1 =
                new UndergraduatesPublic(21, "cs", "WSU");
        UndergraduatesPublic undergraduatesPublic2 =
                new UndergraduatesPublic(22, "IT", "CSU");
        UndergraduatesPublic undergraduatesPublic3 =
                new UndergraduatesPublic(23, "maths", "NYU");
        UndergraduatesPublic undergraduatesPublic4 =
                new UndergraduatesPublic(24, "technology", "NEU");
        UndergraduatesPublic undergraduatesPublic5 =
                new UndergraduatesPublic(21, "agriculture", "UW");

        undergraduatesPublicDao.createUndergraduate(undergraduatesPublic1);
        undergraduatesPublicDao.createUndergraduate(undergraduatesPublic2);
        undergraduatesPublicDao.createUndergraduate(undergraduatesPublic3);
        undergraduatesPublicDao.createUndergraduate(undergraduatesPublic4);
        undergraduatesPublicDao.createUndergraduate(undergraduatesPublic5);


        WorkExperiencesPublic workExperiencesPublic1 = new WorkExperiencesPublic(21, "lululemon");
        WorkExperiencesPublic workExperiencesPublic2 = new WorkExperiencesPublic(22, "Scality");
        WorkExperiencesPublic workExperiencesPublic3 = new WorkExperiencesPublic(23, "Redfin");
        WorkExperiencesPublic workExperiencesPublic4 = new WorkExperiencesPublic(24, "blackrock");
        WorkExperiencesPublic workExperiencesPublic5 = new WorkExperiencesPublic(21, "FedEx");

        workExperiencesPublicDao.createWorkExperience(workExperiencesPublic1);
        workExperiencesPublicDao.createWorkExperience(workExperiencesPublic2);
        workExperiencesPublicDao.createWorkExperience(workExperiencesPublic3);
        workExperiencesPublicDao.createWorkExperience(workExperiencesPublic4);
        workExperiencesPublicDao.createWorkExperience(workExperiencesPublic5);
        
        
    	Students newStudent = new Students("0000000", "tomcat@gmail.com", "Tom", "",
				"Cat", Gender.M, "F1", "1111111111",
				"401 Terry Ave", "WA", "Seattle", "98109", Term.FALL, 2015,
				Term.SPRING, 2017,
				EnrollmentStatus.FULL_TIME, Campus.SEATTLE, DegreeCandidacy.MASTERS, null, true);
		Students newStudent2 = new Students("1111111", "jerrymouse@gmail.com", "Jerry", "",
				"Mouse", Gender.F, "F1", "1111111111",
				"225 Terry Ave", "MA", "Seattle", "98109", Term.FALL, 2014,
				Term.SPRING, 2016,
				EnrollmentStatus.PART_TIME, Campus.BOSTON, DegreeCandidacy.MASTERS, null, true);
		Students newStudent3 = new Students("2222222", "tomcat3@gmail.com", "Tom", "",
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
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
		Date startdate = null;
		try {
			startdate = formatter.parse(STARTDATE);
		} catch (ParseException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		Date enddate = null;
		try {
			enddate = formatter.parse(ENDDATE);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ExtraExperiences extraExperiences = new ExtraExperiences(NEUIDTEST, "companyName", startdate, 
				enddate, "title", "description"	);

//		studentFacing.addExtraExperience(NEUIDTEST, extraExperiences);

		// Add courses
		//		Courses newCourse = new Courses("100", "course2", "course description 2");
		//		Electives elective = new Electives();
		//		elective.setNeuId(NEUIDTEST);
		//		elective.setElectiveId(100);
		//		elective.setCourseId(newCourse.getCourseId());
		//
		//		coursesDao.createCourse(newCourse);
		//		electivesDao.addElective(elective);

		Privacies privacy = new Privacies();
		privacy.setNeuId(NEUIDTEST);
		privacy.setPublicId(studentsDao.getStudentRecord(NEUIDTEST).getPublicId());
		privacy.setCoop(true);
		privaciesDao.createPrivacy(privacy);

		WorkExperiences newWorkExperience = new WorkExperiences();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			newWorkExperience.setStartDate(dateFormat.parse("2017-06-01"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			newWorkExperience.setEndDate(dateFormat.parse("2017-12-01"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		newWorkExperience.setCurrentJob(false);
		newWorkExperience.setCoop(true);
		newWorkExperience.setTitle("Title");
		newWorkExperience.setDescription("Description");
		newWorkExperience.setNeuId(NEUIDTEST);
		newWorkExperience.setCompanyName("Amazon");
		workExperiencesDao.createWorkExperience(newWorkExperience);

		SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
		Projects project = null;
		try {
			project = new Projects(NEUIDTEST, "Student Website", dateFormat2.parse("2018-01-01"),
					dateFormat2.parse("2018-04-01"), "My Project");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		projectsDao.createProject(project);

    }

    @After
    public void deleteForDuplicateDatabase() {
        studentsPublicDao.deleteStudentByPublicId(21);
        studentsPublicDao.deleteStudentByPublicId(22);
        studentsPublicDao.deleteStudentByPublicId(23);
        studentsPublicDao.deleteStudentByPublicId(24);
    }

    @SuppressWarnings("unchecked")
	@Test
	public void updateStudentRecordTest(){
		Students students = studentsDao.getStudentRecord(NEUIDTEST);
		students.setCity("BOSTON");

		studentFacing.updateStudentRecord(NEUIDTEST, students);
		students = studentsDao.getStudentRecord(NEUIDTEST);

		Assert.assertEquals(students.getCity(), "BOSTON");
	}

    @SuppressWarnings("unchecked")
    @Test
    public void getHigestEducation() {
        JSONObject education = new JSONObject();
        Response res = publicFacing.getListOfHighestEducation();
        Assert.assertEquals(education.toString(), res.getEntity());
        Assert.assertEquals(200, res.getStatus());
    }


    @SuppressWarnings("unchecked")
    @Test
    public void getState() {
        JSONObject state = new JSONObject();
        Response res = publicFacing.getListOfState();
        Assert.assertEquals(state.toString(), res.getEntity());
        Assert.assertEquals(200, res.getStatus());
    }

    @SuppressWarnings("unchecked")
    @Test
    public void getRace() {
        JSONObject race = new JSONObject();
        Response res = publicFacing.getRace();
        Assert.assertEquals(race.toString(), res.getEntity());
        Assert.assertEquals(200, res.getStatus());
    }

    @SuppressWarnings("unchecked")
    @Test
    public void getAllSchools() {
        List<String> schools = new ArrayList<>();
        schools.add("UW");
        schools.add("WSU");
        schools.add("CSU");
        schools.add("NYU");
        schools.add("NEU");
        Response res = publicFacing.getAllSchools();
        List response = (List) res.getEntity();
        Assert.assertEquals(schools, response);
        Assert.assertEquals(200, res.getStatus());
    }

    @SuppressWarnings("unchecked")
    @Test
    public void getTopSchools() {
        List<String> schools = new ArrayList<>();
        schools.add("UW");
        schools.add("WSU");
        schools.add("CSU");
        JSONArray result = new JSONArray();
        for (String school : schools) {
            result.put(school);
        }
        Response res = publicFacing.getUndergradSchools(3);
        String response = (String) res.getEntity();
        Assert.assertEquals(result.toString(), response);
        Assert.assertEquals(200, res.getStatus());
    }

    @SuppressWarnings("unchecked")
    @Test
    public void getTopCoops() {
        List<String> coops = new ArrayList<>();
        coops.add("FedEx");
        coops.add("lululemon");
        coops.add("Scality");
        JSONArray result = new JSONArray();
        for (String coop : coops) {
            result.put(coop);
        }
        Response res = publicFacing.getTopCoops(3);
        String response = (String) res.getEntity();
        Assert.assertEquals(result.toString(), response);
        Assert.assertEquals(200, res.getStatus());
    }

    @SuppressWarnings("unchecked")
    @Test
    public void getTopMajor() {
        List<String> undergradMajor = new ArrayList<>();
        undergradMajor.add("agriculture");
        undergradMajor.add("cs");
        undergradMajor.add("IT");
        JSONArray result = new JSONArray();
        for (String major : undergradMajor) {
            result.put(major);
        }
        Response res = publicFacing.getUndergradDegrees(3);
        String response = (String) res.getEntity();
        Assert.assertEquals(result.toString(), response);
        Assert.assertEquals(200, res.getStatus());
    }

    @SuppressWarnings("unchecked")
    @Test
    public void getTopYear() {
        List<Integer> years = new ArrayList<>();
        years.add(2015);
        years.add(2016);
        years.add(2017);
        JSONArray result = new JSONArray();
        for (Integer year : years) {
            result.put(Integer.toString(year));
        }
        Response res = publicFacing.getTopGraduationYears(3);
        String response = (String) res.getEntity();
        Assert.assertEquals(result.toString(), response);
        Assert.assertEquals(200, res.getStatus());
    }



    @SuppressWarnings("unchecked")
    @Test
    public void getAllCoops() {

        List<String> coops = new ArrayList<>();
        coops.add("FedEx");
        coops.add("lululemon");
        coops.add("Scality");
        coops.add("Redfin");
        coops.add("blackrock");
        Response res = publicFacing.getAllCoopCompanies();
        List response = (List) res.getEntity();
        Assert.assertEquals(200, res.getStatus());
        Assert.assertEquals(coops, response);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void getAllUndergard() {
        List<String> undergrads = new ArrayList<>();
        undergrads.add("agriculture");
        undergrads.add("cs");
        undergrads.add("IT");
        undergrads.add("maths");
        undergrads.add("technology");
        Response res = publicFacing.getAllUndergradDegrees();
        List response = (List) res.getEntity();
        Assert.assertEquals(200, res.getStatus());
        Assert.assertEquals(undergrads, response);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void getAllGradYear() {
        List<Integer> gradYear = new ArrayList<>();
        gradYear.add(2015);
        gradYear.add(2016);
        gradYear.add(2017);
        gradYear.add(2018);
        JSONArray result = new JSONArray();
        for (Integer year : gradYear) {
            result.put(Integer.toString(year));
        }
        Response res = publicFacing.getAllGradYears();
        String response = (String) res.getEntity();
        //  Assert.assertEquals(200, res.getStatus());
        Assert.assertEquals(result.toString(), response);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void getEnrollment() {

        String full = "full-time";
        String part = "part-time";
        Response res = publicFacing.getEnrollmentStatus();
        //String response = (String) res.getEntity();
        Assert.assertEquals("{\"full-time\"" + ":\"49.09091\",\"part-time\"" + ":\"50.90909\"}", res.getEntity());
    }

    @SuppressWarnings("unchecked")
    @Test
    public void getCampus() {

        Response res = publicFacing.getCampusData();
        //String response = (String) res.getEntity();
        Assert.assertEquals("{\"boston\":\"24.324326\",\"charlotte\":\"25.225225\",\"siliconvalley\":\"25.675674\",\"seattle\":\"24.774775\"}", res.getEntity());
    }

    @SuppressWarnings("unchecked")
    @Test
    public void getGraduationTest() {

        Response res = publicFacing.getGraduation();
        //String response = (String) res.getEntity();
        Assert.assertEquals("{\"graduated\":\"49.51456\",\"terminated\":\"50.48544\"}", res.getEntity());
    }

    @SuppressWarnings("unchecked")
    @Test
    public void getScholarshipTest() {

        Response res = publicFacing.getScholarshipData();
        //String response = (String) res.getEntity();
        Assert.assertEquals("{\"scholarship\":\"54.545456\",\"none\":\"45.454548\"}", res.getEntity());
    }

    @SuppressWarnings("unchecked")
    @Test
    public void getGenderTest() {

        Response res = publicFacing.getGender();
        //String response = (String) res.getEntity();
        Assert.assertEquals("{\"female\":\"52.0\",\"male\":\"48.0\"}", res.getEntity());
    }
}