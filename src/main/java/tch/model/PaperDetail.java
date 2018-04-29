package tch.model;

import java.util.Date;

public class PaperDetail {
    private String paperid;

    private String tId;

    private String subject;

    private Integer score;

    private Integer totaltitle;

    private Date time;

    private String subjectperson;

    private Date uptime;

    private String teacher;

    private String papertime;

    private Integer num;

    private String term;

    public String getPaperid() {
        return paperid;
    }

    public void setPaperid(String paperid) {
        this.paperid = paperid == null ? null : paperid.trim();
    }

    public String gettId() {
        return tId;
    }

    public void settId(String tId) {
        this.tId = tId == null ? null : tId.trim();
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject == null ? null : subject.trim();
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getTotaltitle() {
        return totaltitle;
    }

    public void setTotaltitle(Integer totaltitle) {
        this.totaltitle = totaltitle;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getSubjectperson() {
        return subjectperson;
    }

    public void setSubjectperson(String subjectperson) {
        this.subjectperson = subjectperson == null ? null : subjectperson.trim();
    }

    public Date getUptime() {
        return uptime;
    }

    public void setUptime(Date uptime) {
        this.uptime = uptime;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher == null ? null : teacher.trim();
    }

    public String getPapertime() {
        return papertime;
    }

    public void setPapertime(String papertime) {
        this.papertime = papertime == null ? null : papertime.trim();
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term == null ? null : term.trim();
    }
}