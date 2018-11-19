package common;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import index.IndexDAO;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import server.step1.UpdateDateDAO;
import vo.TokensVO;
import vo.UpdateVO;
import vo.UpdateVO2;
import vo.VersesVO;

public class SendPushData {
	private final String urlAddress = "https://fcm.googleapis.com/fcm/send";
	private final String apiKey = "AAAA2tJgawY:APA91bHmk9ctWxVjSg2vQdHtrtxj-gCSdYg8M_r2PclcN8NrAnzNO2o-4JGEc4xhiB07vtJMaCy0NpDrL3j6ktONCkg4seJMI5Br4Yie8q-DJAM-H4TwbDNzIfzWqstILLDM8HBN1zuj";
	
    public void sendNotification(ArrayList<UpdateVO> updateList){
            try {
            	String body_kr =  "";
            	String body_en = "";
            	String type_kr = "";
            	String type_en = "";
            	String lastdate = "";
            	for (int i = 0; i < updateList.size(); i++) {
            		switch (Integer.valueOf(updateList.get(i).getUpdate_type())) {
					case 1: type_kr = "(추가)"; type_en = "(ADD)"; break;
					case 2:	type_kr = "(변경)"; type_en = "(ALTER)";	break;
					case 3:	type_kr = "(삭제)"; type_en = "(DELETE)"; break;}
            		body_kr += updateList.get(i).getVerse_title_kr() + type_kr;
            		body_en += updateList.get(i).getVerse_title_en() + type_en;
            		lastdate += updateList.get(i).getLastdate();
            		if (i == updateList.size() - 1) {
						break;
					}
            		body_kr += ",";
            		body_en += ",";
            		lastdate += ",";
				}
         
                URL url = new URL(urlAddress);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setDoOutput(true);
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setRequestProperty("Authorization", "key=" + apiKey);
                
                String data = "{\"data\" : "
										+ "{\"title_kr\" : \" 새로운 말씀 구절  \", "
										+  "\"title_en\" : \"  New bible verses \", "
										+ " \"body_kr\" : \""+ body_kr +"\", "
										+ " \"body_en\" : \""+ body_en +"\", "
										+ " \"lastdate\" : \""+ lastdate +"\", "
										+ " \"lastDateServer\" : \""+ updateList.get(0).getLastdate() +"\"}, "
							+ " \"to\":\"/topics/AllMembers\"}";
                
                OutputStream os = conn.getOutputStream();
                os.write(data.getBytes("UTF-8"));
                os.flush();
                os.close();
 
                int responseCode = conn.getResponseCode();
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();
 
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                conn.disconnect();
                System.out.println("********Pushed Successfully "+response.toString());
            } catch (Exception e) {
				// TODO: handle exception
			} 
    }
}
