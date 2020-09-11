package org.klevu.engin.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "selected_data")
public class SelectedData {

	@Id
	@Field(value = "id")
	private String id;

	@Field(value = "product_id")
	private String productId;

	@Field(value = "ip_address")
	private String ipAddress;

	@Field(value = "product_detail")
	private String productDetail;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getProductDetail() {
		return productDetail;
	}

	public void setProductDetail(String productDetail) {
		this.productDetail = productDetail;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SelectedData [id=");
		builder.append(id);
		builder.append(", productId=");
		builder.append(productId);
		builder.append(", ipAddress=");
		builder.append(ipAddress);
		builder.append(", productDetail=");
		builder.append(productDetail);
		builder.append("]");
		return builder.toString();
	}

}
