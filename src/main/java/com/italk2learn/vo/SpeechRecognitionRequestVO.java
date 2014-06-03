package com.italk2learn.vo;

import com.italk2learn.vo.RequestVO;


public class SpeechRecognitionRequestVO extends RequestVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private byte[] data;

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

}
