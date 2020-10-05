import { Component, OnInit, ɵConsole } from '@angular/core';
import { WalletService } from '../wallet.service';
import { Router } from '@angular/router';
import { WalletsignupService } from '../walletsignup.service';

@Component({
  selector: 'app-add-money',
  templateUrl: './add-money.component.html',
  styleUrls: ['./add-money.component.css']
})
export class AddMoneyComponent implements OnInit {

  constructor(private walletService: WalletService,
    private router: Router,
    private walletsignupService: WalletsignupService) { }

  amount:number = 0;
  valid:boolean = true;
  balance: number
  isBankAccountAdded:boolean = false
  isLoaded:boolean = false

  isValidFlag: boolean = true;
  accountId:number = null
  flag : boolean=false;

  ngOnInit(): void {
    
    let retrievedObject:any = JSON.parse(localStorage.getItem('wallet'))
    console.log(retrievedObject)
    
     if(retrievedObject) {
          this.accountId = retrievedObject.accountId
          this.checkBankInfo();
          this. getBalance();
        }

      }

  getBalance(){
    this.walletService.getAccountBalance(this.accountId).subscribe(
      data=>{
        console.log(data);
        
        this.balance = data;
        this.flag = true;
      }
    );
  }

  addMoney(){
    if(this.isValidFlag)
    this.walletService.addMoney(this.amount,
      this.accountId).subscribe(
        data=>{
          console.log(data);
          this.getBalance();
        },error=>{
          console.log(error);
        }
      )
      
  }

  checkBankInfo(){

    this.walletService.checkBanktoWallet(this.accountId).subscribe(
      data => {
        if(data)
        this.isBankAccountAdded =true
      },
      error=>{
        this.isBankAccountAdded = false
        console.log("not added")
        console.log(error);
      }
    );
    this.isLoaded = true
  }

  back(){
    this.router.navigate(['/home']);
  }

  isValid(){
    if(this.amount==undefined){
      this.isValidFlag = false;
    }
    else{
      this.addMoney();
    }
  }

}
