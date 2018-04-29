package tch.model;


public class PaperDetailToString {
    private String paperid;
    
    private String tId;

    private String subject;

    private Integer score;

    private String time;

    private String subjectperson;

    private String uptime;

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
		this.tId = tId;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSubjectperson() {
        return subjectperson;
    }

    public void setSubjectperson(String subjectperson) {
        this.subjectperson = subjectperson == null ? null : subjectperson.trim();
    }

    public String getUptime() {
        return uptime;
    }

    public void setUptime(String uptime) {
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