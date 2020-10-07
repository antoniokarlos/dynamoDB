package br.com.dynamo.test;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName="openbanking")
public class Model {

	private Integer mes_referencia;
	private String data_envio;
	private String company;
	private int ordenacao;
	private String personal;
	@DynamoDBHashKey
	public Integer getMes_referencia() {
		return mes_referencia;
	}
	public void setMes_referencia(Integer mes_referencia) {
		this.mes_referencia = mes_referencia;
	}
	@DynamoDBAttribute
	public String getData_envio() {
		return data_envio;
	}
	public void setData_envio(String data_envio) {
		this.data_envio = data_envio;
	}
	@DynamoDBAttribute
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	@DynamoDBAttribute
	public int getOrdenacao() {
		return ordenacao;
	}
	public void setOrdenacao(int ordenacao) {
		this.ordenacao = ordenacao;
	}
	@DynamoDBAttribute
	public String getPersonal() {
		return personal;
	}
	public void setPersonal(String personal) {
		this.personal = personal;
	}
	public Model(Integer mes_referencia, String data_envio, String company, int ordenacao, String personal) {
		super();
		this.mes_referencia = mes_referencia;
		this.data_envio = data_envio;
		this.company = company;
		this.ordenacao = ordenacao;
		this.personal = personal;
	}
	
	public Model() {
		
	}
}


