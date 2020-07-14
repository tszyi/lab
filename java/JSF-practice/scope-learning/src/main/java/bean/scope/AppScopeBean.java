package bean.scope;

import java.io.Serializable;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

/**
 * Application Scope的Bean的壽命與application能活長就有多長，不管是開新分頁、新視窗，甚至把瀏覽器關掉重啟、換另一個瀏覽器，bean依然存在.
 * @author tszyi
 *
 */
@ManagedBean
@ApplicationScoped
public class AppScopeBean implements Serializable{
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
