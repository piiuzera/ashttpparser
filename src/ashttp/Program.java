package ashttp;

import ashttp.models.enumtypes.AsEnumMethod;

public class Program {
	public static void main(String[] args) {
		AsHttpRequest request = new AsHttpRequest();
		request.setUrl("http://amarocorp.com.br/");
		request.setMethod(AsEnumMethod.GET);		
		try {
			request.send();
			
			System.out.println(request.getResponse().getDocument());
		} catch (AsHttpParserException e) {
			System.out.println(e.getMessage());
		}
	}
}
