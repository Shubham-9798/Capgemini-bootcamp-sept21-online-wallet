import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { WalletsignupService } from '../walletsignup.service';

@Component({
  selector: 'app-update-password',
  templateUrl: './update-password.component.html',
  styleUrls: ['./update-password.component.css']
})
export class UpdatePasswordComponent implements OnInit {

  constructor(private router: Router,
    private walletsignupService: WalletsignupService) { }
    userId:number;
    password:string
    newPassword:string
    isValid:boolean
    isUpdated:boolean

  ngOnInit(): void {
    this.userId = JSON.parse(localStorage.getItem("userInfo")).userId
  }
  updatePassword() {
    console.log(this.password, this.newPassword, this.userId)

    this.walletsignupService.updatePassword({password: this.password, newPassword: this.newPassword}, this.userId).subscribe(
      data=>{
        if(data === "User updated")
        this.isUpdated = true
      },
      (err)=>{
        console.log(err);
        
      })
  }

  validate(){
    if( this.password==undefined || this.newPassword.length === 0 || this.password.length ===0 ||this.newPassword==undefined){
      this.isValid = false;
    }
    else{
      console.log("uy");
      
      this.isValid = true;
      this.updatePassword();
    }
  }


}
