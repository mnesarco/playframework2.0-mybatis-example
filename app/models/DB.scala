package models

import org.mybatis.scala.config._
import play.api.Play.current
import play.api.db.DB._

object DB {

  val conf = Configuration(Environment("default", new ManagedTransactionFactory(), getDataSource()))
  conf ++= models.TaskDAO
  val context = conf.createPersistenceContext

}

