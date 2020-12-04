package model;

//选修
public class Study {
    private String sid;
    private int teachID;
    private String clid;//班级编号
    private int cid;//课程号
    private String cname;//课程名称
    private int normalGrade;//平时成绩
    private int termGrade;//期末成绩
    private int finalGrade;//最终成绩


    public String getSid() {
        return sid;
    }

    public int getCid() {
        return cid;
    }

    public int getTeachID() {
        return teachID;
    }

    public int getNormalGrade() {
        return normalGrade;
    }

    public int getTermGrade() {
        return termGrade;
    }

    public int getFinalGrade() {
        return finalGrade;
    }

    public String getCname() {
        return cname;
    }

    public String getClid() {
        return clid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public void setTermGrade(int termGrade) {
        this.termGrade = termGrade;
    }

    public void setNormalGrade(int normalGrade) {
        this.normalGrade = normalGrade;
    }

    public void setFinalGrade(int finalGrade) {
        this.finalGrade = finalGrade;
    }

    public void setTeachID(int teachID) {
        this.teachID = teachID;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public void setClid(String classno) {
        this.clid = classno;
    }

    @Override
    public String toString() {
        return "Study{" +
                "sid='" + sid + '\'' +
                ", teachID=" + teachID +
                ", clid='" + clid + '\'' +
                ", cid=" + cid +
                ", cname='" + cname + '\'' +
                ", normalGrade=" + normalGrade +
                ", termGrade=" + termGrade +
                ", finalGrade=" + finalGrade +
                '}';
    }
}
