import { Component } from '@angular/core';
import { Recipe } from '../recipe.model';

@Component({
  selector: 'app-recipe-list',
  templateUrl: './recipe-list.component.html',
  styleUrl: './recipe-list.component.css'
})
export class RecipeListComponent {

  recipes :  Recipe[] = [
    new Recipe('A Test Recipe','This is simply test','https://images.app.goo.gl/EnXmzjP4Nyr2Xrfy9')
  ];

  constructor(){

  }
}
