package com.italk2learn.speech.util;

import java.util.HashMap;

import com.italk2learn.vo.ASRInstanceVO;

public class EnginesMap {
	
	private static EnginesMap instance = null;
	
	private static final String LANGUAGE = "en_ux";
	private static final String SERVER = "localhost";
	private static final String MODEL = "base";
	private static final String SERVER_PART1 = "http://193.61.29.166:";
	private static final String SERVER_PART2 = "/italk2learnsm/speechRecognition";
	
	//JLF: Key=ServerID, Value=AsrInstanceVO 
	private HashMap<Integer, ASRInstanceVO> engines;
	
	protected EnginesMap() {
		engines= new HashMap<Integer, ASRInstanceVO>();
		//JLF: Making an engine of 30 instances. The summative evaluations will contain 30 students		
		int port=8081;
		for (int i=1;i<=30;i++){
			ASRInstanceVO server= new ASRInstanceVO();
			server.setAvailability(true);
			server.setInstance(i);
			server.setId(i);
			server.setLanguageCode(LANGUAGE);
			server.setServer(SERVER);
			server.setModel(MODEL);
			server.setUser("");
			server.setUrl(SERVER_PART1+port+SERVER_PART2);
			port++;
			engines.put(i,server);			
		}
		this.setEngines(engines);
	}
	
	public static EnginesMap getInstance() {
		if (instance == null) {
			instance = new EnginesMap();
		}
		return instance;
	}
	
	//JLF: It retrieves an available instance of the engine
	public ASRInstanceVO getInstanceEngineAvailable(String user){
		ASRInstanceVO aux= new ASRInstanceVO();
		for(Integer key: engines.keySet())
			if (engines.get(key).getAvailability()==true) {
				aux=engines.get(key);
				aux.setAvailability(false);
				aux.setUser(user);
				return aux;
			}
		return aux;
	}
	
	public String getUrlByUser(String user) {
		for(Integer key: engines.keySet())
			if (engines.get(key).getUser().contentEquals(user)) {
				return engines.get(key).getUrl();
			}
		return null;
	}
	
	public Integer getInstanceByUser(String user) {
		for(Integer key: engines.keySet())
			if (engines.get(key).getUser().contentEquals(user)) {
				return engines.get(key).getInstance();
			}
		return null;
	}
	
	//JLF It releases an instance when the connection is close or the session is destroyed by a specific user 
	public void releaseEngineInstance(String user){
		for(Integer key: engines.keySet())
			if (engines.get(key).getUser().contentEquals(user)) {
				engines.get(key).setAvailability(true);
				engines.get(key).setUser("");
			}
	}

	public HashMap<Integer, ASRInstanceVO> getEngines() {
		return engines;
	}

	public void setEngines(HashMap<Integer, ASRInstanceVO> engines) {
		this.engines = engines;
	}

	
}
