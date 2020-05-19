import { Injectable } from '@angular/core';
import { CasService } from '../cas.service';
import { SessionKey } from '../session-key.enum';
import { User } from './user.model';
import { HttpClient } from '@angular/common/http';

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
  constructor(private cas: CasService, private http: HttpClient) { }

  /**
   * @description 查询session中的用户
   */
  async querySessionUser(): Promise<User> {
    const item = sessionStorage.getItem(SessionKey.CAS_USER);
    if (item) {
      return JSON.parse(item);
    }
    const user: User = await this.http.get<User>('/spring/user/query').toPromise();
    sessionStorage.setItem(SessionKey.CAS_USER, JSON.stringify(user));
    return user;
  }

  /**
   * @description 删除session中的用户
   */
  async removeSessionUser() {
    sessionStorage.clear();
    const logout = (await this.http.get<string[]>('/spring/logout').toPromise())[0];
    location.href = logout;
  }
}
