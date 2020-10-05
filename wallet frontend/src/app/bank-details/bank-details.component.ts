import { Component, OnInit } from '@angular/core';
import { UserBankDetails } from '../models/UserBankDetails';
import { WalletService } from '../wallet.service';
import { Router } from '@angular/router';
import { WalletsignupService } from '../walletsignup.service';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-bank-details',
  templateUrl: './bank-details.component.html',
  styleUrls: ['./bank-details.component.css']
})
export class BankDetailsComponent implements OnInit {

  userObj: Observable<UserBankDetails>;
  accountId
  userBankDetails: UserBankDetails 
  isDataLoaded:boolean = false

  constructor(private router:Router,
    private walletsignupService: WalletsignupService,
    private walletService: WalletService) { }
  
  ngOnInit(): void {
    let retrievedObject:any = JSON.parse(localStorage.getItem('wallet'))
    this.accountId = retrievedObject.accountId
    this.userObj = this.walletService.getBankAccountDetails(this.accountId);
    this.userObj.subscribe(
      data=>{
        console.log(data)
        this.isDataLoaded = true
        this.userBankDetails = data;
      }
    )

  }
}
