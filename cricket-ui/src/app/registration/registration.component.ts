import { Component } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { HttpserviceService } from '../httpservice.service';
export interface Team {
  id: number;
  teamCountry: string;
}
export interface Player {
  playerName: string;
  speciality: string;
  teamId: number;
}

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrl: './registration.component.scss'
})
export class RegistrationComponent {

  teamForm!: FormGroup;

  teams :Team[]=[];
  specialities = ['Batsman', 'Bowler', 'All-Rounder'];

  constructor(private fb: FormBuilder, private httpService:HttpserviceService) {}

  ngOnInit(): void {
        this.fetchTeams();

    this.teamForm = this.fb.group({
      team: ['', Validators.required],
      players: this.fb.array([])
    });

    // Add 12 players
    for (let i = 0; i < 12; i++) {
      this.addPlayer();
    }
  }


  fetchTeams() {
    this.httpService.get('cricket/teams').subscribe((data:any) => {
              console.log('Fetched teams:ghg', data);

    this.teams = data;
        console.log('Fetched teams:', data);
    });
  }
  // Getter for players FormArray
  get players(): FormArray {
    return this.teamForm.get('players') as FormArray;
  }

  // Add a player FormGroup to the array
  addPlayer() {
    const playerGroup = this.fb.group({
      name: ['', Validators.required, Validators.pattern('^[A-Za-z ]+$') ],
      speciality: ['', Validators.required],
      age: ['', Validators.required,
         Validators.pattern('^[0-9]+$'), 
        Validators.max(40)  
      ],
    });
    this.players.push(playerGroup);
  }

submitTeam() {
  if (this.teamForm.valid) {
    const formValue = this.teamForm.value;

    // Extract team info
    const teamId = formValue.team;
    const teamCountry = formValue.teamCountry;

    // Build players array
  const payload = formValue.players.map((player: any) => ({
  playerName: player.name,
  speciality: player.speciality,
  teamId: teamId,
  age: player.age
}));


this.httpService.post('http://localhost:8081/cricket/players/addPlayers', payload)

    console.log('Payload:', payload);
// cricket/players/addPlayers
    // Call backend
    this.httpService.post('cricket/players/addPlayers', payload).subscribe({
      next: (res) => {
        alert('Team & Players Registered Successfully!');
        console.log(res);

        // Reset form
        this.teamForm.reset();
        this.players.clear();
        for (let i = 0; i < 12; i++) {
          this.addPlayer();
        }
      },
      error: (err) => {
        console.error('Error saving players:', err);
      }
    });
  } 
  else {
    alert('Please fill all required fields');
  }
}




}
