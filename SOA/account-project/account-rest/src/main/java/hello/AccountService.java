package hello;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import se.liu.ida.tdp024.account.logic.api.facade.AccountLogicFacade;
import se.liu.ida.tdp024.account.logic.api.facade.TransactionLogicFacade;
import se.liu.ida.tdp024.account.logic.impl.facade.AccountLogicFacadeImpl;
import se.liu.ida.tdp024.account.logic.impl.facade.TransactionLogicFacadeImpl;
import se.liu.ida.tdp024.account.data.impl.db.facade.AccountEntityFacadeDB;
import se.liu.ida.tdp024.account.data.impl.db.facade.TransactionEntityFacadeDB;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.ArrayList;
import se.liu.ida.tdp024.account.util.json.AccountJsonSerializer;
import se.liu.ida.tdp024.account.util.json.AccountJsonSerializerImpl;

@RestController
@RequestMapping("account-rest")
public class AccountService {

  private final AccountLogicFacade accountlogicfacade = new AccountLogicFacadeImpl(new AccountEntityFacadeDB());
  private final TransactionLogicFacade transactionlogicfacade = new TransactionLogicFacadeImpl(new TransactionEntityFacadeDB());
  private static final AccountJsonSerializerImpl jsonSerializer = new AccountJsonSerializerImpl();

  @RequestMapping("/account/create")
  public String create(@RequestParam (required = false) String person,  @RequestParam (required = false) String bank, @RequestParam (required = false) String accounttype) {
    if(person == null || bank==null ||accounttype==null){
      return "FAILED";
    }
    boolean status = accountlogicfacade.register(person, bank, accounttype);
    //System.out.printf("¤¤¤¤¤¤¤¤¤%s¤¤¤¤¤¤¤¤¤¤¤¤¤", status);
    if(status) {
      return "OK";//new ResponseEntity("OK", HttpStatus.OK);
    }
    return "FAILED";//new ResponseEntity("FAILED", HttpStatus.NOT_ACC);
    //return String.format("Route called with %s", word);
  }

  @RequestMapping("/account/find/person")
  public ResponseEntity findPerson(@RequestParam String person){
    String person_list = accountlogicfacade.findPerson(person);
    //String json = jsonSerializer.toJson(person_list);
    return new ResponseEntity(person_list, HttpStatus.OK);
  }

  @RequestMapping("/account/debit")
  public ResponseEntity debit(@RequestParam long id, @RequestParam Integer amount) {
    boolean status = accountlogicfacade.debitAccount(id, amount);
    transactionlogicfacade.addTransaction("DEBIT", id, amount, status);
    if(status){
      return new ResponseEntity(status, HttpStatus.OK);

    }
    return new ResponseEntity("{\"message\":\"Something went wrong\"}", HttpStatus.NOT_FOUND);

  }

  @RequestMapping("/account/credit")
  public ResponseEntity credit(@RequestParam long id, @RequestParam Integer amount){
    boolean status = accountlogicfacade.creditAccount(id, amount);
    transactionlogicfacade.addTransaction("CREDIT", id, amount, status);
    if(status)
    {
      return new ResponseEntity(HttpStatus.OK);
    }
    return new ResponseEntity("{\"message\":\"Something went wrong\"}", HttpStatus.NOT_FOUND);
  }

  @RequestMapping("/account/transactions")
  public ResponseEntity transactions(@RequestParam long id) {
    String trans_list = transactionlogicfacade.getTransactions(id);
    //String json = jsonSerializer.toJson(trans_list);
    return new ResponseEntity(trans_list, HttpStatus.OK);
  }
}
