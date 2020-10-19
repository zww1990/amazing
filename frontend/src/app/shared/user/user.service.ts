import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { CasService } from '../cas.service';
import { SessionKey } from '../session-key.enum';
import { User } from './user.model';

/**
 * @description 用户服务
 * @author zww
 */
@Injectable()
export class UserService {
  /**
   * @description 构造用户服务
   * @param cas CAS认证服务
   */
  constructor(
    private cas: CasService,
    private http: HttpClient,
    private router: Router
  ) { }

  /**
   * @description 查询session中的用户
   */
  async querySessionUser(): Promise<User> {
    const item = sessionStorage.getItem(SessionKey.CAS_USER);
    if (item) {
      return JSON.parse(item);
    }
    try {
      const user: User = await this.http.get<User>('/spring/user/query').toPromise();
      sessionStorage.setItem(SessionKey.CAS_USER, JSON.stringify(user));
      return user;
    } catch (error) {
      console.warn({ 'error': error.error, 'message': error.message });
    }
  }

  /**
   * @description 删除session中的用户
   */
  async removeSessionUser() {
    const tgt = sessionStorage.getItem(SessionKey.CAS_TGT);
    sessionStorage.clear();
    try {
      const logout = (await this.http.get<string[]>('/spring/logout').toPromise())[0];
      location.href = logout;
    } catch (error) {
      await this.cas.casDeleteTGT(tgt);
      location.href = location.origin;
    }
  }
}
