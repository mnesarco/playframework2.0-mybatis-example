package models

import org.mybatis.scala.mapping._

class Task {
  var id : Long = _
  var label : String = _
}

object TaskDAO {

  val all = new SelectList[Task] {
    def xsql = <xsql>SELECT * FROM task ORDER BY id</xsql>
  }

  val create = new Insert[String] {
    def xsql = <xsql>INSERT INTO task(label) VALUES (#{{label}})</xsql>
  }

  val delete = new Delete[Long] {
    def xsql = <xsql>DELETE FROM task WHERE id = #{{id}}</xsql>
  }
  
  val bind = Seq(all, create, delete)

}

object Task {

  import DB.context.managed
  
  def all() = managed(implicit s => TaskDAO.all())

  def create(label: String) = managed(implicit s => TaskDAO.create(label))

  def delete(id:Long) = managed(implicit s => TaskDAO.delete(id))

}

