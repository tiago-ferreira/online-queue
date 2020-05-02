package br.com.mio.di.bao.onlinequeue.service;

import br.com.mio.di.bao.onlinequeue.model.queue.Queue;
import br.com.mio.di.bao.onlinequeue.repository.queue.QueueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QueueService {

    @Autowired
    private QueueRepository queueRepository;

    public List<Queue> getQueues() {
        return queueRepository.findAll();
    }
}
