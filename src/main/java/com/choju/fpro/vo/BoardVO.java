package com.choju.fpro.vo;

import java.sql.Date;

import org.springframework.web.multipart.MultipartFile;

public class BoardVO {
	private int Board_Num;
	private String Board_Password;
	private String Board_Type;
	private String Board_Title;
	private String Board_Content;
	private MultipartFile Board_File;
	private int Board_Hit;
	private Date Board_Date;
	
	private String Board_FileName;

	@Override
	public String toString() {
		return "BoardVO [Board_Num=" + Board_Num + ", Board_Password=" + Board_Password + ", Board_Type=" + Board_Type
				+ ", Board_Title=" + Board_Title + ", Board_Content=" + Board_Content + ", Board_File=" + Board_File
				+ ", Board_Hit=" + Board_Hit + ", Board_Date=" + Board_Date + ", Board_FileName=" + Board_FileName
				+ "]";
	}
	
	//생성자
	public BoardVO() {
		
	}
	
	public BoardVO(int board_Num, String board_Password, String board_Type, String board_Title, String board_Content,
			MultipartFile board_File, int board_Hit, Date board_Date, String board_FileName) {
		Board_Num = board_Num;
		Board_Password = board_Password;
		Board_Type = board_Type;
		Board_Title = board_Title;
		Board_Content = board_Content;
		Board_File = board_File;
		Board_Hit = board_Hit;
		Board_Date = board_Date;
		Board_FileName = board_FileName;
	}

	public int getBoard_Num() {
		return Board_Num;
	}

	public void setBoard_Num(int board_Num) {
		Board_Num = board_Num;
	}

	public String getBoard_Password() {
		return Board_Password;
	}

	public void setBoard_Password(String board_Password) {
		Board_Password = board_Password;
	}

	public String getBoard_Type() {
		return Board_Type;
	}

	public void setBoard_Type(String board_Type) {
		Board_Type = board_Type;
	}

	public String getBoard_Title() {
		return Board_Title;
	}

	public void setBoard_Title(String board_Title) {
		Board_Title = board_Title;
	}

	public String getBoard_Content() {
		return Board_Content;
	}

	public void setBoard_Content(String board_Content) {
		Board_Content = board_Content;
	}

	public MultipartFile getBoard_File() {
		return Board_File;
	}

	public void setBoard_File(MultipartFile board_File) {
		Board_File = board_File;
	}

	public int getBoard_Hit() {
		return Board_Hit;
	}

	public void setBoard_Hit(int board_Hit) {
		Board_Hit = board_Hit;
	}

	public Date getBoard_Date() {
		return Board_Date;
	}

	public void setBoard_Date(Date board_Date) {
		Board_Date = board_Date;
	}

	public String getBoard_FileName() {
		return Board_FileName;
	}

	public void setBoard_FileName(String board_FileName) {
		Board_FileName = board_FileName;
	}


}
