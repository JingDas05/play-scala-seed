package router

import controllers.RestfulController
import javax.inject.Inject
import play.api.routing.{Router, SimpleRouter}
//不引入 GET无法识别
import play.api.routing.sird._


//查询参数和路径参数
class RestfulRouter @Inject()(restfulController: RestfulController) extends SimpleRouter {
  override def routes: Router.Routes = {
    case GET(p"/$id" ? q"age=${int(age)}" & q"name=$name") => restfulController.getResultWith(id, name, age)
  }
}
