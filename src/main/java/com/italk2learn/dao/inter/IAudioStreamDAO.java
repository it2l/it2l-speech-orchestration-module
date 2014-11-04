package com.italk2learn.dao.inter;

import com.italk2learn.exception.ITalk2LearnException;

public interface IAudioStreamDAO {
	
	public Boolean saveByteArray(byte[] data,int idUser) throws ITalk2LearnException;


}
