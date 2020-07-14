package bean.scope;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * View Scope的bean存活在一個瀏覽器的同一個"頁籤(tab)"，只要開新視窗、新頁籤，bean就不復存在.
 * @author tszyi
 *
 */
@ManagedBean
@ViewScoped
public class ViewScopeBean implements Serializable{
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
