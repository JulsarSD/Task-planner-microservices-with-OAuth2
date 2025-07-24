package micro.planner.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Getter
@Setter
@Entity
@Table(name = "activity", schema = "todo", catalog = "planner_todo")
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Activity {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "uuid", nullable = false, length = Integer.MAX_VALUE, updatable = false)
    private String uuid;

    @Column(name = "activated", nullable = false)
    @Basic
    @Convert(converter = org.hibernate.type.NumericBooleanConverter.class)
    private boolean activated;

    @Column(name="user_id")
    private Long userID;

}