package recognition01;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.watson.developer_cloud.visual_recognition.v3.VisualRecognition;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.DetectFacesOptions;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.DetectedFaces;

public class Recognition01_main {
	public static void main(String[] args){
		VisualRecognition service = new VisualRecognition("2018-03-19");
		service.setApiKey("j16016");


		DetectFacesOptions detectFacesOptions = null;
		try {
			detectFacesOptions = new DetectFacesOptions.Builder()
			  .imagesFile(new File("img/prez.jpg"))
			  .build();
		} catch (FileNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		DetectedFaces result = service.detectFaces(detectFacesOptions).execute();
		System.out.println(result);

		String s = String.valueOf(result);

		ObjectMapper mapper = new ObjectMapper();

		try {
			JsonNode node = mapper.readTree(s);
			int age_min = node.get("images").get(0).get("faces").get(0).get("age").get("min").asInt();
			 System.out.println("age_min : " + age_min);

			 int age_max = node.get("images").get(0).get("faces").get(0).get("age").get("max").asInt();
			 System.out.println("age_max : " + age_max);

			 float age_score = node.get("images").get(0).get("faces").get(0).get("age").get("score").floatValue();
			 System.out.println("age_score : " + age_score);

			 String gender = node.get("images").get(0).get("faces").get(0).get("gender").get("gender").asText();
			 System.out.println("gender : " + gender);

			 float gender_min = node.get("images").get(0).get("faces").get(0).get("gender").get("score").floatValue();
			 System.out.println("gender_min : " + gender_min);



		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}



	}

}
