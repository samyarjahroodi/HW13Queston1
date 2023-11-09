package ir.maktab.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@ToString

public class PersonSummary {
    private Integer id;
    private String firstName;
    private String lastName;
    private Integer age;
    private Date birthDate;

    public PersonSummary(Person person){
        this.id = person.getId();
        this.firstName = person.getFirstName();
        this.lastName = person.getLastName();
        this.birthDate = convertBirthdate(person.getBirthDate());
        this.age = calculateAge(person.getBirthDate());
    }

    private Integer calculateAge(String birthDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            return 2023-( dateFormat.parse(birthDate).getYear()+1900);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private Date convertBirthdate(String birthDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            return dateFormat.parse(birthDate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

}
