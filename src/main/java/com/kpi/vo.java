package com.kpi;

public class vo {

	private String remote_addr;// ��¼�ͻ��˵�ip��ַ
	private String remote_user;// ��¼�ͻ����û�����,��������"-"
	private String time_local;// ��¼����ʱ����ʱ��
	private String request;// ��¼�����url��httpЭ��
	private String status;// ��¼����״̬���ɹ���200
	private String body_bytes_sent;// ��¼���͸��ͻ����ļ��������ݴ�С
	private String http_referer;// ������¼���Ǹ�ҳ�����ӷ��ʹ�����
	private String http_user_agent;// ��¼�ͻ�������������Ϣ
	
	private boolean valid = true;// �ж������Ƿ�Ϸ�

	public String getRemote_addr() {
		return remote_addr;
	}

	public void setRemote_addr(String remote_addr) {
		this.remote_addr = remote_addr;
	}

	public String getRemote_user() {
		return remote_user;
	}

	public void setRemote_user(String remote_user) {
		this.remote_user = remote_user;
	}

	public String getTime_local() {
		return time_local;
	}

	public void setTime_local(String time) {
		this.time_local = time;
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getBody_bytes_sent() {
		return body_bytes_sent;
	}

	public void setBody_bytes_sent(String body_bytes_sent) {
		this.body_bytes_sent = body_bytes_sent;
	}

	public String getHttp_referer() {
		return http_referer;
	}

	public void setHttp_referer(String http_referer) {
		this.http_referer = http_referer;
	}

	public String getHttp_user_agent() {
		return http_user_agent;
	}

	public void setHttp_user_agent(String http_user_agent) {
		this.http_user_agent = http_user_agent;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public String toString() {
		return ("�����ߵ�ַ:" + this.getRemote_addr() + "\n�����豸:" + this.getRemote_user() + "\n����ʱ��:" + this.getTime_local()
				+ "\n����ҳ��:" + this.getRequest() + "\n���ʽ��:" + this.getStatus() + "\n������Դ:" + this.getHttp_referer()
				+ "\n�������Ϣ:" + this.getHttp_user_agent() + "\n���͸��ͻ����ļ��������ݴ�С:" + this.getBody_bytes_sent());
	}

}
