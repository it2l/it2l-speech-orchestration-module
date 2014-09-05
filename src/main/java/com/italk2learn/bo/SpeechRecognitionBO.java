package com.italk2learn.bo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.italk2learn.bo.inter.ISpeechRecognitionBO;
import com.italk2learn.exception.ITalk2LearnException;
import com.italk2learn.speech.util.EnginesMap;
import com.italk2learn.vo.ASRInstanceVO;
import com.italk2learn.vo.SpeechRecognitionRequestVO;
import com.italk2learn.vo.SpeechRecognitionResponseVO;

@Service("speechRecognitionBO")
public class SpeechRecognitionBO implements ISpeechRecognitionBO {
	
	private static final Logger logger = LoggerFactory
			.getLogger(SpeechRecognitionBO.class);
	
	@Autowired
	private RestTemplate restTemplate;
	
	private EnginesMap em= new EnginesMap();
	private String url="";
	private Integer instanceNum;


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
			instanceNum=aux.getInstance();
			url=aux.getUrl();
			vars.put("user", request.getHeaderVO().getLoginUser());
			vars.put("instance", aux.getInstance().toString());
			//Call initEngineService of an available instance
			this.restTemplate.getForObject(aux.getUrl() + "/initEngine?user={user}&instance={instance}",String.class, vars);
			return res;
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return res;	
	}
	
	/*
	 * Call http service to close the engine and it receives the final transcription
	 */
	public SpeechRecognitionResponseVO closeASREngine(SpeechRecognitionRequestVO request) throws ITalk2LearnException{
		logger.debug("closeASREngine()--- Closing ASREngine");
		em.releaseEngineInstance(request.getHeaderVO().getLoginUser());
		SpeechRecognitionResponseVO res=new SpeechRecognitionResponseVO();
		request.setInstanceNum(instanceNum);
		try {
			String response=this.restTemplate.getForObject(url + "/closeEngine?instance={instance}",String.class, instanceNum.toString());
			res.setResponse(response);
			url="";
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
		request.setInstanceNum(instanceNum);
		try {
			return this.restTemplate.postForObject(url+"/sendData", request.getData(), SpeechRecognitionResponseVO.class);
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return res;
	}
	
	public EnginesMap getEm() {
		return em;
	}

	public void setEm(EnginesMap em) {
		this.em = em;
	}

}
