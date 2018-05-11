
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;


import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class FXMLDocumentController {



    Employee employee = new Employee();
    SelectAll find_all = new SelectAll();
    find_name find_name =new find_name();
    EntityManager entityManager;




    @FXML
    private javafx.scene.control.TextField imie;
    @FXML
    private javafx.scene.control.TextField kod;
    @FXML
    private javafx.scene.control.TextField email;
    @FXML
    private javafx.scene.control.TextArea wazne;
    @FXML
    private javafx.scene.control.TextField zarobki;
    @FXML
    private javafx.scene.control.TextField find_name_textfield;
    @FXML
    private  javafx.scene.control.ComboBox combo_box;

    public TextField getFind_name_textfield() {

        return find_name_textfield;
    }

    @FXML
    private TableView<Person> table_view;
    @FXML
    private TableColumn<Person,String> col_imie;
    @FXML
    private TableColumn<Person,Long> col_id;
    @FXML
    private TableColumn<Person,String> col_email;
    @FXML
    private  TableColumn<Person,String> col_wazne;
    @FXML
    private  TableColumn<Person,String> col_kod;
    @FXML
    private TableColumn<Person,String> col_zarobki;

    @FXML
    public void Button_Wyslij_Action() {
        if (check_in()) { // sprawdza poprawnosc danych
            save();
           show_table_all_employee();
            } else {
           System.out.println("bład");
        }
    }
    @FXML
    public void Button_Find(){
        wys_name();
    }
    public void wys_name() {
        ObservableList<Person> observableList =FXCollections.observableArrayList();
        col_imie.setCellValueFactory(new PropertyValueFactory<Person,String>("imie"));
        col_id.setCellValueFactory(new PropertyValueFactory<Person,Long>("id"));
        col_email.setCellValueFactory(new PropertyValueFactory<Person,String >("email"));
        col_wazne.setCellValueFactory(new PropertyValueFactory<Person,String>("wazne"));
        col_kod.setCellValueFactory(new PropertyValueFactory<Person,String>("kod"));
        col_zarobki.setCellValueFactory(new PropertyValueFactory<Person,String>("zarobki"));
        table_view.setItems(observableList);

        for (Employee e : find_name.findbyname(find_name_textfield.getText())
                ) {
            observableList.add(new Person(e.getId(),e.getEmail(),e.getKod(),e.getName(),e.getWazne(),e.getZarobki()));
        }
    }
    public void show_table_all_employee() {

        ObservableList<Person> observableList =FXCollections.observableArrayList();
        col_imie.setCellValueFactory(new PropertyValueFactory<Person,String>("imie"));
        col_id.setCellValueFactory(new PropertyValueFactory<Person,Long>("id"));
        col_email.setCellValueFactory(new PropertyValueFactory<Person,String >("email"));
        col_wazne.setCellValueFactory(new PropertyValueFactory<Person,String>("wazne"));
        col_kod.setCellValueFactory(new PropertyValueFactory<Person,String>("kod"));
        col_zarobki.setCellValueFactory(new PropertyValueFactory<Person,String>("zarobki"));

        table_view.setItems(observableList);

        for (Employee e : find_all.select_all()
                ) {

            observableList.add(new Person(e.getId(),e.getEmail(),e.getKod(),e.getName(),e.getWazne(),e.getZarobki()));
        }

    }

    public Boolean check_in() {

        Pattern valid_email = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Pattern valid_kod = Pattern.compile("\\d{2}-\\d{3}", Pattern.CASE_INSENSITIVE);
        Pattern valid_imie = Pattern.compile("^(\\w+\\S+)$", Pattern.CASE_INSENSITIVE);
        Pattern wanze_valid = Pattern.compile("Bomba", Pattern.CASE_INSENSITIVE);
        Matcher matcher = valid_email.matcher(email.getText().trim());
        Matcher matcher1 = valid_kod.matcher(kod.getText().trim());
        Matcher matcher2 = valid_imie.matcher(imie.getText().trim());
        if (matcher.find() && matcher1.find() && matcher2.find()) {
            System.out.printf("Prawidłowo!");
            Matcher matcher3 = wanze_valid.matcher(wazne.getText().trim());
            if (matcher3.find()) {
                wazne.setText(matcher3.replaceAll("Zamianaaaaa"));
                return true;
            }

        } else {

            wazne.setText("Błędnie wprowadzone dane !");

            return false;
        }
        return true;
    }

    public void save() {
        try {
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("mydata");
            entityManager = entityManagerFactory.createEntityManager();
            employee = new Employee();

            employee.setName(imie.getText().trim());
            employee.setWazne(wazne.getText().trim());
            employee.setKod(kod.getText().trim());
            employee.setEmail(email.getText().trim());
            employee.setZarobki(zarobki.getText().trim());


            entityManager.getTransaction().begin();
            entityManager.persist(employee);
            entityManager.getTransaction().commit();


            entityManager.close();
            entityManagerFactory.close();
            System.out.println("Dodało !");


        } catch (Exception E) {
            System.out.println(E);
        }


    }




}

