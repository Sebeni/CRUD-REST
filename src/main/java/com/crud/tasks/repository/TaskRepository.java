package com.crud.tasks.repository;

import com.crud.tasks.domain.task.Task;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface TaskRepository extends CrudRepository<Task, Long> {
    @Override
    List<Task> findAll();
    
    @Override
    Optional<Task> findById(Long id);

    @Override
    void deleteById(Long taskId);
    
    @Override
    long count();
    
    @Query(value = "SELECT * FROM tasks ORDER BY id DESC LIMIT :taskLimit", nativeQuery = true)
    List<Task> getLatestTasks(@Param("taskLimit") int taskLimit);
    
}
