package batch

import batch.util.JobUtils
import com.google.inject.{Inject, Singleton}
import org.springframework.batch.core.{Job, JobExecution, JobParameter, JobParameters}
import org.springframework.batch.core.launch.JobLauncher
import org.springframework.context.annotation.AnnotationConfigApplicationContext

import scala.collection.JavaConverters

@Singleton
class JobAction @Inject()() {

  // 初始化Spring batch
  lazy val batchContext = new AnnotationConfigApplicationContext("batch/job")

  // Any 支持 STRING, DATETIME, LONG, DOUBLE 四种类型
  def startJob(params: Option[Map[String, Any]] = None): JobExecution = {

    val jobLauncher = batchContext.getBean(classOf[JobLauncher])
    val job = batchContext.getBean(classOf[Job])
    jobLauncher.run(job, getJobParameters(params))
  }

  private[this] def getJobParameters(params: Option[Map[String, Any]]): JobParameters = {

    val jobParameters: Option[java.util.Map[String, JobParameter]] =
      params.map(scalaMap => JavaConverters.mapAsJavaMap(scalaMap.map(each => (each._1, JobUtils.getJobParameter(each._2)))))
    new JobParameters(jobParameters.getOrElse(new java.util.HashMap[String, JobParameter]()))
  }
}
