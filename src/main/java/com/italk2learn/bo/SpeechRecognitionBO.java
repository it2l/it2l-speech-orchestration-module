package com.italk2learn.bo;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.italk2learn.bo.inter.ISpeechRecognitionBO;
import com.italk2learn.dao.inter.IAudioStreamDAO;
import com.italk2learn.exception.ITalk2LearnException;
import com.italk2learn.speech.util.EnginesMap;
import com.italk2learn.vo.ASRInstanceVO;
import com.italk2learn.vo.SpeechRecognitionRequestVO;
import com.italk2learn.vo.SpeechRecognitionResponseVO;

@Service("speechRecognitionBO")
@Transactional(rollbackFor = { ITalk2LearnException.class, ITalk2LearnException.class })
public class SpeechRecognitionBO implements ISpeechRecognitionBO {
	
	private static final Logger logger = LoggerFactory
			.getLogger(SpeechRecognitionBO.class);
	
	@Autowired
	private RestTemplate restTemplate;
	
	public IAudioStreamDAO audioStreamDAO;
	
	private EnginesMap em= EnginesMap.getInstance();
	
	
	@Autowired
	public SpeechRecognitionBO(IAudioStreamDAO audioStreamDAO) {
		this.audioStreamDAO = audioStreamDAO;
	}

	/*
	 * Call http service to init the ASR Engine
	 */
	public SpeechRecognitionResponseVO initASREngine(SpeechRecognitionRequestVO request) throws ITalk2LearnException{
		logger.debug("initASREngine()--- Init ASREngine");
		SpeechRecognitionResponseVO res=new SpeechRecognitionResponseVO();
		try {
			Map<String, String> vars = new HashMap<String, String>();
			//Get an available instance
			ASRInstanceVO aux= em.getInstanceEngineAvailable(request.getHeaderVO().getLoginUser());
			System.out.println("Speech module available for user: "+request.getHeaderVO().getLoginUser()+" with instance: "+aux.getInstance().toString() );
			vars.put("user", request.getHeaderVO().getLoginUser());
			vars.put("instance", aux.getInstance().toString());
			vars.put("server", aux.getServer());
			vars.put("language", aux.getLanguageCode());
			vars.put("model", aux.getModel());
			//Call initEngineService of an available instance
			this.restTemplate.getForObject(aux.getUrl() + "/initEngine?user={user}&instance={instance}&server={server}&language={language}&model={model}",String.class, vars);
			return res;
		} catch (Exception e) {
			em.releaseEngineInstance(request.getHeaderVO().getLoginUser());
			logger.error(e.toString());
		}
		return res;	
	}
	
	/*
	 * Call http service to close the engine and it receives the final transcription
	 */
	public SpeechRecognitionResponseVO closeASREngine(SpeechRecognitionRequestVO request) throws ITalk2LearnException{
		logger.debug("closeASREngine()--- Closing ASREngine");
		SpeechRecognitionResponseVO res=new SpeechRecognitionResponseVO();
		String url=em.getUrlByUser(request.getHeaderVO().getLoginUser());
		Integer instanceNum=em.getInstanceByUser(request.getHeaderVO().getLoginUser());
		if (instanceNum==null){
			System.out.println("Instance already released by this user or never used");
			logger.info("closeASREngine()--- Instance already released by this user or never used");
			return res;
		}
		em.releaseEngineInstance(request.getHeaderVO().getLoginUser());
		request.setInstance(instanceNum);
		try {
			System.out.println("Speech module released by user: "+request.getHeaderVO().getLoginUser()+" with instance: "+instanceNum.toString());
			String response=this.restTemplate.getForObject(url + "/closeEngine?instance={instance}",String.class, instanceNum.toString());
			res.setResponse(response);
			return res;
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return res;
	}
	
	/*
	 * Call http service to send audio chunks
	 */
	public SpeechRecognitionResponseVO sendNewAudioChunk(SpeechRecognitionRequestVO request) throws ITalk2LearnException{
		logger.debug("sendNewAudioChunk()--- Sending new audio chunk");
		SpeechRecognitionResponseVO res=new SpeechRecognitionResponseVO();
		request.setInstance(em.getInstanceByUser(request.getHeaderVO().getLoginUser()));
		try {
			res=this.restTemplate.postForObject(em.getUrlByUser(request.getHeaderVO().getLoginUser())+"/sendData", request, SpeechRecognitionResponseVO.class);
		} catch (Exception e) {
			em.releaseEngineInstance(request.getHeaderVO().getLoginUser());
			logger.error(e.toString());
		}
		return res;
	}
	
	public SpeechRecognitionResponseVO saveByteArray(
			SpeechRecognitionRequestVO request) throws ITalk2LearnException {
		SpeechRecognitionResponseVO response= new SpeechRecognitionResponseVO();
		try {
			getAudioStreamDAO().saveByteArray(request.getFinalByteArray(), request.getHeaderVO().getIdUser());
		}
		catch (Exception e){
			logger.error("saveByteArray()--- "+e.toString());
		}
		return response;
	}
	
	public EnginesMap getEm() {
		return em;
	}

	public void setEm(EnginesMap em) {
		this.em = em;
	}
	
	public IAudioStreamDAO getAudioStreamDAO() {
		return audioStreamDAO;
	}

	public void setAudioStreamDAO(IAudioStreamDAO audioStreamDAO) {
		this.audioStreamDAO = audioStreamDAO;
	}


}
