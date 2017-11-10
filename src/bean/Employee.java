package bean;

import javax.annotation.Generated;
import javax.persistence.*;

/**
 * Created by lenovo on 2017/5/19.
 */
@Entity
@Table(name="employee")
public class Employee {
      @Id
      @Column(name="id")
     private  int id;
      @Column (name="salary")
     private  int salary;
      @Column (name="first_name")
     private  String firstName;
      @Column (name="last_name")
     private  String lastName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
