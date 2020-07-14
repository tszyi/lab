package bean.scope;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 * 若不使用任何Scope Annotation，則預設為Request Scope.
 * 視野中次短的(最短的是NoneScoped，只活在EL運算式結束後就被銷毀)，bean存活在HTTP的request至response間，response完後就不復存在.
 * @author tszyi
 *
 */
@ManagedBean
@RequestScoped
public class ReqScopeBean implements Serializable{
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private int num = 1;

  public int getNum() {
    return this.num;
  }

  public void incNum() {
    this.num++;
  }
}
