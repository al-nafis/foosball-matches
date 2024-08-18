# Foosball Matches App
<img src="https://github.com/user-attachments/assets/d97e68c6-c8fb-40d8-8efb-e9d7e94b96ac" width="200" />

This app showcases a skill set that includes Kotlin, Android Jetpack, RxJava2, MVVM, Dagger, Room, and more.

## Notes
  - In RxJava2 subscriptions, ```Schedulers.single()``` was used on ```observerOn()``` instead of ```AndroidSchedulers.mainThread()``` due to difficulties adding the RxAndroid2 dependency.
The interfaces ```PlayersRepository``` and ```MatchesRepository``` serve as blueprints for repository implementation. These interfaces allow for various data storage solutions. Currently, Room is used for local data storage via the ```PlayersDatabaseManager``` and ```MatchesDatabaseManager``` implementations. In the future, these interfaces can be utilized for API integration to store data remotely.

## Screen Definition and Design Justification
### Matches
- List Matches: Displays the list of matches.
- Add New Match: Tied scores are not allowed, so submission with a tied score is prevented.
- Edit Match: Re-selecting players is not permitted. Deleting a match updates the associated players' wins, losses, and total played values.
### Players
- List Players: Displays the list of players.
- Add New Player: Duplicate IDs are not allowed.
- Edit Player: Editing the employee ID is not permitted. Deleting a player also removes the matches they participated in, without affecting other players' stats.
### Ranking
- Ranking: Sorts by wins, losses, and most played matches.
### Settings
- Developer Settings: Provides options to generate sample data or clear the database.

## Room for Improvements
- Add daily, weekly, and monthly ranking views for the ranking screen.
- Implement inline error messages.
- Add confirmation prompts during data deletion.

## Screenshots

### Matches Screen
<img src="https://github.com/user-attachments/assets/cd868edc-1d6d-478c-b5be-df30d7f44abe" width="300" />

### Add New Match
<img src="https://github.com/user-attachments/assets/9192416e-d591-415d-8cc2-8a78a2205b87" width="300" />

### Edit Match (player id info section is disabled for this screen)
<img src="https://github.com/user-attachments/assets/d330f4c2-2ebf-4c03-8fb7-7a101f53af8a" width="300" />

### Ranking
<img src="https://github.com/user-attachments/assets/779947f9-99de-4c94-95dc-a6797da84c17" width="300" />

### Ranking Sorting Type
<img src="https://github.com/user-attachments/assets/ea0e110d-db1f-45e1-a6bd-1d5254a67474" width="300" />

### Players
<img src="https://github.com/user-attachments/assets/4687d911-e71b-4467-ac13-2033dd9cee42" width="300" />

### Add New Player
<img src="https://github.com/user-attachments/assets/70170555-c073-407d-801f-b6dd142eed67" width="300" />

### Edit Player(plauyer id section is disabled in this screen)
<img src="https://github.com/user-attachments/assets/96351945-513d-4155-882b-c9d6ac186fb8" width="300" />

### Settings(quick options to poulate with sample data and clear it)
<img src="https://github.com/user-attachments/assets/cdb934a2-2733-42b7-b135-48a366538fca" width="300" />
