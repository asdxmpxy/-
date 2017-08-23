package cn.e3mall.common.pojo;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Dxm
 * @param <T>
 *
 */
public class DatagridResult<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer total;
	
	private List<T> rows;

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}
	
	

}
