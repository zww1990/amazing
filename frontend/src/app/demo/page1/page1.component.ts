import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { NzMessageService } from 'ng-zorro-antd/message';

@Component({
  selector: 'app-page1',
  templateUrl: './page1.component.html',
  styleUrls: ['./page1.component.less']
})
export class Page1Component implements OnInit {

  constructor(
    private msg: NzMessageService,
    private fb: FormBuilder,
    private http: HttpClient
  ) { }

  /**
   * @description 初始化
   */
  ngOnInit() {
  }

}
