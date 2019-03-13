package batch.job;

import org.springframework.context.annotation.Configuration;

@Configuration
public class JobSelf {

    public void test() {
        System.out.println("-----------------");
        System.out.println("injected");
        System.out.println("-----------------");
    }
}
