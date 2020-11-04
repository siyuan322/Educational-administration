package model;

// 课程
public class Course {
    private int cid;    // 课程号
    private String cname;   // 课程名
    private String chour;   // 学时
    private float ccredit;  // 学分
    private String institute;   // 开课学院

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
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

    public String getInstitute() {
        return institute;
    }

    public void setInstitute(String institute) {
        this.institute = institute;
    }

    @Override
    public String toString() {
        return "Course{" +
                "cid=" + cid +
                ", cname='" + cname + '\'' +
                ", chour='" + chour + '\'' +
                ", ccredit='" + ccredit + '\'' +
                '}';
    }
}
