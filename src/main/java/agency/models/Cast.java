package agency.models;

public class Cast {
    private int id;
    private String appreciation;
    private int score;
    private int IdActor;

    public Cast(String appreciation, int score, int idActor) {
        this.appreciation = appreciation;
        this.score = score;
        IdActor = idActor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAppreciation() {
        return appreciation;
    }

    public void setAppreciation(String appreciation) {
        this.appreciation = appreciation;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getIdActor() {
        return IdActor;
    }

    public void setIdActor(int idActor) {
        IdActor = idActor;
    }

    @Override
    public String toString() {
        return "Casting = [" +
                " ID = " + id +
                ", appreciation ='" + appreciation + '\'' +
                ", score=" + score +
                ", Identifiant de l'acteur = " + IdActor +
                ']';
    }
}
