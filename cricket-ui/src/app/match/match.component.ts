import { Component } from '@angular/core';
import { HttpserviceService } from '../httpservice.service';
import { interval, Subscription } from 'rxjs';

@Component({
  selector: 'app-match',
  templateUrl: './match.component.html',
  styleUrl: './match.component.scss'
})
export class MatchComponent {


teams: any[] = [];
  selectedTeam1: number | undefined;
  selectedTeam2: number | undefined;
  matchResult: any;
  matchStatus: string = '';
  winnerMessage: string = '';
  private pollSubscription: Subscription | undefined;
  noPlayer:boolean=false;
  constructor(private httpService:HttpserviceService) {}

  ngOnInit() {
    this.loadTeams();
  }

  ngOnDestroy() {
    if (this.pollSubscription) {
      this.pollSubscription.unsubscribe();
    }
  }

  loadTeams() {
    this.httpService.get('cricket/teams').subscribe((data:any) => {
      this.teams = data;
    });
  }

  startMatch() {
    if (!this.selectedTeam1 || !this.selectedTeam2) {
      alert('Please select both teams');
      return;
    }
    
    if(this.selectedTeam1 === this.selectedTeam2){
      alert("Please select different teams to start the match.");
      return;
    }
    this.matchStatus = '🏏 Match has started!';
    this.matchResult = null;
    this.winnerMessage = '';

    // Stop previous polling if any
    if (this.pollSubscription) {
      this.pollSubscription.unsubscribe();
    }
this.noPlayer=false;
    // Poll every 2 seconds
    // this.pollSubscription = interval(2000).subscribe(() => {
      this.httpService.get(
        `cricket/match/playMatch?team1Id=${this.selectedTeam1}&team2Id=${this.selectedTeam2}`
      ).subscribe(result => {
        if(result){
        this.matchResult = result;
        console.log("the match result is ",this.matchResult.playersMesage);
        console.log('Match result:', result);
        this.updateWinnerMessage();
        }
        
      });
    // });
  }

  updateWinnerMessage() {
    if (!this.matchResult) return;
    const { team1Score, team2Score, team1Name, team2Name } = this.matchResult;
    if (team1Score === 0 && team2Score === 0) {
    this.winnerMessage = `😅 Both teams are warming up and not making any progress yet!`;
    } else if (team1Score > team2Score) {
      this.winnerMessage = `🎉 ${team1Name} is leading! Keep it up!`;
    } else if (team2Score > team1Score) {
      this.winnerMessage = `🎉 ${team2Name} is leading! Go go go!`;
    } else {
      this.winnerMessage = `🤝 It's currently a tie! What a close match!`;
    }
  }
}
