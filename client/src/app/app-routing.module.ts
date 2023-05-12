import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { View1Component } from './components/view1/view1.component';

const routes: Routes = [
  { path: 'upload', component: View1Component },
  { path: '', redirectTo: '/upload', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
