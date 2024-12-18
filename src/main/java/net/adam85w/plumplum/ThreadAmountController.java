package net.adam85w.plumplum;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api/thread-amount")
public class ThreadAmountController {

    @GetMapping
    public ResponseEntity<Void> call() throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/info")
    public ResponseEntity<Integer> info() {
        return ResponseEntity.ok(Thread.activeCount());
    }
}
