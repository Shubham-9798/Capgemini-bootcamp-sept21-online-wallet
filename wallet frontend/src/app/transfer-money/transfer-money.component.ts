import { Component, OnInit } from '@angular/core';
import { WalletTransaction } from '../models/WalletTransaction';
import { WalletService } from '../wallet.service';
import { Router } from '@angular/router';
import { WalletsignupService } from '../walletsignup.service';

@Component({
  selector: 'app-transfer-money',
  templateUrl: './transfer-money.component.html',
  styleUrls: ['./transfer-money.component.css']
})
export class TransferMoneyComponent implements OnInit {

  transaction : WalletTransaction
  valid:boolean
  isValidFlag: boolean = true;

  constructor(private walletService: WalletService,
    private router: Router,
    private walletsignupService: WalletsignupService) {}

  amount: number;
  reciverAccountID:number
  accountId: number;
  transferType:string;

  accountBalance:number;
  ngOnInit(): void {
    let retrievedObject:any = JSON.parse(localStorage.getItem('wallet'))
    console.log(retrievedObject)
    
     if(retrievedObject){
          this.accountId = retrievedObject.accountId
          // this. getBalance();
        }

  }

  transferMoney(){
    this.transaction = {
      transactionId: null,
      description: this.transferType,
      amount: this.amount,
      receiverAccountId: this.reciverAccountID,
      dateOfTransaction: new Date(),
      accountBalance:null
    }
    console.log(this.transaction)
    this.walletService.createTransaction(this.transaction, this.accountId).subscribe(
      data=>{
        console.log(data);
        if(data =="Transaction Created"){
          alert("Money Transferred Successfully");
        }

      }, error=>{
        alert("Your transaction declined");
        console.log(error);
      }
    )
    this.router.navigate(['/home']);
  }


  isValid(){
    console.log(this.transferType, this.accountId, this.amount)

    if(this.amount==undefined || this.accountId==undefined || this.transferType==undefined 
      || Number(this.amount)<= 0
      ){
      this.isValidFlag = false;
      console.log("fill")
    }
    else{
      this.transferMoney();
    }
  }

}
