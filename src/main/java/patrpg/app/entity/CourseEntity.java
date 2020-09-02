package patrpg.app.entity;


import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "Course")
@Data
public class CourseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String code;
}
