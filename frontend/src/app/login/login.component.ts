import { Component, OnInit } from '@angular/core';
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
      const result = await this.cas.casServiceValidate(st);
      if (result.status) {
        const user = new User(result.text, this.loginForm.get('username').value);
        sessionStorage.setItem(SessionKey.CAS_USER, JSON.stringify(user));
        this.router.navigate(['']);
      } else {
        this.message.error(result.text);
      }
    } catch (error) {
      this.message.error('用户名或密码错误！');
    }
  }
}
