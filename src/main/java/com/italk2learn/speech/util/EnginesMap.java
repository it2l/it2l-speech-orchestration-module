package com.italk2learn.speech.util;

import java.util.HashMap;

import com.italk2learn.vo.ASRInstanceVO;

public class EnginesMap {
	
	private static EnginesMap instance = null;
	
	//JLF: Key=ServerID, Value=AsrInstanceVO 
	private HashMap<Integer, ASRInstanceVO> engines;
	
	protected EnginesMap() {
		engines= new HashMap<Integer, ASRInstanceVO>();
		
		//Instance1		
		ASRInstanceVO server1= new ASRInstanceVO();
		server1.setAvailability(true);
		server1.setInstance(1);
		server1.setId(1);
		server1.setLanguageCode("en_ux");
		server1.setServer("localhost");
		server1.setModel("ittl1");
		server1.setUser("");
		server1.setUrl("http://localhost:8081/italk2learnsm/speechRecognition");
		
		//Instance2
		ASRInstanceVO server2= new ASRInstanceVO();
		server2.setAvailability(true);
		server2.setInstance(2);
		server2.setId(2);
		server2.setLanguageCode("en_ux");
		server2.setServer("localhost");
		server2.setModel("ittl1");
		server2.setUser("");
		server2.setUrl("http://localhost:8082/italk2learnsm/speechRecognition");
		
		//Instance3
		ASRInstanceVO server3= new ASRInstanceVO();
		server3.setAvailability(true);
		server3.setInstance(3);
		server3.setId(3);
		server3.setLanguageCode("en_ux");
		server3.setServer("localhost");
		server3.setModel("ittl1");
		server3.setUser("");
		server3.setUrl("http://localhost:8083/italk2learnsm/speechRecognition");
		
		//Instance4
		ASRInstanceVO server4= new ASRInstanceVO();
		server4.setAvailability(true);
		server4.setInstance(4);
		server4.setId(4);
		server4.setLanguageCode("en_ux");
		server4.setServer("localhost");
		server4.setModel("ittl1");
		server4.setUser("");
		server4.setUrl("http://localhost:8084/italk2learnsm/speechRecognition");
		
		//Instance5
		ASRInstanceVO server5= new ASRInstanceVO();
		server5.setAvailability(true);
		server5.setInstance(5);
		server5.setId(5);
		server5.setLanguageCode("en_ux");
		server5.setServer("localhost");
		server5.setModel("ittl1");
		server5.setUser("");
		server5.setUrl("http://localhost:8085/italk2learnsm/speechRecognition");
		
		//Instance6
		ASRInstanceVO server6= new ASRInstanceVO();
		server6.setAvailability(true);
		server6.setInstance(6);
		server6.setId(6);
		server6.setLanguageCode("en_ux");
		server6.setServer("localhost");
		server6.setModel("ittl1");
		server6.setUser("");
		server6.setUrl("http://localhost:8086/italk2learnsm/speechRecognition");
		
		//Instance7
		ASRInstanceVO server7= new ASRInstanceVO();
		server7.setAvailability(true);
		server7.setInstance(7);
		server7.setId(7);
		server7.setLanguageCode("en_ux");
		server7.setServer("localhost");
		server7.setModel("ittl1");
		server7.setUser("");
		server7.setUrl("http://localhost:8087/italk2learnsm/speechRecognition");

		//Instance8
		ASRInstanceVO server8= new ASRInstanceVO();
		server8.setAvailability(true);
		server8.setInstance(8);
		server8.setId(8);
		server8.setLanguageCode("en_ux");
		server8.setServer("localhost");
		server8.setModel("ittl1");
		server8.setUser("");
		server8.setUrl("http://localhost:8088/italk2learnsm/speechRecognition");
		
		//Instance9
		ASRInstanceVO server9= new ASRInstanceVO();
		server9.setAvailability(true);
		server9.setInstance(9);
		server9.setId(9);
		server9.setLanguageCode("en_ux");
		server9.setServer("localhost");
		server9.setModel("ittl1");
		server9.setUser("");
		server9.setUrl("http://localhost:8089/italk2learnsm/speechRecognition");

		//Instance10
		ASRInstanceVO server10= new ASRInstanceVO();
		server10.setAvailability(true);
		server10.setInstance(10);
		server10.setId(10);
		server10.setLanguageCode("en_ux");
		server10.setServer("localhost");
		server10.setModel("ittl1");
		server10.setUser("");
		server10.setUrl("http://localhost:8090/italk2learnsm/speechRecognition");

		//Instance11
		ASRInstanceVO server11= new ASRInstanceVO();
		server11.setAvailability(true);
		server11.setInstance(11);
		server11.setId(11);
		server11.setLanguageCode("en_ux");
		server11.setServer("localhost");
		server11.setModel("ittl1");
		server11.setUser("");
		server11.setUrl("http://localhost:8091/italk2learnsm/speechRecognition");
		
		//Instance12
		ASRInstanceVO server12= new ASRInstanceVO();
		server12.setAvailability(true);
		server12.setInstance(12);
		server12.setId(12);
		server12.setLanguageCode("en_ux");
		server12.setServer("localhost");
		server12.setModel("ittl1");
		server12.setUser("");
		server12.setUrl("http://localhost:8092/italk2learnsm/speechRecognition");
		
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
