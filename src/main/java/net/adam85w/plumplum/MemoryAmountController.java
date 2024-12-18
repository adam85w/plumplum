package net.adam85w.plumplum;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/memory-amount")
public class MemoryAmountController {

    private final List<Long> listOfLongs = new ArrayList<>();

    @PostMapping("/exceed")
    public ResponseEntity<Void> exceedMemoryAmount() {
        var max = Runtime.getRuntime().maxMemory();
        for (long i = 0; i <= (max/8)+8; i++) {
            listOfLongs.add(i);
        }
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/fulfill")
    public ResponseEntity<Map<String, Map<String, Integer>>> fulfillMemoryAmount(@RequestParam(name = "amount", defaultValue = "1") long amount) {
        var before = obtainMemoryAmount();
        for (long i = 0; i <= (amount*1024*1024)/8; i++) {
            listOfLongs.add(i);
        }
        return ResponseEntity.ok(new HashMap<>() {{
            put("before", before);
            put("after", obtainMemoryAmount());
        }});
    }

    @GetMapping("/info")
    public ResponseEntity<Map<String, Integer>> info() {
        return ResponseEntity.ok(obtainMemoryAmount());
    }

    @DeleteMapping
    public ResponseEntity<Map<String, Map<String, Integer>>> clear(@RequestParam(name = "gc", defaultValue = "true") boolean gc) {
        var before = obtainMemoryAmount();
        listOfLongs.clear();
        if (gc) {
            Runtime.getRuntime().gc();
        }
        return ResponseEntity.ok(new HashMap<>() {{
            put("before", before);
            put("after", obtainMemoryAmount());
        }});
    }

    private Map<String, Integer> obtainMemoryAmount() {
        var max = Runtime.getRuntime().maxMemory();
        var free = Runtime.getRuntime().freeMemory();
        var used = max - free;
        return new HashMap<>() {{
            put("Max memory", (int) (max/(1024*1024)));
            put("Free memory", (int) (free/(1024*1024)));
            put("Used memory", (int) (used/(1024*1024)));
        }};
    }
}
