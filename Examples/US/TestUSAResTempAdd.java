package USA;

import JavaAPI.*;

public class TestUSAResTempAdd
{
	public static void main(String[] args)
	{
		String store_id = "monusqa002";
		String api_token = "qatoken";
		String pan = "5454545454545454";
		String expdate = "1902";        //YYMM format
		String crypt_type = "7";
		String duration = "900";
		String processing_country_code = "US";
		boolean status_check = false;

		ResTempAdd resTempAdd = new ResTempAdd();
		resTempAdd.setPan(pan);
		resTempAdd.setExpdate(expdate);
		resTempAdd.setDuration(duration);
		resTempAdd.setCryptType(crypt_type);

		HttpsPostRequest mpgReq = new HttpsPostRequest();
		mpgReq.setProcCountryCode(processing_country_code);
		mpgReq.setTestMode(true); //false or comment out this line for production transactions
		mpgReq.setStoreId(store_id);
		mpgReq.setApiToken(api_token);
		mpgReq.setTransaction(resTempAdd);
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
			System.out.println("MaskedPan = " + receipt.getResMaskedPan());
			System.out.println("Exp Date = " + receipt.getResExpdate());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
