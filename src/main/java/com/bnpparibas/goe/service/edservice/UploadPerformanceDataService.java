package com.bnpparibas.goe.service.edservice;


public interface UploadPerformanceDataService {

	/**
	 * 
	 * @param edCodeText
	 * @param cutOffDateText
	 * @param signatureDataText
	 * @param uploadDateText
	 * @param fileStream
	 * @return
	 * @throws Exception
	 */
	public Object execute(String edCodeText, String cutOffDateText, String signatureDataText, 
			String uploadDateText, byte[] fileStream) throws Exception;
	
}
