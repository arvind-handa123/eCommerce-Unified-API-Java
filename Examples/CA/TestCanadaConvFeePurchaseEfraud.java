package Canada;

import JavaAPI.*;

public class TestCanadaConvFeePurchaseEfraud
{
	public static void main(String args[])
	{
		String store_id = "monca00392";
		String api_token = "qYdISUhHiOdfTr1CLNpN";
		String processing_country_code = "CA";

		java.util.Date createDate = new java.util.Date();
		String order_id = "Test"+createDate.getTime();
		String amount = "10.31";
		String pan = "4242424242424242";
		String expdate = "1911";
		String crypt = "7";

		AvsInfo avsCheck = new AvsInfo();
		avsCheck.setAvsStreetNumber("212");
		avsCheck.setAvsStreetName("Payton Street");
		avsCheck.setAvsZipCode("M1M1M1");
		avsCheck.setAvsEmail("test@host.com");
		avsCheck.setAvsHostname("hostname");
		avsCheck.setAvsBrowser("Mozilla");
		avsCheck.setAvsShiptoCountry("CAN");
		avsCheck.setAvsShipMethod("G");
		avsCheck.setAvsMerchProdSku("123456");
		avsCheck.setAvsCustIp("192.168.0.1");
		avsCheck.setAvsCustPhone("5556667777");

		CvdInfo cvdCheck = new CvdInfo();
		cvdCheck.setCvdIndicator("1");
		cvdCheck.setCvdValue("099");
		
		ConvFeeInfo convFeeInfo = new ConvFeeInfo();
		convFeeInfo.setConvenienceFee("1.00");
	
		Purchase purchase = new Purchase();
		purchase.setOrderId(order_id);
		purchase.setAmount(amount);
		purchase.setPan(pan);
		purchase.setExpdate(expdate);
		purchase.setCryptType(crypt);
		purchase.setAvsInfo(avsCheck);
		purchase.setCvdInfo(cvdCheck);
		purchase.setConvFeeInfo(convFeeInfo);

		HttpsPostRequest mpgReq = new HttpsPostRequest();
		mpgReq.setProcCountryCode(processing_country_code);
		mpgReq.setTestMode(true); //false or comment out this line for production transactions
		mpgReq.setStoreId(store_id);
		mpgReq.setApiToken(api_token);
		mpgReq.setTransaction(purchase);
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
			
			System.out.println("Avs Response = " + receipt.getAvsResultCode());
			System.out.println("Cvd Response = " + receipt.getCvdResultCode());

			System.out.println("CfSuccess = " + receipt.getCfSuccess());
			System.out.println("CfStatus = " + receipt.getCfStatus());
			System.out.println("FeeAmount = " + receipt.getFeeAmount());
			System.out.println("FeeRate = " + receipt.getFeeRate());
			System.out.println("FeeType = " + receipt.getFeeType());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
