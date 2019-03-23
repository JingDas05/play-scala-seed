package controllers

import com.google.inject.Inject
//import io.growing.micros.middleware.cache.{Cache, CacheConfig, RedisCaches}
// 引入 seconds 的隐式转换
import scala.concurrent.duration._

//class FileController @Inject()(redisCaches: RedisCaches) {
class FileController @Inject()() {


//  val redisCache: Cache[String] = redisCaches.newRedisCache[String](
//    CacheConfig("prefix", Some(10.seconds.toSeconds)),
//    "namespace")

}
