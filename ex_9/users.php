<style>
  @import url('https://fonts.googleapis.com/css?family=Poppins:400,500,600,700,800,900');

  body {
    font-family: 'Poppins', sans-serif;
    font-weight: 310;
    font-size: 15px;
    line-height: 1.7;
    color: #00008B;
    background-color: #ffffff;
    overflow-x: hidden;
  }

  form {
    color: black;
  }

  .card {
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    background-color: transparent;
    border-radius: 8px;
    border-color: goldenrod;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
    padding: 16px;
    max-width: 500px;
    width: 100%;
    justify-content: center;
    align-content: center;
    align-items: center;
  }

    .ncard {
    position: absolute;
    top: 80%;
    left: 50%;
    transform: translate(-50%, -50%);
    background-color: transparent;
    border-radius: 8px;
    border-color: goldenrod;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
    padding: 16px;
    max-width: 500px;
    width: 100%;
    justify-content: center;
    align-content: center;
    align-items: center;
  }

  .card h1 {
    margin-top: 0;
    font-size: 28px;
    color: #111111;
  }

  .card p {
    color: #000000;
  }
</style>
<center><h1> Dance </h1></center>
<center><h1> dance forms Info </h1></center>
<h3>Detail retrieval using xml</h3>
<p>Retrieve Action Figure details by entering danceform ID</p>
<form method="get">
  <div class="card">
  <label for="userId">danceform ID:</label>
  <input type="text" id="id" name="id"><br>
  <button type="submit">Submit</button>
</div>
</form>

<?php
  $users = simplexml_load_file('users.xml');
  if ($users === false) {
    die('Error loading XML file');
  }
  $id = $_GET['id'];
  $user = $users->xpath("/users/user[@id='$id']");
  if (count($user) === 0) {
    die('User not found');
  }
  $user = $user[0];
  echo "<div class='ncard'>";
  echo "<h1>{$user->name}</h1>";
  echo "<p>style Name: {$user->pname}</p>";
  echo "<p>value: {$user->points}</p>";
  echo "</div>";
?>