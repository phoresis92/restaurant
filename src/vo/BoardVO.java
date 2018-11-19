package vo;

public class BoardVO {

	private int seq;// board 레코드 컬럼 저장 변수
	private String title;
	private String contents;
	private String menu;
	private String writer;
	private int writer_seq;
	private String indate;
	private int viewcount;
	private int password;
	
	public BoardVO(int seq, String title, String contents, String menu, String writer, int writer_seq, String indate,
			int viewcount, int password) {
		super();
		this.seq = seq;
		this.title = title;
		this.contents = contents;
		this.menu = menu;
		this.writer = writer;
		this.writer_seq = writer_seq;
		this.indate = indate;
		this.viewcount = viewcount;
		this.password = password;
	}
	
	
	
	
	
	

}
