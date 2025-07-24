package micro.planner.todo.service;

import jakarta.transaction.Transactional;
import micro.planner.entity.Stat;
import micro.planner.todo.repo.StatRepository;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class StatService {

    private final StatRepository repository;

    public StatService(StatRepository repository) {
        this.repository = repository;
    }

    public Stat findStat(Long userId) {
        return repository.findByUserID(userId);
    }
}
