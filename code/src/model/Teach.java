package model;

public class Teach {
    private int teachID;
    private int cid;
    private String tid;
    private int capacity;
    private int currentNum;
    private String studyTime;
    private String description;
    private String institute;
    private int isAudit;

    public Teach() {
    }

    public Teach(int cid, String tid, int capacity, int currentNum, String studyTime, String description, String institute, int isAudit) {
        this.cid = cid;
        this.tid = tid;
        this.capacity = capacity;
        this.currentNum = currentNum;
        this.studyTime = studyTime;
        this.description = description;
        this.institute = institute;
        this.isAudit = isAudit;
    }

    public int getTeachID() {
        return teachID;
    }

    public void setTeachID(int teachID) {
        this.teachID = teachID;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
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

    public int getIsAudit() {
        return isAudit;
    }

    public void setAudit(int audit) {
        isAudit = audit;
    }
}
