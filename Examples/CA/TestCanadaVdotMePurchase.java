package Canada;

import JavaAPI.*;

public class TestCanadaVdotMePurchase
{
	public static void main(String[] args)
	{
		String store_id = "store2";
		String api_token = "yesguy";
		String cust_id = "Joe Doe";
		java.util.Date createDate = new java.util.Date();
		String order_id = "Test"+createDate.getTime(); 
		String amount = "8.00";
		String crypt_type = "7";
		String call_id = "9104624497663077101";
		String dynamic_descriptor = "inv123";
		String processing_country_code = "CA";
		boolean status_check = false;

		VdotMePurchase vmepurchase = new VdotMePurchase();
		vmepurchase.setOrderId(order_id);
		vmepurchase.setCustId(cust_id);
		vmepurchase.setAmount(amount);
		vmepurchase.setCallId(call_id);
		vmepurchase.setCryptType(crypt_type);
		vmepurchase.setDynamicDescriptor(dynamic_descriptor);

		HttpsPostRequest mpgReq = new HttpsPostRequest();
		mpgReq.setProcCountryCode(processing_country_code);
		mpgReq.setTestMode(true); //false or comment out this line for production transactions
		mpgReq.setStoreId(store_id);
		mpgReq.setApiToken(api_token);
		mpgReq.setTransaction(vmepurchase);
		mpgReq.setStatusCheck(status_check);
		mpgReq.send();

		try
		{
			Receipt receipt = mpgReq.getReceipt();

			System.out.println("CardType = " + receipt.getCardType());
			System.out.println("TransAmount = " + receipt.getTransAmount());
			System.out.println("TxnNumber = " + receipt.getTxnNumber());
			System.out.println("ReceiptId = " + receipt.getReceiptId());
			System.out.println("TransType = " + receipt.getTransType());
			System.out.println("ReferenceNum = " + receipt.getReferenceNum());
			System.out.println("ResponseCode = " + receipt.getResponseCode());
			System.out.println("ISO = " + receipt.getISO());
			System.out.println("BankTotals = " + receipt.getBankTotals());
			System.out.println("Message = " + receipt.getMessage());
			System.out.println("AuthCode = " + receipt.getAuthCode());
			System.out.println("Complete = " + receipt.getComplete());
			System.out.println("TransDate = " + receipt.getTransDate());
			System.out.println("TransTime = " + receipt.getTransTime());
			System.out.println("Ticket = " + receipt.getTicket());
			System.out.println("TimedOut = " + receipt.getTimedOut());
			System.out.println("StatusCode = " + receipt.getStatusCode());
			System.out.println("StatusMessage = " + receipt.getStatusMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
