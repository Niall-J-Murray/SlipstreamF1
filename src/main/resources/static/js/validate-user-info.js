const validate = () => {
    const username = document.getElementById('username');
    const email = document.getElementById('email');
    const password = document.getElementById('password');
    const confirmPassword = document.getElementById('confirm-password');
    const formMessage = document.getElementById('form-message');

    if (username.value.trim() === "") {
        formMessage.innerText = "Please enter your username";
        username.focus();
        return false;
    }

    if (!isNotBlank(username.value)) {
        formMessage.innerText = "Please enter a valid username";
        username.focus();
        return false;
    }

    if (email.value.trim() === "") {
        formMessage.innerText = "Please enter your email address";
        email.focus();
        return false;
    }

    if (!emailIsValid(email.value)) {
        formMessage.innerText = "Please enter a valid email address";
        email.focus();
        return false;
    }

    if (password.value.trim() === "") {
        formMessage.innerText = "Passwords must be 6+ characters";
        password.focus();
        return false;
    }

    if (password.value.size < 6) {
        formMessage.innerText = "Passwords must be 6+ characters";
        password.focus();
        return false;
    }

    if (password.value !== confirmPassword.value) {
        formMessage.innerText = "Passwords do not match, please try again";
        confirmPassword.focus();
        return false;
    }

    return true;
}

const emailIsValid = email => {
    return /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email);
}

const isNotBlank = team => {
    return /^[A-Za-z0-9 _]*[A-Za-z0-9][A-Za-z0-9 _]*$/.test(team);
}

