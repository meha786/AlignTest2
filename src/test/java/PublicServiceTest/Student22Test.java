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
import org.mehaexample.asdDemo.restModels.EmailToRegister;
import org.mehaexample.asdDemo.restModels.StudentProfile;
import org.mehaexample.asdDemo.restModels.TopUnderGradSchools;

import javax.ws.rs.core.Response;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Student22Test {
	
	public static PublicFacing publicFacing;
    public static StudentsPublicDao studentsPublicDao;
    public static UndergraduatesPublicDao undergraduatesPublicDao;
    public static WorkExperiencesPublicDao workExperiencesPublicDao;


    @BeforeClass
    public static void init() {
        publicFacing = new PublicFacing();
        studentsPublicDao = new StudentsPublicDao();
        undergraduatesPublicDao = new UndergraduatesPublicDao();
        workExperiencesPublicDao = new WorkExperiencesPublicDao();
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
    public void getHigestEducation() {
        JSONObject education = new JSONObject();
        Response res = publicFacing.getListOfHighestEducation();
        Assert.assertEquals(education.toString(), res.getEntity());
        Assert.assertEquals(200, res.getStatus());
    }

    @SuppressWarnings("unchecked")
    @Test
    public void getHigestEducation2() {
        JSONObject education = new JSONObject();
        Response res = publicFacing.getListOfHighestEducation();
        Assert.assertEquals(education.toString(), res.getEntity());
        Assert.assertEquals(200, res.getStatus());
    }
   
}