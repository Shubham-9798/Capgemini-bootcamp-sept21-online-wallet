import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { WalletUser } from './models/WalletUser';
import { WalletUserCredential } from './models/WalletUserCredential';

@Injectable({
  providedIn: 'root'
})
export class WalletsignupService {

  walletAccountId: number;

  basurl1: string="http://localhost:8071/";

  constructor(private http: HttpClient) {}

  createUser(walletUser : WalletUser): Observable<Object>{
    return this.http.post(`${this.basurl1+"/User/addUser"}`, walletUser);
  }

  createWalletAccount(userId: number): Observable<any>{
    return this.http.get(`${this.basurl1+"WalletAccount/create"}/${userId}`);
  }

  checkLoginDetails(walletUser: WalletUserCredential): Observable<any>{
    return this.http.post(`${this.basurl1+"User/login"}`, walletUser);
  }
  
}
