package ashttp;

import ashttp.models.AsHttpProxy;
import ashttp.models.enumtypes.AsEnumMethod;

public class Program {
	public static void main(String[] args) {
		AsHttpRequest request = new AsHttpRequest();
		request.setUrl("http://www.localizaip.com.br/api/iplocation.php");
		request.setMethod(AsEnumMethod.GET);
		request.setProxy(new AsHttpProxy("HTTP", "23.91.96.251", 80));
		
		try {
			request.send();
			
			System.out.println(request.getResponse().getJson().get("ip"));
		} catch (AsHttpParserException e) {
			System.out.println(e.getMessage());
		}
	}
}
