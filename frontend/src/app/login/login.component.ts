import { Component, OnInit, VERSION } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { NzMessageService } from 'ng-zorro-antd';
import { CasService } from '../shared/cas.service';
import { SessionKey } from '../shared/session-key.enum';
import { User } from '../shared/user/user.model';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.less']
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup;
  version = VERSION;

  constructor(private fb: FormBuilder, private router: Router, private message: NzMessageService, private cas: CasService) { }

  ngOnInit() {
    this.loginForm = this.fb.group({
      username: [null, [Validators.required]],
      password: [null, [Validators.required]]
    });
  }

  /**
   * @description CAS用户登录
   */
  async submitForm() {
    try {
      const tgt = await this.cas.casCreateTGT(this.loginForm.value);
      const st = await this.cas.casCreateST(tgt);
      const username = this.loginForm.get('username').value;
      const user = new User(username, username);
      sessionStorage.setItem(SessionKey.CAS_USER, JSON.stringify(user));
      sessionStorage.setItem(SessionKey.CAS_TGT, tgt);
      this.router.navigate(['']);
    } catch (error) {
      this.message.error('用户名或密码错误！');
    }
  }
}
