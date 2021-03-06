package se.liu.ida.tdp024.account.data.impl.db.entity;

import se.liu.ida.tdp024.account.data.api.entity.Account;
import se.liu.ida.tdp024.account.data.impl.db.entity.TransactionDB;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class AccountDB implements Account {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  private String personKey;
  private String accountType = new String("CHECKING");
  private String bankKey;
  private Integer holdings;


  public long getId(){
    return id;
  }

  public void setPersonKey(String personKey){
      this.personKey = personKey;
  }

  public String getPersonKey(){
      return personKey;
  }


  public void setAccountType(String accountType){
    this.accountType = accountType;
  }

  public String getAccountType(){
    return accountType;
  }

  public String getBankKey(){
    return bankKey;
  }

  public void setBankKey(String bankKey){
    this.bankKey = bankKey;
  }

  public Integer getHoldings(){
    return holdings;
  }

  public void setHoldings(Integer holdings){
    this.holdings = holdings;
  }

  public boolean addHoldings(Integer holdings){
    this.holdings += holdings;
    return true;
  }

  public boolean removeHoldings(Integer holdings){
    if(this.holdings - holdings >= 0) {
      this.holdings -= holdings;
      return true;
    }
    return false;
  }
}
