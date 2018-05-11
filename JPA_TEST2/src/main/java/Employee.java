import com.sun.jndi.url.corbaname.corbanameURLContextFactory;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Pracownicy")
//@SecondaryTable(name = "Adres" ,pkJoinColumns = @PrimaryKeyJoinColumn(name = "ID_Pracownika"))// klucz obcy dla tej tabeli

public class Employee {

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWazne() {
        return wazne;
    }

    public void setWazne(String wazne) {
        this.wazne = wazne;
    }

    public String getKod() {
        return kod;
    }

    public void setKod(String kod) {
        this.kod = kod;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//(strategy = GenerationType.SEQUENCE,generator = "CLIENT_YQ")
    //@SequenceGenerator(name="CLIENT_YQ" ,sequenceName = "CLIENT_YQ",allocationSize = 1000)
    private Long id;

    @Column(name = "imie", nullable = false, length = 20)
    private String name;

    @Column(name = "Cos_tam")
    private String wazne;

    @Column(name="kod",columnDefinition = "VARCHAR(20) NOT NULL")//po table przypisuje pole do tabeli
    private String kod;

    @Column(name="email")
    private String email;

    public String getZarobki() {
        return zarobki;
    }

    public void setZarobki(String zarobki) {
        this.zarobki = zarobki;
    }

    @Column(name= "zarobki")
    private String zarobki;


}

 /* @Column(name = "Data")
    @Temporal(TemporalType.DATE)
    private java.util.Date data;*/

   /* @OneToOne
    private Address address;

    public Address getAddress() {
        return address;
    }
    public void setAddress(Address address) {
        this.address = address;
    }*/