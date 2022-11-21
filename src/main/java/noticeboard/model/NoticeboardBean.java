package noticeboard.model;

import java.sql.Timestamp;

import org.springframework.web.multipart.MultipartFile;

public class NoticeboardBean {
	
	private int no; 
	private String subject;
	private String writer;   
	private String open;
	private Timestamp reg_date; //작성일               
	private String content;      
	private String image;
	private int readcount; //조회수  
	
	private MultipartFile upload;
	
	public MultipartFile getUpload() {
		return upload;
	}
	public void setUpload(MultipartFile upload) {
		this.upload = upload;
		System.out.println("setUpload upload:"+upload);
		System.out.println("upload.getName:"+upload.getName());
		System.out.println("upload.getOriginalFilename:"+upload.getOriginalFilename());
		this.image = upload.getOriginalFilename();		
	}
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getOpen() {
		return open;
	}
	public void setOpen(String open) {
		this.open = open;
	}
	public Timestamp getReg_date() {
		return reg_date;
	}
	public void setReg_date(Timestamp reg_date) {
		this.reg_date = reg_date;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getReadcount() {
		return readcount;
	}
	public void setReadcount(int readcount) {
		this.readcount = readcount;
	}
	
	
}
