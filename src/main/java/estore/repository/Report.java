package estore.repository;

import java.io.Serializable;

public interface Report { // tao ra lớp interface để áp dụng qua ReportDAO.
	Serializable getGroup(); 
	// theo java Bean bỏ get, set , phần chữ còn lại đổi ký tự đầu tiên thành thường 'getGroup' --> 'group' đó chính là tên thuộc tính.
	Long getCount();
	Double getSum();
	Double getMin();
	Double getMax();
	Double getAvg();
}
