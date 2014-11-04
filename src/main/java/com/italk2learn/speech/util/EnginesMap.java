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
		
		//Instance1		
		ASRInstanceVO server1= new ASRInstanceVO();
		server1.setAvailability(true);
		server1.setInstance(1);
		server1.setId(1);
		server1.setLanguageCode(LANGUAGE);
		server1.setServer(SERVER);
		server1.setModel(MODEL);
		server1.setUser("");
		server1.setUrl(SERVER_PART1+"8081"+SERVER_PART2);
		
		//Instance2
		ASRInstanceVO server2= new ASRInstanceVO();
		server2.setAvailability(true);
		server2.setInstance(2);
		server2.setId(2);
		server2.setLanguageCode(LANGUAGE);
		server2.setServer(SERVER);
		server2.setModel(MODEL);
		server2.setUser("");
		server2.setUrl(SERVER_PART1+"8082"+SERVER_PART2);
		
		//Instance3
		ASRInstanceVO server3= new ASRInstanceVO();
		server3.setAvailability(true);
		server3.setInstance(3);
		server3.setId(3);
		server3.setLanguageCode(LANGUAGE);
		server3.setServer(SERVER);
		server3.setModel(MODEL);
		server3.setUser("");
		server3.setUrl(SERVER_PART1+"8083"+SERVER_PART2);
		
		//Instance4
		ASRInstanceVO server4= new ASRInstanceVO();
		server4.setAvailability(true);
		server4.setInstance(4);
		server4.setId(4);
		server4.setLanguageCode(LANGUAGE);
		server4.setServer(SERVER);
		server4.setModel(MODEL);
		server4.setUser("");
		server4.setUrl(SERVER_PART1+"8084"+SERVER_PART2);
		
		//Instance5
		ASRInstanceVO server5= new ASRInstanceVO();
		server5.setAvailability(true);
		server5.setInstance(5);
		server5.setId(5);
		server5.setLanguageCode(LANGUAGE);
		server5.setServer(SERVER);
		server5.setModel(MODEL);
		server5.setUser("");
		server5.setUrl(SERVER_PART1+"8085"+SERVER_PART2);
		
		//Instance6
		ASRInstanceVO server6= new ASRInstanceVO();
		server6.setAvailability(true);
		server6.setInstance(6);
		server6.setId(6);
		server6.setLanguageCode(LANGUAGE);
		server6.setServer(SERVER);
		server6.setModel(MODEL);
		server6.setUser("");
		server6.setUrl(SERVER_PART1+"8086"+SERVER_PART2);
		
		//Instance7
		ASRInstanceVO server7= new ASRInstanceVO();
		server7.setAvailability(true);
		server7.setInstance(7);
		server7.setId(7);
		server7.setLanguageCode(LANGUAGE);
		server7.setServer(SERVER);
		server7.setModel(MODEL);
		server7.setUser("");
		server7.setUrl(SERVER_PART1+"8087"+SERVER_PART2);

		//Instance8
		ASRInstanceVO server8= new ASRInstanceVO();
		server8.setAvailability(true);
		server8.setInstance(8);
		server8.setId(8);
		server8.setLanguageCode(LANGUAGE);
		server8.setServer(SERVER);
		server8.setModel(MODEL);
		server8.setUser("");
		server8.setUrl(SERVER_PART1+"8088"+SERVER_PART2);
		
		//Instance9
		ASRInstanceVO server9= new ASRInstanceVO();
		server9.setAvailability(true);
		server9.setInstance(9);
		server9.setId(9);
		server9.setLanguageCode(LANGUAGE);
		server9.setServer(SERVER);
		server9.setModel(MODEL);
		server9.setUser("");
		server9.setUrl(SERVER_PART1+"8089"+SERVER_PART2);

		//Instance10
		ASRInstanceVO server10= new ASRInstanceVO();
		server10.setAvailability(true);
		server10.setInstance(10);
		server10.setId(10);
		server10.setLanguageCode(LANGUAGE);
		server10.setServer(SERVER);
		server10.setModel(MODEL);
		server10.setUser("");
		server10.setUrl(SERVER_PART1+"8090"+SERVER_PART2);

		//Instance11
		ASRInstanceVO server11= new ASRInstanceVO();
		server11.setAvailability(true);
		server11.setInstance(11);
		server11.setId(11);
		server11.setLanguageCode(LANGUAGE);
		server11.setServer(SERVER);
		server11.setModel(MODEL);
		server11.setUser("");
		server11.setUrl(SERVER_PART1+"8091"+SERVER_PART2);
		
		//Instance12
		ASRInstanceVO server12= new ASRInstanceVO();
		server12.setAvailability(true);
		server12.setInstance(12);
		server12.setId(12);
		server12.setLanguageCode(LANGUAGE);
		server12.setServer(SERVER);
		server12.setModel(MODEL);
		server12.setUser("");
		server12.setUrl(SERVER_PART1+"8092"+SERVER_PART2);
		
		//Storing instances
		engines.put(1,server1);
		engines.put(2,server2);
		engines.put(3,server3);
		engines.put(4,server4);
		engines.put(5,server5);
		engines.put(6,server6);
		engines.put(7,server7);
		engines.put(8,server8);
		engines.put(9,server9);
		engines.put(10,server10);
		engines.put(11,server11);
		engines.put(12,server12);
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
