package ashttp;

import ashttp.models.AsHttpAuthorization;
import ashttp.models.enumtypes.AsEnumMethod;

public class Program {
	public static void main(String[] args) {
		AsHttpRequest request = new AsHttpRequest();
		request.setUrl("http://sapdsf.grupomag.corp:8000/gerencial/");
		request.setMethod(AsEnumMethod.GET);
		request.setAuthorization(new AsHttpAuthorization("phlp0001", "3141569"));
		
		try {
			request.send();
			
			System.out.println(request.getResponse().getDocument());
		} catch (AsHttpParserException e) {
			System.out.println(e.getMessage());
		}
	}
}
