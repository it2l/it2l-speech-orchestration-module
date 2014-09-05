package com.italk2learn.speech.util;

import java.util.HashMap;

import com.italk2learn.vo.ASRInstanceVO;

public class EnginesMap {
	
	public EnginesMap() {
		super();
		engines= new HashMap<Integer, ASRInstanceVO>();
		ASRInstanceVO server1= new ASRInstanceVO();
		server1.setAvailability(true);
		server1.setInstance(1);
		server1.setId(1);
		server1.setLanguageCode("en_ux");
		server1.setServer("localhost");
		server1.setModel("base");
		server1.setUser("");
		server1.setUrl("http://localhost:8081/italk2learnsm/speechRecognition");
		ASRInstanceVO server2= new ASRInstanceVO();
		server2.setAvailability(true);
		server2.setInstance(2);
		server2.setId(2);
		server2.setLanguageCode("en_ux");
		server2.setServer("localhost");
		server2.setModel("base");
		server2.setUser("");
		server2.setUrl("http://localhost:8082/italk2learnsm/speechRecognition");
		engines.put(1,server1);
		engines.put(2,server2);
		this.setEngines(engines);
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

	//JLF: Key=ServerID, Value=AsrInstanceVO 
	private HashMap<Integer, ASRInstanceVO> engines;
	
	
}
