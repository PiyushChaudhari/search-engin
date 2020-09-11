package org.klevu.engin.vo;

public class SelectedDataVO {

	private String productId;
	private String productName;

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SelectedDataVO [productId=");
		builder.append(productId);
		builder.append(", productName=");
		builder.append(productName);
		builder.append("]");
		return builder.toString();
	}

}
