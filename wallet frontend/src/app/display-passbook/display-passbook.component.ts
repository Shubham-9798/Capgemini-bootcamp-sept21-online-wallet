import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { WalletTransaction } from '../models/WalletTransaction';
import { WalletService } from '../wallet.service';

import { WalletAccount } from '../signup/WalletAccount';

@Component({
  selector: 'app-display-passbook',
  templateUrl: './display-passbook.component.html',
  styleUrls: ['./display-passbook.component.css']
})
export class DisplayPassbookComponent implements OnInit {
  
  transactions: Observable<WalletTransaction[]>;
  walletAccount: WalletAccount = new WalletAccount();

  constructor(private walletService: WalletService) { }

    accountNumber: number;
    accountId
    isBankAccountAdded:boolean


  ngOnInit(): void {
    let retrievedObject:any = JSON.parse(localStorage.getItem('wallet')) // localstorage
 //   console.log(retrievedObject)
    
     if(retrievedObject){
          this.accountId = retrievedObject.accountId
          this.checkBankInfo();
        }


   this.reloadData();
  }

  reloadData(){
       this.transactions = this.walletService.getTransactionList(this.accountId);

    this.walletService.getWalletAccountInfo(this.accountId).subscribe(
      data=>{
        this.walletAccount = data;
        console.log(data);
      },error=>{
        console.log("error"+error);
      }
    );

    this.transactions.subscribe((data) => {
      console.log(data)
    })
    
  }

  // is bank account added
  checkBankInfo(){

    this.walletService.checkBanktoWallet(this.accountId).subscribe(
      data => {
        console.log(data)
        if(data)
        this.isBankAccountAdded = true;
      },
      error=>{
        this.isBankAccountAdded = false
        console.log(error);
      }
    );
  }
}
