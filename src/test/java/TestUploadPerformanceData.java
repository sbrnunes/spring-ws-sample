import java.io.File;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ws.soap.client.SoapFaultClientException;

import ut.eurodw.eu.edservices.UploadPerformanceDataResponse;

import com.bnpparibas.goe.service.edservice.UploadPerformanceDataService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
	"classpath*:web-services-application-context.xml",	
	"classpath*:edservices/web-services-edw-config.xml"	
})
public class TestUploadPerformanceData {

	
	
	@Autowired
	private UploadPerformanceDataService service;
	
	@Test
	public void testService() throws Exception {
		File zippedFile = new File("files/RMBSFRAA6718100120117_20111031_20121030.xml.gz");
		byte[] fileStream = FileUtils.readFileToByteArray(zippedFile);
		
		File signedDataFile = new File("files/RMBSFRAA6718100120117_20111031_20121030.xml.gz.asc");
		String signedData = FileUtils.readFileToString(signedDataFile, "UTF-8");
		
		
		try {
			Object result = service.execute("test", "2011-09-01", signedData, "2012-11-04", fileStream);
			
			if(result instanceof UploadPerformanceDataResponse) {
				Assert.assertNotNull(((UploadPerformanceDataResponse)result).getRequestId());			
			}
			
		} catch (SoapFaultClientException sfe) {
			sfe.printStackTrace();
		}
		
	}
}
