import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class WalletService {

  baseUrl: string="http://localhost:8071";
  headers = new HttpHeaders().set('Content-Type', 'text/plain; charset=utf-8');

  constructor(private http: HttpClient) { }

  createTransaction(transaction : Object, accountId:number): Observable<any>{
    const option :Object =  {
      responseType: "text"
    }
    return this.http.post(`${this.baseUrl+"/transaction/"+accountId}`, transaction, option);
  }

  getTransactionList(accountId: number): Observable<any>{
    return this.http.get(`${this.baseUrl+"/transactionList"}/${accountId}`);
  }

  getAccountBalance(accountId: number): Observable<any>{
    return this.http.get(`${this.baseUrl+"/WalletAccount/getBalance"}/${accountId}`);
  }

  getWalletAccountInfo(accountId: number): Observable<any>{
    return this.http.get(`${this.baseUrl+"/WalletAccount/seeWalletAccount/"}/${accountId}`);
  }

  addMoney(amount:number, accountId:number): Observable<any>{
    return this.http.get(`${this.baseUrl+"/WalletAccount/addMoney"}/${accountId}/${amount}`);
  }

  public addBanktoWallet(bankWallet, id): Observable<any> {
    return this.http.post<any>(this.baseUrl+"/BankAccount/addAccount/"+id, bankWallet);
  } 
  
  public checkBanktoWallet(walletAccountId: number){
    return this.http.get<any>(`${this.baseUrl + "/BankAccount/findBankAccount"}/${walletAccountId}`)
  }

  public getBankAccountDetails(walletAccountId: number){
    return this.http.get<any>(`${this.baseUrl + "/BankAccount/findBankAccount"}/${walletAccountId}`)
  }

}