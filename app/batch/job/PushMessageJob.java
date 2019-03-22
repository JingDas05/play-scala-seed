package batch.job;

import batch.job.step.DeviceIdProcessor;
import batch.job.step.DeviceIdRdeader;
import batch.job.step.DeviceIdWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@Configuration
public class PushMessageJob {


    @Resource
    private JobBuilderFactory jobBuilderFactory;

    @Resource
    private StepBuilderFactory stepBuilderFactory;

    @Bean("deviceIdJob")
    public Job deviceIdJob(@Qualifier("deviceIdStep") Step step) {
        return jobBuilderFactory.get("deviceIdJob")
                .start(step)
                .build();
    }


    @Bean
    public Step deviceIdStep(@Qualifier("deviceIdReader")ItemReader<String> itemReader ) {
        return stepBuilderFactory.get("step")
                .<String, String>chunk(1)
                .reader(itemReader)
                .processor(deviceIdProcessor())
                .writer(deviceIdWriter())
                .build();
    }

    @Bean
    @StepScope
    public ItemReader<String> deviceIdReader(@Value("#{jobParameters[messageId]}") String messageId) {
        return new DeviceIdRdeader();
    }

    @Bean
    public ItemProcessor<String, String> deviceIdProcessor() {
        return new DeviceIdProcessor();
    }

    @Bean
    public ItemWriter<String> deviceIdWriter() {
        return new DeviceIdWriter();
    }
}
