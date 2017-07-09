package com.jumoassess.aggregator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jumoassess.aggregator.api.DataAggregator;

public class DataAggregatorImpl implements DataAggregator {
	
	@Override
	public List<CsvFileMapperBean> aggregateData(List<CsvFileMapperBean> networkLoansMapper) {
		List<CsvFileMapperBean> loans = new ArrayList<CsvFileMapperBean>();
		
		Map<String, Totals> aggregatedData = calculateTotalPerNetworkProductDateAggregation(networkLoansMapper);
		
		for (String key : aggregatedData.keySet()) {
			CsvFileMapperBean bean = new CsvFileMapperBean();
			bean.setAmount(aggregatedData.get(key).getTotalAmount());
			bean.setCount(aggregatedData.get(key).getTotalCount());
			
			String[] tuple = key.split(", ");
			
			bean.setNetwork(tuple[0]);
			bean.setProduct(tuple[1]);
			bean.setDate(tuple[2]);
			
			loans.add(bean);
		}
		return loans;
	}
	
	private Map<String, Totals> calculateTotalPerNetworkProductDateAggregation(List<CsvFileMapperBean> networkLoansMapper) {
		Map<String, Totals> result = new HashMap<>();
		
		for (CsvFileMapperBean bean : networkLoansMapper) {
			String key = bean.getNetwork() + ", " + bean.getProduct() + ", " + getMonth(bean.getDate());
			if (result.containsKey(key)) {
				Totals totals = result.get(key);
				BigDecimal currentAmount = totals.getTotalAmount();
				BigDecimal additionalAmount = bean.getAmount();
				totals.setTotalAmount(currentAmount.add(additionalAmount));
				totals.setTotalCount(totals.getTotalCount() + 1);
				result.put(key, totals);
			} else {
				Totals totals = new TotalsImpl();
				totals.setTotalAmount(bean.getAmount());
				totals.setTotalCount(1);
				result.put(key, totals);
			}
		}
		
		return result;
	}
	
	private String getMonth(String date) throws UnsupportedOperationException {
		String[] month = date.split("-");
		return month[1];
	}
	
	
	private interface Totals {
		
		void setTotalAmount(BigDecimal amount);
		BigDecimal getTotalAmount();
		
		void setTotalCount(int count);
		int getTotalCount();
		
	}
	
	private class TotalsImpl implements Totals {
		private BigDecimal amount;
		private int count;
		
		@Override
		public void setTotalAmount(BigDecimal amount) {
			this.amount = amount;	
		}

		@Override
		public BigDecimal getTotalAmount() {
			return amount;
		}

		@Override
		public void setTotalCount(final int count) {
			this.count = count;
		}

		@Override
		public int getTotalCount() {
			return count;
		}
		
	}

}