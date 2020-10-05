import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { WalletTransaction } from '../models/WalletTransaction';
import { WalletService } from '../wallet.service';
import { WalletsignupService } from '../walletsignup.service';
import { DatePipe } from '@angular/common';
import { WalletAccount } from '../signup/WalletAccount';

@Component({
  selector: 'app-display-passbook',
  templateUrl: './display-passbook.component.html',
  styleUrls: ['./display-passbook.component.css']
})
export class DisplayPassbookComponent implements OnInit {
  
  transactions: Observable<WalletTransaction[]>;
  walletAccount: WalletAccount = new WalletAccount();

  constructor(private walletService: WalletService,
    private router: Router,private datePipe: DatePipe,
    private walletsignupService: WalletsignupService) { }

    accountNumber: number;
    accountId
    isBankAccountAdded:boolean


  ngOnInit(): void {
    let retrievedObject:any = JSON.parse(localStorage.getItem('wallet'))
    console.log(retrievedObject)
    
     if(retrievedObject){
          console.log(2)
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
