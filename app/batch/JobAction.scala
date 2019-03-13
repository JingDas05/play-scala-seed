package batch

import batch.job.JobSelf
import com.google.inject.{Inject, Singleton}
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.support.ClassPathXmlApplicationContext

@Singleton
class JobAction @Inject() () {

  def test(): Unit = {
    println("annotationBatchContext")
    val annotationBatchContext: ApplicationContext = new AnnotationConfigApplicationContext("batch/job", "batch/conf")
    val job = annotationBatchContext.getBean("jobSelf").asInstanceOf[JobSelf]
    println(job.test())

    println("xmlBatchContext")
    val xmlBatchContext: ApplicationContext = new ClassPathXmlApplicationContext()
  }
}
