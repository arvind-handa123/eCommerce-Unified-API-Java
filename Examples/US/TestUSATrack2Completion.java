package USA;

import JavaAPI.*;

public class TestUSATrack2Completion
{
	public static void main(String[] args)
	{
		String store_id = "monusqa002";
		String api_token = "qatoken";
		String order_id = "Test1432091954389";
		String txn_number = "123898-0_25";
		String amount = "1.00";
		String pos_code = "00";
		String commcard_invoice = "INVC090";
		String commcard_tax_amount = "1.00";
		String dynamic_descriptor = "123456";
		String cust_id = "my customer id";
		String processing_country_code = "US";
		boolean status_check = false;

		Track2Completion track2completion = new Track2Completion();
		track2completion.setOrderId(order_id);
		track2completion.setCustId(cust_id);
		track2completion.setTxnNumber(txn_number);
		track2completion.setAmount(amount);
		track2completion.setPosCode(pos_code);
		track2completion.setCommcardInvoice(commcard_invoice);
		track2completion.setCommcardTaxAmount(commcard_tax_amount);
		track2completion.setDynamicDescriptor(dynamic_descriptor);

		HttpsPostRequest mpgReq = new HttpsPostRequest();
		mpgReq.setProcCountryCode(processing_country_code);
		mpgReq.setTestMode(true); //false or comment out this line for production transactions
		mpgReq.setStoreId(store_id);
		mpgReq.setApiToken(api_token);
		mpgReq.setTransaction(track2completion);
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
			//System.out.println("StatusCode = " + receipt.getStatusCode());
			//System.out.println("StatusMessage = " + receipt.getStatusMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
