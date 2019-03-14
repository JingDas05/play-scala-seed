package batch.job.step;

import org.springframework.batch.item.ItemWriter;

import java.util.List;

public class DeviceIdWriter implements ItemWriter<String> {
    @Override
    public void write(List<? extends String> items) throws Exception {
        for (String each : items) {
            System.out.println(each);
        }
    }
}
