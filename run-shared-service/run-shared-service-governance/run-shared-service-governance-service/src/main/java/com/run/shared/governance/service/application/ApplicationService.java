package com.run.shared.governance.service.application;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;

@RestController
@RequestMapping("/governance")
public class ApplicationService {

	@Autowired
	private EurekaClient	eurekaClient;

	@Autowired
	private DiscoveryClient	discoveryClient;

	private RestTemplate	restTemplate	= new RestTemplate();



	/**
	 * 
	 * 获取所有应用
	 *
	 * @return
	 */
	@CrossOrigin(origins = "*")
	@RequestMapping("/getapplications")
	public List<Application> getRegistered() {
		return eurekaClient.getApplications().getRegisteredApplications();
	}



	@CrossOrigin(origins = "*")
	@RequestMapping("/getservices")
	public List<String> getServices() {
		return discoveryClient.getServices();
	}



	@CrossOrigin(origins = "*")
	@RequestMapping("/getapis")
	public JSONObject getApis() {
		List<Application> registeredApplications = eurekaClient.getApplications().getRegisteredApplications();
		JSONObject apis = new JSONObject();
		for (Application application : registeredApplications) {
			List<InstanceInfo> instances = application.getInstances();
			for (InstanceInfo instanceInfo : instances) {
				StringBuilder sb = new StringBuilder("http://");
				sb.append(instanceInfo.getIPAddr() + ":");
				sb.append(instanceInfo.getPort());
				sb.append("/mappings");
				String str = restTemplate.getForObject(sb.toString(), String.class);
				JSONObject mappings = JSON.parseObject(str);
				for (Map.Entry<String, Object> entry : mappings.entrySet()) {
					JSONObject json = JSONObject.parseObject(entry.getValue().toString());
					if (json.containsKey("bean")
							&& json.getString("bean").equalsIgnoreCase("requestMappingHandlerMapping")) {
						if (!entry.getKey().contains("[/error]")) {
							String strKey = entry.getKey().replace("{", "").replace("}", "");
							apis.put(strKey, entry.getValue());
						}
					}

				}
			}
		}
		return apis;
	}

}
