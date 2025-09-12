import { Component } from '@angular/core';
import { HttpserviceService } from '../httpservice.service';

@Component({
  selector: 'app-teams',
  templateUrl: './teams.component.html',
  styleUrl: './teams.component.scss'
})
export class TeamsComponent {

 teams: any[] = [];    // ✅ start as empty array
  players: any[] = [];  // ✅ start as empty array
  selectedTeamId: any;

  constructor(private httpService: HttpserviceService) {}

  ngOnInit(): void {
    this.fetchTeams();
  }

  fetchTeams() {
    this.httpService.get('cricket/teams').subscribe((data:any) => {
              console.log('Fetched teams:ghg', data);

    this.teams = data;
        console.log('Fetched teams:', data);
    });
  }

  onTeamChange() {
    
    if (this.selectedTeamId) {
      this.httpService.get(`cricket/players/getPlayersByTeamID?teamId=${this.selectedTeamId}`).subscribe((data:any)=>{
          this.players = data;
          console.log('Fetched players:', this.players);
      });
    } else {
      console.warn('No players are not therre for the team');
      this.players = [];
    }
  }
}
