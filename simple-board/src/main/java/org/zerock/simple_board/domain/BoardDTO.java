package org.zerock.simple_board.domain;

import lombok.Data;

@Data
public class BoardDTO {
	
	int num;
	String author;
	String title;
	String content;
	int readcnt;
	String writeday;
	int repRoot;
	int repStep;
	int repIndent;

} // end class
