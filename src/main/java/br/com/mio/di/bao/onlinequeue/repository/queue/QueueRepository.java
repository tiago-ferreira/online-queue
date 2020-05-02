package br.com.mio.di.bao.onlinequeue.repository.queue;

import br.com.mio.di.bao.onlinequeue.model.queue.Queue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QueueRepository extends JpaRepository<Queue, Integer> {
}
