package mrsa.tim018.dto;

public class MailsDTO {
	private String mailClient;
	private String mailRenter;

	public MailsDTO(String mailClient, String mailRenter) {
		super();
		this.mailClient = mailClient;
		this.mailRenter = mailRenter;
	}

	public MailsDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getMailClient() {
		return mailClient;
	}

	public void setMailClient(String mailClient) {
		this.mailClient = mailClient;
	}

	public String getMailRenter() {
		return mailRenter;
	}

	public void setMailRenter(String mailRenter) {
		this.mailRenter = mailRenter;
	}

}
