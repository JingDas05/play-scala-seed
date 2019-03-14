package batch.job;

import batch.job.step.DeviceIdProcessor;
import batch.job.step.DeviceIdRdeader;
import batch.job.step.DeviceIdWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@Configuration
public class PushMessageJob {


    @Resource
    private JobBuilderFactory jobBuilderFactory;

    @Resource
    private StepBuilderFactory stepBuilderFactory;

    @Bean()
    public Job deviceIdJob() {
        return jobBuilderFactory.get("deviceIdJob")
                .start(step())
                .build();
    }


    private Step step() {
        return stepBuilderFactory.get("step")
                .<String, String>chunk(1)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }



    private ItemReader<String> reader() {
        return new DeviceIdRdeader();
    }

    private ItemProcessor<String, String> processor() {
        return new DeviceIdProcessor();
    }

    private ItemWriter<String> writer() {
        return new DeviceIdWriter();
    }
}
