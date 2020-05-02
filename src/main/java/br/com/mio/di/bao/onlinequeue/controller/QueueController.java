package br.com.mio.di.bao.onlinequeue.controller;

import br.com.mio.di.bao.onlinequeue.model.queue.Queue;
import br.com.mio.di.bao.onlinequeue.service.QueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/queue")
public class QueueController {

    @Autowired
    private QueueService queueService;

    @GetMapping
    public ResponseEntity<List<Queue>> status() {
        return new ResponseEntity<>(queueService.getQueues(), HttpStatus.OK);
    }
}
