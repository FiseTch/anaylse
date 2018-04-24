package tch.model;


public class ReviewResultToString {
    private String id;

    private String pId;

    private String tId;

    private String time;

    private Double validityA;

    private Double validityB;

    private Double reliability;

    private Double difficulty;

    private Double distinction;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId == null ? null : pId.trim();
    }

    public String gettId() {
        return tId;
    }

    public void settId(String tId) {
        this.tId = tId == null ? null : tId.trim();
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Double getValidityA() {
        return validityA;
    }

    public void setValidityA(Double validityA) {
        this.validityA = validityA;
    }

    public Double getValidityB() {
        return validityB;
    }

    public void setValidityB(Double validityB) {
        this.validityB = validityB;
    }

    public Double getReliability() {
        return reliability;
    }

    public void setReliability(Double reliability) {
        this.reliability = reliability;
    }

    public Double getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Double difficulty) {
        this.difficulty = difficulty;
    }

    public Double getDistinction() {
        return distinction;
    }

    public void setDistinction(Double distinction) {
        this.distinction = distinction;
    }
}