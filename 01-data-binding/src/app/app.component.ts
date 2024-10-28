import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  
  constructor(){
    this.getUserName();
  }

  userName:string ="Jai Ho";
  num:number =100;
  getUserName():string{
    return this.userName;
  }

  getNumber():number{
    return this.num;
  }

  object:object={
    name:"paddy",
    age:23
  };

  getObject():object{
    return this.object;
  }


  // Property Binding
  link = "http://www.google.com";

  imgUrl="https://images.pexels.com/photos/210019/pexels-photo-210019.jpeg?cs=srgb&dl=pexels-pixabay-210019.jpg&fm=jpg";

  onShowAlert(){
    alert("Button Pressed....");
  }

  number:number=0;

  add(){
    if(this.number==10)
    {
      alert("Cannot Increment");
      return this.number
    }
    this.number++;
    return this.number;
  }

  minus(){
    if(this.number==0)
      {
        alert("Cannot Decrement");
        return this.number
      }
    this.number--;
    return this.number;
  }

  reset(){
    this.number=0;
    return this.number;
  }

  inputText:string='';
  onSubmitText(val:any){
    this.inputText=val;
  }
}
