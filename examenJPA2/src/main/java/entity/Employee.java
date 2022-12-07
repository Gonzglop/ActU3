package entity;

import javax.persistence.*;

@Entity
@NamedQuery(name = "Employee.byDept",query = "select e from Employee e where e.departmentByDept.name= ?1")
public class Employee {
    private int id;
    private String firstName;
    private String lastName;
    private Department departmentByDept;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "firstName")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "lastName")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Employee employee = (Employee) o;

        if (id != employee.id) return false;
        if (firstName != null ? !firstName.equals(employee.firstName) : employee.firstName != null) return false;
        if (lastName != null ? !lastName.equals(employee.lastName) : employee.lastName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", departmentByDept=" + departmentByDept +
                '}';
    }

    @ManyToOne
    @JoinColumn(name = "dept", referencedColumnName = "id")
    public Department getDepartmentByDept() {
        return departmentByDept;
    }

    public void setDepartmentByDept(Department departmentByDept) {
        this.departmentByDept = departmentByDept;
    }
}
