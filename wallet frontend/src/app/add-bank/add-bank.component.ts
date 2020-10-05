import { Component, OnInit } from '@angular/core';
import { WalletService } from '../wallet.service';
import { Router } from '@angular/router';
import { UserBankDetails } from '../models/UserBankDetails';
import { BankWallet } from '../models/BankWallet';
import { WalletsignupService } from '../walletsignup.service';

@Component({
  selector: 'app-add-bank',
  templateUrl: './add-bank.component.html',
  styleUrls: ['./add-bank.component.css']
})
export class AddBankComponent implements OnInit {

  constructor(private router:Router,
    private walletsignupService: WalletsignupService,
    private walletService: WalletService) { }

  isBankAdded:boolean = false
  isValid:boolean;
  accountNo: number;
  holderName: string;
  ifscCode: string;
  bankName: string;
  accountId

  userDetails:UserBankDetails = null
  bankWallet: BankWallet = new BankWallet();

  ngOnInit(): void {
    
    let retrievedObject:any = JSON.parse(localStorage.getItem('wallet'))
    console.log(retrievedObject)
    this.accountId = retrievedObject.accountId
    this.checkBankInfo();
  }
  checkBankInfo(){

    this.walletService.checkBanktoWallet(this.accountId).subscribe(
      data => {
        if(data){ 
          this.isBankAdded = true
          this.router.navigate(['/bank-details']);
          console.log(data)
        }
      },
      error=>{
        console.log(error);
      }
    );
  }


  addAccount()
  {
    this.userDetails = {accountNo: this.accountNo, holderName: this.holderName, ifscCode: this.ifscCode, bankName: this.bankName}

    this.bankWallet.walletAccountId = this.walletsignupService.walletAccountId;
    this.bankWallet.bankAccountId = this.userDetails.accountNo;
    this.walletService.addBanktoWallet(this.userDetails, this.accountId).subscribe(
      data => {
        console.log(data);
        this.router.navigate(['/bank-details']);
      },
      error=>{
        console.log(error);
      }
    );
  }

  validate(){
    if(this.bankName==undefined || this.holderName==undefined || this.ifscCode==undefined || this.accountNo==undefined){
      this.isValid = false;
    }
    else{
      this.isValid = true;
      console.log(this.bankName, this.holderName, this.ifscCode, this.accountNo)
      this.addAccount();

    }
  }
}
