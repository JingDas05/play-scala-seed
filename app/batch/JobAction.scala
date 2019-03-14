package batch

import com.google.inject.{Inject, Singleton}
import org.springframework.batch.core.{Job, JobParameters}
import org.springframework.batch.core.launch.JobLauncher
import org.springframework.context.annotation.AnnotationConfigApplicationContext

@Singleton
class JobAction @Inject()() {

  // 初始化Spring容器，并且注册batchConfiguration
  lazy val batchContext = new AnnotationConfigApplicationContext("batch/job")

  def start(): Unit = {
    val jobLauncher = batchContext.getBean(classOf[JobLauncher])
    val job = batchContext.getBean(classOf[Job])
    val jobParameters = new JobParameters()
    jobLauncher.run(job, jobParameters)
  }
}
