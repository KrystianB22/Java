import java.util.Date;

public class Person {

    private Long id;
    private String kod;
    private String imie;
    private String email;
    private String wazne;

    public String getZarobki() {
        return zarobki;
    }

    public void setZarobki(String zarobki) {
        this.zarobki = zarobki;
    }

    private String zarobki;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKod() {
        return kod;
    }

    public void setKod(String kod) {
        this.kod = kod;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getWazne() {
        return wazne;
    }

    public void setWazne(String wazne) {
        this.wazne = wazne;
    }

    public Person(Long id, String email, String kod, String imie, String wazne, String zarobki) {
        this.id = id;
        this.zarobki = zarobki;
        this.kod = kod;
        this.imie = imie;
        this.email = email;
        this.wazne = wazne;
    }
}
