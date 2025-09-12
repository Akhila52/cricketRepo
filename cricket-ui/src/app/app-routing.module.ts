import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { TeamsComponent } from './teams/teams.component';
import { MatchComponent } from './match/match.component';
import { RegistrationComponent } from './registration/registration.component';
import { HomeComponent } from './home/home.component';

const routes: Routes = [
  {path: '', redirectTo: '/home', pathMatch: 'full'},
  {path:'team',component:TeamsComponent},
  {path:'match',component:MatchComponent},
  {path:'registration',component:RegistrationComponent},
  {path:'home',component:HomeComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})


export class AppRoutingModule { }
