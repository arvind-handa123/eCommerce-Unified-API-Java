package Canada;

import JavaAPI.*;

public class TestCanadaResAddCC
{
	public static void main(String[] args)
	{
		String store_id = "store5";
		String api_token = "yesguy";
		String pan = "4242424242424242";
		String expdate = "1912";
		String phone = "0000000000";
		String email = "bob@smith.com";
		String note = "my note";
		String cust_id = "customer1";
		String crypt_type = "7";
		String data_key_format = "0";
		String processing_country_code = "CA";
		boolean status_check = false;

		AvsInfo avsCheck = new AvsInfo();
		avsCheck.setAvsStreetNumber("212");
		avsCheck.setAvsStreetName("Payton Street");
		avsCheck.setAvsZipCode("M1M1M1");

		ResAddCC resaddcc = new ResAddCC();
		resaddcc.setPan(pan);
		resaddcc.setExpdate(expdate);
		resaddcc.setCryptType(crypt_type);
		resaddcc.setCustId(cust_id);
		resaddcc.setPhone(phone);
		resaddcc.setEmail(email);
		resaddcc.setNote(note);
		resaddcc.setAvsInfo(avsCheck);
		//resaddcc.setDataKeyFormat(data_key_format); //optional

		//Mandatory - Credential on File details
		CofInfo cof = new CofInfo();
		cof.setIssuerId("139X3130ASCXAS9"); //can be obtained by performing card verification
		
		resaddcc.setCofInfo(cof);
		
		HttpsPostRequest mpgReq = new HttpsPostRequest();
		mpgReq.setProcCountryCode(processing_country_code);
		mpgReq.setTestMode(true); //false or comment out this line for production transactions
		mpgReq.setStoreId(store_id);
		mpgReq.setApiToken(api_token);
		mpgReq.setTransaction(resaddcc);
		mpgReq.setStatusCheck(status_check);
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
			System.out.println("PaymentType = " + receipt.getPaymentType());
			System.out.println("Cust ID = " + receipt.getResCustId());
			System.out.println("Phone = " + receipt.getResPhone());
			System.out.println("Email = " + receipt.getResEmail());
			System.out.println("Note = " + receipt.getResNote());
			System.out.println("MaskedPan = " + receipt.getResMaskedPan());
			System.out.println("Exp Date = " + receipt.getResExpdate());
			System.out.println("Crypt Type = " + receipt.getResCryptType());
			System.out.println("Avs Street Number = " + receipt.getResAvsStreetNumber());
			System.out.println("Avs Street Name = " + receipt.getResAvsStreetName());
			System.out.println("Avs Zipcode = " + receipt.getResAvsZipcode());
			System.out.println("IssuerId = " + receipt.getIssuerId());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
