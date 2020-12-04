package model;

public class grade {
    private String sid;
    private int teachID;
    private String clano;//班级编号
    private int cid;//课程号
    private int satisticGrade;
    private String cname;//班级编号

    public String getSid() {
        return sid;
    }

    public int getCid() {
        return cid;
    }

    public int getTeachID() {
        return teachID;
    }

    public String getClano() {
        return clano;
    }

    public int getSatisticGrade() {
        return satisticGrade;
    }

    public String getCname() {
        return cname;
    }


    public String setSid(String sid) {
        return this.sid = sid;
    }

    public int setCid(int cid) {
        return this.cid = cid;
    }

    public int setTeachID(int teachID) {
        return this.teachID = teachID;
    }

    public String setClano(String classno) {
        return this.clano = classno;
    }

    public String setCname(String cname) {
        return this.cname = cname;
    }

    public int setSatisticGrade(int satisticGrade) {
        return this.satisticGrade = satisticGrade;
    }
}
