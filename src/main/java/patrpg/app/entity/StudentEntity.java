package patrpg.app.entity;


import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "Student")
@Data
public class StudentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    Integer idCourse;

    Integer rut;

    String dv;

    String name;

    String lastName;

    Integer age;
}
