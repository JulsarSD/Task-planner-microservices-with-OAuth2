package micro.planner.todo.service;

import jakarta.transaction.Transactional;
import micro.planner.entity.Category;
import micro.planner.entity.Priority;
import micro.planner.entity.Task;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
@Transactional
public class TestDataService {

    private final TaskService taskService;

    private final CategoryService categoryService;

    private final PriorityService priorityService;

    public TestDataService(TaskService taskService, CategoryService categoryService, PriorityService priorityService) {
        this.taskService = taskService;
        this.categoryService = categoryService;
        this.priorityService = priorityService;
    }

    @KafkaListener(topics = "java-test")
    public void listenerKafka(String userId){
        System.out.println("new userId = " + userId);
        initTestData(userId);
    }

    public void initTestData(String userId){
        Priority prior1 = new Priority();
        prior1.setColor("#fff");
        prior1.setTitle("Высокий");
        prior1.setUserID(userId);

        Priority prior2 = new Priority();
        prior2.setColor("#ffe");
        prior2.setTitle("Низкий");
        prior2.setUserID(userId);

        priorityService.add(prior1);
        priorityService.add(prior2);

        Category cat1 = new Category();
        cat1.setTitle("Работа");
        cat1.setUserID(userId);

        Category cat2 = new Category();
        cat2.setTitle("Дом");
        cat2.setUserID(userId);

        categoryService.add(cat1);
        categoryService.add(cat2);

        Date tomorrow = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(tomorrow);
        c.add(Calendar.DATE, 1);
        tomorrow = c.getTime();

        Date oneWeek;
        Calendar c2 = Calendar.getInstance();
        c2.setTime(tomorrow);
        c2.add(Calendar.DATE, 7);
        oneWeek = c2.getTime();

        Task task1 = new Task();
        task1.setTitle("Сделать отчет");
        task1.setCategory(cat1);
        task1.setPriority(prior2);
        task1.setCompleted(false);
        task1.setTaskDate(oneWeek);
        task1.setUserID(userId);

        Task task2 = new Task();
        task2.setTitle("Покормить кота");
        task2.setCategory(cat2);
        task2.setPriority(prior1);
        task2.setCompleted(true);
        task2.setTaskDate(tomorrow);
        task2.setUserID(userId);

        taskService.add(task1);
        taskService.add(task2);
    }
}
