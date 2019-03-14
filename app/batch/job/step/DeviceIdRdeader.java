package batch.job.step;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

public class DeviceIdRdeader implements ItemReader<String> {

    private int limit = 10;
    private int current = 0;

    @Override
    public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        if (current < limit) {
            current++;
            return "-----in-----" + System.currentTimeMillis();
        }
        return null;
    }
}
