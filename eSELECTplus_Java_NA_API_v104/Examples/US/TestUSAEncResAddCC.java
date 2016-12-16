package USA;

import JavaAPI.*;

public class TestUSAEncResAddCC
{
	public static void main(String args[])
	{
		String store_id = "monusqa002";
		String api_token = "qatoken";

		String enc_track2 = "02D901801F4F2800039B%*4924********3428^TESTCARD/MONERIS^*****************************************?*;4924********3428=********************?*9AC1D386E03D553ACB8976E58653B76386B5C7ACBFA126D6ADC026018152893229626E03BE0D41673C975B69C773FDE3A890AD2B5452B02739529B85C8231B3798AEE7E702280CC8A3E4B3CE1C098E4CB24A0A7DFF462507E3419EFF3880E9D52C869BA6C970C49B10B3C6E146B626EDBB13F3C6057377E43CD06F7A188D84EA0260832F743E485C0D369929D4840FFAFA12BC3938C4A4DE4FA3FA837D1C2190FFFF314159200420005F345603";
		String device_type = "idtech";
		String phone = "55555555555";
		String email = "test.user@moneris.com";
		String note = "my note";
		String cust_id = "customer2";
		String crypt = "7";
		String processing_country_code = "US";

		AvsInfo avsCheck = new AvsInfo();

		avsCheck.setAvsStreetNumber("212");
		avsCheck.setAvsStreetName("Payton Street");
		avsCheck.setAvsZipcode("M1M1M1");

		EncResAddCC resAddCC = new EncResAddCC();
		resAddCC.setEncTrack2(enc_track2);
		resAddCC.setDeviceType(device_type);
		resAddCC.setCryptType(crypt);		
		resAddCC.setCustId(cust_id);
		resAddCC.setPhone(phone);
		resAddCC.setEmail(email);
		resAddCC.setNote(note);
		//usResAddCC.setAvsInfo(avsCheck);

		HttpsPostRequest mpgReq = new HttpsPostRequest();
		mpgReq.setProcCountryCode(processing_country_code);
		mpgReq.setTestMode(true); //false or comment out this line for production transactions
		mpgReq.setStoreId(store_id);
		mpgReq.setApiToken(api_token);
		mpgReq.setTransaction(resAddCC);
		mpgReq.send();

		try
		{
			Receipt receipt = mpgReq.getReceipt();

			System.out.println("DataKey = " + receipt.getDataKey());
			System.out.println("ResponseCode = " + receipt.getResponseCode());
			System.out.println("Message = " + receipt.getMessage());
			System.out.println("TransDate = " + receipt.getTransDate());
			System.out.println("TransTime = " + receipt.getTransTime());
			System.out.println("Complete = " + receipt.getComplete());
			System.out.println("TimedOut = " + receipt.getTimedOut());
			System.out.println("ResSuccess = " + receipt.getResSuccess());
			System.out.println("PaymentType = " + receipt.getPaymentType() + "\n");

			//Contents of ResolveData
			System.out.println("Cust ID = " + receipt.getResCustId());
			System.out.println("Phone = " + receipt.getResPhone());
			System.out.println("Email = " + receipt.getResEmail());
			System.out.println("Note = " + receipt.getResNote());
			System.out.println("MaskedPan = " + receipt.getResMaskedPan());
			System.out.println("Exp Date = " + receipt.getResExpDate());
			System.out.println("Crypt Type = " + receipt.getResCryptType());
			System.out.println("Avs Street Number = " + receipt.getResAvsStreetNumber());
			System.out.println("Avs Street Name = " + receipt.getResAvsStreetName());
			System.out.println("Avs Zipcode = " + receipt.getResAvsZipcode());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
