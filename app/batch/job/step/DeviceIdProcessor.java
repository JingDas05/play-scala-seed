package batch.job.step;

import org.springframework.batch.item.ItemProcessor;

public class DeviceIdProcessor implements ItemProcessor<String, String> {
    @Override
    public String process(String item) throws Exception {
        return item;
    }
}
