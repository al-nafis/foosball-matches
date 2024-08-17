# Foosball Matches App
<img src="https://github.com/user-attachments/assets/d97e68c6-c8fb-40d8-8efb-e9d7e94b96ac" width="200" />

This app demonstrates the skill set that includes Kotlin, Android Jetpack, RxJava2, MVVM, Dagger, Room etc.

## Notes
  - On RxJava2 subscriptions, I used ```Schedulers.single()``` on ```observerOn()``` instead of ```AndroidSchedulers.mainThread()``` since there were difficulties adding the dependency(RxAndroid2).
  - The interfaces ```PlayersRepository``` and ```MatchesRepository``` are the blueprints of the repository implementation. Using them, we can come up with different solutions to data storage. For now, Room is being used to store data locally with one of the implementation(```PlayersDatabaseManager``` and ```MatchesDatabaseManager```). For future, we can utilize this interface to create an API integration to store data remotely

## Screen Definition and Design Justification
  - Matches
      - Lists the matches 
  - Add New Match:
      - A tied score is not allowed, thus user cannot submit with a tied score
  - Match Edit:
      - Re-selecting the players is not allowed
      - Deleting a match also updates the associated wins, losses and total played values of the players
  - Ranking:
      - Sorting by wins, losses and most played matches
  - Players
      - List the players
  - Add New Player:
      - Duplicated ID is not allowed
  - Edit Player:
      - Editing employee id is not allowed
      - Deleting a player also deletes the matches the player participated, but it does not affect any other players' stats
  - Settings:
      - Developer Settings to generate sample data or clear the database

## Room for Improvements
  - Moving common codes into Base classes for Activities and Fragments
  - For ranking screen: daily, weekly, and monthly ranking view
  - Inline Error messages
  - Confirmation prompts during data deletion
  - A general message when a screen has no list items

## Screenshots

### Matches Screen
<img src="https://github.com/user-attachments/assets/e905f65c-305b-4f03-a890-1df3d0bf78c0" width="300" />

### Add New Match
<img src="https://github.com/user-attachments/assets/9192416e-d591-415d-8cc2-8a78a2205b87" width="300" />

### Edit Match (player id info section is disabled for this screen)
<img src="https://github.com/user-attachments/assets/d330f4c2-2ebf-4c03-8fb7-7a101f53af8a" width="300" />

### Ranking
<img src="https://github.com/user-attachments/assets/779947f9-99de-4c94-95dc-a6797da84c17" width="300" />

### Ranking Sorting Type
<img src="https://github.com/user-attachments/assets/2813bd32-d807-4f9d-ab59-0305d9499659" width="300" />

### Players
<img src="https://github.com/user-attachments/assets/4687d911-e71b-4467-ac13-2033dd9cee42" width="300" />

### Add New Player
<img src="https://github.com/user-attachments/assets/70170555-c073-407d-801f-b6dd142eed67" width="300" />

### Edit Player(plauyer id section is disabled in this screen)
<img src="https://github.com/user-attachments/assets/96351945-513d-4155-882b-c9d6ac186fb8" width="300" />

### Settings(quick options to poulate with sample data and clear it)
<img src="https://github.com/user-attachments/assets/cdb934a2-2733-42b7-b135-48a366538fca" width="300" />
