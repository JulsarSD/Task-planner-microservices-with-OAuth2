package micro.planner.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;


@Getter
@Setter
@Entity
@Table(name = "task", schema = "todo", catalog = "planner_todo")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Convert(converter = org.hibernate.type.NumericBooleanConverter.class)
    private Boolean completed;

    @Column(name = "task_date")
    private Date taskDate;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "priority_id", referencedColumnName = "id")
    private Priority priority;

    @Column(name="user_id")
    private String userID;

    @Override
    public String toString() {
        return title;
    }
}