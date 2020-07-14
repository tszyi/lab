package bean.scope;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * Session Scope的bean存活在同一個瀏覽器(HTTP裡的Session)，在瀏覽器不關閉的情況下，不管開(關)多少新頁籤、新視窗，或者應用程式被重載也依然存在.
 * @author tszyi
 *
 */
@ManagedBean
@SessionScoped
public class SessScopeBean implements Serializable{
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
