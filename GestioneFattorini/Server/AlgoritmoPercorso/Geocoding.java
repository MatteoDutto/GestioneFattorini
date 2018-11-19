import java.io.ByteArrayOutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.List;
import org.apache.commons.io.IOUtils; (For this you need to add "commons-io-1.3.1.jar" in your project.)

public Class GeocodeImplementation {

/*Geocode request URL. Here see we are passing "json" it means we will get the output in JSON format.
* You can also pass "xml" instead of "json" for XML output.
* For XML output URL will be "http://maps.googleapis.com/maps/api/geocode/xml"; 
*/

private static final String URL = "http://maps.googleapis.com/maps/api/geocode/json"; 

/*
* Here the fullAddress String is in format like "address,city,state,zipcode". Here address means "street number + route" .
*
*/
public String getJSONByGoogle(String fullAddress) {

/*
* Create an java.net.URL object by passing the request URL in constructor. 
* Here you can see I am converting the fullAddress String in UTF-8 format. 
* You will get Exception if you don't convert your address in UTF-8 format. Perhaps google loves UTF-8 format. :)
* In parameter we also need to pass "sensor" parameter.
* sensor (required parameter) — Indicates whether or not the geocoding request comes from a device with a location sensor. This value must be either true or false.
*/
	URL url = new URL(URL + "?address=" + URLEncoder.encode(fullAddress, "UTF-8")+ "&sensor=false");

// Open the Connection 
	URLConnection conn = url.openConnection();

//This is Simple a byte array output stream that we will use to keep the output data from google. 
	ByteArrayOutputStream output = new ByteArrayOutputStream(1024);

// copying the output data from Google which will be either in JSON or XML depending on your request URL that in which format you have requested.
	IOUtils.copy(conn.getInputStream(), output);

//close the byte array output stream now.
	output.close();

return output.toString(); // This returned String is JSON string from which you can retrieve all key value pair and can save it in POJO.
}
}
