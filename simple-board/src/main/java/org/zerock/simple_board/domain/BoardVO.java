package org.zerock.simple_board.domain;

import lombok.Value;

@Value
public class BoardVO {

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
