package micro.planner.todo.repo;

import micro.planner.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("SELECT c FROM Category c where " +
            "(:title is null or :title='' " +
            " or lower(c.title) like lower(concat('%', :title, '%'))) " +
            " and c.userID=:userId " +
            " order by c.title asc ")
    List<Category> findByTitle(@Param("title") String title, @Param("userId") String userId);

    List<Category> findByUserIDOrderByTitleAsc(String userId);
}
