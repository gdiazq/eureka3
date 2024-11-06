import { Component, OnInit } from '@angular/core';
import { initFlowbite } from 'flowbite';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html'
})
export class AppComponent implements OnInit {
  title = 'frontend';

//Este codigo es para poder trabajar con flowbite

  ngOnInit(): void {
      initFlowbite();
  }
}


