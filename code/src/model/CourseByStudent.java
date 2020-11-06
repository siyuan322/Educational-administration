package model;

//学生选课视图
public class CourseByStudent {
    private int teachID;
    private String cname;
    private String tname;
    private String chour;
    private Float ccredit;
    private int capacity;
    private int currentNum;
    private String studyTime;
    private String description;
    private String institute;

    public int getTeachID() {
        return teachID;
    }

    public void setTeachID(int teachID) {
        this.teachID = teachID;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public String getChour() {
        return chour;
    }

    public void setChour(String chour) {
        this.chour = chour;
    }

    public Float getCcredit() {
        return ccredit;
    }

    public void setCcredit(Float ccredit) {
        this.ccredit = ccredit;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getCurrentNum() {
        return currentNum;
    }

    public void setCurrentNum(int currentNum) {
        this.currentNum = currentNum;
    }

    public String getStudyTime() {
        return studyTime;
    }

    public void setStudyTime(String studyTime) {
        this.studyTime = studyTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInstitute() {
        return institute;
    }

    public void setInstitute(String institute) {
        this.institute = institute;
    }
}
