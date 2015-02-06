package com.italk2learn.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.hibernate.dto.Audiostream;
import com.hibernate.dto.User;
import com.italk2learn.dao.inter.IAudioStreamDAO;
import com.italk2learn.exception.ITalk2LearnException;

public class AudioStreamDAO extends HibernateDaoSupport implements IAudioStreamDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public final Session getITalk2LearnSession() {
		return this.getSessionFactory().getCurrentSession();
	}

	public Boolean saveByteArray(byte[] data,int idUser) throws ITalk2LearnException {
		final Session session = this.getITalk2LearnSession();
		try{
			Audiostream as=new Audiostream();
			User us= (User) session.load(User.class, idUser);
			as.setUser(us);
			as.setBytearray(data);	
			session.saveOrUpdate(as);
			return true;
		}catch (Exception e){
			e.printStackTrace();
			throw new ITalk2LearnException(e);
		}
	}

}
