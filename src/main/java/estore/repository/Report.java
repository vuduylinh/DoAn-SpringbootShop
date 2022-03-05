package estore.repository;

import java.io.Serializable;

public interface Report {
	Serializable getGroup();
	Long getCount();
	Double getSum();
	Double getMin();
	Double getMax();
	Double getAvg();
}
