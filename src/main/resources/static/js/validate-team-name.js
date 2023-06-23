const validateTeam = () => {
    const teamName = document.getElementById('teamName');
    const teamNameMessage = document.getElementById('team-name-message');

    if (teamName.value.trim() === "") {
        teamNameMessage.innerText = "Please choose a team name";
        teamName.focus();
        return false;
    }

    if (!teamIsValid(teamName.value)) {
        teamNameMessage.innerText = "Please choose a team name";
        teamName.focus();
        return false;
    }

    return true;
}

const teamIsValid = team => {
    return /^[A-Za-z0-9 _]*[A-Za-z0-9][A-Za-z0-9 _]*$/.test(team);
}


