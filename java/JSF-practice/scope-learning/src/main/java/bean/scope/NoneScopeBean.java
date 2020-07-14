package bean.scope;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.NoneScoped;

/**
 * 所有視野中bean存活最短，壽命僅止於view的EL運算式"運算期間"，運算完後就不復存在了.
 * @author tszyi
 *
 */
@ManagedBean
@NoneScoped
public class NoneScopeBean implements Serializable{
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
