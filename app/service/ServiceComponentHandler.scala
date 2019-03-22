package service

import com.google.inject.{Inject, Singleton}

@Singleton
class ServiceComponentHandler @Inject()(serviceComponent: ServiceComponent) {

  def use(): Unit = {
    serviceComponent.testInject()
  }

}
