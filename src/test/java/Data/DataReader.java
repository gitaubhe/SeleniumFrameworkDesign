package Data;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader {
public List<HashMap<String, String>>  getJsonDataToMap() throws IOException
{
	//Json to String
	String jsonContent = FileUtils.readFileToString(new File(System.getProperty("user.dir")+"\\src\\test\\java\\Data\\PurchaseOrder.json"));
   
	//String to Hashmap using Jackson data bind
	ObjectMapper mapper = new ObjectMapper();
    List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>(){});
return data;
}
}
