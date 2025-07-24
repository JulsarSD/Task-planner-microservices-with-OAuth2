package micro.planner.todo.service;

import jakarta.transaction.Transactional;
import micro.planner.entity.Priority;
import micro.planner.todo.repo.PriorityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class PriorityService {

    private final PriorityRepository repository;

    public PriorityService(PriorityRepository repository) {
        this.repository = repository;
    }

    public Priority add(Priority priority) {
        return repository.save(priority);
    }

    public Priority update(Priority priority) {
        return repository.save(priority);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public List<Priority> findByTitle(String text, Long userId){
        return repository.findByTitle(text, userId);
    }

    public Priority findById(Long id) {
        return repository.findById(id).get();
    }

    public List<Priority> findAll(Long userId) {
        return repository.findByUserIDOrderByTitleAsc(userId);
    }
}
