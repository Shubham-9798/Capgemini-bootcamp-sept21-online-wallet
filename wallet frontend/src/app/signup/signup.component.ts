import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { WalletsignupService } from '../walletsignup.service';
import { WalletUser } from '../models/WalletUser';
import { WalletAccount } from './WalletAccount';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {
  walletUser: WalletUser = null
  constructor(private router: Router,
    private walletsignupService: WalletsignupService) {}


  userName: string;
  userId:number
  password: string;
  phoneNumber: number;
  isValid : boolean = true;
  isSignUpSuccessfull: boolean = false;
  isUserAlreadyExists:boolean = false

  ngOnInit(): void {
  
  }

  signUp(){
    console.log(this.walletUser)
    
    this.walletsignupService.createUser(this.walletUser).subscribe(
      (data:WalletUser) => {
        console.log(data)
       
        if(!data){
          this.isSignUpSuccessfull = false;
          console.log(data);
        }
        else{
          this.userId = data.userId
          localStorage.setItem("userInfo", JSON.stringify(data))
          this.isSignUpSuccessfull = true;

          this.walletsignupService.createWalletAccount(this.userId).subscribe(
          data=>{
            this.walletsignupService.walletAccountId = data;
            localStorage.setItem("wallet", JSON.stringify(data))
            console.log(data);
          }, error=>{
            console.log(error);
          } )
          alert("You are Registered Successfully");
          this.router.navigate(['/home']);
        }
      }, error=>{
        console.log(error);
      }
    )
  }

  validate(user){
    // console.log(user.password.length())
    const {password, phoneNumber, userName} = user
    // console.log(password.length, phoneNumber, userName)
    if(
      password==undefined || phoneNumber==undefined || userName==undefined ||
      password.length ===0 || phoneNumber.length === 0  ||userName.length === 0
      ){
      this.isValid = false;
    }
    else{
      console.log("sign up")
      this.isValid = true;
      this.walletUser ={
        userId: null,
        userName:user.userName,
        password : user.password,
        phoneNumber: user.phoneNumber
      }
      this.signUp();
    }
  }

}
