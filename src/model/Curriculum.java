package model;

/**
 * Created by Christofferpalsgaard on 26/11/2016.
 */
public class Curriculum {
    private String school;
    private String education;
    private int semester ;
    private int curriculumID;

    public Curriculum(String school, String education, int semester, int curriculumID) {
        this.school = school;
        this.education = education;
        this.semester = semester;
        this.curriculumID = curriculumID;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        school = school;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        education = education;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        semester = semester;
    }

    public int getCurriculumID() {
        return curriculumID;
    }

    public void setCurriculumID(int curriculumID) {
        curriculumID = curriculumID;
    }
}
