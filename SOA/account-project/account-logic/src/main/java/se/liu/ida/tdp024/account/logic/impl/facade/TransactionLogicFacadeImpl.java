package se.liu.ida.tdp024.account.logic.impl.facade;
import se.liu.ida.tdp024.account.logic.api.facade.TransactionLogicFacade;
import se.liu.ida.tdp024.account.data.api.facade.TransactionEntityFacade;
import se.liu.ida.tdp024.account.data.api.entity.Transaction;
import se.liu.ida.tdp024.account.data.impl.db.entity.TransactionDB;
import java.util.ArrayList;
import com.google.gson.Gson;
import se.liu.ida.tdp024.account.util.http.HTTPHelper;
import se.liu.ida.tdp024.account.util.http.HTTPHelperImpl;
import se.liu.ida.tdp024.account.util.json.AccountJsonSerializer;
import se.liu.ida.tdp024.account.util.json.AccountJsonSerializerImpl;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.*;
import se.liu.ida.tdp024.account.data.api.entity.Account;



public class TransactionLogicFacadeImpl implements TransactionLogicFacade {


  private TransactionEntityFacade transactionEntityFacade;
  private static final HTTPHelper httpHelper = new HTTPHelperImpl();
  private static final AccountJsonSerializerImpl jsonSerializer = new AccountJsonSerializerImpl();

  public TransactionLogicFacadeImpl(TransactionEntityFacade transactionEntityFacade) {
    this.transactionEntityFacade = transactionEntityFacade;
  }

  @Override
  public boolean addTransaction(String type, Integer amount, boolean status, Account acc){
    return transactionEntityFacade.addTransaction(type, amount, status, acc);
  }

  @Override
  public List<Transaction> getTransactions(long id){
    List<Transaction> asdf = transactionEntityFacade.findTransactions(id);
    return asdf;
  }

}
