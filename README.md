<h2>Introduction:</h2>

Musiqle was created to provide a fun and challenging game to all music lovers! The game has two path options that will test your knowledge of artist's album covers or your knowledge of song lyrics. Each path will be able to track your progress and save your scores in our database. There are many ways to compete against yourself or compare yourself with other players. We hope you enjoy our game!

This backend was created with Java using the SpringBoot framework. The intended use of this backend is to make API calls to MusixMix and Spotify to gain access to song, artist, lyrics and album information to be used in the game. Postgres is used as the back-end database to ensure streamline data storage for each user to save their progress in the game. 

<br>
<h2>Features:</h2>

<b>Metrics the user will have access to in the database:</b>

  <ul>
    <li>ID
    <li>Name 
    <li>Current score
    <li>Total score
    <li>Best score in a streak
    <li>Longest streak
    <li>Best score for game type - Artist
    <li>Best score for game type - Song 
  </ul>

<br>

<b>RESTFUL API with CRUD Methods</b>

<ul>
  <li>Create/Post: Create new players
  <li>Read/Get: Get all players / Get players by id
  <li>Update/Patch: Update the current score, total score, best score in streak, longest streak, best score for game based on type (artist or song)
  <li>Delete a player
</ul>

<br>
<b>SPOTIFY API</b>

<ul>
  <li>Create a Spotify access token
  <li>Retrieve list of genres
  <li>Retrieve list of playlists
  <li>Retrieve tracks from selected playlist
  <li>Retrieve artist and name of track
  <li>Retrieve user's playlists
</ul>

<br>
<b>MUSIX MATCH API</b>

<ul>
  <li>Utilizes Musix Match API Token
  <li>Retrieve track id
  <li>Retrieve lyrics for specific track
  <li>Retrieves artist and name of track
  <li>Get Top 100 popular songs 
</ul>

<h2>Dependencies:</h2>

<ul>
  <li>Java version 17 or higher
  <li>Must have a postgresql Account
  <li>Create a MusixMix API Account
  <li>https://developer.musixmatch.com/
  <li>Create a Spotify API Account 
  <li>https://developer.spotify.com/documentation/web-api
</ul>
