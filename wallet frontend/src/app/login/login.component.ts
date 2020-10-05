import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { WalletsignupService } from '../walletsignup.service';
import { WalletUser } from '../models/WalletUser';
import { WalletUserCredential } from '../models/WalletUserCredential';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  walletUser: WalletUserCredential 
  userName: string;
  password: string;
  isValid : boolean;

  isRightUser: boolean = true;
  constructor(private router: Router,
    private walletsignupService: WalletsignupService) { }

  ngOnInit(): void {
    const userInfo = localStorage.getItem("userInfo")
    const wallet = localStorage.getItem("wallet")
    if(userInfo && wallet) {
      this.router.navigate(['/home']);
    }
    // console.log(userInfo, wallet)
  }

  onLogin(){
    this.walletUser = {userName: this.userName, password: this.password}
    console.log(this.walletUser)
    
    this.walletsignupService.checkLoginDetails(this.walletUser).subscribe(
      data=>{
        console.log(data);
        localStorage.setItem("userInfo", JSON.stringify(data))
        localStorage.setItem("wallet", JSON.stringify(data.walletAccount))
        if(data != null){
          this.isRightUser = true;
          this.isValid = true;
          this.router.navigate(['/home']);
        }
        else if(data=="Invalid Password" || data=="Invalid UserId"){
          this.isRightUser = false;
        }
        else{
          this.isRightUser = true;
        }
      },
      error=>{
        console.log(error);
      }
    )
    
    
  }

  validate(){
    if(this.userName==undefined || this.password==undefined || this.password.length == 0 || this.userName.length == 0){
      this.isValid = false;
    }
    else{
      this.isValid = true;
      this.onLogin();
    }
  }

}
